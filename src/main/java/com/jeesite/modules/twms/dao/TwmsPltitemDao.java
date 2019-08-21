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
}