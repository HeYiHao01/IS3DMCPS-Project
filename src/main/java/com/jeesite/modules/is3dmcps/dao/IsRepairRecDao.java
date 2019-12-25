/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.is3dmcps.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.is3dmcps.entity.IsRepairRec;
import com.jeesite.modules.is3dmcps.entity.PartsConsumption;

/**
 * 维修记录DAO接口
 * @author xx
 * @version 2019-03-08
 */
@MyBatisDao
public interface IsRepairRecDao extends CrudDao<IsRepairRec> {
	public Integer getRepairCount(String repairTime);
	public String getRepairResult(String faultsId);
	public List<IsRepairRec> repairList();
	
	public List<IsRepairRec> repairLogList();
	public List<IsRepairRec> filterRepairLog(@Param("faultsName")String faultsName,@Param("operator")String operator,@Param("results")String results,@Param("startTime")String startTime,@Param("endTime")String endTime);
	
	public List<PartsConsumption> partsConsumptionList();
	
	public List<IsRepairRec> filterRepairLogPage(@Param("faultsName")String faultsName,@Param("operator")String operator,@Param("results")String results,@Param("startTime")String startTime,@Param("endTime")String endTime, @Param("rangeStart")int rangeStart,  @Param("rangeEnd")int rangeEnd);
	
	public int updateRepairRec(@Param("faultsId") String faultsId, @Param("results") String results);
}