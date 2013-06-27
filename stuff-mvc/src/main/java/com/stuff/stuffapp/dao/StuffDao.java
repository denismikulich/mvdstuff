package com.stuff.stuffapp.dao;

import java.util.List;

import com.stuff.stuffapp.data.StuffType;
import com.stuff.stuffapp.domain.Stuff;

public interface StuffDao {

	public Stuff saveStuff(Stuff stuff);

	public List<Stuff> stuffList();

	public void removeStuff(Long id);

	public Stuff retriveStuff(Long id);

	public Stuff findStuff(String regNumber, int year);
	
	public Stuff findStuff(String regNumber, StuffType type, int year);
}
