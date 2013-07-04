package com.stuff.stuffapp.dao;

import java.util.List;

import com.stuff.stuffapp.domain.CurrentFlow;
import com.stuff.stuffapp.domain.Stuff;
import com.stuff.stuffapp.domain.StuffFlow;
import com.stuff.stuffapp.formbean.SearchCriteriaBean;

public interface CurrentFlowDao {

	public void save(CurrentFlow curFlow);
	
	public CurrentFlow retrive(Long id);
	
	public StuffFlow getFlowByStuff(Stuff stuff);

	public CurrentFlow getCurFlowByStuff(Stuff stuff);
	
	public List<StuffFlow> advancedSearch(SearchCriteriaBean criteria);
}
