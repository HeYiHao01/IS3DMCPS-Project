/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.twms.dao;

import java.util.List;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.twms.entity.WmsPrdOutDetl;

/**
 * wms_prd_out_detlDAO接口
 * @author xx
 * @version 2019-05-14
 */
@MyBatisDao
public interface WmsPrdOutDetlDao extends CrudDao<WmsPrdOutDetl> {
	public List<WmsPrdOutDetl> getDetailByBatchNo(String batchNo);
}