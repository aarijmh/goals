package com.org.buisnesslogic.ActionHandlers;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.org.coursegenrator.utilities.Constants;
import com.org.sg.DAO.ConceptDAO;
import com.org.sg.DAO.LearnerConceptDAO;
import com.org.sg.DAO.LearnerDAO;
import com.org.sg.DAO.LearnerprojectDAO;
import com.org.sg.DAO.ProjectDAO;
import com.org.sg.DAO.UserDAO;
import com.org.sg.POJO.action.Concept;
import com.org.sg.POJO.action.Learner;
import com.org.sg.POJO.action.LearnerConcept;
import com.org.sg.POJO.action.Learnerproject;

public class LearnerActionHandler extends AbstractActionHandler {

	Learner learner;

	public LearnerActionHandler(Learner learner) {
		super();
		this.learner = learner;
	}

	@Override
	public void processing() {

		if (learner.getAction().equalsIgnoreCase(Constants.SHOW))
			show();
		else if (learner.getAction().equalsIgnoreCase(Constants.SHOW_ADD_EDIT))
			showAddEdit();
		else if (learner.getAction().equalsIgnoreCase(Constants.ADD_EDIT))
			addEdit();
		else if (learner.getAction().equalsIgnoreCase(Constants.DELETE))
			delete();
		else if (learner.getAction().equalsIgnoreCase(Constants.SHOW_WORKSPACE))
			showWorkspace();
		else if (learner.getAction().equalsIgnoreCase(
				Constants.SHOW_ADD_EDIT_WORKSPACE))
			showAddEditWorkspace();
		else if (learner.getAction().equalsIgnoreCase(
				Constants.ADD_EDIT_WORKSPACE))
			addEditWorkspace();
		else if (learner.getAction().equalsIgnoreCase(
				Constants.DELETE_WORKSPACE))
			deleteWorkspace();
		else if (learner.getAction().equalsIgnoreCase(Constants.SHOW_JSON))
			showJson();
	}

	public void show() {
		Integer userId = (Integer) learner.getSession().get(Constants.LOGIN_ID);
		LearnerDAO learnerDAO = new LearnerDAO();
		learner.setLearnerList(learnerDAO.findLearnersOfUser(userId));
	}

	public void showWorkspace() {
		Integer projectId = (Integer) learner.getSession().get(
				Constants.PROJECT_ID);
		LearnerprojectDAO learnerDAO = new LearnerprojectDAO();
		learner.setLearnerList(learnerDAO.findLearnersOfProject(projectId));
	}

	public void showAddEdit() {

		ProjectDAO projectDAO = new ProjectDAO();

		if (learner.getId() != null && !learner.getId().equals(0)) {
			LearnerDAO learnerDAO = new LearnerDAO();
			LearnerprojectDAO learnerprojectDAO = new LearnerprojectDAO();

			Learner l = learnerDAO.findById(learner.getId());
			learner.setName(l.getName().replaceAll("'", "\'"));
			learner.setDescription(l.getDescription().replaceAll("'", "\'"));
			learner.setDatebirth(l.getDatebirth());
			learner.setEmail(l.getEmail());
			learner.setAdresse(l.getAdresse());
			learner.setOrganization(l.getOrganization());
			learner.setId(l.getId());

			List<Integer> list = learnerprojectDAO.getOfLearnerIds(l.getId());
			Integer[] a = new Integer[list.size()];
			int count = 0;
			for (Integer lp : list) {
				a[count++] = lp;
			}

			learner.setProjectIds(a);
		}

		learner.setProjectList(projectDAO.findProjectsOfUser((Integer) learner
				.getSession().get(Constants.LOGIN_ID)));
	}

