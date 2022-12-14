const register = () => {
	// Get values from input tags
	const usernameValue = document.getElementById('usernameInput').value;
	const passwordValue = document.getElementById('passwordInput').value;
	// Hash the password so that it is not exposed to the server
	const passwordToSend = keccak_256(passwordValue);
	const defaultBillingValue = document.getElementById('defaultBillingInput').value;
	const defaultShippingValue = document.getElementById('defaultShippingInput').value;
	const nameValue = document.getElementById('nameInput').value;

	// Make sure that the user has entered the required fields
	if (usernameValue === null || usernameValue === '' || usernameValue.length === 0) {
		addToast('Please enter a username');
		return;
	}
	if (passwordValue === null || passwordValue === '' || passwordValue.length === 0) {
		addToast('Please enter a password');
		return;
	}
	if (
		defaultBillingValue === null ||
		defaultBillingValue === '' ||
		defaultBillingValue.length === 0
	) {
		addToast('Please enter a billing info');
		return;
	}
	if (
		defaultShippingValue === null ||
		defaultShippingValue === '' ||
		defaultShippingValue.length === 0
	) {
		addToast('Please enter a shipping info');
		return;
	}
	if (nameValue === null || nameValue === '' || nameValue.length === 0) {
		addToast('Please enter a name info');
		return;
	}

	// Send the necessarry info for registering a user to the server
	fetch(
		`/User?type=register&username=${usernameValue}&passwordHash=${passwordToSend}&billing=${defaultBillingValue}&shipping=${defaultShippingValue}&name=${nameValue}`
	)
		.then((response) => {
			console.log('fetched...');
			return response.json();
		})
		.then((responseJSON) => {
			console.log('received JSON: ', responseJSON);
			if (responseJSON.username) {
				// Redirect user to index page if registeration is successful
				window.location.href = '/index.jsp';
			} else {
				// Alert the user that registeration failed on the server side
				addToast('Invalid values, maybe username is taken');
			}
		})
		.catch('cannot fetch...');
};
function addToast(msg) {
	document.getElementById('toast-message').innerHTML = msg;
	const toastLiveExample = document.getElementById('liveToast');
	const toast = new bootstrap.Toast(toastLiveExample);
	toast.show();
}