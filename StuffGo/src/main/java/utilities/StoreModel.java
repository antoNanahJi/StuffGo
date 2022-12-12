package utilities;

import java.sql.SQLException;
import java.util.Map;
import bean.ItemBean;
import javax.naming.NamingException;
import dao.ItemDAO;


public class StoreModel {
	private ItemDAO itemData;
	private static final String [] SQL_COMMANDS = {"SELECT", "UPDATE", "DELETE", "INSERT INTO",
			"CREATE DATABASE", "ALTER DATABASE", "CREATE TABLE", "ALTER TABLE", "DROP TABLE", "CREATE INDEX",
			"DROP INDEX", "=", ";","*"};
	
	public StoreModel() throws ClassNotFoundException{
		itemData = new ItemDAO();
	
	}
	//return the Map of CSE ID and the student information
	public Map<String, ItemBean> retrieve(String brand, String type, String category,String ID) throws Exception {
		if(brand!=null) {
			if(checkForInjection(brand)) {
				throw new Exception("SQL Injection Attempt");
			}
		}
		if(type!=null) {
			if(checkForInjection(type)) {
				throw new Exception("SQL Injection Attempt");
			}
		}
		if(category!=null) {
			if(checkForInjection(category)) {
				throw new Exception("SQL Injection Attempt");
			}
		}
		if(ID!=null) {
			if(checkForInjection(ID)) {
				throw new Exception("SQL Injection Attempt");
			}
		}
		
		return this.itemData.retrieve(brand,type,category,ID);
	}
	
	public ItemBean retreiveItem(String ID) throws Exception {
		if(ID!=null) {
			if(checkForInjection(ID)) {
				throw new Exception("SQL Injection Attempt");
			}
		}
		
		return this.itemData.retrieveItem(ID);
	}
	
	private boolean checkForInjection(String paramToCheck) {
		for(int i = 0 ; i < SQL_COMMANDS.length ;i++ ) {
			if(paramToCheck.contains(SQL_COMMANDS[i])) {
				return true;
			}
		}
		return false;
	}
}
