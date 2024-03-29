/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.isopc.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.isopc.entity.IsCarRec;

/**
 * 穿梭车状态DAO接口
 * @author xx
 * @version 2019-03-12
 */
@MyBatisDao
public interface IsCarRecDao extends CrudDao<IsCarRec> {
	
}