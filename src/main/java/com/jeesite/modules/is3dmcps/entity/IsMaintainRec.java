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
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 保养记录Entity
 * @author xx
 * @version 2019-03-06
 */
@Table(name="is_maintain_rec", alias="a", columns={
		@Column(name="id", attrName="id", label="ID", isPK=true),
		@Column(name="maintain_id", attrName="maintainId", label="保养点ID"),
		@Column(name="maintain_name", attrName="maintainName", label="保养点名称"),
		@Column(name="device_no", attrName="deviceNo", label="设备编号"),
		@Column(name="plan_person", attrName="planPerson", label="计划人员"),
		@Column(name="plan_date", attrName="planDate", label="计划保养日期"),
		@Column(name="record", attrName="record", label="保养记录"),
		@Column(name="maintain_person", attrName="maintainPerson", label="保养人员"),
		@Column(name="maintain_time", attrName="maintainTime", label="保养时间"),
		@Column(name="rec_status", attrName="recStatus", label="记录状态"),
		@Column(includeEntity=DataEntity.class),
/*		},  joinTable={
				@JoinTable(type=Type.LEFT_JOIN, entity=IsMaintain.class, alias="m", 
						on="m.id = a.maintain_id",
						columns={@Column(includeEntity=IsMaintain.class)}),
*/	}, orderBy="a.maintain_time DESC"
)
public class IsMaintainRec extends DataEntity<IsMaintainRec> {
	
	private static final long serialVersionUID = 1L;
	private String maintainId;		// 保养点ID
	private String maintainName;		// 保养点名称
	private String deviceNo;		// 设备编号
	private String planPerson;		// 计划人员
	private Date planDate;		// 计划保养日期
	private String record;		// 保养记录
	private String maintainPerson;		// 保养人员
	private Date maintainTime;		// 保养时间
	private String recStatus;		// 记录状态
	
	public IsMaintainRec() {
		this(null);
	}

	public IsMaintainRec(String id){
		super(id);
	}
	
	@NotBlank(message="保养点ID不能为空")
	@Length(min=0, max=64, message="保养点ID长度不能超过 64 个字符")
	public String getMaintainId() {
		return maintainId;
	}

	public void setMaintainId(String maintainId) {
		this.maintainId = maintainId;
	}
	
	@Length(min=0, max=10, message="设备编号长度不能超过 10 个字符")
	public String getDeviceNo() {
		return deviceNo;
	}

	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}
	
	@Length(min=0, max=20, message="计划人员长度不能超过 20 个字符")
	public String getPlanPerson() {
		return planPerson;
	}

	public void setPlanPerson(String planPerson) {
		this.planPerson = planPerson;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getPlanDate() {
		return planDate;
	}

	public void setPlanDate(Date planDate) {
		this.planDate = planDate;
	}
	
	@Length(min=0, max=200, message="保养记录长度不能超过 200 个字符")
	public String getRecord() {
		return record;
	}

	public void setRecord(String record) {
		this.record = record;
	}
	
	@Length(min=0, max=20, message="保养人员长度不能超过 20 个字符")
	public String getMaintainPerson() {
		return maintainPerson;
	}

	public void setMaintainPerson(String maintainPerson) {
		this.maintainPerson = maintainPerson;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getMaintainTime() {
		return maintainTime;
	}

	public void setMaintainTime(Date maintainTime) {
		this.maintainTime = maintainTime;
	}

	public Date getMaintainTime_gte() {
		return sqlMap.getWhere().getValue("maintain_time", QueryType.GTE);
	}

	public void setMaintainTime_gte(Date maintainTime) {
		sqlMap.getWhere().and("maintain_time", QueryType.GTE, maintainTime);
	}
	
	public Date getMaintainTime_lte() {
		return sqlMap.getWhere().getValue("maintain_time", QueryType.LTE);
	}

	public void setMaintainTime_lte(Date maintainTime) {
		sqlMap.getWhere().and("maintain_time", QueryType.LTE, maintainTime);
	}

	public String getMaintainName() {
		return maintainName;
	}

	public void setMaintainName(String maintainName) {
		this.maintainName = maintainName;
	}

	public String getRecStatus() {
		return recStatus;
	}

	public void setRecStatus(String recStatus) {
		this.recStatus = recStatus;
	}

	public IsMaintainRec(String maintainId, String maintainName, String deviceNo, String planPerson, Date planDate, String record, String maintainPerson, Date maintainTime, String recStatus) {
		this.maintainId = maintainId;
		this.maintainName = maintainName;
		this.deviceNo = deviceNo;
		this.planPerson = planPerson;
		this.planDate = planDate;
		this.record = record;
		this.maintainPerson = maintainPerson;
		this.maintainTime = maintainTime;
		this.recStatus = recStatus;
	}
}