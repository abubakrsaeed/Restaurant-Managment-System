package main;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Menu {

	private int order_no;
	private Employee emp;
	
	public Menu(int orderNo, Employee obj) {
		order_no = orderNo;
		emp = obj;
	}
	
	
	public boolean addOrder( String name, int qty) {
		return emp.getDB().addToOrder(order_no, name, qty, emp.getName());
	}
	
	public boolean updateOrderQty( int id, int qty ) {
		return emp.getDB().updateOrderQty(order_no, id, qty);
	}
	
	public boolean deleteOrder(int id) {
		return emp.getDB().deleteOrder(order_no, id);
	}
	
	
	
	public String[][] viewCurrentOrder() throws SQLException {

		ResultSet rs = emp.getDB().getCurrentOrder(order_no); 

		ArrayList<String[]> rowList = new ArrayList<String[]>();
		int r = 0;

		try {
			while(rs.next()) {
				rowList.add(new String[] { rs.getString("id"), rs.getString("name"), String.valueOf(rs.getInt("qty"))});
				r++;
			}
		} catch (SQLException e) { e.printStackTrace(); }

		//
		int c = 3; //size of columns from table - fixed
		return emp.makeTable(rowList, r, c);
	
	}
}
