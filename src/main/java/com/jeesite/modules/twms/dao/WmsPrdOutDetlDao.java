/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.twms.dao;

import java.util.List;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.twms.entity.WmsPrdInDetl;
import com.jeesite.modules.twms.entity.WmsPrdOutDetl;

/**
 * wms_prd_out_detlDAO接口
 * @author xx
 * @version 2019-05-14
 */
@MyBatisDao
public interface WmsPrdOutDetlDao extends CrudDao<WmsPrdOutDetl> {
	public List<WmsPrdOutDetl> getDetailByBatchNo(String batchNo);
	public List<WmsPrdOutDetl> getDetailMonthly(String month);	
	public List<WmsPrdOutDetl> getDetailByWN(String workNo);
	
	public List<WmsPrdOutDetl> getNewDetailByBatchNo(String batchNo);	
	public List<WmsPrdOutDetl> getNewDetailByWN(String workNo);
	
	public List<WmsPrdOutDetl> getNewDetailMonthly(String month);
	public List<WmsPrdOutDetl> getBatchWeightMonthly(String month);
	public List<WmsPrdOutDetl> getClassWeightMonthly(String month);
	public List<WmsPrdOutDetl> getBrandDaily(String day);
	public List<WmsPrdOutDetl> getBatchWeightDaily(String day);
	public List<WmsPrdOutDetl> getClassWeightDaily(String day);
}