package com.stuff.stuffapp.data;

public enum StuffType {

	OTKAZ(1), PRIVATE(2), ADMINISTRATIVE(3);
	
	private int type;
	
	private StuffType(int type) {
		this.type = type;
	}

	@Override
	public String toString() {
		switch (this) {
			case OTKAZ:
				return "OTKAZ";
			case PRIVATE:
				return "PRIVATE";
			case ADMINISTRATIVE:
				return "ADMINISTRATIVE";
			default:
				return super.toString();
		}
	}

	public int getIntValue() {
		return type;
	}
	
	public static StuffType valueOf(int code) {
		for (StuffType stuffType : StuffType.values()) {
			if (stuffType.getIntValue() == code) {
				return stuffType;
			}
		}
		return null;
	}
	
}
