/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.isopc.entity;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.jeesite.common.mybatis.annotation.JoinTable;
import com.jeesite.common.mybatis.annotation.JoinTable.Type;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 穿梭车状态Entity
 * @author xx
 * @version 2019-03-12
 */
@Table(name="is_car_rec", alias="a", columns={
		@Column(name="id", attrName="id", label="ID", isPK=true),
		@Column(name="device_id", attrName="deviceId.deviceId", label="设备ID"),
		@Column(name="device_name", attrName="deviceName", label="设备名称"),
		@Column(name="record_time", attrName="recordTime", label="记录时间"),
		@Column(name="row_no", attrName="rowNo", label="行"),
		@Column(name="column_no", attrName="columnNo", label="列"),
		@Column(name="layer_no", attrName="layerNo", label="层"),
		@Column(name="angle", attrName="angle", label="角度"),
		@Column(name="plt", attrName="plt", label="托盘状态"),
	}, orderBy="a.id ASC"
)
public class IsCarRec extends DataEntity<IsCarRec> {
	
	private static final long serialVersionUID = 1L;
	private IsCarStatus deviceId;		// 设备ID 父类
	private String deviceName;		// 设备名称
	private Date recordTime;		// 记录时间
	private Integer rowNo;		// 行
	private Integer columnNo;		// 列
	private Integer layerNo;		// 层
	private Integer angle;		// 角度
	private String plt;		// 托盘状态
	
	public IsCarRec() {
		this(null);
	}


	public IsCarRec(IsCarStatus deviceId){
		this.deviceId = deviceId;
	}
	
	@NotBlank(message="设备ID不能为空")
	@Length(min=0, max=64, message="设备ID长度不能超过 64 个字符")
	public IsCarStatus getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(IsCarStatus deviceId) {
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
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="记录时间不能为空")
	public Date getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}
	
	public Integer getRowNo() {
		return rowNo;
	}

	public void setRowNo(Integer rowNo) {
		this.rowNo = rowNo;
	}
	
	public Integer getColumnNo() {
		return columnNo;
	}

	public void setColumnNo(Integer columnNo) {
		this.columnNo = columnNo;
	}
	
	public Integer getLayerNo() {
		return layerNo;
	}

	public void setLayerNo(Integer layerNo) {
		this.layerNo = layerNo;
	}
	
	public Integer getAngle() {
		return angle;
	}

	public void setAngle(Integer angle) {
		this.angle = angle;
	}
	
	@Length(min=0, max=1, message="托盘状态长度不能超过 1 个字符")
	public String getPlt() {
		return plt;
	}

	public void setPlt(String plt) {
		this.plt = plt;
	}
	
}