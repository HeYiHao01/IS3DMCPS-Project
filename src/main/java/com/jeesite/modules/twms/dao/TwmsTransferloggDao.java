/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.twms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.twms.entity.TwmsTransferlogg;

/**
 * twms_transferloggDAO接口
 * @author xx
 * @version 2019-05-24
 */
@MyBatisDao
public interface TwmsTransferloggDao extends CrudDao<TwmsTransferlogg> {
	public TwmsTransferlogg getByLoggnum(@Param("loggNum")String loggNum,@Param("count")int count);
	
	public int getCount();
	public List<TwmsTransferlogg> getByLotnum(@Param("lotnum")String lotnum,@Param("count")int count);
	
	public TwmsTransferlogg getByLoggnumByIndex(@Param("loggNum")String loggNum);
}