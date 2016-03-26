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
 * PrProperty entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "pr_property")
public class PrProperty implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 6222447792620816464L;
	private Integer id;
	private Pedagogicalresource pedagogicalresource;
	private Boolean parameter;
	private String name;
	private String value;

	// Constructors

	/** default constructor */
	public PrProperty() {
	}

	/** minimal constructor */
	public PrProperty(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public PrProperty(Integer id, Pedagogicalresource pedagogicalresource,
			Boolean parameter, String name, String value) {
		this.id = id;
		this.pedagogicalresource = pedagogicalresource;
		this.parameter = parameter;
		this.name = name;
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
	@JoinColumn(name = "pedagogicalResource")
	public Pedagogicalresource getPedagogicalresource() {
		return this.pedagogicalresource;
	}

	public void setPedagogicalresource(Pedagogicalresource pedagogicalresource) {
		this.pedagogicalresource = pedagogicalresource;
	}

	@Column(name = "parameter")
	public Boolean getParameter() {
		return this.parameter;
	}

	public void setParameter(Boolean parameter) {
		this.parameter = parameter;
	}

	@Column(name = "name", length = 65535)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "value", length = 65535)
	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}