/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.twms.dao;

import java.util.List;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.twms.entity.WmsGdxdOut;

/**
 * wms_gdxd_outDAO接口
 * @author xx
 * @version 2019-05-14
 */
@MyBatisDao
public interface WmsGdxdOutDao extends CrudDao<WmsGdxdOut> {
	public List<WmsGdxdOut> getAllOut(String date);
	public List<WmsGdxdOut> getBatchAndTime(String date);	
}