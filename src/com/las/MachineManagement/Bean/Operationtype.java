package com.las.MachineManagement.Bean;
// Generated 2017-7-3 9:45:31 by Hibernate Tools 5.2.3.Final

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
 * Operationtype generated by hbm2java
 */
@Entity
@Table(name = "operationtype", catalog = "machinemanagement")
public class Operationtype implements java.io.Serializable {

	private Integer id;
	private String typeCode;
	private String typeName;
	private String typeDescription;
	private Date updateTime;
	private String state;

	public Operationtype() {
	}

	public Operationtype(String typeCode, String typeName, String typeDescription, Date updateTime, String state) {
		this.typeCode = typeCode;
		this.typeName = typeName;
		this.typeDescription = typeDescription;
		this.updateTime = updateTime;
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

	@Column(name = "TypeCode")
	public String getTypeCode() {
		return this.typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	@Column(name = "TypeName")
	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	@Column(name = "TypeDescription", length = 2000)
	public String getTypeDescription() {
		return this.typeDescription;
	}

	public void setTypeDescription(String typeDescription) {
		this.typeDescription = typeDescription;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UpdateTime", length = 19)
	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "State")
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
