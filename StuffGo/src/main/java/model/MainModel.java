package model;

import utilities.ItemPurchasedModel;
import utilities.ItemReviewModel;
import utilities.StoreModel;
import utilities.UserModel;
import utilities.WebsiteUsageModel;

/**
 * The application model class
 * @author antoji
 *
 */
public class MainModel {
	// Private attributes
	private static MainModel instance;
	private ItemReviewModel itemReviewModel;
	private StoreModel storeModel;
	private UserModel userModel;
	private WebsiteUsageModel websiteUsageModel;
	private ItemPurchasedModel itemPurchasedModel;
	
	/***
	 * Creates and returns a singleton instance of this class
	 */
	public static MainModel getInstance() throws ClassNotFoundException {
		if(instance == null) {
			instance = new MainModel();
			instance.itemReviewModel = new ItemReviewModel();
			instance.storeModel = new StoreModel();
			instance.userModel = new UserModel();
			instance.websiteUsageModel = new WebsiteUsageModel();
			instance.itemPurchasedModel = new ItemPurchasedModel();
		}
		return instance;
	}
	
	/**
	 * @return Item Review Model
	 */
	public ItemReviewModel getItemReviewModel(){
		return this.itemReviewModel;
	}
	
	/**
	 * @return Store Model
	 */
	public StoreModel getStoreModel(){
		return this.storeModel;
	}
	
	/**
	 * @return User Model
	 */
	public UserModel getUserModel(){
		return this.userModel;
	}
	
	/**
	 * @return Website Usage Model
	 */
	public WebsiteUsageModel getWebsiteUsageModel(){
		return this.websiteUsageModel;
	}

	/**
	 * @return Item Purchased Model
	 */
	public ItemPurchasedModel getItemPurchasedModel() {
		return itemPurchasedModel;
	}

}