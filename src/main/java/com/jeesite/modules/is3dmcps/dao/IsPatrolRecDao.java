/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.is3dmcps.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.is3dmcps.entity.IsPatrolRec;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * 巡检记录DAO接口
 * @author xx
 * @version 2019-03-07
 */
@MyBatisDao
public interface IsPatrolRecDao extends CrudDao<IsPatrolRec> {
	public Date getLastTime(String id);
	public Integer getPatrolCount(String date);
	public List<IsPatrolRec> patrolList();
	
	public List<IsPatrolRec> patrolPlanList();
	public List<IsPatrolRec> patrolLogList();
	public List<IsPatrolRec> filterPatrolLog(@Param("patrolName")String patrolName, @Param("operator")String operator, @Param("startTime")String startTime, @Param("endTime")String endTime);
	
	public List<IsPatrolRec> filterPatrolLogPage(@Param("patrolName")String patrolName, @Param("operator")String operator, @Param("startTime")String startTime, @Param("endTime")String endTime,  @Param("rangeStart")int rangeStart,  @Param("rangeEnd")int rangeEnd);
	
	public List<IsPatrolRec> filterPatrolLogPageRemark(@Param("patrolName")String patrolName, @Param("operator")String operator, @Param("remarks")String remarks, @Param("startTime")String startTime, @Param("endTime")String endTime,  @Param("rangeStart")int rangeStart,  @Param("rangeEnd")int rangeEnd);
}