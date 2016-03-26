package com.org.buisnesslogic.ActionHandlers;

import java.util.ArrayList;
import java.util.List;

import com.org.sg.SessionFactory.HibernateSessionFactory;

public abstract class AbstractActionHandler {

	private List<String> errors = new ArrayList<String>();
	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	public void execute()
	{
		try {
			preProcessing();
			processing();
			postProcessing();
		} catch (Exception e) {
			errors.add(e.getMessage());
			e.printStackTrace();
			
			if(HibernateSessionFactory.getSession().getTransaction().isActive())
				HibernateSessionFactory.getSession().getTransaction().rollback();
			
			HibernateSessionFactory.getSession().close();
		}
	}
	
	public void preProcessing()
	{
		HibernateSessionFactory.getSession().beginTransaction();
	}
	
	abstract public void processing();
	
	public void postProcessing()
	{
		if(HibernateSessionFactory.getSession().getTransaction().isActive())
			HibernateSessionFactory.getSession().getTransaction().commit();
		
		HibernateSessionFactory.getSession().close();
	}
}
