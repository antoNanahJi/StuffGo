/**
 * 
 */
 window.onload = () => {
	var request = new XMLHttpRequest();
	request.open("GET", "http://localhost:8080/StuffGo/ItemInfo?out=getReviews", true);
	request.onreadystatechange = function() {
		handler(request, false);
	};
	request.send(null);
	document.getElementById("customer_review").innerHTML = "";
}

 function validate() {
	var ok = true;
	var p = document.getElementById("credit").value;
	if (isNaN(p) || p <= 0) {
		alert("Credit invalid!");
		document.getElementById("principle-error").style.display = "inline";
		document.getElementById("period-error").style.color = "red";
		ok = false;
	} 
	
	return ok;
}

function addReview(address) {
	var today = new Date();
	var dd = String(today.getDate()).padStart(2, '0');
	var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
	var yyyy = today.getFullYear();

	today = mm + '/' + dd + '/' + yyyy;
	var request = new XMLHttpRequest();
	var data = '&';
	data += "REVIEW=" + document.getElementById("customer_review").value + "&";
	data += "REVIEWDATE=" + today;
	console.log(address + data);
	request.open("GET", ( address
	 + data), true);
	request.onreadystatechange = function() {
		handler(request, true);
	};
	request.send(null);
}


function handler(request, oneReview) {
	if ((request.readyState == 4) && (request.status == 200)) {
		var target = document.getElementById("reviews");
		if (oneReview) {
			var review = document.getElementById("customer_review").value;
			addParagraph(target, review);
			document.getElementById("customer_review").innerHTML = "";
		} else {
			var rs=JSON.parse(request.responseText);
			
			if (rs.reviews && rs.reviews.length > 0) {
				for (var i=0; i<rs.reviews.length; i++) {
					console.log(rs.reviews.length);
					addParagraph(target, rs.reviews[i].review);
				}
			}

		}
	}
}


function addParagraph(parent, review) {
	let resu = "";

    resu+= "<p>"
	+  review
	+ "</p>"
	parent.innerHTML += resu;
}