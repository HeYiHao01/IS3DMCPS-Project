/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.twms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.twms.entity.WmsPrdInDetl;

/**
 * wms_prd_in_detlDAO接口
 * @author xx
 * @version 2019-05-14
 */
@MyBatisDao
public interface WmsPrdInDetlDao extends CrudDao<WmsPrdInDetl> {
	public WmsPrdInDetl getDetailByWB(@Param("woNo")String woNo,@Param("batchNo")String batchNo);
	public List<WmsPrdInDetl> getDetailByBatchNo(String batchNo);
	public List<WmsPrdInDetl> getDetailMonthly(String month);
	public List<WmsPrdInDetl> getDetailByWN(String workNo);
	
	public List<WmsPrdInDetl> getNewDetailByBatchNo(String batchNo);
	public List<WmsPrdInDetl> getNewDetailMonthly(String month);
	public List<WmsPrdInDetl> getNewDetailByWN(String workNo);
}