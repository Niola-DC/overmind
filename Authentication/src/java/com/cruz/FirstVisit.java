/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.cruz;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Donna
 */
public class FirstVisit extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response, RequestDispatcher dispatcher)
            throws ServletException, IOException {
        Connection conn = null;
//        String name;
        PreparedStatement pstmt = null;
        PrintWriter out = response.getWriter();
        try {
            DataConnector dConnector = new DataConnector();
            // Get a connection from the DataConnector
            conn = dConnector.getConnection();
//            conn = {connected.zero};
//            name = sc.next();
//          name = Chichi;
            // Get user details from request
            String userName = request.getParameter("name");
            String userEmail = request.getParameter("email");
            String password1 = request.getParameter("password");            
            String password2 = request.getParameter("re-password");
            
//            Get Session ID
            HttpSession session = request.getSession();
            String sessionId = session.getId();
            
//          Authenticate password1 = password2
            String mss ="Password Mismatch";
            String userPassword = password1.equals(password2) ? password1: mss;
//            String encryptPass = scrambleString(userPassword);
           
            // SQL query to insert data
            String sql = "INSERT INTO users (userName, email, userPassword) VALUES (?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,userName);
            pstmt.setString(2, userEmail);
            pstmt.setString(3, userPassword);
//            pstmt.setString(2, );

            // Execute the insertion
            int row = pstmt.executeUpdate();
            

            // Provide feedback to the user
            if (row > 0) {
                out.println("Data has been saved successfully!");
                Thread.sleep(2000);
                dispatcher = request.getRequestDispatcher("/hello.html");
                dispatcher.forward(request, response);
            } else {
                out.println("Failed to save data.");
            }
        } catch (SQLException | NumberFormatException | NamingException e) {
            e.printStackTrace();
            out.println("An error occurred: " + e.getMessage());
        } catch (InterruptedException ex) {
            Logger.getLogger(FirstVisit.class.getName()).log(Level.SEVERE, null, ex);
        } 
        finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */

    }
    
    public static String scrambleString(String input) {
        // Convert the input string to a list of characters
        List<Character> characters = new ArrayList<>();
        for (char c : input.toCharArray()) {
            characters.add(c);
        }

        // Shuffle the list
        Collections.shuffle(characters);

        // Convert the list back to a string
        StringBuilder scrambled = new StringBuilder();
        for (char c : characters) {
            scrambled.append(c);
        }

        return scrambled.toString();
    }
}
