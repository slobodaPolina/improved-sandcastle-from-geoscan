<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Настройки профиля</title>
<link href="<c:url value="/resources/styles.css" />" rel="stylesheet" />
<link rel="shortcut icon" type="image/gif" href="images/icon.gif" />
</head>
<body>
	<div id="page">
		<%@ include file="header.jsp"%>
		<div class="content-text">
			<form action="passchange" method="post">
				<p>Изменить пароль:</p>
				<p>
					Введите старый пароль: <input type="text" name="oldPassword"
						autofocus />
				</p>
				<p>
					Введите новый пароль: <input type="text" name="newPassword" />
				</p>
				<p>
					<input type="submit" value="Изменить" class="submit-button" />
				</p>
			</form>
			<br />
			<p>Загрузить фотографию:</p>
			<form action="addphoto" method="post">
				<p>
					<input type="file" name="photo" />
				</p>
				<p>
					<input type="submit" value="Загрузить" class="submit-button" />
				</p>
			</form>
		</div>
	</div>
	<%@ include file="footer.jsp"%>

</body>
</html>