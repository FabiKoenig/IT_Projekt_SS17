package de.hdm.itProjektSS17.server.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

	private static Connection con = null;
	
	/**
	 * Die URL, mit deren Hilfe die Datenbank angesprochen wird.
	 */
	private static String url = "jdbc:mysql://localhost:3306/itprojekt";
	
	private static String user = "root";
	
	private static String password = "";
	
	
	public DBConnection(){
		
	}
	
	
	public static Connection connection(){
		
		if(con==null){
			try{
				con = DriverManager.getConnection(url, user, password);
			
				return con;
			}catch(Exception e){
			
			System.out.println(e);
			
			}
			
		}
		return con;
		
		
		
	}
	
}
