package model;

import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import bean.StudentBean;
import dao.EnrollmentDAO;
import dao.StudentDAO;
import bean.EnrollmentBean;

public class SisModel {
	private StudentDAO studentData;
	private EnrollmentDAO enrollmentData;
	
	public SisModel() throws ClassNotFoundException{
		studentData = new StudentDAO();
		enrollmentData = new EnrollmentDAO();
	}
	//return the Map of CSE ID and the student information
	public Map<String, StudentBean> retriveStudent(String namePrefix, String credit_taken) throws Exception{
		return this.studentData.retrieve(sanitizeNamePrefix(namePrefix), sanitizeCreditTaken(credit_taken));
	}

	//return the course ID and the course information
	public Map<String, EnrollmentBean> retriveEnrollment() throws Exception{
		return this.enrollmentData.retrieve();
	}
	
	private String sanitizeNamePrefix(String namePrefix) throws Exception{
		if (namePrefix == null)
			return "";
		return namePrefix;
	}

	private int sanitizeCreditTaken(String credit_taken) throws Exception{
		int result= -1;
		if ((credit_taken.equals("")) || (credit_taken == null)){
			return 0;
		}
		if (credit_taken.matches("[0-9]+"))
			result = Integer.parseInt(credit_taken);
		else throw new Exception("CREDIT_TAKEN format is invalid");
		if ((result < 0) || (result > 150)) 
			throw new Exception("CREDIT_TAKEN must be between 0-150");
		return result;
	}
	//export database into XML
	public void export(String namePrefix, String credit_taken, String filename) throws Exception{
		namePrefix = sanitizeNamePrefix(namePrefix);
		int sanitizeCreditTaken = sanitizeCreditTaken(credit_taken);
		String path = filename.substring(0, filename.lastIndexOf("/"));
		Map<String, StudentBean> studentList = retriveStudent(namePrefix, credit_taken);
		Map<String, EnrollmentBean> enrollmentList = retriveEnrollment();
		for (EnrollmentBean value : enrollmentList.values()) {
			List<String> students = value.getStudents();
			int credit = value.getCredit();
			for (String s: students){
				if (studentList.containsKey(s)){
					studentList.get(s).addCreditTaking(credit);
				}
			}
		}
		List<StudentBean> list = new ArrayList<StudentBean>(studentList.values());
		ListWrapper lw = new ListWrapper(namePrefix, sanitizeCreditTaken, list);
	    
	    StringWriter sw = new StringWriter();
	    sw.write("<?xml version='1.0'?>\n");
	    sw.write("<?xml-stylesheet type=\"text/xsl\" href=\"SIS.xsl\"?>");
	    sw.write("\n");
	    
	    System.out.println(sw.toString()); // for debugging
	    FileWriter fw = new FileWriter(filename);
	    fw.write(sw.toString());
	    fw.close();
	}
}
