package com.oles.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.oles.jsonDeserialize.CustomDateSerializer;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;



/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */
 
@javax.persistence.Entity 
public class Test implements Serializable {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;

	@JsonSerialize(using = CustomDateSerializer.class)
	@javax.persistence.Temporal(javax.persistence.TemporalType.DATE)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Calendar dateTime;

	private String name;

	@javax.persistence.Column(nullable = true)
	private Boolean isToConduct;

	@JsonIgnore
	@javax.persistence.OneToMany(mappedBy = "test",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<TestDetail> testDetail;


	public Test(){
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Calendar getDateTime() {
		return dateTime;
	}

	public void setDateTime(Calendar dateTime) {
		this.dateTime = dateTime;
	}

	public Boolean getToConduct() {
		return isToConduct;
	}

	public void setToConduct(Boolean toConduct) {
		isToConduct = toConduct;
	}

	public List<TestDetail> getTestDetail() {
		return testDetail;
	}

	public void setTestDetail(List<TestDetail> testDetail) {
		this.testDetail = testDetail;
	}
}

