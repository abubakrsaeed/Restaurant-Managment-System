package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;

import database.DBConnection;

public class Manager {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub`

		User user = new User();
		
		user.setFullName(getName());
		//System.out.println(user.getFullName());
		
		user.setUsername(getUsername());
		//System.out.println(user.getUsername());
		
		user.setPassword(getPassword());
		//System.out.println(user.getPassword());
		
		user.setUserType(getUserType());
		//System.out.println(user.getUserType());
		
		
		
//		//test
//		DBConnection lp = new DBConnection();
//		
//		Connection conn = lp.createConnection();
//		
//		//lp.createNewUser(conn, 123, user.getUsername(), 123123);
//		lp.createNewUser(conn, user.getFullName(), user.getUsername(), user.getPassword(), user.getUserType());
//		String auth = lp.authenticateUser(conn, "admin", "admin", "admin");		
//		//String auth = lp.authenticateUser(conn, user);
//		System.out.println(auth);
		
	}
	
	public static String getName() throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); 
		//needs changing during integration
		System.out.print("Full name: ");
	    return reader.readLine(); 
	}
	
	public static String getUsername() throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Username: ");
		return reader.readLine();
	}
	
	public static String getPassword() throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); 
		System.out.print("Password: ");
	    return reader.readLine();
	}
	
	public static String getUserType() throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); 
		System.out.print("User type (admin/employee): ");
	    return reader.readLine(); 
	}

}
