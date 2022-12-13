<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Document</title>
  <!-- CSS only -->
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
               	<a class="nav-link" href="http://localhost:8080/StuffGo/Cart">Cart</a>
             </li>
        </ul>
      </div>
    </div>
  </nav>
  <section>
    <div class="container">
      <div class="row">
        <div class="col-lg-3 filter">
          <h3>Filters</h3>
          <form method="POST" id="filters"> 
            <h5>Brands</h5>
            <input type="radio" id="html" name="fav_language" value="HTML">
            <label for="html">HTML</label><br>
            <input type="radio" id="css" name="fav_language" value="CSS">
            <label for="css">CSS</label><br>
            <input type="radio" id="javascript" name="fav_language" value="JavaScript">
            <label for="javascript">JavaScript</label><br><br>


            <h5>Types</h5>
            <input type="radio" id="age1" name="age" value="30">
            <label for="age1">0 - 30</label><br>
            <input type="radio" id="age2" name="age" value="60">
            <label for="age2">31 - 60</label><br>
            <input type="radio" id="age3" name="age" value="100">
            <label for="age3">61 - 100</label><br><br>

            <h5>Categories</h5>
            <input type="radio" id="age1" name="age" value="30">
            <label for="age1">0 - 30</label><br>
            <input type="radio" id="age2" name="age" value="60">
            <label for="age2">31 - 60</label><br>
            <input type="radio" id="age3" name="age" value="100">
            <label for="age3">61 - 100</label><br><br>
          </form>

          <button type="button" name="ajax" value="true"
            onclick="doSimpleAjax('/StuffGo/home?');">Filter Results</button>
            <button type="button" name="reset" value=""
            onclick="resetRadio();">Reset Filters</button>
        </div>
        <div class="col-lg-9 items">
          <div class="row" id="result">
          </div>
        </div>
      </div>
    </div>
  </section>  
  <script type="text/javascript" src="scripts/item.js">;</script>
  
</body>
</html>