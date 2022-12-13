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
	
	public Map<String, ItemBean> retrieve(String brand, String type, String category,String ID) throws SQLException, NamingException{
		String query = "select * from ITEMS";
		if(brand != null || type != null || category != null || ID != null) {
			query+= " where ";
		}
		if(brand != null ) {
			query+= "brand=" +"'" + brand + "' AND ";
		}
		if(type != null) {
			query+= "type1=" +"'" + type + "' AND ";
		}
		if(category != null ) {
			query+= "category=" +"'" + category + "' AND ";
		}
		if( ID != null) {
			query+= "ID="  +"'" + ID + "' AND ";
		}
		
		if(brand != null || type != null || category != null || ID != null) {
			
			query = query.substring(0,query.length()-4);
		}
		
		return getTable(query);
		
	}

	public Map<String, ItemBean> getTable(String query) throws SQLException{
		Map<String, ItemBean> rv = new HashMap<String, ItemBean>();
		Connection con = this.ds.getConnection();
		PreparedStatement p = con
				.prepareStatement(query);
		ResultSet r = p.executeQuery();
		while (r.next()){
			String ID = r.getString("ID");
			String category = r.getString("category");
			String brand = r.getString("brand");
			String type  = r.getString("type1");
			String name = r.getString("name");
			String price = r.getString("price");
			int quantity = r.getInt("quantity");
			String description = r.getString("description");
			String reviews = r.getString("reviews");
			String imageURL = r.getString("image");

			rv.put(ID, new ItemBean(ID, category, brand, type,name, price, quantity,
					description, reviews, imageURL));
		}
		r.close();
		p.close();
		con.close();
		return rv;
	}
	
	public ItemBean retrieveItem(String ID) throws SQLException, NamingException{
		String query = "select * from ITEMS ";

		if( ID != null) {
			query+= "where ID=" + ID;
		}
		return getRow(query);
	}
	
	public ItemBean getRow(String query) throws SQLException{
		ItemBean item = null;
		Connection con = this.ds.getConnection();
		PreparedStatement p = con
				.prepareStatement(query);
		ResultSet r = p.executeQuery();
		while (r.next()){
			String ID = r.getString("ID");
			String category = r.getString("category");
			String brand = r.getString("brand");
			String type  = r.getString("type1");
			String name = r.getString("name");
			String price = r.getString("price");
			int quantity = r.getInt("quantity");
			String description = r.getString("description");
			String reviews = r.getString("reviews");
			String imageURL = r.getString("image");

			item = new ItemBean(ID, category, brand, type,name, price, quantity,
					description, reviews, imageURL);
		}
		r.close();
		p.close();
		con.close();
		return item;
	}
	
	public int updateQuantity(String ID, int quantity) throws SQLException, NamingException{
		String query = "update ITEMS set quantity=? where ID=?";
		
		Connection con = this.ds.getConnection();
		
		PreparedStatement stmt = con.prepareStatement(query);
		
		stmt.setInt(1, quantity);
		stmt.setString(2, ID);

		
		return stmt.executeUpdate();
	}
}