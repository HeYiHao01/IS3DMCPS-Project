/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.is3dmcps.dao;

import java.util.List;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.is3dmcps.entity.IsDeviceProperties;

/**
 * is_device_propertiesDAO接口
 * @author xx
 * @version 2019-05-10
 */
@MyBatisDao
public interface IsDevicePropertiesDao extends CrudDao<IsDeviceProperties> {
	public List<IsDeviceProperties> getDevicePropertiesDetail(String deviceId);
}