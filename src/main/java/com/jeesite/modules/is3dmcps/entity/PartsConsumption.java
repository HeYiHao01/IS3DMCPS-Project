package com.jeesite.modules.is3dmcps.entity;

/**
 * 备件消耗实体类
 * @author HAO
 *
 */
public class PartsConsumption {
	private String deviceCodeName;
	private String model;
	private String deviceNo;
	private String repairTime;
	private String operator;
	private String results;
	public String getDeviceCodeName() {
		return deviceCodeName;
	}
	public void setDeviceCodeName(String deviceCodeName) {
		this.deviceCodeName = deviceCodeName;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getDeviceNo() {
		return deviceNo;
	}
	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}
	public String getRepairTime() {
		return repairTime;
	}
	public void setRepairTime(String repairTime) {
		this.repairTime = repairTime;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getResults() {
		return results;
	}
	public void setResults(String results) {
		this.results = results;
	}
}
