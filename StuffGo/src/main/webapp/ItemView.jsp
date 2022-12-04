<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
	<head>
	    <meta charset="UTF-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <title>StuffGo Item view</title>
	    <!-- CSS only -->
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
		<!-- JavaScript Bundle with Popper -->
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
		<link rel="StyleSheet" href="res/main.css" type="text/css" title="cse4413" media="screen, print"/>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
		
		
	</head>
	<body>
		<nav class="navbar navbar-expand-md justify-content-between navigation">
			<div class="container">
		    	<a class="navbar-brand" href="http://localhost:8080/StuffGo/index.html">StuffGo</a>
		        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
		        	<span class="navbar-toggler-icon"></span>
		        </button>
		        <div class="collapse navbar-collapse justify-content-end" id="navbarNav">
		        	<ul class="navbar-nav">
		            	<li class="nav-item active">
		                	<a class="nav-link" href="http://localhost:8080/StuffGo/index.html">Catalog</span></a>
		                </li>
		        		<li class="nav-item">
		                  	<a class="nav-link" href="http://localhost:8080/StuffGo/login.html">Login</a>
		                </li>
		                <li class="nav-item">
		                  	<a class="nav-link" href="http://localhost:8080/StuffGo/cart.html">Cart</a>
		                </li>
		            </ul>
		        </div>
		    </div>
		 </nav>
		 <section>
		 	<div class="container">
		    	<div class="row">
		        	<div class="col-lg-7 productImage" style="background-image: url(${image});"></div>
		            <div class="col-lg-5 productContainer">
		            	<div class="row productDetails">
		                	
		                	<div style="display: flex;" id="star-container">
		                		<h3>${name}</h3>
							</div>
		                	<p>${description}</p>
		                	<h3>$ ${price}</h3>
		                	<label for="Rate"> Rate: </label>
		            	</div>
		        	</div>
		        </div>
		        <div class="row">
		            <div class="col-lg-3 productContainer">
			        	<div class="row productDetails" style="text-align: center;">
				        		<p style=" margin: 3px;">How was your experience?</p>
								<div style="display: flex; margin-left: 25%" >
									<div id = "star_1" style=" margin-right: 3px;  color: black;" class="bi bi-star" onclick="addRating(1)"></div>
									<div id = "star_2" style=" margin-right: 3px; color: black;" class="bi bi-star" onclick="addRating(2)"></div>
									<div id = "star_3" style=" margin-right: 3px; color: black;" class="bi bi-star" onclick="addRating(3)"></div>
									<div id = "star_4" style=" margin-right: 3px; color: black;" class="bi bi-star" onclick="addRating(4)"></div>
									<div id = "star_5" style="  color: black;" class="bi bi-star" onclick="addRating(5)"></div>
								</div>
							
								<p  id="star_value" style=" margin: 0px;" >0 of 5</p>
								<button type="button" id="star_submit" name="ajax" style="visibility: hidden;" value="true" 
		            				onclick="submitRating('http://localhost:8080/StuffGo/ItemInfo?out=addRating&ID=${itemID}')">Submit</button>
					      
				        </div>
			        </div>
			        <div class="col-lg-9 productContainer">
				        <div class="row productDetails">
				        	<div id="input-group">
								<textarea class="form-control" id="customer_review" aria-label="With textarea"></textarea>
								<button type="button" name="ajax" value="true"
		            				onclick="addReview('http://localhost:8080/StuffGo/ItemInfo?out=addReview&ID=${itemID}')">Write a customer review</button>
							</div>        
				        </div>
					</div>
				</div>
 				<div class = "row">
				  <div class="row productDetails">
				    	<div style="overflow-y: scroll; height:200px;" id="reviews" ></div>
			  	  </div>
				</div>
			</div>
			<div class="toast-container position-fixed bottom-0 end-0 p-3">
			    <div id="liveToast" class="toast" role="alert" aria-live="assertive" aria-atomic="true">
			      <div class="toast-header">
			        <strong class="me-auto">Alert</strong>
			        <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
			      </div>
			      <div class="toast-body" id="toast-message"></div>
			    </div>
		 	</div>
		</section>
		<script type="text/javascript" src="scripts/ItemInfo.js">;</script>
	</body>
</html>