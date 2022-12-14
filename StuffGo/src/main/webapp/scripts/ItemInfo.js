/**
 * On load
 */
window.onload = () => {
	var request = new XMLHttpRequest();
	request.open('GET', 'https://stuffgoapp.azurewebsites.net/ItemInfo?out=getReviewsStars', true);
	request.onreadystatechange = function () {
		reviewHandler(request, false);
		ratingHandler(request, false);
	};
	request.send(null);
	document.getElementById('customer_review').innerHTML = '';
	const loginText = document.getElementById('loginout');
	const nameNav = document.getElementById('nohov');

	if (nameNav && nameNav.innerHTML && nameNav.innerHTML.length !== 0) {
		console.log('nameNav is not empty therefore changing login to logout');
		loginText.innerHTML = 'logout';
		loginText.href = loginText.href.replace('login.jsp', 'logout.jsp');
	}
};


// Validate user input
function validate() {
	var ok = true;
	var SQLCommandsList = [			
		    "CREATE",
			"DROP",
			"ALTER",
			"TRUNCATE",			
			"INSERT",
			"UPDATE",
			"DELETE",			
			"GRANT",
			"REVOKE",
			"COMMIT",			
			"ROLLBACK",
			"SAVE",
			"SELECT"]
	var p = document.getElementById('customer_review').value;
	const arr = p.split(" ");
	if (!p) {
		addToast('Please write a review!');
		ok = false;
	}
	
	if (SQLCommandsList.includes(arr[0].toUpperCase())) {
		addToast('Please write a valid review!');
		ok = false;
	}
	
	if (p.includes("script") || p.includes("style")) {
		addToast('Please write a valid review!');
		ok = false;
	}
	
	return ok;
}

// Add this item to cart
function addItemToCart(address) {
	var el = document.getElementById( "Quantity");
	if (parseInt(el.value) < parseInt(el.min)) {
		addToast('Invalid input!!');
		return;
	}

	if (parseInt(el.value) > parseInt(el.max)) {
		addToast('Out of stock!!');
		return;
	}
	
	var request = new XMLHttpRequest();
	var data = '&';
	data += 'Quantity=' + document.getElementById('Quantity').value;
	console.log(address + data);
	request.open('GET', address + data, true);

	request.onreadystatechange = function () {
		addToast('Item added to cart!!');
	};
	request.send(null);
}

// Add a new revirew to this item using Ajax call
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


// Called after the ajax request is success for adding revirew
function reviewHandler(request, oneReview) {
	if (request.readyState == 4 && request.status == 200) {
		var target = document.getElementById('reviews');
		console.log(request.responseText);

		if (request.responseText != null && request.responseText != '') {
			var rs = JSON.parse(request.responseText);
			if (rs.login == 'false') {
				location.href = 'https://stuffgoapp.azurewebsites.net/login.jsp';
				return;
			}

			if (oneReview) {
				if (rs.reviewAdded == 'false') {
					addToast('Sorry ' + rs.user + '! To add a review you need to purchase this item.');
					return;
				}
				var review = document.getElementById('customer_review').value;
				var today = new Date();
				var dd = String(today.getDate()).padStart(2, '0');
				var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
				var yyyy = today.getFullYear();

				today = mm + '-' + dd + '-' + yyyy;

				document.getElementById('customer_review').value = '';
				if (document.getElementById(rs.user + '_review')) {
					document.getElementById(rs.user + '_review').innerHTML = review;
					document.getElementById(rs.user + '_date').innerHTML = today;
				} else {
					addParagraph(target, rs.user, review, today);
				}
				addToast('Thank you ' + rs.user + ' for submitting a review!!');
			} else {
				if (rs.reviews && rs.reviews.length > 0) {
					for (var i = 0; i < rs.reviews.length; i++) {
						if (rs.reviews[i].review != '') {
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


// Add new review into the review list
function addParagraph(parent, user, review, date) {
	let resu = '';

	resu += '<div class="border border-success p-2 mb-2" style="margin: 5px;">';
	resu += '	<h4>' + user + '</h4>';
	resu += '    <p id="' + user + '_review">' + review + '</p>';
	resu += '    <p id="' + user + '_date">' + date + '</p>';
	resu += '</div>';
	parent.innerHTML += resu;
}


// Add a new rating to this item using Ajax call 
function submitRating(address) {
	var request = new XMLHttpRequest();
	var data = '&';
	data += 'RATING=' + document.getElementById('star_value').innerHTML[0];
	console.log(address + data);
	request.open('GET', address + data, true);

	request.onreadystatechange = function () {
		ratingHandler(request, true);
	};
	request.send(null);
}

// Update the stars
function addStar(parent) {
	let resu = '';

	resu += '<div style=" margin-left: 3px;  color: yellow;" class="bi bi-star-fill""></div>';
	parent.innerHTML += resu;
}

// Called after the ajax request is success for adding rating
function ratingHandler(request, oneReview) {
	if (request.readyState == 4 && request.status == 200) {
		if (request.responseText != null && request.responseText != '') {
			var rs = JSON.parse(request.responseText);
			if (oneReview) {
				if (rs.login == 'false') {
					location.href = 'https://stuffgoapp.azurewebsites.net/login.jsp';
					return;
				}
				
				if (rs.ratingAdded == 'false') {
					addToast('Sorry ' + rs.user + '! To add a rating you need to purchase this item.');
					return;
				}
				//toast code
				addToast('Thank you ' + rs.user + ' for sharing your experience!!');
			} else {
				var container = document.getElementById('star-container');

				for (var i = 0; i < rs.starCount; i++) {
					addStar(container);
				}
			}
		}
	}
}

// Updates the rating star color
function addRating(id) {
	for (var i = 1; i <= id; i++) {
		document.getElementById('star_' + i).classList.add('bi-star-fill');
		document.getElementById('star_' + i).classList.remove('bi-star');
		document.getElementById('star_' + i).style.color = 'yellow';
	}
	for (var i = id + 1; i <= 5; i++) {
		document.getElementById('star_' + i).classList.add('bi-star');
		document.getElementById('star_' + i).classList.remove('bi-star-fill');
		document.getElementById('star_' + i).style.color = 'black';
	}

	document.getElementById('star_value').innerHTML = id + ' of 5';
	document.getElementById('star_submit').style.visibility = 'visible';
}

// Adds a toast
function addToast(msg) {
	document.getElementById('toast-message').innerHTML = msg;
	const toastLiveExample = document.getElementById('liveToast');
	const toast = new bootstrap.Toast(toastLiveExample);
	toast.show();
}
