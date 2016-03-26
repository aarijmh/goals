package com.org.buisnesslogic.relationVerifiers;

import java.util.List;
import java.util.Map;

import com.org.sg.POJO.action.Concept;
import com.org.sg.POJO.action.Learner;
import com.org.sg.POJO.action.LearnerConcept;

public interface RelationVerifier {

	List<String> verify(String value, Learner learner, Concept concept, Map<Integer, LearnerConcept> lcMap);
}
