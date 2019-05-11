/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.is3dmcps.dao;

import java.util.List;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.is3dmcps.entity.IsMaintain;

/**
 * 保养点DAO接口
 * @author xx
 * @version 2019-03-06
 */
@MyBatisDao
public interface IsMaintainDao extends CrudDao<IsMaintain> {
	public List<IsMaintain> getMaintainPopContent(String deviceCodeId);
}