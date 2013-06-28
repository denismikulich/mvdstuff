package com.stuff.stuffapp.formbean;

public class StuffStateFormBean {

	private String stuffNumber;
	/**
	 * 1 - Created, all OK. <br>
	 * 2 - Created, current place wrong.<br>
	 * 3 - Not created.<br>
	 * 4 - Not created, number contain not numeric symbols<br>
	 * 5 - Repeat.
	 */
	private int state;

	public StuffStateFormBean(String stuffNumber) {
		this.stuffNumber = stuffNumber;
		this.state = 1;
	}

	public StuffStateFormBean(String stuffNumber, int state) {
		if (state > 5 || state < 1) {
			throw new IllegalArgumentException(
					"Illegal Stuff state value. Must be from 1 to 5.");
		}
		this.stuffNumber = stuffNumber;
		this.state = state;
	}

	public String getStuffNumber() {
		return stuffNumber;
	}

	public void setStuffNumber(String stuffNumber) {
		this.stuffNumber = stuffNumber;
	}

	public int getState() {
		return state;
	}

	/**
	 * 1 - Created, all OK. <br>
	 * 2 - Created, current place wrong.<br>
	 * 3 - Not created.<br>
	 * 4 - Not created, number contain not numeric symbols<br>
	 * 5 - Repeat.
	 */
	public void setState(int state) {
		if (state > 5 || state < 1) {
			throw new IllegalArgumentException(
					"Illegal Stuff state value. Must be from 1 to 5.");
		}
		this.state = state;
	}

	public String getStateDescription() {
		switch (state) {
		case 1:
			return "";
		case 2:
			return "current place wrong";
		case 3:
			return "Not created yet";
		case 4:
			return "number contain not numeric symbols";
		case 5:
			return "Repeat";
		default:
			return "";
		}
	}

}
