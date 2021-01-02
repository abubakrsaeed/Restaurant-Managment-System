package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.ArrayList;
import java.util.Arrays;

import database.DBConnection;

public class AdminEmployeeScreen extends Frame implements WindowListener, ActionListener {
	ArrayList<ArrayList<String>> employees;
	
	int subject_emp_index = 0;
	String subject_emp_olduname = "";
	
	TextField tf_uename; 		// Text field that holds the updated name
	TextField tf_ueusername;	// Text field that holds the updated user name
	TextField tf_uepass;		// Text field that holds the updated password
	TextField tf_uesalary;		// Text field that holds the updated salary
	TextField tf_uetype;		// Text field that holds the updated user type
	
	TextField tf_cename; 		// Text field that holds the created name
	TextField tf_ceusername;	// Text field that holds the created user name
	TextField tf_cepass;		// Text field that holds the created password
	TextField tf_cesalary;		// Text field that holds the created salary
	TextField tf_cetype;		// Text field that holds the created user type
	
	Button b_change;
	Button b_create;
	
	Button b_remove_emp;
	Button b_modify_emp;
	Button b_create_emp;
    JPanel scrollPanel = new JPanel();
    JScrollPane scrollFrame = new JScrollPane(scrollPanel);
    
    DBConnection mydb;
	
	public static void main(String[] args) {
		new AdminEmployeeScreen("Admin Staff Management Interface");
	}
	
	public AdminEmployeeScreen(String title) {
	    super(title);
	    setLayout(new GridLayout(4,0));
	    addWindowListener(this);
	    setSize(400, 600);
	    
	    scrollPanel.setLayout(new GridLayout(0,1));
		
		add(populateScrollPane());
		
		b_remove_emp = new Button("Remove Employee");
		b_remove_emp.addActionListener(this);
		add(b_remove_emp);
		b_modify_emp = new Button("Modify Employee");
		b_modify_emp.addActionListener(this);
		add(b_modify_emp);
		b_create_emp = new Button("Register a New Employee");
		b_create_emp.addActionListener(this);
		add(b_create_emp);
		
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == b_remove_emp) {
			if(employees.get(subject_emp_index).get(4).equals("employee")) {
				mydb.deleteUser(employees.get(subject_emp_index).get(1));
				populateScrollPane();
			} else {
				JOptionPane.showMessageDialog(this,
					    "You are not allowed to delete a manager account.",
					    "Restricted Query",
					    JOptionPane.ERROR_MESSAGE);	
			}
		} else if(e.getSource() == b_modify_emp) {
			AdminMenuScreen modifyPopUp = new AdminMenuScreen("Modify Employee");
			modifyPopUp.removeAll();
			modifyPopUp.setLayout(new FlowLayout());
			modifyPopUp.setSize(300, 220);
			
			subject_emp_olduname = employees.get(subject_emp_index).get(1);
			
			JLabel jl_iname = new JLabel("Name:"); 
			modifyPopUp.add(jl_iname);
			tf_uename = new TextField(20);
			tf_uename.setText(employees.get(subject_emp_index).get(0));
			modifyPopUp.add(tf_uename);
			JLabel jl_iusername = new JLabel("User Name:"); 
			modifyPopUp.add(jl_iusername);
			tf_ueusername = new TextField(20);
			tf_ueusername.setText(employees.get(subject_emp_index).get(1));
			modifyPopUp.add(tf_ueusername);
			JLabel jl_ipass = new JLabel("Password:"); 
			modifyPopUp.add(jl_ipass);
			tf_uepass = new TextField(20);
			tf_uepass.setText(employees.get(subject_emp_index).get(2));
			modifyPopUp.add(tf_uepass);
			JLabel jl_isalary = new JLabel("Salary:"); 
			modifyPopUp.add(jl_isalary);
			tf_uesalary = new TextField(20);
			tf_uesalary.setText(employees.get(subject_emp_index).get(3));
			modifyPopUp.add(tf_uesalary);
			JLabel jl_itype = new JLabel("Position:"); 
			modifyPopUp.add(jl_itype);
			tf_uetype = new TextField(20);
			tf_uetype.setText(employees.get(subject_emp_index).get(4));
			modifyPopUp.add(tf_uetype);
			
			b_change = new Button("Change");
			b_change.addActionListener(this);
			modifyPopUp.add(b_change);
		} else if(e.getSource() == b_create_emp){
			AdminMenuScreen createPopUp = new AdminMenuScreen("Register Employee");
			createPopUp.removeAll();
			createPopUp.setLayout(new FlowLayout());
			createPopUp.setSize(300, 220);
			
			JLabel jl_iname = new JLabel("Name:"); 
			createPopUp.add(jl_iname);
			tf_cename = new TextField(20);
			createPopUp.add(tf_cename);
			JLabel jl_iusername = new JLabel("User Name:"); 
			createPopUp.add(jl_iusername);
			tf_ceusername = new TextField(20);
			createPopUp.add(tf_ceusername);
			JLabel jl_ipass = new JLabel("Password:"); 
			createPopUp.add(jl_ipass);
			tf_cepass = new TextField(20);
			createPopUp.add(tf_cepass);
			JLabel jl_isalary = new JLabel("Salary:"); 
			createPopUp.add(jl_isalary);
			tf_cesalary = new TextField(20);
			createPopUp.add(tf_cesalary);
			JLabel jl_itype = new JLabel("Position:"); 
			createPopUp.add(jl_itype);
			tf_cetype = new TextField(20);
			createPopUp.add(tf_cetype);
			
			b_create = new Button("Create");
			b_create.addActionListener(this);
			createPopUp.add(b_create);
		} else if(e.getSource() == b_change) {
			mydb.updateUser(tf_uename.getText(), tf_ueusername.getText(), tf_uepass.getText(), Integer.parseInt(tf_uesalary.getText()), tf_uetype.getText(), subject_emp_olduname);
			populateScrollPane();
		} else if(e.getSource() == b_create) {
			mydb.createNewUser(tf_cename.getText(), tf_ceusername.getText(), tf_cepass.getText(), Integer.parseInt(tf_cesalary.getText()), tf_cetype.getText());
			populateScrollPane();
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
	
	public JScrollPane populateScrollPane() {	
		scrollPanel.removeAll();
		ButtonGroup bg_group = new ButtonGroup();
		
		mydb = new DBConnection();
	    mydb.init();
		
		employees = mydb.getAllEmployees();
		
		for(int i=0; i<employees.size(); ++i){
			String emp_label = employees.get(i).get(0) + " - " +
						 	   employees.get(i).get(1) + " - " +
						 	   employees.get(i).get(2) + " - " +
						 	   employees.get(i).get(3) + " - " +
						 	   employees.get(i).get(4); 
			JRadioButton rb_item_radiobutton = new JRadioButton(emp_label);
			rb_item_radiobutton.setSelected(true);
			bg_group.add(rb_item_radiobutton);
			scrollPanel.add(rb_item_radiobutton);
			int index = i;
			
			rb_item_radiobutton.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					subject_emp_index = index;
				}    
	        });
		}
		scrollFrame.revalidate();
		scrollFrame.repaint();
		
		return scrollFrame;
	}
}
