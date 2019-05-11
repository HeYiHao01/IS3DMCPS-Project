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
 * is_scene_device_componentEntity
 * @author xx
 * @version 2019-05-10
 */
@Table(name="is_scene_device_component", alias="a", columns={
		@Column(name="device_type_id", attrName="deviceTypeId", label="device_type_id", isPK=true),
		@Column(name="device_component_id", attrName="deviceComponentId", label="device_component_id"),
		@Column(name="scene_parent_path", attrName="sceneParentPath", label="scene_parent_path"),
	}, orderBy="a.device_type_id DESC"
)
public class IsSceneDeviceComponent extends DataEntity<IsSceneDeviceComponent> {
	
	private static final long serialVersionUID = 1L;
	private String deviceTypeId;		// device_type_id
	private String deviceComponentId;		// device_component_id
	private String sceneParentPath;		// scene_parent_path
	
	public IsSceneDeviceComponent() {
		this(null);
	}

	public IsSceneDeviceComponent(String id){
		super(id);
	}
	
	public String getDeviceTypeId() {
		return deviceTypeId;
	}

	public void setDeviceTypeId(String deviceTypeId) {
		this.deviceTypeId = deviceTypeId;
	}
	
	@Length(min=0, max=64, message="device_component_id长度不能超过 64 个字符")
	public String getDeviceComponentId() {
		return deviceComponentId;
	}

	public void setDeviceComponentId(String deviceComponentId) {
		this.deviceComponentId = deviceComponentId;
	}
	
	@Length(min=0, max=60, message="scene_parent_path长度不能超过 60 个字符")
	public String getSceneParentPath() {
		return sceneParentPath;
	}

	public void setSceneParentPath(String sceneParentPath) {
		this.sceneParentPath = sceneParentPath;
	}
	
}