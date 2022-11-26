package model;

import java.sql.SQLException;
import java.util.Map;
import bean.ItemBean;
import javax.naming.NamingException;
import dao.ItemDAO;


public class StoreModel {
	private ItemDAO itemData;
	
	
	public StoreModel() throws ClassNotFoundException{
		itemData = new ItemDAO();
	
	}
	//return the Map of CSE ID and the student information
	public Map<String, ItemBean> retreiveItem() throws SQLException, NamingException {
		return this.itemData.retrieve();
	}
}
