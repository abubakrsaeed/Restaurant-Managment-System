package main;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.DBConnection;

public abstract class User {

	private String name;
	private String username;
	private String password;
	private int salary;
	private String usertype;
	private DBConnection dbObj;

	//constructor
	public User(String name,String username,String password,int salary,String usertype) {
		this.name = name;
		this.username = username;
		this.password = password;
		 this.salary = salary;
		 this.usertype = usertype;
	}
	
	
	//getters
	public String getlName() { return name; }
	public String getUsername() { return username; }
	public String getPassword() { return password; }
	public int getSalary() { return salary; }
	public String getUserType() { return usertype; }
	
	
	
	
	
	
	//methods
//	1. authenticateUser 	
	public String authenticateUser(String username, String password, String usertype) throws SQLException {
		DBConnection db= new DBConnection();
		db.init();
		
		String userType;
		
		userType=db.getUserType(username, password);
		
		return userType;
		
	} 
	
//	2. getAllItems 
	public ArrayList<ArrayList<String>> getAllItems() throws SQLException {

		DBConnection db= new DBConnection();
		db.init();

		ArrayList<ArrayList<String>> menu = new ArrayList<ArrayList<String>> ();
		menu=db.getAllItems();
		
		return menu;
	}

	

		
	


}
