package com.org.buisnesslogic;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.org.coursegenrator.utilities.Constants;
import com.org.sg.DAO.ConceptrelationDAO;
import com.org.sg.DAO.LearnerConceptDAO;
import com.org.sg.POJO.action.Concept;
import com.org.sg.POJO.action.Conceptrelation;
import com.org.sg.POJO.action.Learner;
import com.org.sg.POJO.action.LearnerConcept;

public class ConceptGeneratorAlgorithm {

	private LearnerConceptDAO learnerConceptDAO;
	private ConceptrelationDAO conceptrelationDAO;
	private Integer count = 0;
	private Integer globalCount = 1;
	
	private Map<Concept,List<Concept>> reasoningMap = new LinkedHashMap<Concept, List<Concept>>();

	// private RelationfromDAO relationfromDAO;
	// private RelationtoDAO relationtoDAO;

	// private Map<Relationto, String> conceptResult = new
	// LinkedHashMap<Relationto, String>();

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public ConceptGeneratorAlgorithm() {
		learnerConceptDAO = new LearnerConceptDAO();
		conceptrelationDAO = new ConceptrelationDAO();

		/*learnerConceptDAO.setSessionFactory(HibernateSessionFactory.getSessionFactory());
		conceptrelationDAO.setSessionFactory(HibernateSessionFactory.getSessionFactory());*/
		// relationfromDAO = new RelationfromDAO();
		// relationtoDAO = new RelationtoDAO();
	}

	public Map<Integer, Map<Concept, String>> generateConcepts(List<Concept> conceptList, List<Float> percentageList, Learner learner, boolean recursive, Integer count) {

		Map<Integer, Map<Concept, String>> tempResult = new LinkedHashMap<Integer, Map<Concept, String>>();

		synchronized (tempResult) {
			int i = 0;
			for (Concept c : conceptList) {
				LearnerConcept userconcept = learnerConceptDAO.findConceptInProfile(learner.getId(), c.getId());
				float percentage = percentageList.get(i++).floatValue();
				float percentageOriginal = percentage;
				if (userconcept == null || Float.valueOf(userconcept.getValue()) < percentage || recursive) {
					if (!recursive)
						percentage -= userconcept == null || Float.valueOf(userconcept.getValue()) == null ? 0 : Float.valueOf(userconcept.getValue());

					if(!reasoningMap.containsKey(c))
					{
						reasoningMap.put(c, new ArrayList<Concept>());
					}
					
					if(!recursive)
					{
						if(tempResult.containsKey(count))
						{
							tempResult.get(count).put(c, String.valueOf(percentage));
						}
						else
						{
							Map<Concept,String> jMap = new LinkedHashMap<Concept, String>();
							jMap.put(c, String.valueOf(percentage));
							tempResult.put(count,jMap);
						}
					}
					
					
					RequiredRelation(tempResult, c, percentageOriginal, learner, count);
					HasPartRelation(tempResult, c, percentageOriginal, learner, count);
					TypeOfRelation(tempResult, c, percentageOriginal, learner, count);
				}
			}
		}

		return tempResult;
	}

