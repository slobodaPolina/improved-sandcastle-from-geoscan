<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<!DOCTYPE html>
<html lang="en">
<head>
<link href="<c:url value="/resources/styles_for_login.css" />"
	rel="stylesheet" />

<link href="<c:url value="/resources/styles.css" />" rel="stylesheet" />
<link rel="shortcut icon" type="image/gif" href="images/icon.gif" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript" src="resources/CheckingRegInfo.js"></script>

<title>confirm</title>
</head>
<body>
	<div class="content-text">
		<p>${text}</p>
		<a href="hello"><button class="submit-button">Log In</button></a>
		<div class="clearleft"></div>
	</div>
</body>
</html>