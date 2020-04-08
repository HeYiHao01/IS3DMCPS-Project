/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.is3dmcps.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.is3dmcps.entity.IsMaintainRec;
import com.jeesite.modules.is3dmcps.entity.MaintainPersonInfo;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * 保养记录DAO接口
 * @author xx
 * @version 2019-03-06
 */
@MyBatisDao
public interface IsMaintainRecDao extends CrudDao<IsMaintainRec> {
    public int need_maintain();
    public List<IsMaintainRec> need_maintain_details();
    public List<IsMaintainRec> maintainList();
    public List<IsMaintainRec> getMaintainPop(String deviceNo);
    public int getMaintainPlanCount(String planDate);
    public int getFinishCount(String maintainTime);
    public List<IsMaintainRec> filterMaintainRec(@Param("maintainName")String maintainName, @Param("deviceNo")String deviceNo, @Param("planPerson")String planPerson);
    
    public List<MaintainPersonInfo> getMaintainPersonAll();
    public List<MaintainPersonInfo> getMaintainPersonPlan();
    public List<MaintainPersonInfo> getMaintainPersonFinish();
    
    public List<IsMaintainRec> filterMaintainRecPage(@Param("maintainName")String maintainName, @Param("deviceNo")String deviceNo, @Param("planPerson")String planPerson, @Param("status")String status, @Param("startTime")String startTime, @Param("endTime")String endTime, @Param("rangeStart")int rangeStart,  @Param("rangeEnd")int rangeEnd);
     
    public MaintainPersonInfo getMaintainPersonPlanByName(@Param("planPerson")String planPerson);
    public MaintainPersonInfo getMaintainPersonFinishByName(@Param("maintainPerson")String maintainPerson);
    
    public int updateMaintainRec(@Param("maintainId") String maintainId, @Param("deviceNo") String deviceNo,
    		@Param("record") String record, @Param("maintainPerson") String maintainPerson, @Param("maintainTime") Date maintainTime, @Param("recStatus") String recStatus);
    
    public int getMaintainPlanCountMonth(String planDate);
    public int getFinishCountMonth(String maintainTime);
}