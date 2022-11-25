const register = () => {
	const usernameValue = document.getElementById('usernameInput').value;
	const passwordValue = document.getElementById('passwordInput').value;
	const passwordToSend = keccak_256(passwordValue);
	const defaultBillingValue = document.getElementById('defaultBillingInput').value;
	const defaultShippingValue = document.getElementById('defaultShippingInput').value;

	fetch(
		`/StuffGo/User?login=false&username=${usernameValue}&passwordHash=${passwordToSend}&billing=${defaultBillingValue}&shipping=${defaultShippingValue}`
	)
		.then((response) => {
			console.log('fetched...');
			return response.json();
		})
		.then((responseJSON) => {
			if (responseJSON.isRegistered) {
				window.location.href = '/StuffGo/homepage.html';
			} else {
				alert('Invalid values, maybe username is taken');
			}
		})
		.catch('cannot fetch...');
};
