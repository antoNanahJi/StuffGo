package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import bean.ItemReviewBean;

/**
 * This class is responsible to retrieve data from ITEMREVIEWS table
 * @author antoji
 *
 */
public class ItemReviewDAO {
	// Private attributes
	private DataSource ds;
	
	/**
	 * Constructor
	 * @throws ClassNotFoundException
	 */
	public ItemReviewDAO() throws ClassNotFoundException{
		try {
			ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/EECS");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @return data from the ITEMREVIEWS table where ITEMID is equal to given ITEMID
	 * @throws SQLException, NamingException
	 */
	public List<ItemReviewBean> getReviews(String ITEMID)throws SQLException, NamingException{
		String preparedStatement = "select * from ITEMREVIEWS where ITEMID=?";
		
		Connection con = this.ds.getConnection();
		
		PreparedStatement stmt = con.prepareStatement(preparedStatement);
		
		stmt.setString(1, ITEMID);
		ResultSet r = stmt.executeQuery();
		List<ItemReviewBean> rs = new ArrayList<ItemReviewBean>();

		while (r.next()) {
			String id = r.getString("ID");
			String userID = r.getString("USERID");
			String itemID = r.getString("ITEMID");
			String review = r.getString("REVIEW");
			int rating = r.getInt("RATING");
			String reviewDate = r.getString("REVIEWDATE");

			rs.add(new ItemReviewBean(id, userID, itemID, review, rating, reviewDate));
		}
		
		r.close();
		stmt.close();
		con.close();
		
		return rs;
	}
	
	/**
	 * Adds record into the ITEMREVIEWS table if it does not exist
	 * @return number of inserted rows into ITEMREVIEWS table
	 * @throws NamingException
	 * @throws SQLException
	 */
	public int addReview(String ID, String USERID, String ITEMID, String REVIEW, int RATING, String REVIEWDATE, boolean isRating) throws SQLException, NamingException{
		String preparedStatement = "select * from ITEMREVIEWS where ID=?";
		
		Connection con = this.ds.getConnection();
		
		PreparedStatement stmt = con.prepareStatement(preparedStatement);
		
		stmt.setString(1, ID);
		ResultSet r = stmt.executeQuery();
		
		String preReview = "";
		String preDate = "";
		int preRating = 0;
		
		boolean isFirstReview = true;
		while (r.next()) {
			preReview = r.getString("REVIEW");
			preRating = r.getInt("RATING");
			preDate =  r.getString("REVIEWDATE");
			isFirstReview = false;
			break;
		}
		
		if (!isFirstReview) {
			this.deleteReview(ID);
		}
		
		r.close();
		stmt.close();
		con.close();
		
		if (isRating) {
			return this.insertReview(ID, USERID, ITEMID, preReview, RATING, preDate);
		}
		return this.insertReview(ID, USERID, ITEMID, REVIEW, preRating, REVIEWDATE);
		
	}
	
	/**
	 * @return number of deleted rows from ITEMREVIEWS table
	 * @throws NamingException
	 * @throws SQLException
	 */
	private int deleteReview(String ID) throws SQLException, NamingException{
		String preparedStatement = "delete from ITEMREVIEWS where ID=?";
		
		Connection con = this.ds.getConnection();
		
		PreparedStatement stmt = con.prepareStatement(preparedStatement);
		
		stmt.setString(1, ID);
		
		return stmt.executeUpdate();
	}
	
	/**
	 * @return number of inserted rows into ITEMREVIEWS table
	 * @throws NamingException
	 * @throws SQLException
	 */
	private int insertReview(String ID, String USERID, String ITEMID, String REVIEW, int RATING, String REVIEWDATE) throws SQLException, NamingException{
		String preparedStatement = "insert into ITEMREVIEWS values(?,?,?,?,?,?)";
		
		Connection con = this.ds.getConnection();
		
		PreparedStatement stmt = con.prepareStatement(preparedStatement);
		
		stmt.setString(1, ID);
		stmt.setString(2, USERID);
		stmt.setString(3, ITEMID);
		stmt.setString(4, REVIEW);
		stmt.setInt(5, RATING);
		stmt.setString(6, REVIEWDATE);
		
		return stmt.executeUpdate();
		
	}
}
