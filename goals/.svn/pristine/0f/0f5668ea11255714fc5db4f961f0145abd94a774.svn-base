package com.org.buisnesslogic.ActionHandlers;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.org.buisnesslogic.ScenarioGenerator;
import com.org.coursegenrator.utilities.Constants;
import com.org.sg.DAO.ProjectDAO;
import com.org.sg.POJO.action.Concept;
import com.org.sg.POJO.action.ConceptPr;
import com.org.sg.POJO.action.Conceptrelation;
import com.org.sg.POJO.action.Gameresource;
import com.org.sg.POJO.action.PedagogicalGame;
import com.org.sg.POJO.action.Pedagogicalresource;
import com.org.sg.POJO.action.Project;
import com.org.sg.Utility.action.Scenario;
import com.org.sg.UtilityClasses.ObjectGraph;
import com.org.sg.UtilityClasses.ScenarioResults;

public class ScenarioActionHandler extends AbstractActionHandler {

	Scenario scenario;

	public ScenarioActionHandler(Scenario scenario) {
		this.scenario = scenario;
	}

	@Override
	public void processing() {
		if (Constants.GENERATE_SCENARIO.equalsIgnoreCase(scenario.getAction())) {
			generateScenario();
		}

	}

	@SuppressWarnings("unchecked")
	public void generateScenario() {
		Gson gson = new Gson();

		List<Map<String, String>> jsoned = new ArrayList<Map<String, String>>();
		jsoned = gson.fromJson(scenario.getJson(), List.class);

		List<Integer> cList = new ArrayList<Integer>();
		List<String> vList = new ArrayList<String>();

		for (Map<String, String> map : jsoned) {
			cList.add(Integer.parseInt(map.get("concept")));
			vList.add(map.get("values"));
		}

		ScenarioGenerator generator = new ScenarioGenerator(cList, vList,
				scenario.getLearnerId(), scenario.getPresentationId());
		generator.generateScenario();

		Map<String, Object> responseMap = new LinkedHashMap<String, Object>();
		responseMap.put("messages", scenario.getErrors());
		responseMap.put("html",
				generator.getTextualScenario(generator.getResults(), 1));
		responseMap.put("data", generateData(generator.getResults()));
		scenario.setJson(gson.toJson(responseMap));

	}

