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
	public Map<String, ItemBean> retreiveItem(String brand, String type, String category,String ID) throws SQLException, NamingException {
		return this.itemData.retrieve(brand,type,category,ID);
	}
}
