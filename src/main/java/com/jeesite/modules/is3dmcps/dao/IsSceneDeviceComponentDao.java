/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.is3dmcps.dao;

import java.util.List;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.is3dmcps.entity.IsSceneDeviceComponent;

/**
 * is_scene_device_componentDAO接口
 * @author xx
 * @version 2019-05-10
 */
@MyBatisDao
public interface IsSceneDeviceComponentDao extends CrudDao<IsSceneDeviceComponent> {
	public List<IsSceneDeviceComponent> getComponentByDeviceTypeId(String deviceTypeId);
}