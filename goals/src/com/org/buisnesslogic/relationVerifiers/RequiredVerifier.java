package com.org.buisnesslogic.relationVerifiers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.org.sg.DAO.ConceptrelationDAO;
import com.org.sg.DAO.LearnerConceptDAO;
import com.org.sg.POJO.action.Concept;
import com.org.sg.POJO.action.Conceptrelation;
import com.org.sg.POJO.action.Learner;
import com.org.sg.POJO.action.LearnerConcept;

public class RequiredVerifier implements RelationVerifier {

	@Override
	public List<String> verify(String value, Learner learner, Concept concept, Map<Integer, LearnerConcept> lcMap) {
		
		List<String> returnList = new ArrayList<>();
		
		ConceptrelationDAO conceptrelationDAO = new ConceptrelationDAO();
		LearnerConceptDAO learnerConceptDAO = new LearnerConceptDAO();
		
		List<Conceptrelation> requiredList = conceptrelationDAO.findRequiredRelationsOfConcept(concept.getId());
		for(Conceptrelation cr : requiredList)
		{
			LearnerConcept lc = lcMap.containsKey(cr.getConceptByConceptTo().getId()) ? lcMap.get(cr.getConceptByConceptTo().getId()) : learnerConceptDAO.findConceptInProfile(learner.getId(), cr.getConceptByConceptTo().getId());
			if(lc == null ||  Double.valueOf(lc.getValue()) < Double.valueOf(cr.getValue()))
			{	
				returnList.add("Cannot add concept '"+concept.getName()+"' because the learner does not have required("+cr.getValue()+"%) competence of concept '"+cr.getConceptByConceptTo().getName()+"' <br>\n");
			}
			
		}
		return returnList;
	}

}
