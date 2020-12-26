package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AdminMainScreen extends Frame implements WindowListener, ActionListener {
	Button b_manage_menu;
	Button b_manage_employees;
	
	public static void main(String[] args) {
		AdminMainScreen myWindow = new AdminMainScreen("Administration Interface");
	}
	
	public AdminMainScreen(String title) {
	    super(title);
	    setLayout(new GridLayout(2,0));
	    addWindowListener(this);
	    setSize(300, 300);
	    
	    b_manage_menu = new Button("Manage Menu Items");
	    b_manage_menu.addActionListener(this);
	    b_manage_employees = new Button("Manage Employees");
	    b_manage_employees.addActionListener(this);
	    
	    add(b_manage_menu);
	    add(b_manage_employees);
	    
	    setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == b_manage_menu) {
			new AdminMenuScreen("Admin Menu Management Interface");
		} else if (e.getSource() == b_manage_employees) {
			new AdminEmployeeScreen("Admin Staff Management Interface");
		}
	}
	
	public void windowClosing(WindowEvent e) {
		dispose();
	}
	
	public void windowOpened(WindowEvent e) {}
	public void windowActivated(WindowEvent e) {}
	public void windowIconified(WindowEvent e) {}
	public void windowDeiconified(WindowEvent e) {}
	public void windowDeactivated(WindowEvent e) {}
	public void windowClosed(WindowEvent e) {}
}
