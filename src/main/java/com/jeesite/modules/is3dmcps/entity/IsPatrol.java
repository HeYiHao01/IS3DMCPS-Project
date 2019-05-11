/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.is3dmcps.entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 设备巡检点Entity
 * @author xx
 * @version 2019-03-07
 */
@Table(name="is_patrol", alias="a", columns={
		@Column(name="id", attrName="id", label="ID", isPK=true),
		@Column(name="name", attrName="name", label="巡检点名称", queryType=QueryType.LIKE),
		@Column(name="sort_no", attrName="sortNo", label="巡检序号"),
		@Column(name="device_id", attrName="deviceId", label="设备ID"),
		@Column(name="device_name", attrName="deviceName", label="设备名称"),
		@Column(name="type", attrName="type", label="工作类型"),
		@Column(name="interval", attrName="interval", label="巡检间隔"),
		@Column(name="content", attrName="content", label="巡检内容"),
		@Column(name="knowledge_id", attrName="knowledgeId", label="巡检手册"),
		@Column(includeEntity=DataEntity.class),
	}, orderBy="a.sort_no"
)
public class IsPatrol extends DataEntity<IsPatrol> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 巡检点名称
	private Integer sortNo;		// 巡检序号
	private String deviceId;		// 设备ID
	private String deviceName;		// 设备名称
	private String type;		// 工作类型
	private Integer interval;		// 巡检间隔
	private String content;		// 巡检内容
	private String knowledgeId;		// 巡检手册
	
	public IsPatrol() {
		this(null);
	}

	public IsPatrol(String id){
		super(id);
	}
	
	@NotBlank(message="巡检名称不能为空")
	@Length(min=0, max=60, message="巡检点名称长度不能超过 60 个字符")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getSortNo() {
		return sortNo;
	}

	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}
	
	
	@Length(min=0, max=1, message="工作类型长度不能超过 1 个字符")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public Integer getInterval() {
		return interval;
	}

	public void setInterval(Integer interval) {
		this.interval = interval;
	}
	
	@Length(min=0, max=200, message="巡检内容长度不能超过 200 个字符")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Length(min=0, max=64, message="巡检手册长度不能超过 64 个字符")
	public String getKnowledgeId() {
		return knowledgeId;
	}

	public void setKnowledgeId(String knowledgeId) {
		this.knowledgeId = knowledgeId;
	}

	@NotBlank(message="设备ID不能为空")
	@Length(min=0, max=64, message="设备代码ID长度不能超过 64 个字符")
	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}


}