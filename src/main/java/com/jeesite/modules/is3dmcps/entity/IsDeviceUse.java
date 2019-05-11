/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.is3dmcps.entity;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 设备使用记录Entity
 * @author xx
 * @version 2019-03-12
 */
@Table(name="is_device_use", alias="a", columns={
		@Column(name="id", attrName="id", label="ID", isPK=true),
		@Column(name="part_id", attrName="partId", label="零部件ID"),
		@Column(name="part_name", attrName="partName", label="零部件名称"),
		@Column(name="device_id", attrName="deviceId", label="设备ID"),
		@Column(name="device_name", attrName="deviceName", label="设备名称"),
		@Column(name="type", attrName="type", label="操作类型"),
		@Column(name="operator", attrName="operator", label="操作人员"),
		@Column(name="operate_time", attrName="operateTime", label="操作时间"),
		@Column(includeEntity=DataEntity.class),
	}, orderBy="a.update_date DESC"
)
public class IsDeviceUse extends DataEntity<IsDeviceUse> {
	
	private static final long serialVersionUID = 1L;
	private String partId;		// 设备ID
	private String partName;		// 设备名称
	private String deviceId;		// 设备ID
	private String deviceName;		// 设备名称
	private String type;		// 操作类型
	private String operator;		// 操作人员
	private Date operateTime;		// 操作时间
	
	public IsDeviceUse() {
		this(null);
	}

	public IsDeviceUse(String id){
		super(id);
	}
	
	@NotBlank(message="设备ID不能为空")
	@Length(min=0, max=64, message="设备ID长度不能超过 64 个字符")
	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	
	@NotBlank(message="设备名称不能为空")
	@Length(min=0, max=80, message="设备名称长度不能超过 80 个字符")
	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	
	@NotBlank(message="操作类型不能为空")
	@Length(min=0, max=1, message="操作类型长度不能超过 1 个字符")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=20, message="操作人员长度不能超过 20 个字符")
	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}
	
	public Date getOperateTime_gte() {
		return sqlMap.getWhere().getValue("operate_time", QueryType.GTE);
	}

	public void setOperateTime_gte(Date operateTime) {
		sqlMap.getWhere().and("operate_time", QueryType.GTE, operateTime);
	}
	
	public Date getOperateTime_lte() {
		return sqlMap.getWhere().getValue("operate_time", QueryType.LTE);
	}

	public void setOperateTime_lte(Date operateTime) {
		sqlMap.getWhere().and("operate_time", QueryType.LTE, operateTime);
	}

	public String getPartId() {
		return partId;
	}

	public void setPartId(String partId) {
		this.partId = partId;
	}

	public String getPartName() {
		return partName;
	}

	public void setPartName(String partName) {
		this.partName = partName;
	}
	
}