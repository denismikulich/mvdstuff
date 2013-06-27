package com.stuff.stuffapp.formbean;

import java.util.Date;

public class PrepareStuffsFormBean {

	private String sender;
	private String reciever;
	private String listStuffs;
	private int stuffsYear;
	private int stuffsType;
	private String sendNumber;
	private Date sendDate;
	private String sign;

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReciever() {
		return reciever;
	}

	public void setReciever(String reciever) {
		this.reciever = reciever;
	}

	public String getListStuffs() {
		return listStuffs;
	}

	public void setListStuffs(String listStuffs) {
		this.listStuffs = listStuffs;
	}

	public int getStuffsYear() {
		return stuffsYear;
	}

	public void setStuffsYear(int stuffsYear) {
		this.stuffsYear = stuffsYear;
	}

	public String getSendNumber() {
		return sendNumber;
	}

	public void setSendNumber(String sendNumber) {
		this.sendNumber = sendNumber;
	}

	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public int getStuffsType() {
		return stuffsType;
	}

	public void setStuffsType(int stuffsType) {
		this.stuffsType = stuffsType;
	}
	
}
