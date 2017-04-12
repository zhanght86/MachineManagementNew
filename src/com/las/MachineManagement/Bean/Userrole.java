package com.las.MachineManagement.Bean;
// Generated 2017-4-12 10:59:31 by Hibernate Tools 5.2.1.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Userrole generated by hbm2java
 */
@Entity
@Table(name = "userrole", catalog = "machinemanagement")
public class Userrole implements java.io.Serializable {

	private Integer id;
	private Integer parentroleid;
	private String rolename;
	private String description;
	private Date updatetime;
	private String state;

	public Userrole() {
	}

	public Userrole(Integer parentroleid, String rolename, String description, Date updatetime, String state) {
		this.parentroleid = parentroleid;
		this.rolename = rolename;
		this.description = description;
		this.updatetime = updatetime;
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

	@Column(name = "parentroleid")
	public Integer getParentroleid() {
		return this.parentroleid;
	}

	public void setParentroleid(Integer parentroleid) {
		this.parentroleid = parentroleid;
	}

	@Column(name = "rolename", length = 200)
	public String getRolename() {
		return this.rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	@Column(name = "description", length = 100)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updatetime", length = 19)
	public Date getUpdatetime() {
		return this.updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	@Column(name = "state", length = 100)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
