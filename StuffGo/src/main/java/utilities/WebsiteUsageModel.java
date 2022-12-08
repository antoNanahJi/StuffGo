package utilities;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

import dao.WebsiteUsageDAO;
import bean.ItemReviewBean;
import bean.WebsiteUsageBean;


/**
 * The application model class
 * @author antoji
 *
 */
public class WebsiteUsageModel {
	// Private attributes
	private WebsiteUsageDAO id;
	
	public WebsiteUsageModel() {
		try {
			this.id = new WebsiteUsageDAO();
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
	public List<WebsiteUsageBean> retriveRecords() throws SQLException, NamingException {
		
		List<WebsiteUsageBean> rs = this.id.getRecords();
		return rs;
	}
	
	/**
	 * @return number of inserted rows into STUDENTS table
	 * @throws NamingException
	 * @throws SQLException
	 */
	public  int insertRecord(String ipAddress, String date, String itemID, eventTypes event) throws SQLException, NamingException {
		// validate inputs sid, givenname, surname, credittake, and creditgraduate
		
		if (ipAddress == null || ipAddress.equals("")) {
			throw new NamingException("IP address can not be empty\n");
		}
		if (date == null || date.equals("")) {
			throw new NamingException("Date can not be empty\n");
		}
		if (itemID == null || itemID.equals("")) {
			throw new NamingException("ITEMID can not be empty\n");
		}
		if (event == null) {
			throw new NamingException("Event can not be empty\n");
		}

		return this.id.addRecord(ipAddress, date, itemID, event);
	}
	
}