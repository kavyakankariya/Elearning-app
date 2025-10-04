/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lab607
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class DatabaseConnection {
private static final String DB_URL = "jdbc:mysql://localhost:3305/user_accounts";
private static final String USER = "root";
private static final String PASS = ""; // Your MySQL password, if you have one

public static Connection getConnection() {
Connection conn = null;
try {
conn = DriverManager.getConnection(DB_URL, USER, PASS);
} catch (SQLException e) {
JOptionPane.showMessageDialog(null, "Failed to connect to the database.",
"Database Error", JOptionPane.ERROR_MESSAGE);
e.printStackTrace();
}

return conn;
}
}