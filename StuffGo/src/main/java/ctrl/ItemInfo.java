package ctrl;

import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ItemBean;
import bean.ItemReviewBean;
import bean.StudentBean;
import model.MainModel;
import model.SisModel;

/**
 * Servlet implementation class ItemInfo
 */
@WebServlet("/ItemInfo")
public class ItemInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private String itemID = "";
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ItemInfo() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    /**
     * @see HttpServlet#init(ServletConfig)
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
    	super.init(config);
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		ServletContext context = this.getServletContext();
		Writer resOut = response.getWriter();
		
		
		
		try {
			
			MainModel model = (MainModel) context.getAttribute("MainModel");
				
				if(request.getParameter("out") != null && request.getParameter("out").equals("addReview")) {
					
					if (request.getSession().getAttribute("username") == null) {
						System.out.println("Inside the null thing");
						String target = "login.html";
						request.getRequestDispatcher(target).forward(request, response);
						return;
					}
						
						String userID = "123"; // get from the session
						String review = "";
						String reviewDate = "";
						String ID = "";
						
						if(request.getParameter("ID") != null) {
							ID = request.getParameter("ID");
						}
								
						// Get credit taken value
						if(request.getParameter("REVIEW") != null) {
							review =  request.getParameter("REVIEW");
						}
						
						// Get credit taken value
						if(request.getParameter("REVIEWDATE") != null) {
							reviewDate =  request.getParameter("REVIEWDATE");
						} 
						try {
							int k = model.getItemReviewModel().insertReview(userID + '-' + ID, userID, ID, review, reviewDate);
							System.out.print("K: " + k);
						} catch(Exception e) {			
							resOut.append(e.getMessage());
						}
						
						resOut.write("{\"user\":\"" + userID + "\"}");
						resOut.flush();
					//}
				}
				if(request.getParameter("out") != null && request.getParameter("out").equals("getReviews")) {
					StringBuilder jsonData = new StringBuilder();
					System.out.print("itemID: " + this.itemID);
					try {
						List<ItemReviewBean> reviews = model.getItemReviewModel().retriveReviews(this.itemID);
						if (reviews.size() > 0) {
							response.setContentType("application/json");
							
							// Create the JSON data
							jsonData.append("{ \"reviews\" : [");
							
							for (ItemReviewBean iBean : reviews) {
								jsonData.append("{\"user\":");
								jsonData.append("\"" + iBean.getUserID() + "\"");
								jsonData.append(",\"review\":");
								jsonData.append("\"" + iBean.getReview() + "\"");
								jsonData.append(",\"date\":");
								jsonData.append("\"" + iBean.getReviewDate() + "\"");
								jsonData.append("}, ");
							}
							jsonData.replace(jsonData.length() - 2, jsonData.length(), "]}");
							
							System.out.print(jsonData.toString());
						}
					} catch(Exception e) {			
						jsonData.append(e.getMessage());
					}
					
					if (jsonData.length() > 0) {
						resOut.write(jsonData.toString());
						resOut.flush();
					}
				}
			if (request.getParameter("out") == null) {
				if(request.getParameter("ID") != null) {
					this.itemID = request.getParameter("ID");
					
				}
				ItemBean itemInfo = model.getStoreModel().retreiveItem(this.itemID);
				
				request.setAttribute("image", itemInfo.getImage());
				request.setAttribute("name", itemInfo.getName());
				request.setAttribute("description", itemInfo.getDescription());
				request.setAttribute("price", itemInfo.getPrice());
				request.setAttribute("itemID", itemInfo.getID());
				
				String target = "/ItemView.jsp";
				request.getRequestDispatcher(target).forward(request, response);
			}

			
		} catch (Exception e) {
			System.out.println("error");
			e.printStackTrace();
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
