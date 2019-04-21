<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
String emptyspace = " ";
String username = request.getParameter("username");
if(username == null)
	username = "";
%>

<html>
	<head>
		<meta charset="UTF-8">
		<title>Register</title>
		<link rel="stylesheet" type="text/css" href="register1.css" />
		<script>
			
		</script>
	</head>
	
	<body>
		<div id="topbar" class="read_success">
			<a href="" id="weathermeister_topbar">G y s t</a>
			<a href=""><img id="logo" src="gyst_icon.png"></a>
		</div>
		
		<div id="signdiv">	
			<div id="register_p_div">
				<p id="registerp">Register</p>
			</div>		
			<form action="" onsubmit="return check();" id="submitform">
				<p id="usernametext">Username</p>
				<input type="text" name="username" id="username" class="inputbar" value="<%= username %>">
				<p id="passwordtext">Password</p>
				<input type="password" name="password" id="password" class="inputbar">
				<p id="passwordtext">Confirm Password</p>
				<input type="password" name="confirmpassword" id="confirmpassword" class="inputbar">
				<p id="errormessage"><br></p>
				<input type="submit" name="submit" value="Register" id="registerbutton">
			</form>
		</div>
		
		<div id="logindiv">
			<p id="loginp">Have an account?</p>
			<button id="loginbutton" onclick="location.href='login.jsp'">login</button>
		</div>
		
	</body>
</html>