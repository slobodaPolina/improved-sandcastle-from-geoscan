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
<title>welcome</title>
</head>
<body>
	<form action="./" method="get">
		<input type="hidden" name="exit" value="true"> <input
			type="submit" class="log_button" style="text-decoration: none"
			value="Log out" />
	</form>
	<!-- Used Bootstrap for cute table and he decided to change the style of p. 
	"Welcome, ..." is right on the top of the screen now. 
	Dont wanna create another text style for it, but i am angry!-->
	<p id="welcome">
		Welcome,
		<c:out value="${name}" />
	</p>

	<!-- //here i had an awful mistake: if my checkbox isnt ticked, i have an
	exception here - he is not even in request -->

	<c:if test="${remember}">
		<%
			CookieUtils.demoUserCookie(request, response, out);
				System.out.println("I was asked to remember that user");
		%>
	</c:if>

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