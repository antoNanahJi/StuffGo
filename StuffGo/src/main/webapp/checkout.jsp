<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- CSS only -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
	crossorigin="anonymous">
<title>Checkout</title>
<script src="scripts/cart.js"></script>
</head>
<body>
	<nav class="navbar navbar-expand-md justify-content-between navigation">
		<div class="container">
			<a class="navbar-brand"
				href="http://localhost:8080/StuffGo/index.html">StuffGo</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarNav" aria-controls="navbarNav"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse justify-content-end"
				id="navbarNav">
				<ul class="navbar-nav">
					<li class="nav-item active"><a class="nav-link"
						href="http://localhost:8080/StuffGo/index.html">Catalog</span></a></li>
					<li class="nav-item"><a class="nav-link"
						href="http://localhost:8080/StuffGo/login.html">Login</a></li>
					<li class="nav-item"><a class="nav-link"
						href="http://localhost:8080/StuffGo/cart.html">Cart</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<button onclick="swap()">swap</button>
	<div id="checkout-login">
		<h4>Please Login or Register to Complete Purchase</h4>
		<a href="login.html">Login</a>/<a href="register.html">register</a>
	</div>
	<div id="checkout" style="display: none">
		<Form>
			<h4>Confirm Billing and Shipping Info</h4>
			<label for="name">Name: </label> <input name="name" id="name"
				value="${name}"> <br /> <label for="address">Address:
			</label> <input name="address" id="address" value="${address}">
			<h4>Enter Payment Method</h4>
			<label for="creditCard">Card #: </label> 
			<input name="creditCard" id="creditCard"> <br />
			<button>Submit Order</button>
		</Form>
	</div>
</body>
</html>