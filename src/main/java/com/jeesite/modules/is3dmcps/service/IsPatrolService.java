/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.is3dmcps.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.is3dmcps.entity.IsPatrol;
import com.jeesite.modules.is3dmcps.dao.IsPatrolDao;

import java.util.List;

/**
 * 设备巡检点Service
 * @author xx
 * @version 2019-03-07
 */
@Service
@Transactional(readOnly=true)
public class IsPatrolService extends CrudService<IsPatrolDao, IsPatrol> {
	
	/**
	 * 获取单条数据
	 * @param isPatrol
	 * @return
	 */
	@Override
	public IsPatrol get(IsPatrol isPatrol) {
		return super.get(isPatrol);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param isPatrol
	 * @return
	 */
	@Override
	public Page<IsPatrol> findPage(Page<IsPatrol> page, IsPatrol isPatrol) {
		return super.findPage(page, isPatrol);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param isPatrol
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(IsPatrol isPatrol) {
		super.save(isPatrol);
	}
	
	/**
	 * 更新状态
	 * @param isPatrol
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(IsPatrol isPatrol) {
		super.updateStatus(isPatrol);
	}
	
	/**
	 * 删除数据
	 * @param isPatrol
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(IsPatrol isPatrol) {
		super.delete(isPatrol);
	}
	
	/**
	 * 获取patrol所有记录
	 */
	public List<IsPatrol> getPatrol() {
		return this.dao.getPatrol();
	}

	/**
	 * 根据deviceID获取patrol
	 * @return
	 */
	@Transactional(readOnly=false)
	public List<IsPatrol> getPatrolByDeviceId(String deviceId)
	{
		return this.dao.getPatrolByDeviceId(deviceId);
	}
	
	/**
	 * 获取需要巡检的设备
	 */
	public List<IsPatrol> getNeedPatrol(){
		return this.dao.getNeedPatrol();
	}
}