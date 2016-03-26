package com.org.buisnesslogic.ActionHandlers;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.org.coursegenrator.utilities.Constants;
import com.org.sg.DAO.GameresourceDAO;
import com.org.sg.DAO.GrPropertyDAO;
import com.org.sg.DAO.PedagogicalGameDAO;
import com.org.sg.DAO.PedagogicalresourceDAO;
import com.org.sg.DAO.ProjectDAO;
import com.org.sg.DAO.TypesDAO;
import com.org.sg.POJO.action.Gameresource;
import com.org.sg.POJO.action.GrProperty;
import com.org.sg.POJO.action.PedagogicalGame;
import com.org.sg.POJO.action.Pedagogicalresource;

public class GameResourceActionHandler extends AbstractActionHandler {

	Gameresource gameresource;

	public GameResourceActionHandler(Gameresource gameresource) {
		this.gameresource = gameresource;
	}

	@Override
	public void processing() {

		if (gameresource.getAction().equalsIgnoreCase(Constants.LOGGED))
			show();
		else if (gameresource.getAction().equalsIgnoreCase(Constants.SHOW_ADD_EDIT))
			showAddEdit();
		else if (gameresource.getAction().equalsIgnoreCase(Constants.ADD_EDIT))
			addEdit();
		else if (gameresource.getAction().equalsIgnoreCase(Constants.DELETE))
			delete();
		else if (gameresource.getAction().equalsIgnoreCase(Constants.SHOW_JSON))
			showJson();
		else if (gameresource.getAction().equalsIgnoreCase(Constants.SHOW_PROPERTIES))
			showProperties();
		else if (gameresource.getAction().equalsIgnoreCase(Constants.SHOW_ADD_EDIT_RELATION))
			showAddEditRelation();
		else if (gameresource.getAction().equalsIgnoreCase(Constants.ADD_EDIT_RELATION))
			addEditRelation();
		else if (gameresource.getAction().equalsIgnoreCase(Constants.SHOW_JSON_RELATION))
			showJsonRelation();
		else if (gameresource.getAction().equalsIgnoreCase(Constants.DELETE_RELATION))
			deleteRelation();
		else if (gameresource.getAction().equalsIgnoreCase(Constants.CHECK_NAME))
			checkName();
		
	}

	public void show() {

	}

	public void showAddEdit() {
		GameresourceDAO gameresourceDAO = new GameresourceDAO();
		TypesDAO typesDAO = new TypesDAO();

		if (gameresource.getId() != null && !gameresource.getId().equals(0)) {
			Gameresource p = gameresourceDAO.findById(gameresource.getId());
			gameresource.setName(p.getName().replaceAll("'", "\'"));
			gameresource.setValue(p.getValue().replaceAll("'", "\'"));
			gameresource.setId(p.getId());
		}

		gameresource.setTypesList(typesDAO.findAll());
	}
	
	public void checkName()
	{
		GameresourceDAO gameresourceDAO = new GameresourceDAO();
		Gson gson = new Gson();
		
		Map<String, Object> responseMap = new LinkedHashMap<String, Object>();
		responseMap.put("messages", gameresourceDAO.checkName(gameresource.getId(), gameresource.getProjectId(), gameresource.getName()));
		
		gameresource.setJson(gson.toJson(responseMap));
		
	}

	public void showJson() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Map<String, List<Map<String, Object>>> myMap = new LinkedHashMap<String, List<Map<String, Object>>>();

		List<Map<String, Object>> metaDataList = new ArrayList<Map<String, Object>>();

		PedagogicalresourceDAO pedagogicalresourceDAO = new PedagogicalresourceDAO();
		List<Pedagogicalresource> conceptList = pedagogicalresourceDAO.findOfProject(gameresource.getProjectId());


		Map<String, Object> nameMap = new LinkedHashMap<String, Object>();
		nameMap.put("name", "select");
		nameMap.put("label", "Select");
		nameMap.put("datatype", "html");
		nameMap.put("editable", false);
		metaDataList.add(nameMap);

		Map<String, Object> gameresourceNameMap = new LinkedHashMap<String, Object>();
		gameresourceNameMap.put("name", "pr");
		gameresourceNameMap.put("label", "Pedagogical Resource");
		gameresourceNameMap.put("datatype", "string");
		gameresourceNameMap.put("editable", true);

		Map<String, String> conceptValueMap = new LinkedHashMap<String, String>();
		conceptValueMap.put("0","Select a pedagogical resource");
		for (Pedagogicalresource c : conceptList) {
			conceptValueMap.put(c.getId().toString(), c.getName());
		}
		gameresourceNameMap.put("values", conceptValueMap);
		metaDataList.add(gameresourceNameMap);


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

