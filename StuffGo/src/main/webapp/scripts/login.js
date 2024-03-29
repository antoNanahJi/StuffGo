const login = () => {
	// Get values from input tags
	const usernameValue = document.getElementById('usernameInput').value;
	const passwordValue = document.getElementById('passwordInput').value;
	// Hash the password so that it is not exposed to the server
	const passwordToSend = keccak_256(passwordValue);

	// Make sure that the user has entered the required fields
	if (usernameValue === null || usernameValue === '' || usernameValue.length === 0) {
		addToast('Please enter a username');
		return;
	}
	if (passwordValue === null || passwordValue === '' || passwordValue.length === 0) {
		addToast('Please enter a password');
		return;
	}

	// Send the username and password to the server
	fetch(`./User?type=login&username=${usernameValue}&passwordHash=${passwordToSend}`)
		.then((response) => {
			console.log('fetched...');
			return response.json();
		})
		.then((responseJSON) => {
			console.log('received JSON: ', responseJSON);
			if (responseJSON.username) {
				var target = './index.jsp';
				
				// Redirect user to admin page if login was from Analytics page
				if (responseJSON.AdminPage != null && responseJSON.AdminPage == 'true') {
					target = './Analytics';
				}

				// Redirect user to index page if login is successful
				window.location.href = target;
				

			} else {
				// Alert the user that login failed on the server side
				addToast('Wrong username or password');
			}
		})
		.catch('cannot fetch...');
};
// Adds a toast
function addToast(msg) {
	document.getElementById('toast-message').innerHTML = msg;
	const toastLiveExample = document.getElementById('liveToast');
	const toast = new bootstrap.Toast(toastLiveExample);
	toast.show();
}
