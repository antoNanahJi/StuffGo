const logout = () => {
	fetch('/StuffGo/User?type=logout')
		.then((response) => {
			// console.log('fetched...');
			return response.json();
		})
		.then((responseJSON) => {
			// console.log('received JSON: ', responseJSON);
			if (responseJSON.username === null) {
				window.location.href = '/StuffGo/index.jsp';
			} else {
				alert('Could not logout');
			}
		})
		.catch('cannot fetch...');
};

window.onload = () => {
	logout();
};
