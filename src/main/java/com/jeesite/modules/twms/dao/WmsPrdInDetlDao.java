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
	public List<WmsPrdInDetl> getNewDetailByWN(String workNo);
	
	public List<WmsPrdInDetl> getBatchWeightYearly(String year);
	public List<WmsPrdInDetl> getClassWeightYearly(String year);
	public List<WmsPrdInDetl> getBatchWeightMonthly(String month);
	public List<WmsPrdInDetl> getClassWeightMonthly(String month);
	public List<WmsPrdInDetl> getBrandDaily(String day);
	public List<WmsPrdInDetl> getBatchWeightDaily(String day);
	public List<WmsPrdInDetl> getClassWeightDaily(String day);
	
	public List<WmsPrdInDetl> getNewDetailYearly(String year);
	public List<WmsPrdInDetl> getNewDetailMonthly(String month);
	public List<WmsPrdInDetl> getNewDetailDaily(String day);
	
	public List<WmsPrdInDetl> getBatchCountYearly(@Param("classTeam") String classTeam, @Param("year") String year);
	public List<WmsPrdInDetl> getBatchCountMonthly(@Param("classTeam") String classTeam, @Param("month") String month);
	public List<WmsPrdInDetl> getBatchCountDaily(@Param("classTeam") String classTeam, @Param("day") String day);
	
	public List<WmsPrdInDetl> getProductInfoCountBrandYearly(@Param("year") String year);
	public List<WmsPrdInDetl> getProductInfoCountBrandMonthly(@Param("month") String month);
	public List<WmsPrdInDetl> getProductInfoCountBrandDaily(@Param("day") String day);
	public List<WmsPrdInDetl> getProductInfoCountClassYearly(@Param("year") String year);
	public List<WmsPrdInDetl> getProductInfoCountClassMonthly(@Param("month") String month);
	public List<WmsPrdInDetl> getProductInfoCountClassDaily(@Param("day") String day);
}