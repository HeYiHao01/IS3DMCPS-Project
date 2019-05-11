/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.twms.dao;

import java.util.List;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.twms.entity.WmsGdxdIn;

/**
 * wms_gdxd_inDAO接口
 * @author xx
 * @version 2019-05-10
 */
@MyBatisDao
public interface WmsGdxdInDao extends CrudDao<WmsGdxdIn> {
	public List<WmsGdxdIn> getWorkInfo(String date);
}