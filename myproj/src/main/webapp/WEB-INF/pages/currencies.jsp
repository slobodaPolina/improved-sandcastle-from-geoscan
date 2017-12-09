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
<link href="<c:url value="/resources/Bootstrap.css" />" rel="stylesheet" />
<link href="<c:url value="/resources/styles.css" />" rel="stylesheet" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>currencies</title>
</head>
<body>
	<div id="page">
		<%@ include file="header.jsp"%>
		<!-- stringBuilder for string changing: when u use string it creates new one for each modification,
	 but strbuilder doesnt. StringBuffer for multithread (it is slow and safe) -->
		<div class="content-text">
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
		</div>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>