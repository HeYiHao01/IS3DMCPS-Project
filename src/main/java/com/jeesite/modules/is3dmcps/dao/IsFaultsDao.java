/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.is3dmcps.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.is3dmcps.entity.IsFaults;

import java.util.List;

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
}