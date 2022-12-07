const register = () => {
	const usernameValue = document.getElementById('usernameInput').value;
	const passwordValue = document.getElementById('passwordInput').value;
	const passwordToSend = keccak_256(passwordValue);
	const defaultBillingValue = document.getElementById('defaultBillingInput').value;
	const defaultShippingValue = document.getElementById('defaultShippingInput').value;
	const nameValue = document.getElementById('nameInput').value;

	if (usernameValue === null || usernameValue === '' || usernameValue.length === 0) {
		alert('Please enter a username');
		return;
	}
	if (passwordValue === null || passwordValue === '' || passwordValue.length === 0) {
		alert('Please enter a password');
		return;
	}
	if (
		defaultBillingValue === null ||
		defaultBillingValue === '' ||
		defaultBillingValue.length === 0
	) {
		alert('Please enter a billing info');
		return;
	}
	if (
		defaultShippingValue === null ||
		defaultShippingValue === '' ||
		defaultShippingValue.length === 0
	) {
		alert('Please enter a shipping info');
		return;
	}
	if (nameValue === null || nameValue === '' || nameValue.length === 0) {
		alert('Please enter a name info');
		return;
	}

	fetch(
		`/StuffGo/User?type=register&username=${usernameValue}&passwordHash=${passwordToSend}&billing=${defaultBillingValue}&shipping=${defaultShippingValue}&name=${nameValue}`
	)
		.then((response) => {
			console.log('fetched...');
			return response.json();
		})
		.then((responseJSON) => {
			console.log('received JSON: ', responseJSON);
			if (responseJSON.username) {
				window.location.href = '/StuffGo/index.jsp';
			} else {
				alert('Invalid values, maybe username is taken');
			}
		})
		.catch('cannot fetch...');
};
