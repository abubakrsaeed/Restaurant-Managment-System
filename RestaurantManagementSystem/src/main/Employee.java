package main;

import java.util.Random;

import database.DBConnection;

public class Employee extends User {

	public Employee(String name, String username, String password, int salary, String usertype) {
		super(name, username, password, salary, usertype);
	}

	//helper
	private String generateRandomString() {
		Random rnd = new Random();
		int number = rnd.nextInt(999999);
		return String.format("%06d", number);
	}


	public boolean updatePrice(String name, int price, int update_id) {
		DBConnection db= new DBConnection();
		db.init();
		
		boolean var=db.updateItem( name, price,update_id);
		
		return var;
	}

	public void createOrder() {
		DBConnection db= new DBConnection();
		db.init();
		
		
		//those should been taken from the GUI
		int orderNo = 0; Employee obj = null;int qty = 0;String name = null;
		
		String placedBy=obj.getlName();
		
		Order n=new Order(orderNo,obj,qty,name);
		
		db.createNewOrder(orderNo, name, qty, placedBy);
		
		
	
	}
}
