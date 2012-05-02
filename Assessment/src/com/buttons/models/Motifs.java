package com.buttons.models;

import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.buttons.db.DbBean;

/**
 * Represents the different motifs that can be printed on buttons
 * Code adapted from code available at: 
 * http://www.abdn.ac.uk/~csc228/teaching/CS3514/practicals/practical4.shtml
 * 
 * @author Marius Fjeld Wold
 *
 */
public class Motifs {
	int id = 0;
	String name = "";
	double price = 0;
	int quantity;

	/**
	 * Constructor for objects of type motifs
	 */
	public Motifs(){

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
	 * Get the name of the motif
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
	 * Set the quantity
	 */
	public void setQuantity(int quantity){
		this.quantity = quantity;
	}
	
	/**
	 * Get the quantity
	 */
	public int getQuantity(){
		return quantity;
	}

	/**
	 * Get the price of the motif
	 */
	public double getPrice(){
		return price;
	}

	/**
	 * Set the price
	 */
	public void setPrice(double price){
		this.price = price;
	}

	// For use by staff

	/**
	 * Add a new motif to the database
	 * 
	 * @param name The name of the motif
	 * @param price The price of it
	 */
	public void insertMotif(String name, double price, int quantity) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		DbBean db = new DbBean();
		try{
			db.connect();
			int max = db.getMaxValue("motif", "ID");
			//Create new ID
			max = max+1;
			PreparedStatement inst = db.prepareStatement("INSERT INTO motif VALUES(?,?,?,?)");
			inst.setInt(1,max);
			inst.setString(2,name);
			inst.setDouble(3,price);
			inst.setInt(4,quantity);
			
			inst.executeUpdate();
			inst.close();
			
			//Close  connection
			db.close();
		} catch (SQLException e) {
			System.out.println(
					"SQLException from db: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * Delete the specified motif from the database
	 * @param id ID of motif that is to be removed
	 */
	public void deleteMotif(int id) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException{
		DbBean db = new DbBean();
		StringBuffer qry = new StringBuffer(1024);
		qry.append("DELETE FROM motif WHERE ID=");
		qry.append(id);
		db.connect();
		Statement stmt = db.createStatement();
		stmt.executeUpdate(qry.toString());
		stmt.close();
		//Close connection
		db.close();
		}
	
	/**
	 * List all motifs, to list all motifs 'all' must be
	 * passed as the string.
	 */
	public Vector <Motifs>listMotifs(String value) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException{
		Vector <Motifs>list = new Vector <Motifs>();
		ResultSet rs = null;
		DbBean db = new DbBean();
		StringBuffer qry = new StringBuffer(1024);
		if(value.equals("all")){
			qry.append("SELECT * from app.motif");
		}
		
		db.connect();
		rs = db.execSQL(qry);
		while(rs.next()){
			Motifs motif = new Motifs();
			motif.setID(rs.getInt("ID"));
			motif.setName(rs.getString("Name"));
			motif.setPrice(rs.getDouble("Price"));
			motif.setQuantity(rs.getInt("Quantity"));
			list.add(motif);
		}
		rs.close();
		db.close();
		return list;
	}
	
	/**
	 * Select a single motif
	 * 
	 * TODO Insert check to prevent multiples
	 */
	public Vector<Motifs> selectMotif(String name) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException{
		ResultSet rs = null;
		DbBean db = new DbBean();
		StringBuffer qry = new StringBuffer(1024);
		Vector <Motifs>list = new Vector <Motifs>();
		qry.append("SELECT * ");
		qry.append("FROM motif WHERE name = \'");
		qry.append(name);
		qry.append("\'");
		db.connect();
		Statement stmt = db.createStatement();
		rs = stmt.executeQuery(qry.toString());
		try{
			while(rs.next()){
				Motifs motif = new Motifs();
				motif.setID(rs.getInt("ID"));
				motif.setName(rs.getString("name"));
				motif.setPrice(rs.getDouble("price"));
				motif.setQuantity(rs.getInt("quantity"));
				list.add(motif);
			}
			rs.close();
			db.close();
		} catch (SQLException e) {
			System.out.println("didn't get to rs.next:");
			e.printStackTrace();
		}
		return list;
	}
}
