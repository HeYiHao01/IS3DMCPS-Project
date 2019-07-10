package com.jeesite.modules.is3dmcps.entity;

public class Device {
	private String deviceCodeName;		// 设备名称
	private String deviceNo;		// 设备编号
	private String model;		// 型号规格
	public String getDeviceCodeName() {
		return deviceCodeName;
	}
	public void setDeviceCodeName(String deviceCodeName) {
		this.deviceCodeName = deviceCodeName;
	}
	public String getDeviceNo() {
		return deviceNo;
	}
	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
}
