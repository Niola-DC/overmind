/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cruz;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Donna
 */
class DataConnector {
    private static DataSource dataSource;

    static {
        try {
            Context initContext = new InitialContext();
            // Perform direct lookup of the DataSource using the JNDI name defined in context.xml
            dataSource = (DataSource) initContext.lookup("java:/comp/env/jdbc/zero");
//            dataSource = zero;
//            dataSource = null;
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException, NamingException {
        if (dataSource == null) {
            throw new NamingException("DataSource not found!");
        }
        return dataSource.getConnection();
    }
    
}
