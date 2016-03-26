package com.org.sg.POJO.action;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Functions entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "functions")
public class Functions implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 3045461446930136795L;
	private Integer id;
	private String func;
	private String description;
	private String parameters;
	private Set<Conceptrelation> conceptrelations = new HashSet<Conceptrelation>(
			0);

	// Constructors

	/** default constructor */
	public Functions() {
	}

	/** minimal constructor */
	public Functions(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Functions(Integer id, String func, String description,
			String parameters, Set<Conceptrelation> conceptrelations) {
		this.id = id;
		this.func = func;
		this.description = description;
		this.parameters = parameters;
		this.conceptrelations = conceptrelations;
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

	@Column(name = "func", length = 65535)
	public String getFunc() {
		return this.func;
	}

	public void setFunc(String func) {
		this.func = func;
	}

	@Column(name = "description", length = 65535)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "parameters", length = 65535)
	public String getParameters() {
		return this.parameters;
	}

	public void setParameters(String parameters) {
		this.parameters = parameters;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "functions")
	public Set<Conceptrelation> getConceptrelations() {
		return this.conceptrelations;
	}

	public void setConceptrelations(Set<Conceptrelation> conceptrelations) {
		this.conceptrelations = conceptrelations;
	}

}