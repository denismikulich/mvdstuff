package com.stuff.stuffapp.data;

import java.util.List;

/**
 * Stuff history business object
 * 
 * @author denis.mikulich
 * 
 */
public class HistoryBO {

	private StuffBO stuff;
	private FlowBO currentPlace;
	private List<FlowBO> history;

	public StuffBO getStuff() {
		return stuff;
	}

	public void setStuff(StuffBO stuff) {
		this.stuff = stuff;
	}

	public FlowBO getCurrentPlace() {
		return currentPlace;
	}

	public void setCurrentPlace(FlowBO currentPlace) {
		this.currentPlace = currentPlace;
	}

	public List<FlowBO> getHistory() {
		return history;
	}

	public void setHistory(List<FlowBO> history) {
		this.history = history;
	}

}
