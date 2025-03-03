package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
	
	public boolean createNewOrder(int orderNo, String name,int qty,String placedBy) {
		try {	
			PreparedStatement ps = con.prepareStatement("INSERT INTO order_ (orderNo_, name, qty,placedBy)  VALUES (?,?,?,?);");
			ps.setLong(1, orderNo);
			ps.setString(2, name);
			ps.setInt(3, qty);
			ps.setString(4, placedBy);

			int exec = ps.executeUpdate();
			
			if ( exec == 0) {
			System.out.println("Create new order unsuccessfull.");
				return false;
			} else {
				System.out.println("Create new order successfull.");
				return true;
			}
		} catch(Exception e) {
			System.out.println(e);
			return false;
		}
	}
	
	
//Return a set of menu items to be used in the getAllItems func
private void itemsSet(ArrayList<ArrayList<String>> dataToReturn, ResultSet rs) {
        try {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int price = rs.getInt("price");
                
                ArrayList<String> tempData = new ArrayList<>();
                tempData.add(Integer.toString(id));
                tempData.add(name);
                tempData.add(Integer.toString(price));
               
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	
	//The Func that return a arrayList of all menu items 
	
	public ArrayList<ArrayList<String>> getAllItems()
	{
		 ArrayList<ArrayList<String>> dataToReturn = new ArrayList<>();
		 try {
	            ResultSet rs = stmt.executeQuery("SELECT * FROM menu");
	           itemsSet(dataToReturn, rs);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return dataToReturn;
	}
	
	private void employeeSet(ArrayList<ArrayList<String>> dataToReturn, ResultSet rs) {
        try {
            while (rs.next()) {
               
                String name = rs.getString("name");
                String username = rs.getString("username");
                int salary = rs.getInt("salary");
               
                ArrayList<String> tempData = new ArrayList<>();
               
                tempData.add(name);
                tempData.add(username);
                tempData.add(Integer.toString(salary));
               
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	public ArrayList<ArrayList<String>> getAllEmployees()
	{
		 ArrayList<ArrayList<String>> dataToReturn = new ArrayList<>();
		 try {
	            ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE username=?");
	            pstmt.setString(1, "employee");
	           itemsSet(dataToReturn, rs);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return dataToReturn;
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
	
	//close connection
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
