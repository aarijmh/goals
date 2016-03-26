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
 * Relation entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "relation")
public class Relation implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -5135753688871212417L;
	private Integer id;
	private String name;
	private String description;
	private Set<Conceptrelation> conceptrelations = new HashSet<Conceptrelation>(
			0);

	// Constructors

	/** default constructor */
	public Relation() {
	}

	/** minimal constructor */
	public Relation(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Relation(Integer id, String name, String description,
			Set<Conceptrelation> conceptrelations) {
		this.id = id;
		this.name = name;
		this.description = description;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "relation")
	public Set<Conceptrelation> getConceptrelations() {
		return this.conceptrelations;
	}

	public void setConceptrelations(Set<Conceptrelation> conceptrelations) {
		this.conceptrelations = conceptrelations;
	}

}