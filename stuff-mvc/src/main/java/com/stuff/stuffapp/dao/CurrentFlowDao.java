package com.stuff.stuffapp.dao;

import com.stuff.stuffapp.domain.CurrentFlow;
import com.stuff.stuffapp.domain.Stuff;
import com.stuff.stuffapp.domain.StuffFlow;

public interface CurrentFlowDao {

	public void save(CurrentFlow curFlow);
	
	public CurrentFlow retrive(Long id);
	
	public StuffFlow getFlowByStuff(Stuff stuff);
}
