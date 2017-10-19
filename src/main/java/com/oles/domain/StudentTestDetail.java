package com.oles.domain;


import javax.persistence.JoinColumn;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */
 
@javax.persistence.Entity 
public class StudentTestDetail
{
	 
	@javax.persistence.Id
	private Long id;

	private String answerWithAlgorithm;

	private String answerWithCode;

	private int gradeForAlgortithm;

	protected int gradeForCode;


	@javax.persistence.ManyToOne
	@JoinColumn(name = "student_id")
	private Student student;
	 
	@javax.persistence.ManyToOne
	@JoinColumn(name = "testDetail_id")
	private TestDetail testDetail;

	public StudentTestDetail(){
		super();
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAnswerWithAlgorithm() {
		return answerWithAlgorithm;
	}

	public void setAnswerWithAlgorithm(String answerWithAlgorithm) {
		this.answerWithAlgorithm = answerWithAlgorithm;
	}

	public String getAnswerWithCode() {
		return answerWithCode;
	}

	public void setAnswerWithCode(String answerWithCode) {
		this.answerWithCode = answerWithCode;
	}

	public int getGradeForAlgortithm() {
		return gradeForAlgortithm;
	}

	public void setGradeForAlgortithm(int gradeForAlgortithm) {
		this.gradeForAlgortithm = gradeForAlgortithm;
	}

	public int getGradeForCode() {
		return gradeForCode;
	}

	public void setGradeForCode(int gradeForCode) {
		this.gradeForCode = gradeForCode;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public TestDetail getTestDetail() {
		return testDetail;
	}

	public void setTestDetail(TestDetail testDetail) {
		this.testDetail = testDetail;
	}
}

