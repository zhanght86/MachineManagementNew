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
 * Checkrecorda generated by hbm2java
 */
@Entity
@Table(name = "checkrecorda", catalog = "machinemanagement")
public class Checkrecorda implements java.io.Serializable {

	private Integer id;
	private Checkinfoa checkinfoa;
	private String os;
	private Integer monthNumber;
	private String patch;
	private String application;
	private String dataBaseCheck;
	private String check360;
	private String antivirus;
	private String passwordStrength;
	private String accountNormal;
	private String accountAbnormal;
	private String eventLog;
	private String webLog;
	private String dataBaseLog;
	private String hardDriverUsage;
	private String pageException;
	private String reactionaryException;
	private String patchScanExist;
	private String patchScanHandle;
	private String patchScanOperator;
	private String service;
	private String osresponsibleSingnature;
	private String osadminsitratorSignature;
	private String checkstate;
	private String state;

	public Checkrecorda() {
	}

	public Checkrecorda(Checkinfoa checkinfoa) {
		this.checkinfoa = checkinfoa;
	}

	public Checkrecorda(Checkinfoa checkinfoa, String os, Integer monthNumber, String patch, String application, String dataBaseCheck, String check360, String antivirus, String passwordStrength, String accountNormal, String accountAbnormal, String eventLog, String webLog, String dataBaseLog, String hardDriverUsage, String pageException, String reactionaryException, String patchScanExist, String patchScanHandle, String patchScanOperator, String service, String osresponsibleSingnature, String osadminsitratorSignature, String checkstate, String state) {
       this.checkinfoa = checkinfoa;
       this.os = os;
       this.monthNumber = monthNumber;
       this.patch = patch;
       this.application = application;
       this.dataBaseCheck = dataBaseCheck;
       this.check360 = check360;
       this.antivirus = antivirus;
       this.passwordStrength = passwordStrength;
       this.accountNormal = accountNormal;
       this.accountAbnormal = accountAbnormal;
       this.eventLog = eventLog;
       this.webLog = webLog;
       this.dataBaseLog = dataBaseLog;
       this.hardDriverUsage = hardDriverUsage;
       this.pageException = pageException;
       this.reactionaryException = reactionaryException;
       this.patchScanExist = patchScanExist;
       this.patchScanHandle = patchScanHandle;
       this.patchScanOperator = patchScanOperator;
       this.service = service;
       this.osresponsibleSingnature = osresponsibleSingnature;
       this.osadminsitratorSignature = osadminsitratorSignature;
       this.checkstate = checkstate;
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
	@JoinColumn(name = "CheckInfoID", nullable = false)
	public Checkinfoa getCheckinfoa() {
		return this.checkinfoa;
	}

	public void setCheckinfoa(Checkinfoa checkinfoa) {
		this.checkinfoa = checkinfoa;
	}

	@Column(name = "os", length = 100)
	public String getOs() {
		return this.os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	@Column(name = "MonthNumber")
	public Integer getMonthNumber() {
		return this.monthNumber;
	}

	public void setMonthNumber(Integer monthNumber) {
		this.monthNumber = monthNumber;
	}

	@Column(name = "Patch")
	public String getPatch() {
		return this.patch;
	}

	public void setPatch(String patch) {
		this.patch = patch;
	}

	@Column(name = "Application", length = 100)
	public String getApplication() {
		return this.application;
	}

	public void setApplication(String application) {
		this.application = application;
	}

	@Column(name = "DataBaseCheck", length = 100)
	public String getDataBaseCheck() {
		return this.dataBaseCheck;
	}

	public void setDataBaseCheck(String dataBaseCheck) {
		this.dataBaseCheck = dataBaseCheck;
	}

	@Column(name="360Check", length=100)
    public String getCheck360() {
        return this.check360;
    }

	public void setCheck360(String check360) {
        this.check360 = check360;
    }

	@Column(name = "Antivirus", length = 100)
	public String getAntivirus() {
		return this.antivirus;
	}

	public void setAntivirus(String antivirus) {
		this.antivirus = antivirus;
	}

	@Column(name = "PasswordStrength")
	public String getPasswordStrength() {
		return this.passwordStrength;
	}

	public void setPasswordStrength(String passwordStrength) {
		this.passwordStrength = passwordStrength;
	}

	@Column(name = "AccountNormal", length = 100)
	public String getAccountNormal() {
		return this.accountNormal;
	}

	public void setAccountNormal(String accountNormal) {
		this.accountNormal = accountNormal;
	}

	@Column(name = "AccountAbnormal", length = 100)
	public String getAccountAbnormal() {
		return this.accountAbnormal;
	}

	public void setAccountAbnormal(String accountAbnormal) {
		this.accountAbnormal = accountAbnormal;
	}

	@Column(name = "EventLog", length = 100)
	public String getEventLog() {
		return this.eventLog;
	}

	public void setEventLog(String eventLog) {
		this.eventLog = eventLog;
	}

	@Column(name = "WebLog", length = 100)
	public String getWebLog() {
		return this.webLog;
	}

	public void setWebLog(String webLog) {
		this.webLog = webLog;
	}

	@Column(name = "DataBaseLog", length = 100)
	public String getDataBaseLog() {
		return this.dataBaseLog;
	}

	public void setDataBaseLog(String dataBaseLog) {
		this.dataBaseLog = dataBaseLog;
	}

	@Column(name = "HardDriverUsage", length = 100)
	public String getHardDriverUsage() {
		return this.hardDriverUsage;
	}

	public void setHardDriverUsage(String hardDriverUsage) {
		this.hardDriverUsage = hardDriverUsage;
	}

	@Column(name = "PageException")
	public String getPageException() {
		return this.pageException;
	}

	public void setPageException(String pageException) {
		this.pageException = pageException;
	}

	@Column(name = "ReactionaryException")
	public String getReactionaryException() {
		return this.reactionaryException;
	}

	public void setReactionaryException(String reactionaryException) {
		this.reactionaryException = reactionaryException;
	}

	@Column(name = "PatchScanExist")
	public String getPatchScanExist() {
		return this.patchScanExist;
	}

	public void setPatchScanExist(String patchScanExist) {
		this.patchScanExist = patchScanExist;
	}

	@Column(name = "PatchScanHandle")
	public String getPatchScanHandle() {
		return this.patchScanHandle;
	}

	public void setPatchScanHandle(String patchScanHandle) {
		this.patchScanHandle = patchScanHandle;
	}

	@Column(name = "PatchScanOperator")
	public String getPatchScanOperator() {
		return this.patchScanOperator;
	}

	public void setPatchScanOperator(String patchScanOperator) {
		this.patchScanOperator = patchScanOperator;
	}

	@Column(name = "Service")
	public String getService() {
		return this.service;
	}

	public void setService(String service) {
		this.service = service;
	}

	@Column(name = "OSResponsibleSingnature", length = 100)
	public String getOsresponsibleSingnature() {
		return this.osresponsibleSingnature;
	}

	public void setOsresponsibleSingnature(String osresponsibleSingnature) {
		this.osresponsibleSingnature = osresponsibleSingnature;
	}

	@Column(name = "OSAdminsitratorSignature", length = 100)
	public String getOsadminsitratorSignature() {
		return this.osadminsitratorSignature;
	}

	public void setOsadminsitratorSignature(String osadminsitratorSignature) {
		this.osadminsitratorSignature = osadminsitratorSignature;
	}

	@Column(name = "checkstate", length = 100)
	public String getCheckstate() {
		return this.checkstate;
	}

	public void setCheckstate(String checkstate) {
		this.checkstate = checkstate;
	}

	@Column(name = "state", length = 100)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
