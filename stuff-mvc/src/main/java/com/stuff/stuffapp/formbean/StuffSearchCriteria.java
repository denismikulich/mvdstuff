package com.stuff.stuffapp.formbean;

/**
 * Data class includes criteria for Stuff searching.
 * 
 * @author Denis.Mikulich
 *
 */
public class StuffSearchCriteria {

	private String stuffNumber;
	private int stuffsYear;
	private int type;

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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
