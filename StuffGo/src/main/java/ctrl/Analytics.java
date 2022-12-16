package ctrl;

import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ItemBean;
import bean.WebsiteUsageBean;
import model.MainModel;
import utilities.eventTypes;

/**
 * Servlet implementation class Analytics
 */
@WebServlet("/Analytics")
public class Analytics extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Analytics() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    /**
     * @see HttpServlet#init(ServletConfig)
     */
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		ServletContext context = this.getServletContext();
		Writer resOut = response.getWriter();
		boolean isAdminCurl = false;
		
		// Get the item ID
		if(request.getParameter("adminCurl") != null) {
			isAdminCurl = Boolean.valueOf(request.getParameter("adminCurl"));
			
		}
		
		try {
			// Get the model
			MainModel model = (MainModel) context.getAttribute("MainModel");
			
			// Get the website usage report
			if(request.getParameter("out") != null && request.getParameter("out").equals("WebsiteUsage"))
			{
				if (!isAdminCurl) {
					// To make sure the user is logged in
					if (request.getSession().getAttribute("username") == null) {
						request.getSession().setAttribute("AdminPage", true);
						resOut.write("{\"login\":\"" + false + "\"}");
						resOut.flush();
						return;
					}
				}
				StringBuilder jsonData = new StringBuilder();
				
				List<WebsiteUsageBean> records = model.getWebsiteUsageModel().retriveRecords();
				System.out.println("Ajax call");//this is for testing at server side...
				
				if (records.size() > 0) {
					response.setContentType("application/json");
					
					// Create the JSON data
					jsonData.append("{ \"records\" : [");
					
					for (WebsiteUsageBean wBean : records) {
						jsonData.append("{\"ipAddress\":");
						jsonData.append("\"" + wBean.getIpAddress() + "\"");
						jsonData.append(",\"date\":");
						jsonData.append("\"" + wBean.getDate() + "\"");
						jsonData.append(",\"itemID\":");
						jsonData.append("\"" + wBean.getItemID() + "\"");
						jsonData.append(",\"event\":");
						jsonData.append("\"" + wBean.getEvent() + "\"");
						jsonData.append("}, ");
					}
					jsonData.replace(jsonData.length() - 2, jsonData.length(), "], ");
					jsonData.append("\"login\":\"" + true + "\"}");
				}
				if (jsonData.length() > 0) {
					resOut.write(jsonData.toString());
					resOut.flush();
				}
			}
			
			// Get the monthly item sold report
			if(request.getParameter("out") != null && request.getParameter("out").equals("MonthlyItemReport"))
			{
				if (!isAdminCurl) {
					// To make sure the user is logged in
					if (request.getSession().getAttribute("username") == null) {
						request.getSession().setAttribute("AdminPage", true);
						resOut.write("{\"login\":\"" + false + "\"}");
						resOut.flush();
						return;
					}
				}
				
				StringBuilder jsonData = new StringBuilder();
				
				Map<String,int[]> records = model.getItemPurchasedModel().retriveRecords();
				System.out.println("Ajax call");//this is for testing at server side...
				
				if (records.size() > 0) {
					response.setContentType("application/json");
					
					// Create the JSON data
					jsonData.append("{ \"records\" : [");
					
					for (String item : records.keySet()) {
						jsonData.append("{\"itemID\":");
						jsonData.append("\"" + item + "\"");
						jsonData.append(", \"quantity\": [");
						for(int q: records.get(item)) {
							jsonData.append("\"" + q + "\", ");
						}
						jsonData.replace(jsonData.length() - 2, jsonData.length(), "]}, ");
					}
					jsonData.replace(jsonData.length() - 2, jsonData.length(), "], ");
					jsonData.append("\"login\":\"" + true + "\"}");
				}
				if (jsonData.length() > 0) {
					resOut.write(jsonData.toString());
					resOut.flush();
				}
			}
		} catch(SQLException | NamingException e) {			
			System.out.println(e.getMessage());
			resOut.write(e.getMessage());
			e.printStackTrace();
		}
		
		if (request.getParameter("out") == null) {
			
			String target = "/Analytics.jsp";
			
			if (!isAdminCurl) {
				// To make sure the  user is logged in
				if (request.getSession().getAttribute("username") == null) {
					request.getSession().setAttribute("AdminPage", true);
					target = "/login.jsp";
				}
				
				// To make sure the logged in user is Admin
				boolean isAdmin = false;
				if (request.getSession().getAttribute("isAdmin") != null) {
					isAdmin = (boolean) request.getSession().getAttribute("isAdmin");
				}
				if (!isAdmin) {
					request.setAttribute("AdminDisplay", "none");
					request.setAttribute("AdminMessage", "Please login with Admin account.");
				} else {
					request.setAttribute("AdminDisplay", "block");
					request.setAttribute("AdminMessage", "");
				}
			}
			
			// Redirect to target page
			request.getRequestDispatcher(target).forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