	public void showAddEditWorkspace() {
		if (learner.getId() != null && !learner.getId().equals(0)) {
			LearnerDAO learnerDAO = new LearnerDAO();

			Learner l = learnerDAO.findById(learner.getId());
			learner.setName(l.getName().replaceAll("'", "\'"));
			learner.setDescription(l.getDescription().replaceAll("'", "\'"));
			learner.setDatebirth(l.getDatebirth());
			learner.setEmail(l.getEmail());
			learner.setAdresse(l.getAdresse());
			learner.setOrganization(l.getOrganization());
			learner.setId(l.getId());
		}
	}
	
	public void deleteWorkspace()
	{
		LearnerprojectDAO learnerprojectDAO = new LearnerprojectDAO();
		learnerprojectDAO.deleteLearnerFromProject(learner.getId(),learner.getProjectId());
	}

	public void addEdit() {
		LearnerDAO learnerDAO = new LearnerDAO();
		LearnerprojectDAO learnerprojectDAO = new LearnerprojectDAO();
		ProjectDAO projectDAO = new ProjectDAO();

		Learner l;
		if (learner.getId() != null && !learner.getId().equals(0)) {
			l = learnerDAO.findById(learner.getId());
			learner.setNewObject(false);
			learnerprojectDAO.deleteOfLearner(learner.getId());
		} else {
			UserDAO userDAO = new UserDAO();
			l = new Learner();
			l.setUser(userDAO.findById((Integer) learner.getSession().get(
					Constants.LOGIN_ID)));
			learner.setNewObject(true);
		}

		l.setName(learner.getName());
		l.setDescription(learner.getDescription());
		l.setDatebirth(learner.getDatebirth());
		l.setEmail(learner.getEmail());
		l.setAdresse(learner.getAdresse());
		l.setOrganization(learner.getOrganization());

		learnerDAO.save(l);
		
		if(learner.getProjectIds() != null)
		for (Integer lpId : learner.getProjectIds()) {
			Learnerproject learnerproject = new Learnerproject();
			learnerproject.setLearner(l);
			learnerproject.setProject(projectDAO.findById(lpId));
			learnerprojectDAO.save(learnerproject);
		}

		learner.setId(l.getId());
	}

	@SuppressWarnings("unchecked")
	public void addEditWorkspace() {
		Gson gson = new Gson();
		LearnerDAO learnerDAO = new LearnerDAO();
		LearnerprojectDAO learnerprojectDAO = new LearnerprojectDAO();
		ProjectDAO projectDAO = new ProjectDAO();
		ConceptDAO conceptDAO = new ConceptDAO();
		LearnerConceptDAO learnerConceptDAO = new LearnerConceptDAO();
		
		List<Map<String, String>> jsoned = new ArrayList<Map<String, String>>();
		jsoned = gson.fromJson(learner.getJson(), List.class);

		Learner l = new Learner();
		l.setNewObject(true);

		if (learner.getId() != null && !learner.getId().equals(0)) {
			l = learnerDAO.findById(learner.getId());
			learner.setNewObject(false);
			learnerConceptDAO.deleteOfLearner(learner.getId());
		} else {
			UserDAO userDAO = new UserDAO();
			l.setUser(userDAO.findById((Integer) learner.getSession().get(
					Constants.LOGIN_ID)));

		}

		l.setName(learner.getName());
		l.setDescription(learner.getDescription());
		l.setDatebirth(learner.getDatebirth());
		l.setEmail(learner.getEmail());
		l.setAdresse(learner.getAdresse());
		l.setOrganization(learner.getOrganization());

		learnerDAO.save(l);

		
		Learnerproject learnerproject = learnerprojectDAO.findOfLearnerProject(l.getId(), (Integer) learner
				.getSession().get(Constants.PROJECT_ID));
		
		if(learnerproject == null)
		{
			learnerproject = new Learnerproject();
			learnerproject.setProject(projectDAO.findById((Integer) learner
					.getSession().get(Constants.PROJECT_ID)));
			learnerproject.setLearner(l);
		}
		
		

		
		for (Map<String, String> maps : jsoned) {
			Integer id = Integer.valueOf(maps.get("concept"));
			if (id != null && !id.equals(0)) {
				
				LearnerConcept learnerConcept = new LearnerConcept();
				learnerConcept.setLearner(l);
				learnerConcept.setConcept(conceptDAO.findById(id));
				learnerConcept.setValue(maps.get("values"));
				learnerConcept.setDescription(maps.get("desc"));
				learnerConceptDAO.save(learnerConcept);
			}
		}

		learnerprojectDAO.save(learnerproject);

		learner.setId(l.getId());
		learner.setNewObject(l.getNewObject());
	
		learner.getErrors().add("Learner Saved Successfully");
		Map<String, Object> responseMap = new LinkedHashMap<String, Object>();
		responseMap.put("messages", learner.getErrors());
		responseMap.put("id", l.getId());
		responseMap.put("newObject", l.getNewObject());
		
		learner.setJson(gson.toJson(responseMap));
	}

