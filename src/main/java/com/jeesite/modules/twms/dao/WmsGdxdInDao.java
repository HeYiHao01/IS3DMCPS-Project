/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.twms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

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
	public List<WmsGdxdIn> getAllIn(String date);
	public List<WmsGdxdIn> getBatchAndTime(String date);
	public List<WmsGdxdIn> getByWN(String woNo);
	
	public List<WmsGdxdIn> getNewWorkInfo(String date);
	public List<WmsGdxdIn> getNewAllIn(String date);
	public List<WmsGdxdIn> getNewBatchAndTime(String date);
	public List<WmsGdxdIn> getNewByWN(String woNo);
	
	public List<WmsGdxdIn> getPackingLine();
	public List<WmsGdxdIn> getAllByPackingLine(String inLine);
	
	public List<WmsGdxdIn> getClassTeam();
	public List<WmsGdxdIn> classTeamList();
	public List<WmsGdxdIn> getAllByClassTeam(String teamCd);
	public List<WmsGdxdIn> getAllByClassTeamNull();
	
	public List<WmsGdxdIn> getBrands();
	public List<WmsGdxdIn> filterWorkOrderIn(@Param("inLine") String inLine, @Param("brand") String brand, @Param("startTime") String startTime, @Param("endTime") String endTime, @Param("rangeStart") int rangeStart, @Param("rangeEnd") int rangeEnd);
	
	public List<WmsGdxdIn> filterByClassTeam(@Param("teamCd") String teamCd, @Param("brand") String brand, @Param("startTime") String startTime, @Param("endTime") String endTime, @Param("rangeStart") int rangeStart, @Param("rangeEnd") int rangeEnd);
	
	public int getGdxdInLength();
	public List<WmsGdxdIn> getByWoState(@Param("woState") String woState, @Param("length") int length);
	
	public List<WmsGdxdIn> getNewAllInYearly(@Param("year") String year);
	public List<WmsGdxdIn> getNewAllInMonthly(@Param("month") String month);
	
	public List<WmsGdxdIn> filterWorkOrderInBatch(@Param("inLine") String inLine, @Param("brand") String brand, @Param("batch") String batch, @Param("startTime") String startTime, @Param("endTime") String endTime, @Param("rangeStart") int rangeStart, @Param("rangeEnd") int rangeEnd);
	public List<WmsGdxdIn> filterByClassTeamBatch(@Param("teamCd") String teamCd, @Param("brand") String brand, @Param("batch") String batch, @Param("startTime") String startTime, @Param("endTime") String endTime, @Param("rangeStart") int rangeStart, @Param("rangeEnd") int rangeEnd);	
}