package utilities;

import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import bean.StudentBean;
import bean.UserBean;
import dao.EnrollmentDAO;
import dao.StudentDAO;
import dao.UserDAO;
import bean.EnrollmentBean;

public class UserModel {
	private UserDAO userDAO;
	
	public UserModel() throws ClassNotFoundException{
		userDAO = new UserDAO();
	}

	public boolean loginUser(String username, String passwordHash) {
    	try {
    		return this.userDAO.login(username, passwordHash);
    	} catch (Exception e) {
    		return false;
    	}
	}

    public boolean registerUser(UserBean newUser) {
    	try {
    		return this.userDAO.register(newUser);	
    	} catch (Exception e) {
    		return false;
    	}
    }

	public boolean isUserAdmin(String username) {
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
