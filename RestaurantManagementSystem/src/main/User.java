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
	public User(String name, String username, String password, int salary, String usertype, DBConnection dbObj) {
		setName(name);
		setUsername(username);
		setPassword(password);
		setSalary(salary);
		setDB(dbObj);
	}
	
	
	//setters
	public void setName(String name) { this.name = name; }
	public void setUsername(String username) { this.username = username; }
	public void setPassword(String password) { this.password = password; }
	public void setSalary (int salary) { this.salary = salary; }
	public void setUserType(String usertype) { this.usertype = usertype; }
	public void setDB (DBConnection dbObj) { this.dbObj = dbObj; }

	//getters
	public String getlName() { return name; }
	public String getUsername() { return username; }
	public String getPassword() { return password; }
	public int getSalary() { return salary; }
	public String getUserType() { return usertype; }
	public DBConnection getDB() { return dbObj; }
	
	
	//helper
	public String[][] makeTable(ArrayList<String[]> arr,int r, int c) {	
		String[][] data =  new String[r][c];
		for (int i = 0; i < r; i++)
			for (int j = 0; j < c; j++)
				data[i][j] = arr.get(i)[j];
		return data;
	}
	
	
	//methods
//	1. authenticateUser 	
	public String authenticateUser(String username, String password, String usertype) throws SQLException {
			return dbObj.authenticateUser(username, password, usertype);
			//returns manger, employee or failed
	} 
	
//	2. getAllItems : 2D String Array
	public String[][] getAllItems() throws SQLException {

		ResultSet rs = dbObj.getAllItems(); //check

		ArrayList<String[]> rowList = new ArrayList<String[]>();
		int r = 0;

		try {
			while(rs.next()) {
				rowList.add(new String[] { rs.getString("id"), rs.getString("name"), String.valueOf(rs.getInt("price"))});
				r++;
			}
		} catch (SQLException e) { e.printStackTrace(); }

		//
		int c = 3; //size of columns from table - fixed
		return makeTable(rowList, r, c);
//		String[][] data =  new String[r][c];
////		data = new String[r][c];
//		
//		for (int i = 0; i < r; i++)
//			for (int j = 0; j < c; j++)
//				data[i][j] = rowList.get(i)[j];
//		
//		return data;
	}

	
//	3. deleteItem (will be used by Employee )
//	public boolean deleteItem(int id, String table) {
//		if (dbObj.searchItem(id, table)) 
//			return dbObj.deleteItem(id, table); 
//		else return false;
//	}


}
