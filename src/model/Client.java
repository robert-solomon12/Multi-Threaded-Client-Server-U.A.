package model;

import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Client extends JFrame {
  // Text field for receiving radius
  private JTextField jtf = new JTextField();

  // Text area to display contents
  private JTextArea jta = new JTextArea();
  
  private JButton sd = new JButton();
  
  // IO streams
  private DataOutputStream toServer;
  private DataInputStream fromServer;
  private JButton sdBtn;
  private JButton exitBtn;
  
  
  public static void main(String[] args) {
    new Client();
  }

  public Client() { 
	// Place text area on the frame
	    setLayout(new BorderLayout());
	    add(new JScrollPane(jta), BorderLayout.CENTER);
	     
	    // Panel contentP to hold the label and text field
	    JPanel p = new JPanel();
	    p.setLayout(new BorderLayout());
	    p.add(new JLabel("Enter radius"), BorderLayout.WEST);
	    p.add(jtf, BorderLayout.CENTER);
	    jtf.setHorizontalAlignment(JTextField.RIGHT);

	    setLayout(new BorderLayout());
	    add(p, BorderLayout.SOUTH);
	    add(new JScrollPane(jta), BorderLayout.CENTER);

	    setTitle("Area of Circle");
	    setSize(500, 600);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setVisible(true); // It is necessary to show the frame here!

	    
	    sdBtn = new JButton("Send");
	    sdBtn.setFont(new Font("Tahoma", Font.PLAIN, 10));
	    sdBtn.setBounds(130, 190, 50, 60);
	    
	    sdBtn.addActionListener(new ActionListener() {


		@Override
		public void actionPerformed(ActionEvent arg0) {
		   
			      try {
			        // Get the radius from the text field
			        double radius = Double.parseDouble(jtf.getText().trim());

			        // Send the radius to the server
			        toServer.writeDouble(radius);
			       

			        // Get area from the server
			        double area = fromServer.readDouble();

			        // Display to the text area
			        jta.append("Radius is " + radius + "\n");
			        jta.append("Area received from the server is "
			          + area + '\n');
			      }
			      
			      //Handling IOException on 1st level
			      catch (IOException ex) {
			    	  
			    	 // printing the stacktrace
			       System.err.println(ex);
			       
			      }
			      
			      //Handling the NumberFormatException error on 2nd level
			      catch (NumberFormatException numFex) {
				        System.err.println(numFex);
				        System.out.println("Invalid Int, please Enter an Integer.");
				      }
			    }
		}
    );
  p.add(sdBtn, BorderLayout.AFTER_LINE_ENDS); // BorderLayout.AFTER_LAST_LINE);
  
  exitBtn = new JButton("Exit");
  exitBtn.setFont(new Font("Tahoma", Font.PLAIN, 10));
  exitBtn.setBounds(130, 390, 350, 360);
  
  exitBtn.addActionListener(new ActionListener() {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		//ServerClientTest. //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	  
  });
  p.add(exitBtn, BorderLayout.AFTER_LAST_LINE);
  
  }
}