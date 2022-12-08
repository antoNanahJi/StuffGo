/**
 * Validate function for user inputs
 */
function reportMonthlyItemSell(address){
	var request = new XMLHttpRequest();
	
	request.open("GET", address, true);
	request.onreadystatechange = function() {
		handlerA(request);
	};
	request.send(null);
}

/**
 * Called after the ajax request is success
 */
function handlerA(request){
	
	if ((request.readyState == 4) && (request.status == 200)){
		
		var target = document.getElementById("result");
		target.innerHTML = "";
		console.log(request.responseText);
		//here you want to add parse the json and display individual key,
		//values pairs as html elements ( tables, paragraphs, etc..)
		if (request.responseText && request.responseText.charAt(0) == "{") {
			var rs=JSON.parse(request.responseText);
			
			if (rs.records && rs.records.length > 0) {
				addTableA(target, rs.records);	
			}

		}
	}
}

/**
 * Adds table to the html
 */
function addTableA(target, jsonData) {
 	var months = ["","January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];
		
	if (target != null && jsonData != null) {
			var myTable = document.createElement("TABLE");
			myTable.setAttribute("id", "myTable");
			myTable.setAttribute("class", "table");
			
			
			var r = document.createElement("TR");
			var h1 = document.createElement("TH");
			h1.innerHTML = "Item ID";
			r.appendChild(h1);	
			
			var h2 = document.createElement("TH");
			h2.innerHTML = "Quantity";
			r.appendChild(h2);	
				
			var r1 = document.createElement("TR");
			for(var i=0; i<months.length; i++) {		  
				var h = document.createElement("TH");
				h.innerHTML = months[i];			  
				r1.appendChild(h);		
			}
			myTable.appendChild(r);
			myTable.appendChild(r1);
			
			var size = jsonData.length;
			for (var i=0; i<size; i++) {
				var r = document.createElement("TR");
				myTable.appendChild(r);
				
				var td1 = document.createElement("TD");
				td1.innerHTML = jsonData[i].itemID;
				r.appendChild(td1);
				console.log(jsonData[i]);
				var s = jsonData[i].quantity.length;
				for(var k=0; k < s; k++) {		  
					var td = document.createElement("TD");
					td.innerHTML = jsonData[i].quantity[k];		  
					r.appendChild(td);	
				}
				
				  
				myTable.appendChild(r);
			}
			
			target.appendChild(myTable);
	}
}


/**
 * Applies ajax
 */
function reportWebsiteUsage(address){
	var request = new XMLHttpRequest();
	
	request.open("GET", address, true);
	request.onreadystatechange = function() {
		handler(request);
	};
	request.send(null);
}

/**
 * Called after the ajax request is success
 */
function handler(request){
	
	if ((request.readyState == 4) && (request.status == 200)){
		
		var target = document.getElementById("result");
		target.innerHTML = "";
		//here you want to add parse the json and display individual key,
		//values pairs as html elements ( tables, paragraphs, etc..)
		if (request.responseText && request.responseText.charAt(0) == "{") {
			var rs=JSON.parse(request.responseText);
			
			if (rs.records && rs.records.length > 0) {
				addTable(target, rs.records);	
			}

		}
	}
}

/**
 * Adds table to the html
 */
function addTable(target, jsonData) {
 
	if (target != null && jsonData != null) {
			var myTable = document.createElement("TABLE");
			myTable.setAttribute("id", "myTable");
			myTable.setAttribute("class", "table");
			
			var r1 = document.createElement("TR");
			  		  
			var h1 = document.createElement("TH");
			h1.innerHTML = "IP ADDRESS";
			var h2 = document.createElement("TH");
			h2.innerHTML = "DATE";
			var h3 = document.createElement("TH");
			h3.innerHTML = "ITEM ID";
			var h4 = document.createElement("TH");
			h4.innerHTML = "EVENT TYPE";
			  
			r1.appendChild(h1);
			r1.appendChild(h2);
			r1.appendChild(h3);
			r1.appendChild(h4);
			myTable.appendChild(r1);
			
			var size = jsonData.length;
			for (var i=0; i<size; i++) {
				var r = document.createElement("TR");
				myTable.appendChild(r);
				var td1 = document.createElement("TD");
				td1.innerHTML = jsonData[i].ipAddress;
				var td2 = document.createElement("TD");
				td2.innerHTML = jsonData[i].date;
				var td3 = document.createElement("TD");
				td3.innerHTML = jsonData[i].itemID;
				var td4 = document.createElement("TD");
				td4.innerHTML = jsonData[i].event;
				 
				r.appendChild(td1);
				r.appendChild(td2);
				r.appendChild(td3);
				r.appendChild(td4);
				  
				myTable.appendChild(r);
			}
			
			target.appendChild(myTable);
	}
}
