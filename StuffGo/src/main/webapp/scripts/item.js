// SQL ARRAY VALUES
const sqlValues = [
	'SELECT',
	'UPDATE',
	'DELETE',
	'INSERT INTO',
	'CREATE DATABASE',
	'ALTER DATABASE',
	'CREATE TABLE',
	'ALTER TABLE',
	'DROP TABLE',
	'CREATE INDEX',
	'DROP INDEX',
	'=',
	';',
];
//the onload call gathering all the data from database
window.onload = () => {
	var request = new XMLHttpRequest();
	request.open('GET', './home', true);
	request.onreadystatechange = function () {
		handlerLoad(request);
		console.log(request);
	};
	request.send(null);
	const loginText = document.getElementById('loginout');
	const nameNav = document.getElementById('nohov');

	if (nameNav && nameNav.innerHTML && nameNav.innerHTML.length !== 0) {
		console.log('nameNav is not empty therefore changing login to logout');
		loginText.innerHTML = 'logout';
		loginText.href = loginText.href.replace('login.jsp', 'logout.jsp');
	}
};

// SEND AJAX REQUEST
function doSimpleAjax(address) {
	var request = new XMLHttpRequest();
	var data = '';
	var brand = document.getElementsByName('brand');
	var type = document.getElementsByName('type');
	var category = document.getElementsByName('category');
	console.log(brand);

	for (i = 0; i < brand.length; i++) {
		if (brand[i].checked) {
			console.log('checked');
			console.log(brand[i].value);
			data += 'brand=' + brand[i].value;
		}
	}

	for (i = 0; i < type.length; i++) {
		if (type[i].checked) {
			console.log('checked');
			console.log(type[i].value);
			data += '&type=' + type[i].value;
		}
	}

	for (i = 0; i < category.length; i++) {
		if (category[i].checked) {
			console.log('checked');
			console.log(category[i].value);
			data += '&category=' + category[i].value;
		}
	}

	console.log(address + data);

	request.open('GET', address + data, true);
	request.onreadystatechange = function () {
		handlerClick(request);
		console.log(request);
	};
	request.send(null);
	resetRadio();
}
//RESET FILTERS FUNCTION
function resetRadio() {
	var brand = document.getElementsByName('brand');
	var type = document.getElementsByName('type');
	var category = document.getElementsByName('category');
	console.log(brand);

	for (i = 0; i < brand.length; i++) {
		brand[i].checked = false;
	}

	for (i = 0; i < type.length; i++) {
		type[i].checked = false;
	}

	for (i = 0; i < category.length; i++) {
		category[i].checked = false;
	}
}
//HANDLE AJAX REQUEST
function handlerLoad(request) {
	if (request.readyState == 4 && request.status == 200) {
		var mainTarget = document.getElementById('result');
		var filterTarget = document.getElementById('filters');
		//here you want to add parse the json and display individual key,
		//values pairs as html elements ( tables, paragraphs, etc..)
		var rsFilters = JSON.parse(request.responseText);
		var rsParagrahps = JSON.parse(request.responseText);
		addParagraphs(mainTarget, rsParagrahps);
		addFilters(filterTarget, rsFilters);
	}
}

//HANDLE AJAX REQUEST
function handlerClick(request) {
	if (request.readyState == 4 && request.status == 200) {
		var target = document.getElementById('result');
		//here you want to add parse the json and display individual key,
		//values pairs as html elements ( tables, paragraphs, etc..)
		var rs = JSON.parse(request.responseText);
		addParagraphs(target, rs);
	}
}
//HANDLE AND DISPLAY ITEM GRID
function addParagraphs(parent, rs) {
	let resu = '';
	if (rs.items.length === 0) {
		parent.innerHTML = 'No items found';
	} else {
		rs.items.map((datum) => {
			resu +=
				'<a ' +
				'class="col-4 item-box d-flex justify-content-center align-items-end" ' +
				'style="background-image:linear-gradient(to bottom, rgba(128, 128, 128, 0),rgba(128, 128, 128, 0.2), rgb(128, 128, 128 ,1)), url(' +
				datum.image +
				');" ' +
				'href="./ItemInfo?ID=' +
				datum.ID +
				'">' +
				'<h6>' +
				datum.name +
				'</h6>' +
				'</a>';
			parent.innerHTML = resu;
		});
	}
	console.log(rs);
	console.log(resu);
}
//ADD FILTERS RADIO BUTTONS WITH SPECIFICS
function addFilters(parent, rs) {
	let resu = '';
	resu += '<h5>Brands</h5>';
	let setBrand =  [...new Set(rs.items.map(datum => datum.brand))];
	console.log("are " + setBrand);
	[
			setBrand.map(
				(item) =>
					(resu +=
						'<input type="radio" id="' +
						item +
						'" name="brand" value="' +
						item +
						'">' +
						'<label for="html">' +
						item +
						'</label><br>')
			)
	];
	resu += '<h5>Types</h5>';
	let setTypes =  [...new Set(rs.items.map(datum => datum.type))];
	console.log("are " + setTypes);
	[
			setTypes.map(
				(item) =>
					(resu +=
						'<input type="radio" id="' +
						item +
						'" name="type" value="' +
						item +
						'">' +
						'<label for="html">' +
						item +
						'</label><br>')
			)
	];
	resu += '<h5>Categories</h5>';
	let setCategories =  [...new Set(rs.items.map(datum => datum.category))];
	console.log("are " + setCategories);
	[
			setCategories.map(
				(item) =>
					(resu +=
						'<input type="radio" id="' +
						item +
						'" name="category" value="' +
						item +
						'">' +
						'<label for="html">' +
						item +
						'</label><br>')
			)
	];
	parent.innerHTML = resu;
	console.log(rs);
}
