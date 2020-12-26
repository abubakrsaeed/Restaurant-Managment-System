package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class AdminMenuScreen extends Frame implements WindowListener, ActionListener {
	ArrayList<String> menu_items = new ArrayList<String>(Arrays.asList("Burger", "Coke", "Water", "Pizza", "Fries", "Lahmacun"));
	int subject_item_index = 0;
	TextField tf_uiname; // Text field that holds the updated name
	TextField tf_ciname; // Text field that holds the created name
	Button b_change;
	Button b_create;
	
	Button b_remove_item;
	Button b_modify_item;
	Button b_create_item;
    JPanel scrollPanel = new JPanel();
    JScrollPane scrollFrame = new JScrollPane(scrollPanel);
	
	public static void main(String[] args) {
		new AdminMenuScreen("Admin Menu Management Interface");
	}
	
	public AdminMenuScreen(String title) {
	    super(title);
	    setLayout(new GridLayout(4,0));
	    addWindowListener(this);
	    setSize(200, 600);
	    
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
			menu_items.remove(subject_item_index);
			populateScrollPane();
		} else if(e.getSource() == b_modify_item) {
			AdminMenuScreen modifyPopUp = new AdminMenuScreen("Modify Item");
			modifyPopUp.removeAll();
			modifyPopUp.setLayout(new FlowLayout());
			modifyPopUp.setSize(400, 80);
			
			JLabel jl_iname = new JLabel("Item Name:"); 
			modifyPopUp.add(jl_iname);
			tf_uiname = new TextField(20);
			modifyPopUp.add(tf_uiname);
			b_change = new Button("Change");
			b_change.addActionListener(this);
			modifyPopUp.add(b_change);
		} else if(e.getSource() == b_create_item){
			AdminMenuScreen createPopUp = new AdminMenuScreen("Create Item");
			createPopUp.removeAll();
			createPopUp.setLayout(new FlowLayout());
			createPopUp.setSize(400, 80);
			
			JLabel jl_iname = new JLabel("Item Name:"); 
			createPopUp.add(jl_iname);
			tf_ciname = new TextField(20);
			createPopUp.add(tf_ciname);
			b_create = new Button("Create");
			b_create.addActionListener(this);
			createPopUp.add(b_create);
		} else if(e.getSource() == b_change) {
			menu_items.set(subject_item_index, tf_uiname.getText());
			populateScrollPane();
		} else if(e.getSource() == b_create) {
			menu_items.add(tf_ciname.getText());
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
		for(int i=0; i<menu_items.size(); ++i){
			JRadioButton rb_item_radiobutton = new JRadioButton(menu_items.get(i));
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
