/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.twms.dao;

import java.util.List;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.twms.entity.TwmsLoc;

/**
 * twms_locDAO接口
 * @author xx
 * @version 2019-05-13
 */
@MyBatisDao
public interface TwmsLocDao extends CrudDao<TwmsLoc> {
	public List<TwmsLoc> getAll();
	public List<TwmsLoc> getNewAll();
	public int getGoodsAllocation();
	public int getNormalGoodsAllocation();
}