package utilities;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;

import dao.WebsiteUsageDAO;
import bean.WebsiteUsageBean;


/**
 * The website usage model class
 * @author antoji
 *
 */
public class WebsiteUsageModel {
	// Private attributes
	private WebsiteUsageDAO id;
	
	/**
	 * Constructor
	 */
	public WebsiteUsageModel() {
		try {
			this.id = new WebsiteUsageDAO();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @return data from the VISITEVENT table 
	 * @throws IOException
	 * @throws SQLException
	 */
	public List<WebsiteUsageBean> retriveRecords() throws SQLException, NamingException {
		
		List<WebsiteUsageBean> rs = this.id.getRecords();
		return rs;
	}
	
	/**
	 * @return number of inserted rows into VISITEVENT table
	 * @throws NamingException
	 * @throws SQLException
	 */
	public  int insertRecord(String ipAddress, String date, String itemID, eventTypes event) throws SQLException, NamingException {
		// validate inputs ipAddress, date, itemID, and event
		
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
		
		if (Security.containsXSS(itemID) || Security.containsSQL(itemID)) {
			throw new NamingException("SQL Injection/ XSS attempt\n");
		}

		return this.id.addRecord(ipAddress, date, itemID, event);
	}
	
}