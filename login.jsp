<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
String username = request.getParameter("username");
if(username == null)
	username = "";
%>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Login</title>
		<link rel="stylesheet" type="text/css" href="login5.css" />
		<script>
			function checkUsernamePassword()
			{
				//TODO
			}
		
			function guestlogin()
			{
				//TODO
			}
			
		</script>
	</head>
	
	<body>
		<div id="topbar" class="read_success">
			<a href="" id="weathermeister_topbar">G y s t</a>
			<a href=""><img id="logo" src="gyst_icon.png"></a>
		</div>
		
		<div id="signdiv">			
			<div id="login_p_div">
				<p id="loginp">Login</p>
			</div>
			<form action="index.jsp" onsubmit="return checkUsernamePassword();" id="submitform">
				<p id="usernametext">Username</p>
				<input type="text" name="username" id="username" class="inputbar" value="<%= username %>">
				<p id="passwordtext">Password</p>
				<input type="password" name="password" id="password" class="inputbar">
 				<p id="errormessage"><br></p>
 				<input type="submit" name="submit" value="login" id="loginbutton">
			</form>
			<div id="guestdiv">
				<button id="guestbutton" onclick="guestlogin()">guest</p>
			</div>
		</div>
		
		<div id="registerdiv">
			<p id="registerp">New User?</p>
			<button id="registerbutton" onclick="location.href='register.jsp'">register</button>
		</div>
		
	</body>
</html>