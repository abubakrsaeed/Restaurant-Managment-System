package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class AdminEmployeeScreen extends Frame implements WindowListener, ActionListener {
	ArrayList<String> employees = new ArrayList<String>(Arrays.asList("sefasenlik", "aliveli", "testemp", "testadm"));
	int subject_emp_index = 0;
	TextField tf_uename; // Text field that holds the updated name
	TextField tf_cename; // Text field that holds the created name
	Button b_change;
	Button b_create;
	
	Button b_remove_emp;
	Button b_modify_emp;
	Button b_create_emp;
    JPanel scrollPanel = new JPanel();
    JScrollPane scrollFrame = new JScrollPane(scrollPanel);
	
	public static void main(String[] args) {
		new AdminEmployeeScreen("Admin Staff Management Interface");
	}
	
	public AdminEmployeeScreen(String title) {
	    super(title);
	    setLayout(new GridLayout(4,0));
	    addWindowListener(this);
	    setSize(200, 600);
	    
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
			employees.remove(subject_emp_index);
			populateScrollPane();
		} else if(e.getSource() == b_modify_emp) {
			AdminMenuScreen modifyPopUp = new AdminMenuScreen("Modify Employee");
			modifyPopUp.removeAll();
			modifyPopUp.setLayout(new FlowLayout());
			modifyPopUp.setSize(400, 80);
			
			JLabel jl_iname = new JLabel("Employee Name:"); 
			modifyPopUp.add(jl_iname);
			tf_uename = new TextField(20);
			modifyPopUp.add(tf_uename);
			b_change = new Button("Change");
			b_change.addActionListener(this);
			modifyPopUp.add(b_change);
		} else if(e.getSource() == b_create_emp){
			AdminMenuScreen createPopUp = new AdminMenuScreen("Register Employee");
			createPopUp.removeAll();
			createPopUp.setLayout(new FlowLayout());
			createPopUp.setSize(400, 80);
			
			JLabel jl_iname = new JLabel("Employee Name:"); 
			createPopUp.add(jl_iname);
			tf_cename = new TextField(20);
			createPopUp.add(tf_cename);
			b_create = new Button("Create");
			b_create.addActionListener(this);
			createPopUp.add(b_create);
		} else if(e.getSource() == b_change) {
			employees.set(subject_emp_index, tf_uename.getText());
			populateScrollPane();
		} else if(e.getSource() == b_create) {
			employees.add(tf_cename.getText());
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
		for(int i=0; i<employees.size(); ++i){
			JRadioButton rb_item_radiobutton = new JRadioButton(employees.get(i));
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
