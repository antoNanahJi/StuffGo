package ctrl;

import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
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
import model.MainModel;
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
		
		// Set the clientIP value
		if (request.getSession().getAttribute("clientIP") == null) {
			request.getSession().setAttribute("clientIP", request.getRemoteAddr());
		}
		
		// Set the eventDate value
		if (request.getSession().getAttribute("eventDate") == null) {
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			request.getSession().setAttribute("eventDate",formatter.format(date));
		}
		
		String  clientIP = (String) request.getSession().getAttribute("clientIP");
		String	date = (String) request.getSession().getAttribute("eventDate");
	
		try {
			// Get the model
			MainModel model = (MainModel) context.getAttribute("MainModel");
				
			// Insert a new user review into database
			if(request.getParameter("out") != null && request.getParameter("out").equals("addReview")) {
				
				// To make sure the user is logged in
				if (request.getSession().getAttribute("username") == null) {
					resOut.write("{\"login\":\"" + false + "\"}");
					resOut.flush();
					return;
				}
					
				String userID = (String) request.getSession().getAttribute("username");
				String review = "";
				String reviewDate = "";
				String ID = "";

				// Get the unique ID
				if(request.getParameter("ID") != null) {
					ID = request.getParameter("ID");
				}
								
				// Get REVIEW value
				if(request.getParameter("REVIEW") != null) {
					review =  request.getParameter("REVIEW");
				}
						
				// Get REVIEWDATE value
				if(request.getParameter("REVIEWDATE") != null) {
					reviewDate =  request.getParameter("REVIEWDATE");
				} 
				
				try {
					List<String> purchasedHistory = model.getItemPurchasedModel().getPurchasedHistory(userID);
					
					// To make sure the user can add review only if they bought the item
					if (!purchasedHistory.contains(ID)) {
						resOut.write("{\"login\":\"" + true + "\", " + "\"user\":\"" + userID + "\", " + "\"reviewAdded\":\"" + false + "\"}");
					} else {								
						
						// Insert a new SUBMIT_REVIEW event
						model.getWebsiteUsageModel().insertRecord(clientIP, date, itemID, eventTypes.SUBMIT_REVIEW);
						
						model.getItemReviewModel().insertReview(userID + '-' + ID, userID, ID, review, "0", reviewDate, false);
						resOut.write("{\"login\":\"" + true + "\", " + "\"user\":\"" + userID + "\", " + "\"reviewAdded\":\"" + true + "\"}");
								
					}
				} catch(Exception e) {			
					resOut.append(e.getMessage());
				}
			}
			
			// Add the item into cart
			if(request.getParameter("out") != null && request.getParameter("out").equals("addItem")) {
				String itemID = "";
				int quantity = 0;
				
				// Get the unique ID
				if(request.getParameter("ID") != null) {
						itemID = request.getParameter("ID");
				}
				
				// Get the Quantity
				if(request.getParameter("Quantity") != null) {
					quantity = Integer.valueOf(request.getParameter("Quantity"));
				}
					
				// Update the cartItems map
				if (request.getSession().getAttribute("cartItems") == null) {
				   	request.getSession().setAttribute("cartItems", itemID + "=" + quantity);
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
				
				// Insert a new ADD_CART event
				model.getWebsiteUsageModel().insertRecord(clientIP, date, itemID, eventTypes.ADD_CART);
				resOut.write("Item added to cart!!");
			}
			
			// Insert a new rating
			if(request.getParameter("out") != null && request.getParameter("out").equals("addRating")) {
					
				// To make sure the user is logged in
				if (request.getSession().getAttribute("username") == null) {
					resOut.write("{\"login\":\"" + false + "\"}");
					resOut.flush();
					return;
				}
				
				String userID = (String) request.getSession().getAttribute("username");
				String ID = "";
				String rating = "";
					
				// Get unique ID
				if(request.getParameter("ID") != null) {
					ID = request.getParameter("ID");
				}
				
				// Get Rating value
				if(request.getParameter("RATING") != null) {
					rating = request.getParameter("RATING");
				}
					
				try {
					List<String> purchasedHistory = model.getItemPurchasedModel().getPurchasedHistory(userID);
					
					// To make sure the user can add rating only if they bought the item
					if (!purchasedHistory.contains(ID)) {
						resOut.write("{\"login\":\"" + true + "\", " + "\"user\":\"" + userID + "\", " + "\"ratingAdded\":\"" + false + "\"}");
					} else {
						// Insert a new SUBMIT_RATING event
						model.getWebsiteUsageModel().insertRecord(clientIP, date, itemID, eventTypes.SUBMIT_RATING);
						
						model.getItemReviewModel().insertReview(userID + '-' + ID, userID, ID, "", rating, "", true);
						resOut.write("{\"login\":\"" + true + "\", " + "\"user\":\"" + userID + "\", " + "\"ratingAdded\":\"" + true + "\"}");
					}
				} catch(Exception e) {			
					resOut.append(e.getMessage());
				}
			}

			// Get the reviews and the average rating for this item
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
			
			// Get this item info from database and send it to front end
			if (request.getParameter("out") == null) {
			
				// Get the item ID
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
				
				//Redirect to ItemView.jsp
				String target = "/ItemView.jsp";
				request.getRequestDispatcher(target).forward(request, response);
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			resOut.write(e.getMessage());
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
	
	/**
	 * Converts the given string into map
	 */
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
