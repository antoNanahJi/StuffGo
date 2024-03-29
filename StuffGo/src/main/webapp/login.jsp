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
  <section style="padding-bottom:74px;">
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
      <a style="text-align:center ;" class="justify-content-center" href="./register.jsp">Do not have an account?</a>
    </div>
			</div>
    </div>
		</form>
	
  </div>
		</section>
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
		<script type="text/javascript" src="scripts/load.js">;</script>
	</body>
</html>
