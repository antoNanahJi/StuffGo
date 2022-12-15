package utilities;

import bean.UserBean;
import dao.UserDAO;


public class UserModel {
	private UserDAO userDAO;
	
	public UserModel() throws ClassNotFoundException{
		userDAO = new UserDAO();
	}

	public boolean loginUser(String username, String passwordHash) {
		if (username != null) {
			if (Security.containsSQL(username)) {
				return false;
			}
			if (Security.containsXSS(username)) {
				return false;
			}
		}
		
		if (passwordHash != null) {
			if (Security.containsSQL(passwordHash)) {
				return false;
			}
			if (Security.containsXSS(passwordHash)) {
				return false;
			}
		}
		
    	try {
    		return this.userDAO.login(username, passwordHash);
    	} catch (Exception e) {
    		return false;
    	}
	}

    public boolean registerUser(UserBean newUser) {
    	if (newUser.getName() != null) {
			if (Security.containsSQL(newUser.getName())) {
				return false;
			}
			if (Security.containsXSS(newUser.getName())) {
				return false;
			}
		}
		
		if (newUser.getBilling() != null) {
			if (Security.containsSQL(newUser.getBilling())) {
				return false;
			}
			if (Security.containsXSS(newUser.getBilling())) {
				return false;
			}
		}
		
		if (newUser.getShipping() != null) {
			if (Security.containsSQL(newUser.getShipping())) {
				return false;
			}
			if (Security.containsXSS(newUser.getShipping())) {
				return false;
			}
		}
		
		if (newUser.getUsername() != null) {
			if (Security.containsSQL(newUser.getUsername())) {
				return false;
			}
			if (Security.containsXSS(newUser.getUsername())) {
				return false;
			}
		}
		
		
		if (newUser.getPassword() != null) {
			if (Security.containsSQL(newUser.getPassword())) {
				return false;
			}
			if (Security.containsXSS(newUser.getPassword())) {
				return false;
			}
		}
		
    	try {
    		return this.userDAO.register(newUser);	
    	} catch (Exception e) {
    		return false;
    	}
    }

	public boolean isUserAdmin(String username) {
		if (username != null) {
			if (Security.containsSQL(username)) {
				return false;
			}
			if (Security.containsXSS(username)) {
				return false;
			}
		}
		try {
			return this.userDAO.isUserAdmin(username);
		} catch (Exception e) {
			return false;
		}
	} 
    
    public String returnBillingAddress(String username) throws Exception {
        return this.userDAO.getBillingAddress(username);
    }
    public String returnShippingAddress(String username) throws Exception {
        return this.userDAO.getShippingAddress(username);
    }
}
