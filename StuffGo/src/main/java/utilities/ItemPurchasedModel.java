package utilities;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;

import dao.ItemPurchasedDAO;


/**
 * The item purchased model class
 * @author antoji
 *
 */
public class ItemPurchasedModel {
	// Private attributes
	private ItemPurchasedDAO id;
	
	/**
	 * Constructor
	 */
	public ItemPurchasedModel() {
		try {
			this.id = new ItemPurchasedDAO();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @return data from the ITEMPURCHASED table
	 * @throws SQLException, NamingException
	 */
	public Map<String, int[]> retriveRecords() throws SQLException, NamingException {
		
		Map<String, int[]> rs = this.id.getRecords();
		return rs;
	}
	
	/**
	 * @return number of inserted rows from ITEMPURCHASED table
	 * @throws NamingException
	 * @throws SQLException
	 */
	public  int insertRecord(String itemID, String date, int quantity, String userID)  throws SQLException, NamingException {
		// validate inputs itemID, date, quantity, and userID
		
		if (userID == null || userID.equals("")) {
			throw new NamingException("User ID can not be empty\n");
		}
		if (date == null || date.equals("") || date.length() != 2) {
			throw new NamingException("Date can not be empty\n");
		}
		if (itemID == null || itemID.equals("")) {
			throw new NamingException("ITEMID can not be empty\n");
		}
		if (quantity < 0) {
			throw new NamingException("Quantity should be a number >= 0\n");
		}

		return this.id.insertRecord(itemID, date, quantity, userID);
	}
	
	/**
	 * @return List containing the users purchased history
	 * @throws NamingException
	 * @throws SQLException
	 */
	public List<String> getPurchasedHistory(String userID) throws SQLException, NamingException {
		if (userID == null || userID.equals("")) {
			throw new NamingException("User ID can not be empty\n");
		}
		
		return this.id.getPurchasedHistory(userID);
	}
	
}