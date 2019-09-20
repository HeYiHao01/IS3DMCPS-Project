/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.twms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.is3dmcps.entity.BoxStatics;
import com.jeesite.modules.twms.entity.TwmsPltitem;

/**
 * twms_pltitemDAO接口
 * @author xx
 * @version 2019-05-10
 */
@MyBatisDao
public interface TwmsPltitemDao extends CrudDao<TwmsPltitem> {
	public List<TwmsPltitem> getBrandCount();
	public List<BoxStatics> getBoxStatics(@Param("vpltnum") String vpltnum);
	public List<TwmsPltitem> getNewBrandCount();
	public List<BoxStatics> getNewBoxStatics(@Param("vpltnum") String vpltnum);
	public List<BoxStatics> getContainLocation();
	public int getRealCase();
	public int getEmptyCase();
	
	public List<BoxStatics> filterNewBoxStatics(@Param("brand") String brand,@Param("rangeStart")  int rangeStart,@Param("rangeEnd")  int rangeEnd);
	
	public List<BoxStatics> filterNewBoxStaticsLineAsc(@Param("brand") String brand,@Param("rangeStart")  int rangeStart,@Param("rangeEnd")  int rangeEnd);
	public List<BoxStatics> filterNewBoxStaticsLineDesc(@Param("brand") String brand,@Param("rangeStart")  int rangeStart,@Param("rangeEnd")  int rangeEnd);
	public List<BoxStatics> filterNewBoxStaticsLieAsc(@Param("brand") String brand,@Param("rangeStart")  int rangeStart,@Param("rangeEnd")  int rangeEnd);
	public List<BoxStatics> filterNewBoxStaticsLieDesc(@Param("brand") String brand,@Param("rangeStart")  int rangeStart,@Param("rangeEnd")  int rangeEnd);
	public List<BoxStatics> filterNewBoxStaticsLayerAsc(@Param("brand") String brand,@Param("rangeStart")  int rangeStart,@Param("rangeEnd")  int rangeEnd);
	public List<BoxStatics> filterNewBoxStaticsLayerDesc(@Param("brand") String brand,@Param("rangeStart")  int rangeStart,@Param("rangeEnd")  int rangeEnd);
	
	//增加batch搜索
	public List<BoxStatics> filterNewBoxStaticsLineBatchAsc(@Param("brand") String brand,@Param("batch") String batch,@Param("rangeStart")  int rangeStart,@Param("rangeEnd")  int rangeEnd);
	public List<BoxStatics> filterNewBoxStaticsLineBatchDesc(@Param("brand") String brand,@Param("batch") String batch,@Param("rangeStart")  int rangeStart,@Param("rangeEnd")  int rangeEnd);
	public List<BoxStatics> filterNewBoxStaticsLieBatchAsc(@Param("brand") String brand,@Param("batch") String batch,@Param("rangeStart")  int rangeStart,@Param("rangeEnd")  int rangeEnd);
	public List<BoxStatics> filterNewBoxStaticsLieBatchDesc(@Param("brand") String brand,@Param("batch") String batch,@Param("rangeStart")  int rangeStart,@Param("rangeEnd")  int rangeEnd);
	public List<BoxStatics> filterNewBoxStaticsLayerBatchAsc(@Param("brand") String brand,@Param("batch") String batch,@Param("rangeStart")  int rangeStart,@Param("rangeEnd")  int rangeEnd);
	public List<BoxStatics> filterNewBoxStaticsLayerBatchDesc(@Param("brand") String brand,@Param("batch") String batch,@Param("rangeStart")  int rangeStart,@Param("rangeEnd")  int rangeEnd);
	
}