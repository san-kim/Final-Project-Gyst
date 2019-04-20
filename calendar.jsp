<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
String username = request.getParameter("username");
if(username == null)
	username = "";
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Calendar</title>
	<style>
		html body
		{
			width: 100%;
			height: 100%;
			margin: 0px;
			padding: 0px;
			font-family: Avenir Next;
		}
		
		#tododiv
		{
			position:absolute;
			width: 14vw;
			height: 96vh;
			margin-left: 2vw;
			margin-top: 2vh;	
			border-radius: 10px;
			background-color: #B9DEE4;
		}
		
		#todop
		{
			text-align: center;
			font-weight:300;
			font-size:27px;
			height:5vh;
			margin-top:calc(3.5vh - 18.125px);
			margin-left: calc(50% - 36.492px);
			position:absolute;
		}
		
		#todolistdiv
		{
			position: absolute;
			border-radius:10px;
			background-color:white;
			width: calc(100% - 34px);
			height: 60%;
			margin-top: 7vh;
			margin-left:17px;
			margin-right:17px;
		}
		
		#calendardiv
		{
			border: 1px solid grey;
			position:absolute;
			width: 80vw;
			height: 89vh;
			margin-left: 18vw;
			margin-right: 2vw; 
			margin-top: 9vh; 
			
			border-radius: 10px;
		}
		
		#nexteventdiv
		{
			position:absolute;
			width: 30vw;
			height: 5vh;
			margin-top:2vh;
			margin-left:18vw;
			border-radius: 10px;
			background-color: #ededed;
		}
		
		#monthdiv
		{
			position: absolute;
			width: 80vw;
			height: 9vh;
			margin-left: 18vw;
		}
		
		#monthp
		{
			text-align: center;
			font-family:'Avenir Next';
			font-size: 50px;
			font-weight:300;
			margin-top: calc(4.5vh - 34px);
		}
		
		#addtodobutton
		{
			position: absolute;
			width: 45px;
			height:45px;
			margin-left: calc(50% - 22.5px);
			margin-top: calc(57.6vh - 60px);
			border-radius:45px;
			text-align: center;
			font-family:'Avenir Next';
			font-size: 20px;
			font-weight:100;
			font-size: 25px;
			box-shadow: 2px 3px 5px 0px #ccc;
			outline:none;
		}
		
		#addtodobutton:active
		{
			position: absolute;
			width: 45px;
			height:45px;
			margin-left: calc(50% - 22.5px);
			margin-top: calc(57.6vh - 60px);
			border-radius:45px;
			text-align: center;
			font-family:'Avenir Next';
			font-size: 20px;
			font-weight:100;
			font-size: 25px;
			box-shadow: inset 0 0 5px #aaaaaa;
			outline:none;
		}
		
		#browsingtimebutton
		{
			position: absolute;
			width: calc(100% - 34px);
			height:5vh;
			margin-left: 17px;
			margin-top: calc(86vh - 34px);
			border-radius:8px;
			text-align: center;
			font-family:'Avenir Next';
			font-size: 20px;
			font-weight:100;
			box-shadow: 2px 3px 5px 0px #ccc;
			outline:none;
			background-color:#F5FCFD;
		}
		
		#browsingtimebutton:active
		{
			position: absolute;
			width: calc(100% - 34px);
			height:5vh;
			margin-left: 17px;
			margin-top: calc(86vh - 34px);
			border-radius:8px;
			text-align: center;
			font-family:'Avenir Next';
			font-size: 20px;
			font-weight:100;
			box-shadow: inset 0 0 5px #aaaaaa;
			outline:none;
			background-color:#F5FCFD;
		}
		
		#logoutbutton
		{
			position: absolute;
			width: calc(100% - 34px);
			height:5vh;
			margin-left: 17px;
			margin-top: calc(91vh - 17px);
			border-radius:8px;
			text-align: center;
			font-family:'Avenir Next';
			font-size: 20px;
			font-weight:100;
			box-shadow: 2px 3px 5px 0px #ccc;
			outline:none;
			background-color:#DEF6FA;
		}
		
		#logoutbutton:active
		{
			position: absolute;
			width: calc(100% - 34px);
			height:5vh;
			margin-left: 17px;
			margin-top: calc(91vh - 17px);
			border-radius:8px;
			text-align: center;
			font-family:'Avenir Next';
			font-size: 20px;
			font-weight:100;
			box-shadow: inset 0 0 5px #aaaaaa;
			outline:none;
			background-color:#DEF6FA;
		}
		
		#addeventbutton
		{
			position: absolute;
			width: 200px;
			height:5vh;
			margin-left: calc(98vw - 200px);
			margin-top: 2vh;
			border-radius:8px;
			text-align: center;
			font-family:'Avenir Next';
			font-size: 20px;
			font-weight:100;
			box-shadow: 2px 3px 5px 0px #ccc;
			outline:none;
		}
		
		#addeventbutton:active
		{
			position: absolute;
			width: 200px;
			height:5vh;
			margin-left: calc(98vw - 200px);
			margin-top: 2vh;
			border-radius:8px;
			text-align: center;
			font-family:'Avenir Next';
			font-size: 20px;
			font-weight:100;
			box-shadow: inset 0 0 5px #aaaaaa;
			outline:none;
		}
		
	</style>
</head>
<body>
	<div id="tododiv">
		<p id="todop">To Do</p>
		<div id="todolistdiv">
			<!-- POPULATE TABLE HERE -->
			<button id="addtodobutton">+</button>
		</div>
		<button id="browsingtimebutton" onclick="">Browsing Time</button>
		<button id="logoutbutton" onclick="">log out</button>
	</div>
	<div id="calendardiv">calendardiv</div>
	<div id="nexteventdiv">nexteventdiv</div>
	<div id="monthdiv">
		<p id="monthp">April</p>
	</div>
	<button id="addeventbutton" onclick="">Add Calendar Event</button>
</body>
</html>