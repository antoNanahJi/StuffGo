package utilities;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;

import dao.ItemPurchasedDAO;
import dao.WebsiteUsageDAO;
import bean.ItemReviewBean;
import bean.WebsiteUsageBean;


/**
 * The application model class
 * @author antoji
 *
 */
public class ItemPurchasedModel {
	// Private attributes
	private ItemPurchasedDAO id;
	
	public ItemPurchasedModel() {
		try {
			this.id = new ItemPurchasedDAO();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @return data from STUDENTS table where surname contains namePrefix and credit_taken equals credit_taken 
	 * @throws IOException
	 * @throws SQLException
	 */
	public Map<String, int[]> retriveRecords() throws SQLException, NamingException {
		
		Map<String, int[]> rs = this.id.getRecords();
		return rs;
	}
	
	/**
	 * @return number of inserted rows into STUDENTS table
	 * @throws NamingException
	 * @throws SQLException
	 */
	public  int insertRecord(String itemID, String date, int quantity, String userID)  throws SQLException, NamingException {
		// validate inputs sid, givenname, surname, credittake, and creditgraduate
		
		if (userID == null || userID.equals("")) {
			throw new NamingException("User ID can not be empty\n");
		}
		if (date == null || date.equals("")) {
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
	
}