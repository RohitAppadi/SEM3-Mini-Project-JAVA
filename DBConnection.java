package com.library.fine;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    // Database credentials and URL. Make sure to replace these with your actual details.
    private static final String DB_URL = "jdbc:mysql://localhost:3306/library_db";
    private static final String USER = "root"; // <-- Change this
    private static final String PASS = "Ch@ng3m3!!"; // <-- Change this

    /**
     * Establishes and returns a connection to the MySQL database.
     * @return A valid Connection object.
     * @throws SQLException if a database access error occurs.
     */
    public static Connection getConnection() throws SQLException {
        try {
            // This line is often no longer needed for modern JDBC drivers,
            // but is good practice to include for compatibility.
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("MySQL JDBC Driver not found.");
        }
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
}