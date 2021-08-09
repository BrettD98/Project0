package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbConnection {
    private static final String URL = "jdbc:postgresql://database-1.ctsdg3jjkoip.us-east-2.rds.amazonaws.com/postgres?currentSchema=Bank";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "BigCheese";
    private static Connection connection;

    public static Connection getConnection(){
        try {
            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

}

/*
envoirnment variables
System.getenv(String name);

do properties file
add file with .properties extention
	key value pairs
*/