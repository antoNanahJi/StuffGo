package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import bean.EnrollmentBean;
import bean.ItemBean;
import bean.ItemReviewBean;

public class ItemReviewDAO {
	
	private DataSource ds;
	
	public ItemReviewDAO() throws ClassNotFoundException{
		try {
			ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/EECS");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public List<ItemReviewBean> getReviews(String ITEMID)throws SQLException, NamingException{
		String preparedStatement = "select * from ITEMREVIEWS where ITEMID=?";
		
		Connection con = this.ds.getConnection();
		
		PreparedStatement stmt = con.prepareStatement(preparedStatement);
		
		stmt.setString(1, ITEMID);
		ResultSet r = stmt.executeQuery();
		List<ItemReviewBean> rs = new ArrayList<ItemReviewBean>();
		System.out.print("lplpl: " + ITEMID);
		while (r.next()) {
			String id = r.getString("ID");
			String userID = r.getString("USERID");
			String itemID = r.getString("ITEMID");
			String review = r.getString("REVIEW");
			String reviewDate = r.getString("REVIEWDATE");

			rs.add(new ItemReviewBean(id, userID, itemID, review, reviewDate));
		}
		
		r.close();
		stmt.close();
		con.close();
		
		return rs;
	}
	
	public int addReview(String ID, String USERID, String ITEMID, String REVIEW, String REVIEWDATE) throws SQLException, NamingException{
		String preparedStatement = "select * from ITEMREVIEWS where ID=?";
		
		Connection con = this.ds.getConnection();
		
		PreparedStatement stmt = con.prepareStatement(preparedStatement);
		
		stmt.setString(1, ID);
		ResultSet r = stmt.executeQuery();
		System.out.print("LPLP " + ID);
		boolean isFirstReview = true;
		while (r.next()) {
			System.out.print("LPLP");
			isFirstReview = false;
			break;
		}
		
		if (!isFirstReview) {
			this.deleteReview(ID);
		}
		
		r.close();
		stmt.close();
		con.close();

		return this.insertReview(ID, USERID, ITEMID, REVIEW, REVIEWDATE);
	}
	
	public int deleteReview(String ID) throws SQLException, NamingException{
		String preparedStatement = "delete from ITEMREVIEWS where ID=?";
		
		Connection con = this.ds.getConnection();
		
		PreparedStatement stmt = con.prepareStatement(preparedStatement);
		
		stmt.setString(1, ID);
		
		return stmt.executeUpdate();
	}
	
	public int insertReview(String ID, String USERID, String ITEMID, String REVIEW, String REVIEWDATE) throws SQLException, NamingException{
		String preparedStatement = "insert into ITEMREVIEWS values(?,?,?,?,?)";
		
		Connection con = this.ds.getConnection();
		
		PreparedStatement stmt = con.prepareStatement(preparedStatement);
		
		stmt.setString(1, ID);
		stmt.setString(2, USERID);
		stmt.setString(3, ITEMID);
		stmt.setString(4, REVIEW);
		stmt.setString(5, REVIEWDATE);
		
		return stmt.executeUpdate();
	}
}
