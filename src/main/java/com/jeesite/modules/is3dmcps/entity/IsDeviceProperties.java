/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.is3dmcps.entity;

import org.hibernate.validator.constraints.Length;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * is_device_propertiesEntity
 * @author xx
 * @version 2019-05-10
 */
@Table(name="is_device_properties", alias="a", columns={
		@Column(name="device_id", attrName="deviceId", label="device_id", isPK=true),
		@Column(name="device_name", attrName="deviceName", label="device_name", queryType=QueryType.LIKE),
		@Column(name="property", attrName="property", label="property"),
		@Column(name="property_value", attrName="propertyValue", label="property_value"),
	}, orderBy="a.device_id DESC"
)
public class IsDeviceProperties extends DataEntity<IsDeviceProperties> {
	
	private static final long serialVersionUID = 1L;
	private String deviceId;		// device_id
	private String deviceName;		// device_name
	private String property;		// property
	private String propertyValue;		// property_value
	
	public IsDeviceProperties() {
		this(null);
	}

	public IsDeviceProperties(String id){
		super(id);
	}
	
	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	
	@Length(min=0, max=10, message="device_name长度不能超过 10 个字符")
	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	
	@Length(min=0, max=16, message="property长度不能超过 16 个字符")
	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}
	
	@Length(min=0, max=60, message="property_value长度不能超过 60 个字符")
	public String getPropertyValue() {
		return propertyValue;
	}

	public void setPropertyValue(String propertyValue) {
		this.propertyValue = propertyValue;
	}
	
}