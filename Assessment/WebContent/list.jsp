<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" import="java.util.Iterator, java.util.Vector, com.buttons.models.Motifs" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%> 
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id= "mot" class = "com.buttons.models.Motifs" scope="session"/>

<%
	Vector <Motifs>vec = new Vector<Motifs>();
	vec = (Vector)request.getAttribute("items");
%>
        
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Motifs</title>
<link rel="stylesheet" href="Buttonstyles.css" type="text/css"/>
</head>

<body>

<div align = "center"><%@include file="staff.txt" %></div>

<h4>List of Motifs</h4>
<table width="500" border="0" cellspacing="5" cellpadding="5" align="center"/>

<table border="1">
<tr><td><b>ID</b></td><td><b>Name</b></td><td><b>Quantity</b></td><td><b>Price</b></td></tr>
<%
	Iterator iter = vec.iterator();
	while (iter.hasNext()){
	mot = (Motifs) iter.next();
%>

<tr><td><%=mot.getID() %></td><td><%=mot.getName() %></td>
<td><%=mot.getQuantity() %></td><td><%=mot.getPrice() %></td></tr>

<% 
	}
%>

</table>
<% if((Boolean)session.getAttribute("customer")){
	%><form name="addOrder" method="post" action="Order?action=insertOrder">
<table border="0" width="90%">
<tr><td colspan="2"><h2>Place an order</h2></td></tr>
<tr><td>Quantity</td><td><input type="text" name="quantity"/></td><td></td></tr>
<tr><td>Price</td><td><input type="text" name="price"/></td></tr>
<tr><td>MotifID</td><td><input type="text" name="motifID"/></td></tr>
<tr><td colspan="2"><input type="submit" name="Submit" value="Submit"/></td></tr>
</table>    
</form>
<% 
}
%>

</body>
</html>