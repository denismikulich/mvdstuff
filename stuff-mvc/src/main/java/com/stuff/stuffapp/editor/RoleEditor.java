package com.stuff.stuffapp.editor;

import java.beans.PropertyEditorSupport;

import org.springframework.stereotype.Component;

import com.stuff.stuffapp.domain.Role;

public class RoleEditor extends PropertyEditorSupport  {

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
			this.setValue(new Role());
	}

	

	
}
