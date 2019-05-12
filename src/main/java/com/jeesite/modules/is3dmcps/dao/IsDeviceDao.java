/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.is3dmcps.dao;

import java.util.List;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.is3dmcps.entity.IsDevice;

/**
 * 设备DAO接口
 * @author xx
 * @version 2019-03-07
 */
@MyBatisDao
public interface IsDeviceDao extends CrudDao<IsDevice> {
    public int getAll();
    public List<IsDevice> getDeviceDetails();
    public List<IsDevice> getDeviceById(String deviceId);
    public List<IsDevice> getDeviceByCodeId(String deviceCodeId);
    public Integer getPartCountByCodeId(String deviceCodeId);
    public List<IsDevice> getDeviceByCodeName(String deviceCodeName);
}