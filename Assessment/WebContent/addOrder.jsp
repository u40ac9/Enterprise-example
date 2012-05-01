<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Add an Order</title>
<link rel="stylesheet" href="Buttonstyles.css" type="text/css">
</head>

<table width="500" border="0" cellspacing="5" cellpadding="5" align="center">

<div align="center"><%@include file="staff.txt" %></div>
<h1 align="center">Here you can add new motifs or orders</h1>
<p>Put some entries here later</p>

<form name="addOrder" method="post" action="Button?action=insertOrder">
<table border="0" width="90%">
<tr><td colspan="2"><h2>Add a new Order</h2></td></tr>
<tr><td>Quantity</td><td><input type="text" name="quantity"></td></tr>
<tr><td>Price</td><td><input type="text" name="price"></td></tr>
<tr><td>MotifID</td><td><input type="text" name="motifID"></td></tr>
<tr><td>CustomerID</td><td><input type="text" name="customerID"></td></tr>
 <tr><td colspan="2"><input type="submit" name="Submit" value="Submit"></td></tr>
</table>    
</form>

</body>
</html>