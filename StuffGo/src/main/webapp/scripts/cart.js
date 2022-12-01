var counter = 0;
var total = 0;
var swapCounter = 0;


function updateTotal() {
	document.getElementById("total").value = total;
}
function quantityUp(index) {
	var num = document.getElementById("num" + index);
	num.value++;
	var cost = document.getElementById("cost" + index);
	cost.innerHTML = "$ " + 10 * num.value;
	total = total + 10;
	updateTotal();
}

function quantityDown(index) {
	var num = document.getElementById("num" + index);
	if (num.value <= 1) { var item = document.getElementById("item" + index); item.remove(); }
	else {
		num.value--;
		var cost = document.getElementById("cost" + index);
		cost.innerHTML = " $" + 10 * num.value;
	}
	total = total - 10;
	updateTotal();
}

function createItems() {
	counter++;
	var form = document.getElementById("cartForm");
	total = total + 10;
	var form = document.getElementById("cart");
	var fieldset = document.createElement("fieldset");
	var input = document.createElement("input");
	var button1 = document.createElement("button");
	var button2 = document.createElement("button");
	var cost = document.createElement("span");
	var text = document.createTextNode("item" + counter + " ");
	fieldset.setAttribute("id", "item" + counter);
	fieldset.appendChild(text);
	text =document.createTextNode(" $10");
	text = document.createTextNode(" $10");
	cost.setAttribute("id", "cost" + counter);
	cost.appendChild(text);
	fieldset.appendChild(cost);
	input.type = "number";
	input.value = 1;
	input.setAttribute("id", "num" + counter);
	fieldset.appendChild(input);
	button1.type = "button";
	button1.setAttribute("onClick", "quantityUp(" + counter + ")");
	text = document.createTextNode("+");
	button1.appendChild(text);
	button2.type = "button";
	button2.setAttribute("onClick", "quantityDown(" + counter + ")");
	text = document.createTextNode("-");
	button2.appendChild(text);
	fieldset.appendChild(button1);
	fieldset.appendChild(button2);
	form.appendChild(fieldset);
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