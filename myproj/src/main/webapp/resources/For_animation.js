(function() {
	var amount_of_balls = 15;
	var speedX = 5;// maximum of speeds (from 0 to speedX)
	var speedY = 1;
	var size = 100;
	var balls = [];
	var width = document.getElementById("results").offsetWidth;
	var height = document.getElementById("results").offsetHeight;
	var acceleration = 1;
	var angle = 0;
	for (var i = 0; i < amount_of_balls; i++) {
		var CurXC = Math.round(Math.random() * (width - size));
		var CurYC = Math.round((Math.random() / 2) * (height - size));
		var CurXS = Math.round(Math.random() * speedX);
		var CurYS = Math.round(Math.random() * speedY);
		balls[i] = {
			img : document.createElement("img"),
			xcoord : CurXC,
			ycoord : CurYC,
			xspeed : CurXS,
			yspeed : CurYS
		};
		balls[i].img.setAttribute("src", "images/food.png");
		balls[i].img.setAttribute("width", size);
		balls[i].img.setAttribute("height", size);
		// balls[i].img.setAttribute("id", "number" + i); For jQuery
		document.getElementById("results").appendChild(balls[i].img);
	}
	setInterval(move, 20);
	function move() {
		window.onresize = recount;
		function recount() {
			width = document.getElementById("results").offsetWidth;
			height = document.getElementById("results").offsetHeight;
		}
		for (var i = 0; i < amount_of_balls; i++) {
			balls[i].xcoord += balls[i].xspeed;
			balls[i].ycoord += balls[i].yspeed;
			if (balls[i].xcoord >= (width - size)) {
				balls[i].xcoord = width - size;
				balls[i].xspeed = -Math.abs(balls[i].xspeed);
			}
			if (balls[i].xcoord <= 0) {
				balls[i].xspeed = Math.abs(balls[i].xspeed);
				balls[i].xcoord = 0;
			}
			if (balls[i].ycoord <= 0) {
				// if it has touched the top of the box something has gone wrong
				// and his yspeed is increasing:(
				balls[i].yspeed = Math.abs(balls[i].yspeed) * 0.9;
				balls[i].ycoord = 0;
			}
			if (balls[i].ycoord >= (height - size - balls[i].yspeed)) {
				balls[i].ycoord = height - size - balls[i].yspeed;
				balls[i].yspeed = -Math.abs(balls[i].yspeed);
			}
			balls[i].yspeed += acceleration;
			balls[i].img.style.transform = 'translate(' + balls[i].xcoord
					+ 'px, ' + balls[i].ycoord + 'px)' + 'rotate(' + angle
					+ 'deg)';
			// jQuery("#number" + i).rotate(angle); "no function rotate"
		}
		angle += 1;
	}
}());