package main;

import java.util.Random;

import database.DBConnection;

public class Employee extends User {

	public Employee(String name, String username, String password, int salary, String usertype, DBConnection dbObj) {
		super(name, username, password, salary, usertype, dbObj);
	}

	//helper
	private String generateRandomString() {
		Random rnd = new Random();
		int number = rnd.nextInt(999999);
		return String.format("%06d", number);
	}


	public int updatePrice(int qty, int price) {
		return qty * price;
	}

	public void createOrder() {
		
		int total = 0;
		
		int orderNo = Integer.parseInt(generateRandomString());
		while (!getDB().checkOrderNo(orderNo)) { orderNo = Integer.parseInt(generateRandomString()); }

		Menu order = new Menu(orderNo, this);
		
		String command=""; //from gui

		if (command.equals("new")) {
			//first list all items
			//get name and qty from user (radio button or whatever)
			order.addOrder( name, qty);
			total += updatePrice(qty, getDB().getPrice(name, "menu"));
		}
		else if (command.equals("update")) {
			order.viewCurrentOrder();
			//get id and qty from the above view
			order.updateOrderQty(id, qty);
		}
		else if (command.equals("delete")) {
			order.viewCurrentOrder();
			//get id from user
			order.deleteOrder(id);
		}
		else if (command.equals("submit")) {
			//destroy the menu object here and close
		}
	}
}
