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
	
	/**
	 * Item Bean Constructor
	 * @param iD
	 * @param category
	 * @param brand
	 * @param type1
	 * @param name
	 * @param price
	 * @param quantity
	 * @param description
	 * @param reviews
	 * @param image
	 */
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

	/**
	 * @return ID
	 */
	public String getID() {
		return ID;
	}

	/**
	 * @param iD
	 */
	public void setID(String iD) {
		ID = iD;
	}

	/**
	 * @return category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * @return brand
	 */
	public String getBrand() {
		return brand;
	}

	/**
	 * @param brand
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}

	/**
	 * @return type
	 */
	public String getType1() {
		return type1;
	}

	/**
	 * @param type1
	 */
	public void setType1(String type1) {
		this.type1 = type1;
	}

	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return price
	 */
	public String getPrice() {
		return price;
	}

	/**
	 * @param price
	 */
	public void setPrice(String price) {
		this.price = price;
	}

	/**
	 * @return description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return reviews
	 */
	public String getReviews() {
		return reviews;
	}

	/**
	 * @param reviews
	 */
	public void setReviews(String reviews) {
		this.reviews = reviews;
	}

	/**
	 * @return image
	 */
	public String getImage() {
		return image;
	}

	/**
	 * @param image
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * @return quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	



}