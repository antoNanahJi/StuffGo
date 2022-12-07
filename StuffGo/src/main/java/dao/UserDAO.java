package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import bean.ItemBean;
import bean.ItemReviewBean;
import bean.StudentBean;
import bean.UserBean;
//DAO for retrieving student database
public class UserDAO {

	private DataSource ds;

	public UserDAO() throws ClassNotFoundException{
		try {
			ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/EECS");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public boolean login(String username, String passwordHash) throws SQLException {
		Connection connection = this.ds.getConnection();
		String query = "SELECT * FROM USERS WHERE USERNAME = ? AND PASSWORD = ?";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, username);
		statement.setString(2, passwordHash);
		ResultSet results = statement.executeQuery();
		boolean success = false;
		if (results.next()) {
			success = true;
		}
		results.close();
		statement.close();
		connection.close();
		return success;
	}

	public boolean register(UserBean newUser) throws SQLException {
		Connection connection = this.ds.getConnection();
		String query = "INSERT INTO USERS (USERNAME, PASSWORD, BILLING_ADDRESS, SHIPPING_ADDRESS, ISADMIN, NAME) values (?, ?, ?, ?, ?, ?)";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, newUser.getUsername());
		statement.setString(2, newUser.getPassword());
		statement.setString(3, newUser.getBilling());
		statement.setString(4, newUser.getShipping());
		statement.setInt(5, newUser.getIsAdmin());
		statement.setString(6, newUser.getName());
		int resCount = statement.executeUpdate();
		if (resCount >= 0) {
			return true;
		}
		return false;
	}

	public boolean isUserAdmin(String username) throws SQLException {
		Connection connection = this.ds.getConnection();
		String query = "SELECT * FROM USERS WHERE USERNAME = ?";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, username);
		ResultSet results = statement.executeQuery();
		int isAdminField = 0;
		if (results.next()) {
			isAdminField = results.getInt("ISADMIN");
		}
		results.close();
		statement.close();
		connection.close();
		return isAdminField == 0 ? false : true;
	}
	
	public String getBillingAddress(String username) throws SQLException {
		Connection connection = this.ds.getConnection();
		String query = "SELECT BILLING_ADDRESS FROM USERS WHERE USERNAME = ?";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, username);
		ResultSet results  = statement.executeQuery();
		String result = "";
		while(results.next()){
			result = results.getString("BILLING_ADDRESS");
			}
		return result;
	}
	public String getShippingAddress(String username) throws SQLException {
		Connection connection = this.ds.getConnection();
		String query = "SELECT SHIPPING_ADDRESS FROM USERS WHERE USERNAME = ?";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, username);
		ResultSet results  = statement.executeQuery();
		String result = "";
		while(results.next()){
			result = results.getString("SHIPPING_ADDRESS");
			}
		return result;
	}
}
