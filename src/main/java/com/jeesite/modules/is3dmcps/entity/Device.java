package com.jeesite.modules.is3dmcps.entity;

public class Device {
	private String deviceCodeName;		// 设备名称
	private String deviceNo;		// 设备编号
	private String model;		// 型号规格
	private String code;		// 设备代码
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
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
