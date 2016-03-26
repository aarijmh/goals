package com.org.buisnesslogic.ActionHandlers;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.org.coursegenrator.utilities.Constants;
import com.org.sg.DAO.CPropertyDAO;
import com.org.sg.DAO.ConceptDAO;
import com.org.sg.DAO.ConceptrelationDAO;
import com.org.sg.DAO.FunctionsDAO;
import com.org.sg.DAO.LearnerprojectDAO;
import com.org.sg.DAO.PresentationDAO;
import com.org.sg.DAO.ProjectDAO;
import com.org.sg.DAO.RelationDAO;
import com.org.sg.POJO.action.CProperty;
import com.org.sg.POJO.action.Concept;
import com.org.sg.POJO.action.ConceptPr;
import com.org.sg.POJO.action.Conceptrelation;
import com.org.sg.POJO.action.Functions;
import com.org.sg.POJO.action.Gameresource;
import com.org.sg.POJO.action.PedagogicalGame;
import com.org.sg.POJO.action.Pedagogicalresource;
import com.org.sg.POJO.action.Project;
import com.org.sg.POJO.action.Relation;
import com.org.sg.UtilityClasses.ObjectGraph;

public class ConceptActionHandler extends AbstractActionHandler {

	Concept concept;

	public ConceptActionHandler(Concept con) {
		this.concept = con;
	}

	@Override
	public void processing() {

		if (concept.getAction().equalsIgnoreCase(Constants.LOGGED))
			show();
		else if (concept.getAction().equalsIgnoreCase(Constants.SHOW_ADD_EDIT))
			showAddEdit();
		else if (concept.getAction().equalsIgnoreCase(Constants.ADD_EDIT))
			addEdit();
		else if (concept.getAction().equalsIgnoreCase(Constants.DELETE))
			delete();
		else if (concept.getAction().equalsIgnoreCase(Constants.SHOW_ADD_EDIT_CONCEPT))
			showAddEditConcept();
		else if (concept.getAction().equalsIgnoreCase(Constants.SHOW_ADD_EDIT_CONCEPT_JSON))
			showAddEditConceptJson();
		else if (concept.getAction().equalsIgnoreCase(Constants.SHOW_JSON))
			showJson();
		else if (concept.getAction().equalsIgnoreCase(Constants.SHOW_PROPERTIES))
			showProperties();
		else if (concept.getAction().equalsIgnoreCase(Constants.SHOW_ADD_EDIT_RELATION))
			showAddEditRelation();
		else if (concept.getAction().equalsIgnoreCase(Constants.ADD_EDIT_RELATION))
			addEditRelation();
		else if (concept.getAction().equalsIgnoreCase(Constants.SHOW_JSON_RELATION))
			showJsonRelation();
		else if (concept.getAction().equalsIgnoreCase(Constants.SHOW_GENERATOR))
			showGenerator();
		else if (concept.getAction().equalsIgnoreCase(Constants.SHOW_JSON_GENERATOR))
			showJsonGenerator();
		else if (concept.getAction().equalsIgnoreCase(Constants.DELETE))
			delete();
		else if (concept.getAction().equalsIgnoreCase(Constants.DELETE_RELATION))
			deleteRelation();
		else if (concept.getAction().equalsIgnoreCase(Constants.CHECK_NAME))
			checkName();

	}
	
	

	public void show() {

	}
	
	public void checkName()
	{
		ConceptDAO conceptDAO = new ConceptDAO();
		Gson gson = new Gson();
		
		Map<String, Object> responseMap = new LinkedHashMap<String, Object>();
		responseMap.put("messages", conceptDAO.checkName(concept.getId(), concept.getProjectId(), concept.getName()));
		
		concept.setJson(gson.toJson(responseMap));
		
	}

	public void showAddEdit() {
		ConceptDAO conceptDAO = new ConceptDAO();

		if (concept.getId() != null && !concept.getId().equals(0)) {
			Concept p = conceptDAO.findById(concept.getId());
			concept.setName(p.getName().replaceAll("'", "\'"));
			concept.setDescription(p.getDescription().replaceAll("'", "\'"));
			concept.setId(p.getId());
		}

		concept.setConceptList(conceptDAO.findOfProject(concept.getProjectId()));
	}

	public void showJson() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Map<String, List<Map<String, Object>>> myMap = new LinkedHashMap<String, List<Map<String, Object>>>();

