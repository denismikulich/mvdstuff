package com.stuff.stuffapp.formbean;

import com.stuff.stuffapp.data.StuffState;

public class StuffStateFormBean {

	private String stuffNumber;
	private StuffState state;

	public StuffStateFormBean(String stuffNumber, StuffState state) {
		this.stuffNumber = stuffNumber;
		this.state = state;
	}

	public String getStuffNumber() {
		return stuffNumber;
	}

	public void setStuffNumber(String stuffNumber) {
		this.stuffNumber = stuffNumber;
	}

	public StuffState getState() {
		return state;
	}

	public void setState(StuffState state) {
		this.state = state;
	}

	public String getStateDescription() {
		return state.toString();
	}

}
