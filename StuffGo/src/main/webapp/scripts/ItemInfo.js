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
	var p = document.getElementById("customer_review").value;
	if (!p) {
		alert("Review is empty!");
		ok = false;
	} 
	
	return ok;
}

function addReview(address) {
	var ok = validate();
	if (!ok)
		return;
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
	request.open("GET", ( address + data), true);
	request.onreadystatechange = function() {
		handler(request, true);
	};
	request.send(null);
}


function handler(request, oneReview) {
	if ((request.readyState == 4) && (request.status == 200)) {
		var target = document.getElementById("reviews");
		console.log(request.responseText)
		var rs=JSON.parse(request.responseText);
		if (oneReview) {
			var review = document.getElementById("customer_review").value;
			var today = new Date();
			var dd = String(today.getDate()).padStart(2, '0');
			var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
			var yyyy = today.getFullYear();
			
			today = mm + '/' + dd + '/' + yyyy;
			addParagraph(target, rs.user, review, today);
			document.getElementById("customer_review").innerHTML = "";
		} else {
			
			
			if (rs.reviews && rs.reviews.length > 0) {
				for (var i=0; i<rs.reviews.length; i++) {
					console.log(rs.reviews.length);
					addParagraph(target, rs.reviews[i].user, rs.reviews[i].review, rs.reviews[i].date);
				}
			}

		}
	}
}


function addParagraph(parent, user, review, date) {
	let resu = "";

    resu+= "<div class=\"border border-success p-2 mb-2\" style=\"margin: 5px;\">";
    resu+= "	<h4>" + user + "</h4>";
	resu+= "    <p>" + review + "</p>";
	resu+= "    <p>" + date + "</p>";
	resu+="</div>";
	parent.innerHTML += resu;
}