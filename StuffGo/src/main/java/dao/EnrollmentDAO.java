package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import bean.EnrollmentBean;
//DAO for retrieving enrollment database
public class EnrollmentDAO {

	private DataSource ds;
	public EnrollmentDAO() throws ClassNotFoundException{
		try {
			ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/EECS");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public Map<String, EnrollmentBean> retrieve() throws SQLException, NamingException{
		String query = "select * from enrollment";
		return getTable(query);
	}
	
	public Map<String, EnrollmentBean> getTable(String query) throws SQLException{
		Connection con = this.ds.getConnection();
		PreparedStatement p = con
				.prepareStatement(query);
		ResultSet r = p.executeQuery();
		Map<String, EnrollmentBean> enrollmentList = new HashMap<String, EnrollmentBean>();
		while (r.next()){
			String cid = r.getString("CID");
			String sid = r.getString("SID");
			int credit = r.getInt("CREDIT");
			if (!enrollmentList.containsKey(cid)){
				ArrayList<String> students = new ArrayList<String>();
				students.add(sid);
				EnrollmentBean enrollment = new EnrollmentBean(cid, students , credit);
				enrollmentList.put(cid, enrollment);
			}else{
				enrollmentList.get(cid).addStudent(sid);
			}
		}
		r.close();
		p.close();
		con.close();
		return enrollmentList;
	}
	
}
