window.onload = () => {
	var loginText = document.getElementById('loginout');
	var nameNav = document.getElementById('nohov');
	if(nameNav.innerHTML.length!=0){
		loginText.innerHTML="logout";
		console.log(loginText.href);
	}
};