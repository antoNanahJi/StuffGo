function validate() {
	var ok = true;
	var p = document.getElementById("credit").value;
	if (isNaN(p) || p <= 0) {
		alert("Credit invalid!");
		document.getElementById("principle-error").style.display = "inline";
		document.getElementById("period-error").style.color = "red";
		ok = false;
	} 
	
	return ok;
}


function handler(request) {
	if ((request.readyState == 4) && (request.status == 200)) {
		var target = document.getElementById("result");
		target.innerHTML = request.responseText;
		//var rs=JSON.parse(request.responseText);
		//console.log(rs);
		//addResult(target, rs);
	}
}

function reportAjax(address) {
	var request = new XMLHttpRequest();
	var data = '&';
	data += "namePrefix=" + document.getElementById("namePrefix").value + "&";
	data += "credit_taken=" + document.getElementById("credit_taken").value;
	request.open("GET", ( address + "?" + data), true);
	request.onreadystatechange = function() {
		handler(request);
	};
	request.send(null);
}

function addResultAsTable(parent,r){
	
				var html_p1 = document.createElement("table");
				
				
							
				//add the <p> element to the <div>
				//parent.appendHeader(html_p1);
				//parent.appendRows(html_p2);
				
			}