package bean;

public class ItemReviewBean {
	private String id;
	private String userID;
	private String itemID;
	private String review;
	private int rating;
	private String reviewDate;
	
	public ItemReviewBean (String ID, String USERID, String ITEMID, String REVIEW, int RATING, String REVIEWDATE) {
		this.id = ID;
		this.userID = USERID;
		this.itemID = ITEMID;
		this.review = REVIEW;
		this.rating = RATING;
		this.reviewDate = REVIEWDATE;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getItemID() {
		return itemID;
	}
	public void setItemID(String itemID) {
		this.itemID = itemID;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	public String getReviewDate() {
		return reviewDate;
	}
	public void setReviewDate(String reviewDate) {
		this.reviewDate = reviewDate;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}

}
