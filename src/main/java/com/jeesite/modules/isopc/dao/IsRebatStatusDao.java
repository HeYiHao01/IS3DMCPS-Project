/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.isopc.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.isopc.entity.IsRebatStatus;

/**
 * 快换电池装置状态DAO接口
 * @author xx
 * @version 2019-03-12
 */
@MyBatisDao
public interface IsRebatStatusDao extends CrudDao<IsRebatStatus> {
	
}