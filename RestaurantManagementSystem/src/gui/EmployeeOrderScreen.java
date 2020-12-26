package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class EmployeeOrderScreen extends Frame implements WindowListener, ActionListener {
	String[] menu_items = new String[]{"Burger", "Coke", "Water", "Pizza", "Fries", "Lahmacun"};
	ArrayList<String> orders = new ArrayList<String>();
	
	Button b_submit_orders;
	
	public static void main(String[] args) {
		new EmployeeOrderScreen("Employee Order Interface");
	}
	
	public EmployeeOrderScreen(String title) {
	    super(title);
	    setLayout(new GridLayout(2,0));
	    addWindowListener(this);
	    setSize(200, 300);
	    
	    JPanel scrollPanel = new JPanel();
	    scrollPanel.setLayout(new GridLayout(0,1));
	    JScrollPane scrollFrame = new JScrollPane(scrollPanel);
		
		for(int i=0; i<menu_items.length; ++i){
			JCheckBox cb_item_checkbox = new JCheckBox(menu_items[i]);
			scrollPanel.add(cb_item_checkbox);
			int index = i;
			
			cb_item_checkbox.addItemListener(new ItemListener() {    
				public void itemStateChanged(ItemEvent e) {
					if(!orders.contains(menu_items[index])) {
						orders.add(menu_items[index]);
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
