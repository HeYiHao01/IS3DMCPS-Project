/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.is3dmcps.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.is3dmcps.entity.IsSceneDeviceComponent;
import com.jeesite.modules.is3dmcps.dao.IsSceneDeviceComponentDao;

/**
 * is_scene_device_componentService
 * @author xx
 * @version 2019-05-10
 */
@Service
@Transactional(readOnly=true)
public class IsSceneDeviceComponentService extends CrudService<IsSceneDeviceComponentDao, IsSceneDeviceComponent> {
	
	/**
	 * 获取单条数据
	 * @param isSceneDeviceComponent
	 * @return
	 */
	@Override
	public IsSceneDeviceComponent get(IsSceneDeviceComponent isSceneDeviceComponent) {
		return super.get(isSceneDeviceComponent);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param isSceneDeviceComponent
	 * @return
	 */
	@Override
	public Page<IsSceneDeviceComponent> findPage(Page<IsSceneDeviceComponent> page, IsSceneDeviceComponent isSceneDeviceComponent) {
		return super.findPage(page, isSceneDeviceComponent);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param isSceneDeviceComponent
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(IsSceneDeviceComponent isSceneDeviceComponent) {
		super.save(isSceneDeviceComponent);
	}
	
	/**
	 * 更新状态
	 * @param isSceneDeviceComponent
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(IsSceneDeviceComponent isSceneDeviceComponent) {
		super.updateStatus(isSceneDeviceComponent);
	}
	
	/**
	 * 删除数据
	 * @param isSceneDeviceComponent
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(IsSceneDeviceComponent isSceneDeviceComponent) {
		super.delete(isSceneDeviceComponent);
	}
	
	/**
	 * 根据deviceTypeID（device_code_id）
	 */
	public List<IsSceneDeviceComponent> getComponentByDeviceTypeId(String deviceTypeId) {
		return this.dao.getComponentByDeviceTypeId(deviceTypeId);
		
	}
}