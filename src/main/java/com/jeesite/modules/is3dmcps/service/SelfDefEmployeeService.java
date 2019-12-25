/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.is3dmcps.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.is3dmcps.entity.SelfDefEmployee;
import com.jeesite.modules.is3dmcps.dao.SelfDefEmployeeDao;

/**
 * 员工表Service
 * @author hy
 * @version 2019-12-25
 */
@Service
@Transactional(readOnly=true)
public class SelfDefEmployeeService extends CrudService<SelfDefEmployeeDao, SelfDefEmployee> {
	
	/**
	 * 获取单条数据
	 * @param selfDefEmployee
	 * @return
	 */
	@Override
	public SelfDefEmployee get(SelfDefEmployee selfDefEmployee) {
		return super.get(selfDefEmployee);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param selfDefEmployee
	 * @return
	 */
	@Override
	public Page<SelfDefEmployee> findPage(Page<SelfDefEmployee> page, SelfDefEmployee selfDefEmployee) {
		return super.findPage(page, selfDefEmployee);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param selfDefEmployee
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(SelfDefEmployee selfDefEmployee) {
		super.save(selfDefEmployee);
	}
	
	/**
	 * 更新状态
	 * @param selfDefEmployee
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(SelfDefEmployee selfDefEmployee) {
		super.updateStatus(selfDefEmployee);
	}
	
	/**
	 * 删除数据
	 * @param selfDefEmployee
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(SelfDefEmployee selfDefEmployee) {
		super.delete(selfDefEmployee);
	}
	
	public SelfDefEmployee getEmployeeByName(@Param("empName") String empName){
		return this.dao.getEmployeeByName(empName);
	}
	
}