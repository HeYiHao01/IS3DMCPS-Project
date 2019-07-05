/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.is3dmcps.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.is3dmcps.entity.IsFaults;
import com.jeesite.modules.is3dmcps.dao.IsFaultsDao;

import java.util.List;

/**
 * 设备故障Service
 * @author xx
 * @version 2019-03-08
 */
@Service
@Transactional(readOnly=true)
public class IsFaultsService extends CrudService<IsFaultsDao, IsFaults> {
	
	/**
	 * 获取单条数据
	 * @param isFaults
	 * @return
	 */
	@Override
	public IsFaults get(IsFaults isFaults) {
		return super.get(isFaults);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param isFaults
	 * @return
	 */
	@Override
	public Page<IsFaults> findPage(Page<IsFaults> page, IsFaults isFaults) {
		return super.findPage(page, isFaults);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param isFaults
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(IsFaults isFaults) {
		super.save(isFaults);
	}
	
	/**
	 * 更新状态
	 * @param isFaults
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(IsFaults isFaults) {
		super.updateStatus(isFaults);
	}
	
	/**
	 * 删除数据
	 * @param isFaults
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(IsFaults isFaults) {
		super.delete(isFaults);
	}

	/**
	 * 根据日期获取故障设备
	 */
	@Transactional(readOnly=false)
	public int getFaultsCount(String faultsTime){
		return this.dao.getFaultsCount(faultsTime);
	}

	/**
	 * 获取故障详情
	 */
	@Transactional(readOnly=false)
	public List<IsFaults> getFaultsDetails(){
        return this.dao.getFaultsDetails();
	}

	/**
	 * 获取故障设备总数
	 */
	public Integer getAllFaultsCount(){
		return this.dao.getAllFaultsCount();
	}
	
	/**
	 * 根据deviceID去is_faults查询详细信息
	 */
	public IsFaults getFaultsStateDetails(String deviceId) {
		return this.dao.getFaultsStateDetails(deviceId);
	}
	
	/**
	 * 获取需要维修的设备
	 */
	public List<IsFaults> getNeedRepair(){
		return this.dao.getNeedRepair();
	}
	
	/**
	 * 根据name获取
	 */
    public List<IsFaults> getFaultsByName(String name){
    	return this.dao.getFaultsByName(name);
    }
    
    /**
     * 获取故障记录
     * @return
     */
    public List<IsFaults> faultsList(){
    	return this.dao.faultsList();
    }
}