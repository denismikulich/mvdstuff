package com.stuff.stuffapp.data;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class SearchResultData {

	List<FlowBO> data;

	public SearchResultData() {
		data = new ArrayList<FlowBO>();
	}

	public List<FlowBO> getData() {
		return data;
	}

	public void setData(List<FlowBO> data) {
		this.data = data;
	}
	
}
