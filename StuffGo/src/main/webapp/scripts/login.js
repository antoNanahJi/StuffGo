const login = () => {
	const usernameValue = document.getElementById('usernameInput').value;
	const passwordValue = document.getElementById('passwordInput').value;
	const passwordToSend = keccak_256(passwordValue);

	fetch(`/StuffGo/User?type=login&username=${usernameValue}&passwordHash=${passwordToSend}`)
		.then((response) => {
			console.log('fetched...');
			return response.json();
		})
		.then((responseJSON) => {
			console.log("received JSON: ", responseJSON);
			if (responseJSON.username) {
				window.location.href = '/StuffGo/index.jsp';
			} else {
				alert('Wrong username or password');
			}
		})
		.catch('cannot fetch...');
};
