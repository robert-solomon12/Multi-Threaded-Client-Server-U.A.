package controller;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.*;

public class Database {

	
//	private final String userName = "root";
//	/** The password for the MySQL account (or empty for anonymous) */
//	private final String password = "admin";
//	/** The name of the computer running MySQL */
//	private final String serverName = "localhost";
//	/** The port of the MySQL server (default is 3306) */
//	private final int portNumber = 3306;
//	/** The name of the database we are testing with (this default is installed with MySQL) */
//	private final String tableName = "students";
//	
//	private final String dbName = "assign2";
	
	/*
	 * Connection method call to execute connection to my WAMP Server Database
	 */
	public static Connection getConnection() throws SQLException {
		
     	Connection conn;

		try {	
			/*
			 * Connection String containing 3 parameters to establish connectivity to my Database WAMP SERVER (URL, USERNAME, PASSWORD)
			 */
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/assign2?serverTimezone=UTC","root","admin");
			
			//Printing a successful connection message if a successful connection has been established. 
			System.out.println("Connection to Database has been successfully established!");
			
//			JOptionPane.showMessageDialog(null, "Connection to Database has been successfully established!");
			
			//			consoleFeedback.setText("Connection to Database has been successfully established!");
			return conn;
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			System.out.println("Error: " + e.getMessage());
			return null;
		}
		// TODO: handle exception
	}
}
	