	public void RequiredRelation(Map<Integer, Map<Concept, String>> tempResult, Concept c, float percentage, Learner userprofile, Integer count) {
		/*
		 * Required Relation
		 */
		List<Conceptrelation> requiredList = conceptrelationDAO.findRequiredRelationsOfConcept(c.getId());

		if (requiredList != null && requiredList.size() > 0) {
			/*
			 * DO ordering of all the sub relations
			 */
			
			for (Conceptrelation conceptrelation : requiredList) {
				
				
				
				Map<Integer,Integer> resultCountMap = new LinkedHashMap<Integer, Integer>();
				
				Map<Concept, String> putMap = new LinkedHashMap<Concept, String>();
				LearnerConcept learnerConcept = learnerConceptDAO.findConceptInProfile(userprofile.getId(), conceptrelation.getConceptByConceptTo().getId());

				if (learnerConcept != null) {
					if (Float.valueOf(learnerConcept.getValue()) < Float.valueOf(conceptrelation.getValue())) {
						if (conceptrelation.getValue() != null) {
							putMap.put(conceptrelation.getConceptByConceptTo(), String.valueOf((Float.valueOf(conceptrelation.getValue()) - Float.valueOf(learnerConcept.getValue()))));
//							tempResult.put(count, putMap);
						}
						else {
							putMap.put(conceptrelation.getConceptByConceptTo(), conceptrelation.getValue());
						}
					}
					else {
						continue;
					}
				}
				else {
					putMap.put(conceptrelation.getConceptByConceptTo(), conceptrelation.getValue());
				}
				
				reasoningMap.get(c).add(conceptrelation.getConceptByConceptTo());
				
				if(tempResult.keySet().size() == 0)
					tempResult.put(count, putMap);
				else
				{
					for(Integer r : tempResult.keySet())
					{
						tempResult.get(r).putAll(putMap);
					}
				}


				/*
				 * Uncoomment this section to return to the version at
				 * 20/09/2011 @ 13:48
				 */
				// Map<Conceptrelation, String> tempMap = new
				// LinkedHashMap<Conceptrelation, String>();
				// tempMap.put(conceptrelation,
				// tempResult.get(conceptrelation));

				List<Concept> conceptList = new ArrayList<Concept>();
				conceptList.add(conceptrelation.getConceptByConceptTo());

				List<Float> floatList = new ArrayList<Float>();
				try {
					floatList.add(Float.valueOf(tempResult.get(count).get(conceptrelation.getConceptByConceptTo())));
				}
				catch (Exception ne) {
					floatList.add(0.0f);
				}
				
				Integer highCount = globalCount;

				for(int i = 0; i <= highCount; i++)
				{
					if(!tempResult.containsKey(i))
						continue;
					
					Map<Integer, Map<Concept, String>> returnR = generateConcepts(conceptList, floatList, userprofile, true, i);
					
					
					for(Integer r : returnR.keySet())
					{
						resultCountMap.put(r, r);
						returnR.get(r).putAll(tempResult.get(i));
					}
	//				
					for(Integer r : returnR.keySet())
					{
						if(tempResult.containsKey(r))
							tempResult.get(r).putAll(returnR.get(r));
						else
							tempResult.put(r,returnR.get(r));
					}
					
					for(Integer r : tempResult.keySet())
					{
						if(globalCount < r)
							globalCount = r;
					}
				}
				
//				Integer highCount;
//				highCount = globalCount;
//				
//				for(int r = 0; r <= globalCount ; r++)
//				{
//					if(!tempResult.containsKey(r))
//						continue;
//					
//					
//					if(resultCountMap.containsKey(r))
//						continue;
//					
//					boolean first = true;
//					
//					for(Integer k : returnR.keySet())
//					{
//						if(first)
//							tempResult.get(r).putAll(returnR.get(k));
//						else
//						{
//							tempResult.put(++highCount, returnR.get(k));
//							//tempResult.get(highCount).putAll(tempResult.get(r));
//						}
//						first = false;
//					}
//				}
				
//				System.out.println("sdfsdf");
				
				
				/*
				 * End of Uncomment section
				 */
			}
			/*
			 * Finish required relation
			 */
		}
	}

