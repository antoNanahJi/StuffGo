package bean;

//Bean class for Student.
//credit_taken: # of credits that student has taken so far
//credit_graduate: # of credits that student need to graduate
//credit_taking: # of credits that student is currently enrolling
//sid: CSE ID
//name: full name
public class ItemBean {
	private String category;
	private String name;
	private String ID;
	private String description;
	private String reviews;
	private String image;
	
	public ItemBean(String category, String name, String ID, String description, String reviews, String image) {
		super();
		this.category = category;
		this.name = name;
		this.ID = ID;
		this.description = description;
		this.reviews = reviews;
		this.image = image;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getReviews() {
		return reviews;
	}

	public void setReviews(String reviews) {
		this.reviews = reviews;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	
	
}