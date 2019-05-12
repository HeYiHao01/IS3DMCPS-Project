/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.isopc.dao;

import java.util.List;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.isopc.entity.IsCarCount;

/**
 * 穿梭车运行统计DAO接口
 * @author xx
 * @version 2019-03-12
 */
@MyBatisDao
public interface IsCarCountDao extends CrudDao<IsCarCount> {
	public List<IsCarCount> getAllByDeviceId(String deviceId);
	public List<IsCarCount> getAllByDeviceName(String deviceName);
}