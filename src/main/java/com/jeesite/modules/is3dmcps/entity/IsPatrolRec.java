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
 * 巡检记录Entity
 * @author xx
 * @version 2019-03-07
 */
@Table(name="is_patrol_rec", alias="a", columns={
		@Column(name="id", attrName="id", label="ID", isPK=true),
		@Column(name="patrol_id", attrName="patrolId", label="巡检点ID"),
		@Column(name="patrol_name", attrName="patrolName", label="巡检点名称"),
		@Column(name="record", attrName="record", label="巡检记录"),
		@Column(name="operator", attrName="operator", label="巡检人"),
		@Column(name="patrol_time", attrName="patrolTime", label="巡检时间"),
		@Column(includeEntity=DataEntity.class),
	}, orderBy="a.patrol_time DESC"
)
public class IsPatrolRec extends DataEntity<IsPatrolRec> {
	
	private static final long serialVersionUID = 1L;
	private String patrolId;		// 巡检点ID
	private String patrolName;		// 巡检点名称
	private String record;		// 巡检记录
	private String operator;		// 巡检人
	private Date patrolTime;		// 巡检时间
	public IsPatrolRec() {
		this(null);
	}

	public IsPatrolRec(String id){
		super(id);
	}
	
	@NotBlank(message="巡检点ID不能为空")
	@Length(min=0, max=64, message="巡检点ID长度不能超过 64 个字符")
	public String getPatrolId() {
		return patrolId;
	}

	public void setPatrolId(String patrolId) {
		this.patrolId = patrolId;
	}
	
	@Length(min=0, max=200, message="巡检记录长度不能超过 200 个字符")
	public String getRecord() {
		return record;
	}

	public void setRecord(String record) {
		this.record = record;
	}
	
	@Length(min=0, max=20, message="巡检人长度不能超过 20 个字符")
	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getPatrolTime() {
		return patrolTime;
	}

	public void setPatrolTime(Date patrolTime) {
		this.patrolTime = patrolTime;
	}

	public Date getPatrolTime_gte() {
		return sqlMap.getWhere().getValue("patrol_time", QueryType.GTE);
	}

	public void setPatrolTime_gte(Date patrolTime) {
		sqlMap.getWhere().and("patrol_time", QueryType.GTE, patrolTime);
	}
	
	public Date getPatrolTime_lte() {
		return sqlMap.getWhere().getValue("patrol_time", QueryType.LTE);
	}

	public void setPatrolTime_lte(Date patrolTime) {
		sqlMap.getWhere().and("patrol_time", QueryType.LTE, patrolTime);
	}

	public String getPatrolName() {
		return patrolName;
	}

	public void setPatrolName(String patrolName) {
		this.patrolName = patrolName;
	}

	public IsPatrolRec(String patrolId, String patrolName, String record, String operator, Date patrolTime) {
		this.patrolId = patrolId;
		this.patrolName = patrolName;
		this.record = record;
		this.operator = operator;
		this.patrolTime = patrolTime;
	}
}