/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.is3dmcps.dao;

import java.util.List;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.is3dmcps.entity.IsRepairRec;

/**
 * 维修记录DAO接口
 * @author xx
 * @version 2019-03-08
 */
@MyBatisDao
public interface IsRepairRecDao extends CrudDao<IsRepairRec> {
	public Integer getRepairCount(String repairTime);
	public String getRepairResult(String faultsId);
	public List<IsRepairRec> repairList();
}