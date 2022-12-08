package ctrl;

import java.io.IOException;
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
import bean.StudentBean;
import bean.WebsiteUsageBean;
import model.MainModel;
import utilities.eventTypes;

/**
 * Servlet implementation class Analytics
 */
@WebServlet("/Admin")
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		StringBuilder jsonData = new StringBuilder();
		ServletContext context = this.getServletContext();
		
		
		try {
			// retrieve Students data
			MainModel model = (MainModel) context.getAttribute("MainModel");
			
			if(request.getParameter("out") != null && request.getParameter("out").equals("WebsiteUsage"))
			{
				
				
				List<WebsiteUsageBean> records = model.getWebsiteUsageModel().retriveRecords();
				System.out.println("Ajax call");//this is for testing at server side...
				
				if (records.size() > 0) {
					response.setContentType("application/json");
					
					// Create the JSON data
					jsonData.append("{ \"arr\" : [");
					
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
					jsonData.replace(jsonData.length() - 2, jsonData.length(), "]}");
				}
			
			}
		} catch(SQLException | NamingException e) {			
			jsonData.append(e.getMessage());
		}
		
		if (request.getParameter("out") == null) {
			//if (request.getSession().getAttribute("username") == null) {
			//	resOut.write("{\"login\":\"" + false + "\"}");
			//	resOut.flush();
			//	return;
			//}
			
			String target = "/Analytics.jsp";
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
