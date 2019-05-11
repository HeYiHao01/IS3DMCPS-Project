/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.is3dmcps.entity;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.jeesite.modules.is3dmcps.entity.IsPatrolRec;

/**
 * 巡检记录Entity
 * @author xx
 * @version 2019-03-07
 */

public class IsPatrolOperate  {
	
	private String operator;		// 巡检人
	private Date patrolTime;		// 巡检时间
	
	private List<IsPatrolRec> isPatrolRecList =new ArrayList<IsPatrolRec>();
	
	public IsPatrolOperate() {
	}
		
	@NotBlank(message="巡检人不能为空")
	@Length(min=0, max=20, message="巡检人长度不能超过 20 个字符")
	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}
	
	@NotBlank(message="巡检时间不能为空")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getPatrolTime() {
		return patrolTime;
	}

	public void setPatrolTime(Date patrolTime) {
		this.patrolTime = patrolTime;
	}


	public List<IsPatrolRec> getIsPatrolRecList() {
		return isPatrolRecList;
	}


	public void setIsPatrolRecList(List<IsPatrolRec> isPatrolRecList) {
		this.isPatrolRecList = isPatrolRecList;
	}
	
}