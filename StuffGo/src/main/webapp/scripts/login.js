const login = () => {
	// Get values from input tags
	const usernameValue = document.getElementById('usernameInput').value;
	const passwordValue = document.getElementById('passwordInput').value;
	// Hash the password so that it is not exposed to the server
	const passwordToSend = keccak_256(passwordValue);

	// Make sure that the user has entered the required fields
	if (usernameValue === null || usernameValue === '' || usernameValue.length === 0) {
		alert('Please enter a username');
		return;
	}
	if (passwordValue === null || passwordValue === '' || passwordValue.length === 0) {
		alert('Please enter a password');
		return;
	}

	// Send the username and password to the server
	fetch(`/StuffGo/User?type=login&username=${usernameValue}&passwordHash=${passwordToSend}`)
		.then((response) => {
			console.log('fetched...');
			return response.json();
		})
		.then((responseJSON) => {
			console.log('received JSON: ', responseJSON);
			if (responseJSON.username) {
				var target = '/StuffGo/index.jsp';
				
				// Redirect user to admin page if login was from Analytics page
				if (responseJSON.AdminPage != null && responseJSON.AdminPage == 'true') {
					target = '/StuffGo/Analytics';
				}

				// Redirect user to index page if login is successful
				window.location.href = target;
				

			} else {
				// Alert the user that login failed on the server side
				alert('Wrong username or password');
			}
		})
		.catch('cannot fetch...');
};
