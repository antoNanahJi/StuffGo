package bean;

//Bean class for User.
/**
 * @author ziyaicoz
 *
 */
public class UserBean {
	private String username; // Primary key for the Users table
	private String password;
	private String shipping;
	private String billing;
	private int isAdmin; // If this value is 0 user is not an admin else user is an admin
	private String name;

	public UserBean(String username, String password, String shipping, String billing, int isAdmin, String name) {
		this.username = username;
		this.password = password;
		this.shipping = shipping;
		this.billing = billing;
		this.isAdmin = isAdmin;
		this.name = name;
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
	
	public int getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
