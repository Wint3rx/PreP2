package org.example.Ejercicio1.Database.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion_BD {
    private static final String URL = "jdbc:mysql://localhost:3306/preparcial";
    private static final String USER = "root";
    private static final String PASSWORD = "Franklin23030917@";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;

    }
}
