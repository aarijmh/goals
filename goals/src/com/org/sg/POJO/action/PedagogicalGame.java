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
 * PedagogicalGame entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "pedagogical_game")
public class PedagogicalGame implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 8495770837150757341L;
	private Integer id;
	private Pedagogicalresource pedagogicalresource;
	private Gameresource gameresource;
	private String value;

	// Constructors

	/** default constructor */
	public PedagogicalGame() {
	}

	/** minimal constructor */
	public PedagogicalGame(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public PedagogicalGame(Integer id, Pedagogicalresource pedagogicalresource,
			Gameresource gameresource, String value) {
		this.id = id;
		this.pedagogicalresource = pedagogicalresource;
		this.gameresource = gameresource;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "gameResource")
	public Gameresource getGameresource() {
		return this.gameresource;
	}

	public void setGameresource(Gameresource gameresource) {
		this.gameresource = gameresource;
	}

	@Column(name = "value", length = 65535)
	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}