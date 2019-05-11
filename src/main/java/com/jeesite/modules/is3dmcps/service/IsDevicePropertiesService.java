/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.is3dmcps.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.is3dmcps.dao.IsDevicePropertiesDao;
import com.jeesite.modules.is3dmcps.entity.IsDeviceProperties;

/**
 * is_device_propertiesService
 * @author xx
 * @version 2019-05-10
 */
@Service
@Transactional(readOnly=true)
public class IsDevicePropertiesService extends CrudService<IsDevicePropertiesDao, IsDeviceProperties> {
	
	/**
	 * 获取单条数据
	 * @param isDeviceProperties
	 * @return
	 */
	@Override
	public IsDeviceProperties get(IsDeviceProperties isDeviceProperties) {
		return super.get(isDeviceProperties);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param isDeviceProperties
	 * @return
	 */
	@Override
	public Page<IsDeviceProperties> findPage(Page<IsDeviceProperties> page, IsDeviceProperties isDeviceProperties) {
		return super.findPage(page, isDeviceProperties);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param isDeviceProperties
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(IsDeviceProperties isDeviceProperties) {
		super.save(isDeviceProperties);
	}
	
	/**
	 * 更新状态
	 * @param isDeviceProperties
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(IsDeviceProperties isDeviceProperties) {
		super.updateStatus(isDeviceProperties);
	}
	
	/**
	 * 删除数据
	 * @param isDeviceProperties
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(IsDeviceProperties isDeviceProperties) {
		super.delete(isDeviceProperties);
	}
	
	/**
	 * 根据deviceID获取properties（参数信息）
	 */
	public List<IsDeviceProperties> getDevicePropertiesDetail(String deviceId) {
		return this.dao.getDevicePropertiesDetail(deviceId);
	}
	
}