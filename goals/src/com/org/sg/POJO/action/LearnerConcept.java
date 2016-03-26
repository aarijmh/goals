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
 * LearnerConcept entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "learner_concept")
public class LearnerConcept implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 3918955370810679566L;
	private Integer id;
	private Concept concept;
	private Learner learner;
	private String value;
	private String description;

	// Constructors

	/** default constructor */
	public LearnerConcept() {
	}

	/** minimal constructor */
	public LearnerConcept(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public LearnerConcept(Integer id, Concept concept, Learner learner,
			String value, String description) {
		this.id = id;
		this.concept = concept;
		this.learner = learner;
		this.value = value;
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
	@JoinColumn(name = "concept")
	public Concept getConcept() {
		return this.concept;
	}

	public void setConcept(Concept concept) {
		this.concept = concept;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "learner")
	public Learner getLearner() {
		return this.learner;
	}

	public void setLearner(Learner learner) {
		this.learner = learner;
	}

	@Column(name = "value", length = 65535)
	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Column(name = "description", length = 65535)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}