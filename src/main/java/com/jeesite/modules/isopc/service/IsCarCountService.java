/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.isopc.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.isopc.dao.IsCarCountDao;
import com.jeesite.modules.isopc.entity.CarCount;
import com.jeesite.modules.isopc.entity.IsCarCount;

/**
 * 穿梭车运行统计Service
 * @author xx
 * @version 2019-03-12
 */
@Service
@Transactional(readOnly=true)
public class IsCarCountService extends CrudService<IsCarCountDao, IsCarCount> {
	
	/**
	 * 获取单条数据
	 * @param isCarCount
	 * @return
	 */
	@Override
	public IsCarCount get(IsCarCount isCarCount) {
		return super.get(isCarCount);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param isCarCount
	 * @return
	 */
	@Override
	public Page<IsCarCount> findPage(Page<IsCarCount> page, IsCarCount isCarCount) {
		return super.findPage(page, isCarCount);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param isCarCount
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(IsCarCount isCarCount) {
		super.save(isCarCount);
	}
	
	/**
	 * 更新状态
	 * @param isCarCount
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(IsCarCount isCarCount) {
		super.updateStatus(isCarCount);
	}
	
	/**
	 * 删除数据
	 * @param isCarCount
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(IsCarCount isCarCount) {
		super.delete(isCarCount);
	}
	
	/**
	 *根据device_id获取小车运行统计
	 */
	public List<IsCarCount> getAllByDeviceId(String deviceId) {
		return this.dao.getAllByDeviceId(deviceId);
	}
	
	/**
	 *根据device_name获取小车运行统计
	 */
	public List<IsCarCount> getAllByDeviceName(String deviceName) {
		return this.dao.getAllByDeviceName(deviceName);
	}
	
	/**
	 * 根据日期获取小车运行时长
	 */
	public CarCount getByDaily(@Param("date")String date,@Param("deviceId")String deviceId){
		return this.dao.getByDaily(date, deviceId);
	}
}