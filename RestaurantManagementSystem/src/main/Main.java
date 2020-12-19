package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException; 
import java.io.InputStreamReader;
import java.sql.SQLException;

import database.DBConnection; 

public class Main {

	public static void instantiateJDBC() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static String readConfig(DBConnection database_connection) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("properties.conf"));
		try {
			String ip = reader.readLine();
			String port = reader.readLine();
			String username = reader.readLine();
			String password = reader.readLine();

			String url = "jdbc:mysql://"+ip+":"+port+","+username+","+password;

			return url;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			reader.close();
		}
		return null;
	}

	
	public static int login(String username_, String password_, DBConnection con) {
		
		return 0;
	}	

	public static void main(String[] args) throws Exception {
		
		DBConnection database_connection = null;
		instantiateJDBC();
		
		String username; 
		String password;
		
		String url = readConfig(database_connection);

		try {
			database_connection = new DBConnection(url);
			database_connection.post("USE sql12383017;");

			BufferedReader reader =   new BufferedReader(new InputStreamReader(System.in));
			username = reader.readLine();
			password = reader.readLine();
			
			User user = new User(username, password, database_connection);
			
			

		} catch (SQLException e) {
			System.out.println("connection failed");
			//e.printStackTrace();
		} finally {
			if(database_connection != null){
				database_connection.close();
			}
		}
		
		


	}

}
