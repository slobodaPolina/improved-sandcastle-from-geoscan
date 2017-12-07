<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="helpful.CookieUtils"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Главная страничка</title>
<link href="<c:url value="/resources/styles.css" />" rel="stylesheet" />
<link rel="shortcut icon" type="image/gif" href="images/icon.gif" />
</head>

<!-- //here i had an awful mistake: if my checkbox isnt ticked, i have an
	exception here - he is not even in request -->


<c:if test="${remember}">
	<%
		CookieUtils.demoUserCookie(request, response, out);
			System.out.println("I was asked to remember that user");
	%>
</c:if>
<body>
	<div id="page">
		<header>
		<div class="header">
			<ul class="menu">
				<li><a href="index.html"> Главная </a></li>
				<li><a href="Scripts.html" target="_blank"> Скрипты</a></li>
				<li><a href="Animation.html" target="_blank"> Летающие
						бублики</a></li>
				<li><a href="currencies.jsp" target="_blank"> Список валют</a></li>
				<li><form action="./" method="get">
						<input type="hidden" name="exit" value="true"> <input
							type="submit" value="Log out" />
					</form></li>
			</ul>
		</div>
		</header>
		<div class="content-text">
			<ul id="pages_list">
				<li><a href="Песочница.html" target="_blank"> Песочница </a>-
					самая первая страница, где были попробованы разные элементы и есть
					нерабочая опросная форма.</li>
				<li><a href="статья о животных.html" target="_blank">В мире
						животных</a> - страница с текстом википедии, на которой я училась
					плавающим элементам и подобному.</li>
				<li><a href="Кошка.html" target="_blank">О котиках</a> -
					разбиение на колонки (изначально это плавающие элементы, потом я
					заменила их на колонки bootstrap). Википедия. Ничего интересного.</li>
				<li><a href="Scripts.html" target="_blank">Скрипты</a> - первая
					реально интересная страница, содержащая в себе таймер с кнопочками
					управления и тест с удивительными фактами (основан на форме с
					вопросами radiobuttons).</li>
				<li><a href="Animation.html" target="_blank">Анимация.</a>
					Изначально я хотела поставить ее на другие страницы так, чтобы
					бублики запускались при отсутствии движения мышью, но потом поняла,
					что это будет отвлекать от очень интересного информативного
					контента. Может, я сделаю запуск бубликов только при отсутствии
					движения на анимационной странице чуть позже, будем посмотреть.</li>
				<li><a href="Flexpage.html" target="_blank">Flex.</a>
					Страничка, сверстанная с помощью flex. Наполнение- логические
					парадоксы)</li>
				<li><a href="Bootstrappage.html" target="_blank">Bootstrap</a>
					Страничка, на которой я поигралась в Bootstrap. Да, он немного
					применен и в прочих вышеприведенных страницах, но здесь его куда
					больше. Эта страничка адаптирована под разные экраны и содержит
					текст о миграции морских котиков)</li>
				<li><a href="currencies.jsp" target="_blank"> Справочник
						валют</a> Страница с валютами, запрашиваемыми у центробанка</li>
			</ul>
		</div>
	</div>
	<footer>
	<div class="footer-bg">
		<div class="copyright">Заглавная страничка этого странного сайта</div>
	</div>
	</footer>
</body>
</html>