var counter = 0;
var total;
var swapCounter = 0;

function updateTotal() {
	document.getElementById("total").value = total;
}
function quantityUp(index, price) {
	var num = document.getElementById("num" + index);
	num.value++;
	var cost = document.getElementById("cost" + index);
	cost.innerHTML = "$ " + price * num.value;
	if (total != null) {
		total = total + price;
	}
	else {
		total = parseInt(document.getElementById("total").value) + price;
	}
	updateTotal();
}

function quantityDown(index, price) {
	var num = document.getElementById("num" + index);
	if (num.value <= 1) {
		var item = document.getElementById("item" + index);
		item.remove();
	}
	else {
		num.value--;
		var cost = document.getElementById("cost" + index);
		cost.innerHTML = " $" + price * num.value;
	}
	if (total != null) {
		total = total - price;
	}
	else {
		total = parseInt(document.getElementById("total").value) - price;
	}
	updateTotal();
}

function swap() {
	counter++;
	if (counter % 2 == 1) {
		document.getElementById("checkout-login").style.display = "none";
		document.getElementById("checkout").style.display = "block";
	} else {
		document.getElementById("checkout-login").style.display = "block";
		document.getElementById("checkout").style.display = "none";
		counter = 0;
	}
}