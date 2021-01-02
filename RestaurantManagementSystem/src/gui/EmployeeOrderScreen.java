package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

import database.DBConnection;

public class EmployeeOrderScreen extends Frame implements WindowListener, ActionListener {
	String employee_username;
	
	ArrayList<ArrayList<String>> menu_items;
	ArrayList<String> orders = new ArrayList<String>();
	
	Button b_submit_orders;
	
	DBConnection mydb;
	
	public static void main(String[] args) {
		new EmployeeOrderScreen("Employee Order Interface");
	}
	
	public EmployeeOrderScreen(String title) {
	    super(title);
	    setLayout(new GridLayout(2,0));
	    addWindowListener(this);
	    setSize(300, 300);
	    
	    JPanel scrollPanel = new JPanel();
	    scrollPanel.setLayout(new GridLayout(0,1));
	    JScrollPane scrollFrame = new JScrollPane(scrollPanel);
	    
	    mydb = new DBConnection();
	    mydb.init();
	    
	    menu_items = mydb.getAllItems();
		
		for(int i=0; i<menu_items.size(); ++i){
			String item_label = menu_items.get(i).get(0) + " " + menu_items.get(i).get(1) + " " + menu_items.get(i).get(2);
			JCheckBox cb_item_checkbox = new JCheckBox(item_label);
			scrollPanel.add(cb_item_checkbox);
			
			int index = i;
			cb_item_checkbox.addItemListener(new ItemListener() {    
				public void itemStateChanged(ItemEvent e) {
					if(!orders.contains(menu_items.get(index).get(1))) {
						orders.add(menu_items.get(index).get(1));
					}
				}    
	        });
		}
		scrollFrame.revalidate();
		scrollFrame.repaint();
		add(scrollFrame);
		
		b_submit_orders = new Button("Submit Orders");
		b_submit_orders.addActionListener(this);
		add(b_submit_orders);
		
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == b_submit_orders) {
			for(int i=0; i<orders.size(); ++i){
				System.out.println(orders.get(i));
				System.out.println("Database updated: " + mydb.addToOrders(orders.get(i), 1, employee_username));
			}
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
