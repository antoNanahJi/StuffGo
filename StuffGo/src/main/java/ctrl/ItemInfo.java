package ctrl;

import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import utilities.eventTypes;

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

		
		if (request.getSession().getAttribute("clientIP") == null) {
			request.getSession().setAttribute("clientIP", request.getRemoteAddr());
		}
		
		if (request.getSession().getAttribute("eventDate") == null) {
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			request.getSession().setAttribute("eventDate",formatter.format(date));
		}
		
		String  clientIP = (String) request.getSession().getAttribute("clientIP");
		String	date = (String) request.getSession().getAttribute("eventDate");
	
		try {
			MainModel model = (MainModel) context.getAttribute("MainModel");
				
				if(request.getParameter("out") != null && request.getParameter("out").equals("addReview")) {
					
					if (request.getSession().getAttribute("username") == null) {
						resOut.write("{\"login\":\"" + false + "\"}");
						resOut.flush();
						return;
					}
						
						String userID = (String) request.getSession().getAttribute("username");
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
							model.getItemReviewModel().insertReview(userID + '-' + ID, userID, ID, review, "0", reviewDate, false);
							model.getWebsiteUsageModel().insertRecord(clientIP, date, itemID, eventTypes.SUBMIT_REVIEW);
							resOut.write("{\"login\":\"" + true + "\", " + "\"user\":\"" + userID + "\"}");
						} catch(Exception e) {			
							resOut.append(e.getMessage());
						}
						
						
				
				}
				if(request.getParameter("out") != null && request.getParameter("out").equals("addItem")) {
					String itemID = "";
					int quantity = 0;
					if(request.getParameter("ID") != null) {
						itemID = request.getParameter("ID");
						
					}
					if(request.getParameter("Quantity") != null) {
						quantity = Integer.valueOf(request.getParameter("Quantity"));
					}
					
					if (request.getSession().getAttribute("cartItems") == null) {
				    	request.getSession().setAttribute("cartItems", "");
					} else {
						String cartItems = (String) request.getSession().getAttribute("cartItems");
						Map<String, Integer> cartItemsMap = toMap(cartItems);

						if (cartItemsMap.containsKey(itemID)) {
							quantity += cartItemsMap.get(itemID);
						}
						cartItemsMap.put(itemID, quantity);
						cartItems = cartItemsMap.toString();
						request.getSession().setAttribute("cartItems", cartItems.substring(1, cartItems.length() - 1).replaceAll("\\s", ""));
						
					}
					model.getWebsiteUsageModel().insertRecord(clientIP, date, itemID, eventTypes.ADD_CART);
					
				}
				if(request.getParameter("out") != null && request.getParameter("out").equals("addRating")) {
					
					if (request.getSession().getAttribute("username") == null) {
						resOut.write("{\"login\":\"" + false + "\"}");
						resOut.flush();
						return;
					}
					String userID = (String) request.getSession().getAttribute("username");
					String ID = "";
					String rating = "";
					
					if(request.getParameter("ID") != null) {
						ID = request.getParameter("ID");
					}
					if(request.getParameter("RATING") != null) {
						rating = request.getParameter("RATING");
					}
					
					try {
						model.getItemReviewModel().insertReview(userID + '-' + ID, userID, ID, "", rating, "", true);
						model.getWebsiteUsageModel().insertRecord(clientIP, date, itemID, eventTypes.SUBMIT_RATING);
						resOut.write("{\"login\":\"" + true + "\", " + "\"user\":\"" + userID + "\"}");
					} catch(Exception e) {			
						resOut.append(e.getMessage());
					}
				}

				if(request.getParameter("out") != null && request.getParameter("out").equals("getReviewsStars")) {
					StringBuilder jsonData = new StringBuilder();
					
					
					try {
						List<ItemReviewBean> reviews = model.getItemReviewModel().retriveReviews(this.itemID);
						if (reviews.size() > 0) {
							response.setContentType("application/json");
							int starCount = 0;
							
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
								starCount += iBean.getRating();
							}
							jsonData.replace(jsonData.length() - 2, jsonData.length(), "],");
							jsonData.append(" \"starCount\":\"" + (starCount/reviews.size()) + "\"}");
							
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
				request.setAttribute("quantity", itemInfo.getQuantity());
				request.setAttribute("itemID", itemInfo.getID());
			
				model.getWebsiteUsageModel().insertRecord(clientIP, date, itemID, eventTypes.VIEW);
				
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
	
	private Map<String, Integer> toMap(String str) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		if (str.length() == 0) {
			return map;
		}
		String[] pairs = str.split(",");
		
		int size = pairs.length;
		for (int i=0;i<size;i++) {
		    String pair = pairs[i];
		    String[] keyValue = pair.split("=");
		    map.put(keyValue[0], Integer.valueOf(keyValue[1]));
		}
		return map;
	}

}
