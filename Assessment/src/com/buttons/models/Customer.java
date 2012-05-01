package com.buttons.models;

public class Customer {
	
	int customerID;
	String name;
	String address;
	
	public Customer(){

	}
	
	public void setCustomerID(int customerID){
		this.customerID = customerID;
	}
	
	public int getCustomerID(){
		return customerID;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public void setAddress(String address){
		this.address = address;
	}
	public String getAddress(){
		return address;
	}

}
