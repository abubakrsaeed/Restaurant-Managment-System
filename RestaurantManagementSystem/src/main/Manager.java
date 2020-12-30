package main;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.DBConnection;

public class Manager extends User {
	
	public Manager(String name, String username, String password, int salary, String usertype, DBConnection dbObj) {
		super(name, username, password, salary, usertype, dbObj);
	}

	public boolean deleteItem(int id, String table) {
		if (getDB().searchItem(id, table)) 
			return getDB().deleteItem(id, table); 
		else return false;
	}
	
	public boolean createNewItem(int id, String name, int price) {
		if ( !getDB().searchItem(id, "menu") ) 
			return getDB().createNewItem(id, name, price);
		else return false;
	}	

	public boolean deleteItemFromMenu(int id) {
		return deleteItem(id, "menu");
//		if ( getDB().searchItem(id, "menu") ) 
//			return getDB().deleteItem(id, "menu");
//		else return false;
	}

	public boolean updateItem(int id, String name, int price) {
		if ( getDB().searchItem(id, "menu") ) 
			return getDB().updateItem(name, price, id);
		else return false;
	}

	
}
