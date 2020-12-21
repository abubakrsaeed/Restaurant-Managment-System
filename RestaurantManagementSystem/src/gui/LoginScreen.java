package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class LoginScreen extends Frame implements WindowListener, ActionListener {
	String[][] credentials = new String[][]{{"sefasenlik", "12345", "adm"}, {"aliveli", "56789", "emp"}}; 
	String[] menu_items = new String[]{"Burger", "Coke", "Water", "Pizza", "Fries", "Lahmacun"};
	ArrayList<String> orders = new ArrayList<String>();
	
	TextField tf_uname = new TextField(20);
	JPasswordField pf_pass = new JPasswordField(18);
	JLabel jl_uname = new JLabel("Username:"); 
	JLabel jl_pass = new JLabel("Password:");   
	Button b_login;
	
	Button b_submit_orders;
	
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
			
			for(int i=0; i<credentials.length; ++i){
				if(credentials[i][0].contentEquals(i_uname) && credentials[i][1].contentEquals(i_pass)) {
					System.out.println("Login success!");
					if(credentials[i][2].contentEquals("adm"))
						loginSuccessAdmin();
					else
						loginSuccessEmployee();
					return;
				}
			}
			JOptionPane.showMessageDialog(this,
				    "User not found! Incorrect username or password.",
				    "Login Failed",
				    JOptionPane.ERROR_MESSAGE);
		} else if(e.getSource() == b_submit_orders) {
			for(int i=0; i<orders.size(); ++i){
				System.out.println(orders.get(i));
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
	
	public void loginSuccessEmployee() {
		new EmployeeOrderScreen("Employee Order Interface");
	}
	
	public void loginSuccessAdmin() {
		
	}
}
