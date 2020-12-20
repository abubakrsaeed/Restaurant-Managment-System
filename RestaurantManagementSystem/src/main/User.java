package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import database.DBConnection;


public class User {
	
	private static String username;
	private String password;
	private static DBConnection con;
	
	
	public User(String username_, String password_, DBConnection con_) {
		username = username_;
		password = password_;
		con = con_;
	}
	

	
}
