package utilities;

import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;

import dao.ItemReviewDAO;
import bean.ItemReviewBean;


/**
 * The item review model class
 * @author antoji
 *
 */
public class ItemReviewModel {
	// Private attributes
	private ItemReviewDAO id;
	
	/**
	 * Constructor
	 */
	public ItemReviewModel() {
		try {
			this.id = new ItemReviewDAO();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @return data from the ITEMREVIEWS table where ITEMID is equal to given ITEMID
	 * @throws SQLException, NamingException
	 */
	public List<ItemReviewBean> retriveReviews(String ITEMID) throws SQLException, NamingException {
		// validate inputs namePrefix, creditTaken

		if (ITEMID == null || ITEMID.equals("")) {
			throw new NamingException("ITEM ID should not be empty\n");
		}

		
		List<ItemReviewBean> rs = this.id.getReviews(ITEMID);
		return rs;
	}
	
	/**
	 * @return number of inserted rows from ITEMREVIEWS table
	 * @throws NamingException
	 * @throws SQLException
	 */
	public int insertReview(String ID, String USERID, String ITEMID, String REVIEW, String RATING, String REVIEWDATE, boolean isRating) throws SQLException, NamingException {
		// validate inputs ID, USERID, ITEMID, REVIEW, RATING, and REVIEWDATE
		
		if (ID == null || ID.equals("")) {
			throw new NamingException("ID can not be empty\n");
		}
		if (USERID == null || USERID.equals("")) {
			throw new NamingException("USERID can not be empty\n");
		}
		if (ITEMID == null || ITEMID.equals("")) {
			throw new NamingException("ITEMID can not be empty\n");
		}
		if (REVIEW == null) {
			throw new NamingException("REVIEW can not be empty\n");
		}
		
		if (RATING == null) {
			throw new NamingException("RATING can not be empty\n");
		}
		int rating = Integer.valueOf(RATING);
		if (rating < 0) {
			throw new NamingException("RATING sould be number > 0\n");
		}
		
		if (REVIEWDATE == null) {
			throw new NamingException("REVIEWDATE can not be empty\n");
		}

		return this.id.addReview(ID, USERID, ITEMID, REVIEW, rating, REVIEWDATE, isRating);
	}
	
}