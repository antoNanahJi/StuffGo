<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cart</title>
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
	<div style="max-width: 900px;" class="container filter">
		<form id="cartForm" method="GET" action="Checkout">
			<div id="cart">
				<c:set var="counter" value="0" scope="page" />
				<c:set var="total" value="0" scope="page" />
				<c:set var="cartItemsSplit" value="${fn:split(cartItems, ',')}" />
				<c:set var="cart" value="" />
				<c:forEach items="${items}" var="item">
					<c:set var="counter" value="${counter + 1}" scope="page" />

					<fieldset id="item${counter}" class="border border-success p-3">
						<img src="${item.getImage()}" width="133" height="75">
						<h6>${item.getName()}</h6>
						<p>${item.getBrand()}</p>
						<button type="button" class="qdown"
							onClick="quantityDown(${counter}, ${item.getID()}, ${item.getPrice()}, 'http://localhost:8080/StuffGo/Cart?out=changeItem')">-
						</button>
						<c:set var="split2"
							value="${fn:split(cartItemsSplit[counter - 1], '=')}" />
						<fmt:parseNumber var="quantity" type="number" value="${split2[1]}" />
						<input id="num${counter}" value="${quantity}" readonly>
						<c:set var="total" value="${total + item.getPrice() * quantity}"
							scope="page" />
						<button type="button" class="qup"
							onClick="quantityUp(${counter}, ${item.getID()}, ${item.getPrice()}, 'http://localhost:8080/StuffGo/Cart?out=changeItem')">+</button>
						<h5 style="margin-top: 12px">
							price : <span id="cost${counter}">$${item.getPrice() *
								quantity}</span>
						</h5>
						<c:set var="cart" value="${cart}${item.getID()}-${quantity}_" />
					</fieldset>
					<br />
				</c:forEach>
				<input name="cart" value="${cart}" readonly style="display: none">
			</div>
			<div class="login">
				<Label for="total">Total: $</Label> <input type="number"
					name="total" id="total" value="${total}" readonly><br />
				<button name="checkout" value="true" type="submit">Checkout</button>
			</div>
		</form>
	</div>
	<script type="text/javascript" src="scripts/load.js">;</script>
</body>
</html>