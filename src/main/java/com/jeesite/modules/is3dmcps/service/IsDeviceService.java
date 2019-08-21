/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.is3dmcps.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.is3dmcps.entity.Device;
import com.jeesite.modules.is3dmcps.entity.IsDevice;
import com.jeesite.modules.is3dmcps.entity.SpareParts;
import com.jeesite.modules.is3dmcps.dao.IsDeviceDao;

/**
 * 设备Service
 * @author xx
 * @version 2019-03-07
 */
@Service
@Transactional(readOnly=true)
public class IsDeviceService extends CrudService<IsDeviceDao, IsDevice> {
	
	/**
	 * 获取单条数据
	 * @param isDevice
	 * @return
	 */
	@Override
	public IsDevice get(IsDevice isDevice) {
		return super.get(isDevice);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param isDevice
	 * @return
	 */
	@Override
	public Page<IsDevice> findPage(Page<IsDevice> page, IsDevice isDevice) {
		return super.findPage(page, isDevice);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param isDevice
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(IsDevice isDevice) {
		super.save(isDevice);
	}
	
	/**
	 * 更新状态
	 * @param isDevice
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(IsDevice isDevice) {
		super.updateStatus(isDevice);
	}
	
	/**
	 * 删除数据
	 * @param isDevice
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(IsDevice isDevice) {
		super.delete(isDevice);
	}

	/**
	 * 获得总数
	 */
	public int getAll(){return this.dao.getAll();}
	
	/**
	 * 获取详情
	 */
	public List<IsDevice> getDeviceDetails() {
		return this.dao.getDeviceDetails();
	}
	
	/**
	 * 根据ID获取device
	 */
	public List<IsDevice> getDeviceById(String deviceId) {
		return this.dao.getDeviceById(deviceId);
	}
	
	/**
	 * 根据device_code_ID获取device
	 */
	public List<IsDevice> getDeviceByCodeId(String deviceCodeId) {
		return this.dao.getDeviceByCodeId(deviceCodeId);
	}
	
	/**
	 * 根据device_code_ID获取零件数量
	 */
	public Integer getPartCountByCodeId(String deviceCodeId) {
		return this.dao.getPartCountByCodeId(deviceCodeId);
	}
	
	/**
	 * 根据device_code_name获取device
	 */
	public List<IsDevice> getDeviceByCodeName(String deviceCodeName) {
		return this.dao.getDeviceByCodeName(deviceCodeName);
	}
	
	/**
	 * 根据device_no获取device
	 */
	public List<IsDevice> getDeviceByDeviceNo(String deviceNo) {
		return this.dao.getDeviceByDeviceNo(deviceNo);
	}
	
	public List<Device> sparePartsList(){
		return this.dao.sparePartsList();
	}
	
	public List<SpareParts> sparePartsCount(){
		return this.dao.sparePartsCount();
	}
}