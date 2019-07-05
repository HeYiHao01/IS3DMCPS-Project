/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.twms.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.twms.entity.WmsGdxdIn;
import com.jeesite.modules.twms.dao.WmsGdxdInDao;

/**
 * wms_gdxd_inService
 * @author xx
 * @version 2019-05-10
 */
@Service
@Transactional(readOnly=true)
public class WmsGdxdInService extends CrudService<WmsGdxdInDao, WmsGdxdIn> {
	
	/**
	 * 获取单条数据
	 * @param wmsGdxdIn
	 * @return
	 */
	@Override
	public WmsGdxdIn get(WmsGdxdIn wmsGdxdIn) {
		return super.get(wmsGdxdIn);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param wmsGdxdIn
	 * @return
	 */
	@Override
	public Page<WmsGdxdIn> findPage(Page<WmsGdxdIn> page, WmsGdxdIn wmsGdxdIn) {
		return super.findPage(page, wmsGdxdIn);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param wmsGdxdIn
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(WmsGdxdIn wmsGdxdIn) {
		super.save(wmsGdxdIn);
	}
	
	/**
	 * 更新状态
	 * @param wmsGdxdIn
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(WmsGdxdIn wmsGdxdIn) {
		super.updateStatus(wmsGdxdIn);
	}
	
	/**
	 * 删除数据
	 * @param wmsGdxdIn
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(WmsGdxdIn wmsGdxdIn) {
		super.delete(wmsGdxdIn);
	}
	
	public WmsGdxdIn get(Integer id, boolean isNewRecord) {		
		return super.get(String.valueOf(id), isNewRecord);
	}
	
	/**
	 * 获取工单信息(完成比例等)
	 * @return
	 */
	public List<WmsGdxdIn> getWorkInfo(String date) {
		return this.dao.getWorkInfo(date);
	}
	
	/**
	 * 根据日期获取工单信息
	 */
	public List<WmsGdxdIn> getAllIn(String date){
		return this.dao.getAllIn(date);
	}
	
	/**
	 * 根据日期获取batch_no和timeCost(end_time-start_time)
	 */
	public List<WmsGdxdIn> getBatchAndTime(String date){
		return this.dao.getBatchAndTime(date);
	}
	
	public List<WmsGdxdIn> getByWN(String woNo){
		return this.dao.getByWN(woNo);
	}
	
	/**
	 * 获取工单信息(完成比例等)
	 * @return
	 */
	public List<WmsGdxdIn> getNewWorkInfo(String date) {
		return this.dao.getNewWorkInfo(date);
	}
	
	/**
	 * 根据日期获取工单信息
	 */
	public List<WmsGdxdIn> getNewAllIn(String date){
		return this.dao.getNewAllIn(date);
	}
	
	/**
	 * 根据日期获取batch_no和timeCost(end_time-start_time)
	 */
	public List<WmsGdxdIn> getNewBatchAndTime(String date){
		return this.dao.getNewBatchAndTime(date);
	}
	
	public List<WmsGdxdIn> getNewByWN(String woNo){
		return this.dao.getNewByWN(woNo);
	}
}