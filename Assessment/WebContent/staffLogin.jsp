<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Staff Login</title>
<link rel="stylesheet" href="Buttonstyles.css" type="text/css"/>
</head>

<body>
<h2>Enter your login details</h2>

<form name="login" method="post" action="Staff?action=login">
<table border="0" width="25%">
<tr><td>Name</td><td><input type="text" name="name"/></td></tr>
<tr><td>Password</td><td><input type="password" name="password"/></td></tr>
<tr><td colspan="2"><input type="submit" name="Submit" value="Submit"/></td></tr>
</table>    
</form>

</body>
</html>