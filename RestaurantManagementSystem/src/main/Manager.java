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
		if ( getDB().searchItem(id, "menu") ) 
			return getDB().deleteItem(id, "menu");
		else return false;
	}

	public boolean updateItem(int id, String name, int price) {
		if ( getDB().searchItem(id, "menu") ) 
			return getDB().updateItem(name, price, id);
		else return false;
	
	public boolean createNewEmployee(String name, String username, String password, int salary) {
		if ( !getDB().searchEmployee(username) ) 
			return getDB().createNewUser(name, username, password, salary, "employee");
		else return false;
	}
	
	

	public boolean deleteEmployee(String username) {
		if ( getDB().searchEmployee(username) ) 
			return getDB().deleteEmployee(username);
		else return false;
	}
	

	public boolean updateEmployee(String name,  String password, int salary, String update_username) {
		if ( getDB().searchEmployee(update_username) ) 
			return getDB().updateEmployee(name, password, salary, "employee", update_username);
		else return false;
	}
	
	
	public String[][] getAllEmployees() throws SQLException {

		ResultSet rs = getDB().getAllEmployees();

		ArrayList<String[]> rowList = new ArrayList<String[]>();
		int r = 0;

		try {
			while(rs.next()) {
				rowList.add(new String[] { rs.getString("name"), rs.getString("username"), String.valueOf(rs.getInt("salary")) });
				r++;
			}
		} catch (SQLException e) { e.printStackTrace(); }

		int c = 3;
		return makeTable(rowList, r, c);

				String[][] data =  new String[r][c];
			
				for (int i = 0; i < r; i++)
					for (int j = 0; j < c; j++)
						data[i][j] = rowList.get(i)[j];

				return data;
	}

	
}