		List<Map<String, Object>> metaDataList = new ArrayList<Map<String, Object>>();

		ConceptDAO conceptDAO = new ConceptDAO();
		List<Concept> conceptList = conceptDAO.findOfProject(concept.getProjectId());

		RelationDAO relationDAO = new RelationDAO();
		List<Relation> relationList = relationDAO.findAll();

		FunctionsDAO functionsDAO = new FunctionsDAO();
		List<Functions> functionsList = functionsDAO.findAll();

		Map<String, Object> nameMap = new LinkedHashMap<String, Object>();
		nameMap.put("name", "select");
		nameMap.put("label", "Select");
		nameMap.put("datatype", "html");
		nameMap.put("editable", false);
		metaDataList.add(nameMap);

		Map<String, Object> conceptNameMap = new LinkedHashMap<String, Object>();
		conceptNameMap.put("name", "concept");
		conceptNameMap.put("label", "Concepts");
		conceptNameMap.put("datatype", "string");
		conceptNameMap.put("editable", true);

		Map<String, String> conceptValueMap = new LinkedHashMap<String, String>();
		conceptValueMap.put("0", "Select a concept");
		for (Concept c : conceptList) {
			conceptValueMap.put(c.getId().toString(), c.getName());
		}
		conceptNameMap.put("values", conceptValueMap);
		metaDataList.add(conceptNameMap);

		Map<String, Object> relationNameMap = new LinkedHashMap<String, Object>();
		relationNameMap.put("name", "relation");
		relationNameMap.put("label", "Relations");
		relationNameMap.put("datatype", "string");
		relationNameMap.put("editable", true);

		Map<String, String> relationValueMap = new LinkedHashMap<String, String>();
		relationValueMap.put("0", "Select a relation");
		for (Relation c : relationList) {
			relationValueMap.put(c.getId().toString(), c.getName());
		}
		relationNameMap.put("values", relationValueMap);
		metaDataList.add(relationNameMap);

		Map<String, Object> functionsNameMap = new LinkedHashMap<String, Object>();
		functionsNameMap.put("name", "functions");
		functionsNameMap.put("label", "Functions");
		functionsNameMap.put("datatype", "string");
		functionsNameMap.put("editable", true);

		Map<String, String> functionsValueMap = new LinkedHashMap<String, String>();
		functionsValueMap.put("0", "Select a function");

		for (Functions c : functionsList) {
			functionsValueMap.put(c.getId().toString(), c.getFunc());
		}
		functionsNameMap.put("values", functionsValueMap);
		metaDataList.add(functionsNameMap);

		Map<String, Object> valuesNameMap = new LinkedHashMap<String, Object>();
		valuesNameMap.put("name", "values");
		valuesNameMap.put("label", "Values");
		valuesNameMap.put("datatype", "string");
		valuesNameMap.put("editable", true);

		Map<Integer, String> valuesValueMap = new LinkedHashMap<Integer, String>();
		valuesValueMap.put(0, "Select a value");
		valuesValueMap.put(10, "10");
		valuesValueMap.put(20, "20");
		valuesValueMap.put(30, "30");
		valuesValueMap.put(40, "40");
		valuesValueMap.put(50, "50");
		valuesValueMap.put(60, "60");
		valuesValueMap.put(70, "70");
		valuesValueMap.put(80, "80");
		valuesValueMap.put(90, "90");
		valuesValueMap.put(100, "100");

		valuesNameMap.put("values", valuesValueMap);
		metaDataList.add(valuesNameMap);

		Map<String, Object> actionMap = new LinkedHashMap<String, Object>();
		actionMap.put("name", "action");
		actionMap.put("label", "Action");
		actionMap.put("datatype", "html");
		actionMap.put("editable", false);
		metaDataList.add(actionMap);

		myMap.put("metadata", metaDataList);

		if (this.concept.getId() != null && !this.concept.getId().equals(0)) {
			ConceptrelationDAO conceptrelationDAO = new ConceptrelationDAO();
			List<Conceptrelation> conceptrelations = conceptrelationDAO.findAllRelationOfConcept(this.concept.getId());

			List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();

			for (Conceptrelation cr : conceptrelations) {
				Map<String, Object> dataMap = new LinkedHashMap<String, Object>();
				dataMap.put("id", cr.getId());
				Map<String, String> dataValueMap = new LinkedHashMap<String, String>();
				dataValueMap.put("select", "<input type=\"checkbox\" name=\"conceptSelect\" value=\"" + cr.getId().toString() + "\">");
				dataValueMap.put("concept", cr.getConceptByConceptTo().getId().toString());
				dataValueMap.put("relation", cr.getRelation().getId().toString());
				dataValueMap.put("functions", cr.getFunctions().getId().toString());
				dataValueMap.put("values", cr.getValue());
				dataValueMap.put("action", " ");
				dataMap.put("values", dataValueMap);
				dataList.add(dataMap);
			}
			myMap.put("data", dataList);
		}

