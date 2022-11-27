package dao;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import bean.ItemBean;
//DAO for retrieving student database
public class ItemDAO {

	private DataSource ds;
	public ItemDAO() throws ClassNotFoundException{
		try {
			ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/EECS");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public Map<String, ItemBean> retrieve() throws SQLException, NamingException{
		String query = "select * from ITEMS";
		return getTable(query);
	}

	public Map<String, ItemBean> getTable(String query) throws SQLException{
		Map<String, ItemBean> rv = new HashMap<String, ItemBean>();
		Connection con = this.ds.getConnection();
		PreparedStatement p = con
				.prepareStatement(query);
		ResultSet r = p.executeQuery();
		while (r.next()){
			String category = r.getString("category");
			String name = r.getString("name");
			String ID = r.getString("ID");
			String description = r.getString("description");
			String reviews = r.getString("reviews");
			String imageURL = r.getString("image");
			rv.put(ID, new ItemBean(category, name ,ID ,description, reviews, imageURL));
		}
		r.close();
		p.close();
		con.close();
		return rv;
	}
}