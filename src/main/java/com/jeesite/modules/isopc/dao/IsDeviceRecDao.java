/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.isopc.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.isopc.entity.IsDeviceRec;

import java.util.List;

/**
 * 设备运行记录DAO接口
 * @author zhangxu
 * @version 2019-04-25
 */
@MyBatisDao
public interface IsDeviceRecDao extends CrudDao<IsDeviceRec> {
	public List<String> getTimeById(String device_id);
}