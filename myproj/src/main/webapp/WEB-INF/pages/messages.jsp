<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Сообщения</title>
<link href="<c:url value="/resources/styles.css" />" rel="stylesheet" />
<link rel="shortcut icon" type="image/gif" href="images/icon.gif" />
</head>
<body>
	<div id="page">
		<%@ include file="header.jsp"%>
		<div class="content-text">
			<a href="addmessage"><button class="submit-button"
					style="float: right">Добавить сообщение</button></a>
			<div class="clearleft"></div>
			Ранее добавленные пользователями сообщения:
			<ul id="pages_list">
				<c:forEach var="i" items="${list}">
					<li><p>От пользователя ${i.sender}:</p>
						<p>${i.text}</p>
						<p style="text-align: right">${i.time}</p></li>
				</c:forEach>
			</ul>
		</div>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>