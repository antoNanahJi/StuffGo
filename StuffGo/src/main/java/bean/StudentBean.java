package bean;

//Bean class for Student.
//credit_taken: # of credits that student has taken so far
//credit_graduate: # of credits that student need to graduate
//credit_taking: # of credits that student is currently enrolling
//sid: CSE ID
//name: full name
public class StudentBean {
	private String sid;
	private String name;
	private int credit_taken;
	private int credit_graduate;
	private int credit_taking;
	
	public StudentBean(String sid, String name, int credit_taken, int credit_graduate){
		this.sid = sid;
		this.name = name;
		this.credit_graduate = credit_graduate;
		this.credit_taken = credit_taken;
		this.credit_taking = 0;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCredit_taken() {
		return credit_taken;
	}

	public void setCredit_taken(int credit_taken) {
		this.credit_taken = credit_taken;
	}

	public int getCredit_graduate() {
		return credit_graduate;
	}

	public void setCredit_graduate(int credit_graduate) {
		this.credit_graduate = credit_graduate;
	}

	public int getCredit_taking() {
		return credit_taking;
	}

	public void setCredit_taking(int credit_taking) {
		this.credit_taking = credit_taking;
	}
	
	public void addCreditTaking(int credit){
		this.credit_taking += credit;
	}
	
}