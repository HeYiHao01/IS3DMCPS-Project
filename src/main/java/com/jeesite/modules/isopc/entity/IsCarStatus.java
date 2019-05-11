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
import java.util.List;
import com.jeesite.common.collect.ListUtils;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 穿梭车状态Entity
 * @author xx
 * @version 2019-03-12
 */
@Table(name="is_car_status", alias="a", columns={
		@Column(name="device_id", attrName="deviceId", label="设备ID", isPK=true),
		@Column(name="device_name", attrName="deviceName", label="设备名称", queryType=QueryType.LIKE),
		@Column(name="opc_id", attrName="opcId", label="OPCID"),
		@Column(name="row_no", attrName="rowNo", label="行"),
		@Column(name="column_no", attrName="columnNo", label="列"),
		@Column(name="layer_no", attrName="layerNo", label="层"),
		@Column(name="angle", attrName="angle", label="角度"),
		@Column(name="plt", attrName="plt", label="托盘状态"),
		@Column(name="bat_voltage", attrName="batVoltage", label="电压"),
		@Column(name="bat_current", attrName="batCurrent", label="电流"),
		@Column(name="run_state", attrName="runState", label="运行状态"),
		@Column(name="work_state", attrName="workState", label="任务状态"),
		@Column(name="err01", attrName="err01", label="故障1"),
		@Column(name="err02", attrName="err02", label="故障2"),
		@Column(name="err03", attrName="err03", label="故障3"),
		@Column(name="err04", attrName="err04", label="故障4"),
		@Column(name="err05", attrName="err05", label="故障5"),
		@Column(name="work_starttime", attrName="workStarttime", label="开始运行时间"),
	}, orderBy="a.device_id DESC"
)
public class IsCarStatus extends DataEntity<IsCarStatus> {
	
	private static final long serialVersionUID = 1L;
	private String deviceId;		// 设备ID
	private String deviceName;		// 设备名称
	private String opcId;		// OPCID
	private Integer rowNo;		// 行
	private Integer columnNo;		// 列
	private Integer layerNo;		// 层
	private Integer angle;		// 角度
	private String plt;		// 托盘状态
	private Double batVoltage;		// 电压
	private Double batCurrent;		// 电流
	private String runState;		// 运行状态
	private String workState;		// 任务状态
	private Integer err01;		// 故障1
	private Integer err02;		// 故障2
	private Integer err03;		// 故障3
	private Integer err04;		// 故障4
	private Integer err05;		// 故障5
	private Date workStarttime;		// 开始运行时间
	private List<IsCarRec> isCarRecList = ListUtils.newArrayList();		// 子表列表
	
	public IsCarStatus() {
		this(null);
	}

	public IsCarStatus(String id){
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
	
	public Double getBatVoltage() {
		return batVoltage;
	}

	public void setBatVoltage(Double batVoltage) {
		this.batVoltage = batVoltage;
	}
	
	public Double getBatCurrent() {
		return batCurrent;
	}

	public void setBatCurrent(Double batCurrent) {
		this.batCurrent = batCurrent;
	}
	
	@Length(min=0, max=1, message="运行状态长度不能超过 1 个字符")
	public String getRunState() {
		return runState;
	}

	public void setRunState(String runState) {
		this.runState = runState;
	}
	
	@Length(min=0, max=1, message="任务状态长度不能超过 1 个字符")
	public String getWorkState() {
		return workState;
	}

	public void setWorkState(String workState) {
		this.workState = workState;
	}
	
	public Integer getErr01() {
		return err01;
	}

	public void setErr01(Integer err01) {
		this.err01 = err01;
	}
	
	public Integer getErr02() {
		return err02;
	}

	public void setErr02(Integer err02) {
		this.err02 = err02;
	}
	
	public Integer getErr03() {
		return err03;
	}

	public void setErr03(Integer err03) {
		this.err03 = err03;
	}
	
	public Integer getErr04() {
		return err04;
	}

	public void setErr04(Integer err04) {
		this.err04 = err04;
	}
	
	public Integer getErr05() {
		return err05;
	}

	public void setErr05(Integer err05) {
		this.err05 = err05;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getWorkStarttime() {
		return workStarttime;
	}

	public void setWorkStarttime(Date workStarttime) {
		this.workStarttime = workStarttime;
	}
	
	public List<IsCarRec> getIsCarRecList() {
		return isCarRecList;
	}

	public void setIsCarRecList(List<IsCarRec> isCarRecList) {
		this.isCarRecList = isCarRecList;
	}
	
}