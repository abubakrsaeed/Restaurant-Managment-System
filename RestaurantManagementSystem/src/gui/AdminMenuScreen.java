package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.ArrayList;
import java.util.Arrays;

import database.DBConnection;

public class AdminMenuScreen extends Frame implements WindowListener, ActionListener {
	ArrayList<ArrayList<String>> menu_items;
	
	int subject_item_index = 0;
	int subject_item_id = 0;
	
	TextField tf_uiid; 		// Text field that holds the updated id
	TextField tf_uiname; 	// Text field that holds the updated name
	TextField tf_uiprice; 	// Text field that holds the updated price
	
	TextField tf_ciid; 		// Text field that holds the created id
	TextField tf_ciname; 	// Text field that holds the created name
	TextField tf_ciprice; 	// Text field that holds the created price
	
	Button b_change;
	Button b_create;
	
	Button b_remove_item;
	Button b_modify_item;
	Button b_create_item;
    JPanel scrollPanel = new JPanel();
    JScrollPane scrollFrame = new JScrollPane(scrollPanel);
    
	DBConnection mydb;
	
	public static void main(String[] args) {
		new AdminMenuScreen("Admin Menu Management Interface");
	}
	
	public AdminMenuScreen(String title) {
	    super(title);
	    setLayout(new GridLayout(4,0));
	    addWindowListener(this);
	    setSize(300, 600);
	    
	    scrollPanel.setLayout(new GridLayout(0,1));
		
		add(populateScrollPane());
		
		b_remove_item = new Button("Remove Item");
		b_remove_item.addActionListener(this);
		add(b_remove_item);
		b_modify_item = new Button("Modify Item");
		b_modify_item.addActionListener(this);
		add(b_modify_item);
		b_create_item = new Button("Create a New Item");
		b_create_item.addActionListener(this);
		add(b_create_item);
		
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == b_remove_item) {
			mydb.deleteItem(Integer.parseInt(menu_items.get(subject_item_index).get(0)));
			populateScrollPane();
		} else if(e.getSource() == b_modify_item) {
			AdminMenuScreen modifyPopUp = new AdminMenuScreen("Modify Item");
			modifyPopUp.removeAll();
			modifyPopUp.setLayout(new FlowLayout());
			modifyPopUp.setSize(300, 150);
			
			subject_item_id = Integer.parseInt(menu_items.get(subject_item_index).get(0));
			
			JLabel jl_iid = new JLabel("Item ID:"); 
			modifyPopUp.add(jl_iid);
			tf_uiid = new TextField(20);
			tf_uiid.setText(menu_items.get(subject_item_index).get(0));
			modifyPopUp.add(tf_uiid);
			JLabel jl_iname = new JLabel("Item Name:"); 
			modifyPopUp.add(jl_iname);
			tf_uiname = new TextField(20);
			tf_uiname.setText(menu_items.get(subject_item_index).get(1));
			modifyPopUp.add(tf_uiname);
			JLabel jl_iprice = new JLabel("Item Price:"); 
			modifyPopUp.add(jl_iprice);
			tf_uiprice = new TextField(20);
			tf_uiprice.setText(menu_items.get(subject_item_index).get(2));
			modifyPopUp.add(tf_uiprice);
			
			b_change = new Button("Change");
			b_change.addActionListener(this);
			modifyPopUp.add(b_change);
		} else if(e.getSource() == b_create_item){
			AdminMenuScreen createPopUp = new AdminMenuScreen("Create Item");
			createPopUp.removeAll();
			createPopUp.setLayout(new FlowLayout());
			createPopUp.setSize(300, 150);
			
			JLabel jl_iid = new JLabel("Item ID:"); 
			createPopUp.add(jl_iid);
			tf_ciid = new TextField(20);
			createPopUp.add(tf_ciid);
			JLabel jl_iname = new JLabel("Item Name:"); 
			createPopUp.add(jl_iname);
			tf_ciname = new TextField(20);
			createPopUp.add(tf_ciname);
			JLabel jl_iprice = new JLabel("Item Price:"); 
			createPopUp.add(jl_iprice);
			tf_ciprice = new TextField(20);
			createPopUp.add(tf_ciprice);
			
			b_create = new Button("Create");
			b_create.addActionListener(this);
			createPopUp.add(b_create);
		} else if(e.getSource() == b_change) {
			mydb.updateItem(Integer.parseInt(tf_uiid.getText()), tf_uiname.getText(), Double.parseDouble(tf_uiprice.getText()), subject_item_id);
			populateScrollPane();
		} else if(e.getSource() == b_create) {
			mydb.createNewItem(Integer.parseInt(tf_ciid.getText()), tf_ciname.getText(), Double.parseDouble(tf_ciprice.getText()));
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
	    mydb = new DBConnection();
	    mydb.init();
	    
	    menu_items = mydb.getAllItems();
	    
		scrollPanel.removeAll();
		ButtonGroup bg_group = new ButtonGroup();
		for(int i=0; i<menu_items.size(); ++i){
			String item_label = menu_items.get(i).get(0) + " " + menu_items.get(i).get(1) + " " + menu_items.get(i).get(2);
			JRadioButton rb_item_radiobutton = new JRadioButton(item_label);
			rb_item_radiobutton.setSelected(true);
			bg_group.add(rb_item_radiobutton);
			scrollPanel.add(rb_item_radiobutton);
			
			int index = i;
			rb_item_radiobutton.addItemListener(new ItemListener() {    
				public void itemStateChanged(ItemEvent e) {
					subject_item_index = index;
				}    
	        });
		}
		scrollFrame.revalidate();
		scrollFrame.repaint();
		
		return scrollFrame;
	}
}
