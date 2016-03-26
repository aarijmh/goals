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
 * LearnerTraces entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "learner_traces")
public class LearnerTraces implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -4636287963626561057L;
	private Integer id;
	private Pedagogicalresource pedagogicalresource;
	private Concept concept;
	private Presentation presentation;
	private Learner learner;
	private Gameresource gameresource;
	private String evaluation;
	private Integer responsetime;
	private String response;
	private Timestamp time;
	private String event;
	private String level;
	private Integer session;
	private String oldProfile;
	private String newProfile;

	// Constructors

	/** default constructor */
	public LearnerTraces() {
	}

	/** minimal constructor */
	public LearnerTraces(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public LearnerTraces(Integer id, Pedagogicalresource pedagogicalresource,
			Concept concept, Presentation presentation, Learner learner,
			Gameresource gameresource, String evaluation, Integer responsetime,
			String response, Timestamp time, String event, String level,
			Integer session, String oldProfile, String newProfile) {
		this.id = id;
		this.pedagogicalresource = pedagogicalresource;
		this.concept = concept;
		this.presentation = presentation;
		this.learner = learner;
		this.gameresource = gameresource;
		this.evaluation = evaluation;
		this.responsetime = responsetime;
		this.response = response;
		this.time = time;
		this.event = event;
		this.level = level;
		this.session = session;
		this.oldProfile = oldProfile;
		this.newProfile = newProfile;
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
	@JoinColumn(name = "pedagogicalresource")
	public Pedagogicalresource getPedagogicalresource() {
		return this.pedagogicalresource;
	}

	public void setPedagogicalresource(Pedagogicalresource pedagogicalresource) {
		this.pedagogicalresource = pedagogicalresource;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "concept")
	public Concept getConcept() {
		return this.concept;
	}

	public void setConcept(Concept concept) {
		this.concept = concept;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "presentationmodel")
	public Presentation getPresentation() {
		return this.presentation;
	}

	public void setPresentation(Presentation presentation) {
		this.presentation = presentation;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "learner")
	public Learner getLearner() {
		return this.learner;
	}

	public void setLearner(Learner learner) {
		this.learner = learner;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "seriousresource")
	public Gameresource getGameresource() {
		return this.gameresource;
	}

	public void setGameresource(Gameresource gameresource) {
		this.gameresource = gameresource;
	}

	@Column(name = "evaluation", length = 65535)
	public String getEvaluation() {
		return this.evaluation;
	}

	public void setEvaluation(String evaluation) {
		this.evaluation = evaluation;
	}

	@Column(name = "responsetime")
	public Integer getResponsetime() {
		return this.responsetime;
	}

	public void setResponsetime(Integer responsetime) {
		this.responsetime = responsetime;
	}

	@Column(name = "response", length = 65535)
	public String getResponse() {
		return this.response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	@Column(name = "time", length = 19)
	public Timestamp getTime() {
		return this.time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	@Column(name = "event", length = 65535)
	public String getEvent() {
		return this.event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	@Column(name = "level", length = 65535)
	public String getLevel() {
		return this.level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	@Column(name = "session")
	public Integer getSession() {
		return this.session;
	}

	public void setSession(Integer session) {
		this.session = session;
	}

	@Column(name = "oldProfile")
	public String getOldProfile() {
		return this.oldProfile;
	}

	public void setOldProfile(String oldProfile) {
		this.oldProfile = oldProfile;
	}

	@Column(name = "newProfile")
	public String getNewProfile() {
		return this.newProfile;
	}

	public void setNewProfile(String newProfile) {
		this.newProfile = newProfile;
	}

}