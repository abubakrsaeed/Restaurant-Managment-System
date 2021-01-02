package main;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Menu {
	
	int price;
	String name;
	int id;
	
  
	public Menu(int id, String name,int price) {
		this.id=id;
		this.name=name;
		this.price=price;
	}

}
