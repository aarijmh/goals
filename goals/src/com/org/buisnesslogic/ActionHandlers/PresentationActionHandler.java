package com.org.buisnesslogic.ActionHandlers;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.org.coursegenrator.utilities.Constants;
import com.org.sg.DAO.PedagogicalresourceDAO;
import com.org.sg.DAO.PresentationDAO;
import com.org.sg.DAO.PresentationPrDAO;
import com.org.sg.DAO.ProjectDAO;
import com.org.sg.DAO.TypesDAO;
import com.org.sg.POJO.action.Presentation;
import com.org.sg.POJO.action.PresentationPr;
import com.org.sg.POJO.action.Types;

public class PresentationActionHandler extends AbstractActionHandler {

	Presentation presentation;
	
	public PresentationActionHandler(Presentation presentation)
	{
		this.presentation = presentation;
	}
	
	@Override
	public void processing() {

		if(presentation.getAction().equalsIgnoreCase(Constants.SHOW))
		{
			show();
		}
		else if(presentation.getAction().equalsIgnoreCase(Constants.SHOW_ADD_EDIT))
		{
			showAddEdit();
		}
		else if(presentation.getAction().equalsIgnoreCase(Constants.SHOW_JSON))
		{
			showJson();
		}
		else if(presentation.getAction().equalsIgnoreCase(Constants.ADD_EDIT))
		{
			addEdit();
		}
	}
	
	public void show()
	{
		PresentationDAO presentationDAO = new PresentationDAO();
		presentation.setPresentationList(presentationDAO.findAllOfProject(presentation.getProjectId()));
	}
	
	public void showAddEdit()
	{
		
		
		PedagogicalresourceDAO pedagogicalresourceDAO = new PedagogicalresourceDAO();
		
		if (presentation.getId() != null && !presentation.getId().equals(0)) {
			PresentationDAO presentationDAO = new PresentationDAO();
			
			Presentation p = presentationDAO.findById(presentation.getId());
			presentation.setName(p.getName().replaceAll("'", "\'"));
			presentation.setDescription(p.getDescription().replaceAll("'", "\'"));
			presentation.setId(p.getId());

		}

		presentation.setPrList(pedagogicalresourceDAO.findOfProject(presentation.getProjectId()));
	}
	
	@SuppressWarnings("unchecked")
	public void addEdit()
	{
		PresentationDAO presentationDAO = new PresentationDAO();
		PresentationPrDAO presentationPrDAO = new PresentationPrDAO();
		TypesDAO typesDAO = new TypesDAO();
		
		ProjectDAO projectDAO = new ProjectDAO();
		
		Gson gson = new Gson();
		
		List<Map<String,String>> jsoned = new ArrayList<Map<String,String>>();
		jsoned = gson.fromJson(presentation.getJson(), List.class);
		
		Presentation pr = new Presentation();
		pr.setNewObject(true);
		if (this.presentation.getId() != null && !this.presentation.getId().equals(0)) {
			pr = presentationDAO.findById(presentation.getId());
			presentationPrDAO.deleteOfPR(this.presentation.getId());
			pr.setNewObject(false);
		}
		
		pr.setName(presentation.getName());
		pr.setDescription(presentation.getDescription());
		pr.setProject(projectDAO.findById(presentation.getProjectId()));
		presentationDAO.save(pr);
		
		
		
		for(Map<String,String> maps : jsoned)
		{
			Integer id = Integer.valueOf(maps.get("types"));
			if(id != null && !id.equals(0))
			{
				PresentationPr cr = new  PresentationPr();
				cr.setPresentation(pr);
				cr.setTypes(typesDAO.findById(id));
				cr.setDescription(maps.get("desc"));
				
				presentationPrDAO.save(cr);
			}
		}
		
		presentation.getErrors().add("Presentation Saved Successfully");
		Map<String,Object> responseMap = new LinkedHashMap<String, Object>();
		responseMap.put("messages", presentation.getErrors());
		responseMap.put("id", pr.getId());
		responseMap.put("newObject", pr.getNewObject());
		presentation.setJson(gson.toJson(responseMap));
	}
	
	public void showJson()
	{
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Map<String, List<Map<String, Object>>> myMap = new LinkedHashMap<String, List<Map<String, Object>>>();

		List<Map<String, Object>> metaDataList = new ArrayList<Map<String, Object>>();

		TypesDAO typesDAO = new TypesDAO(); 
		List<Types> conceptList = typesDAO.findAll();


		Map<String, Object> nameMap = new LinkedHashMap<String, Object>();
		nameMap.put("name", "select");
		nameMap.put("label", "Select");
		nameMap.put("datatype", "html");
		nameMap.put("editable", false);
		metaDataList.add(nameMap);

		Map<String, Object> presentationNameMap = new LinkedHashMap<String, Object>();
		presentationNameMap.put("name", "types");
		presentationNameMap.put("label", "Types");
		presentationNameMap.put("datatype", "string");
		presentationNameMap.put("editable", true);

		Map<String, String> conceptValueMap = new LinkedHashMap<String, String>();
		conceptValueMap.put("0","Select a Type");
		for (Types c : conceptList) {
			conceptValueMap.put(c.getId().toString(), c.getName());
		}
		presentationNameMap.put("values", conceptValueMap);
		metaDataList.add(presentationNameMap);


		Map<String, Object> valuesNameMap = new LinkedHashMap<String, Object>();
		valuesNameMap.put("name", "desc");
		valuesNameMap.put("label", "Description");
		valuesNameMap.put("datatype", "string");
		valuesNameMap.put("editable", true);

		metaDataList.add(valuesNameMap);
		
		Map<String, Object> actionMap = new LinkedHashMap<String, Object>();
		actionMap.put("name", "action");
		actionMap.put("label", "Action");
		actionMap.put("datatype", "html");
		actionMap.put("editable", false);
		metaDataList.add(actionMap);

		myMap.put("metadata", metaDataList);

		if (this.presentation.getId() != null && !this.presentation.getId().equals(0)) {
			PresentationDAO presentationDAO = new PresentationDAO();
			
			Presentation p = presentationDAO.findById(this.presentation.getId());
			

			List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();

			for (PresentationPr cr : p.getPresentationPrs()) {
				Map<String, Object> dataMap = new LinkedHashMap<String, Object>();
				dataMap.put("id", cr.getId());
				Map<String, String> dataValueMap = new LinkedHashMap<String, String>();
				dataValueMap.put("select", "<input type=\"checkbox\" name=\"presentationSelect\" value=\""+cr.getId().toString()+"\">");
				dataValueMap.put("types", cr.getTypes().getId()
						.toString());
				dataValueMap.put("desc", cr.getDescription());
				dataValueMap.put("action", " ");
				dataMap.put("values", dataValueMap);
				dataList.add(dataMap);
			}
			myMap.put("data", dataList);
		}
		
		presentation.setJson(gson.toJson(myMap));
	}

}
