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

/**
 * 设备Entity
 * @author xx
 * @version 2019-03-07
 */
@Table(name="is_device", alias="a", columns={
		@Column(name="id", attrName="id", label="ID", isPK=true),
		@Column(name="device_code_id", attrName="deviceCodeId", label="设备代码ID"),
		@Column(name="device_code_name", attrName="deviceCodeName", label="设备代码名称"),
		@Column(name="device_no", attrName="deviceNo", label="设备编号"),
		@Column(name="manufacturer", attrName="manufacturer", label="制造厂商"),
		@Column(name="production_date", attrName="productionDate", label="出厂日期"),
		@Column(name="supplier", attrName="supplier", label="供应商"),
		@Column(name="use_office", attrName="useOffice", label="启用部门"),
		@Column(name="use_date", attrName="useDate", label="启用时间"),
		@Column(name="install_location", attrName="installLocation", label="安装地点"),
		@Column(name="location_x", attrName="locationX", label="定位X"),
		@Column(name="location_y", attrName="locationY", label="定位Y"),
		@Column(name="location_z", attrName="locationZ", label="定位Z"),
		@Column(name="device_status", attrName="deviceStatus", label="设备状态"),
		@Column(name="device_id", attrName="deviceId", label="所属设备ID"),
		@Column(name="device_name", attrName="deviceName", label="所属设备名称"),
		@Column(name="type", attrName="type", label="类别"),
		@Column(includeEntity=DataEntity.class),
/*		},  joinTable={
				@JoinTable(type=Type.LEFT_JOIN, entity=IsDeviceCode.class, alias="c", 
						on="c.id = a.device_code_id",
						columns={@Column(includeEntity=IsDeviceCode.class)}),
*/	}, orderBy="a.device_code_id,a.device_no"
)
public class IsDevice extends DataEntity<IsDevice> {
	
	private static final long serialVersionUID = 1L;
	private String deviceCodeId;		// 设备代码ID
	private String deviceCodeName;		// 设备名称
	private String deviceNo;		// 设备编号
	private String manufacturer;		// 制造厂商
	private Date productionDate;		// 出厂日期
	private String supplier;		// 供应商
	private String useOffice;		// 启用部门
	private Date useDate;		// 启用时间
	private String installLocation;		// 安装地点
	private Double locationX;		// 定位X
	private Double locationY;		// 定位Y
	private Double locationZ;		// 定位Z
	private String deviceStatus;		// 设备状态
	private String deviceId;		// 所属设备ID
	private String deviceName;		// 所属设备名称
	private String type;		// 设备类别
	private String opcType;		// OPC类别
	private String opcId;		// OPCID
	public IsDevice() {
		this(null);
	}

	public IsDevice(String id){
		super(id);
	}
	
	@NotBlank(message="设备代码ID不能为空")
	@Length(min=0, max=64, message="设备代码ID长度不能超过 64 个字符")
	public String getDeviceCodeId() {
		return deviceCodeId;
	}

	public void setDeviceCodeId(String deviceCodeId) {
		this.deviceCodeId = deviceCodeId;
	}
	
	@Length(min=0, max=10, message="设备编号长度不能超过 10 个字符")
	public String getDeviceNo() {
		return deviceNo;
	}

	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}
	
	@Length(min=0, max=60, message="制造厂商长度不能超过 60 个字符")
	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getProductionDate() {
		return productionDate;
	}

	public void setProductionDate(Date productionDate) {
		this.productionDate = productionDate;
	}
	
	@Length(min=0, max=60, message="供应商长度不能超过 60 个字符")
	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	
	@Length(min=0, max=60, message="启用部门长度不能超过 60 个字符")
	public String getUseOffice() {
		return useOffice;
	}

	public void setUseOffice(String useOffice) {
		this.useOffice = useOffice;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUseDate() {
		return useDate;
	}

	public void setUseDate(Date useDate) {
		this.useDate = useDate;
	}
	
	@Length(min=0, max=60, message="安装地点长度不能超过 60 个字符")
	public String getInstallLocation() {
		return installLocation;
	}

	public void setInstallLocation(String installLocation) {
		this.installLocation = installLocation;
	}
	
	public Double getLocationX() {
		return locationX;
	}

	public void setLocationX(Double locationX) {
		this.locationX = locationX;
	}
	
	public Double getLocationY() {
		return locationY;
	}

	public void setLocationY(Double locationY) {
		this.locationY = locationY;
	}
	
	public Double getLocationZ() {
		return locationZ;
	}

	public void setLocationZ(Double locationZ) {
		this.locationZ = locationZ;
	}

	public String getDeviceCodeName() {
		return deviceCodeName;
	}

	public void setDeviceCodeName(String deviceCodeName) {
		this.deviceCodeName = deviceCodeName;
	}

	public String getDeviceStatus() {
		return deviceStatus;
	}

	public void setDeviceStatus(String deviceStatus) {
		this.deviceStatus = deviceStatus;
	}

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOpcId() {
		return opcId;
	}

	public void setOpcId(String opcId) {
		this.opcId = opcId;
	}

	public String getOpcType() {
		return opcType;
	}

	public void setOpcType(String opcType) {
		this.opcType = opcType;
	}
	
}