package view;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
//import controller.database;
import javax.swing.border.EmptyBorder;

import controller.Database;
import controller.Server;
import model.Client;



public class LoginFrame extends JFrame implements ActionListener  {
	
	
    private static final long serialVersionUID = 1L;
    private JTextField studentId;
    
    private JButton loginBtn, openRegBtn;
//    private JButton openRegBtn;
//    private JButton clearBtn;
//    private JLabel label;
    private JPanel contentPane;
    private Database conn;
    private final String tableName = "students";
    
    
    	
public static void main(String[] args) {
	try {
	LoginFrame logFr = new LoginFrame();
	logFr.setVisible(true);
}
	catch (Exception e) {
		e.printStackTrace();
	}
}
	
//	public static void main(String[] args) {
//	EventQueue.invokeLater(new Runnable() {
//        public void run() {
//            try {
//                LoginFrame fr = new LoginFrame();
//                fr.setVisible(true);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        });
//}

/**
 * Creating the frame.
 */
public LoginFrame() {
	
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(450, 190, 1014, 597);
    setResizable(false);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);

    
    //Login Label
    
    JLabel lblNewLabel = new JLabel("Login AoC");
    lblNewLabel.setForeground(Color.BLACK);
    lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 46));
    lblNewLabel.setBounds(423, 13, 273, 93);
    contentPane.add(lblNewLabel);

    studentId = new JTextField();
    studentId.setFont(new Font("Tahoma", Font.PLAIN, 32));
    studentId.setBounds(481, 170, 281, 68);
    contentPane.add(studentId);
    studentId.setColumns(10);

    //Student ID Label
    
    JLabel studID = new JLabel("Student ID");
    studID.setBackground(Color.BLACK);
    studID.setForeground(Color.BLACK);
    studID.setFont(new Font("Tahoma", Font.PLAIN, 31));
    studID.setBounds(250, 166, 193, 52);
    contentPane.add(studID);


    loginBtn = new JButton("Login");
    loginBtn.setFont(new Font("Tahoma", Font.PLAIN, 26));
    loginBtn.setBounds(245, 392, 162, 73);
    
    loginBtn.addActionListener(new ActionListener() {
       	
        public void actionPerformed(ActionEvent e) {
            String stud_id = studentId.getText();
//            String firstN = fName.getText();
//            String lastN = lName.getText();
            
                        
            //method already in another class
            try {
            	Connection db = Database.getConnection();

                PreparedStatement st = (PreparedStatement) 
                		db.prepareStatement("SELECT * FROM students WHERE STUD_ID=?");

                st.setString(1, stud_id);
                
                ResultSet rs = st.executeQuery();
                if (rs.next()) {
//                    dispose();
                    LoginFrame logH = new LoginFrame();
                    logH.setTitle("Welcome");
                    logH.setVisible(true);
                    JOptionPane.showMessageDialog(loginBtn, "Welcome!" );   //MUST CHECK PROJECT SPEC
                    Client cl = new Client();
                    Server svr = new Server();
                } else {
                    JOptionPane.showMessageDialog(loginBtn, "Sorry" + stud_id , "You are not a registered Student. Bye.", JOptionPane.ERROR_MESSAGE);
                }
            } 
   
            catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
    });

    contentPane.add(loginBtn);
      
    openRegBtn = new JButton("Register");
    openRegBtn.setFont(new Font("Tahoma", Font.PLAIN, 26));
    openRegBtn.setBounds(545, 392, 162, 73);

    openRegBtn.setFocusable(false);
    openRegBtn.addActionListener(this);
    contentPane.add(openRegBtn);
    
}

@Override
public void actionPerformed(ActionEvent e) {
	try {
	if(e.getSource()==openRegBtn) {
//		 Client cl = new Client();
		 RegisterFrame regFr = new RegisterFrame();
			regFr.setVisible(true);
	}
	
}catch (Exception exc) {
	exc.printStackTrace();
}
}
	
}   
   

  
		
		
		
		
		
	
