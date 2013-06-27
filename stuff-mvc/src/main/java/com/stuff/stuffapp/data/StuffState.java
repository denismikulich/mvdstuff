package com.stuff.stuffapp.data;

public enum StuffState {
	
	OK(1), WRONG_PLACE(2), NOT_CREATED(3), CONTAIN_SIMBOLS(4), REPEAT(5), UNCHECKED(6);
	
	private int state;
	
	private StuffState(int state) {
		this.state = state;
	}

}
