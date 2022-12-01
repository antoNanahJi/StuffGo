package model;

import javax.naming.NamingException;
import utilities.ItemReviewModel;
import utilities.StoreModel;
import utilities.UserModel;

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
	
	/***
	 * Creates and returns a singleton instance of this class
	 */
	public static MainModel getInstance() throws ClassNotFoundException {
		if(instance == null) {
			instance = new MainModel();
			instance.itemReviewModel = new ItemReviewModel();
			instance.storeModel = new StoreModel();
			instance.userModel = new UserModel();
		}
		return instance;
	}
	
	public ItemReviewModel getItemReviewModel(){
		return this.itemReviewModel;
	}
	
	public StoreModel getStoreModel(){
		return this.storeModel;
	}
	
	public UserModel getUserModel(){
		return this.userModel;
	}

}