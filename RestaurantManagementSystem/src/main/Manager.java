package main;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.DBConnection;

public class Manager extends User {
	
	public Manager(String name, String username, String password, int salary, String usertype) {
		super(name, username, password, salary, usertype);
	}

	public boolean deleteItem(int id, String table) {
		DBConnection db= new DBConnection();
		db.init();
		boolean var=db.deleteItem(id, table);
		return var;
	}
	
	public boolean createNewItem(int id, String name, int price) {
		DBConnection db= new DBConnection();
		db.init();
		boolean var=db.createNewItem(id, name, price);
		return var;
	}
	
	public boolean updateItem(String name, int price, int update_id) {
		DBConnection db= new DBConnection();
		db.init();
		
		boolean var=db.updateItem( name, price,update_id);
		return var;
	}
	
	boolean createNewEmployee(String name, String username, String password, int salary,String usertype) {
		DBConnection db= new DBConnection();
		db.init();
		boolean var=db.createNewUser(name, username, password, salary, usertype);
		return var;
	}
	
	boolean deleteEmployee(String username) {
		DBConnection db= new DBConnection();
		db.init();
		
		boolean var=db.deleteEmployee(username);
		
		return var;
	}
	
	
	boolean updateEmployee(String name,  String password, int salary,String usertype ,String update_username) {
		DBConnection db= new DBConnection();
		db.init();
		
		boolean var=db.updateEmployee(name, password, salary, usertype, update_username);
		
		return var;
	}
	
	
	public ArrayList<ArrayList<String>> getAllEmployees()  {
		DBConnection db= new DBConnection();
		db.init();
		
		ArrayList<ArrayList<String>> var=db.getAllEmployees();
		
		return var;
		
		
	}
	public ArrayList<ArrayList<String>> getAllItems()  {
		DBConnection db= new DBConnection();
		db.init();
		
		ArrayList<ArrayList<String>> 
		var=db.getAllItems();
		
		return var;	
	}
	
}
