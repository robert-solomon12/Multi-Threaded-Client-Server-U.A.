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

  // IO streams
  private DataOutputStream toServer;
  private DataInputStream fromServer;
  private JButton sdBtn;
  private JButton exitBtn;

  

  public Client() {
    // Panel p to hold the label and text field
    JPanel p = new JPanel();
    p.setLayout(new BorderLayout());
    p.add(new JLabel("Enter radius"), BorderLayout.WEST);
    p.add(jtf, BorderLayout.CENTER);
    jtf.setHorizontalAlignment(JTextField.RIGHT);

    setLayout(new BorderLayout());
    add(p, BorderLayout.SOUTH);
    add(new JScrollPane(jta), BorderLayout.CENTER);


    setTitle("Client-1");
    setSize(500, 300);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true); // It is necessary to show the frame here!
    
    
    sdBtn = new JButton("Send");
    sdBtn.setFont(new Font("Tahoma", Font.PLAIN, 10));
    sdBtn.setBounds(130, 190, 50, 60);
    p.add(sdBtn, BorderLayout.AFTER_LINE_ENDS); // BorderLayout.AFTER_LAST_LINE);
    try {
      // Create a socket to connect to the server
      Socket socket = new Socket("localhost", 8000);
      // Socket socket = new Socket("130.254.204.36", 8000);
      // Socket socket = new Socket("drake.Armstrong.edu", 8000);

      // Create an input stream to receive data from the server
      fromServer = new DataInputStream(socket.getInputStream());

      // Create an output stream to send data to the server
      toServer = new DataOutputStream(socket.getOutputStream());
    }
    catch (IOException ex) {
      jta.append(ex.toString() + '\n');
    }
  
  sdBtn.addActionListener(new ActionListener() {

		    @Override
		    public void actionPerformed(ActionEvent e) {
		      try {
		        // Get the radius from the text field
		        double radius = Double.parseDouble(jtf.getText().trim());

		        // Send the radius to the server
		        toServer.writeDouble(radius);
		        toServer.flush();
System.out.print("I'm being clicked!");
		        // Get area from the server
		        double area = fromServer.readDouble();

		        // Display to the text area
		        jta.append("Radius is " + radius + "\n");
		        jta.append("Area received from the server is "
		          + area + '\n');
		      }
		      //Handling I0Exception Thread on the 1st level check
		      catch (IOException ex) {
		        System.err.println(ex);
		      }
		   
			      
			      //Handling the NumberFormatException error on the 2nd level
			      catch (NumberFormatException numFex) {
				        System.err.println(numFex);
				       
				        JOptionPane.showMessageDialog(sdBtn," Invalid Int, please Enter an Integer",null, JOptionPane.ERROR_MESSAGE);
				        System.out.println("Invalid Int, please Enter an Integer.");
				      }
			    }
	  }
  );

  exitBtn = new JButton("Exit");
  exitBtn.setFont(new Font("Tahoma", Font.PLAIN, 26));
  exitBtn.setBounds(305, 270, 162, 73);
  exitBtn.addActionListener(new ActionListener() {
      
  public void actionPerformed(ActionEvent e) {

System.exit(0);
}
});
  
 p.add(exitBtn, BorderLayout.AFTER_LAST_LINE);
}
  }
