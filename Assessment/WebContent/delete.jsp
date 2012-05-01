<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />

<title>Delete Buttons</title>

<div align="center"><%@include file="staff.txt" %></div>
<link rel="stylesheet" href="styles.css" type="text/css">
<BODY BGCOLOR="WHITE" text="#333333" link="#0000FF" vlink="#FF0000" alink="#660066">
<table width="500" border="0" cellspacing="5" cellpadding="5" align="center">

</head>


<body>
<form name="deleteMotif" method="post" action="Button?action=deleteMotif">
<table border="0" width="90%">
<tr><td colspan="2"><h2>Delete a Motif</h2></td></tr>
<tr><td>Name</td><td><input type="text" name="name"></td></tr>
 <tr><td colspan="2"><input type="submit" name="Submit" value="Submit"></td></tr>
</table>    
</form>
</body>
</html>