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
 * 快换电池装置状态Entity
 * @author xx
 * @version 2019-03-12
 */
@Table(name="is_rebat_status", alias="a", columns={
		@Column(name="device_id", attrName="deviceId", label="设备ID", isPK=true),
		@Column(name="device_name", attrName="deviceName", label="设备名称", queryType=QueryType.LIKE),
		@Column(name="opc_id", attrName="opcId", label="OPCID"),
		@Column(name="bat01", attrName="bat01", label="1#充电位"),
		@Column(name="bat02", attrName="bat02", label="2#充电位"),
		@Column(name="bat03", attrName="bat03", label="3#充电位"),
		@Column(name="bat04", attrName="bat04", label="4#充电位"),
		@Column(name="bat05", attrName="bat05", label="5#充电位"),
	}, orderBy="a.device_id DESC"
)
public class IsRebatStatus extends DataEntity<IsRebatStatus> {
	
	private static final long serialVersionUID = 1L;
	private String deviceId;		// 设备ID
	private String deviceName;		// 设备名称
	private String opcId;		// OPCID
	private String bat01;		// 1#充电位
	private String bat02;		// 2#充电位
	private String bat03;		// 3#充电位
	private String bat04;		// 4#充电位
	private String bat05;		// 5#充电位
	
	public IsRebatStatus() {
		this(null);
	}

	public IsRebatStatus(String id){
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
	
	@NotBlank(message="OPCID不能为空")
	@Length(min=0, max=64, message="OPCID长度不能超过 64 个字符")
	public String getOpcId() {
		return opcId;
	}

	public void setOpcId(String opcId) {
		this.opcId = opcId;
	}
	
	@Length(min=0, max=1, message="1#充电位长度不能超过 1 个字符")
	public String getBat01() {
		return bat01;
	}

	public void setBat01(String bat01) {
		this.bat01 = bat01;
	}
	
	@Length(min=0, max=1, message="2#充电位长度不能超过 1 个字符")
	public String getBat02() {
		return bat02;
	}

	public void setBat02(String bat02) {
		this.bat02 = bat02;
	}
	
	@Length(min=0, max=1, message="3#充电位长度不能超过 1 个字符")
	public String getBat03() {
		return bat03;
	}

	public void setBat03(String bat03) {
		this.bat03 = bat03;
	}
	
	@Length(min=0, max=1, message="4#充电位长度不能超过 1 个字符")
	public String getBat04() {
		return bat04;
	}

	public void setBat04(String bat04) {
		this.bat04 = bat04;
	}
	
	@Length(min=0, max=1, message="5#充电位长度不能超过 1 个字符")
	public String getBat05() {
		return bat05;
	}

	public void setBat05(String bat05) {
		this.bat05 = bat05;
	}
	
}