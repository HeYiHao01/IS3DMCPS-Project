/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.isopc.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.jeesite.common.mybatis.annotation.JoinTable;
import com.jeesite.common.mybatis.annotation.JoinTable.Type;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 设备运行记录Entity
 * @author zhangxu
 * @version 2019-04-25
 */
@Table(name="is_device_rec", alias="a", columns={
		@Column(name="id", attrName="id", label="ID", isPK=true),
		@Column(name="device_id", attrName="deviceId", label="设备ID"),
		@Column(name="device_name", attrName="deviceName", label="设备名称", queryType=QueryType.LIKE),
		@Column(name="start_time", attrName="startTime", label="开始时间"),
		@Column(name="end_time", attrName="endTime", label="结束时间"),
		@Column(name="run_time", attrName="runTime", label="运行时长"),
	}, orderBy="a.id DESC"
)
public class IsDeviceRec extends DataEntity<IsDeviceRec> {
	
	private static final long serialVersionUID = 1L;
	private String deviceId;		// 设备ID
	private String deviceName;		// 设备名称
	private Date startTime;		// 开始时间
	private Date endTime;		// 结束时间
	private String runTime;		// 运行时长
	
	public IsDeviceRec() {
		this(null);
	}

	public IsDeviceRec(String id){
		super(id);
	}
	
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
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	@Length(min=0, max=10, message="运行时长长度不能超过 10 个字符")
	public String getRunTime() {
		return runTime;
	}

	public void setRunTime(String runTime) {
		this.runTime = runTime;
	}

	public IsDeviceRec(String deviceId, String deviceName, Date startTime, Date endTime, String runTime) {
		this.deviceId = deviceId;
		this.deviceName = deviceName;
		this.startTime = startTime;
		this.endTime = endTime;
		this.runTime = runTime;
	}
}