	public String generateData(ScenarioResults scenarioResult) {
		ProjectDAO projectDAO = new ProjectDAO();
		Project project = projectDAO.findById(scenario.getProjectId());

		List<ObjectGraph> graphs = new ArrayList<ObjectGraph>();
		List<Concept> cList = new ArrayList<Concept>();
		cList.addAll(project.getConcepts());

		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		List<Map<String, Object>> conceptList = new ArrayList<Map<String, Object>>();

		for (Concept c : cList) {
			Map<String, Object> object = new LinkedHashMap<String, Object>();
			object.put("id", c.getId());
			object.put("type", 1);
			object.put("name", c.getName());
			object.put("desc", c.getDescription());
			ObjectGraph og = new ObjectGraph();
			og.setId(c.getId());
			og.setType(1);
			og.setName(c.getName());
			og.setDescription(c.getDescription());

			if (scenarioResult.getConceptResult().get(1).containsKey(c)) {
				object.put("selected", true);
				og.setSelected(true);
			}
			graphs.add(og);

			conceptList.add(object);
		}

		List<Map<String, Object>> crList = new ArrayList<Map<String, Object>>();

		for (Concept c : cList) {
			for (Conceptrelation cr : c.getConceptrelationsForConceptFrom()) {
				Map<String, Object> object = new LinkedHashMap<String, Object>();

				ObjectGraph og = new ObjectGraph();
				og.setId(cr.getId());
				og.setChild(cr.getConceptByConceptTo().getId());
				og.setChildName(cr.getConceptByConceptTo().getName());
				og.setType(4);
				og.setName(cr.toString());
				og.setParent(c.getId());
				og.setParentName(c.getName());
				try {
					og.setRelationType(cr.getRelation().getId());
				} catch (NullPointerException ne) {
					og.setRelationType(Constants.DEFAULT_RELATION_TYPE);
				}

				object.put("id", cr.getId());
				object.put("child", cr.getConceptByConceptTo().getId());
				object.put("childName", cr.getConceptByConceptTo().getName());
				object.put("parent", c.getId());
				object.put("parentName", c.getName());
				object.put("type", 4);
				object.put("name", cr.toString());
				try {
					object.put("relType", cr.getRelation().getId());
				} catch (NullPointerException e) {
					og.setRelationType(Constants.DEFAULT_RELATION_TYPE);
				}

				crList.add(object);

				graphs.add(og);

			}
		}

		List<Map<String, Object>> prList = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> cprList = new ArrayList<Map<String, Object>>();

		List<Pedagogicalresource> pedList = new ArrayList<Pedagogicalresource>(
				project.getPedagogicalresources());

		for (Pedagogicalresource p : pedList) {
			ObjectGraph og = new ObjectGraph();
			og.setId(p.getId());
			og.setType(2);
			og.setName(p.getName());
			og.setDescription(p.getDescription());

			Map<String, Object> object = new LinkedHashMap<String, Object>();
			object.put("id", p.getId());
			object.put("type", 2);
			object.put("name", p.getName());
			object.put("desc", p.getDescription());

			prList.add(object);
			graphs.add(og);

			for (ConceptPr cpr : p.getConceptPrs()) {
				ObjectGraph ogr = new ObjectGraph();
				ogr.setId(cpr.getId());
				ogr.setChild(cpr.getPedagogicalresource().getId());
				ogr.setChildName(cpr.getPedagogicalresource().getName());
				ogr.setType(5);
				ogr.setName(cpr.toString());
				ogr.setParent(cpr.getConcept().getId());
				ogr.setParentName(cpr.getConcept().getName());

				if (scenarioResult.getPedagogicalResult().get(1)
						.containsKey(cpr.getConcept())) {
					if (scenarioResult.getPedagogicalResult().get(1)
							.get(cpr.getConcept()).containsKey(cpr)) {
						object.put("selected", true);
						og.setSelected(true);
					}
				}

				graphs.add(ogr);

				Map<String, Object> co = new LinkedHashMap<String, Object>();
				co.put("id", cpr.getId());
				co.put("child", p.getId());
				co.put("childName", p.getName());
				co.put("parent", cpr.getConcept().getId());
				co.put("parentName", cpr.getConcept().getName());
				co.put("type", 5);
				co.put("name", cpr.toString());

				cprList.add(co);
			}
		}

		List<Map<String, Object>> grList = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> gprList = new ArrayList<Map<String, Object>>();

		List<Gameresource> gaList = new ArrayList<Gameresource>(
				project.getGameresources());

		for (Gameresource gr : gaList) {
			ObjectGraph og = new ObjectGraph();
			og.setId(gr.getId());
			og.setType(3);
			og.setName(gr.getName());
			og.setDescription(gr.getValue());

			Map<String, Object> object = new LinkedHashMap<String, Object>();
			object.put("id", gr.getId());
			object.put("type", 3);
			object.put("name", gr.getName());
			object.put("desc", gr.getValue());

			if (scenarioResult.getPedagogicalResult().containsKey(gr.getId())) {
				object.put("selected", true);
				og.setSelected(true);
			}

			graphs.add(og);
			grList.add(object);

			for (PedagogicalGame cpr : gr.getPedagogicalGames()) {
				ObjectGraph ogr = new ObjectGraph();
				ogr.setId(cpr.getId());
				ogr.setChild(cpr.getGameresource().getId());
				ogr.setChildName(cpr.getGameresource().getName());
				ogr.setType(6);
				ogr.setName(cpr.toString());
				ogr.setParent(cpr.getPedagogicalresource().getId());
				ogr.setParentName(cpr.getPedagogicalresource().getName());


				graphs.add(ogr);

				Map<String, Object> co = new LinkedHashMap<String, Object>();
				co.put("id", cpr.getId());
				co.put("child", gr.getId());
				co.put("childName", gr.getName());
				co.put("parent", cpr.getPedagogicalresource().getId());
				co.put("parentName", cpr.getPedagogicalresource().getName());
				co.put("type", 6);
				co.put("name", cpr.toString());

				gprList.add(co);
			}
		}

		StringBuilder builder = new StringBuilder();
		builder.append("{\"data\":").append(gson.toJson(graphs)).append("}");
		// concept.setJsonObject(builder.toString());

		Map<String, List<Map<String, Object>>> tempObject = new LinkedHashMap<String, List<Map<String, Object>>>();
		tempObject.put("concept", conceptList);
		tempObject.put("cr", crList);
		tempObject.put("pr", prList);
		tempObject.put("cpr", cprList);
		tempObject.put("gr", grList);
		tempObject.put("gpr", gprList);

		return gson.toJson(tempObject);
	}

}
