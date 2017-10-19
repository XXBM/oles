package com.oles.domain;


import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DynamicInsert(true)
@DynamicUpdate(true)
public abstract class User implements Serializable {
	@javax.persistence.Column(nullable = false)
	protected String userName;

	 
	@javax.persistence.Column(nullable = false) 
	protected String password;


	@ManyToOne
	@JoinColumn(name = "role_id")
	protected Role role;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	protected Long id;


	public User(){
		super();
	}
	public User(User user) {
		this.id = user.id;
		this.password = user.password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}
}

