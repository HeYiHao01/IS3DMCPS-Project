/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.isopc.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.isopc.dao.IsBatteryStatusDao;
import com.jeesite.modules.isopc.entity.IsBatteryStatus;

/**
 * 电池状态Service
 * @author xx
 * @version 2019-03-12
 */
@Service
@Transactional(readOnly=true)
public class IsBatteryStatusService extends CrudService<IsBatteryStatusDao, IsBatteryStatus> {
	
	/**
	 * 获取单条数据
	 * @param isBatteryStatus
	 * @return
	 */
	@Override
	public IsBatteryStatus get(IsBatteryStatus isBatteryStatus) {
		return super.get(isBatteryStatus);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param isBatteryStatus
	 * @return
	 */
	@Override
	public Page<IsBatteryStatus> findPage(Page<IsBatteryStatus> page, IsBatteryStatus isBatteryStatus) {
		return super.findPage(page, isBatteryStatus);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param isBatteryStatus
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(IsBatteryStatus isBatteryStatus) {
		super.save(isBatteryStatus);
	}
	
	/**
	 * 更新状态
	 * @param isBatteryStatus
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(IsBatteryStatus isBatteryStatus) {
		super.updateStatus(isBatteryStatus);
	}
	
	/**
	 * 删除数据
	 * @param isBatteryStatus
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(IsBatteryStatus isBatteryStatus) {
		super.delete(isBatteryStatus);
	}
	
}