		if (this.gameresource.getId() != null && !this.gameresource.getId().equals(0)) {
			PedagogicalGameDAO pedagogicalGameDAO = new PedagogicalGameDAO();
			
			List<PedagogicalGame> pedagogicalGames = pedagogicalGameDAO.findOfGameResource(this.gameresource.getId());

			List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();

			for (PedagogicalGame cr : pedagogicalGames) {
				Map<String, Object> dataMap = new LinkedHashMap<String, Object>();
				dataMap.put("id", cr.getId());
				Map<String, String> dataValueMap = new LinkedHashMap<String, String>();
				dataValueMap.put("select", "<input type=\"checkbox\" name=\"gameresourceSelect\" value=\""+cr.getId().toString()+"\">");
				dataValueMap.put("pr", cr.getPedagogicalresource().getId()
						.toString());
				dataValueMap.put("values", cr.getValue());
				dataValueMap.put("action", " ");
				dataMap.put("values", dataValueMap);
				dataList.add(dataMap);
			}
			myMap.put("data", dataList);
		}
		
		gameresource.setJson(gson.toJson(myMap));
	}
	
	public void showProperties()
	{
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
		
		if (this.gameresource.getId() != null && !this.gameresource.getId().equals(0)) {
			GrPropertyDAO grPropertyDAO = new GrPropertyDAO();
			List<GrProperty> prProperties = grPropertyDAO.findOfGameResource(this.gameresource.getId());
			
			List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
			
			for (GrProperty cr : prProperties) {
				Map<String, Object> dataMap = new LinkedHashMap<String, Object>();
				dataMap.put("id", cr.getId());
				
				Map<String, String> dataValueMap = new LinkedHashMap<String, String>();
				dataValueMap.put("select", "<input type=\"checkbox\" name=\"prPropertySelect\" value=\""+cr.getId().toString()+"\">");
				dataValueMap.put("name", cr.getName());
				dataValueMap.put("value", cr.getValue());
				dataValueMap.put("action", "");

				dataMap.put("values", dataValueMap);
				
				dataList.add(dataMap);
			}
			myMap.put("data", dataList);
		}
		
		gameresource.setJson(gson.toJson(myMap));
	}

	@SuppressWarnings("unchecked")
	public void addEdit() {
		
		GameresourceDAO gameresourceDAO = new GameresourceDAO();
		PedagogicalGameDAO pedagogicalGameDAO = new PedagogicalGameDAO();
		GrPropertyDAO grPropertyDAO = new GrPropertyDAO();
		TypesDAO typesDAO = new TypesDAO();
		
		ProjectDAO projectDAO = new ProjectDAO();
		PedagogicalresourceDAO pedagogicalresourceDAO = new PedagogicalresourceDAO();
		
		Gson gson = new Gson();
		
		List<Map<String,String>> jsoned = new ArrayList<Map<String,String>>();
		jsoned = gson.fromJson(gameresource.getJson(), List.class);
		
		List<Map<String,String>> properties = new ArrayList<Map<String,String>>();
		properties = gson.fromJson(gameresource.getPrJson(), List.class);
		
		Gameresource pr = new Gameresource();
		
		if (this.gameresource.getId() != null && !this.gameresource.getId().equals(0)) {
			pr = gameresourceDAO.findById(gameresource.getId());
			pedagogicalGameDAO.deleteOfGR(this.gameresource.getId());
			grPropertyDAO.deleteOfGR(this.gameresource.getId());
		}
		
		pr.setName(gameresource.getName());
		pr.setValue(gameresource.getValue());
		pr.setProject(projectDAO.findById(gameresource.getProjectId()));
		pr.setTypes(typesDAO.findById(gameresource.getTypesId()));
		gameresourceDAO.save(pr);
		
		
		
		for(Map<String,String> maps : jsoned)
		{
			Integer id = Integer.valueOf(maps.get("pr"));
			if(id != null && !id.equals(0))
			{
				PedagogicalGame cr = new  PedagogicalGame();
				cr.setGameresource(pr);
				cr.setPedagogicalresource(pedagogicalresourceDAO.findById(id));
				cr.setValue(maps.get("values"));
				
				pedagogicalGameDAO.save(cr);
			}
		}
		
		for(Map<String,String> maps : properties)
		{
			String name = maps.get("name");
			if(name != null && !name.trim().equalsIgnoreCase(""))
			{
				GrProperty cProperty = new GrProperty();
				cProperty.setGameresource(pr);
				cProperty.setName(name);
				cProperty.setValue(maps.get("value"));
				
				grPropertyDAO.save(cProperty);
			}
		}
		
		gameresource.getErrors().add("Game Resource Saved Successfully");
		Map<String,Object> responseMap = new LinkedHashMap<String, Object>();
		responseMap.put("messages", gameresource.getErrors());
		responseMap.put("id", pr.getId());
		gameresource.setJson(gson.toJson(responseMap));
	}

	public void delete() {
		if (this.gameresource.getId() != null && !this.gameresource.getId().equals(0)) {
			GameresourceDAO gameresourceDAO = new GameresourceDAO(); 
			gameresourceDAO.delete(gameresourceDAO.findById(this.gameresource.getId()));
		}
	}
	
	public void deleteRelation() {
		if (this.gameresource.getRelationId() != null && !this.gameresource.getRelationId().equals(0)) {
			PedagogicalGameDAO gameresourceDAO = new PedagogicalGameDAO(); 
			gameresourceDAO.delete(gameresourceDAO.findById(this.gameresource.getRelationId()));
		}
	}
	
	public void showAddEditRelation() {

	}
	
	@SuppressWarnings("unchecked")
	public void addEditRelation() {
		GameresourceDAO gameresourceDAO = new GameresourceDAO();
		PedagogicalGameDAO pedagogicalGameDAO = new PedagogicalGameDAO();
		PedagogicalresourceDAO pedagogicalresourceDAO = new PedagogicalresourceDAO();
		
		Gson gson = new Gson();
		
		List<Map<String,String>> jsoned = new ArrayList<Map<String,String>>();
		jsoned = gson.fromJson(gameresource.getJson(), List.class);

		
		PedagogicalGame cr = new PedagogicalGame();
		
		if (this.gameresource.getRelationId() != null && !this.gameresource.getRelationId().equals(0)) {
			cr = pedagogicalGameDAO.findById(gameresource.getRelationId());
		}
		
		
		for(Map<String,String> maps : jsoned)
		{
			Integer id = Integer.valueOf(maps.get("pr"));
			Integer pId = Integer.valueOf(maps.get("gr"));
			if(id != null && !id.equals(0) && pId != null && !pId.equals(0))
			{
				cr.setGameresource(gameresourceDAO.findById(pId));
				cr.setPedagogicalresource(pedagogicalresourceDAO.findById(id));
				cr.setValue(maps.get("values"));
				
				pedagogicalGameDAO.save(cr);
			}
		}

		
		gameresource.getErrors().add("Relation Saved Successfully");
		Map<String,Object> responseMap = new LinkedHashMap<String, Object>();
		responseMap.put("messages", gameresource.getErrors());
		responseMap.put("relationId", cr.getId());
		responseMap.put("source", cr.getPedagogicalresource().getId());
		responseMap.put("target", cr.getGameresource().getId());
		responseMap.put("name", cr.toString());
		gameresource.setJson(gson.toJson(responseMap));
	}
	
	public void showJsonRelation() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Map<String, List<Map<String, Object>>> myMap = new LinkedHashMap<String, List<Map<String, Object>>>();

		List<Map<String, Object>> metaDataList = new ArrayList<Map<String, Object>>();

		PedagogicalresourceDAO pedagogicalresourceDAO = new PedagogicalresourceDAO();
		GameresourceDAO gameresourceDAO = new GameresourceDAO();
		
		List<Pedagogicalresource> prList = pedagogicalresourceDAO.findOfProject(gameresource.getProjectId());
		List<Gameresource> grList = gameresourceDAO.findOfProject(gameresource.getProjectId());



		
		
		Map<String, Object> prNameMap = new LinkedHashMap<String, Object>();
		prNameMap.put("name", "pr");
		prNameMap.put("label", "Game Resource");
		prNameMap.put("datatype", "string");
		prNameMap.put("editable", true);

		Map<String, String> prValueMap = new LinkedHashMap<String, String>();
		prValueMap.put("0","Select a PR");
		for (Pedagogicalresource c : prList) {
			prValueMap.put(c.getId().toString(), c.getName());
		}
		prNameMap.put("values", prValueMap);
		metaDataList.add(prNameMap);
		
		Map<String, Object> conceptNameMap = new LinkedHashMap<String, Object>();
		conceptNameMap.put("name", "gr");
		conceptNameMap.put("label", "Game Resource");
		conceptNameMap.put("datatype", "string");
		conceptNameMap.put("editable", true);

		Map<String, String> conceptValueMap = new LinkedHashMap<String, String>();
		conceptValueMap.put("0","Select a game resource");
		for (Gameresource c : grList) {
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
		

		myMap.put("metadata", metaDataList);
		
		this.gameresource.setNewObject(false);

		PedagogicalGame cr = new PedagogicalGame();
		if (this.gameresource.getRelationId() != null && !this.gameresource.getRelationId().equals(0)) {
			PedagogicalGameDAO conceptPrDAO = new PedagogicalGameDAO();
			cr = conceptPrDAO.findById(this.gameresource.getRelationId());
			this.gameresource.setNewObject(true);
		}
			

			List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();

			
				Map<String, Object> dataMap = new LinkedHashMap<String, Object>();
				dataMap.put("id", gameresource.getNewObject() ? cr.getId():0);
				Map<String, String> dataValueMap = new LinkedHashMap<String, String>();
				dataValueMap.put("select", "<input type=\"checkbox\" name=\"gameresourceSelect\" value=\""+(gameresource.getNewObject() ? cr.getId().toString():"0")+"\">");
				dataValueMap.put("gr", gameresource.getNewObject() ? cr.getGameresource().getId()
						.toString():"0");
				dataValueMap.put("pr", gameresource.getNewObject() ? cr.getPedagogicalresource().getId()
						.toString():"0");				
				dataValueMap.put("values", gameresource.getNewObject() ? cr.getValue():"0");
				dataMap.put("values", dataValueMap);
				dataList.add(dataMap);
			
			myMap.put("data", dataList);
			
		gameresource.setJson(gson.toJson(myMap));
	}


}
