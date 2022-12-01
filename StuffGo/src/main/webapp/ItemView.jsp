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
		<script type="text/javascript" src="scripts/ItemInfo.js">;</script>
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
		                	<h3>${name}</h3>
		                	<p>${description}</p>
		                	<h3>${price}</h3>
		                	<button type="button">Add To Cart</button>
		              	</div>        
		            </div>
		        </div>
		        <div class="row productDetails">
		        	<div id="input-group">
						<textarea class="form-control" id="customer_review" aria-label="With textarea"></textarea>
						<button type="button" name="ajax" value="true"
            				onclick="addReview('http://localhost:8080/StuffGo/ItemInfo?out=addReview&ID=${itemID}')">Write a customer review</button>
					</div>        
		       </div>

 
			  <div class="row productDetails">
			    <div style="overflow-y: scroll; height:200px;" id="reviews" >

			    </div>
			  </div>
			</div>
			</div>
		</section>
	</body>
</html>