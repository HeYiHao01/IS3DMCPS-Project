/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.is3dmcps.dao;

import org.apache.ibatis.annotations.Param;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.is3dmcps.entity.SelfDefEmployee;

/**
 * 员工表DAO接口
 * @author hy
 * @version 2019-12-25
 */
@MyBatisDao
public interface SelfDefEmployeeDao extends CrudDao<SelfDefEmployee> {
	public SelfDefEmployee getEmployeeByName(@Param("empName") String empName);
}