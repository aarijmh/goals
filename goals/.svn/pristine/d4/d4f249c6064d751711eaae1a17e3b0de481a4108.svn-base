package com.org.buisnesslogic;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.org.coursegenrator.utilities.Constants;
import com.org.sg.DAO.ConceptDAO;
import com.org.sg.DAO.ConceptrelationDAO;
import com.org.sg.DAO.LearnerConceptDAO;
import com.org.sg.DAO.LearnerDAO;
import com.org.sg.POJO.action.Conceptrelation;
import com.org.sg.POJO.action.Learner;
import com.org.sg.POJO.action.LearnerConcept;

public class LearnerConceptValuesUpdater {
	
	protected List<Integer> conceptIds;
	protected List<Integer> values;
	protected Integer learnerId;
	protected LearnerConceptDAO learnerConceptDAO;
	protected ConceptrelationDAO conceptrelationDAO;
	protected ConceptDAO conceptDAO;
	protected LearnerDAO learnerDAO;
	protected Learner learner;
	protected Map<Integer, Integer> doneMap = new LinkedHashMap<Integer, Integer>();
	protected Map<Integer, LearnerConcept> lcMap = new LinkedHashMap<Integer, LearnerConcept>();
	
	public LearnerConceptValuesUpdater(List<Integer> cId,List<Integer> cValues, Integer lId)
	{
		this.conceptIds = cId;
		this.values = cValues;
		this.learnerId = lId;
	}
	
	
	public void updateValues() throws Exception
	{
		//HibernateSessionFactory.getSession().beginTransaction();
		
		learnerConceptDAO = new LearnerConceptDAO();
		conceptrelationDAO = new ConceptrelationDAO();
		conceptDAO = new ConceptDAO();
		learnerDAO = new LearnerDAO();
		
		learner = learnerDAO.findById(learnerId);
		
		for(int i = 0; i < conceptIds.size(); i++)
		{
			hasPartDownRelation(conceptIds.get(i),new  Float(values.get(i)));
		}

		/*
		 * 			LearnerConcept learnerConcept = learnerConceptDAO.findConceptInProfile(learnerId, conceptIds.get(i));
			
			if(learnerConcept == null)
			{
				learnerConcept = new LearnerConcept();
				learnerConcept.setConcept(conceptDAO.findById(conceptIds.get(i)));
				learnerConcept.setLearner(learner);
			}
			else
			{
				if(Float.valueOf(learnerConcept.getValue()) == values.get(i))
					continue;
			}
			
			learnerConcept.setValue(values.get(i).toString());
			learnerConceptDAO.save(learnerConcept);
		 */
		
//		HibernateSessionFactory.getSession().getTransaction().commit();
//		HibernateSessionFactory.getSession().close();
	}
	
	public void hasPartDownRelation(Integer conceptId, Float value)
	{

		if(!doneMap.containsKey(conceptId))
		{
			LearnerConcept learnerConcept = lcMap.containsKey(conceptId) ? lcMap.get(conceptId) : learnerConceptDAO.findConceptInProfile(learnerId, conceptId);
			
			if(learnerConcept == null)
			{
				learnerConcept = new LearnerConcept();
				learnerConcept.setConcept(conceptDAO.findById(conceptId));
				learnerConcept.setLearner(learner);
			}
			else
			{
//				if(Float.valueOf(learnerConcept.getValue()) == value)
//					return;
			}
			
			learnerConcept.setValue(value.toString());
			learnerConceptDAO.save(learnerConcept);
			
			lcMap.put(learnerConcept.getConcept().getId(), learnerConcept);
			doneMap.put(conceptId, conceptId);
			
			List<Conceptrelation> hasPartRelation = conceptrelationDAO.findRelationOfConcept(conceptId, Constants.HAS_PARTS_ID);
			
			for(Conceptrelation cr : hasPartRelation)
			{
				Float calcValue = Float.valueOf(cr.getValue())/100 * value;
				LearnerConcept learnerHasPartConcept = lcMap.containsKey(cr.getConceptByConceptTo().getId()) ? lcMap.get(cr.getConceptByConceptTo().getId()) :  learnerConceptDAO.findConceptInProfile(learnerId, cr.getConceptByConceptTo().getId());
				
				if(learnerHasPartConcept == null)
				{
					learnerHasPartConcept = new LearnerConcept();
					learnerHasPartConcept.setConcept(cr.getConceptByConceptTo());
					learnerHasPartConcept.setLearner(learner);
					learnerHasPartConcept.setValue(calcValue.toString());
					learnerConceptDAO.save(learnerHasPartConcept);
					
					lcMap.put(learnerHasPartConcept.getConcept().getId(), learnerHasPartConcept);
					hasPartDownRelation(cr.getConceptByConceptTo().getId(),calcValue);
				}
			}
			
			List<Conceptrelation> toRelation = conceptrelationDAO.findToRelationOfConcept(conceptId, Constants.HAS_PARTS_ID);
			for(Conceptrelation cr : toRelation)
			{
				Float calcValue = Float.valueOf(cr.getValue()) * value/100.0f;
				LearnerConcept learnerHasPartConcept = lcMap.containsKey(cr.getConceptByConceptFrom().getId()) ? lcMap.get(cr.getConceptByConceptFrom().getId()) :learnerConceptDAO.findConceptInProfile(learnerId, cr.getConceptByConceptFrom().getId());
				
				if(learnerHasPartConcept == null)
				{
					learnerHasPartConcept = new LearnerConcept();
					learnerHasPartConcept.setConcept(cr.getConceptByConceptFrom());
					learnerHasPartConcept.setLearner(learner);
					learnerHasPartConcept.setValue(calcValue.toString());
					learnerConceptDAO.save(learnerHasPartConcept);
					
					lcMap.put(learnerHasPartConcept.getConcept().getId(), learnerHasPartConcept);
					hasPartDownRelation(cr.getConceptByConceptFrom().getId(),calcValue);
				}
			}
		}
	}
	
	public void hasPartUpRelation(Integer conceptId, Float value)
	{

//		LearnerConcept learnerConcept = learnerConceptDAO.findConceptInProfile(learnerId, conceptId);
		
//		if(learnerConcept == null)
//		{
//			learnerConcept = new LearnerConcept();
//			learnerConcept.setConcept(conceptDAO.findById(conceptId));
//			learnerConcept.setLearner(learner);
//		}
//
//		
//		learnerConcept.setValue(value.toString());
//		learnerConceptDAO.save(learnerConcept);
//		
		
		List<Conceptrelation> hasPartRelation = conceptrelationDAO.findToRelationOfConcept(conceptId, Constants.HAS_PARTS_ID);
		
		for(Conceptrelation cr : hasPartRelation)
		{
			Float calcValue = Float.valueOf(cr.getValue())/100 * value;
			LearnerConcept learnerHasPartConcept = learnerConceptDAO.findConceptInProfile(learnerId, cr.getConceptByConceptTo().getId());
			
			if(learnerHasPartConcept == null)
			{
				learnerHasPartConcept = new LearnerConcept();
				learnerHasPartConcept.setConcept(cr.getConceptByConceptTo());
				learnerHasPartConcept.setLearner(learner);
				learnerHasPartConcept.setValue(calcValue.toString());
				learnerConceptDAO.save(learnerHasPartConcept);
				
				hasPartDownRelation(cr.getConceptByConceptTo().getId(),calcValue);
			}
		}
	}

}
