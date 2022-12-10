package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


/**
 * This class is responsible to retrieve data from ITEMPURCHASED table
 * @author antoji
 *
 */
public class ItemPurchasedDAO {
	// Private attributes
	private DataSource ds;
	
	/**
	 * Constructor
	 * @throws ClassNotFoundException
	 */
	public ItemPurchasedDAO() throws ClassNotFoundException{
		try {
			ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/EECS");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @return data from the ITEMPURCHASED table
	 * @throws SQLException, NamingException
	 */
	public Map<String, int[]> getRecords()throws SQLException, NamingException{
		String preparedStatement = "select * from ITEMPURCHASED";
		
		Connection con = this.ds.getConnection();
		
		PreparedStatement stmt = con.prepareStatement(preparedStatement);
	
		ResultSet r = stmt.executeQuery();
		Map<String, int[]> rv = new HashMap<String, int[]>();
		

		while (r.next()) {
			String itemID = r.getString("ITEMID");
			int quantity = r.getInt("QUANTITY");
			int month = Integer.valueOf(r.getString("DAY")) - 1;
			
			if (!rv.containsKey(itemID)) {				
				
				int months[] = new int[12];
				months[month] = quantity;
				rv.put(itemID, months);
			} else {
				rv.get(itemID)[month] += quantity;
			}
		}
		
		r.close();
		stmt.close();
		con.close();
		
		return rv;
	}
	
	/**
	 * @return number of inserted rows from ITEMPURCHASED table
	 * @throws NamingException
	 * @throws SQLException
	 */
	public int insertRecord(String itemID, String date, int quantity, String userID) throws SQLException, NamingException{
		String preparedStatement = "insert into ITEMPURCHASED values(?,?,?,?)";
		
		Connection con = this.ds.getConnection();
		
		PreparedStatement stmt = con.prepareStatement(preparedStatement);
		
		stmt.setString(1, itemID);
		stmt.setString(2, date);
		stmt.setInt(3, quantity);
		stmt.setString(4, userID);

		return stmt.executeUpdate();
	
	}
	
	/**
	 * @return List containing the users purchased history
	 * @throws NamingException
	 * @throws SQLException
	 */
	public List<String> getPurchasedHistory(String userID)throws SQLException, NamingException{
		String preparedStatement = "select ITEMID from ITEMPURCHASED where USERID=?";
		
		Connection con = this.ds.getConnection();
		
		PreparedStatement stmt = con.prepareStatement(preparedStatement);
		stmt.setString(1, userID);
		
		ResultSet r = stmt.executeQuery();
		List<String> rv = new ArrayList<String>();
		

		while (r.next()) {
			String itemID = r.getString("ITEMID");
			rv.add(itemID);
		}
		
		r.close();
		stmt.close();
		con.close();
		
		return rv;
	}

}
