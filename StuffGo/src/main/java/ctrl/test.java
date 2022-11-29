package ctrl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class test
 */
@WebServlet("/test")
public class test extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public test() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		try {
			try {
				dbTest();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	public void dbTest() throws SQLException, ClassNotFoundException {
		System.out.println("start performance");
//		String cnnString = "jdbc:sqlserver://stuffgo.database.windows.net;"
//				+ "database=stuffgo;"
//				+ "user=StuffGo;" 
//				+ "password=@stuff123;"
//				+ "encrypt=true;"
//				+ "trustServerCertificate=false;"
//				+ "loginTimeout=30;";
		 Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		 try {
			 ResultSet set = null;
			 Connection con = DriverManager.getConnection("jdbc:sqlserver://stuffgo.database.windows.net:1433;database=stuffgo;user=StuffGo;password=@stuff123;");
			 Statement statement = con.createStatement();
			 set = statement.executeQuery("SELECT * FROM ITEMS");
			 while(set.next()) {
				 System.out.println(set.getString("ID"));
			 }
			 
			 System.out.println( DriverManager.getConnection("jdbc:sqlserver://stuffgo.database.windows.net:1433;database=stuffgo;user=StuffGo;password=@stuff123;"));
		 }
		 catch (Exception e) {
System.out.println("wrong");
			 
	}

}}
