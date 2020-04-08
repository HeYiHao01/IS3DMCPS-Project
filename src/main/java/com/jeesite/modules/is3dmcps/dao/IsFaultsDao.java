/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.is3dmcps.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.is3dmcps.entity.IsFaults;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * 设备故障DAO接口
 * @author xx
 * @version 2019-03-08
 */
@MyBatisDao
public interface IsFaultsDao extends CrudDao<IsFaults> {
    public Integer getFaultsCount(String faultsTime);
    public List<IsFaults> getFaultsDetails();
    public Integer getAllFaultsCount();    
    public IsFaults getFaultsStateDetails(String deviceId);    
    public List<IsFaults> getNeedRepair();
    public List<IsFaults> getFaultsByName(String name);
    public List<IsFaults> faultsList();
    
    public List<IsFaults> faultsLogList();
    public List<IsFaults> filterFaultsLog(@Param("faultName")String faultName, @Param("deviceName")String deviceName, @Param("operator")String operator, @Param("status")String status, @Param("startTime")String startTime, @Param("endTime")String endTime);
    
    public List<IsFaults> filterFaultsLogPage(@Param("faultName")String faultName, @Param("deviceName")String deviceName, @Param("operator")String operator, @Param("status")String status, @Param("startTime")String startTime, @Param("endTime")String endTime, @Param("rangeStart")int rangeStart,  @Param("rangeEnd")int rangeEnd);
    
    public List<IsFaults> faultsPop(@Param("deviceName") String deviceName);
    
    public IsFaults faultsHistoryCount(@Param("deviceId") String deviceId);
    public IsFaults faultsCountDaily(@Param("deviceId") String deviceId, @Param("day") String day);
    public IsFaults faultsCountMonthly(@Param("deviceId") String deviceId, @Param("month") String month);
    
    public int updateIsFaults(@Param("deviceId") String deviceId, @Param("status") String status);
    
    public Integer getFaultsCountMonth(String faultsTime);
}