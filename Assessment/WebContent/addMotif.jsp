<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Add a motif</title>
<link rel="stylesheet" href="Buttonstyles.css" type="text/css"/>
</head>

<body>
<div align = "center"><%@include file="staff.txt" %></div>
<h1 align="center"> Welcome loyal staff member </h1>

<form name="addMotif" method="post" action="Button?action=addMotif">
<table border="0" width="90%">
<tr><td colspan="2"><h4>Add a Motif</h4></td></tr>
<tr><td>Name</td><td><input type="text" name="name"></td></tr>
<tr><td>Quantity</td><td><input type="text" name="quantity"></td></tr>
<tr><td>Price</td><td><input type="text" name="price"></td></tr>
 <tr><td colspan="2"><input type="submit" name="Submit" value="Submit"></td></tr>
</table>    
</form>

</body>
</html>