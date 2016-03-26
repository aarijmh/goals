package com.org.sg.POJO.action;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
import com.org.buisnesslogic.ActionHandlers.UserActionHandler;
import com.org.coursegenrator.utilities.Constants;

/**
 * User entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "user")
@ResultPath(value = "/")
@Results({ 
	    @Result(name = Constants.LOGIN, location = "login.jsp"),
	    @Result(name = Constants.WELCOME, location = "login.jsp"),
	    @Result(name = Constants.LOGOUT, location = "login.jsp"),
	    @Result(name = Constants.LOGGED, type="redirectAction", params ={"actionName" , "project","action",Constants.LOGGED} )})
public class User extends AbstractPOJO implements java.io.Serializable, Action, SessionAware {

	// Fields

	private static final long serialVersionUID = -6580341340204036590L;
	private Integer id;
	private Learner learner;
	private String name;
	private String email;
	private String login;
	private String password;
	private String organization;
	private Integer type;
	private Set<Project> projects = new HashSet<Project>(0);
	private Set<Learner> learners = new HashSet<Learner>(0);

	private Map<String, Object> session; 
	// Constructors

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public User(Integer id, Learner learner, String name, String email,
			String login, String password, String organization, Integer type,
			Set<Project> projects, Set<Learner> learners) {
		this.id = id;
		this.learner = learner;
		this.name = name;
		this.email = email;
		this.login = login;
		this.password = password;
		this.organization = organization;
		this.type = type;
		this.projects = projects;
		this.learners = learners;
	}

	// Property accessors
	@Id
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "learner")
	public Learner getLearner() {
		return this.learner;
	}

	public void setLearner(Learner learner) {
		this.learner = learner;
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

	@Column(name = "login", length = 65535)
	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@Column(name = "password", length = 65535)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "organization", length = 65535)
	public String getOrganization() {
		return this.organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	@Column(name = "type")
	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Project> getProjects() {
		return this.projects;
	}

	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Learner> getLearners() {
		return this.learners;
	}

	public void setLearners(Set<Learner> learners) {
		this.learners = learners;
	}


	@Transient
	public Map<String, Object> getSession()
	{
		return this.session;
	}
	
	@Override
	public void setSession(Map<String, Object> arg0) {
		this.session = arg0;
	}
	

	@Override
	public String execute() throws Exception {
		
		AbstractActionHandler userActionHandler = new UserActionHandler(this);
		userActionHandler.execute();
		
		return this.getAction();
	}

}