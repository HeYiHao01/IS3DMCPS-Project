/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.isopc.entity;

import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 穿梭车运行统计Entity
 * @author xx
 * @version 2019-03-12
 */
@Table(name="is_car_count", alias="a", columns={
		@Column(name="id", attrName="id", label="ID", isPK=true),
		@Column(name="device_id", attrName="deviceId", label="设备ID"),
		@Column(name="device_name", attrName="deviceName", label="设备名称", queryType=QueryType.LIKE),
		@Column(name="count_date", attrName="countDate", label="统计日期"),
		@Column(name="err_count", attrName="errCount", label="故障统计"),
		@Column(name="moveerr_count", attrName="moveerrCount", label="行走故障统计"),
		@Column(name="updownerr_count", attrName="updownerrCount", label="升降故障统计"),
		@Column(name="turnerr_count", attrName="turnerrCount", label="转向故障统计"),
		@Column(name="move_mileage", attrName="moveMileage", label="行走里程"),
		@Column(name="updown_count", attrName="updownCount", label="升降次数"),
		@Column(name="turn_count", attrName="turnCount", label="转向次数"),
		@Column(name="rechange_count", attrName="rechangeCount", label="充电次数"),
		@Column(name="work_time", attrName="workTime", label="工作时长"),
	}, orderBy="a.id DESC"
)
public class IsCarCount extends DataEntity<IsCarCount> {
	
	private static final long serialVersionUID = 1L;
	private String deviceId;		// 设备ID
	private String deviceName;		// 设备名称
	private Date countDate;		// 统计日期
	private Integer errCount;		// 故障统计
	private Integer moveerrCount;		// 行走故障统计
	private Integer updownerrCount;		// 升降故障统计
	private Integer turnerrCount;		// 转向故障统计
	private Double moveMileage;		// 行走里程
	private Integer updownCount;		// 升降次数
	private Integer turnCount;		// 转向次数
	private Integer rechangeCount;		// 充电次数
	private Long workTime;		// 工作时长
	
	public IsCarCount() {
		this(null);
	}

	public IsCarCount(String id){
		super(id);
	}
	
	@NotBlank(message="设备ID不能为空")
	@Length(min=0, max=64, message="设备ID长度不能超过 64 个字符")
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
	
	@NotBlank(message="统计日期不能为空")
	@Length(min=0, max=10, message="统计日期长度不能超过 10 个字符")
	public Date getCountDate() {
		return countDate;
	}

	public void setCountDate(Date countDate) {
		this.countDate = countDate;
	}
	
	public Integer getErrCount() {
		return errCount;
	}

	public void setErrCount(Integer errCount) {
		this.errCount = errCount;
	}
	
	public Integer getMoveerrCount() {
		return moveerrCount;
	}

	public void setMoveerrCount(Integer moveerrCount) {
		this.moveerrCount = moveerrCount;
	}
	
	public Integer getUpdownerrCount() {
		return updownerrCount;
	}

	public void setUpdownerrCount(Integer updownerrCount) {
		this.updownerrCount = updownerrCount;
	}
	
	public Integer getTurnerrCount() {
		return turnerrCount;
	}

	public void setTurnerrCount(Integer turnerrCount) {
		this.turnerrCount = turnerrCount;
	}
	
	public Double getMoveMileage() {
		return moveMileage;
	}

	public void setMoveMileage(Double moveMileage) {
		this.moveMileage = moveMileage;
	}
	
	public Integer getUpdownCount() {
		return updownCount;
	}

	public void setUpdownCount(Integer updownCount) {
		this.updownCount = updownCount;
	}
	
	public Integer getTurnCount() {
		return turnCount;
	}

	public void setTurnCount(Integer turnCount) {
		this.turnCount = turnCount;
	}
	
	public Integer getRechangeCount() {
		return rechangeCount;
	}

	public void setRechangeCount(Integer rechangeCount) {
		this.rechangeCount = rechangeCount;
	}
	
	public Long getWorkTime() {
		return workTime;
	}

	public void setWorkTime(Long workTime) {
		this.workTime = workTime;
	}

	public IsCarCount(String deviceId, String deviceName, Date countDate, Integer errCount, Integer moveerrCount, Integer updownerrCount, Integer turnerrCount, Double moveMileage, Integer updownCount, Integer turnCount, Integer rechangeCount, Long workTime) {
		this.deviceId = deviceId;
		this.deviceName = deviceName;
		this.countDate = countDate;
		this.errCount = errCount;
		this.moveerrCount = moveerrCount;
		this.updownerrCount = updownerrCount;
		this.turnerrCount = turnerrCount;
		this.moveMileage = moveMileage;
		this.updownCount = updownCount;
		this.turnCount = turnCount;
		this.rechangeCount = rechangeCount;
		this.workTime = workTime;
	}
}