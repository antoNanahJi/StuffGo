package dao;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

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
		String query = "SELECT * FROM Users WHERE username = ? AND password = ?";
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
		String query = "INSERT INTO Users (username, password, billing, shipping) values (?, ?, ?, ?)";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, newUser.getUsername());
		statement.setString(2, newUser.getPassword());
		statement.setString(3, newUser.getBilling());
		statement.setString(4, newUser.getShipping());
		int resCount = statement.executeUpdate();
		if (resCount >= 0) {
			return true;
		}
		return false;
	}
}
