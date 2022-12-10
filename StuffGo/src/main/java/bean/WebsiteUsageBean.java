package bean;

import utilities.eventTypes;

/** 
 * Information about VISITEVENT table from DataBase
 * @author antoji
 *
 */
public class WebsiteUsageBean {
	// Private attributes
	private String ipAddress;
	private String date;
	private String itemID;
	private eventTypes event;
	
	/**
	 * Constructor
	 */
	public WebsiteUsageBean (String ipAddress, String date, String itemID, eventTypes event) {
		this.ipAddress = ipAddress;
		this.date = date;
		this.itemID = itemID;
		this.event = event;
	}
	
	/**
	 * @return IP address
	 */
	public String getIpAddress() {
		return ipAddress;
	}
	
	/**
	 * Set IP address
	 */
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	
	/**
	 * @return Event type
	 */
	public eventTypes getEvent() {
		return event;
	}
	
	/**
	 * Set event type
	 */
	public void setEvent(eventTypes event) {
		this.event = event;
	}
	
	/**
	 * @return Date in form 'dd/mm/yyyy'
	 */
	public String getDate() {
		return date;
	}
	
	/**
	 * Set date
	 */
	public void setDate(String date) {
		this.date = date;
	}
	
	/**
	 * @return Item ID
	 */
	public String getItemID() {
		return itemID;
	}
	
	/**
	 * Set item ID
	 */ 
	public void setItemID(String itemID) {
		this.itemID = itemID;
	}
}