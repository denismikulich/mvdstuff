package com.stuff.stuffapp.formbean;

import java.util.Date;

public class SearchCriteriaBean {

	private String stuffNumber;
	private int stuffsYear;
	private String stuffNStartRange;
	private String stuffNEndRange;
	private String reciever;
	private String sender;
	private Date sendDateStartRange;
	private Date sendDateEndRange;
	private String sendNumber;
	private boolean useFullHistory;
	private boolean simpleSearch;

	public SearchCriteriaBean() {
		simpleSearch = true;
	}

	public String getStuffNumber() {
		return stuffNumber;
	}

	public void setStuffNumber(String stuffNumber) {
		this.stuffNumber = stuffNumber;
	}

	public int getStuffsYear() {
		return stuffsYear;
	}

	public void setStuffsYear(int stuffsYear) {
		this.stuffsYear = stuffsYear;
	}

	public String getStuffNStartRange() {
		return stuffNStartRange;
	}

	public void setStuffNStartRange(String stuffNStartRange) {
		this.stuffNStartRange = stuffNStartRange;
	}

	public String getStuffNEndRange() {
		return stuffNEndRange;
	}

	public void setStuffNEndRange(String stuffNEndRange) {
		this.stuffNEndRange = stuffNEndRange;
	}

	public String getReciever() {
		return reciever;
	}

	public void setReciever(String reciever) {
		this.reciever = reciever;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public Date getSendDateStartRange() {
		return sendDateStartRange;
	}

	public void setSendDateStartRange(Date sendDateStartRange) {
		this.sendDateStartRange = sendDateStartRange;
	}

	public Date getSendDateEndRange() {
		return sendDateEndRange;
	}

	public void setSendDateEndRange(Date sendDateEndRange) {
		this.sendDateEndRange = sendDateEndRange;
	}

	public String getSendNumber() {
		return sendNumber;
	}

	public void setSendNumber(String sendNumber) {
		this.sendNumber = sendNumber;
	}

	public boolean isUseFullHistory() {
		return useFullHistory;
	}

	public void setUseFullHistory(boolean useFullHistory) {
		this.useFullHistory = useFullHistory;
	}

	public boolean isSimpleSearch() {
		return simpleSearch;
	}

	public void setSimpleSearch(boolean simpleSearch) {
		this.simpleSearch = simpleSearch;
	}
	
	public boolean isAdvancedSearch() {
		return !simpleSearch;
	}

	public void setAdvancedSearch(boolean advancedSearch) {
		this.simpleSearch = !advancedSearch;
	}

}
