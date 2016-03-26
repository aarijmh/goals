package com.org.sg.UtilityClasses;

import java.io.Serializable;
import java.util.Map;

import com.org.sg.POJO.action.Concept;
import com.org.sg.POJO.action.ConceptPr;
import com.org.sg.POJO.action.Gameresource;
import com.org.sg.POJO.action.Pedagogicalresource;

public class ScenarioResults implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6412026287957773084L;
	

	private Map<Integer,Map<Concept, String>> conceptResult;
	private Map<Integer, Map<Concept, Map<ConceptPr, Map<String, String>>>>  pedagogicalResult;
	private Map<Integer,Map<Concept, Map<Pedagogicalresource,Gameresource>>> gameResult;

	
	
	public Map<Integer, Map<Concept, Map<ConceptPr, Map<String, String>>>> getPedagogicalResult() {
		return pedagogicalResult;
	}
	public void setPedagogicalResult(
			Map<Integer, Map<Concept, Map<ConceptPr, Map<String, String>>>> pedagogicalResult) {
		this.pedagogicalResult = pedagogicalResult;
	}
	public Map<Integer,Map<Concept, Map<Pedagogicalresource,Gameresource>>> getGameResult() {
		return gameResult;
	}
	public void setGameResult(
			Map<Integer,Map<Concept, Map<Pedagogicalresource,Gameresource>>> gameResult) {
		this.gameResult = gameResult;
	}

	public Map<Integer, Map<Concept, String>> getConceptResult() {
		return conceptResult;
	}
	public void setConceptResult(Map<Integer, Map<Concept, String>> conceptResult) {
		this.conceptResult = conceptResult;
	}

}
