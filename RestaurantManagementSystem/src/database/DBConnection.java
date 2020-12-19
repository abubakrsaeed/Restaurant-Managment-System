package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

public class DBConnection {
	
	private Connection conn = null;
	
	
	public DBConnection(String url) throws SQLException {
		
		String[] arg = url.split("[,]");
	    ArrayList<String> list = new ArrayList<String> (Arrays.asList(arg));
	    //list(0) = url, list(1) = username, list(2) = password
			conn = DriverManager.getConnection(list.get(0),list.get(1),list.get(2));
	}
	
	public void close() {
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
			}
		}
	}

	
	
	/**
	 * A method to send select statement to the underlying DBMS (e.g., "select * from Table1")
	 * @param query_statement A query to run on the underlying DBMS 
	 * @return Resultset the query result. 
	 */
	public ResultSet send_query(String query_statement) {
		// Feel free to make them Class attributes
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
		    stmt = conn.createStatement();
		    rs = stmt.executeQuery(query_statement);
		    printData(stmt,rs);
		}
		catch (SQLException ex){
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		finally {

		    if (rs != null) {
		        try {
		            rs.close();
		        } catch (SQLException sqlEx) { } // ignore

		        rs = null;
		    }

		    if (stmt != null) {
		        try {
		            stmt.close();
		        } catch (SQLException sqlEx) { } // ignore

		        stmt = null;
		    }
		}	
	    return rs;
		
	}
	

	public ArrayList<String> getData(String query) throws SQLException {
		 Statement stmt = conn.createStatement();
		 ResultSet rs = stmt.executeQuery(query);
		 
		 ResultSetMetaData rsmd = rs.getMetaData();
		 int columnsNumber = rsmd.getColumnCount();

		ArrayList<String> readData = new ArrayList<String>();

		while (rs.next()) {       
			for(int i = 1 ; i <= columnsNumber; i++) {
				readData.add(rs.getString(i));
			}
        }
		return readData;
	} 

	
	
	public void printData(Statement st, ResultSet rs) throws SQLException {
		ResultSetMetaData rsmd = rs.getMetaData();
		int columnsNumber = rsmd.getColumnCount();
		// Iterate through the data in the result set and display it.
		while (rs.next()) {
			//Print one row          
			for(int i = 1 ; i <= columnsNumber; i++){
				if(i < columnsNumber) {
					System.out.print(rs.getString(i) + ","); //Print one element of a row
					
				}
				else {
					System.out.print(rs.getString(i));
				}
			}
			System.out.println();//Move to the next line to print the next row.           
		}
	} 

	
	
	// TODO Write a method to insert new data into the DBMS
	public  void post(String sql) throws Exception {
		try {
//			PreparedStatement posted = conn.prepareStatement("USE ;");
//			posted.executeUpdate();
			
			PreparedStatement posted = conn.prepareStatement(sql);
			posted.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
//		finally {
//			System.out.println("Insert Completed");
//		}
	}

}
