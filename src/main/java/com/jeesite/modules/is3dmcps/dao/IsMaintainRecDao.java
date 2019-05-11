/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.is3dmcps.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.is3dmcps.entity.IsMaintainRec;

import java.util.List;

/**
 * 保养记录DAO接口
 * @author xx
 * @version 2019-03-06
 */
@MyBatisDao
public interface IsMaintainRecDao extends CrudDao<IsMaintainRec> {
    public int need_maintain();
    public List<IsMaintainRec> need_maintain_details();
    public List<IsMaintainRec> getMaintainPop(String maintainId);
    public int getMaintainPlanCount(String planDate);
    public int getFinishCount(String maintainTime);
}