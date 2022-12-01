package ctrl;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Map;

import dao.StudentDAO;
import model.SisModel;
import utilities.View;
import model.MainModel;


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
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		MainModel model = (MainModel) this.getServletContext().getAttribute("MainModel");
		HttpSession session = request.getSession();
		
		String username = request.getParameter("username");
		String passwordHash = request.getParameter("passwordHash");
		String type = request.getParameter("type");
		System.out.println("username: " + username);
		System.out.println("passwordHash: " + passwordHash);
		
		Writer resOut = response.getWriter();
		response.setContentType("application/json");
		try {
			if (type.equals("login")) {
				 boolean isLoginSuccess = model.getUserModel().loginUser(username, passwordHash);
				 if (isLoginSuccess) {
					session.setAttribute("username", username);
					resOut.append("{\"username\": \"" + username + "\"}");
					resOut.flush();
				 } else {
					resOut.append("{\"username\": null}");
					resOut.flush();
				 }
			} else if (type.equals("register")) {
				// TODO: Handle showing error for duplicate username
				String billing = request.getParameter("billing");
				String shipping = request.getParameter("shipping");
				UserBean newUser = new UserBean(username, passwordHash, shipping, billing);
				boolean isRegisterationSuccess = model.getUserModel().registerUser(newUser);
				System.out.println("isRegistered: " + isRegisterationSuccess);
				if (isRegisterationSuccess) {
					session.setAttribute("username", username);
					resOut.append("{\"username\": \"" + username + "\"}");
					resOut.flush();
				} else {
					resOut.append("{\"username\": null}");
					resOut.flush();
				}
			} else {
				// would mean logout
				session.setAttribute("username", null);
				resOut.append("{\"username\": null}");
				resOut.flush();
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
