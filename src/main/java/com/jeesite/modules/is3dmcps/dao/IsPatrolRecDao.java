/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.is3dmcps.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.is3dmcps.entity.IsPatrolRec;

import java.util.Date;

/**
 * 巡检记录DAO接口
 * @author xx
 * @version 2019-03-07
 */
@MyBatisDao
public interface IsPatrolRecDao extends CrudDao<IsPatrolRec> {
	public Date getLastTime(String id);
	public Integer getPatrolCount(String date);
}