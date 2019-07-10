/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.twms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.twms.entity.WmsGdxdIn;
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
	public List<WmsGdxdOut> getByWN(String woNo);
	
	public List<WmsGdxdOut> getNewAllOut(String date);
	public List<WmsGdxdOut> getNewBatchAndTime(String date);
	public List<WmsGdxdOut> getNewByWN(String woNo);
	
	public List<WmsGdxdOut> getEquId();
	//public List<WmsGdxdOut> getAllByEquId(String equId);
	public List<WmsGdxdOut> getAllByEquId(@Param("equId")String equId, @Param("count")int count);
	
	public List<WmsGdxdOut> getClassTeam();
	public List<WmsGdxdOut> getAllByClassTeam(String teamCd);
}