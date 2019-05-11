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
 * 保养点Entity
 * @author xx
 * @version 2019-03-06
 */
@Table(name="is_maintain", alias="a", columns={
		@Column(name="id", attrName="id", label="ID", isPK=true),
		@Column(name="name", attrName="name", label="保养名称", queryType=QueryType.LIKE),
		@Column(name="device_code_id", attrName="deviceCodeId", label="设备代码ID"),
		@Column(name="device_code_name", attrName="deviceCodeName", label="设备代码名称"),
		@Column(name="type", attrName="type", label="保养类型"),
		@Column(name="content", attrName="content", label="保养内容"),
		@Column(name="interval", attrName="interval", label="间隔时间"),
		@Column(name="knowledge_id", attrName="knowledgeId", label="操作手册"),
		@Column(includeEntity=DataEntity.class),
/*		},  joinTable={
			@JoinTable(type=Type.LEFT_JOIN, entity=IsDeviceCode.class, alias="c", 
				on="c.id = a.device_code_id",
				columns={@Column(includeEntity=IsDeviceCode.class)}),
*/	}, orderBy="a.device_code_id,a.type"
)
public class IsMaintain extends DataEntity<IsMaintain> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 保养名称
	private String deviceCodeId;		// 设备代码ID
	private String deviceCodeName;		// 设备代码名称
	private String type;		// 保养类型
	private String content;		// 保养内容
	private Integer interval;		// 间隔时间
	private String knowledgeId;		// 操作手册
	
	private IsDeviceCode isDeviceCode;
	
	public IsMaintain() {
		this(null);
	}

	public IsMaintain(String id){
		super(id);
	}
	
	@NotBlank(message="保养名称不能为空")
	@Length(min=0, max=60, message="保养名称长度不能超过 60 个字符")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@NotBlank(message="设备代码ID不能为空")
	@Length(min=0, max=64, message="设备代码ID长度不能超过 64 个字符")
	public String getDeviceCodeId() {
		return deviceCodeId;
	}

	public void setDeviceCodeId(String deviceCodeId) {
		this.deviceCodeId = deviceCodeId;
	}
	
	@NotBlank(message="保养类型不能为空")
	@Length(min=0, max=2, message="保养类型长度不能超过 2 个字符")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=500, message="保养内容长度不能超过 500 个字符")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public Integer getInterval() {
		return interval;
	}

	public void setInterval(Integer interval) {
		this.interval = interval;
	}
	
	@Length(min=0, max=64, message="操作手册长度不能超过 64 个字符")
	public String getKnowledgeId() {
		return knowledgeId;
	}

	public void setKnowledgeId(String knowledgeId) {
		this.knowledgeId = knowledgeId;
	}

	public IsDeviceCode getIsDeviceCode() {
		return isDeviceCode;
	}

	public void setIsDeviceCode(IsDeviceCode isDeviceCode) {
		this.isDeviceCode = isDeviceCode;
	}

	public String getDeviceCodeName() {
		return deviceCodeName;
	}

	public void setDeviceCodeName(String deviceCodeName) {
		this.deviceCodeName = deviceCodeName;
	}
	
}