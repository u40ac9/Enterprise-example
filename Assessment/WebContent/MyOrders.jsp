<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" import="java.util.Iterator, java.util.Vector, com.buttons.models.Orders" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%> 
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id= "orders" class = "com.buttons.models.Orders" scope="session"/>

<%
	Vector <Orders>vec = new Vector<Orders>();
	vec = (Vector)request.getAttribute("items");
%>
        
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Your Orders</title>
<link rel="stylesheet" href="Buttonstyles.css" type="text/css"/>
<%@include file="customer.txt" %>
</head>

<body>
<h4>List of Previous Orders</h4>
<table width="500" border="0" cellspacing="5" cellpadding="5" align="center"/>

<table border="0">
<tr><td><b>ID</b></td><td><b>Price</b></td><td><b>MotifID</b></td><td><b>Quantity</b></td><td><b>CustomerID</b></td></tr>
<%
	Iterator iter = vec.iterator();
	while (iter.hasNext()){
	orders = (Orders) iter.next();
%>
<tr><td><%=orders.getID() %></td><td><%=orders.getPrice() %></td>
<td><%=orders.getMotifID() %></td><td><%=orders.getQuantity() %></td><td><%=orders.getCustomerID() %></td></tr>

<% 
	}
%>


</table>

</body>
</html>