package com.company.utils;

import com.company.Constants;


import java.sql.*;


public class DatabaseConnection {

    private static DatabaseConnection instance;
    private final Connection connection;
    private final String url = "jdbc:mysql://localhost:3306/mvcstat2?useSSL=false";
    private final String username = "statmvc";
    private final String password = "111";

    private DatabaseConnection() throws SQLException {
        this.connection = DriverManager.getConnection(url, username, password);
    }

    public Connection getConnection() {
        return connection;
    }


    public static DatabaseConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new DatabaseConnection();
        } else if (instance.getConnection().isClosed()) {
            instance = new DatabaseConnection();
        }

        return instance;
    }

    public static void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                System.err.println(Constants.CANT_CLOSE_RS_ERROR_MES);
            }
        }

    }

    public static void closeStatement(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                System.err.println(Constants.CANT_CLOSE_ST_ERROR_MES);
            }
        }
    }


    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println(Constants.CANT_CLOSE_CONN_ERROR_MES);
            }
        }
    }

}