var counter = 0;
var total;
var swapCounter = 0;

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

function changeItemQuantity(address, id, quantity){
	var request = new XMLHttpRequest();
	data = '&changedItemID=' + id + "&changedItemQuantity=" + quantity;
	console.log(address + data);
	request.open('GET', address + data);
	request.send(null);
	document.getElementById("total").value = total;
}