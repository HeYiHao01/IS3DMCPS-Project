/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.is3dmcps.dao;

import org.apache.ibatis.annotations.Param;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.is3dmcps.entity.IsDeviceStatusInfo;

/**
 * is_device_status_infoDAO接口
 * @author hh
 * @version 2020-04-03
 */
@MyBatisDao
public interface IsDeviceStatusInfoDao extends CrudDao<IsDeviceStatusInfo> {
	public IsDeviceStatusInfo getByEn(@Param("enField") String enField);
	public IsDeviceStatusInfo getByEnDevice(@Param("enField") String enField, @Param("deviceName") String deviceName);
}