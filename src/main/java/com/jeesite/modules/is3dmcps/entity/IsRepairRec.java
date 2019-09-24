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
 * 维修记录Entity
 * @author xx
 * @version 2019-03-08
 */
@Table(name="is_repair_rec", alias="a", columns={
		@Column(name="id", attrName="id", label="ID", isPK=true),
		@Column(name="faults_id", attrName="faultsId", label="故障ID"),
		@Column(name="faults_name", attrName="faultsName", label="故障名称"),
		@Column(name="record", attrName="record", label="处理过程"),
		@Column(name="results", attrName="results", label="维修结果"),
		@Column(name="operator", attrName="operator", label="维修人"),
		@Column(name="repair_time", attrName="repairTime", label="维修时间"),
		@Column(includeEntity=DataEntity.class),
	}, orderBy="a.update_date DESC"
)
public class IsRepairRec extends DataEntity<IsRepairRec> {
	
	private static final long serialVersionUID = 1L;
	private String faultsId;		// 故障ID
	private String faultsName;		// 故障ID
	private String record;		// 处理过程
	private String results;		// 维修结果
	private String operator;		// 维修人
	private Date repairTime;		// 维修时间
	public IsRepairRec() {
		this(null);
	}

	public IsRepairRec(String id){
		super(id);
	}
	
	@NotBlank(message="故障ID不能为空")
	@Length(min=0, max=64, message="故障ID长度不能超过 64 个字符")
	public String getFaultsId() {
		return faultsId;
	}

	public void setFaultsId(String faultsId) {
		this.faultsId = faultsId;
	}
	
	@Length(min=0, max=1000, message="处理过程长度不能超过 1000 个字符")
	public String getRecord() {
		return record;
	}

	public void setRecord(String record) {
		this.record = record;
	}
	
	@Length(min=0, max=200, message="维修结果长度不能超过 200 个字符")
	public String getResults() {
		return results;
	}

	public void setResults(String results) {
		this.results = results;
	}
	
	@Length(min=0, max=20, message="维修人长度不能超过 20 个字符")
	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getRepairTime() {
		return repairTime;
	}

	public void setRepairTime(Date repairTime) {
		this.repairTime = repairTime;
	}
	
	public Date getRepairTime_gte() {
		return sqlMap.getWhere().getValue("repair_time", QueryType.GTE);
	}

	public void setRepairTime_gte(Date repairTime) {
		sqlMap.getWhere().and("repair_time", QueryType.GTE, repairTime);
	}
	
	public Date getRepairTime_lte() {
		return sqlMap.getWhere().getValue("repair_time", QueryType.LTE);
	}

	public void setRepairTime_lte(Date repairTime) {
		sqlMap.getWhere().and("repair_time", QueryType.LTE, repairTime);
	}

	public String getFaultsName() {
		return faultsName;
	}

	public void setFaultsName(String faultsName) {
		this.faultsName = faultsName;
	}

	public IsRepairRec(String faultsId, String faultsName, String record, String results, String operator, Date repairTime) {
		this.faultsId = faultsId;
		this.faultsName = faultsName;
		this.record = record;
		this.results = results;
		this.operator = operator;
		this.repairTime = repairTime;
	}

	public IsRepairRec(String faultsId, String faultsName, String record, String results, String operator, Date repairTime, String remark) {
		this.faultsId = faultsId;
		this.faultsName = faultsName;
		this.record = record;
		this.results = results;
		this.operator = operator;
		this.repairTime = repairTime;
		this.remarks = remark;
	}
}