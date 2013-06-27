package com.stuff.stuffapp.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stuff.stuffapp.dao.StuffFlowDao;
import com.stuff.stuffapp.domain.StuffFlow;
import com.stuff.stuffapp.formbean.SearchCriteriaBean;

@Component
public class SearchModel {

	private SearchCriteriaBean searchCriteriaBean;
	private List<StuffFlow> searchResult;
	
	@Autowired
	private StuffFlowDao flowDao;
	
	public SearchModel() {
		searchCriteriaBean = new SearchCriteriaBean();
		searchResult = new ArrayList<StuffFlow>();
	}

	public SearchCriteriaBean getSearchCriteriaBean() {
		return searchCriteriaBean;
	}

	public void setSearchCriteriaBean(SearchCriteriaBean searchCriteriaBean) {
		this.searchCriteriaBean = searchCriteriaBean;
	}

	public void processSearch() {
		
		searchResult = flowDao.searchFlows(getSearchCriteriaBean());
	}

	public void clearSearchResult() {
		searchResult = new ArrayList<StuffFlow>();
	}
	
	public List<StuffFlow> getSearchResult() {
		return searchResult;
	}
	
	
	
}
