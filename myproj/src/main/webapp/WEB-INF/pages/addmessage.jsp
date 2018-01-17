<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Новое сообщение</title>
<link href="<c:url value="/resources/styles.css" />" rel="stylesheet" />
<link rel="shortcut icon" type="image/gif" href="images/icon.gif" />
</head>
<body>
	<div id="page">
		<%@ include file="header.jsp"%>
		<div class="content-text">
			<form action="newmessage" method="get">
				<p>
					<textarea cols="40" rows="5" name="message"
						placeholder="Напишите сюда свое сообщение"></textarea>
				</p>
				<p>
					<input type="submit" value="Добавить!" class="submit-button" />
				</p>
			</form>
		</div>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>