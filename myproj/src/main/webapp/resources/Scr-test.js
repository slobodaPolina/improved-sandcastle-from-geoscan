function getresult(){
	var res=0;
	var answers=[1, 1, 1, 0, 1, 1, 1, 1, 0, 1];
	function check(name) {
		var rad = document.getElementsByName(name);
		if ((rad[0].checked && answers[name] === 1) || (rad[1].checked && answers[name] === 0)){
			res++;
		}
		else {
			answers[name] = -1;
		}
	}
	for(var i = 0; i < 10; i++){
		check(i);
	}
	if(res === 1){
		document.getElementById("results").innerHTML = "Ваш результат - " + 1 + " правильный ответ!";
	}
	if(res > 1 && res < 5){
		document.getElementById("results").innerHTML = "Ваш результат - " + res + " правильных ответа!";
	}
	if(res > 4 || res === 0){
		document.getElementById("results").innerHTML = "Ваш результат - " + res + " правильных ответов!";
	}
	for(var i=0; i<10; i++){
		if(answers[i] === -1){
			document.getElementById(i).style.color="coral";
		}
		else{
			document.getElementById(i).style.color="yellowgreen";
		}
	}
}