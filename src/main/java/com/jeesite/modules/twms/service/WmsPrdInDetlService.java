/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.twms.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.twms.entity.WmsPrdInDetl;
import com.jeesite.modules.twms.dao.WmsPrdInDetlDao;

/**
 * wms_prd_in_detlService
 * @author xx
 * @version 2019-05-14
 */
@Service
@Transactional(readOnly=true)
public class WmsPrdInDetlService extends CrudService<WmsPrdInDetlDao, WmsPrdInDetl> {
	
	/**
	 * 获取单条数据
	 * @param wmsPrdInDetl
	 * @return
	 */
	@Override
	public WmsPrdInDetl get(WmsPrdInDetl wmsPrdInDetl) {
		return super.get(wmsPrdInDetl);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param wmsPrdInDetl
	 * @return
	 */
	@Override
	public Page<WmsPrdInDetl> findPage(Page<WmsPrdInDetl> page, WmsPrdInDetl wmsPrdInDetl) {
		return super.findPage(page, wmsPrdInDetl);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param wmsPrdInDetl
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(WmsPrdInDetl wmsPrdInDetl) {
		super.save(wmsPrdInDetl);
	}
	
	/**
	 * 更新状态
	 * @param wmsPrdInDetl
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(WmsPrdInDetl wmsPrdInDetl) {
		super.updateStatus(wmsPrdInDetl);
	}
	
	/**
	 * 删除数据
	 * @param wmsPrdInDetl
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(WmsPrdInDetl wmsPrdInDetl) {
		super.delete(wmsPrdInDetl);
	}
	
	/**
	 * 根据wo_no,batch_no获取工单详情
	 */
	public WmsPrdInDetl getDetailByWB(@Param("woNo")String woNo,@Param("batchNo")String batchNo){
		return this.dao.getDetailByWB(woNo, batchNo);
	}
	
	/**
	 * 根据batch_no获取工单详情
	 */
	public List<WmsPrdInDetl> getDetailByBatchNo(String batchNo){
		return this.dao.getDetailByBatchNo(batchNo);
	}
	
	/**
	 * 根据月份获取brand和入库总重
	 */
	public List<WmsPrdInDetl> getDetailMonthly(String month){
		return this.dao.getDetailMonthly(month);
	}
	
	/**
	 * 根据wo_no获取工单详情
	 */
	public List<WmsPrdInDetl> getDetailByWN(String workNo){
		return this.dao.getDetailByWN(workNo);
	}
	
	/**
	 * 根据batch_no获取工单详情
	 */
	public List<WmsPrdInDetl> getNewDetailByBatchNo(String batchNo){
		return this.dao.getNewDetailByBatchNo(batchNo);
	}	
	
	/**
	 * 根据wo_no获取工单详情
	 */
	public List<WmsPrdInDetl> getNewDetailByWN(String workNo){
		return this.dao.getNewDetailByWN(workNo);
	}
	
	/**
	 * 根据月份获取brand和入库总重
	 */
	public List<WmsPrdInDetl> getNewDetailMonthly(String month){
		return this.dao.getNewDetailMonthly(month);
	}
	
	public List<WmsPrdInDetl> getBatchWeightMonthly(String month){
		return this.dao.getBatchWeightMonthly(month);
	}
	public List<WmsPrdInDetl> getClassWeightMonthly(String month){
		return this.dao.getClassWeightMonthly(month);
	}
	public List<WmsPrdInDetl> getBrandDaily(String day){
		return this.dao.getBrandDaily(day);
	}
	public List<WmsPrdInDetl> getBatchWeightDaily(String day){
		return this.dao.getBatchWeightDaily(day);
	}
	public List<WmsPrdInDetl> getClassWeightDaily(String day){
		return this.dao.getClassWeightDaily(day);
	}
	
	public List<WmsPrdInDetl> getNewDetailYearly(String year){
		return this.dao.getNewDetailYearly(year);
	}
	public List<WmsPrdInDetl> getNewDetailDaily(String day){
		return this.dao.getNewDetailDaily(day);
	}
	
	public List<WmsPrdInDetl> getBatchWeightYearly(String year){
		return this.dao.getBatchWeightYearly(year);
	}
	public List<WmsPrdInDetl> getClassWeightYearly(String year){
		return this.dao.getClassWeightYearly(year);
	}
	
	public List<WmsPrdInDetl> getBatchCountYearly(@Param("classTeam") String classTeam, @Param("year") String year){
		return this.dao.getBatchCountYearly(classTeam, year);
	}
	public List<WmsPrdInDetl> getBatchCountMonthly(@Param("classTeam") String classTeam, @Param("month") String month){
		return this.dao.getBatchCountMonthly(classTeam, month);
	}
	public List<WmsPrdInDetl> getBatchCountDaily(@Param("classTeam") String classTeam, @Param("day") String day){
		return this.dao.getBatchCountDaily(classTeam, day);
	}

	public List<WmsPrdInDetl> getProductInfoCountBrandYearly(@Param("year") String year){
		return this.dao.getProductInfoCountBrandYearly(year);
	}
	public List<WmsPrdInDetl> getProductInfoCountBrandMonthly(@Param("month") String month){
		return this.dao.getProductInfoCountBrandMonthly(month);
	}
	public List<WmsPrdInDetl> getProductInfoCountBrandDaily(@Param("day") String day){
		return this.dao.getProductInfoCountBrandDaily(day);
	}
	public List<WmsPrdInDetl> getProductInfoCountClassYearly(@Param("year") String year){
		return this.dao.getProductInfoCountClassYearly(year);
	}
	public List<WmsPrdInDetl> getProductInfoCountClassMonthly(@Param("month") String month){
		return this.dao.getProductInfoCountClassMonthly(month);
	}
	public List<WmsPrdInDetl> getProductInfoCountClassDaily(@Param("day") String day){
		return this.dao.getProductInfoCountClassDaily(day);
	}
}