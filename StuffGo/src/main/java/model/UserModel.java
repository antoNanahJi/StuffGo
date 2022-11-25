package model;

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
	//return the Map of CSE ID and the student information
	public boolean loginUser(String username, String passwordHash) throws Exception{
		return this.userDAO.login(username, passwordHash);
	}

    public boolean registerUser(UserBean newUser) throws Exception{
        return this.userDAO.register(newUser);
    }
}
