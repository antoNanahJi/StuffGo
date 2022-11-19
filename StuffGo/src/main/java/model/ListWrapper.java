package model;

import java.util.ArrayList;
import java.util.List;



import bean.StudentBean;
//Custom export for XML file

public class ListWrapper
{
//	@XmlAttribute
	private String namePrefix;
//	@XmlAttribute(name="creditTaken")
	private int credit_taken;
//	@XmlElement(name="studentList")
	private List<StudentBean> list;
	
	public ListWrapper() {
		this.namePrefix = "";
		this.credit_taken = 0;
		this.list = new ArrayList<StudentBean>();
	}
   
	public ListWrapper(String namePrefix, int credit_taken,
			List<StudentBean> list) {
		this.namePrefix = namePrefix;
		this.credit_taken = credit_taken;
		this.list = list;
	}
	
}