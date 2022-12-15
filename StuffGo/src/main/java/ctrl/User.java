package ctrl;

import java.io.IOException;
import java.io.Writer;

import model.MainModel;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.UserBean;


/**
 * Servlet implementation class User
 */
@WebServlet("/User")
public class User extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void init() throws ServletException {
		super.init();
		try {

	    	// SisModel instance save in context attribute
    		MainModel model = MainModel.getInstance();
	    	this.getServletContext().setAttribute("MainModel", model);

		} catch (ClassNotFoundException e) {
			throw new ServletException("Class Not Found!" + e);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	// Handles every request related to Users
	// Expects "type" parameter in the query to understand which operation to execute
	// Possible "type"s => login, register, logout, isAdmin 
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		MainModel model = (MainModel) this.getServletContext().getAttribute("MainModel");
		HttpSession session = request.getSession();
		

		String type = request.getParameter("type");
		
		Writer resOut = response.getWriter();
		response.setContentType("application/json");
		try {
			if (type.equals("login")) {
				// Get username and password parameters from request
				String username = request.getParameter("username");
				String passwordHash = request.getParameter("passwordHash");
				// Execute login
				 boolean isLoginSuccess = model.getUserModel().loginUser(username, passwordHash);
				 if (isLoginSuccess) {
					 // Set username attribute of session to the logged in user's username
					session.setAttribute("username", username);
					 // Send a JSON response with the logged in user's username
					resOut.append("{\"username\": \"" + username + "\"");

					// Redirect user to admin page if login was from Analytics page
					if (request.getSession().getAttribute("AdminPage") != null) {
						resOut.append(", \"AdminPage\": \"" + request.getSession().getAttribute("AdminPage") +  "\"");
						request.getSession().setAttribute("AdminPage", false);
					}
					
					// Check if the user is admin
					boolean isUserAdmin = model.getUserModel().isUserAdmin(username);
					// Set "isAdmin" attribute of session to be true or false accordingly 
					session.setAttribute("isAdmin", isUserAdmin);
					
					resOut.append("}");
					resOut.flush();
				 } else {
					 // If login was not successfull, return JSON with username being null
					resOut.append("{\"username\": null}");
					resOut.flush();
				 }
			} else if (type.equals("register")) {
				// Get user info from request
				String username = request.getParameter("username");
				String passwordHash = request.getParameter("passwordHash");
				String billing = request.getParameter("billing");
				String shipping = request.getParameter("shipping");
				String name = request.getParameter("name");
				UserBean newUser = new UserBean(username, passwordHash, shipping, billing, 0, name);
				// Execute register
				boolean isRegisterationSuccess = model.getUserModel().registerUser(newUser);
				if (isRegisterationSuccess) {
					// Just like login "Set username attribute of session to the logged in user's username"
					session.setAttribute("username", username);
					// Just like login "Send a JSON response with the logged in user's username"
					resOut.append("{\"username\": \"" + username + "\"}");
					resOut.flush();
				} else {
					// Just like login "If registeration was not successfull, return JSON with username being null"
					resOut.append("{\"username\": null}");
					resOut.flush();
				}
			} else if (type.equals("logout")) {
				// Execute logout by setting username attribute of session to be null
				session.setAttribute("username", null);
				// Return JSON with username value being null
				resOut.append("{\"username\": null}");
				resOut.flush();
			} else if (type.equals("isAdmin")) {
				// To not expose admin accounts, we have decided to only allow to check if a user is admin after they are logged in
				// So, the username in the session will be used to check if the user is admin
				String usernameInSession = (String) session.getAttribute("username");
				if (usernameInSession == null) return;
				// Execute isAdmin check
				boolean isUserAdmin = model.getUserModel().isUserAdmin(usernameInSession);
				// Set "isAdmin" attribute of session to be true or false accordingly 
				session.setAttribute("isAdmin", isUserAdmin);
			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
