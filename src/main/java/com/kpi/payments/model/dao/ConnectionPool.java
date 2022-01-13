package com.kpi.payments.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class ConnectionPool {
    private static final String URL;
    private static final String USER;
    private static final String PASSWORD;

    static {
        ResourceBundle resource = ResourceBundle.getBundle("database");
        URL = resource.getString("url");
        USER = resource.getString("user");
        PASSWORD = resource.getString("password");
    }

    private ConnectionPool() {

    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException exception) {
            throw new RuntimeException("Error getting DB connection", exception);
        }
    }

    public static void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException exception) {
            throw new RuntimeException("Error closing DB connection", exception);
        }
    }
}
