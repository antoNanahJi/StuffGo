package utilities;

import java.sql.SQLException;
import java.util.Map;
import bean.ItemBean;
import javax.naming.NamingException;
import dao.ItemDAO;

/**
 * This DAO class is responsible to retrieve data from Database and Form Queries
 * @author Philip Michalowski
 *
 */
public class StoreModel {
	
	private ItemDAO itemData;


	public StoreModel() throws ClassNotFoundException {
		itemData = new ItemDAO();

	}

	// return the Map of CSE ID and the student information
	public Map<String, ItemBean> retrieve(String brand, String type, String category, String ID) throws Exception {
		if (brand != null) {
			if (Security.containsSQL(brand)) {
				throw new Exception("SQL Injection Attempt");
			}
			if (Security.containsXSS(brand)) {
				throw new Exception("XSS Attempt");
			}
		}
		if (type != null) {
			if (Security.containsSQL(type)) {
				throw new Exception("SQL Injection Attempt");
			}
			if (Security.containsXSS(type)) {
				throw new Exception("XSS Attempt");
			}
		}
		if (category != null) {
			if (Security.containsSQL(category)) {
				throw new Exception("SQL Injection Attempt");
			}
			if (Security.containsXSS(category)) {
				throw new Exception("XSS Attempt");
			}
		}
		if (ID != null) {
			if (Security.containsSQL(ID)) {
				throw new Exception("SQL Injection Attempt");
			}
			if (Security.containsXSS(ID)) {
				throw new Exception("XSS Attempt");
			}
		}

		return this.itemData.retrieve(brand, type, category, ID);
	}

	public ItemBean retreiveItem(String ID) throws Exception {
		if (ID != null) {
			if (ID.equals("")) {
				throw new Exception("Item ID can not be null");
			}
			if (Security.containsSQL(ID)) {
				throw new Exception("SQL Injection Attempt");
			} 
			if (Security.containsXSS(ID)) {
				throw new Exception("XSS Attempt");
			}
		}

		return this.itemData.retrieveItem(ID);
	}



	public int updateQuantity(String ID, int quantity) throws SQLException, NamingException {
		return this.itemData.updateQuantity(ID, quantity);
	}
}
