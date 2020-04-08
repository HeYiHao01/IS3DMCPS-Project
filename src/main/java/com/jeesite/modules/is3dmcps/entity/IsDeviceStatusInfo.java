/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.is3dmcps.entity;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * is_device_status_infoEntity
 * @author hh
 * @version 2020-04-08
 */
@Table(name="is_device_status_info", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="en_field", attrName="enField", label="英文字段", isPK=true),
		@Column(name="zh_field", attrName="zhField", label="中文字段"),
		@Column(name="device_name", attrName="deviceName", label="设备名称", queryType=QueryType.LIKE),
		@Column(name="remark", attrName="remark", label="备注"),
	}, orderBy="a.id DESC, a.en_field DESC"
)
public class IsDeviceStatusInfo extends DataEntity<IsDeviceStatusInfo> {
	
	private static final long serialVersionUID = 1L;
	private String enField;		// 英文字段
	private String zhField;		// 中文字段
	private String deviceName;		// 设备名称
	private String remark;		// 备注		

	public IsDeviceStatusInfo(String id, String enField){
		this.id = id;
		this.enField = enField;
	}
	
	public IsDeviceStatusInfo() {
		super();
	}

	public String getEnField() {
		return enField;
	}

	public void setEnField(String enField) {
		this.enField = enField;
	}
	
	@NotBlank(message="中文字段不能为空")
	@Length(min=0, max=255, message="中文字段长度不能超过 255 个字符")
	public String getZhField() {
		return zhField;
	}

	public void setZhField(String zhField) {
		this.zhField = zhField;
	}
	
	@Length(min=0, max=60, message="设备名称长度不能超过 60 个字符")
	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	
	@Length(min=0, max=255, message="备注长度不能超过 255 个字符")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}