package com.buttons.models;

import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.buttons.db.DbBean;

/**
 * Represents orders that have been placed by customers
 * Code adapted from code available at: 
 * http://www.abdn.ac.uk/~csc228/teaching/CS3514/practicals/practical4.shtml
 * 
 * @author Marius Fjeld Wold
 *
 */
public class Orders {
	int id = 0;
	double price = 0;
	int motifID;
	int quantity;
	int customerID;

	/**
	 * Constructor for objects of type Orders
	 */
	public Orders(){

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
	 * Get the price of the order
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
	
	/**
	 * Get the the quantity of the order
	 */
	public int getQuantity(){
		return quantity;
	}
	
	/**
	 * Set the quantity of the order
	 */
	public void setQuantity(int quantity){
		this.quantity = quantity;
	}
	
	/**
	 * Return the motifID
	 */
	public int getMotifID(){
		return motifID;
	}
	
	/**
	 * Set the motifID
	 */
	public void setMotifID(int id){
		motifID = id;
	}
	
	public void setCustomerID(int id){
		customerID = id;
		
	}
	
	public int getCustomerID(){
		return customerID;
	}
	
	// For use by staff

	/**
	 * Add a new order to the database
	 * 
	 * @param name The name of the order
	 * @param price The price of it
	 */
	public void insertorder(double price, int motifID, int quantity, 
			int customerID) 
	throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		DbBean db = new DbBean();
		try{
			db.connect();
			int max = db.getMaxValue("orders", "ID");
			//Create new ID
			max = max+1;
			PreparedStatement inst = db.prepareStatement("INSERT INTO orders VALUES(?,?,?,?,?)");
			inst.setInt(1,max);
			inst.setInt(2,motifID);
			inst.setInt(3,quantity);
			inst.setDouble(4,price);
			inst.setInt(4,customerID);
			
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
	 * Delete the specified order from the database
	 * @param id ID of order that is to be removed
	 */
	public void deleteorder(int id) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException{
		DbBean db = new DbBean();
		StringBuffer qry = new StringBuffer(1024);
		qry.append("DELETE * FROM orders WHERE ID=");
		qry.append(id);
		db.connect();
		Statement stmt = db.createStatement();
		stmt.executeUpdate(qry.toString());
		stmt.close();
		//Close connection
		db.close();
		}
	
	/**
	 * List all Orders or a specific order. To list all Orders 'all' must be
	 * passed as the string. To list a specific order its ID must be passed as
	 * the string
	 */
	public Vector <Orders>listOrders(String value) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException{
		Vector <Orders>list = new Vector <Orders>();
		ResultSet rs = null;
		DbBean db = new DbBean();
		StringBuffer qry = new StringBuffer(1024);
		if(value.equals("all")){
			qry.append("SELECT * from orders");
		}
		else{
			qry.append("SELECT * from orders where ");
			qry.append("ID = ");
			qry.append(Integer.parseInt(value));
		}
		db.connect();
		rs = db.execSQL(qry);
		while(rs.next()){
			Orders order = new Orders();
			order.setID(rs.getInt("ID"));
			order.setMotifID(rs.getInt("MotifID"));
			order.setQuantity(rs.getInt("Quantity"));
			order.setPrice(rs.getDouble("Price"));
			order.setCustomerID(rs.getInt("CustomerID"));
			list.add(order);
		}
		rs.close();
		db.close();
		return list;
	}
	
	/**
	 * Select a single order
	 * 
	 * TODO Insert check to prevent multiples
	 */
	public Vector<Orders> selectorder(String name) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException{
		ResultSet rs = null;
		DbBean db = new DbBean();
		StringBuffer qry = new StringBuffer(1024);
		Vector <Orders>list = new Vector <Orders>();
		qry.append("SELECT * ");
		qry.append("FROM orders WHERE orders.name Like \'");
		qry.append(name);
		qry.append("\'");
		db.connect();
		Statement stmt = db.createStatement();
		rs = stmt.executeQuery(qry.toString());
		try{
			while(rs.next()){
				Orders order = new Orders();
				order.setID(rs.getInt("ID"));
				order.setMotifID(rs.getInt("MotifID"));
				order.setQuantity(rs.getInt("Quantity"));
				order.setPrice(rs.getDouble("Price"));
				order.setCustomerID(rs.getInt("CustomerID"));
				list.add(order);
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
