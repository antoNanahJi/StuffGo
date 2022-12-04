<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cart</title>
<script src="scripts/cart.js"></script>
</head>
<body>
	<div>
		<form id="cartForm" action="">
			<div id="cart">
				<c:set var="counter" value="0" scope="page" />
				<c:set var="total" value="0" scope="page" />
				<c:forEach items="${items.values()}" var="item">
					<c:set var="counter" value="${counter + 1}" scope="page" />
					<c:set var="total" value="${total + item.getPrice()}" scope="page" />
					<fieldset id="item${counter}">
						${item.getBrand()}
						<button type="button"
							onClick="quantityDown(${counter}, ${item.getPrice()})">-
						</button>
						<input type="number" id="num${counter}" value="1" readonly>
						<button type="button"
							onClick="quantityUp(${counter}, ${item.getPrice()})">+</button>
						price : <span id="cost${counter}">$${item.getPrice()}</span>
					</fieldset>
					<br />
				</c:forEach>
			</div>
			<Label for="total">Total: $</Label> <input type="number" name="total"
				id="total" value="${total}" readonly><br />
			<button name="checkout" value="true" type="submit">Checkout</button>
		</form>
	</div>
</body>
</html>