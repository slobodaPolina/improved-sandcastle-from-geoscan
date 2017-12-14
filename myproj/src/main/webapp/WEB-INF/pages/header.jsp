<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<c:url value="/resources/styles.css" />" rel="stylesheet" />
</head>
<body>
	<div class="header">
		<ul class="menu">
			<li><a href="login"> Главная</a></li>
			<li><a href="scripts"> Тест</a></li>
			<li><a href="animation"> Летающие бублики</a></li>
			<li><a href="currencies"> Список валют</a></li>
			<li><form action="logout" method="post">
					<input type="hidden" name="exit" value="true"> <input
						type="submit" value="Log out" class="submit-button"
						style="border: 0" />
				</form></li>
		</ul>
	</div>
</body>
</html>