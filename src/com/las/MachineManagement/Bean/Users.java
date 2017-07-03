package com.las.MachineManagement.Bean;
// Generated 2017-7-3 9:45:31 by Hibernate Tools 5.2.3.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Users generated by hbm2java
 */
@Entity
@Table(name = "users", catalog = "machinemanagement")
public class Users implements java.io.Serializable {

	private Integer id;
	private Emailconfiguration emailconfiguration;
	private String name;
	private String age;
	private String gender;
	private String username;
	private String password;
	private String email;
	private String islogin;
	private String department;
	private String contactNumber;
	private String state;

	public Users() {
	}

	public Users(Emailconfiguration emailconfiguration, String name, String age, String gender, String username,
			String password, String email, String islogin, String department, String contactNumber, String state) {
		this.emailconfiguration = emailconfiguration;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.username = username;
		this.password = password;
		this.email = email;
		this.islogin = islogin;
		this.department = department;
		this.contactNumber = contactNumber;
		this.state = state;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EmailConfigurationID")
	public Emailconfiguration getEmailconfiguration() {
		return this.emailconfiguration;
	}

	public void setEmailconfiguration(Emailconfiguration emailconfiguration) {
		this.emailconfiguration = emailconfiguration;
	}

	@Column(name = "name", length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "age", length = 100)
	public String getAge() {
		return this.age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	@Column(name = "gender", length = 100)
	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Column(name = "username", length = 100)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "password", length = 100)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "email", length = 100)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "islogin", length = 10)
	public String getIslogin() {
		return this.islogin;
	}

	public void setIslogin(String islogin) {
		this.islogin = islogin;
	}

	@Column(name = "Department", length = 500)
	public String getDepartment() {
		return this.department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@Column(name = "ContactNumber", length = 100)
	public String getContactNumber() {
		return this.contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	@Column(name = "state", length = 100)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
