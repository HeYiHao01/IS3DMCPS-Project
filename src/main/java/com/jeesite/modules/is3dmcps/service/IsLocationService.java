/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.is3dmcps.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.is3dmcps.entity.IsLocation;
import com.jeesite.modules.is3dmcps.dao.IsLocationDao;

/**
 * is_locationService
 * @author xx
 * @version 2019-05-11
 */
@Service
@Transactional(readOnly=true)
public class IsLocationService extends CrudService<IsLocationDao, IsLocation> {
	
	/**
	 * 获取单条数据
	 * @param isLocation
	 * @return
	 */
	@Override
	public IsLocation get(IsLocation isLocation) {
		return super.get(isLocation);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param isLocation
	 * @return
	 */
	@Override
	public Page<IsLocation> findPage(Page<IsLocation> page, IsLocation isLocation) {
		return super.findPage(page, isLocation);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param isLocation
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(IsLocation isLocation) {
		super.save(isLocation);
	}
	
	/**
	 * 更新状态
	 * @param isLocation
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(IsLocation isLocation) {
		super.updateStatus(isLocation);
	}
	
	/**
	 * 删除数据
	 * @param isLocation
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(IsLocation isLocation) {
		super.delete(isLocation);
	}

	public IsLocation get(Integer locationId, boolean isNewRecord) {
		return super.get(String.valueOf(locationId), isNewRecord);
	}
	
}