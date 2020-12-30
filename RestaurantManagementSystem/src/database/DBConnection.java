package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
	
	 private static final String DB_URL = "jdbc:mysql://localhost:3306/project";
	    private static final String USER = "root";
	    private static final String PASS = "159357";

	    private static Connection con = null;
	    private static Statement stmt = null;
	    private static PreparedStatement pstmt = null;

	    public void init() {
	        try {

	            con = DriverManager.getConnection(DB_URL, USER, PASS);
	            stmt = con.createStatement();
	            System.out.println("connection initialized");
	        } catch (Exception e) {
	            e.printStackTrace();
	            System.out.println("Problem!");
	        }

	    }//end try

	    public String getUserType(String name, String pass) {
	       String var="";

	        try {
	            pstmt = con.prepareStatement("SELECT * FROM users WHERE username=? AND password=?");
	            pstmt.setString(1, name);
	            pstmt.setString(2, pass);
	            ResultSet rs = pstmt.executeQuery();
	            while (rs.next()) {
                String mEmail = rs.getString("username");	               
                String password = rs.getString("password");
	            	String userType =rs.getString("usertype");
	            
	                var=userType;

	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return var;
	    }
	    public boolean userExistince(String name, String pass) {
		       boolean var=false;

		        try {
		            pstmt = con.prepareStatement("SELECT * FROM users WHERE username=? AND password=?");
		            pstmt.setString(1, name);
		            pstmt.setString(2, pass);
		            ResultSet rs = pstmt.executeQuery();
		            while (rs.next()) {
	                String mEmail = rs.getString("username");	               
	                String password = rs.getString("password");
		            	String userType =rs.getString("usertype");
		            
		                var=true;

		            }
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		        return var;
		    }




	public boolean createNewUser(String name, String username, String password, int salary, String usertype) {
		try {
		PreparedStatement ps = con.prepareStatement("INSERT INTO users (name, username, password, salary, usertype) VALUES ( '"+name+"','"+username+"','"+password+"',"+ salary+ "','"+usertype+"');");
//
		int exec = ps.executeUpdate();
//			ps.setString(1, name);
//			ps.setString(2, username);
//			ps.setString(3, password);
//			ps.setInt(4, salary);
//			ps.setString(5, usertype);

			if ( exec == 0) {
//				System.out.println("Create new user unsuccessfull.");
				return false;
			} else {
//				System.out.println("Create new user successfull.");
				return true;
			}
		} catch(Exception e) {
//			System.out.println(e);
			return false;
		}
	}


	
	public boolean createNewItem(int id, String name, int price) {
		try {	
//			PreparedStatement ps = con.prepareStatement("INSERT INTO menu (id, name, price)  VALUES (?,?,?);");
//			ps.setString(1, id);
//			ps.setString(2, name);
//			ps.setInt(3, price);

			PreparedStatement ps = con.prepareStatement("INSERT INTO menu (id, name, price)  VALUES  ("+ id +"' ," + name + "' ,"+ price +");");
			int exec = ps.executeUpdate();
			
			if ( exec == 0) {
//				System.out.println("Create new item unsuccessfull.");
				return false;
			} else {
//				System.out.println("Create new item successfull.");
				return true;
			}
		} catch(Exception e) {
//			System.out.println(e);
			return false;
		}
	}


	public String authenticateUser(String username, String password, String usertype) throws SQLException
	{
//		Statement stmt = null;
//		ResultSet rs = null;

		try
		{
			Statement stmt = null;
			ResultSet rs = null;
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT username, password, usertype FROM users");

			while(rs.next())
			{

				if( username.equals(rs.getString("username") ) && password.equals(rs.getString("password"))) {
					if(rs.getString("usertype").equals("manager")) {
						stmt.close();
//						con.close();
						rs.close();
						return "manager";
					} else if(rs.getString("usertype").equals("employee")) {
						stmt.close();
//						con.close();
						rs.close();
						return "employee";
					}//end else-if
				}//end if
			}//end while
		}
		catch(SQLException e) { e.printStackTrace(); }
//		stmt.close();
//		rs.close();
//		con.close();
		return "failed";
	}


	public ResultSet getAllItems() throws SQLException
	{

		Statement stmt = null;
		ResultSet rs = null;

		try
		{
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT * FROM menu;");
		} catch (SQLException e) { e.printStackTrace(); }

		return rs;
	}
	
	
//	public static void getAllItems() throws SQLException {
//		
//		ResultSet rs = rs_getAllItems();
//		
//		ArrayList<String[]> rowList = new ArrayList<String[]>();
//		
//			int rows = 0;
//
//			try {
//				while(rs.next()) {
//					rowList.add(new String[] { rs.getString("id"), rs.getString("name"), String.valueOf(rs.getInt("price"))});
//					rows++;
//				}
//			} catch (SQLException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//	}
	
	

	public ResultSet getAllEmployees () throws SQLException
	{
		Statement statement = null;
		ResultSet rs = null;

		try
		{
			statement = con.createStatement();
			rs = statement.executeQuery("SELECT name, username, salary FROM users;");
		}
		catch(SQLException e) {	e.printStackTrace(); }

		return rs;
	}


	//change return to String (maybe)
	public boolean deleteEmployee(String username) {

//		int param = 0;
		try {
			PreparedStatement ps = con.prepareStatement("DELETE FROM users WHERE username LIKE ? AND usertype == 'employee';");
			ps.setString(2, username);
			int exec = ps.executeUpdate();
//			param = exec;
			System.out.println(exec);
			if (exec == 0) {
				System.out.println("Employee deletion unsuccessfull.");
				return false;
			} else {
				System.out.println("Employee deletion successfull");
				return true;
			}
		} catch(Exception e) { System.out.println(e); return false; }
	}
	
	
	public boolean searchItem (int id, String table) {

		int count = 0;
	
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
		
			statement = con.createStatement();
			resultSet = statement.executeQuery("SELECT id FROM " + table + " WHERE id = " + id + ";");
			
			while (resultSet.next()) {
//				System.out.println(count);
				count++;
			}
			if (count != 0) {
//				System.out.println("Item exsists");
				return true;
			} else {
//				System.out.println("Item does not exsists");
				return false;
			}
		} catch(Exception e) { System.out.println(e); return false; }

	}
	
	//change the return to boolean later
	public boolean deleteItem(int id, String table) {

		try {
			PreparedStatement ps = con.prepareStatement("DELETE FROM '"+ table +"' WHERE  id = " + id + ";");
			int exec = ps.executeUpdate();
			
			System.out.println(exec);
			if (ps.executeUpdate() == 0) 
				return false;
			 else  return true; 
		} catch(Exception e) {	System.out.println(e);	return false; }
	}

	
	public boolean searchEmployee(String username) {

		int count = 0;
		
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			statement = con.createStatement();
			resultSet = statement.executeQuery("Select username FROM users WHERE username like '" + username +"';");
			
			while(resultSet.next()) {
//				System.out.println(count);
				count++;
			}
			if (count != 0) {
//				System.out.println("User exsists.");
				return true;
			} else {
//				System.out.println("User does not exsist.");
				return false;
			}
		} catch(Exception e) {
//			System.out.println(e);
			return false;
		}

	}

	public boolean updateEmployee (String name,  String password, int salary, String usertype, String update_username) {

		try {
			PreparedStatement ps = con.prepareStatement("UPDATE users SET name = '"+name+"', password = '"+password+"', salary ="+ salary+ "', usertype = '"+usertype+"' WHERE username = '"+update_username+"';");

			int exec = ps.executeUpdate(); 
//			System.out.println(count);
			if (exec != 0) {
//				System.out.println("User updated successfull");
				return true;
			} else {
//				System.out.println("User update not successfull");
				return false;
			}
		} catch(Exception e) {
			System.out.println(e);
			return false;
		}
	}



	public boolean updateItem(String name, int price, int update_id) {

		try {
			PreparedStatement ps = con.prepareStatement("UPDATE menu SET 'name='"+name+"', price = '" +price+ "' WHERE id = '"+update_id+"';");

			int exec = ps.executeUpdate();
//			exec=count;
//			System.out.println(count);
			if (exec != 0) {
//				System.out.println("ITEM Updated Successfull");
				return true;
			} else {
//				System.out.println("ITEM Updated not successfull");
				return false;
			}
		} catch (Exception e) {
//			System.out.println(e);
			return false;
		}
	}
	
	//create a close connection
	public void closeConnection() {
		try {
			con.close();
		} catch (SQLException e) { e.printStackTrace(); }
	}
	
	
	
	
	public int getID(String name, String table) {
		
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT id FROM "+ table +" WHERE name like '" + name +"';");			
			return rs.getInt("id");

		} catch(Exception e) {
			return -1;
		}
	}
	
	public int getPrice(String name, String table) {
		
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT price FROM "+ table +" WHERE name like '" + name +"';");			
			return rs.getInt("price");

		} catch(Exception e) {
			return -1;
		}

	}
	
	
	public boolean checkOrderNo (int num) {

		int count = 0;
	
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
		
			statement = con.createStatement();
			resultSet = statement.executeQuery("SELECT order_no FROM order_ WHERE order_no = " + num + ";");
			
			while (resultSet.next()) {
			if (count != 0) {
				return true;
			}
			}
			if (count != 0) {
				return true;
			} else {
				return false;
			}
		} catch(Exception e) { System.out.println(e); return false; }

	}
	
	
	public boolean addToOrder(int order_no, String name, int qty, String placedBy) {
		try {
		PreparedStatement ps = con.prepareStatement("INSERT INTO order_ (order_no, name, qty, placedBy) VALUES ( '"+order_no+"','"+name+"','"+qty+"',"+ placedBy+"');");

		int exec = ps.executeUpdate();
			if ( exec == 0) {
				return false;
			} else {
				return true;
			}
		} catch(Exception e) {
			return false;
		}
	}
	
	public boolean updateOrderQty( int order_no, int id, int qty ) {

		try {
			PreparedStatement ps = con.prepareStatement("UPDATE order_ SET 'qty='"+qty+"' WHERE order_no = '"+order_no+"' id = '"+id+"';");

			int exec = ps.executeUpdate();
			if (exec != 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}
	
	public boolean deleteOrder(int order_no, int id) {

		try {
			PreparedStatement ps = con.prepareStatement("DELETE FROM '"+ order_no +"' WHERE  id = " + id + ";");
			int exec = ps.executeUpdate();
			if (exec == 0) {
				return false;
			} else {
				return true;
			}
		} catch(Exception e) { return false; }
	}

	public ResultSet getCurrentOrder(int order_no) {
		Statement statement = null;
		ResultSet rs = null;

		try
		{
			statement = con.createStatement();
			rs = statement.executeQuery("SELECT id, name, qty FROM order_ WHERE order_no = "+order_no+";");
		}
		catch(SQLException e) {	e.printStackTrace(); }

		return rs;
	}
	
}
