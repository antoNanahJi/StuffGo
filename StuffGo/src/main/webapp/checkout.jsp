<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
	crossorigin="anonymous">
<!-- JavaScript Bundle with Popper -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
	crossorigin="anonymous"></script>
<link rel="StyleSheet" href="res/main.css" type="text/css"
	title="cse4413" media="screen, print" />
</head>
<body>
	<nav class="navbar navbar-expand-md justify-content-between navigation">
		<div class="container">
			<a class="navbar-brand"
				href="http://localhost:8080/StuffGo/index.jsp">StuffGo</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarNav" aria-controls="navbarNav"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse justify-content-end"
				id="navbarNav">
				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link" id="nohov">${username}</a>
					</li>
					<li class="nav-item active"><a class="nav-link"
						href="http://localhost:8080/StuffGo/index.jsp">Catalog</span></a></li>
					<li class="nav-item"><a class="nav-link" id="loginout"
						href="http://localhost:8080/StuffGo/login.jsp">Login</a></li>
					<li class="nav-item"><a class="nav-link"
						href="http://localhost:8080/StuffGo/Cart">Cart</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<button onclick="swap()">swap</button>
	<div id="checkout-login">
		<h4>Please Login or Register to Complete Purchase</h4>
		<a href="login.jsp">Login</a>/<a href="register.jsp">register</a>
	</div>
	<div id="checkout" style="display: none">
		<Form>
			<h4>Confirm Billing and Shipping Info</h4>
			<h5>Billing</h5>
			<label for="billingName">Name: </label> <input name="billingName"
				id="billingName" value="${username}"> <br /> <label
				for="billingAddress">Address: </label> <input name="billingAddress"
				id="billingAddress" value="${billingAddress}">
			<h5>Shipping</h5>
			<label for="shippingName">Name: </label> <input name="shippingName"
				id="shippingName" value="${username}"> <br /> <label
				for="shippingAddress">Address: </label> <input
				name="shippingAddress" id="shippingAddress"
				value="${shippingAddress}">
			<h4>Enter Payment Method</h4>
			<label for="creditCard">Card #: </label> <input name="creditCard"
				id="creditCard"> <br />
			<h4>Order Details</h4>
			<c:forEach items="${items}" var="item">
				<p>${item.key[0]}</p>
				<p>${item.key[1]}</p>	
				<br />
				<p>${item.value.getBrand()}</p>
				<br />
			</c:forEach>
			<button>Submit Order</button>

		</Form>
	</div>
</body>
</html>