<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<!DOCTYPE html>
<html lang="en">
<head>
<link href="<c:url value="/resources/styles_for_login.css" />"
	rel="stylesheet" />

<link href="<c:url value="/resources/styles.css" />" rel="stylesheet" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript" src="resources/CheckingRegInfo.js"></script>

<title>confirm</title>
</head>
<body>
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
	<div class="log_button" id="LogInButton">Log In</div>
	"${text}"
	<script type="text/javascript" src="resources/VisibilityOfWindows.js">
		
	</script>

</body>
</html>