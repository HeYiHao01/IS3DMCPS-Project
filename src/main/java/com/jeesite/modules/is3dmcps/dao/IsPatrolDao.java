/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.is3dmcps.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.is3dmcps.entity.IsPatrol;

import java.util.List;

/**
 * 设备巡检点DAO接口
 * @author xx
 * @version 2019-03-07
 */
@MyBatisDao
public interface IsPatrolDao extends CrudDao<IsPatrol> {
	public List<IsPatrol> getPatrol();
	public List<IsPatrol> getNeedPatrol();
	public List<IsPatrol> getPatrolByDeviceId(String deviceId);
}