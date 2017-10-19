package com.oles.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import java.util.Set;



/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */
 
@javax.persistence.Entity 
public class TestDetail {

	@javax.persistence.Id
	private Long id;

	private String kind;

	private Integer fullScore;

	private String contents;

	@javax.persistence.ManyToOne
	@JoinColumn(name = "test_id")
	private Test test;

	@JsonIgnore
	@javax.persistence.OneToMany(mappedBy = "testDetail",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<StudentTestDetail> studentTest;


	public TestDetail(){
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public Integer getFullScore() {
		return fullScore;
	}

	public void setFullScore(Integer fullScore) {
		this.fullScore = fullScore;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public Test getTest() {
		return test;
	}

	public void setTest(Test test) {
		this.test = test;
	}

	public Set<StudentTestDetail> getStudentTest() {
		return studentTest;
	}

	public void setStudentTest(Set<StudentTestDetail> studentTest) {
		this.studentTest = studentTest;
	}
}

