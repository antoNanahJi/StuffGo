package bean;

import java.util.ArrayList;
//Bean class for Enrollment. Each course will have a list of students who enrolled in this course
//and its number of credit.
//Students list are the list of his/her CSE ID
public class EnrollmentBean {
	private String cid;
	private ArrayList<String> students;
	private int credit;
	
	public EnrollmentBean(String cid, ArrayList<String> students, int credit){
		this.cid = cid;
		this.students = students;
		this.credit = credit;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public ArrayList<String> getStudents() {
		return students;
	}

	public void setStudents(ArrayList<String> students) {
		this.students = students;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}
	
	public void addStudent(String sid){
		this.students.add(sid);
	}

}
