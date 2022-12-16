var counter = 0;
var total;
var swapCounter = 0;

//increases quantity of item in cart by 1
function quantityUp(index, id, price, quantity, address) {
	var num = document.getElementById("num" + index);

	if(num.value >= quantity) {
		return
	}
	
	num.value++;
	var cost = document.getElementById("cost" + index);
	cost.innerHTML = "$" + price * num.value;
	if (total != null) {
		total = total + price;
	}
	else {
		total = parseInt(document.getElementById("total").value) + price;
	}
	changeItemQuantity(address, id, num.value);
}

//decreases quantity of item in cart by 1
function quantityDown(index, id, price, address) {
	var num = document.getElementById("num" + index);
	if (num.value <= 1) {
		num.value--;
		var item = document.getElementById("item" + index);
		item.remove();
	}
	else {
		num.value--;
		var cost = document.getElementById("cost" + index);
		cost.innerHTML = "$" + price * num.value;
	}
	if (total != null) {
		total = total - price;
	}
	else {
		total = parseInt(document.getElementById("total").value) - price;
	}
	changeItemQuantity(address, id, num.value);
}

//Tells java  controller about change in an items quantity and sets new total in jsp file
function changeItemQuantity(address, id, quantity){
	var request = new XMLHttpRequest();
	data = '&changedItemID=' + id + "&changedItemQuantity=" + quantity;
	console.log(address + data);
	request.open('GET', address + data);
	request.onreadystatechange = function () {
		handler(request);
	};
	request.send(null);
	document.getElementById("total").value = total;
}

function handler(request) {
	if (request.readyState == 4 && request.status == 200) {
		var CheckoutBtn = document.getElementById('Checkout');
		console.log(request.responseText);

		if (request.responseText != null && request.responseText != '') {
			var rs = JSON.parse(request.responseText);
			
			if (rs.isCartEmpty == 'true') {
				CheckoutBtn.style.display = 'none'; 
				document.getElementById('Empty').style.display = 'block';
			} else {
				CheckoutBtn.style.display = 'block';
			}
		}
	}
}