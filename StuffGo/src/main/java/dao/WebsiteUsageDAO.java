package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import bean.ItemReviewBean;
import bean.WebsiteUsageBean;
import utilities.eventTypes;

public class WebsiteUsageDAO {
	private DataSource ds;
	
	public WebsiteUsageDAO() throws ClassNotFoundException{
		try {
			ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/EECS");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public List<WebsiteUsageBean> getRecords()throws SQLException, NamingException{
		String preparedStatement = "select * from VISITEVENT";
		
		Connection con = this.ds.getConnection();
		
		PreparedStatement stmt = con.prepareStatement(preparedStatement);
	
		ResultSet r = stmt.executeQuery();
		List<WebsiteUsageBean> rs = new ArrayList<WebsiteUsageBean>();

		while (r.next()) {
			String ip = r.getString("IPADDRESS");
			String date = r.getString("DATE");
			String itemID = r.getString("ITEMID");
			String event = r.getString("EVENT");


			rs.add(new WebsiteUsageBean(ip, date, itemID, eventTypes.valueOf(event)));
		}
		
		r.close();
		stmt.close();
		con.close();
		
		return rs;
	}
	
	public int addRecord(String ipAddress, String date, String itemID, eventTypes event) throws SQLException, NamingException{
		String preparedStatement = "select * from VISITEVENT where IPADDRESS=? and DAY=? and ITEMID=? and EVENT=?";
		
		Connection con = this.ds.getConnection();
		int res = 0;
		
		PreparedStatement stmt = con.prepareStatement(preparedStatement);
		
		stmt.setString(1, ipAddress);
		stmt.setString(2, date);
		stmt.setString(3, itemID);
		stmt.setString(4, event.toString());
		ResultSet r = stmt.executeQuery();
		
		boolean isFirstRecord = true;
		while (r.next()) {
			isFirstRecord = false;
			break;
		}
		
		if (isFirstRecord) {
			res = this.insertRecord(ipAddress, date, itemID, event);
		}
		
		r.close();
		stmt.close();
		con.close();
		
		return res;
		
	}
	
	public int insertRecord(String ipAddress, String date, String itemID, eventTypes event) throws SQLException, NamingException{
		String preparedStatement = "insert into VISITEVENT values(?,?,?,?)";
		
		Connection con = this.ds.getConnection();
		
		PreparedStatement stmt = con.prepareStatement(preparedStatement);
		
		stmt.setString(1, ipAddress);
		stmt.setString(2, date);
		stmt.setString(3, itemID);
		stmt.setString(4, event.toString());
		
		System.out.print("ipAddress: " + ipAddress + " date: " + date + " itemID: " + itemID + " event: " + event );
		return stmt.executeUpdate();
	
	}
	
}
