package com.org.sg.POJO.action;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * AssociatedProjects entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "associated_projects", catalog = "scenariogenerator")
public class AssociatedProjects implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -7420819963028512861L;
	private Integer id;
	private Project projectByTeacherProject;
	private Project projectByAdminProject;
	private User userByAdmin;
	private User userByTeacher;
	private Boolean modified;
	private Date datecreated;
	private String comments;

	// Constructors

	/** default constructor */
	public AssociatedProjects() {
	}

	/** minimal constructor */
	public AssociatedProjects(Project projectByTeacherProject, Project projectByAdminProject, User userByAdmin, User userByTeacher) {
		this.projectByTeacherProject = projectByTeacherProject;
		this.projectByAdminProject = projectByAdminProject;
		this.userByAdmin = userByAdmin;
		this.userByTeacher = userByTeacher;
	}

	/** full constructor */
	public AssociatedProjects(Project projectByTeacherProject, Project projectByAdminProject, User userByAdmin, User userByTeacher, Boolean modified, Date datecreated,
			String comments) {
		this.projectByTeacherProject = projectByTeacherProject;
		this.projectByAdminProject = projectByAdminProject;
		this.userByAdmin = userByAdmin;
		this.userByTeacher = userByTeacher;
		this.modified = modified;
		this.datecreated = datecreated;
		this.comments = comments;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "teacher_project", nullable = false)
	public Project getProjectByTeacherProject() {
		return this.projectByTeacherProject;
	}

	public void setProjectByTeacherProject(Project projectByTeacherProject) {
		this.projectByTeacherProject = projectByTeacherProject;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "admin_project", nullable = false)
	public Project getProjectByAdminProject() {
		return this.projectByAdminProject;
	}

	public void setProjectByAdminProject(Project projectByAdminProject) {
		this.projectByAdminProject = projectByAdminProject;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "admin", nullable = false)
	public User getUserByAdmin() {
		return this.userByAdmin;
	}

	public void setUserByAdmin(User userByAdmin) {
		this.userByAdmin = userByAdmin;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "teacher", nullable = false)
	public User getUserByTeacher() {
		return this.userByTeacher;
	}

	public void setUserByTeacher(User userByTeacher) {
		this.userByTeacher = userByTeacher;
	}

	@Column(name = "modified")
	public Boolean getModified() {
		return this.modified;
	}

	public void setModified(Boolean modified) {
		this.modified = modified;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "datecreated", length = 10)
	public Date getDatecreated() {
		return this.datecreated;
	}

	public void setDatecreated(Date datecreated) {
		this.datecreated = datecreated;
	}

	@Column(name = "comments")
	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

}