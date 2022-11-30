package model;

import javax.naming.NamingException;
import model.ItemReviewModel;

/**
 * The application model class
 * @author antoji
 *
 */
public class MainModel {
	// Private attributes
	private static MainModel instance;
	private ItemReviewModel itemReviewModel;
	
	/***
	 * Creates and returns a singleton instance of this class
	 */
	public static MainModel getInstance() throws ClassNotFoundException {
		if(instance == null) {
			instance = new MainModel();
			instance.itemReviewModel = new ItemReviewModel();
		}
		return instance;
	}
	
	public ItemReviewModel getItemReviewModel(){
		return this.itemReviewModel;
	}

}