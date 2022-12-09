const logout = () => {
	// Send request to server to logout
	fetch('/StuffGo/User?type=logout')
		.then((response) => {
			// console.log('fetched...');
			return response.json();
		})
		.then((responseJSON) => {
			// console.log('received JSON: ', responseJSON);
			if (responseJSON.username === null) {
				// Redirect user
				window.location.href = '/StuffGo/index.jsp';
			} else {
				alert('Could not logout');
			}
		})
		.catch('cannot fetch...');
};

// Run logout function when page loads
window.onload = () => {
	logout();
};
