package com.org.sg.UtilityClasses;

import java.io.Serializable;

public class ObjectGraph implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5372686972185619627L;
	private Integer id;
	private Integer type;
	private String name;
	private String description;
	private Integer parent;
	private String parentName;
	private Integer child;
	private String childName;
	private Integer relationType;
	private Boolean selected;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getParent() {
		return parent;
	}
	public void setParent(Integer parent) {
		this.parent = parent;
	}
	
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public Integer getChild() {
		return child;
	}
	public void setChild(Integer child) {
		this.child = child;
	}
	public String getChildName() {
		return childName;
	}
	public void setChildName(String childName) {
		this.childName = childName;
	}
	public Integer getRelationType() {
		return relationType;
	}
	public void setRelationType(Integer relationType) {
		this.relationType = relationType;
	}
	public Boolean getSelected() {
		return selected;
	}
	public void setSelected(Boolean selected) {
		this.selected = selected;
	}
	public String toString()
	{
		return "id = "+id+"   type = " +type+ "  name = "+name+"  parent = "+parentName;
	}
}
