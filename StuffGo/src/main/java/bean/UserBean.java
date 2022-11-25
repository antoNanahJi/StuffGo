package bean;

//Bean class for User.
public class UserBean {
	private String username;
	private String password;
	private String shipping;
	private String billing;
	
	public UserBean(String username, String password, String shipping, String billing){
		this.username = username;
		this.password = password;
		this.shipping = shipping;
		this.billing = billing;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getShipping() {
		return shipping;
	}

	public void setShipping(String shipping) {
		this.shipping = shipping;
	}

	public String getBilling() {
		return billing;
	}

	public void setBilling(String billing) {
		this.billing = billing;
	}
}
