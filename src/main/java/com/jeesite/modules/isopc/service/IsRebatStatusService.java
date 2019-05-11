/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.isopc.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.isopc.dao.IsRebatStatusDao;
import com.jeesite.modules.isopc.entity.IsRebatStatus;

/**
 * 快换电池装置状态Service
 * @author xx
 * @version 2019-03-12
 */
@Service
@Transactional(readOnly=true)
public class IsRebatStatusService extends CrudService<IsRebatStatusDao, IsRebatStatus> {
	
	/**
	 * 获取单条数据
	 * @param isRebatStatus
	 * @return
	 */
	@Override
	public IsRebatStatus get(IsRebatStatus isRebatStatus) {
		return super.get(isRebatStatus);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param isRebatStatus
	 * @return
	 */
	@Override
	public Page<IsRebatStatus> findPage(Page<IsRebatStatus> page, IsRebatStatus isRebatStatus) {
		return super.findPage(page, isRebatStatus);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param isRebatStatus
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(IsRebatStatus isRebatStatus) {
		super.save(isRebatStatus);
	}
	
	/**
	 * 更新状态
	 * @param isRebatStatus
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(IsRebatStatus isRebatStatus) {
		super.updateStatus(isRebatStatus);
	}
	
	/**
	 * 删除数据
	 * @param isRebatStatus
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(IsRebatStatus isRebatStatus) {
		super.delete(isRebatStatus);
	}
	
}