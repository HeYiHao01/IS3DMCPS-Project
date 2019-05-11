/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.is3dmcps.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.is3dmcps.entity.IsRepairRec;
import com.jeesite.modules.is3dmcps.dao.IsRepairRecDao;

/**
 * 维修记录Service
 * @author xx
 * @version 2019-03-08
 */
@Service
@Transactional(readOnly=true)
public class IsRepairRecService extends CrudService<IsRepairRecDao, IsRepairRec> {
	
	/**
	 * 获取单条数据
	 * @param isRepairRec
	 * @return
	 */
	@Override
	public IsRepairRec get(IsRepairRec isRepairRec) {
		return super.get(isRepairRec);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param isRepairRec
	 * @return
	 */
	@Override
	public Page<IsRepairRec> findPage(Page<IsRepairRec> page, IsRepairRec isRepairRec) {
		return super.findPage(page, isRepairRec);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param isRepairRec
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(IsRepairRec isRepairRec) {
		super.save(isRepairRec);
	}
	
	/**
	 * 更新状态
	 * @param isRepairRec
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(IsRepairRec isRepairRec) {
		super.updateStatus(isRepairRec);
	}
	
	/**
	 * 删除数据
	 * @param isRepairRec
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(IsRepairRec isRepairRec) {
		super.delete(isRepairRec);
	}

	/**
	 * 获取维修数
	 */
	public Integer getRepairCount(String repairTime) {
		return super.dao.getRepairCount(repairTime);
	}
	
	/**
	 * 根据faults_id获取故障设备最新维修结果
	 */
	public String getRepairResult(String faultsId) {
		return this.dao.getRepairResult(faultsId);
	}
}