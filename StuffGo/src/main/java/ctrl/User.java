package ctrl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import dao.StudentDAO;
import model.SisModel;
import utilities.View;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


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
			SisModel sis = new SisModel();
			this.getServletContext().setAttribute("SIS", sis);
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
		// TODO Auto-generated method stub
		SisModel sis = (SisModel) this.getServletContext().getAttribute("SIS");
		String namePrefix, credit_taken;
		String responseText = "{}";
		namePrefix = request.getParameter("namePrefix");
		credit_taken = request.getParameter("credit_taken");
		request.setAttribute("namePrefix", namePrefix);
		request.setAttribute("credit_taken", credit_taken);

		if (request.getParameter("reportAjax") != null) {
			try {
				View view = new View();
				responseText = view.studentAsHTML(sis, namePrefix, credit_taken);
			} catch (Exception e) {
				e.printStackTrace();
			}
			response.setContentType("application/json");
			PrintWriter out = response.getWriter();
			out.append(responseText);
			out.flush();
		}
		if (request.getParameter("reportAjax") == null) {

			try {
				StudentDAO sd = new StudentDAO();
				sd.readAndPrintTableToConsole();
				request.getRequestDispatcher("HTMLJS.html").forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}
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
