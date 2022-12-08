package bean;

import utilities.eventTypes;

public class WebsiteUsageBean {
	private String ipAddress;
	private String date;
	private String itemID;
	private eventTypes event;
	
	public WebsiteUsageBean (String ipAddress, String date, String itemID, eventTypes event) {

		this.ipAddress = ipAddress;
		this.date = date;
		this.itemID = itemID;
		this.event = event;

	}
	
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public eventTypes getEvent() {
		return event;
	}
	public void setEvent(eventTypes event) {
		this.event = event;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getItemID() {
		return itemID;
	}
	public void setItemID(String itemID) {
		this.itemID = itemID;
	}
}