	public void HasPartRelation(Map<Integer, Map<Concept, String>> tempResult, Concept c, float percentage, Learner userprofile, Integer count) {
		/*
		 * Has Parts Relation
		 */

		List<Conceptrelation> hasPartRelation = conceptrelationDAO.findRelationOfConcept(c.getId(), Constants.HAS_PARTS_ID);

		if (hasPartRelation != null && hasPartRelation.size() > 0) {
			/*
			 * DO ordering of all the sub relations
			 */
			/*
			 * Implement Map function
			 */

			Map<Integer, Float> conceptParticipation = new LinkedHashMap<Integer, Float>();

			Map<Integer, Concept> conceptLeft = new LinkedHashMap<Integer, Concept>();

			List<Conceptrelation> toConcepts = new ArrayList<Conceptrelation>();

			toConcepts.addAll(hasPartRelation);

			float participation = percentage / toConcepts.size();
			int counter = 0;

			while (percentage > 0 && conceptLeft.keySet().size() < toConcepts.size()) {

				if (counter == toConcepts.size()) {
					participation = percentage / (toConcepts.size() - conceptLeft.keySet().size());
					counter = 0;
				}

				Conceptrelation relationTo = toConcepts.get(counter);

				if (conceptLeft.containsKey(relationTo.getId())) {
					counter++;
					continue;
				}

				float actpart = conceptParticipation.containsKey(relationTo.getId()) ? conceptParticipation.get(relationTo.getId()) : 0;

				if (participation <= (Float.valueOf(relationTo.getValue()) - actpart))
				/*
				 * The concept is contributing considerably
				 */
				{
					conceptParticipation.put(relationTo.getId(), actpart + participation);
					percentage -= participation;
				}
				else
				/*
				 * The concept is not contributing considerably
				 */
				{
					conceptParticipation.put(relationTo.getId(), Float.valueOf(relationTo.getValue()));
					percentage = percentage - (Float.valueOf(relationTo.getValue()) - actpart);
					conceptLeft.put(relationTo.getId(), relationTo.getConceptByConceptTo());
				}

				counter++;
			}

			for (Conceptrelation relationto : toConcepts) {
				/*
				 * Check whether it is further divided
				 */

				/*
				 * Finish checking
				 */
				Map<Concept, String> putMap = new LinkedHashMap<Concept, String>();

				LearnerConcept toconcept = learnerConceptDAO.findConceptInProfile(userprofile.getId(), relationto.getConceptByConceptTo().getId());

				List<Concept> conceptList = new ArrayList<Concept>();
				
				if (toconcept != null && toconcept.getValue() != null) {

					float resultPart;
					try
					{
						resultPart = (conceptParticipation.get(relationto.getId()) / Float.valueOf(relationto.getValue()) * 100) - Float.valueOf(toconcept.getValue());
					}
					catch(NumberFormatException ne)
					{
						resultPart = 0.0f;
					}
					if (resultPart > 0) {
						putMap.put(relationto.getConceptByConceptTo(), String.valueOf(resultPart));
						if(tempResult.keySet().size() == 0)
							tempResult.put(count, putMap);
						else
						{
							for(Integer r : tempResult.keySet())
							{
								tempResult.get(r).putAll(putMap);
							}
						}
						reasoningMap.get(c).add(relationto.getConceptByConceptTo());
						conceptList.add(relationto.getConceptByConceptTo());

						List<Float> floatList = new ArrayList<Float>();
						floatList.add(Float.valueOf(tempResult.get(count).get(relationto.getConceptByConceptTo())));

						Map<Integer, Map<Concept, String>> returnR = generateConcepts(conceptList, floatList, userprofile, true, count);
						
						for(Integer r : returnR.keySet())
						{
							returnR.get(r).putAll(tempResult.get(count));
						}
						
						
						for(Integer r : returnR.keySet())
						{
							if(tempResult.containsKey(r))
								tempResult.get(r).putAll(returnR.get(r));
							else
								tempResult.put(r,returnR.get(r));
						}
						
						for(Integer r : tempResult.keySet())
						{
							if(globalCount < r)
								globalCount = r;
						}
//						tempResult.putAll(generateConcepts(conceptList, floatList, userprofile, true, count));
					}
				}
				else {
					reasoningMap.get(c).add(relationto.getConceptByConceptTo());
					putMap.put(relationto.getConceptByConceptTo(), String.valueOf((conceptParticipation.get(relationto.getId()) / Float.valueOf(relationto.getValue()) * 100)));
					if(tempResult.keySet().size() == 0)
						tempResult.put(count, putMap);
					else
					{
						for(Integer r : tempResult.keySet())
						{
							tempResult.get(r).putAll(putMap);
						}
					}

					conceptList.add(relationto.getConceptByConceptTo());
					List<Float> floatList = new ArrayList<Float>();
					floatList.add(Float.valueOf(tempResult.get(count).get(relationto.getConceptByConceptTo())));

					Map<Integer, Map<Concept, String>> returnR = generateConcepts(conceptList, floatList, userprofile, true, count);
					
					for(Integer r : returnR.keySet())
					{
						returnR.get(r).putAll(tempResult.get(count));
					}
					
					
					for(Integer r : returnR.keySet())
					{
						if(tempResult.containsKey(r))
							tempResult.get(r).putAll(returnR.get(r));
						else
							tempResult.put(r,returnR.get(r));
					}
					
					for(Integer r : tempResult.keySet())
					{
						if(globalCount < r)
							globalCount = r;
					}
//					tempResult.putAll(generateConcepts(conceptList, floatList, userprofile, true, count));
				}

			}
			/*
			 * Finish Has Parts relation
			 */

		}
	}

