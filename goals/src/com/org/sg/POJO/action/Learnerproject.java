package com.org.sg.POJO.action;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Learnerproject entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "learnerproject")
public class Learnerproject implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -3863512161448669281L;
	private Integer id;
	private Project project;
	private Learner learner;
	private String description;

	// Constructors

	/** default constructor */
	public Learnerproject() {
	}

	/** minimal constructor */
	public Learnerproject(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Learnerproject(Integer id, Project project, Learner learner,
			String description) {
		this.id = id;
		this.project = project;
		this.learner = learner;
		this.description = description;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "learner")
	public Learner getLearner() {
		return this.learner;
	}

	public void setLearner(Learner learner) {
		this.learner = learner;
	}

	@Column(name = "description", length = 65535)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}