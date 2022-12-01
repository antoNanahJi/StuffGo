<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Checkout</title>
<script src="scripts/cart.js"></script>
</head>
<body>
	<button onclick="swap()">swap</button>
	<div id="checkout-login">
		<h4>Please Login or Register to Complete Purchase</h4>
		<a href="login.html">Login</a>/<a href="register.html">register</a>
	</div>
	<div id="checkout" style="display: none">
		<Form>
			<h4>Confirm Billing and Shipping Info</h4>
			<label for="name">Name: </label> <input name="name" id="name"
				value="${name}"> <br /> <label for="name">Address:
			</label> <input name="name" id="name" value="${name}">
			<h4>Enter Payment Method</h4>
			<label for="creditCard">Card #: </label> <input name="name" id="name"
				value="${name}"> <br />
			<button>Submit Order</button>
		</Form>
	</div>
</body>
</html>