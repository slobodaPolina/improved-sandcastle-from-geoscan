<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sandbox</title>
<link href="<c:url value="/resources/styles.css" />" rel="stylesheet" />
<link rel="shortcut icon" type="image/gif" href="images/icon.gif" />

<link href="<c:url value="/resources/Bootstrap.css" />" rel="stylesheet" />

<style>
.table img {
	width: 100px;
	height: 100px;
}
</style>
</head>
<body>

	<%@ include file="header.jsp"%>
	<div class="content-text">
		<h1>Хороший заголовок - это важно!</h1>
		<p>Не менее важен хороший, длинный и увлекательный текст внутри
			заголовка:) Этот вполне себе подходит.</p>
		<a
			href="https://news.yandex.ru/?msid=1499861877.49061.22907.5896&mlid=1499861371.glob_225"
			target="_blank"> На этой странице можно найти множество хороших
			текстов, если Вам недостаточно того, который я написала выше.</a>
		<h2>Ниже приведена таблица с некоторыми животными:</h2>
		<table class="table table-striped table-sm table-bordered">
			<thead>
				<tr>
					<th>Изображение</th>
					<th>Название</th>
					<th>Цвет</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><img src="images/croc.jpg" /></td>
					<td>Крокодил</td>
					<td>Зеленый</td>
				</tr>
				<tr>
					<td><img src="images/cat.jpg" /></td>
					<td>Котик</td>
					<td>Серый</td>
				</tr>
				<tr>
					<td><img src="images/raco.jpg" /></td>
					<td>Енот</td>
					<td>Цвет неясен (предположительно серо-коричневый, но я могу и
						ошибаться, поэтому лучше уточнить у специалистов, если Вам это
						вообще нужно, в чем я очень сомневаюсь, ведь эта страничка вообще
						никому не интересна)</td>
				</tr>
			</tbody>
		</table>
		<h2>Хотелось бы заметить, что котики бывают разных цветов:</h2>
		<ul>
			<li>Белого</li>
			<li>Черного</li>
			<li>Рыжего</li>
			<li>Цвета краски, в которую он свалился (то есть любого)</li>
		</ul>
		Больше о животном мире можно почитать <a
			href="https://ru.wikipedia.org/wiki/Животные" target="_blank">
			здесь</a>, только не стоит сильно доверять тому, что пишут в интернете:)
		<form>
			<h2 id="form)">Расскажите немного о себе.</h2>
			<p>
				Имя:<input type="text" />
			</p>
			<p>
				У вас есть котик? <input type="radio" name="ex" value="yes" />Да,
				разумеется <input type="radio" name="ex" value="no" />Пока нет
			</p>
			<p>
				Каких авторов из нижеперечисленных вы читаете? <input
					type="checkbox" name="read" value="Moam" />Моэм <input
					type="checkbox" name="read" value="Bulgakov" />Булгаков <input
					type="checkbox" name="read" value="Tsweig" />Цвейг <input
					type="checkbox" name="read" value="Wilde" />Уайльд
			</p>
			<p>
				<input type="submit" value="Отправить!" onclick="return false" />
			</p>
			Спасибо за ответы. Они никуда не отправятся и нигде не сохранятся.
			Тем не менее, благодарю за уделение времени этому маленькому
			опроснику. Возможно, он помог разобраться в себе.
		</form>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>