const login = () => {
	const usernameValue = document.getElementById('usernameInput').value;
	const passwordValue = document.getElementById('passwordInput').value;
	const passwordToSend = keccak_256(passwordValue);

	// fetch('/StuffGo/Sis')
	// 	.then((response) => {
	// 		console.log('fetched...');
	// 		console.log(response.text());
	// 		return response.json();
	// 	})
	// 	.then((responseJson) => {
	// 		console.log(responseJson);
	// 	})
	// 	.catch('cannot fetch...');
};
