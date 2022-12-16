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
<script src="scripts/checkout.js"></script>
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
<c:set var="function" value="loadLogin()" />
<c:if test="${Submitted != null && Submitted == true}">
	<c:set var="function" value="loadSubmitted()" />
</c:if>
<c:if test="${username != null && Submitted == false }">
	<c:set var="function" value="loadCheckout()" />
</c:if>
<body onload="${function}">
  <nav class="navbar navbar-expand-md justify-content-between navigation">
    <div class="container">
      <a class="navbar-brand" href="./index.jsp">StuffGo</a>
       <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
      <div class="collapse navbar-collapse justify-content-end" id="navbarNav">
        <ul class="navbar-nav">
        <li class="nav-item">
               	<a class="nav-link"  id="nohov">${username}</a>
             </li>
         	<li class="nav-item active">
             	<a class="nav-link" href="./index.jsp">Catalog</span></a>
             </li>
     		<li class="nav-item">
               	<a class="nav-link" id="loginout" href="./login.jsp">Login</a>
             </li>
             <li class="nav-item">
               	<a class="nav-link" href="./Cart">Cart</a>
             </li>
        </ul>
      </div>
    </div>
  </nav>
	<div id="checkout-login">
		<h4>Please Login or Register to Complete Purchase</h4>
		<a href="login.jsp">Login</a>/<a href="register.jsp">register</a>
	</div>
	<div id="confirmedMsg" style="display: none;">
	        <div class="col-lg-12 productContainer">
	           	<div class="row productDetails">
	               	<p>Order Successfully Completed.</p>
	           	</div>
	    </div>
	</div>
	<div id="checkout" style="display: none; max-width:600px;margin-bottom:74px" class="container filter">
		<span id="ccCounter" style="display: none">${applicationScope['creditCounter']}</span>
		<Form method="get"
			onSubmit="return validate('./Checkout?out=count')">
			<h4>Confirm Billing and Shipping Info</h4>
			<input name="cart" value="${param['cart']}" readonly
				style="display: none">
			<h5 style="padding-top: 24px;">Billing</h5>
			<div class="row login">
			<label for="billingName">Name: </label>
			<input name="billingName" id="billingName" value="${username}"> 
				</div>
				<div class="row login"> 
				<label for="billingAddress">Address: </label> 
				<input name="billingAddress" id="billingAddress" value="${billingAddress}">
				</div>
			<h5 style="padding-top: 24px;">Shipping</h5>
			<div class="row login">
			<label for="shippingName">Name: </label> 
			<input name="shippingName" id="shippingName" value="${username}"> 
			</div> 
				<div class="row login">
				<label for="shippingAddress">Address: </label> 
				<input name="shippingAddress" id="shippingAddress" value="${shippingAddress}">
				</div>
			<h4 style="padding-top: 24px;">Enter Payment Method</h4>
			<div class="row login">
			<label for="creditCard">Card #: </label> 
			<input type = "number" name="creditCard" id="creditCard"> 
			</div>
			<h4 style="padding-top: 24px;">Order Details</h4>
			<c:forEach items="${items}" var="item">
				<p>${item.value.getName()}${item.value.getBrand()} quantity
					${item.key[1]} ${item.value.getQuantity()}</p>
			</c:forEach>
			<button type="submit" name="submit" value="true">Submit
				Order</button>

		</Form>
	</div>
	  <footer class="fixed-bottom" style="background-color:grey;margin-top:24px">
  <div class="container">
  <p style="color:white; font-weight:600;padding-top:24px;padding-bottom:24px;margin-bottom:0 !important;">2022 EECS 4413 - Group AC StuffGo Project</p>
  </div>
  </footer>
	<div class="toast-container position-fixed bottom-0 end-0 p-3">
			    <div id="liveToast" class="toast" role="alert" aria-live="assertive" aria-atomic="true">
			      <div class="toast-header">
			        <strong class="me-auto">Alert</strong>
			        <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
			      </div>
			      <div class="toast-body" id="toast-message"></div>
			    </div>
	</div>
</body>
</html>