package com.oles.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.oles.jsonDeserialize.CustomDateSerializer;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;



/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */
 
@javax.persistence.Entity 
public class Test implements Serializable {
	@javax.persistence.Id
	private Long id;

	@JsonSerialize(using = CustomDateSerializer.class)
	@javax.persistence.Temporal(javax.persistence.TemporalType.DATE)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Calendar dateTime;

	@javax.persistence.Column(nullable = true)
	private boolean isToConduct;

	@JsonIgnore
	@javax.persistence.OneToMany(mappedBy = "test",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<TestDetail> testDetail;


	public Test(){
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Calendar getDateTime() {
		return dateTime;
	}

	public void setDateTime(Calendar dateTime) {
		this.dateTime = dateTime;
	}

	public boolean isToConduct() {
		return isToConduct;
	}

	public void setToConduct(boolean toConduct) {
		isToConduct = toConduct;
	}

	public Set<TestDetail> getTestDetail() {
		return testDetail;
	}

	public void setTestDetail(Set<TestDetail> testDetail) {
		this.testDetail = testDetail;
	}
}

