package view;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;


import model.Server;


public class mainApp extends JFrame {

	private JButton userBtn = new JButton("Click here to launch another Client");
	
			private mainApp() {
		try {
			// Creating the frame
	        setLayout(new BorderLayout());
	        add(userBtn, BorderLayout.CENTER);

	        setTitle("Multithreaded App");
	        setSize(300,300);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setVisible(true);
	        
	        userBtn.addActionListener(e -> { LoginFrame frf= new LoginFrame();
	        									frf.setVisible(true);
			});
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}	
		 public static void main(String[] args){
				new mainApp();
				new Server();
			    LoginFrame fr = new LoginFrame();
			    fr.setVisible(true);
		
	}
		
}
