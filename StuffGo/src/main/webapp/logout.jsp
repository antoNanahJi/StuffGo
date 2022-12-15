<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>Log in</title>
		<!-- Below line imports the keccak_256 function -->
		<script src="https://cdn.jsdelivr.net/gh/emn178/js-sha3/build/sha3.min.js"></script>
		<!--  Below line imports our own js file -->
		<link
			href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
			rel="stylesheet"
			integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
			crossorigin="anonymous"
		/>
		<!-- JavaScript Bundle with Popper -->
		<script
			src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
			integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
			crossorigin="anonymous"
		></script>
		<link
			rel="StyleSheet"
			href="res/main.css"
			type="text/css"
			title="cse4413"
			media="screen, print"
		/>
	</head>
	<body>
		<script type="text/javascript" src="scripts/logout.js">
			;
		</script>
		<div class="toast-container position-fixed bottom-0 end-0 p-3">
			    <div id="liveToast" class="toast" role="alert" aria-live="assertive" aria-atomic="true">
			      <div class="toast-header">
			        <strong class="me-auto">Alert</strong>
			        <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
			      </div>
			      <div class="toast-body" id="toast-message"></div>
			    </div>
		 	</div>
		 	  <footer class="fixed-bottom" style="background-color:grey;margin-top:24px">
  <div class="container">
  <p style="color:white; font-weight:600;padding-top:24px;padding-bottom:24px;margin-bottom:0 !important;">2022 EECS 4413 - Group AC StuffGo Project</p>
  </div>
  </footer>
	</body>
</html>
