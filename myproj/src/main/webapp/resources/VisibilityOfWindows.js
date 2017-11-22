$('.form_window').on('click', "img", function() {
	document.getElementById("REGISTER").style.display = "none";
	document.getElementById("ENTER").style.display = "none";
});
$('body').on('click', "#LogInButton", function() {
	document.getElementById("ENTER").style.display = "block";
});
$('body').on('click', "#SignUpButton", function() {
	document.getElementById("REGISTER").style.display = "block";
});