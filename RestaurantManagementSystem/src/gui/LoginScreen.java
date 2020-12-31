package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import database.DBConnection;

public class LoginScreen extends Frame implements WindowListener, ActionListener {	
	TextField tf_uname = new TextField(20);
	JPasswordField pf_pass = new JPasswordField(18);
	JLabel jl_uname = new JLabel("Username:"); 
	JLabel jl_pass = new JLabel("Password:");   
	Button b_login;
	
	DBConnection mydb;
	
	public static void main(String[] args) {
		LoginScreen myWindow = new LoginScreen("Login Interface");
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
	    add(pf_pass);
	    add(b_login);
	    b_login.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == b_login) {
			String i_uname = tf_uname.getText();
			String i_pass = new String(pf_pass.getPassword());
			
			mydb = new DBConnection();
			mydb.init();
			
			if(mydb.getUserType(i_uname, i_pass).equals("employee")) {
				loginSuccessEmployee(i_uname);
			} else if(mydb.getUserType(i_uname, i_pass).equals("admin")) {
				loginSuccessAdmin();
			} else {
				JOptionPane.showMessageDialog(this,
					    "User not found! Incorrect username or password.",
					    "Login Failed",
					    JOptionPane.ERROR_MESSAGE);				
			}
		}
	}
	
	public void windowClosing(WindowEvent e) {
		dispose();
	    System.exit(0);
	}
	
	public void windowOpened(WindowEvent e) {}
	public void windowActivated(WindowEvent e) {}
	public void windowIconified(WindowEvent e) {}
	public void windowDeiconified(WindowEvent e) {}
	public void windowDeactivated(WindowEvent e) {}
	public void windowClosed(WindowEvent e) {}
	
	public void loginSuccessEmployee(String username) {
		EmployeeOrderScreen empScreen = new EmployeeOrderScreen("Employee Order Interface");
		empScreen.employee_username = username;
	}
	
	public void loginSuccessAdmin() {
		new AdminMainScreen("Administration Interface");
	}
}
