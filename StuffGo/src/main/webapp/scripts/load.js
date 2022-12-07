window.onload = () => {
	console.log('running on load');
	const loginText = document.getElementById('loginout');
	const nameNav = document.getElementById('nohov');

	const isLoginOrRegisterPage =
		document.URL.includes('login.jsp') || document.URL.includes('register.jsp');

	if (isLoginOrRegisterPage) {
		loginText.hidden = true;
		nameNav.hidden = true;
	}

	// If username inside nameNav exists (if user is logged in)
	if (nameNav && nameNav.innerHTML && nameNav.innerHTML.length !== 0) {
		console.log('nameNav is not empty therefore changing login to logout');
		loginText.innerHTML = 'logout';
		loginText.href = loginText.href.replace('login.jsp', 'logout.jsp');
		console.log(loginText.href);
	}
};
