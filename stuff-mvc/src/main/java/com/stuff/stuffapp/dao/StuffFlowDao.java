package com.stuff.stuffapp.dao;

import java.util.List;

import com.stuff.stuffapp.domain.StuffFlow;
import com.stuff.stuffapp.formbean.SearchCriteriaBean;

public interface StuffFlowDao {

	public void saveFlow(StuffFlow flow);

	public StuffFlow retriveFlow(Long id);

	public List<StuffFlow> findFlowsByStuff(String name, Integer type, Integer year);
	
	public List<StuffFlow> searchFlows(SearchCriteriaBean criteria);
	
	public List<StuffFlow> getStuffHistory(Long stuffID);
}
