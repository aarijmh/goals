package com.org.buisnesslogic;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import com.org.sg.DAO.AdaptationknowledgeDAO;
import com.org.sg.DAO.ConceptPrDAO;
import com.org.sg.DAO.LearnerConceptDAO;
import com.org.sg.DAO.LearnerTracesDAO;
import com.org.sg.POJO.action.Adaptationknowledge;
import com.org.sg.POJO.action.Concept;
import com.org.sg.POJO.action.ConceptPr;
import com.org.sg.POJO.action.Learner;
import com.org.sg.POJO.action.LearnerConcept;
import com.org.sg.POJO.action.LearnerTraces;
import com.org.sg.POJO.action.PresentationPr;

public class ResourceSelectorAlgorithm {

	private ConceptPrDAO conceptPrDAO;
	private LearnerTracesDAO learnerTracesDAO;
	private LearnerConceptDAO learnerConceptDAO;
	private AdaptationknowledgeDAO adaptationknowledgeDAO;

	public ResourceSelectorAlgorithm() {
		// prconceptsDAO = new PrconceptsDAO();
		// usertracesDAO = new UsertracesDAO();
		// userconceptDAO = new UserconceptDAO();
		// adaptationmodelDAO = new AdaptationmodelDAO();

		conceptPrDAO = new ConceptPrDAO();
		learnerTracesDAO = new LearnerTracesDAO();
		learnerConceptDAO = new LearnerConceptDAO();
		adaptationknowledgeDAO = new AdaptationknowledgeDAO();

	}

	public Map<Concept, Map<ConceptPr, Map<String, String>>> generateResources(List<Concept> conceptList, Set<PresentationPr> scenarioList, Learner profile) {

		Map<Concept, Map<ConceptPr, Map<String, String>>> returnMap = new LinkedHashMap<Concept, Map<ConceptPr, Map<String, String>>>();

		for (Concept concept : conceptList) {
			Map<ConceptPr, Map<String, String>> returnList = new LinkedHashMap<ConceptPr, Map<String, String>>();
			Integer tId = concept.getId();
			LearnerConcept userconcept = learnerConceptDAO.findConceptInProfile(profile.getId(), tId);

			Map<Integer, Integer> included = new LinkedHashMap<Integer, Integer>();

			for (PresentationPr scenarioresource : scenarioList) {

				List<ConceptPr> tempList = conceptPrDAO.findResourcesOfConcept(tId, scenarioresource.getTypes().getId());

				int index = 0;
				Map<Integer, Integer> visited = new LinkedHashMap<Integer, Integer>();

				if (tempList.size() != 0) {
					for (;;) {

						if (visited.keySet().size() == tempList.size())
							break;

						Random randomGenerator = new Random();

						index = ((int) (randomGenerator.nextInt(tempList.size())));

						ConceptPr prc = tempList.get(index);

						visited.put(prc.getId(), prc.getId());

						if (included.containsKey(prc.getId())) {
							continue;
						}

						// usertracesDAO.findResourcesOfUser(profile.getId(),
						// prc
						// .getPedagogicalresource().getId());

						if (userconcept == null || userconcept.getValue() == null || userconcept.getValue().trim().equalsIgnoreCase("")
								|| Integer.valueOf(prc.getRequiredKnowledge()) <= Float.valueOf(userconcept.getValue())) {

							List<Adaptationknowledge> adaptationmodel = adaptationknowledgeDAO.findByProperty("pedagogicalresource.id", prc.getPedagogicalresource().getId());

							List<LearnerTraces> traceList = learnerTracesDAO.findTracesOfLearnerForResource(profile.getId(), prc.getPedagogicalresource().getId());

							if (traceList.size() == 0) {
								included.put(prc.getId(), prc.getId());
							}
							else {
								/*
								 * ToDO check whether to include this resource
								 * or not
								 */
								included.put(prc.getId(), prc.getId());
							}

							synchronized (returnList) {
								Map<String, String> result = new LinkedHashMap<String, String>();
								if (adaptationmodel != null) {
									// IAdaptationClass adaptationClass =
									// AdaptationModelFactory.getClass(adaptationmodel
									// .getClassName());
									// Map<String, String> parameters = new
									// LinkedHashMap<String, String>();

									// parameters.put("Level",
									// concept.getLevelTo());
									for (Adaptationknowledge ak : adaptationmodel)
										result.put(ak.getRule(), ak.getDescription());
								}

								returnList.put(prc, result);
							}

							break;
						}
						else {
						}

						/*
						 * Match cognitive abilities of the resource with the
						 * cognitive abilities of the user profile
						 */

					}
				}
			}

			returnMap.put(concept, returnList);
		}

		return returnMap;
	}

}
