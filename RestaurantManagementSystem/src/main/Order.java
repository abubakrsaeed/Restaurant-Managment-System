package main;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import database.DBConnection;

public class Order {

	private int order_no;
	private Employee emp;
	private int qty;
	private String name;
	
	public Order(int orderNo, Employee obj,int qty,String name) {
		order_no = orderNo;
		emp = obj;
		this.qty=qty;
		this.name=name;
	}
	
	public boolean addOrder( String name, int qty) {
		DBConnection db= new DBConnection();
		db.init();	
		boolean var=false;
		var=db.addToOrder(order_no, name, qty, emp.getName());
				
				return var;	
	}
	
	public boolean updateOrderQty( int id, int qty ) {
		DBConnection db= new DBConnection();
		db.init();	
		boolean var=false;
		var=db.updateOrderQty(qty, id, qty);
		return var;
	}
	
	public boolean deleteOrder(int id) {
		DBConnection db= new DBConnection();
		db.init();	
		boolean var=false;
		var=db.deleteOrder(order_no, id);
		return var;
	}
	
	public ArrayList<String> viewCurrentOrder() throws SQLException {
		
		DBConnection db= new DBConnection();
		db.init();	
		
		ResultSet rs = db.getCurrentOrder(order_no); 
		ArrayList<String> rowList = new ArrayList<String>();
		int r = 0;

		try {
			while(rs.next()) {
				
				 String id = rs.getString("id");
	                String name = rs.getString("name");
	                int qty = rs.getInt("qty");
	               
	                ArrayList<String> tempData = new ArrayList<>();
	               
	                tempData.add(id);
	                tempData.add(name);
	                tempData.add(Integer.toString(qty));
				
			}
		} catch (SQLException e) { e.printStackTrace(); }

		//
		int c = 3; //size of columns from table - fixed
		return rowList;
	
	}
}
