package com.org.sg.POJO.action;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.Action;
import com.org.buisnesslogic.ActionHandlers.AbstractActionHandler;
import com.org.buisnesslogic.ActionHandlers.ConceptActionHandler;
import com.org.coursegenrator.utilities.Constants;

/**
 * Concept entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "concept")
@ResultPath(value = "/")
@Results({ 
	    @Result(name = "showAddEditConcept", location = "index.jsp"),
	    @Result(name = "showAddEditConceptVis", location = "conceptVIS.jsp"),
	    @Result(name = Constants.SHOW_ADD_EDIT, location = "showAddEditConcept.jsp"),
	    @Result(name = Constants.ADD_EDIT, location = "jsonData.jsp"),
	    @Result(name = Constants.SHOW_JSON, location = "jsonData.jsp"),
	    @Result(name = Constants.SHOW_PROPERTIES, location = "jsonData.jsp"),
	    @Result(name = Constants.SHOW_ADD_EDIT_RELATION, location = "showAddEditCCR.jsp"),
	    @Result(name = Constants.SHOW_JSON_RELATION, location = "jsonData.jsp"),
	    @Result(name = Constants.ADD_EDIT_RELATION, location = "jsonData.jsp"),
	    @Result(name = Constants.SHOW_GENERATOR, location = "scenarioGenerator.jsp"),
	    @Result(name = Constants.SHOW_JSON_GENERATOR, location = "jsonData.jsp"),
	    @Result(name = Constants.SHOW_ADD_EDIT_CONCEPT_JSON, location = "jsonData.jsp"),
	    @Result(name = Constants.DELETE, location = "jsonData.jsp"),
	    @Result(name = Constants.CHECK_NAME, location = "jsonData.jsp"),
	    @Result(name = Constants.DELETE_RELATION, location = "jsonData.jsp")})
public class Concept extends AbstractPOJO implements java.io.Serializable, Action, Comparator<Concept> {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -4486525456049296718L;
	private Integer id;
	private Project project;
	private String name;
	private String description;
	private Integer type;
	private Set<LearnerConcept> learnerConcepts = new HashSet<LearnerConcept>(0);
	private Set<CProperty> CProperties = new HashSet<CProperty>(0);
	private Set<LearnerTraces> learnerTraceses = new HashSet<LearnerTraces>(0);
	private Set<ConceptPr> conceptPrs = new HashSet<ConceptPr>(0);
	private Set<Conceptrelation> conceptrelationsForConceptFrom = new HashSet<Conceptrelation>(
			0);
	private Set<Conceptrelation> conceptrelationsForConceptTo = new HashSet<Conceptrelation>(
			0);

	private String jsonObject;
	private List<Concept> conceptList = new ArrayList<Concept>();
	private List<Relation> relationList = new ArrayList<Relation>();
	private List<Functions> functionsList = new ArrayList<Functions>();
	private List<Conceptrelation> conceptRelationList = new ArrayList<Conceptrelation>();
	private List<CProperty> conceptPropertyList = new ArrayList<CProperty>();
	private Boolean newObject;
	private Integer projectId;
	private String json;
	private String prJson;
	private Integer relationId;
	private List<Learner> learnerList = new ArrayList<Learner>();
	private List<Presentation> presentationList = new ArrayList<Presentation>();
	// Constructors

	/** default constructor */
	public Concept() {
	}

	/** minimal constructor */
	public Concept(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Concept(Integer id, Project project, String name,
			String description, Integer type,
			Set<LearnerConcept> learnerConcepts, Set<CProperty> CProperties,
			Set<LearnerTraces> learnerTraceses, Set<ConceptPr> conceptPrs,
			Set<Conceptrelation> conceptrelationsForConceptFrom,
			Set<Conceptrelation> conceptrelationsForConceptTo) {
		this.id = id;
		this.project = project;
		this.name = name;
		this.description = description;
		this.type = type;
		this.learnerConcepts = learnerConcepts;
		this.CProperties = CProperties;
		this.learnerTraceses = learnerTraceses;
		this.conceptPrs = conceptPrs;
		this.conceptrelationsForConceptFrom = conceptrelationsForConceptFrom;
		this.conceptrelationsForConceptTo = conceptrelationsForConceptTo;
	}

	// Property accessors
	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "project")
	public Project getProject() {
		return this.project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	@Column(name = "name", length = 65535)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "description", length = 65535)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "type")
	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "concept")
	public Set<LearnerConcept> getLearnerConcepts() {
		return this.learnerConcepts;
	}

	public void setLearnerConcepts(Set<LearnerConcept> learnerConcepts) {
		this.learnerConcepts = learnerConcepts;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "concept")
	public Set<CProperty> getCProperties() {
		return this.CProperties;
	}

	public void setCProperties(Set<CProperty> CProperties) {
		this.CProperties = CProperties;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "concept")
	public Set<LearnerTraces> getLearnerTraceses() {
		return this.learnerTraceses;
	}

	public void setLearnerTraceses(Set<LearnerTraces> learnerTraceses) {
		this.learnerTraceses = learnerTraceses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "concept")
	public Set<ConceptPr> getConceptPrs() {
		return this.conceptPrs;
	}

	public void setConceptPrs(Set<ConceptPr> conceptPrs) {
		this.conceptPrs = conceptPrs;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "conceptByConceptFrom")
	public Set<Conceptrelation> getConceptrelationsForConceptFrom() {
		return this.conceptrelationsForConceptFrom;
	}

	public void setConceptrelationsForConceptFrom(
			Set<Conceptrelation> conceptrelationsForConceptFrom) {
		this.conceptrelationsForConceptFrom = conceptrelationsForConceptFrom;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "conceptByConceptTo")
	public Set<Conceptrelation> getConceptrelationsForConceptTo() {
		return this.conceptrelationsForConceptTo;
	}

	public void setConceptrelationsForConceptTo(
			Set<Conceptrelation> conceptrelationsForConceptTo) {
		this.conceptrelationsForConceptTo = conceptrelationsForConceptTo;
	}
	
	@Transient
	public String getJsonObject() {
		return jsonObject;
	}

	public void setJsonObject(String jsonObject) {
		this.jsonObject = jsonObject;
	}

	@Override
	public String toString()
	{
		return name;
	}

	@Transient
	public List<Concept> getConceptList() {
		return conceptList;
	}

	public void setConceptList(List<Concept> conceptList) {
		this.conceptList = conceptList;
	}

	@Transient
	public List<Relation> getRelationList() {
		return relationList;
	}

	public void setRelationList(List<Relation> relationList) {
		this.relationList = relationList;
	}

	@Transient
	public List<Functions> getFunctionsList() {
		return functionsList;
	}

	
	public void setFunctionsList(List<Functions> functionsList) {
		this.functionsList = functionsList;
	}
	@Transient
	public List<Conceptrelation> getConceptRelationList() {
		return conceptRelationList;
	}

	
	public void setConceptRelationList(List<Conceptrelation> conceptRelationList) {
		this.conceptRelationList = conceptRelationList;
	}
	@Transient
	public List<CProperty> getConceptPropertyList() {
		return conceptPropertyList;
	}
	
	public void setConceptPropertyList(List<CProperty> conceptPropertyList) {
		this.conceptPropertyList = conceptPropertyList;
	}
	
	@Transient
	public Boolean getNewObject() {
		return newObject;
	}

	
	public void setNewObject(Boolean newObject) {
		this.newObject = newObject;
	}

	@Transient
	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	@Transient
	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	@Transient
	public String getPrJson() {
		return prJson;
	}

	public void setPrJson(String prJson) {
		this.prJson = prJson;
	}

	@Transient
	public Integer getRelationId() {
		return relationId;
	}

	public void setRelationId(Integer relationId) {
		this.relationId = relationId;
	}

	@Transient
	public List<Learner> getLearnerList() {
		return learnerList;
	}

	public void setLearnerList(List<Learner> learnerList) {
		this.learnerList = learnerList;
	}

	@Transient
	public List<Presentation> getPresentationList() {
		return presentationList;
	}

	public void setPresentationList(List<Presentation> presentationList) {
		this.presentationList = presentationList;
	}



	@Override
	public int compare(Concept o1, Concept o2) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public boolean 	equals(Object obj)
	{
		return this.getId().equals(((Concept)obj).getId());
	}
	
	@Override
	public String execute() throws Exception {

		AbstractActionHandler handler = new ConceptActionHandler(this);
		handler.execute();
		
		return this.getAction();
	}



}