<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<!DOCTYPE html>
<html lang="en">
<head>
<link href="<c:url value="/resources/styles_for_login.css" />"
	rel="stylesheet" />

<link href="<c:url value="/resources/styles.css" />" rel="stylesheet" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript" src="resources/CheckingRegInfo.js"></script>
<title>log in</title>
</head>
<body>
	<!-- optional annotation checks if request param exists -->
	<div id="ENTER">
		<div class="form_window">
			<img src="images/close.png">
			<form action="login" method="post">
				<p>Log in, please</p>
				<p>
					Name: <input type="text" name="name" autofocus />
				</p>
				<p>
					Password: <input type="password" name="password">
				</p>
				<label><input type="checkbox" name="remember" value="true"
					checked> Remember me!</label>
				<p>
					<input type="submit" value="Log In" class="submit-button" />
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
				<input type="text" name="email" id="Email" onkeyup="CheckRegInfo()"
					autofocus />
				<p>Name:</p>
				<input type="text" name="name" id="UserName"
					onkeyup="CheckRegInfo()">
				<p>Password:</p>
				<input type="password" id="pass1" name="password"
					onkeyup="CheckRegInfo()">
				<p>Confirm your password:</p>
				<input type="password" id="pass2" name="password2"
					onkeyup="CheckRegInfo()">
				<p id="RegErrors">Type all the info and log in:)</p>
				<label><input type="checkbox" name="remember" value="true"
					checked> Remember me!</label>
				<p>
					<input type="submit" id="RegisterButton" value="Register"
						class="submit-button" disabled />
				</p>
			</form>
		</div>
	</div>
	<div class="log_button" id="LogInButton">Log In</div>
	<div class="log_button" id="SignUpButton">I am a new user here!</div>
	<script type="text/javascript" src="resources/VisibilityOfWindows.js"></script>
</body>
</html>