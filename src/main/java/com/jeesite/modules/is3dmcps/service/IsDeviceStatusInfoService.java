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
import com.jeesite.modules.is3dmcps.entity.IsDeviceStatusInfo;
import com.jeesite.modules.is3dmcps.dao.IsDeviceStatusInfoDao;

/**
 * is_device_status_infoService
 * @author hh
 * @version 2020-04-08
 */
@Service
@Transactional(readOnly=true)
public class IsDeviceStatusInfoService extends CrudService<IsDeviceStatusInfoDao, IsDeviceStatusInfo> {
	
	/**
	 * 获取单条数据
	 * @param isDeviceStatusInfo
	 * @return
	 */
	@Override
	public IsDeviceStatusInfo get(IsDeviceStatusInfo isDeviceStatusInfo) {
		return super.get(isDeviceStatusInfo);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param isDeviceStatusInfo
	 * @return
	 */
	@Override
	public Page<IsDeviceStatusInfo> findPage(Page<IsDeviceStatusInfo> page, IsDeviceStatusInfo isDeviceStatusInfo) {
		return super.findPage(page, isDeviceStatusInfo);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param isDeviceStatusInfo
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(IsDeviceStatusInfo isDeviceStatusInfo) {
		super.save(isDeviceStatusInfo);
	}
	
	/**
	 * 更新状态
	 * @param isDeviceStatusInfo
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(IsDeviceStatusInfo isDeviceStatusInfo) {
		super.updateStatus(isDeviceStatusInfo);
	}
	
	/**
	 * 删除数据
	 * @param isDeviceStatusInfo
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(IsDeviceStatusInfo isDeviceStatusInfo) {
		super.delete(isDeviceStatusInfo);
	}
	
	public IsDeviceStatusInfo getByEn(@Param("enField") String enField){
		return this.dao.getByEn(enField);
	}
	
	public IsDeviceStatusInfo getByEnDevice(@Param("enField") String enField, @Param("deviceName") String deviceName){
		return this.dao.getByEnDevice(enField, deviceName);
	}
}