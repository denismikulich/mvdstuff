package com.stuff.stuffapp.dao;

import org.springframework.stereotype.Repository;

import com.stuff.stuffapp.domain.CurrentFlow;
import com.stuff.stuffapp.domain.Stuff;
import com.stuff.stuffapp.domain.StuffFlow;

@Repository
public class CurrentFlowDaoImpl implements CurrentFlowDao {

	@Override
	public void save(CurrentFlow curFlow) {
		throw new RuntimeException("Not implemented yet.");

	}

	@Override
	public CurrentFlow retrive(Long id) {
		throw new RuntimeException("Not implemented yet.");
	}

	@Override
	public StuffFlow getFlowByStuff(Stuff stuff) {
		throw new RuntimeException("Not implemented yet.");
	}

}
