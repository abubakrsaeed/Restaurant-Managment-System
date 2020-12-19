package main;

import java.util.ArrayList;
import database.DBConnection;

public class Manager extends User {

	private String username;
	private String password;
	private DBConnection connection_;
	
	
	public Manager() {
		// ??
	}
	
	public Manager(String username_, String password_) {
		username = username_;
		password = password_;
		//DB CONNECTION ?
	}
	
	//extended methods
	
	public ArrayList<String> getData(DBConnection con) {
	
		return null;
	}

	
	//class methods
	public void addNewItem() {
		
	}
	
	public void addEmployee() {
	
	}
}