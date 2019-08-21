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
import com.jeesite.modules.is3dmcps.entity.IsFaultsInfo;
import com.jeesite.modules.is3dmcps.dao.IsFaultsInfoDao;

/**
 * is_faults_infoService
 * @author yh
 * @version 2019-08-19
 */
@Service
@Transactional(readOnly=true)
public class IsFaultsInfoService extends CrudService<IsFaultsInfoDao, IsFaultsInfo> {
	
	/**
	 * 获取单条数据
	 * @param isFaultsInfo
	 * @return
	 */
	@Override
	public IsFaultsInfo get(IsFaultsInfo isFaultsInfo) {
		return super.get(isFaultsInfo);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param isFaultsInfo
	 * @return
	 */
	@Override
	public Page<IsFaultsInfo> findPage(Page<IsFaultsInfo> page, IsFaultsInfo isFaultsInfo) {
		return super.findPage(page, isFaultsInfo);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param isFaultsInfo
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(IsFaultsInfo isFaultsInfo) {
		super.save(isFaultsInfo);
	}
	
	/**
	 * 更新状态
	 * @param isFaultsInfo
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(IsFaultsInfo isFaultsInfo) {
		super.updateStatus(isFaultsInfo);
	}
	
	/**
	 * 删除数据
	 * @param isFaultsInfo
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(IsFaultsInfo isFaultsInfo) {
		super.delete(isFaultsInfo);
	}
	
	public IsFaultsInfo getFaultsInfo(@Param("faultCode") String faultCode){
		return this.dao.getFaultsInfo(faultCode);
	}
}