	public void showJson() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Map<String, List<Map<String, Object>>> myMap = new LinkedHashMap<String, List<Map<String, Object>>>();

		List<Map<String, Object>> metaDataList = new ArrayList<Map<String, Object>>();

		ConceptDAO conceptDAO = new ConceptDAO();
		List<Concept> conceptList = conceptDAO.findOfProject((Integer)learner.getSession().get(Constants.PROJECT_ID));


		Map<String, Object> nameMap = new LinkedHashMap<String, Object>();
		nameMap.put("name", "select");
		nameMap.put("label", "Select");
		nameMap.put("datatype", "html");
		nameMap.put("editable", false);
		metaDataList.add(nameMap);

		Map<String, Object> conceptNameMap = new LinkedHashMap<String, Object>();
		conceptNameMap.put("name", "concept");
		conceptNameMap.put("label", "Concepts *");
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
		valuesNameMap.put("label", "Values *");
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
		
		Map<String, Object> descNameMap = new LinkedHashMap<String, Object>();
		descNameMap.put("name", "desc");
		descNameMap.put("label", "Description");
		descNameMap.put("datatype", "string");
		descNameMap.put("editable", true);
		metaDataList.add(descNameMap);
		
		Map<String, Object> actionMap = new LinkedHashMap<String, Object>();
		actionMap.put("name", "action");
		actionMap.put("label", "Action");
		actionMap.put("datatype", "html");
		actionMap.put("editable", false);
		metaDataList.add(actionMap);

		myMap.put("metadata", metaDataList);

		if (this.learner.getId() != null && !this.learner.getId().equals(0)) {
			LearnerConceptDAO learnerConceptDAO  = new LearnerConceptDAO();
			List<LearnerConcept> conceptrelations = learnerConceptDAO.findOfLearnerProject(this.learner.getId(),(Integer)learner.getSession().get(Constants.PROJECT_ID));

			List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();

			for (LearnerConcept cr : conceptrelations) {
				Map<String, Object> dataMap = new LinkedHashMap<String, Object>();
				dataMap.put("id", cr.getId());
				Map<String, String> dataValueMap = new LinkedHashMap<String, String>();
				dataValueMap.put("select", "<input type=\"checkbox\" name=\"lcSelect\" value=\"" + cr.getId().toString() + "\">");
				dataValueMap.put("concept", cr.getConcept().getId().toString());
				dataValueMap.put("values", cr.getValue());
				dataValueMap.put("desc", cr.getDescription());
				dataValueMap.put("action", " ");
				dataMap.put("values", dataValueMap);
				dataList.add(dataMap);
			}
			myMap.put("data", dataList);
		}

		learner.setJson(gson.toJson(myMap));
	}

	public void delete() {
		LearnerDAO learnerDAO = new LearnerDAO();
		if (learner.getId() != null && !learner.getId().equals(0)) {
			Learner p = learnerDAO.findById(learner.getId());
			learnerDAO.delete(p);
		}
	}
}
