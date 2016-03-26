package com.org.sg.POJO.action;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Transient;

public abstract class AbstractPOJO {

	private String action;
	private List<String> errors = new ArrayList<String>();
	
	@Transient
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	@Transient
	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

}
