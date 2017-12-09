var start_time = 3600;
var speed = 1000;
var time = start_time;
var code = 1;
var min, sec, m, s;
var id_of_timer = setInterval(count, speed);
function count() {
	sec = time % 60;
	min = (time - sec) / 60;
	m = min + "";
	s = sec + "";
	if (min < 10) {
		m = "0" + m;
	}
	if (sec < 10) {
		s = "0" + s;
	}
	document.getElementById("timer").innerHTML = m + " : " + s;
	document.getElementById("timer").style.display = "inline";
	time--;
	if (sec === 0) {
		if (min === 0) {
			document.getElementById("timer").innerHTML = "Время вышло!";
			return;
		}
		min--;
	}
}
function restart() {
	time = start_time;
	count();
}
function pause() {
	if (code % 2 === 1) {
		document.getElementById("pause").innerHTML = "Продолжить";
		clearInterval(id_of_timer);
	} else {
		document.getElementById("pause").innerHTML = "Пауза";
		id_of_timer = setInterval(count, speed);
	}
	code++;
}