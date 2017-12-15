<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Animation</title>
<link rel="shortcut icon" type="image/gif" href="images/icon.gif" />
<style>
img {
	position: absolute;
}

#results {
	position: relative;
	width: 80%;
	height: 80%;
	margin: auto;
	margin-top: 100px;
	margin-bottom: 50px;
	border-radius: 50px;
}
</style>
</head>
<body>
	<%@ include file="header.jsp"%>
	<div id="results"></div>
	<script type="text/javascript" src="resources/For_animation.js"></script>
	<%@ include file="footer.jsp"%>
</body>
</html>