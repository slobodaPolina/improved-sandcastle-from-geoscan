<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Scripts</title>
<link href="<c:url value="/resources/styles.css" />" rel="stylesheet" />
<link rel="shortcut icon" type="image/gif" href="images/icon.gif" />
</head>
<body>
	<%@ include file="header.jsp"%>
	<aside>
	<div class="seealso">
		<img src="images/truefalse.png" /> Проверь свою эрудицию!
	</div>
	</aside>
	<div class="content-text">
		<div class="buttons">
			<button onclick="restart()">Сбросить</button>
			<button id="pause" onclick="pause()">Пауза</button>
			<div id="timer"></div>
			<div class="clearleft"></div>
			<script type="text/javascript" src="resources/Scr-timer.js"></script>
		</div>
		<p>Правдивы ли утверждения?</p>
		<form>
			<p id="0">Первое место среди причин гибели от несчастных случаев
				в Японии в 1995 году заняли туфли на высоком каблуке.</p>
			<input type="radio" name="0" value="yes" />Да <input type="radio"
				name="0" value="no" />Нет
			<p id="1">Авторучку придумали древние египтяне.</p>
			<input type="radio" name="1" value="yes" />Да <input type="radio"
				name="1" value="no" />Нет
			<p id="2">Пауки питаются собственной паутиной.</p>
			<input type="radio" name="2" value="yes" />Да <input type="radio"
				name="2" value="no" />Нет
			<p id="3">Летучие мыши могут принимать радиосигналы.</p>
			<input type="radio" name="3" value="yes" />Да <input type="radio"
				name="3" value="no" />Нет
			<p id="4">Если положить камбалу на шахматную доску, то она станет
				клетчатой.</p>
			<input type="radio" name="4" value="yes" />Да <input type="radio"
				name="4" value="no" />Нет
			<p id="5">Радугу можно увидеть и в полночь.</p>
			<input type="radio" name="5" value="yes" />Да <input type="radio"
				name="5" value="no" />Нет
			<p id="6">В Японии ученики пишут на доске кисточкой с цветными
				чернилами.</p>
			<input type="radio" name="6" value="yes" />Да <input type="radio"
				name="6" value="no" />Нет
			<p id="7">Recaptcha используется для оцифровки книг.</p>
			<input type="radio" name="7" value="yes" />Да <input type="radio"
				name="7" value="no" />Нет
			<p id="8">Монетки, кидаемые туристами в фонтаны, генерируют
				огромные деньги государству.</p>
			<input type="radio" name="8" value="yes" />Да <input type="radio"
				name="8" value="no" />Нет
			<p id="9">Лайм опасен для кошек и собак.</p>
			<input type="radio" name="9" value="yes" />Да <input type="radio"
				name="9" value="no" />Нет
			<p>
				<input type="submit" value="Проверить!" class="submit-button"
					onclick="getresult(); return false" />
			</p>
			<p id="results">Нажмите, чтобы узнать свой результат.
				Утверждения, в правильности которых Вы ошиблись или сомневаетесь,
				станут красными, верно отмеченные - зелеными.</p>
		</form>
		<script type="text/javascript" src="resources/Scr-test.js"></script>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>