<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<%@ page import="helpful.CookieUtils"%>

<%@ page import="helpful.Currency"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" />
<link href="<c:url value="/resources/styles_for_login.css" />"
	rel="stylesheet" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>currencies</title>
</head>
<body>
	<form action="logout" method="get">
		<input type="hidden" name="exit" value="true"> <input
			type="submit" class="log_button" style="text-decoration: none"
			value="Log out" />
	</form>

	<!-- stringBuilder for string changing: when u use string it creates new one for each modification,
	 but strbuilder doesnt. StringBuffer for multithread (it is slow and safe) -->
	<table class='table table-striped table-sm table-bordered'>
		<thead>
			<tr>
				<th>Russian Name</th>
				<th>English Name</th>
				<th>Char Code</th>
				<th>Num Code</th>
			</tr>
		</thead>
		<c:forEach var="i" items="${list}">
			<tr>
				<td>${i.rusName}</td>
				<td>${i.engName}</td>
				<td>${i.charCode}</td>
				<td>${i.numCode}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>