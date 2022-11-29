// SQL ARRAY VALUES
const sqlValues = [ "SELECT", "UPDATE", "DELETE", "INSERT INTO", "CREATE DATABASE", "ALTER DATABASE", "CREATE TABLE",
	"ALTER TABLE", "DROP TABLE", "CREATE INDEX", "DROP INDEX", "=", ";" ];



window.onload = () => {
	var request = new XMLHttpRequest();
	request.open("GET", "http://localhost:8080/StuffGo/home", true);
	request.onreadystatechange = function() {
	handlerLoad(request);
	console.log(request);
	};
	request.send(null);
}

// SEND AJAX REQUEST
function doSimpleAjax(address) {
	if (validate()) {
		var request = new XMLHttpRequest();
		var data = "";
		data += "&prefix=" + document.getElementById("prefix").value
			+ "&";
		data += "credit_taken=" + document.getElementById("credit_taken").value;
		request.open("GET", (address + data), true);
		request.onreadystatechange = function() {
			handler(request);
			console.log(request);
		};
		request.send(null);
	}
}
//HANDLE AJAX REQUEST
function handlerLoad(request) {
	if ((request.readyState == 4) && (request.status == 200)) {
		var mainTarget = document.getElementById("result");
		var filterTarget = document.getElementById("filters");
		//here you want to add parse the json and display individual key,
		//values pairs as html elements ( tables, paragraphs, etc..)
		var rsFilters = JSON.parse(request.responseText);
		var rsParagrahps = JSON.parse(request.responseText);
		addParagraphs(mainTarget, rsParagrahps);
		addFilters(filterTarget,rsFilters)
	}
}

//HANDLE AJAX REQUEST
function handlerClick(request) {
	if ((request.readyState == 4) && (request.status == 200)) {
		var target = document.getElementById("result");
		//here you want to add parse the json and display individual key,
		//values pairs as html elements ( tables, paragraphs, etc..)
		var rs = JSON.parse(request.responseText);
		addParagraphs(target, rs);
	}
}
//FOR A TABLE IN THE FUNCTION
function addParagraphs(parent, rs) {
	let resu = "";
	rs.items.map((datum) => {
    resu+= "<a "
	+ "class=\"col-4 item-box d-flex justify-content-center align-items-end\" "
	+  "style=\"background-image:linear-gradient(to bottom, rgba(128, 128, 128, 0),rgba(128, 128, 128, 0.2), rgb(128, 128, 128 ,1)), url(" + datum.image + ");\" "
	+  "href=\"http://localhost:8080/StuffGo/ItemInfo?ID=" + datum.ID + "\">"
	+ "<h6>" + datum.name+"</h6>"
	+ "</a>"
	parent.innerHTML = resu;
});
console.log(rs);
console.log(resu);
}

function addFilters(parent, rs) {
	let resu = "";
	resu+= "<h5>Brands</h5>";
	[...new Set(rs.items.map(item => resu+= 
	 "<input type=\"radio\" id=\""+ item.brand +"\" name=\"brand\" value=\"" + item.brand +  ">" + 
            "<label for=\"html\">" + item.brand + "</label><br>"
	))];
		resu+= "<h5>Types</h5>";
	[...new Set(rs.items.map(item => resu+= 
	 "<input type=\"radio\" id=\""+ item.brand +"\" name=\"brand\" value=\"" + item.types +  ">" + 
            "<label for=\"html\">" + item.brand + "</label><br>"
	))];
			resu+= "<h5>Categories</h5>";
	[...new Set(rs.items.map(item => resu+= 
	 "<input type=\"radio\" id=\""+ item.brand +"\" name=\"brand\" value=\"" + item.category +  ">" + 
            "<label for=\"html\">" + item.brand + "</label><br>"
	))];
	parent.innerHTML = resu;
	console.log(rs);
}