/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.sys.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.sys.entity.Employee;
import com.jeesite.modules.sys.dao.EmployeeDao;

/**
 * 员工表Service
 * @author hy
 * @version 2019-09-25
 */
@Service
@Transactional(readOnly=true)
public class EmployeeService extends CrudService<EmployeeDao, Employee> {
	
	/**
	 * 获取单条数据
	 * @param employee
	 * @return
	 */
	@Override
	public Employee get(Employee employee) {
		return super.get(employee);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param employee
	 * @return
	 */
	@Override
	public Page<Employee> findPage(Page<Employee> page, Employee employee) {
		return super.findPage(page, employee);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param employee
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(Employee employee) {
		super.save(employee);
	}
	
	/**
	 * 更新状态
	 * @param employee
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(Employee employee) {
		super.updateStatus(employee);
	}
	
	/**
	 * 删除数据
	 * @param employee
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(Employee employee) {
		super.delete(employee);
	}
	
	public Employee getEmployeeByName(@Param("name") String name){
		return this.dao.getEmployeeByName(name);
	}
}