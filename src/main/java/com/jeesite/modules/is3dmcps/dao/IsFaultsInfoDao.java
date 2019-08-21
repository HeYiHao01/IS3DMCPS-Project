/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.is3dmcps.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.is3dmcps.entity.IsFaultsInfo;

/**
 * is_faults_infoDAO接口
 * @author yh
 * @version 2019-08-19
 */
@MyBatisDao
public interface IsFaultsInfoDao extends CrudDao<IsFaultsInfo> {
	public IsFaultsInfo getFaultsInfo(@Param("faultCode") String faultCode);
}