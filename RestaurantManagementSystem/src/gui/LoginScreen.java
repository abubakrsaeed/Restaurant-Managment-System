package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class LoginScreen extends Frame implements WindowListener, ActionListener {
	String[][] credentials = new String[][]{{"sefasenlik", "12345"}, {"aliveli", "56789"}}; 
	
	TextField tf_uname = new TextField(20);
	TextField tf_pass = new TextField(20);
	JLabel jl_uname = new JLabel("Username:"); 
	JLabel jl_pass = new JLabel("Password:");   
	Button b_login;
	
	public static void main(String[] args) {
		LoginScreen myWindow = new LoginScreen("Login Screen");
	    myWindow.setSize(300, 120);
	    myWindow.setVisible(true);
	}
	
	public LoginScreen(String title) {
	    super(title);
	    setLayout(new FlowLayout());
	    addWindowListener(this);
	    b_login = new Button("Login");
	    add(jl_uname);
	    add(tf_uname);
	    add(jl_pass);
	    add(tf_pass);
	    add(b_login);
	    b_login.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		
		//init a connection to database
	
		DBConnection d = new DBConnection();
        d.init();
        String i_uname = tf_uname.getText();
        String i_pass = MD5.getMd5(tf_pass.getText());
        
		
		//check type of user and user existince 
		
		for(int i=0; i<credentials.length; ++i){
			if(d.getManager(i_uname,i_pass)||d.getEmployee(i_uname,i_pass)) {
				System.out.print("Login success!");
				loginSuccess();
				return;
			}
		}
		loginFailure();
	}
	
	public void windowClosing(WindowEvent e) {
		dispose();
//	    System.exit(0);
	}
	
	public void windowOpened(WindowEvent e) {}
	public void windowActivated(WindowEvent e) {}
	public void windowIconified(WindowEvent e) {}
	public void windowDeiconified(WindowEvent e) {}
	public void windowDeactivated(WindowEvent e) {}
	public void windowClosed(WindowEvent e) {}
	
	public void loginSuccess() {
		LoginScreen myPopupWindow = new LoginScreen("Login Info");
		myPopupWindow.removeAll();
		myPopupWindow.setSize(300, 80);
		JLabel jl_info = new JLabel("Success!"); 
		myPopupWindow.add(jl_info);
		myPopupWindow.setVisible(true);
	}
	
	public void loginFailure() {
		LoginScreen myPopupWindow = new LoginScreen("Login Info");
		myPopupWindow.removeAll();
		myPopupWindow.setSize(300, 80);
		JLabel jl_info = new JLabel("User not found! Incorrect username or password."); 
		myPopupWindow.add(jl_info);
		myPopupWindow.setVisible(true);
	}
}
