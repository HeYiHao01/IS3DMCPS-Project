/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.twms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

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
	
	public List<WmsPrdOutDetl> getBatchWeightYearly(String year);
	public List<WmsPrdOutDetl> getClassWeightYearly(String year);
	public List<WmsPrdOutDetl> getBatchWeightMonthly(String month);
	public List<WmsPrdOutDetl> getClassWeightMonthly(String month);
	public List<WmsPrdOutDetl> getBrandDaily(String day);
	public List<WmsPrdOutDetl> getBatchWeightDaily(String day);
	public List<WmsPrdOutDetl> getClassWeightDaily(String day);
	
	public List<WmsPrdOutDetl> getNewDetailYearly(String year);
	public List<WmsPrdOutDetl> getNewDetailMonthly(String month);
	public List<WmsPrdOutDetl> getNewDetailDaily(String day);
	
	public List<WmsPrdOutDetl> getBatchCountYearly(@Param("classTeam") String classTeam, @Param("year") String year);
	public List<WmsPrdOutDetl> getBatchCountMonthly(@Param("classTeam") String classTeam, @Param("month") String month);
	public List<WmsPrdOutDetl> getBatchCountDaily(@Param("classTeam") String classTeam, @Param("day") String day);
	
	public List<WmsPrdOutDetl> getProductInfoCountBrandYearly(@Param("year") String year);
	public List<WmsPrdOutDetl> getProductInfoCountBrandMonthly(@Param("month") String month);
	public List<WmsPrdOutDetl> getProductInfoCountBrandDaily(@Param("day") String day);
	public List<WmsPrdOutDetl> getProductInfoCountClassYearly(@Param("year") String year);
	public List<WmsPrdOutDetl> getProductInfoCountClassMonthly(@Param("month") String month);
	public List<WmsPrdOutDetl> getProductInfoCountClassDaily(@Param("day") String day);
}