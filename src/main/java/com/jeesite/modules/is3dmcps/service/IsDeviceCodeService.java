/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.is3dmcps.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.datasource.DataSourceHolder;
import com.jeesite.common.service.TreeService;
import com.jeesite.modules.is3dmcps.entity.IsDeviceCode;
import com.jeesite.modules.is3dmcps.entity.NewDevice;
import com.jeesite.modules.is3dmcps.dao.IsDeviceCodeDao;

/**
 * 设备代码Service
 * @author xx
 * @version 2019-03-03
 */
@Service
@Transactional(readOnly=true)
public class IsDeviceCodeService extends TreeService<IsDeviceCodeDao, IsDeviceCode> {
	
	/**
	 * 获取单条数据
	 * @param isDeviceCode
	 * @return
	 */
	@Override
	public IsDeviceCode get(IsDeviceCode isDeviceCode) {
		return super.get(isDeviceCode);
	}
	
	/**
	 * 查询列表数据
	 * @param isDeviceCode
	 * @return
	 */
	@Override
	public List<IsDeviceCode> findList(IsDeviceCode isDeviceCode) {
		return super.findList(isDeviceCode);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param isDeviceCode
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(IsDeviceCode isDeviceCode) {
		super.save(isDeviceCode);
	}
	
	/**
	 * 更新状态
	 * @param isDeviceCode
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(IsDeviceCode isDeviceCode) {
		super.updateStatus(isDeviceCode);
	}
	
	/**
	 * 删除数据
	 * @param isDeviceCode
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(IsDeviceCode isDeviceCode) {
		super.delete(isDeviceCode);
	}
	
	/**
	 * 获取所有设备型号等详细信息
	 */
	public List<IsDeviceCode> getDeviceTypeDetail() {
		return this.dao.getDeviceTypeDetail();
	}
	public List<IsDeviceCode> getPartsTypeDetail(){
		return this.dao.getPartsTypeDetail();
	}
	
	/**
	 * 根据ID（device_code_id）获取application的相关信息
	 */
	public IsDeviceCode getPartApplicationById(String id){
		return this.dao.getPartApplicationById(id);
	}
	
	/**
	 * 根据model（deviceTypeId）获取零件信息
	 */
	public List<IsDeviceCode> getPartByModel(String model){
		return this.dao.getPartByModel(model);
	}
	
	public List<IsDeviceCode> getPatrolPoint(){
		return this.dao.getPatrolPoint();
	}
	
	public List<IsDeviceCode> partOfDevice(@Param("deviceNo")String deviceNo){
		return this.dao.partOfDevice(deviceNo);
	}
	
	public List<IsDeviceCode> partOfPart(@Param("deviceNo")String deviceNo){
		return this.dao.partOfPart(deviceNo);
	}
	
	//解决映射bug
	public List<NewDevice> getDevice(){
		return this.dao.getDevice();
	}
	public List<NewDevice> getParts(){
		return this.dao.getParts();
	}
}