window.onload = () => {
	console.log('running on load');
	// In the navbar we have a login button, this button should be logout if the user is logged in
	const loginText = document.getElementById('loginout');
	// In the navbar we have a name, this name should be hidden if the user is not logged in
	const nameNav = document.getElementById('nohov');

	// If the user is on the login or register page, hide the login button and the name
	const isLoginOrRegisterPage =
		document.URL.includes('login.jsp') || document.URL.includes('register.jsp');

	if (isLoginOrRegisterPage) {
		loginText.hidden = true;
		nameNav.hidden = true;
	}

	// Condition for "If username inside nameNav exists (if user is logged in)"
	if (nameNav && nameNav.innerHTML && nameNav.innerHTML.length !== 0) {
		console.log('nameNav is not empty therefore changing login to logout');
		// Change login button to logout button
		loginText.innerHTML = 'logout';
		// Replace to logoutpage
		loginText.href = loginText.href.replace('login.jsp', 'logout.jsp');
		console.log(loginText.href);
	}
};