		concept.setJson(gson.toJson(myMap));
	}

	public void showProperties() {
		Gson gson = new Gson();
		Map<String, List<Map<String, Object>>> myMap = new LinkedHashMap<String, List<Map<String, Object>>>();
		List<Map<String, Object>> metaDataList = new ArrayList<Map<String, Object>>();

		Map<String, Object> selectMap = new LinkedHashMap<String, Object>();
		selectMap.put("name", "select");
		selectMap.put("label", "Select");
		selectMap.put("datatype", "html");
		selectMap.put("editable", false);
		metaDataList.add(selectMap);

		Map<String, Object> nameMap = new LinkedHashMap<String, Object>();
		nameMap.put("name", "name");
		nameMap.put("label", "Name");
		nameMap.put("datatype", "text");
		nameMap.put("editable", true);
		metaDataList.add(nameMap);

		Map<String, Object> propertiesMap = new LinkedHashMap<String, Object>();
		propertiesMap.put("name", "value");
		propertiesMap.put("label", "Values");
		propertiesMap.put("datatype", "text");
		propertiesMap.put("editable", true);
		metaDataList.add(propertiesMap);

		Map<String, Object> actionMap = new LinkedHashMap<String, Object>();
		actionMap.put("name", "action");
		actionMap.put("label", "Action");
		actionMap.put("datatype", "html");
		actionMap.put("editable", false);
		metaDataList.add(actionMap);

		myMap.put("metadata", metaDataList);

		if (this.concept.getId() != null && !this.concept.getId().equals(0)) {
			CPropertyDAO cPropertyDAO = new CPropertyDAO();
			List<CProperty> cProperties = cPropertyDAO.findOfConcept(this.concept.getId());

			List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();

			for (CProperty cr : cProperties) {
				Map<String, Object> dataMap = new LinkedHashMap<String, Object>();
				dataMap.put("id", cr.getId());

				Map<String, String> dataValueMap = new LinkedHashMap<String, String>();
				dataValueMap.put("select", "<input type=\"checkbox\" name=\"conceptPropertySelect\" value=\"" + cr.getId().toString() + "\">");
				dataValueMap.put("name", cr.getName());
				dataValueMap.put("value", cr.getValue());
				dataValueMap.put("action", "");

				dataMap.put("values", dataValueMap);

				dataList.add(dataMap);
			}
			myMap.put("data", dataList);
		}

		concept.setJson(gson.toJson(myMap));
	}

	@SuppressWarnings("unchecked")
	public void addEdit() {

		ConceptDAO conceptDAO = new ConceptDAO();
		ConceptrelationDAO conceptrelationDAO = new ConceptrelationDAO();
		CPropertyDAO cPropertyDAO = new CPropertyDAO();
		RelationDAO relationDAO = new RelationDAO();
		FunctionsDAO functionsDAO = new FunctionsDAO();
		ProjectDAO projectDAO = new ProjectDAO();

		Gson gson = new Gson();

		List<Map<String, String>> jsoned = new ArrayList<Map<String, String>>();
		jsoned = gson.fromJson(concept.getJson(), List.class);

		List<Map<String, String>> properties = new ArrayList<Map<String, String>>();
		properties = gson.fromJson(concept.getPrJson(), List.class);

		Concept con = new Concept();

		if (this.concept.getId() != null && !this.concept.getId().equals(0)) {
			con = conceptDAO.findById(concept.getId());
			conceptrelationDAO.deleteOfConcept(this.concept.getId());
			cPropertyDAO.deleteOfConcept(this.concept.getId());
		}

		con.setName(concept.getName());
		con.setDescription(concept.getDescription());
		con.setProject(projectDAO.findById(concept.getProjectId()));
		conceptDAO.save(con);

		for (Map<String, String> maps : jsoned) {
			Integer id = Integer.valueOf(maps.get("concept"));
			if (id != null && !id.equals(0)) {
				Conceptrelation cr = new Conceptrelation();
				cr.setConceptByConceptFrom(con);
				cr.setConceptByConceptTo(conceptDAO.findById(id));
				cr.setRelation(relationDAO.findById(Integer.valueOf(maps.get("relation"))));
				cr.setFunctions(functionsDAO.findById(Integer.valueOf(maps.get("functions"))));
				cr.setValue(maps.get("values"));

				conceptrelationDAO.save(cr);
			}
		}

		for (Map<String, String> maps : properties) {
			String name = maps.get("name");
			if (name != null && !name.trim().equalsIgnoreCase("")) {
				CProperty cProperty = new CProperty();
				cProperty.setConcept(con);
				cProperty.setName(name);
				cProperty.setValue(maps.get("value"));

				cPropertyDAO.save(cProperty);
			}
		}

		concept.getErrors().add("Concept Saved Successfully");
		Map<String, Object> responseMap = new LinkedHashMap<String, Object>();
		responseMap.put("messages", concept.getErrors());
		responseMap.put("id", con.getId());
		concept.setJson(gson.toJson(responseMap));
	}

	@SuppressWarnings("unchecked")
	public void  addEditRelation() {
		
		ConceptDAO conceptDAO = new ConceptDAO();
		ConceptrelationDAO conceptrelationDAO = new ConceptrelationDAO();
		RelationDAO relationDAO = new RelationDAO();
		FunctionsDAO functionsDAO = new FunctionsDAO();

		Gson gson = new Gson();

		List<Map<String, String>> jsoned = new ArrayList<Map<String, String>>();
		jsoned = gson.fromJson(concept.getJson(), List.class);

		Conceptrelation cr = new Conceptrelation();
		
		if(concept.getRelationId() != null && !concept.getRelationId().equals(0))
		{
			cr = conceptrelationDAO.findById(concept.getRelationId());
		}
		


		for (Map<String, String> maps : jsoned) {
			Integer id = Integer.valueOf(maps.get("conceptFrom"));
			Integer toId = Integer.valueOf(maps.get("conceptTo"));
			
			Conceptrelation temp = conceptrelationDAO.findSimilarRelation(id, toId);
			
			if(temp != null && !temp.getId().equals(cr.getId()))
			{
				concept.getErrors().add("There is already a relation between these two concepts. \n");
				Map<String, Object> responseMap = new LinkedHashMap<String, Object>();
				responseMap.put("messages", concept.getErrors());
				responseMap.put("ErrorCode", 1);
				concept.setJson(gson.toJson(responseMap));
				return;
			}
			
			if (id != null && toId != null && !toId.equals(0) && !id.equals(0)) {
				cr.setConceptByConceptFrom(conceptDAO.findById(id));
				cr.setConceptByConceptTo(conceptDAO.findById(toId));
				cr.setRelation(relationDAO.findById(Integer.valueOf(maps.get("relation"))));
				cr.setFunctions(functionsDAO.findById(Integer.valueOf(maps.get("functions"))));
				cr.setValue(maps.get("values"));

				conceptrelationDAO.save(cr);
			}
		}


		concept.getErrors().add("Concept Relation Saved Successfully");
		Map<String, Object> responseMap = new LinkedHashMap<String, Object>();
		responseMap.put("messages", concept.getErrors());
		responseMap.put("relationId", cr.getId());
		responseMap.put("source", cr.getConceptByConceptFrom().getId());
		responseMap.put("target", cr.getConceptByConceptTo().getId());
		responseMap.put("name", cr.toString());
		responseMap.put("ErrorCode", 0);
		concept.setJson(gson.toJson(responseMap));
	}

	public void showAddEditRelation() {

	}

	public void showJsonRelation() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Map<String, List<Map<String, Object>>> myMap = new LinkedHashMap<String, List<Map<String, Object>>>();

		List<Map<String, Object>> metaDataList = new ArrayList<Map<String, Object>>();

		ConceptDAO conceptDAO = new ConceptDAO();
		List<Concept> conceptList = conceptDAO.findOfProject(concept.getProjectId());

		RelationDAO relationDAO = new RelationDAO();
		List<Relation> relationList = relationDAO.findAll();

		FunctionsDAO functionsDAO = new FunctionsDAO();
		List<Functions> functionsList = functionsDAO.findAll();

		Map<String, Object> conceptNameMap = new LinkedHashMap<String, Object>();
		conceptNameMap.put("name", "conceptFrom");
		conceptNameMap.put("label", "From Concept");
		conceptNameMap.put("datatype", "string");
		conceptNameMap.put("editable", true);

		Map<String, String> conceptValueMap = new LinkedHashMap<String, String>();
		conceptValueMap.put("0", "Select a concept");
		for (Concept c : conceptList) {
			conceptValueMap.put(c.getId().toString(), c.getName());
		}
		conceptNameMap.put("values", conceptValueMap);
		metaDataList.add(conceptNameMap);

		Map<String, Object> conceptFromNameMap = new LinkedHashMap<String, Object>();
		conceptFromNameMap.put("name", "conceptTo");
		conceptFromNameMap.put("label", "To Concept");
		conceptFromNameMap.put("datatype", "string");
		conceptFromNameMap.put("editable", true);

		conceptFromNameMap.put("values", conceptValueMap);
		metaDataList.add(conceptFromNameMap);

		Map<String, Object> relationNameMap = new LinkedHashMap<String, Object>();
		relationNameMap.put("name", "relation");
		relationNameMap.put("label", "Relations");
		relationNameMap.put("datatype", "string");
		relationNameMap.put("editable", true);

		Map<String, String> relationValueMap = new LinkedHashMap<String, String>();
		relationValueMap.put("0", "Select a relation");
		for (Relation c : relationList) {
			relationValueMap.put(c.getId().toString(), c.getName());
		}
		relationNameMap.put("values", relationValueMap);
		metaDataList.add(relationNameMap);

		Map<String, Object> functionsNameMap = new LinkedHashMap<String, Object>();
		functionsNameMap.put("name", "functions");
		functionsNameMap.put("label", "Functions");
		functionsNameMap.put("datatype", "string");
		functionsNameMap.put("editable", true);

		Map<String, String> functionsValueMap = new LinkedHashMap<String, String>();
		functionsValueMap.put("0", "Select a function");

		for (Functions c : functionsList) {
			functionsValueMap.put(c.getId().toString(), c.getFunc());
		}
		functionsNameMap.put("values", functionsValueMap);
		metaDataList.add(functionsNameMap);

		Map<String, Object> valuesNameMap = new LinkedHashMap<String, Object>();
		valuesNameMap.put("name", "values");
		valuesNameMap.put("label", "Values");
		valuesNameMap.put("datatype", "string");
		valuesNameMap.put("editable", true);

		Map<Integer, String> valuesValueMap = new LinkedHashMap<Integer, String>();
		valuesValueMap.put(0, "Select a value");
		valuesValueMap.put(10, "10");
		valuesValueMap.put(20, "20");
		valuesValueMap.put(30, "30");
		valuesValueMap.put(40, "40");
		valuesValueMap.put(50, "50");
		valuesValueMap.put(60, "60");
		valuesValueMap.put(70, "70");
		valuesValueMap.put(80, "80");
		valuesValueMap.put(90, "90");
		valuesValueMap.put(100, "100");

		valuesNameMap.put("values", valuesValueMap);
		metaDataList.add(valuesNameMap);



		myMap.put("metadata", metaDataList);

		Conceptrelation cr = new Conceptrelation();
		
		this.concept.setNewObject(false);
		
		if (this.concept.getRelationId() != null && !this.concept.getRelationId().equals(0)) {
			ConceptrelationDAO conceptrelationDAO = new ConceptrelationDAO();
			cr = conceptrelationDAO.findById(this.concept.getRelationId());
			this.concept.setNewObject(true);
		}
			
			List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();

			Map<String, Object> dataMap = new LinkedHashMap<String, Object>();
			dataMap.put("id", (concept.getNewObject() ? cr.getId().toString() : "0"));
			Map<String, String> dataValueMap = new LinkedHashMap<String, String>();
			dataValueMap.put("select", "<input type=\"checkbox\" name=\"conceptSelectCCR\" value=\"" +  (concept.getNewObject() ? cr.getId().toString() : "0") + "\">");
			dataValueMap.put("conceptTo", concept.getNewObject() ? cr.getConceptByConceptTo().getId().toString() : "0");
			dataValueMap.put("conceptFrom", concept.getNewObject() ? cr.getConceptByConceptFrom().getId().toString() : "0");
			try {
				dataValueMap.put("relation", concept.getNewObject() ? cr.getRelation().getId().toString(): "0");
			} catch (Exception e1) {
				dataValueMap.put("relation", concept.getNewObject() ? Constants.DEFAULT_RELATION_TYPE.toString(): "0");
			}
			try {
				dataValueMap.put("functions", concept.getNewObject() ? cr.getFunctions().getId().toString(): "0");
			} catch (Exception e) {
				dataValueMap.put("functions", concept.getNewObject() ? Constants.DEFAULT_FUNCTION_TYPE.toString(): "0");
			}
			dataValueMap.put("values", concept.getNewObject() ? cr.getValue(): "0");
			dataMap.put("values", dataValueMap);
			dataList.add(dataMap);

			myMap.put("data", dataList);
		

		concept.setJson(gson.toJson(myMap));
	}

	public void delete() {
		if (this.concept.getId() != null && !this.concept.getId().equals(0)) {
			ConceptDAO conceptDAO = new ConceptDAO(); 
			conceptDAO.delete(conceptDAO.findById(this.concept.getId()));
		}
	}
	
	public void deleteRelation() {
		if (this.concept.getRelationId() != null && !this.concept.getRelationId().equals(0)) {
			ConceptrelationDAO conceptDAO = new ConceptrelationDAO(); 
			conceptDAO.delete(conceptDAO.findById(this.concept.getRelationId()));
		}
	}

	public void showAddEditConcept() {
		
	}
	public void showAddEditConceptJson() {
		ProjectDAO projectDAO = new ProjectDAO();
		Project project = projectDAO.findById(concept.getProjectId());

		List<ObjectGraph> graphs = new ArrayList<ObjectGraph>();
		List<Concept> cList = new ArrayList<Concept>();
		cList.addAll(project.getConcepts());

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		List<Map<String,Object>> conceptList = new ArrayList<Map<String,Object>>();

		for (Concept c : cList) {
			Map<String,Object> object = new LinkedHashMap<String, Object>();
			object.put("id", c.getId());
			object.put("type", 1);
			object.put("name", c.getName());
			object.put("desc", c.getDescription());
			ObjectGraph og = new ObjectGraph();
			og.setId(c.getId());
			og.setType(1);
			og.setName(c.getName());
			og.setDescription(c.getDescription());
			graphs.add(og);
			
			conceptList.add(object);
		}
		
		List<Map<String,Object>> crList = new ArrayList<Map<String,Object>>();

		for (Concept c : cList) {
			for (Conceptrelation cr : c.getConceptrelationsForConceptFrom()) {
				Map<String,Object> object = new LinkedHashMap<String, Object>();
				
				ObjectGraph og = new ObjectGraph();
				og.setId(cr.getId());
				og.setChild(cr.getConceptByConceptTo().getId());
				og.setChildName(cr.getConceptByConceptTo().getName());
				og.setType(4);
				og.setName(cr.toString());
				og.setParent(c.getId());
				og.setParentName(c.getName());
				try
				{
					og.setRelationType(cr.getRelation().getId());
				}
				catch(NullPointerException ne)
				{
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

		List<Map<String,Object>> prList = new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> cprList = new ArrayList<Map<String,Object>>();
		
		List<Pedagogicalresource> pedList = new ArrayList<Pedagogicalresource>(project.getPedagogicalresources());

		for (Pedagogicalresource p : pedList) {
			ObjectGraph og = new ObjectGraph();
			og.setId(p.getId());
			og.setType(2);
			og.setName(p.getName());
			og.setDescription(p.getDescription());
			graphs.add(og);
			
			Map<String,Object> object = new LinkedHashMap<String, Object>();
			object.put("id", p.getId());
			object.put("type", 2);
			object.put("name", p.getName());
			object.put("desc", p.getDescription());
			prList.add(object);

			for (ConceptPr cpr : p.getConceptPrs()) {
				ObjectGraph ogr = new ObjectGraph();
				ogr.setId(cpr.getId());
				ogr.setChild(cpr.getPedagogicalresource().getId());
				ogr.setChildName(cpr.getPedagogicalresource().getName());
				ogr.setType(5);
				ogr.setName(cpr.toString());
				ogr.setParent(cpr.getConcept().getId());
				ogr.setParentName(cpr.getConcept().getName());

				graphs.add(ogr);
				
				Map<String,Object> co = new LinkedHashMap<String, Object>();
				co.put("id", cpr.getId());
				co.put("child",p.getId());
				co.put("childName", p.getName());
				co.put("parent", cpr.getConcept().getId());
				co.put("parentName", cpr.getConcept().getName());
				co.put("type", 5);
				co.put("name", cpr.toString());
				
				cprList.add(co);
			}
		}

		
		List<Map<String,Object>> grList = new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> gprList = new ArrayList<Map<String,Object>>();
		
		List<Gameresource> gaList = new ArrayList<Gameresource>(project.getGameresources());

		for (Gameresource gr : gaList) {
			ObjectGraph og = new ObjectGraph();
			og.setId(gr.getId());
			og.setType(3);
			og.setName(gr.getName());
			og.setDescription(gr.getValue());
			graphs.add(og);
			
			Map<String,Object> object = new LinkedHashMap<String, Object>();
			object.put("id", gr.getId());
			object.put("type", 3);
			object.put("name", gr.getName());
			object.put("desc", gr.getValue());
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
				
				
				Map<String,Object> co = new LinkedHashMap<String, Object>();
				co.put("id", cpr.getId());
				co.put("child",gr.getId());
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
		
		Map<String,List<Map<String,Object>>> tempObject = new LinkedHashMap<String, List<Map<String,Object>>>();
		tempObject.put("concept", conceptList);
		tempObject.put("cr", crList);
		tempObject.put("pr", prList);
		tempObject.put("cpr", cprList);
		tempObject.put("gr", grList);
		tempObject.put("gpr", gprList);
		
		
		
		concept.setJson(gson.toJson(tempObject));
		// System.out.println(gson.toJson(graphs));
	}
	
	public void showGenerator()
	{
		ConceptDAO conceptDAO = new ConceptDAO();
		PresentationDAO presentationDAO = new PresentationDAO();
		LearnerprojectDAO learnerDAO = new LearnerprojectDAO();
		
		concept.setLearnerList(learnerDAO.findLearnersOfProject(concept.getProjectId()));
		concept.setPresentationList(presentationDAO.findAllOfProject(concept.getProjectId()));
		concept.setConceptList(conceptDAO.findOfProject(concept.getProjectId()));
	}
	
	public void showJsonGenerator()
	{
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Map<String, List<Map<String, Object>>> myMap = new LinkedHashMap<String, List<Map<String, Object>>>();

		List<Map<String, Object>> metaDataList = new ArrayList<Map<String, Object>>();

		ConceptDAO conceptDAO = new ConceptDAO();
		List<Concept> conceptList = conceptDAO.findOfProject(concept.getProjectId());


		Map<String, Object> conceptNameMap = new LinkedHashMap<String, Object>();
		conceptNameMap.put("name", "concept");
		conceptNameMap.put("label", "Concepts");
		conceptNameMap.put("datatype", "string");
		conceptNameMap.put("editable", true);

		Map<String, String> conceptValueMap = new LinkedHashMap<String, String>();
		conceptValueMap.put("0", "Select a concept");
		for (Concept c : conceptList) {
			conceptValueMap.put(c.getId().toString(), c.getName());
		}
		conceptNameMap.put("values", conceptValueMap);
		metaDataList.add(conceptNameMap);


		Map<String, Object> valuesNameMap = new LinkedHashMap<String, Object>();
		valuesNameMap.put("name", "values");
		valuesNameMap.put("label", "Values");
		valuesNameMap.put("datatype", "string");
		valuesNameMap.put("editable", true);

		Map<Integer, String> valuesValueMap = new LinkedHashMap<Integer, String>();
		valuesValueMap.put(0, "Select a value");
		valuesValueMap.put(10, "10");
		valuesValueMap.put(20, "20");
		valuesValueMap.put(30, "30");
		valuesValueMap.put(40, "40");
		valuesValueMap.put(50, "50");
		valuesValueMap.put(60, "60");
		valuesValueMap.put(70, "70");
		valuesValueMap.put(80, "80");
		valuesValueMap.put(90, "90");
		valuesValueMap.put(100, "100");

		valuesNameMap.put("values", valuesValueMap);
		metaDataList.add(valuesNameMap);

		Map<String, Object> actionMap = new LinkedHashMap<String, Object>();
		actionMap.put("name", "action");
		actionMap.put("label", "Action");
		actionMap.put("datatype", "html");
		actionMap.put("editable", false);
		metaDataList.add(actionMap);

		myMap.put("metadata", metaDataList);
		
		concept.setJson(gson.toJson(myMap));
	}
}
