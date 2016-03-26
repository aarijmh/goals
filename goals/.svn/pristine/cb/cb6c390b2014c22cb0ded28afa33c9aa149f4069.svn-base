package com.org.sg.POJO.action;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * ScenarioTrace entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "scenario_trace")
public class ScenarioTrace implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 4407429210532644391L;
	private Integer id;
	private Presentation presentation;
	private Project project;
	private Learner learner;
	private String objectives;
	private String scenario;
	private String textualScenario;
	private Timestamp date;

	// Constructors

	/** default constructor */
	public ScenarioTrace() {
	}

	/** minimal constructor */
	public ScenarioTrace(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public ScenarioTrace(Integer id, Presentation presentation,
			Project project, Learner learner, String objectives,
			String scenario, String textualScenario, Timestamp date) {
		this.id = id;
		this.presentation = presentation;
		this.project = project;
		this.learner = learner;
		this.objectives = objectives;
		this.scenario = scenario;
		this.textualScenario = textualScenario;
		this.date = date;
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
	@JoinColumn(name = "presentation")
	public Presentation getPresentation() {
		return this.presentation;
	}

	public void setPresentation(Presentation presentation) {
		this.presentation = presentation;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "project")
	public Project getProject() {
		return this.project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "learner")
	public Learner getLearner() {
		return this.learner;
	}

	public void setLearner(Learner learner) {
		this.learner = learner;
	}

	@Column(name = "objectives")
	public String getObjectives() {
		return this.objectives;
	}

	public void setObjectives(String objectives) {
		this.objectives = objectives;
	}

	@Column(name = "scenario")
	public String getScenario() {
		return this.scenario;
	}

	public void setScenario(String scenario) {
		this.scenario = scenario;
	}

	@Column(name = "textualScenario")
	public String getTextualScenario() {
		return this.textualScenario;
	}

	public void setTextualScenario(String textualScenario) {
		this.textualScenario = textualScenario;
	}

	@Column(name = "date", length = 19)
	public Timestamp getDate() {
		return this.date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

}