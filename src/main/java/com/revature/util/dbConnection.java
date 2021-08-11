package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Database Connection Factory
 */

public class dbConnection {
    //class variables for db connection
    private static final String URL = "jdbc:postgresql://database-1.ctsdg3jjkoip.us-east-2.rds.amazonaws.com/postgres?currentSchema=Bank";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "BigCheese";
    private static Connection connection;

    //returns the connection to the database
    public static Connection getConnection(){
        try {
            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

}

//TODO SETUP SOME KIND OF CREDENTIAL HIDING
//Environment Variables
//Properties Files
