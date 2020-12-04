package model;

import java.io.*;
import java.net.*;
import java.sql.SQLException;
import java.util.*;
import java.awt.*;
import javax.swing.*;

import controller.Database;



public class Server extends JFrame {
  // Text area for displaying contents
  private JTextArea jta = new JTextArea();
  private JButton exit_btn = new JButton("Exit The App");
  private Database db = new Database();
  
  public static void main(String[] args) {
    new Server();
  }
    
  public Server() {
    // Place text area on the frame
    setLayout(new BorderLayout());
    add(new JScrollPane(jta), BorderLayout.CENTER);

    setTitle("Server");
    setSize(600, 400);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true); // It is necessary to show the frame here!

    
    exit_btn.addActionListener(e -> System.exit(0));
    
    
    try {
      // Create a server socket
      ServerSocket serverSocket = new ServerSocket(8000);
      jta.append("Server started at " + new Date() + '\n');

      while (true) {
    	  Socket s1=serverSocket.accept();
    	  if (s1.isConnected()) {
    		  jta.append("Processing...");
    	  }
    	 myClient c = new myClient(s1);
    	 c.start();
    	 }
    }
    catch(IOException ex) {
    System.err.println(ex);
    }
    } // End Server Construct
  
  
  private void getConnection(){
      try {
          db.getConnection();
          jta.append("Successfully Connected to Database");
      } catch (SQLException e) {
          jta.append("Connection to Database Failed");
          e.printStackTrace();
      }
  }


  class myClient extends Thread {
	//The socket the client is connected through
	private Socket socket;
	//The ip address of the client
	private InetAddress address;
	
	//The input and output streams to the client
	private DataInputStream inputFromClient;
	private DataOutputStream outputToClient;
			
	// The Constructor for the client
	public myClient(Socket socket) throws IOException {
	// Declare & Initialise input/output streams
		
		inputFromClient = new DataInputStream(socket.getInputStream());
		outputToClient = new DataOutputStream(socket.getOutputStream());		
	}
	
	/*
	* The method that runs when the thread starts
	*/
	public void run() {
	try {
	// Send+Receive+Calculations goes here
		while (true) {
			//Receive radius from the client
			double radius = inputFromClient.readDouble();
			
			
			//Compute Area
			double area = radius * radius * Math.PI;
			
			//Send area back to the client
			outputToClient.writeDouble(area);
			
			jta.append("Radius received from the client: " + radius + '\n');
			jta.append("Area found: " + area + '\n');
		}
				
	} catch (Exception e) {
	System.err.println(e + " on " + socket); 
	
	}
	
	}
  }
}