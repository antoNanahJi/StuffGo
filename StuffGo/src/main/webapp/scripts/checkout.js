var counter = 0;
function validate(address) {
	var ok = true;
	var ccCounter = parseInt(document.getElementById("ccCounter").innerHTML);
	console.log(ccCounter);
	if (document.getElementById("billingName").value == null || document.getElementById("billingName").value == "") {
		alert("Please Enter Billing Name");
		ok = false;
	}
	else if (document.getElementById("billingAddress").value == null || document.getElementById("billingAddress").value == "") {
		alert("Please Enter Billing Address");
		ok = false;

	}
	else if (document.getElementById("shippingName") == null || document.getElementById("shippingName").value == "") {
		alert("Please Enter Billing Name");
		ok = false;
	}
	else if (document.getElementById("shippingAddress") == null || document.getElementById("shippingAddress").value == "") {
		alert("Please Enter Billing Name");
		ok = false;
	}
	else if (document.getElementById("creditCard") == null || document.getElementById("creditCard").value == "" || document.getElementById("creditCard").value < 1111111111111111 || document.getElementById("creditCard").value > 9999999999999999n) {
		alert("Please Enter valid CreditCard Number");
		ok = false;
	} else {
		if (ccCounter == 3) {
			ok = false;
			alert("Credit Card Authorization Failed.");
			document.getElementById("ccCounter").innerHTML = 1;
		} else document.getElementById("ccCounter").innerHTML = ccCounter + 1;
		var request = new XMLHttpRequest();
		request.open('GET', address);
		request.send(null);
	}

	return ok;
}
function loadLogin() {
	document.getElementById("checkout-login").style.display = "block";
	document.getElementById("checkout").style.display = "none";
}
function loadCheckout() {
	document.getElementById("checkout-login").style.display = "none";
	document.getElementById("checkout").style.display = "block";
}