	//
	public void TypeOfRelation(Map<Integer, Map<Concept, String>> tempResult, Concept c, float percentage, Learner userprofile, Integer count) {
		/*
		 * Type-OF Relation
		 */
		List<Conceptrelation> typeRelation = conceptrelationDAO.findRelationOfConcept(c.getId(), Constants.TYPE_OF_ID);
//		Map<Integer, Map<Conceptrelation, String>> copyMap = new LinkedHashMap<Integer, Map<Conceptrelation,String>>();
//		
//		
//		Integer copyCount = count;
		
		Boolean firstTime = true;

		if (typeRelation != null && typeRelation.size() > 0) {
			/*
			 * DO ordering of all the sub relations
			 */

			List<Conceptrelation> toConcepts = new ArrayList<Conceptrelation>();
			toConcepts.addAll(typeRelation);

			for (Conceptrelation relationto : toConcepts) {

				Map<Concept, String> putMap = new LinkedHashMap<Concept, String>();
				LearnerConcept requiredConcept = learnerConceptDAO.findConceptInProfile(userprofile.getId(), relationto.getConceptByConceptTo().getId());
				if (requiredConcept != null) {
					if (Float.valueOf(requiredConcept.getValue()) < Float.valueOf(relationto.getValue())) {

						putMap.put(relationto.getConceptByConceptTo(), String.valueOf((Float.valueOf(relationto.getValue()) - Float.valueOf(requiredConcept.getValue()))));
						reasoningMap.get(c).add(relationto.getConceptByConceptTo());
						
						if(firstTime)
						{
							for(Integer r : tempResult.keySet())
								if(count < r)
									count = r;
							
						//	count++;
						}
						
						if(!firstTime)
							count++;
							
						if(tempResult.get(count) == null)
						{
							tempResult.put(count, putMap);
						}
						else 
						{
							tempResult.get(count).putAll(putMap);
						}
				

						List<Concept> conceptList = new ArrayList<Concept>();
						conceptList.add(relationto.getConceptByConceptTo());

						List<Float> floatList = new ArrayList<Float>();
						floatList.add(Float.valueOf(tempResult.get(count).get(relationto.getConceptByConceptTo())));

						Map<Integer, Map<Concept, String>> returnR = generateConcepts(conceptList, floatList, userprofile, true, count);
						for(Integer r : returnR.keySet())
						{
							returnR.get(r).putAll(tempResult.get(count));
						}
						
						for(Integer r : returnR.keySet())
						{
							if(tempResult.containsKey(r))
								tempResult.get(r).putAll(returnR.get(r));
							else
							{
								tempResult.put(r,returnR.get(r));
							}
							
							if(count < r)
								count = r;
						}

//						tempResult.putAll(generateConcepts(conceptList, floatList, userprofile, true, firstTime ? count : ++count));

							

						firstTime = false;
					}
				}
				else 
				{

					putMap.put(relationto.getConceptByConceptTo(), relationto.getValue().toString());
					reasoningMap.get(c).add(relationto.getConceptByConceptTo());
					
//					if(firstTime)
//					{
//						for(Integer r : tempResult.keySet())
//							if(count < r)
//								count = r;
//					}
					
					
					if(!firstTime)
					{
						if(count < globalCount)
							count = globalCount;
						count++;
					}
					
					if(tempResult.get(count) == null)
					{
						tempResult.put(count, putMap);
					}
					else 
					{
						tempResult.get(count).putAll(putMap);
					}
					
					
//					if(!firstTime)
//					{
//						for(Conceptrelation ccr : copyMap.get(copyCount).keySet())
//						{
//							tempResult.get(count).put(ccr, copyMap.get(copyCount).get(ccr));
//						}
//					}
//					else
//					{
////						copyMap.putAll(tempResult);
//						copyMap.put(count, new LinkedHashMap<Conceptrelation, String>());
//						for(Conceptrelation ccr : tempResult.get(count).keySet())
//						{
//							copyMap.get(count).put(ccr, tempResult.get(copyCount).get(ccr));
//						}
//					}

					List<Concept> conceptList = new ArrayList<Concept>();
					conceptList.add(relationto.getConceptByConceptTo());

					List<Float> floatList = new ArrayList<Float>();

					try {
						floatList.add(Float.valueOf(tempResult.get(count).get(relationto.getConceptByConceptTo())));
					}
					catch (Exception ne) {
						floatList.add(0.0f);
					}
					Map<Integer, Map<Concept, String>> returnR = generateConcepts(conceptList, floatList, userprofile, true,count);
					for(Integer r : returnR.keySet())
					{
						returnR.get(r).putAll(tempResult.get(count));
					}
					
					for(Integer r : returnR.keySet())
					{
						if(tempResult.containsKey(r))
							tempResult.get(r).putAll(returnR.get(r));
						else
							tempResult.put(r,returnR.get(r));
						
						if(count < r)
							count = r;
					}
					
				//	tempResult.putAll(generateConcepts(conceptList, floatList, userprofile, true, firstTime ? count : ++count));
					firstTime = false;
				}
			}
			//
			// for (;;) {
			// int index = (int) (Math.random() * Constants.RANDOM_RANGE) %
			// toConcepts.size();
			//
			// Conceptrelation relationto = toConcepts.get(index);
			//
			// LearnerConcept requiredConcept =
			// learnerConceptDAO.findConceptInProfile(userprofile.getId(),
			// relationto.getConceptByConceptTo().getId());
			//
			// if (requiredConcept != null) {
			// if (Float.valueOf(requiredConcept.getValue()) <
			// Float.valueOf(relationto.getValue())) {
			// tempResult.put(relationto,
			// String.valueOf((Float.valueOf(relationto.getValue()) -
			// Float.valueOf(requiredConcept.getValue()))));
			//
			// List<Concept> conceptList = new ArrayList<Concept>();
			// conceptList.add(relationto.getConceptByConceptTo());
			//
			// List<Float> floatList = new ArrayList<Float>();
			// floatList.add(Float.valueOf(tempResult.get(relationto)));
			//
			// tempResult.putAll(generateConcepts(conceptList, floatList,
			// userprofile, true));
			//
			// break;
			// }
			// continue;
			// }
			//
			// tempResult.put(relationto, relationto.getValue().toString());
			//
			// List<Concept> conceptList = new ArrayList<Concept>();
			// conceptList.add(relationto.getConceptByConceptTo());
			//
			// List<Float> floatList = new ArrayList<Float>();
			// floatList.add(Float.valueOf(tempResult.get(relationto)));
			//
			// tempResult.putAll(generateConcepts(conceptList, floatList,
			// userprofile, true));
			//
			// break;
			// }
		}
		/*
		 * Finish type-of relation
		 */
	}
	//
	// public void ParallelRelation(Map<Relationto, String> tempResult,
	// Concept c, String level, float percentage,
	// Userprofile userprofile)
	// {
	// /*
	// * Parallel Relation
	// */
	// Relationfrom typeRelation =
	// relationfromDAO.findRelationOfConcept(c.getId(),
	// Constants.PARALLEL_ID);
	//
	// if (typeRelation != null)
	// {
	// /*
	// * DO ordering of all the sub relations
	// */
	//
	// List<Relationto> toConcepts = new ArrayList<Relationto>();
	// toConcepts.addAll(relationtoDAO.findRelationsToOfLevel(typeRelation.getId(),
	// level));
	//
	// for (;;)
	// {
	// int index = (int) (Math.random() * Constants.RANDOM_RANGE) %
	// toConcepts.size();
	//
	// Relationto relationto = toConcepts.get(index);
	//
	// Userconcept requiredConcept =
	// userconceptDAO.findConceptInProfile(userprofile.getId(),
	// relationto.getConcept()
	// .getId(), relationto.getLevelTo());
	//
	// if (requiredConcept != null)
	// {
	// if (Float.valueOf(requiredConcept.getValue()) <
	// relationto.getValue())
	// {
	// tempResult.put(relationto, String.valueOf((relationto.getValue() -
	// Float.valueOf(requiredConcept
	// .getValue()))));
	//
	// // Map<Relationto, String> tempMap = new
	// // LinkedHashMap<Relationto, String>();
	// // tempMap.put(relationto, tempResult.get(relationto));
	// // //
	// // RequiredRelation(tempMap, relationto.getConcept(),
	// // relationto.getLevelTo(), Float.valueOf(tempResult
	// // .get(relationto)), userprofile);
	// //
	// // tempResult.putAll(tempMap);
	//
	// List<Concept> conceptList = new ArrayList<Concept>();
	// conceptList.add(relationto.getConcept());
	//
	// tempResult.putAll(generateConcepts(conceptList,
	// relationto.getLevelTo(), Float.valueOf(tempResult
	// .get(relationto)), userprofile,true));
	//
	// break;
	// }
	// continue;
	// }
	//
	// tempResult.put(relationto, relationto.getValue().toString());
	//
	// // Map<Relationto, String> tempMap = new
	// // LinkedHashMap<Relationto, String>();
	// // tempMap.put(relationto, tempResult.get(relationto));
	// // //
	// // RequiredRelation(tempMap, relationto.getConcept(),
	// // relationto.getLevelTo(), Float.valueOf(tempResult
	// // .get(relationto)), userprofile);
	// //
	// // tempResult.putAll(tempMap);
	//
	// List<Concept> conceptList = new ArrayList<Concept>();
	// conceptList.add(relationto.getConcept());
	//
	// tempResult.putAll(generateConcepts(conceptList,
	// relationto.getLevelTo(), Float
	// .valueOf(tempResult.get(relationto)), userprofile,true));
	//
	// break;
	// }
	// }
	// /*
	// * Finish Parallel relation
	// */
	// }

	public Map<Concept, List<Concept>> getReasoningMap() {
		return reasoningMap;
	}

	public void setReasoningMap(Map<Concept, List<Concept>> reasoningMap) {
		this.reasoningMap = reasoningMap;
	}

}
