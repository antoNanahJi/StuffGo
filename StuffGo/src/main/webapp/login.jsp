<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>Log in</title>
		<!-- Below line imports the keccak_256 function -->
		<script src="https://cdn.jsdelivr.net/gh/emn178/js-sha3/build/sha3.min.js"></script>
		<!--  Below line imports our own js file -->
		<script src="scripts/login.js"></script>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
  <!-- JavaScript Bundle with Popper -->
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
    crossorigin="anonymous"></script>
    	<link rel="StyleSheet" href="res/main.css" type="text/css" title="cse4413" media="screen, print"/>
	</head>
	<body>
  <nav class="navbar navbar-expand-md justify-content-between navigation">
    <div class="container">
      <a class="navbar-brand" href="http://localhost:8080/StuffGo/index.jsp">StuffGo</a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
        aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse justify-content-end" id="navbarNav">
        <ul class="navbar-nav">
        <li class="nav-item">
               	<a class="nav-link"  id="nohov">${username}</a>
             </li>
         	<li class="nav-item active">
             	<a class="nav-link" href="http://localhost:8080/StuffGo/index.jsp">Catalog</span></a>
             </li>
     		<li class="nav-item">
               	<a class="nav-link" id="loginout" href="http://localhost:8080/StuffGo/login.jsp">Login</a>
             </li>
             <li class="nav-item">
               	<a class="nav-link" href="http://localhost:8080/StuffGo/cart.jsp">Cart</a>
             </li>
        </ul>
      </div>
    </div>
  </nav>
  <section>
		<form action="" method="post" onsubmit="return false">
			<div style="max-width:600px ;" class="container filter">
        <div class="col">
        <div class="row login">
				<label for="usernameInput"><b>Username</b></label>
				<input type="text" placeholder="Enter Username" id="usernameInput" required />
      </div>
      <div class="row login">
				<label for="passwordInput"><b>Password</b></label>
				<input type="password" placeholder="Enter Password" id="passwordInput" required />
      </div>
      <button type="submit" onclick="return login()">Login</button>
      <div class="row p-3">
      <a style="text-align:center ;" class="justify-content-center" href="http://localhost:8080/StuffGo/register.jsp">Do not have an account?</a>
    </div>
			</div>
    </div>
		</form>
	
  </div>
		</section>
		<script type="text/javascript" src="scripts/load.js">;</script>
	</body>
</html>