package com.oles.domain.project;

import com.oles.domain.Teacher;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import java.io.Serializable;

/**
 * 课题
 * @generated
 */
 
@javax.persistence.Entity
public class GraduateProject implements Serializable {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;
	private String title;
	@javax.persistence.ManyToOne
	@JoinColumn(name = "teacher_id")
	private Teacher teacher;
	@javax.persistence.ManyToOne
	@JoinColumn(name = "graduateProjectCategory_id")
	private GraduateProjectCategory graduateProjectCategory;
	@javax.persistence.ManyToOne
	@JoinColumn(name = "graduateProjectStatus_id")
	private GraduateProjectStatus graduateProjectStatus;
	@javax.persistence.ManyToOne
	@JoinColumn(name = "graduateProjectType_id")
	private GraduateProjectType graduateProjectType;

	public GraduateProject() {
	}

	public GraduateProject(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public GraduateProjectCategory getGraduateProjectCategory() {
		return graduateProjectCategory;
	}

	public void setGraduateProjectCategory(GraduateProjectCategory graduateProjectCategory) {
		this.graduateProjectCategory = graduateProjectCategory;
	}

	public GraduateProjectStatus getGraduateProjectStatus() {
		return graduateProjectStatus;
	}

	public void setGraduateProjectStatus(GraduateProjectStatus graduateProjectStatus) {
		this.graduateProjectStatus = graduateProjectStatus;
	}

	public GraduateProjectType getGraduateProjectType() {
		return graduateProjectType;
	}

	public void setGraduateProjectType(GraduateProjectType graduateProjectType) {
		this.graduateProjectType = graduateProjectType;
	}
}

