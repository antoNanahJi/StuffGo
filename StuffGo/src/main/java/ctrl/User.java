package ctrl;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Map;

import dao.StudentDAO;
import model.SisModel;
import model.UserModel;
import utilities.View;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.UserBean;


/**
 * Servlet implementation class SIS
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
	
	// Initialize helper class called SIS and saved it on Servlet Context
	@Override
	public void init() throws ServletException {
		super.init();
		try {
			UserModel userModel = new UserModel();
			this.getServletContext().setAttribute("USER_MODEL", userModel);
		} catch (ClassNotFoundException e) {
			throw new ServletException("Class Not Found" + e);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserModel userModel = (UserModel) this.getServletContext().getAttribute("USER_MODEL");
		HttpSession session = request.getSession();
		
		String username = request.getParameter("username");
		String passwordHash = request.getParameter("passwordHash");
		String login = request.getParameter("login");
		boolean receivedLogin = Boolean.parseBoolean(login);
		System.out.println("username: " + username);
		System.out.println("passwordHash: " + passwordHash);
		
		Writer resOut = response.getWriter();
		response.setContentType("application/json");
		try {
			if (receivedLogin) {
				 boolean isLoggedIn = userModel.loginUser(username, passwordHash);
				 if (isLoggedIn) {
					session.setAttribute("isLoggedIn", true);
					resOut.append("{\"isLoggedIn\": true}");
					resOut.flush();
				 } else {
					resOut.append("{\"isLoggedIn\": false}");
					resOut.flush();
				 }
				// TODO: Create UserDAO and use it to check if the user exists
				// TODO: make sure to update session so that user is signed in

			} else {
				// Means it is a register
				String billing = request.getParameter("billing");
				String shipping = request.getParameter("shipping");
				UserBean newUser = new UserBean(username, passwordHash, shipping, billing);
				boolean isRegistered = userModel.registerUser(newUser);
				System.out.println("isRegistered: " + isRegistered);
				if (isRegistered) {
					session.setAttribute("isLoggedIn", true);
					resOut.append("{\"isRegistered\": true}");
					resOut.flush();
				} else {
					resOut.append("{\"isRegistered\": false}");
					resOut.flush();
				}
			}
			// TODO: save that user is logged in to the session
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
