package view;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.*;
//import controller.database;
import javax.swing.border.EmptyBorder;

import controller.Database;
import model.Client;




public class LoginFrame extends JFrame implements ActionListener  {
	
	
    private static final long serialVersionUID = 1L;
    private JTextField studentId;
    
    private JButton loginBtn;
    private JButton exitBtn;
    private JPanel contentPane;

    	
public static void main(String[] args) {
	try {
	LoginFrame logFr = new LoginFrame();
	logFr.setVisible(true);
}
	catch (Exception e) {
		e.printStackTrace();
	}
}

/**
 * Creating the frame.
 */
public LoginFrame() {
	
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setSize(600,400);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);

    
    //Login Label
    
    JLabel lblNewLabel = new JLabel("Login AoC");
    lblNewLabel.setForeground(Color.BLACK);
    lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 46));
    lblNewLabel.setBounds(100, 13, 273, 93);
    contentPane.add(lblNewLabel);

    studentId = new JTextField();
    studentId.setFont(new Font("Tahoma", Font.PLAIN, 32));
    studentId.setBounds(250, 170, 200, 30);
    contentPane.add(studentId);
    studentId.setColumns(10);

    //Student ID Label
    
    JLabel studID = new JLabel("Enter Student ID");
    studID.setBackground(Color.BLACK);
    studID.setForeground(Color.BLACK);
    studID.setFont(new Font("Tahoma", Font.PLAIN, 25));
    studID.setBounds(60, 155, 200, 52);
    contentPane.add(studID);

//exitBtn.addActionListener(e -> System.exit(0));
//contentPane.add(exitBtn);

    loginBtn = new JButton("Login");
    loginBtn.setFont(new Font("Tahoma", Font.PLAIN, 26));
    loginBtn.setBounds(150, 270, 162, 73);

    loginBtn.addActionListener(new ActionListener() {
    
        public void actionPerformed(ActionEvent e) {
            String stud_id = studentId.getText();
           
            try {
            	Connection db = Database.getConnection();

                PreparedStatement st = (PreparedStatement) 
               
               db.prepareStatement("SELECT * FROM students WHERE STUD_ID=?");		   
               db.prepareStatement("UPDATE students SET TOT_REQ + 1 WHERE STUD_ID=?");
               
                st.setString(1, stud_id);
               
               ResultSet rs = st.executeQuery();
               if(rs.next()) {

            	    String firstN = rs.getString("FNAME");
                    String lastN = rs.getString("SNAME");
         
                    JOptionPane.showMessageDialog(loginBtn,"Welcome " + firstN + " " + lastN + "... You are now connected to the Server");
                    new Client();
                } else {
                    JOptionPane.showMessageDialog(loginBtn, "Sorry " + stud_id + " " + "You are not a registered Student. Try again or exit!", null, JOptionPane.ERROR_MESSAGE);
                }
            }
            //Catching sql Exception thread
            catch (SQLException sqlException) {
                //sqlException.printStackTrace();
                System.out.println("Could not Login ...sql exception issue");
            }
        }
    });

    contentPane.add(loginBtn);
      
}

@Override
public void actionPerformed(ActionEvent arg0) {
	// TODO Auto-generated method stub
	
}
}