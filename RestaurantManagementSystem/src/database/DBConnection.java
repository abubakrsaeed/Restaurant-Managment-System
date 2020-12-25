package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//import main.User; 

public class DBConnection {

//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		Connection conn = createConnection();
////		createNewUser(conn, 456, "Lahore", 6445);
//	}
	public static Connection createConnection() {
		
		String con_driver = "com.mysql.jdbc.Driver";
		String con_localHost = "localhost:3306";
		String con_databaseName = "rms_database";
		String con_userName = "root";
		String con_password = "";
	
		Connection connection = null;
		try {
			Class.forName(con_driver);
			connection=DriverManager.getConnection("jdbc:mysql://"+con_localHost+"/"+con_databaseName+"",con_userName,con_password);	
		}catch(Exception e1) {
			System.out.println(e1);
		}
		System.out.println(connection);
		return connection;
	}
	
	public static void createNewUser(Connection conn, String name, String username, String password, String usertype) {
		try {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO users VALUES(?,?,?,?);");
			
			ps.setString(1, name);
			ps.setString(2, username);
			ps.setString(3, password);
			ps.setString(4, usertype);

			
			int x = ps.executeUpdate();
			if (x > 0) {
				System.out.println("Successful");
			} else {
				System.out.println("Not successful");
			}
		} catch(Exception e1) {
			System.out.println(e1);
		}
	}

//	public static boolean login(String userName, String password) {	
//		return true;
//	}
//	
	
	public String authenticateUser(Connection con, String username, String password, String usertype)
	{
	    
		//copy string parameters
		
	    Statement statement = null;
	    ResultSet resultSet = null;
	 
	    String usernameDB = "";
	    String passwordDB = "";
	    String usertypeDB = "";
	 
	    try
	    {
	        statement = con.createStatement();
	        resultSet = statement.executeQuery("SELECT username, password, usertype FROM users");
	 
	        while(resultSet.next())
	        {
	        	usernameDB = resultSet.getString("username");
	            passwordDB = resultSet.getString("password");
	            usertypeDB = resultSet.getString("usertype");
	 
	            if(username.equals(usernameDB) && password.equals(passwordDB) && usertypeDB.equals("admin"))
	            	return "Manager";
	            else if(username.equals(usernameDB) && password.equals(passwordDB) && usertypeDB.equals("employee"))
	            	return "Employee";
	        }
	    }
	    catch(SQLException e)
	    {
	        e.printStackTrace();
	    }
	    return "Invalid user credentials";
	}
	
	
	
        public static ResultSet getAllItems(Connection con) throws SQLException
	{

		Statement statement = null;
	    ResultSet resultSet = null;
	 
	    try
	    {
	        statement = con.createStatement();
	        resultSet = statement.executeQuery("SELECT id, name, price FROM menu");
	    } catch(SQLException e)
	    {
	        e.printStackTrace();
	    }
	    
	    return resultSet;
	}
	
	public static ResultSet getAllUsers(Connection con) throws SQLException
	{

		Statement statement = null;
	    ResultSet resultSet = null;
	    
	    try
	    {
	        statement = con.createStatement();
	        resultSet = statement.executeQuery("SELECT name, username, usertype FROM users");
	    }
	    catch(SQLException e)
	    {
	        e.printStackTrace();
	    }
	    
	    return resultSet;
	}	
	
}
