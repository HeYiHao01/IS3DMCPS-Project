package com.jeesite.modules.is3dmcps.entity;

public class NewDevice {
	private String id;
	private String code;		// 设备代码
	private String name;		// 设备名称
	private String type;		// 类别
	private String model;		// 型号规格
	private String parameters;		// 设备参数
	private String application;		// 设备用途
	private String manufacturer;		// 制造厂商
	private String life;		// 使用年限
	private String depreciation;		// 年折旧率	
	private String parentCode;      //父级编号
	private Integer stockWarn;		// 库存预警参数
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getParameters() {
		return parameters;
	}
	public void setParameters(String parameters) {
		this.parameters = parameters;
	}
	public String getApplication() {
		return application;
	}
	public void setApplication(String application) {
		this.application = application;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getLife() {
		return life;
	}
	public void setLife(String life) {
		this.life = life;
	}
	public String getDepreciation() {
		return depreciation;
	}
	public void setDepreciation(String depreciation) {
		this.depreciation = depreciation;
	}
	public String getParentCode() {
		return parentCode;
	}
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
	public Integer getStockWarn() {
		return stockWarn;
	}
	public void setStockWarn(Integer stockWarn) {
		this.stockWarn = stockWarn;
	}
	
}
