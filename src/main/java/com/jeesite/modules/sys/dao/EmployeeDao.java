/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.sys.dao;

import org.apache.ibatis.annotations.Param;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.sys.entity.Employee;

/**
 * 员工表DAO接口
 * @author hy
 * @version 2019-09-25
 */
@MyBatisDao
public interface EmployeeDao extends CrudDao<Employee> {
	public Employee getEmployeeByName(@Param("name") String name);
}