/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.is3dmcps.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jeesite.common.dao.TreeDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.is3dmcps.entity.IsDeviceCode;

/**
 * 设备代码DAO接口
 * @author xx
 * @version 2019-03-03
 */
@MyBatisDao
public interface IsDeviceCodeDao extends TreeDao<IsDeviceCode> {
	public List<IsDeviceCode> getDeviceTypeDetail();
	public IsDeviceCode getPartApplicationById(String id);
	public List<IsDeviceCode> getPartByModel(String model);
	public List<IsDeviceCode> getPatrolPoint();
	
	public List<IsDeviceCode> partOfDevice(@Param("deviceNo")String deviceNo);
	public List<IsDeviceCode> partOfPart(@Param("deviceNo")String deviceNo);
}