package com.org.sg.POJO.action;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
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
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.Action;
import com.org.buisnesslogic.ActionHandlers.AbstractActionHandler;
import com.org.buisnesslogic.ActionHandlers.LearnerActionHandler;
import com.org.coursegenrator.utilities.Constants;

/**
 * Learner entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "learner")
@ResultPath(value = "/")
@Results({
		@Result(name = Constants.SHOW, location = "showLearners.jsp"),
		@Result(name = Constants.SHOW_ADD_EDIT, location = "showAddEditLearner.jsp"),
		@Result(name = Constants.ADD_EDIT, location = "addEditProject.jsp"),
		@Result(name = Constants.DELETE, location = "addEditLearner.jsp"),
		@Result(name = Constants.SHOW_WORKSPACE, location = "showLearnersWorkspace.jsp"),
		@Result(name = Constants.SHOW_ADD_EDIT_WORKSPACE, location = "showAddEditLearnerWorkspace.jsp"),
		@Result(name = Constants.ADD_EDIT_WORKSPACE, location = "jsonData.jsp"),
		@Result(name = Constants.SHOW_JSON, location = "jsonData.jsp")})
public class Learner extends AbstractPOJO implements java.io.Serializable,
		Action, SessionAware {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 5262136286794850409L;
	private Integer id;
	private User user;
	private String name;
	private String email;
	private Integer age;
	private String description;
	private Integer prototype;
	private String organization;
	private String adresse;
	private String datebirth;
	private Set<ScenarioTrace> scenarioTraces = new HashSet<ScenarioTrace>(0);
	private Set<LearnerTraces> learnerTraceses = new HashSet<LearnerTraces>(0);
	private Set<User> users = new HashSet<User>(0);
	private Set<LearnerConcept> learnerConcepts = new HashSet<LearnerConcept>(0);
	private Set<Learnerproject> learnerprojects = new HashSet<Learnerproject>(0);

	// Constructors

	private Map<String, Object> session;
	private List<Learner> learnerList;
	private Boolean newObject;
	private List<Project> projectList;
	private Integer[] projectIds;
	private String json;

	/** default constructor */
	public Learner() {
	}

	/** minimal constructor */
	public Learner(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Learner(Integer id, User user, String name, String email,
			Integer age, String description, Integer prototype,
			String organization, String adresse, String datebirth,
			Set<ScenarioTrace> scenarioTraces,
			Set<LearnerTraces> learnerTraceses, Set<User> users,
			Set<LearnerConcept> learnerConcepts,
			Set<Learnerproject> learnerprojects) {
		this.id = id;
		this.user = user;
		this.name = name;
		this.email = email;
		this.age = age;
		this.description = description;
		this.prototype = prototype;
		this.organization = organization;
		this.adresse = adresse;
		this.datebirth = datebirth;
		this.scenarioTraces = scenarioTraces;
		this.learnerTraceses = learnerTraceses;
		this.users = users;
		this.learnerConcepts = learnerConcepts;
		this.learnerprojects = learnerprojects;
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
	@JoinColumn(name = "user")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "name", length = 65535)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "email", length = 65535)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "age")
	public Integer getAge() {
		return this.age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Column(name = "description", length = 65535)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "prototype")
	public Integer getPrototype() {
		return this.prototype;
	}

	public void setPrototype(Integer prototype) {
		this.prototype = prototype;
	}

	@Column(name = "organization", length = 65535)
	public String getOrganization() {
		return this.organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	@Column(name = "adresse", length = 65535)
	public String getAdresse() {
		return this.adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	@Column(name = "datebirth", length = 15)
	public String getDatebirth() {
		return this.datebirth;
	}

	public void setDatebirth(String datebirth) {
		this.datebirth = datebirth;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "learner")
	public Set<ScenarioTrace> getScenarioTraces() {
		return this.scenarioTraces;
	}

	public void setScenarioTraces(Set<ScenarioTrace> scenarioTraces) {
		this.scenarioTraces = scenarioTraces;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "learner")
	public Set<LearnerTraces> getLearnerTraceses() {
		return this.learnerTraceses;
	}

	public void setLearnerTraceses(Set<LearnerTraces> learnerTraceses) {
		this.learnerTraceses = learnerTraceses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "learner")
	public Set<User> getUsers() {
		return this.users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "learner")
	public Set<LearnerConcept> getLearnerConcepts() {
		return this.learnerConcepts;
	}

	public void setLearnerConcepts(Set<LearnerConcept> learnerConcepts) {
		this.learnerConcepts = learnerConcepts;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "learner")
	public Set<Learnerproject> getLearnerprojects() {
		return this.learnerprojects;
	}

	public void setLearnerprojects(Set<Learnerproject> learnerprojects) {
		this.learnerprojects = learnerprojects;
	}

	@Transient
	public Map<String, Object> getSession() {
		return session;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		this.session = arg0;
	}

	@Transient
	public List<Learner> getLearnerList() {
		return learnerList;
	}

	public void setLearnerList(List<Learner> learnerList) {
		this.learnerList = learnerList;
	}

	@Transient
	public Boolean getNewObject() {
		return newObject;
	}

	public void setNewObject(Boolean newObject) {
		this.newObject = newObject;
	}

	@Transient
	public List<Project> getProjectList() {
		return projectList;
	}

	public void setProjectList(List<Project> projectList) {
		this.projectList = projectList;
	}

	@Transient
	public Integer[] getProjectIds() {
		return projectIds;
	}

	public void setProjectIds(Integer[] projectIds) {
		this.projectIds = projectIds;
	}

	@Transient
	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	@Override
	public String execute() throws Exception {

		AbstractActionHandler actionHandler = new LearnerActionHandler(this);
		actionHandler.execute();

		return getAction();
	}

}