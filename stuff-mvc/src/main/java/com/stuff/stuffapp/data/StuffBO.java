package com.stuff.stuffapp.data;

public class StuffBO {

	private String regNumber;

	private StuffType type;

	private Integer year;

	public String getRegNumber() {
		return regNumber;
	}

	public void setRegNumber(String regNumber) {
		this.regNumber = regNumber;
	}

	public StuffType getType() {
		return type;
	}

	public void setType(StuffType type) {
		this.type = type;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StuffBO other = (StuffBO) obj;
		if (!regNumber.equals(other.regNumber)) {
			return false;
		}
		if (type != other.type) {
			return false;
		}
		if (year != other.year) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		return prime * year + prime * type.getIntValue() + regNumber.hashCode();
	}

}
