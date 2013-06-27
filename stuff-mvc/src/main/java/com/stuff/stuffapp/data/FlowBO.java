package com.stuff.stuffapp.data;

import java.util.Date;

public class FlowBO {

	private StuffBO stuff;
	/**
	 * accompanying document data.
	 */
	private String sender;
	private String recipient;
	private String outgoingNo;
	private Date outgoingDate;
	private String signature;
	private String description;
	/**
	 * user who entered data.
	 */
	private UserBO user;

	public StuffBO getStuff() {
		return stuff;
	}

	public void setStuff(StuffBO stuff) {
		this.stuff = stuff;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public String getOutgoingNo() {
		return outgoingNo;
	}

	public void setOutgoingNo(String outgoingNo) {
		this.outgoingNo = outgoingNo;
	}

	public Date getOutgoingDate() {
		return outgoingDate;
	}

	public void setOutgoingDate(Date outgoingDate) {
		this.outgoingDate = outgoingDate;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public UserBO getUser() {
		return user;
	}

	public void setUser(UserBO user) {
		this.user = user;
	}

}
