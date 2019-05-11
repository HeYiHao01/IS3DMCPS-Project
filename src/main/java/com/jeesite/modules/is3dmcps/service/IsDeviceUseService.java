/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.is3dmcps.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.is3dmcps.entity.IsDeviceUse;
import com.jeesite.modules.is3dmcps.dao.IsDeviceUseDao;

/**
 * 设备使用记录Service
 * @author xx
 * @version 2019-03-12
 */
@Service
@Transactional(readOnly=true)
public class IsDeviceUseService extends CrudService<IsDeviceUseDao, IsDeviceUse> {
	
	/**
	 * 获取单条数据
	 * @param isDeviceUse
	 * @return
	 */
	@Override
	public IsDeviceUse get(IsDeviceUse isDeviceUse) {
		return super.get(isDeviceUse);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param isDeviceUse
	 * @return
	 */
	@Override
	public Page<IsDeviceUse> findPage(Page<IsDeviceUse> page, IsDeviceUse isDeviceUse) {
		return super.findPage(page, isDeviceUse);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param isDeviceUse
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(IsDeviceUse isDeviceUse) {
		super.save(isDeviceUse);
	}
	
	/**
	 * 更新状态
	 * @param isDeviceUse
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(IsDeviceUse isDeviceUse) {
		super.updateStatus(isDeviceUse);
	}
	
	/**
	 * 删除数据
	 * @param isDeviceUse
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(IsDeviceUse isDeviceUse) {
		super.delete(isDeviceUse);
	}
	
}