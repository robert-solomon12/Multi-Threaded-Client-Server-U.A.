package view;


import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.Database;



public class RegisterFrame extends JFrame {
	
	
	     private static final long serialVersionUID = 1L;
	     private JPanel contentPane;
	     private JTextField fName;
	     private JTextField lName;
	     private JTextField studId;
	     
	     private JButton regBtn;
	     
//	     RegisterFrame regFr = new RegisterFrame();
	     
	     /**
	      * Creating the frame.
	      */
	     public static void main(String[] args) {
	    	    new RegisterFrame();
	    	  }
	     
	     
	     public RegisterFrame() {
	        // setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\User\\Desktop\\STDM.jpg"));
	         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	         setBounds(450, 190, 1014, 597);
	         setResizable(false);
	         contentPane = new JPanel();
	         contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	         setContentPane(contentPane);
	         contentPane.setLayout(null);

	         JLabel lbStudentRegister = new JLabel("Student Registry");
	         lbStudentRegister.setFont(new Font("Times New Roman", Font.PLAIN, 25));
	         lbStudentRegister.setBounds(362, 52, 325, 50);
	         contentPane.add(lbStudentRegister);

	         JLabel fNameLabel = new JLabel("First Name");
	         fNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
	         fNameLabel.setBounds(58, 152, 99, 43);
	         contentPane.add(fNameLabel);

	         JLabel lNameLabel = new JLabel("Last Name");
	         lNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
	         lNameLabel.setBounds(58, 243, 110, 29);
	         contentPane.add(lNameLabel);
	         
	         JLabel studentIdLabel = new JLabel("Student Id");
	         studentIdLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
	         studentIdLabel.setBounds(58, 334, 110, 29);
	         contentPane.add(studentIdLabel); 
	         
	         fName = new JTextField();
	         fName.setFont(new Font("Tahoma", Font.PLAIN, 32));
	         fName.setBounds(214, 151, 228, 50);
	         contentPane.add(fName);
	         fName.setColumns(10);

	         lName = new JTextField();
	         lName.setFont(new Font("Tahoma", Font.PLAIN, 32));
	         lName.setBounds(214, 235, 228, 50);
	         contentPane.add(lName);
	         lName.setColumns(10);

	         studId = new JTextField();
	         studId.setFont(new Font("Tahoma", Font.PLAIN, 32));
	         studId.setBounds(214, 320, 228, 50);
	         contentPane.add(studId);
	         studId.setColumns(10);
	         
	         
	         regBtn = new JButton("Register");
	         regBtn.addActionListener(new ActionListener() {
	        	 
	        	 
	             public void actionPerformed(ActionEvent e) {
//	            	 dispose();
	                 String firstName = fName.getText();
	                 String lastName = lName.getText();
	                 String studIds = studId.getText();
	                 int len = studIds.length();
	                 
//	                 String TOT_REQ = totReq.getText();
	                 
	                 String msg = "" + firstName + "" + lastName;
	                 msg += " \n";
	                 if (len != 10) {
	                     JOptionPane.showMessageDialog(regBtn, "Enter a valid student Id");
	                 }

	                 try {
	                	 Connection db = Database.getConnection();

	                     String query = "INSERT INTO students values('" + firstName + "','" + lastName + "','" + studIds + "')"; //TOTAL REQ ...INCREMENT BY ONE TO INCLUDE IN DB EXECUTION!
	                     
	                     Statement sta = db.createStatement();
	                     int x = sta.executeUpdate(query);
	                     if (x == 0) {
	                         JOptionPane.showMessageDialog(regBtn, "This user alredy exists!");
	                         
	                       //TOTAL REQ ...INCREMENT BY ONE TO INCLUDE IN DB EXECUTION HERE AND SHOW IN DB!
	                     } else {
	                         JOptionPane.showMessageDialog(regBtn,
	                             "Welcome, " + msg + "Your account is sucessfully created");
	                     }
	                     System.out.println("Record inserted into Database!");
	                     db.close();
	                 } catch (Exception exception) {
	                     exception.printStackTrace();
	                 }
	             }
	         });
	         regBtn.setFont(new Font("Tahoma", Font.PLAIN, 22));
	         regBtn.setBounds(399, 447, 259, 74);
	         contentPane.add(regBtn);
	     }
	 }