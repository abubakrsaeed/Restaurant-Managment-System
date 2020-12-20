package database;

import java.sql.*;
import java.util.ArrayList;
import main.MD5;


public class DBConnection {

    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/project";
    private static final String USER = "root";
    private static final String PASS = "159357";

    private static Connection conn = null;
    private static Statement stmt = null;
    private static PreparedStatement pstmt = null;

    public void init() {
        try {

            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            System.out.println("connection initialized");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Problem!");
        }

    }//end try


    public boolean getManager(String username, String pass) {
        Boolean var = false;

        try {
            pstmt = conn.prepareStatement("SELECT * FROM manager WHERE mUserName=? AND passWord=?");
            pstmt.setString(1, username);
            pstmt.setString(2, pass);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {

                if (rs.next())
                    var = true;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return var;
    }


    public boolean getEmployee(String username, String pass) {
        Boolean var = false;

        try {
            pstmt = conn.prepareStatement("SELECT * FROM employee WHERE eUserName=? AND passWord=?");
            pstmt.setString(1, username);
            pstmt.setString(2, pass);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                var = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return var;
    }
	
	//rest of of functions should be implemented here

}
