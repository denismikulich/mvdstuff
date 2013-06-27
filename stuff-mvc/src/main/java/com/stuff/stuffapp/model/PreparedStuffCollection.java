package com.stuff.stuffapp.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.stuff.stuffapp.data.StuffBO;
import com.stuff.stuffapp.data.StuffState;
import com.stuff.stuffapp.data.StuffType;
import com.stuff.stuffapp.domain.Stuff;
import com.stuff.stuffapp.formbean.StuffNumberBean;
import com.stuff.stuffapp.service.DBService;

public class PreparedStuffCollection {

	Map<StuffBO, StuffState> stuffs;
	
	@Autowired
	DBService dbService;

	public PreparedStuffCollection() {
		stuffs = new HashMap<StuffBO, StuffState>();
	}
	
	public void clear() {
		stuffs.clear();
	}
	
	/**
	 * Split numbers comma list and put it into the state map.
	 * Check stuff state with DB.
	 * 
	 * @param numbersCommaList Numbers comma list.
	 * @param type Stuff type.
	 * @param year Stuff year.
	 */
	public void processData(String numbersCommaList, StuffType type, int year) {
		if (numbersCommaList == null)
			return;
		String[] numbersArray = numbersCommaList.split("[ .,]");
		stuffs.clear();
		for (String regNumber : numbersArray) {
			regNumber = regNumber.trim();
			if (!regNumber.isEmpty()) {
				StuffBO stuff = new StuffBO();
				stuff.setRegNumber(regNumber);
				stuff.setType(type);
				stuff.setYear(year);
				stuffs.put(stuff, StuffState.UNCHECKED);
			}
		}
		checkStuffsState();
	}
	
	private void checkStuffsState() {
		Set<StuffBO> keys = stuffs.keySet();
		
		for (StuffBO stuff : keys) {
			stuffs.remove(stuff); // remove item from collection.
			if (!dbService.isStuffExist(stuff)) { // not created entity.
				StuffState state = null;
				try {
					Integer.parseInt(stuff.getRegNumber());
				} catch(NumberFormatException ex) {
					state = StuffState.CONTAIN_SIMBOLS; // state not created and regNumber contain symbols.
				}
				if (state == null) {
					state = StuffState.NOT_CREATED;
				}
				stuffs.put(stuff, state); // put new value.
			} else { // stuff is exist
				// TODO:
				// boolean bWrongPlace = isWrongPlace(Stuff stuff);
				stuffs.put(stuff, StuffState.OK);
			}
			
			/*if (isRepeat(stuffNumberBean)) {
				stuffNumberBean.setState(5);
			}
			stuffList.add(stuffEntity);*/
		}
	}
	
	private boolean isRepeat(StuffNumberBean bean) {
		
		return false;
	}
}
