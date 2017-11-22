<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<!DOCTYPE html>
<html lang="en">
<head>
<link href="<c:url value="/resources/styles_for_login.css" />"
	rel="stylesheet" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<%@ page import="helpful.CookieUtils"%>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript" src="resources/CheckingRegInfo.js"></script>
<title>log in</title>
</head>
<body>
<!-- optional annotation checks if request param exists -->
	<div id="ENTER">
		<div class="form_window">
			<img src="images/close.png">
			<form action="hello" method="post">
				<p>Log in, please</p>
				<p>
					Name: <input type="text" name="name" value="${userName}">
				</p>
				<p>
					Password: <input type="password" name="password" value="${userPassword}">
				</p>
				<!-- i need it only for avoiding exception, because hello page wants remember parameter from request-->
				<input type="hidden" name="remember" value="false">
				<p>
					<input type="submit" value="Log In" />
				</p>
			</form>
		</div>
	</div>

	<div id="REGISTER">
		<div class="form_window">
			<img src="images/close.png">
			<form action="register" onsubmit='CheckRegInfo();' method="post">
				<p>Sign up:</p>
				<p>Email address:</p>
				<input type="text" name="email" id="Email" onkeyup="CheckRegInfo()">
				<p>Name:</p>
				<input type="text" name="name" id="UserName"
					onkeyup="CheckRegInfo()">
				<p>Password:</p>
				<input type="password" id="pass1" name="password1"
					onkeyup="CheckRegInfo()">
				<p>Confirm your password:</p>
				<input type="password" id="pass2" name="password2"
					onkeyup="CheckRegInfo()">
				<p id="RegErrors">Type all the info and log in:)</p>
				<!-- The thing below i need to put "remember" in the request anyway,
					even if the user missed the tick -->
				<label><input type="radio" name="remember" value="true"
					checked> Remember me!</label> <label> <input type="radio"
					name="remember" value="false"> Don`t do it
				</label>
				<p>
					<input type="submit" id="RegisterButton" value="Register" disabled />
				</p>
			</form>
		</div>
	</div>
	<div class="log_button" id="LogInButton">Log In</div>
	<div class="log_button" id="SignUpButton">I am a new user here!</div>
	<script type="text/javascript" src="resources/VisibilityOfWindows.js"></script>
</body>
</html>