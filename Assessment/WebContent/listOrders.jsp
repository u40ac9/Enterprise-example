<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" import="java.util.Iterator, java.util.Vector, com.buttons.models.Orders" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id= "ord" class = "com.buttons.models.Orders" scope="session"/>

<%
	Vector <Orders>vec = new Vector<Orders>();
	vec = (Vector)request.getAttribute("orders");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Orders</title>
<link rel="stylesheet" href="Buttonstyles.css" type="text/css"/>
</head>


<body>

<div align ="center"><%@include file="customer.txt" %></div>
<h1>Welcome loyal staff member</h1>

<h4>List of orders placed by patriotic comrades</h4>
<table width="500" border="0" cellspacing="5" cellpadding="5" align="center"/>

<table border="1">
<tr><td><b>ID</b></td><td><b>MotifID</b></td><td><b>Quantity</b></td><td><b>Price</b></td>
<td><b>CustomerID</b></td></tr>
<%
	Iterator iter = vec.iterator();
	while (iter.hasNext()){
	ord = (Orders) iter.next();
%>
<tr><td><%=ord.getID() %></td><td><%=ord.getMotifID() %></td>
<td><%=ord.getQuantity() %></td><td><%=ord.getPrice() %></td>
<td><%=ord.getCustomerID() %></td>
<% 
	}
%>
</table>
</body>


</html>