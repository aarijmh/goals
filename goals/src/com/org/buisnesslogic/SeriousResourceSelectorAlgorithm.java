package com.org.buisnesslogic;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import com.org.sg.POJO.action.Concept;
import com.org.sg.POJO.action.ConceptPr;
import com.org.sg.POJO.action.Gameresource;
import com.org.sg.POJO.action.Learner;
import com.org.sg.POJO.action.PedagogicalGame;
import com.org.sg.POJO.action.Pedagogicalresource;

public class SeriousResourceSelectorAlgorithm {

	// private GameresourceDAO gameresourceDAO;

	public SeriousResourceSelectorAlgorithm() {
		// gameresourceDAO = new GameresourceDAO();
	}

	public Map<Concept, Map<Pedagogicalresource, Gameresource>> generateSeriousResource(
			Map<Concept, Map<ConceptPr, Map<String, String>>> pedaResources,
			Learner profile) {
		Map<Concept, Map<Pedagogicalresource, Gameresource>> returnMap = new LinkedHashMap<Concept, Map<Pedagogicalresource, Gameresource>>();

		for (Iterator<Concept> iterator = pedaResources.keySet()
				.iterator(); iterator.hasNext();) {
			Concept relationto = iterator.next();

			Map<ConceptPr, Map<String, String>> pedagogicalResource = pedaResources
					.get(relationto);

			if (pedagogicalResource.keySet().size() > 0) {
				for (ConceptPr conceptPr : pedagogicalResource.keySet()) {
					Map<Pedagogicalresource, Gameresource> tempMap = new LinkedHashMap<Pedagogicalresource, Gameresource>();

					if (conceptPr.getPedagogicalresource()
							.getPedagogicalGames() != null
							&& conceptPr.getPedagogicalresource()
									.getPedagogicalGames().size() > 0) {
						int index = ((Double) (Math.random() * 100)).intValue()
								% conceptPr.getPedagogicalresource()
										.getPedagogicalGames().size();
						tempMap.put(
								conceptPr.getPedagogicalresource(),
								((PedagogicalGame) (conceptPr
										.getPedagogicalresource()
										.getPedagogicalGames().toArray()[index]))
										.getGameresource());
					} else {
						tempMap.put(conceptPr.getPedagogicalresource(), null);
					}

					returnMap.put(relationto, tempMap);
				}
			} else {
				returnMap.put(relationto,
						new LinkedHashMap<Pedagogicalresource, Gameresource>());
			}
		}

		return returnMap;
	}
}
