package com.org.sg.Utility.action;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.Action;
import com.org.buisnesslogic.ActionHandlers.AbstractActionHandler;
import com.org.buisnesslogic.ActionHandlers.ScenarioActionHandler;
import com.org.coursegenrator.utilities.Constants;
import com.org.sg.POJO.action.AbstractPOJO;

@ResultPath(value = "/")
@Results({ 
	    @Result(name = Constants.GENERATE_SCENARIO, location = "jsonData.jsp")})
public class Scenario  extends AbstractPOJO implements java.io.Serializable, Action {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4677123526300739561L;
	private Integer learnerId;
	private Integer presentationId;
	private String json;
	private Integer projectId;

	public Integer getLearnerId() {
		return learnerId;
	}

	public void setLearnerId(Integer learnerId) {
		this.learnerId = learnerId;
	}

	public Integer getPresentationId() {
		return presentationId;
	}

	public void setPresentationId(Integer presentationId) {
		this.presentationId = presentationId;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	@Override
	public String execute() throws Exception {
		
		AbstractActionHandler actionHandler = new ScenarioActionHandler(this);
		actionHandler.execute();
		
		return getAction();
	}

}
