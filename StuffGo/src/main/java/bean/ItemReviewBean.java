package bean;

/** 
 * Information about ITEMREVIEWS table from DataBase
 * @author antoji
 *
 */
public class ItemReviewBean {
	// Private attributes
	private String id;
	private String userID;
	private String itemID;
	private String review;
	private int rating;
	private String reviewDate;
	
	/**
	 * Constructor
	 */
	public ItemReviewBean (String ID, String USERID, String ITEMID, String REVIEW, int RATING, String REVIEWDATE) {
		this.id = ID;
		this.userID = USERID;
		this.itemID = ITEMID;
		this.review = REVIEW;
		this.rating = RATING;
		this.reviewDate = REVIEWDATE;
	}
	
	/**
	 * @return table ID in the form 'userID-itemID'
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Set table ID in the following form 'userID-itemID'
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * @return User ID
	 */
	public String getUserID() {
		return userID;
	}
	
	/**
	 * Sets user ID
	 */
	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	/**
	 * @return Item ID
	 */
	public String getItemID() {
		return itemID;
	}
	
	/**
	 * Sets the item ID
	 */
	public void setItemID(String itemID) {
		this.itemID = itemID;
	}
	
	/**
	 * @return Item review
	 */
	public String getReview() {
		return review;
	}
	
	/**
	 * Set review
	 */
	public void setReview(String review) {
		this.review = review;
	}
	
	/**
	 * @return Review date
	 */
	public String getReviewDate() {
		return reviewDate;
	}
	
	/**
	 * Set review date
	 */
	public void setReviewDate(String reviewDate) {
		this.reviewDate = reviewDate;
	}
	
	/**
	 * @return Item rating
	 */
	public int getRating() {
		return rating;
	}
	
	/**
	 * Set rating
	 */
	public void setRating(int rating) {
		this.rating = rating;
	}

}
