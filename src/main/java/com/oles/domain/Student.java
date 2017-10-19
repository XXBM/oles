package com.oles.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import java.util.Set;



/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */
 
@javax.persistence.Entity 
public class Student extends User {

	private String idNo;

	@JsonIgnore
	@javax.persistence.OneToMany(mappedBy = "student",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<StudentTestDetail> studentTest;

	public Student(){
		super();
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public Set<StudentTestDetail> getStudentTest() {
		return studentTest;
	}

	public void setStudentTest(Set<StudentTestDetail> studentTest) {
		this.studentTest = studentTest;
	}
}

