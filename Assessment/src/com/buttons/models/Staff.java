package com.buttons.models;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.buttons.db.DbBean;

/**
 * Represents a member of staff of the online store
 * Based on code available at: 
 * http://www.abdn.ac.uk/~csc228/teaching/CS3514/practicals/practical4.shtml
 * 
 * @author Marius Fjeld Wold
 * 
 */
public class Staff {
	int id = 0;
	String name = "";
	String password = "";
	
	/**
	 * Constructor for objects of type motifs
	 */
	public Staff(){

	}
	
	//Getters and setters
	/**
	 * Return the ID
	 */
	public int getID(){
		return id;
	}

	/**
	 * Set the ID 
	 */
	public void setID(int id){
		this.id = id;
	}

	/**
	 * Get the staff name
	 */
	public String getName(){
		return name;
	}

	/**
	 * Set the name
	 */
	public void setName(String name){
		this.name = name;
	}
	
	/**
	 * Get the password
	 */
	public String getPassword(){
		return password;
	}
	
	/**
	 * Set the password
	 */
	public void setPassword(String pas){
		password = pas;
	}
	
	//Login for staff
	public boolean loginStaff(String name, String password) throws ClassNotFoundException, 
		SQLException, InstantiationException, IllegalAccessException{
		ResultSet rs = null;
		DbBean db = new DbBean();
		StringBuffer qry = new StringBuffer(1024);
		
		qry.append("SELECT * FROM staff WHERE NAME=");
		qry.append("'"+name+"'");
		qry.append(" AND Password=");
		qry.append("'"+password+"'");
		
		db.connect();
		rs = db.execSQL(qry);
		if(rs.next()){
			rs.close();
			db.close();
			return true;
		}
		rs.close();
		db.close();
		return false;
	}
}
