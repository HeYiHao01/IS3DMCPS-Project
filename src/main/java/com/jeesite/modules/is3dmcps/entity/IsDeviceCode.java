/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.is3dmcps.entity;

import org.hibernate.validator.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.entity.TreeEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 设备代码Entity
 * @author xx
 * @version 2019-03-03
 */
@Table(name="Is_Device_Code", alias="a", columns={
		@Column(name="id", attrName="id", label="ID", isPK=true),
		@Column(name="code", attrName="code", label="设备代码"),
		@Column(name="name", attrName="name", label="设备名称", queryType=QueryType.LIKE, isTreeName=true),
		@Column(name="type", attrName="type", label="设备类别"),
		@Column(name="model", attrName="model", label="型号规格"),
		@Column(name="parameters", attrName="parameters", label="设备参数"),
		@Column(name="application", attrName="application", label="设备用途"),
		@Column(name="manufacturer", attrName="manufacturer", label="制造厂商"),
		@Column(name="life", attrName="life", label="使用年限"),
		@Column(name="depreciation", attrName="depreciation", label="年折旧率"),
		@Column(name="model_url", attrName="modelUrl", label="三维模型"),
		@Column(includeEntity=TreeEntity.class),
		@Column(includeEntity=DataEntity.class),
	}, orderBy="code"
)
public class IsDeviceCode extends TreeEntity<IsDeviceCode> {
	
	private static final long serialVersionUID = 1L;
	private String code;		// 设备代码
	private String name;		// 设备名称
	private String type;		// 设备类别
	private String model;		// 型号规格
	private String parameters;		// 设备参数
	private String application;		// 设备用途
	private String manufacturer;		// 制造厂商
	private String life;		// 使用年限
	private String depreciation;		// 年折旧率
	private String modelUrl;		// 三维模型
	
	public IsDeviceCode() {
		this(null);
	}

	public IsDeviceCode(String id){
		super(id);
	}
	
	@Override
	public IsDeviceCode getParent() {
		return parent;
	}

	@Override
	public void setParent(IsDeviceCode parent) {
		this.parent = parent;
	}
	
	@NotBlank(message="设备代码不能为空")
	@Length(min=0, max=60, message="设备代码长度不能超过 60 个字符")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@NotBlank(message="设备名称不能为空")
	@Length(min=0, max=60, message="设备名称长度不能超过 60 个字符")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=2, message="设备类别长度不能超过 2 个字符")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=60, message="型号规格长度不能超过 60 个字符")
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}
	
	@Length(min=0, max=500, message="设备参数长度不能超过 500 个字符")
	public String getParameters() {
		return parameters;
	}

	public void setParameters(String parameters) {
		this.parameters = parameters;
	}
	
	@Length(min=0, max=60, message="设备用途长度不能超过 60 个字符")
	public String getApplication() {
		return application;
	}

	public void setApplication(String application) {
		this.application = application;
	}
	
	@Length(min=0, max=60, message="制造厂商长度不能超过 60 个字符")
	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	
	@Length(min=0, max=10, message="使用年限长度不能超过 10 个字符")
	public String getLife() {
		return life;
	}

	public void setLife(String life) {
		this.life = life;
	}
	
	@Length(min=0, max=10, message="年折旧率长度不能超过 10 个字符")
	public String getDepreciation() {
		return depreciation;
	}

	public void setDepreciation(String depreciation) {
		this.depreciation = depreciation;
	}
	
	@Length(min=0, max=200, message="三维模型长度不能超过 200 个字符")
	public String getModelUrl() {
		return modelUrl;
	}

	public void setModelUrl(String modelUrl) {
		this.modelUrl = modelUrl;
	}
	
}