<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />

<title>Delete Buttons</title>
<link rel="stylesheet" href="Buttonstyles.css" type="text/css"/>
</head>


<body>
<div align="center"><%@include file="staff.txt" %></div>
<h1>Welcome loyal staff member</h1>
<br></br>
<table width="500" border="0" cellspacing="5" cellpadding="5" align="center"/>
<h3>This action must be sanctioned by a supervisor</h3>
<form name="deleteMotif" method="post" action="Button?action=deleteMotif">
<table border="0" width="90%">
<tr><td colspan="2"><h3>Delete a Motif</h3></td></tr>
<tr><td>ID</td><td><input type="text" name="id"></td></tr>
 <tr><td colspan="2"><input type="submit" name="Submit" value="Submit"></td></tr>
</table>    
</form>

</body>
</html>