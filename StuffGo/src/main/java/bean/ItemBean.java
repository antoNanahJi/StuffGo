package bean;

/**
 * This class is used to create item Object
 * @author Philip Michalowski
 *
 */
public class ItemBean {
	private String ID;
	private String category;
	private String brand;
	private String type1;
	private String name;
	private String price;
	private int quantity;
	private String description;
	private String reviews;
	private String image;
	
	public ItemBean(String iD, String category, String brand, String type1, String name, String price, int quantity,
			String description, String reviews, String image) {
		super();
		ID = iD;
		this.category = category;
		this.brand = brand;
		this.type1 = type1;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.description = description;
		this.reviews = reviews;
		this.image = image;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getType1() {
		return type1;
	}

	public void setType1(String type1) {
		this.type1 = type1;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	



}