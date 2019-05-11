/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.is3dmcps.entity;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;

/**
 * 知识库Entity
 * @author xx
 * @version 2019-03-06
 */
@Table(name="is_knowledge", alias="a", columns={
		@Column(name="id", attrName="id", label="ID", isPK=true),
		@Column(name="device_code_id", attrName="deviceCodeId", label="设备代码ID"),
		@Column(name="device_code_name", attrName="deviceCodeName", label="设备代码名称"),
		@Column(name="type", attrName="type", label="知识类型"),
		@Column(name="title", attrName="title", label="知识标题"),
		@Column(name="content", attrName="content", label="知识内容"),
		@Column(name="media_type", attrName="mediaType", label="多媒体类型"),
		@Column(name="media_url", attrName="mediaUrl", label="多媒体地址"),
		@Column(includeEntity=DataEntity.class),
	}, orderBy="a.update_date DESC"
)
public class IsKnowledge extends DataEntity<IsKnowledge> {
	
	private static final long serialVersionUID = 1L;
	private String deviceCodeId;		// 设备代码ID
	private String deviceCodeName;		// 设备代码名称
	private String type;		// 知识类型
	private String knowledgeCode;		// 知识点代码
	private String title;		// 知识标题
	private String content;		// 知识内容
	private String mediaType;		// 多媒体类型
	private String mediaUrl;		// 多媒体地址
	
	private IsDeviceCode isDeviceCode;
	public IsKnowledge() {
		this(null);
	}

	public IsKnowledge(String id){
		super(id);
	}
	
	@Length(min=0, max=64, message="设备代码ID长度不能超过 64 个字符")
	public String getDeviceCodeId() {
		return deviceCodeId;
	}

	public void setDeviceCodeId(String deviceCodeId) {
		this.deviceCodeId = deviceCodeId;
	}
	
	@NotBlank(message="知识类型不能为空")
	@Length(min=0, max=2, message="知识类型长度不能超过 2 个字符")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=2, message="知识点代码长度不能超过 2 个字符")
	public String getKnowledgeCode() {
		return knowledgeCode;
	}

	public void setKnowledgeCode(String knowledgeCode) {
		this.knowledgeCode = knowledgeCode;
	}
	
	@NotBlank(message="知识标题不能为空")
	@Length(min=0, max=400, message="知识标题长度不能超过 400 个字符")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Length(min=0, max=1, message="多媒体类型长度不能超过 1 个字符")
	public String getMediaType() {
		return mediaType;
	}

	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}
	
	@Length(min=0, max=200, message="多媒体地址长度不能超过 200 个字符")
	public String getMediaUrl() {
		return mediaUrl;
	}

	public void setMediaUrl(String mediaUrl) {
		this.mediaUrl = mediaUrl;
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