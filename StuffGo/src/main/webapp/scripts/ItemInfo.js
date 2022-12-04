/**
 *
 */
window.onload = () => {
	var request = new XMLHttpRequest();
	request.open('GET', 'http://localhost:8080/StuffGo/ItemInfo?out=getReviewsStars', true);
	request.onreadystatechange = function () {
		reviewHandler(request, false);
		ratingHandler(request, false)
	};
	request.send(null);
	document.getElementById('customer_review').innerHTML = '';
};

function validate() {
	var ok = true;
	var p = document.getElementById('customer_review').value;
	if (!p) {
		addToast('Please write a review!');
		ok = false;
	}

	return ok;
}

function addReview(address) {
	var ok = validate();
	if (!ok) return;
	var today = new Date();
	var dd = String(today.getDate()).padStart(2, '0');
	var mm = String(today.getMonth() + 1).padStart(2, '0');
	var yyyy = today.getFullYear();

	today = mm + '/' + dd + '/' + yyyy;
	var request = new XMLHttpRequest();
	var data = '&';
	data += 'REVIEW=' + document.getElementById('customer_review').value + '&';
	data += 'REVIEWDATE=' + today;
	console.log(address + data);
	request.open('GET', address + data, true);
	request.onreadystatechange = function () {
		reviewHandler(request, true);
	};
	request.send(null);
}

function reviewHandler(request, oneReview) {
	if (request.readyState == 4 && request.status == 200) {
		var target = document.getElementById('reviews');
		console.log(request.responseText);
		
		if (request.responseText != null && request.responseText != "") {
			var rs = JSON.parse(request.responseText);
			if (rs.login  == "false") {
				location.href = 'http://localhost:8080/StuffGo/login.html';
				return
			}
			
			if (oneReview) {
				var review = document.getElementById('customer_review').value;
				var today = new Date();
				var dd = String(today.getDate()).padStart(2, '0');
				var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
				var yyyy = today.getFullYear();
	
				today = mm + '-' + dd + '-' + yyyy;
				
				document.getElementById('customer_review').value = "";
				if(document.getElementById( rs.user + "_review")) {
					document.getElementById( rs.user + "_review").innerHTML = review;
					document.getElementById( rs.user + "_date").innerHTML = today;
				} else {
					addParagraph(target, rs.user, review, today);
				}
				addToast("Thank you " + rs.user + " for submitting a review!!")
			} else {
				
				if (rs.reviews && rs.reviews.length > 0) {
					for (var i = 0; i < rs.reviews.length; i++) {
						if (rs.reviews[i].review != ""){
							addParagraph(
								target,
								rs.reviews[i].user,
								rs.reviews[i].review,
								rs.reviews[i].date
							);
						}
					}
				}
			}
		}
	}
}

function addParagraph(parent, user, review, date) {
	let resu = '';

	resu += '<div class="border border-success p-2 mb-2" style="margin: 5px;">';
	resu += '	<h4>' + user + '</h4>';
	resu += '    <p id="' + user + '_review">' + review + '</p>';
	resu += '    <p id="' + user + '_date">' + date + '</p>';
	resu += '</div>';
	parent.innerHTML += resu;
}

function submitRating(address) {

	var request = new XMLHttpRequest();
	var data = '&';
	data += 'RATING=' + document.getElementById( "star_value").innerHTML[0] ;
	console.log(address + data);
	request.open('GET', address + data, true);
	
	request.onreadystatechange = function () {
		ratingHandler(request, true);
	};
	request.send(null);
}

function addStar(parent) {
	let resu = '';

	resu += '<div style=" margin-left: 3px;  color: yellow;" class="bi bi-star-fill""></div>';
	parent.innerHTML += resu;
}

function ratingHandler(request, oneReview) {
	if (request.readyState == 4 && request.status == 200) {
		
		if (request.responseText != null && request.responseText != "") {
			var rs = JSON.parse(request.responseText);
			if (oneReview) {
				if (rs.login  == "false") {
					location.href = 'http://localhost:8080/StuffGo/login.html';
					return
				}
				
				//toast code
				addToast("Thank you " + rs.user + " for sharing your experience!!");
			} else {
				var container  = document.getElementById('star-container');
				
				for(var i=0; i<rs.starCount; i++) {
					addStar(container);
				}
			}
		}
	}
}

function addRating(id) {
	for(var i=1; i<=id; i++) {
		document.getElementById( "star_" + i).classList.add("bi-star-fill");
		document.getElementById( "star_" + i).classList.remove("bi-star");
		document.getElementById( "star_" + i).style.color = "yellow";
		
	}
	for(var i=id + 1; i<=5; i++) {
		document.getElementById( "star_" + i).classList.add("bi-star");
		document.getElementById( "star_" + i).classList.remove("bi-star-fill");
		document.getElementById( "star_" + i).style.color = "black";
		
	}
	
	document.getElementById( "star_value").innerHTML = id + " of 5";
	document.getElementById( "star_submit").style.visibility = "visible";
}

function addToast(msg) {
	document.getElementById("toast-message").innerHTML = msg;
	const toastLiveExample = document.getElementById('liveToast');
	const toast = new bootstrap.Toast(toastLiveExample);
	toast.show();
}
