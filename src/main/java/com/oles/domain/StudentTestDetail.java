package com.oles.domain;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import java.io.Serializable;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */
 
@javax.persistence.Entity 
public class StudentTestDetail implements Serializable {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;

	private String answerWithAlgorithm;

	@javax.persistence.Column(nullable = true)
	private Boolean answerWithAlgorithmSign;

	private String answerWithCode;

	@javax.persistence.Column(nullable = true)
	private Boolean answerWithCodeSign;

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

	public StudentTestDetail(Student student, TestDetail testDetail) {
		this.student = student;
		this.testDetail = testDetail;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getAnswerWithAlgorithmSign() {
		return answerWithAlgorithmSign;
	}

	public void setAnswerWithAlgorithmSign(Boolean answerWithAlgorithmSign) {
		this.answerWithAlgorithmSign = answerWithAlgorithmSign;
	}

	public Boolean getAnswerWithCodeSign() {
		return answerWithCodeSign;
	}

	public void setAnswerWithCodeSign(Boolean answerWithCodeSign) {
		this.answerWithCodeSign = answerWithCodeSign;
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

