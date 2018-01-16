<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<!DOCTYPE html>
<html lang="en">
<head>
<link href="<c:url value="/resources/styles.css" />" rel="stylesheet" />

<link href="<c:url value="/resources/styles.css" />" rel="stylesheet" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Info</title>
</head>
<body>
	<div class="content-text">
		<p>Hello, thanks for your registration! I have sent you an email
			on ${address} address. Please visit it and confirm your registration.
			You will be able to recreate your password using the email in the
			future.</p>
		<p>You can tap the button below to login now.</p>
		<a href="hello"><button class="submit-button">Log In</button></a>
		<div class="clearleft"></div>
	</div>
</body>
</html>