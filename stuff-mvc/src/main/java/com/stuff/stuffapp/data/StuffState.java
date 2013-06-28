package com.stuff.stuffapp.data;


public enum StuffState {
	
	OK(1), WRONG_PLACE(2), NOT_CREATED(3), CONTAIN_SIMBOLS(4), REPEAT(5), UNCHECKED(6);
	
	private int state;
	
	private StuffState(int state) {
		this.state = state;
	}

	@Override
	public String toString() {
		switch (this) {
		case OK:
			return "stuffstate.OK";
		case WRONG_PLACE:
			return "stuffstate.WRONG_PLACE";
		case NOT_CREATED:
			return "stuffstate.NOT_CREATED";
		case CONTAIN_SIMBOLS:
			return "stuffstate.CONTAIN_SIMBOLS";
		case REPEAT:
			return "stuffstate.REPEAT";
		case UNCHECKED:
			return "stuffstate.UNCHECKED";
		default:
			return "stuffstate.UNDEFINED";
		}
	}
	
	public int getIntValue() {
		return state;
	}
	
	public static StuffState valueOf(int code) {
		for (StuffState stuffState : StuffState.values()) {
			if (stuffState.getIntValue() == code) {
				return stuffState;
			}
		}
		return null;
	}

}
