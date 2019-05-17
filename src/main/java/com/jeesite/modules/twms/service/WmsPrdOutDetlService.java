/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.twms.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.twms.entity.WmsPrdOutDetl;
import com.jeesite.modules.twms.dao.WmsPrdOutDetlDao;

/**
 * wms_prd_out_detlService
 * @author xx
 * @version 2019-05-14
 */
@Service
@Transactional(readOnly=true)
public class WmsPrdOutDetlService extends CrudService<WmsPrdOutDetlDao, WmsPrdOutDetl> {
	
	/**
	 * 获取单条数据
	 * @param wmsPrdOutDetl
	 * @return
	 */
	@Override
	public WmsPrdOutDetl get(WmsPrdOutDetl wmsPrdOutDetl) {
		return super.get(wmsPrdOutDetl);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param wmsPrdOutDetl
	 * @return
	 */
	@Override
	public Page<WmsPrdOutDetl> findPage(Page<WmsPrdOutDetl> page, WmsPrdOutDetl wmsPrdOutDetl) {
		return super.findPage(page, wmsPrdOutDetl);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param wmsPrdOutDetl
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(WmsPrdOutDetl wmsPrdOutDetl) {
		super.save(wmsPrdOutDetl);
	}
	
	/**
	 * 更新状态
	 * @param wmsPrdOutDetl
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(WmsPrdOutDetl wmsPrdOutDetl) {
		super.updateStatus(wmsPrdOutDetl);
	}
	
	/**
	 * 删除数据
	 * @param wmsPrdOutDetl
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(WmsPrdOutDetl wmsPrdOutDetl) {
		super.delete(wmsPrdOutDetl);
	}
	
	/**
	 * 根据batch_no获取
	 * @param date
	 * @return
	 */
	public List<WmsPrdOutDetl> getDetailByBatchNo(String batchNo){
		return this.dao.getDetailByBatchNo(batchNo);
	}
	
	/**
	 * 根据月份获取brand和出库总重
	 */
	public List<WmsPrdOutDetl> getDetailMonthly(String month){
		return this.dao.getDetailMonthly(month);
	}
	
	/**
	 * 根据wo_no获取
	 */
	public List<WmsPrdOutDetl> getDetailByWN(String workNo){
		return this.dao.getDetailByWN(workNo);
	}
}