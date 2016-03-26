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
 * Conceptrelation entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "conceptrelation")
public class Conceptrelation implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8616235949682611800L;
	// Fields

	private Integer id;
	private Functions functions;
	private Concept conceptByConceptTo;
	private Relation relation;
	private Concept conceptByConceptFrom;
	private String value;

	// Constructors

	/** default constructor */
	public Conceptrelation() {
	}

	/** minimal constructor */
	public Conceptrelation(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Conceptrelation(Integer id, Functions functions,
			Concept conceptByConceptTo, Relation relation,
			Concept conceptByConceptFrom, String value) {
		this.id = id;
		this.functions = functions;
		this.conceptByConceptTo = conceptByConceptTo;
		this.relation = relation;
		this.conceptByConceptFrom = conceptByConceptFrom;
		this.value = value;
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
	@JoinColumn(name = "func")
	public Functions getFunctions() {
		return this.functions;
	}

	public void setFunctions(Functions functions) {
		this.functions = functions;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "conceptTo")
	public Concept getConceptByConceptTo() {
		return this.conceptByConceptTo;
	}

	public void setConceptByConceptTo(Concept conceptByConceptTo) {
		this.conceptByConceptTo = conceptByConceptTo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "relation")
	public Relation getRelation() {
		return this.relation;
	}

	public void setRelation(Relation relation) {
		this.relation = relation;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "conceptFrom")
	public Concept getConceptByConceptFrom() {
		return this.conceptByConceptFrom;
	}

	public void setConceptByConceptFrom(Concept conceptByConceptFrom) {
		this.conceptByConceptFrom = conceptByConceptFrom;
	}

	@Column(name = "value", length = 65535)
	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString()
	{
		return "From : " + getConceptByConceptFrom() + ", To : " + getConceptByConceptTo();
	}
}