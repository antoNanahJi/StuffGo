package ctrl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.EnrollmentBean;
import model.SisModel;
import utilities.View;
import bean.StudentBean;

@WebServlet("/Start")
public class Start extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Start() {
        super();
    }
    //Initialize helper class called SIS and saved it on Servlet Context
    @Override
    public void init() throws ServletException{
    	super.init();
    	try{
    		SisModel sis = new SisModel();
    		this.getServletContext().setAttribute("SIS", sis);
    	}catch (ClassNotFoundException e){
    		throw new ServletException("Class Not Found" + e);
    	}
 	
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jsp = "";
		SisModel sis = (SisModel) this.getServletContext().getAttribute("SIS");
		String namePrefix, credit_taken;
		namePrefix = request.getParameter("namePrefix");
		credit_taken = request.getParameter("credit_taken");
		request.setAttribute("namePrefix", namePrefix);
		request.setAttribute("credit_taken", credit_taken);
		//If user just run the URL
		if (request.getParameter("report") == null && request.getParameter("generateXML") == null){
			jsp = "/HTMLJs.html";
		//If user clicked on generate XML button
		}
		if (request.getParameter("generateXML") != null){
			String f = "export/" + request.getSession().getId()+".xml";
			String filename = this.getServletContext().getRealPath("/" + f);
			try {
				sis.export(namePrefix, credit_taken, filename);
				request.setAttribute("xml_file", f);
				System.out.println(f);
			} catch (Exception e) {
				String msg = e.getMessage();
				request.setAttribute("error", msg);
				e.printStackTrace();
				jsp = "/Form.jspx";
			}
			jsp = "/Done.jspx";
		//If user clicked on generate rerort
		}
		if (request.getParameter("reportJSP") != null){
			try {
				jsp = "/Form.jspx";
				Map<String, StudentBean> studentList = sis.retriveStudent(namePrefix, credit_taken);

				Map<String, EnrollmentBean> enrollmentList = sis.retriveEnrollment();
				//for each course with student list, we will add that credit 
				//into the number of credit that student is taking
				for (EnrollmentBean value : enrollmentList.values()) {
					List<String> students = value.getStudents();
					int credit = value.getCredit();
					for (String s: students){
						if (studentList.containsKey(s)){
							studentList.get(s).addCreditTaking(credit);
						}
					}
				}
				request.setAttribute("studentList", studentList);
				request.setAttribute("done", true);
			} catch (Exception e) {
				String msg = e.getMessage();
				request.setAttribute("error", msg);
			}
		}
		if (request.getParameter("reportAjax") != null){
			String studentListAsJSON="{}";
			try{
				View view = new View();
				studentListAsJSON = view.studentAsJson(sis, namePrefix, credit_taken);
		}catch(Exception e) {
				String msg = e.getMessage();
			}	
			response.setContentType("application/json");
			PrintWriter out = response.getWriter();
			//out.printf("Grace Period Interest: $%.2f<br/>Monthly payment: $%.2f", gracePeriodInterest, monthlyPayment);
            out.append(studentListAsJSON);
			out.flush();
		
			
		}
		request.getRequestDispatcher(jsp).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
