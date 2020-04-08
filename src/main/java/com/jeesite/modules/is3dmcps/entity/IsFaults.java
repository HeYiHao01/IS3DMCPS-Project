/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.is3dmcps.entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 设备故障Entity
 * @author xx
 * @version 2019-03-08
 */
@Table(name="is_faults", alias="a", columns={
		@Column(name="id", attrName="id", label="ID", isPK=true),
		@Column(name="name", attrName="name", label="故障名称", queryType=QueryType.LIKE),
		@Column(name="device_id", attrName="deviceId", label="设备ID"),
		@Column(name="device_name", attrName="deviceName", label="设备名称"),
		@Column(name="faults_code", attrName="faultsCode", label="故障代码"),
		@Column(name="faults_time", attrName="faultsTime", label="故障时间"),
		@Column(name="operator", attrName="operator", label="报修人"),
		@Column(name="reason", attrName="reason", label="故障原因"),
		@Column(name="knowledge_id", attrName="knowledgeId", label="推荐方案"),
		@Column(includeEntity=DataEntity.class),
	}, orderBy="a.faults_time DESC"
)
public class IsFaults extends DataEntity<IsFaults> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 故障名称
	private String deviceId;		// 设备ID
	private String deviceName;		// 设备名称
	private String faultsCode;		// 故障代码
	private Date faultsTime;		// 故障时间
	private String operator;		// 报修人
	private String reason;		// 故障原因
	private String knowledgeId;		// 推荐方案
	
	public IsFaults() {
		this(null);
	}

	public IsFaults(String id){
		super(id);
	}
	
	@NotBlank(message="故障名称不能为空")
	@Length(min=0, max=64, message="故障名称长度不能超过 64 个字符")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@NotBlank(message="设备ID不能为空")
	@Length(min=0, max=64, message="设备ID长度不能超过 64 个字符")
	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	
	@Length(min=0, max=80, message="设备名称长度不能超过 80 个字符")
	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	
	@Length(min=0, max=20, message="故障代码长度不能超过 20 个字符")
	public String getFaultsCode() {
		return faultsCode;
	}

	public void setFaultsCode(String faultsCode) {
		this.faultsCode = faultsCode;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getFaultsTime() {
		return faultsTime;
	}

	public void setFaultsTime(Date faultsTime) {
		this.faultsTime = faultsTime;
	}
	
	@Length(min=0, max=20, message="报修人长度不能超过 20 个字符")
	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}
	
	@Length(min=0, max=200, message="故障原因长度不能超过 200 个字符")
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
	@Length(min=0, max=64, message="推荐方案长度不能超过 64 个字符")
	public String getKnowledgeId() {
		return knowledgeId;
	}

	public void setKnowledgeId(String knowledgeId) {
		this.knowledgeId = knowledgeId;
	}

	public Date getFaultsTime_gte() {
		return sqlMap.getWhere().getValue("faults_time", QueryType.GTE);
	}

	public void setFaultsTime_gte(Date faultsTime) {
		sqlMap.getWhere().and("faults_time", QueryType.GTE, faultsTime);
	}
	
	public Date getFaultsTime_lte() {
		return sqlMap.getWhere().getValue("faults_time", QueryType.LTE);
	}

	public void setFaultsTime_lte(Date faultsTime) {
		sqlMap.getWhere().and("faults_time", QueryType.LTE, faultsTime);
	}

	public IsFaults(String name, String deviceId, String deviceName, String faultsCode, Date faultsTime, String operator, String reason, String knowledgeId) {
		this.name = name;
		this.deviceId = deviceId;
		this.deviceName = deviceName;
		this.faultsCode = faultsCode;
		this.faultsTime = faultsTime;
		this.operator = operator;
		this.reason = reason;
		this.knowledgeId = knowledgeId;
	}

	public IsFaults(String name, String deviceId, String deviceName, String faultsCode, Date faultsTime, String operator, String reason, String knowledgeId, String remark) {
		this.name = name;
		this.deviceId = deviceId;
		this.deviceName = deviceName;
		this.faultsCode = faultsCode;
		this.faultsTime = faultsTime;
		this.operator = operator;
		this.reason = reason;
		this.knowledgeId = knowledgeId;
		this.remarks = remark;
		this.status = "0";
	}
}