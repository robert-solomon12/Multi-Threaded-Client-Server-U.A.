package controller;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;

public class Database {

	/*
	 * Connection method call to execute connection to my WAMP Server Database
	 */
	public static Connection getConnection() throws SQLException {
		
     	Connection conn;

			/*
			 * Connection String containing 3 parameters to establish connectivity to my Database WAMP SERVER (URL, USERNAME, PASSWORD)
			 */
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/assign2?serverTimezone=UTC","root","admin");
			
			//Printing a successful connection message if a successful connection has been established. 
			System.out.println("Connection to Database has been successfully established!");
			return conn;
		}
}


	
