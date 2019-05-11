/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.isopc.entity;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 电池状态Entity
 * @author xx
 * @version 2019-03-12
 */
@Table(name="is_battery_status", alias="a", columns={
		@Column(name="device_id", attrName="deviceId", label="设备ID", isPK=true),
		@Column(name="device_name", attrName="deviceName", label="设备名称", queryType=QueryType.LIKE),
		@Column(name="location", attrName="location", label="电池位置"),
		@Column(name="charging_time", attrName="chargingTime", label="充电次数"),
	}, orderBy="a.device_id DESC"
)
public class IsBatteryStatus extends DataEntity<IsBatteryStatus> {
	
	private static final long serialVersionUID = 1L;
	private String deviceId;		// 设备ID
	private String deviceName;		// 设备名称
	private String location;		// 电池位置
	private Integer chargingTime;		// 充电次数
	
	public IsBatteryStatus() {
		this(null);
	}

	public IsBatteryStatus(String id){
		super(id);
	}
	
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
	
	@NotBlank(message="电池位置不能为空")
	@Length(min=0, max=60, message="电池位置长度不能超过 60 个字符")
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	public Integer getChargingTime() {
		return chargingTime;
	}

	public void setChargingTime(Integer chargingTime) {
		this.chargingTime = chargingTime;
	}
	
	public Integer getChargingTime_gte() {
		return sqlMap.getWhere().getValue("charging_time", QueryType.GTE);
	}

	public void setChargingTime_gte(Integer chargingTime) {
		sqlMap.getWhere().and("charging_time", QueryType.GTE, chargingTime);
	}
	
	public Integer getChargingTime_lte() {
		return sqlMap.getWhere().getValue("charging_time", QueryType.LTE);
	}

	public void setChargingTime_lte(Integer chargingTime) {
		sqlMap.getWhere().and("charging_time", QueryType.LTE, chargingTime);
	}
	
}