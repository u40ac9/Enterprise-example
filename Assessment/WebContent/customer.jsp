<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" import = "java.io.PrintWriter" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />

<link rel="stylesheet" href="Buttonstyles.css" type="text/css">
<table width="500" border="0" cellspacing="5" cellpadding="5" align="center">

<title>Customer Page</title>

<div align="center"><%@include file="customer.txt" %></div>
</head>
<body>
<h1 align="center">Welcome friends of buttons</h1>



<h4> Previous customer?</h4>

<form name="loginCustomer" method="post" action="Customer?action=loginCustomer">
<table border="0" width="90%">
<tr><td>ID</td><td><input type="text" name="customerID"/></td></tr>
<tr><td>Password</td><td><input type="password" name="password"/></td></tr>
<tr><td colspan="2"><input type="submit" name="Submit" value="Submit"/></td></tr>
</table>    
</form>

<h4> Or create an account? </h4>

<form name="newCustomer" method="post" action="Button?action=newCustomer">
<table border="0" width="90%">
<tr><td>Name</td><td><input type="text" name="name"></td></tr>
<tr><td>Address</td><td><input type="text" name="address"></td></tr>
<tr><td>Password</td><td><input type="text" name="newPassword"></td></tr>
<tr><td colspan="2"><input type="submit" name="Submit" value="Submit"></td></tr>
</table>    
</form>


</body>
</html>