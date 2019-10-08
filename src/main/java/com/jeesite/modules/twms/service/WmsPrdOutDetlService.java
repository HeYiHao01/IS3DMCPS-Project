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
	
	/**
	 * 根据batch_no获取
	 * @param date
	 * @return
	 */
	public List<WmsPrdOutDetl> getNewDetailByBatchNo(String batchNo){
		return this.dao.getNewDetailByBatchNo(batchNo);
	}		
	
	/**
	 * 根据wo_no获取
	 */
	public List<WmsPrdOutDetl> getNewDetailByWN(String workNo){
		return this.dao.getNewDetailByWN(workNo);
	}
	
	/**
	 * 根据月份获取brand和出库总重
	 */
	public List<WmsPrdOutDetl> getNewDetailMonthly(String month){
		return this.dao.getNewDetailMonthly(month);
	}
	
	public List<WmsPrdOutDetl> getBatchWeightMonthly(String month){
		return this.dao.getBatchWeightMonthly(month);
	}
	public List<WmsPrdOutDetl> getClassWeightMonthly(String month){
		return this.dao.getClassWeightMonthly(month);
	}
	public List<WmsPrdOutDetl> getBrandDaily(String day){
		return this.dao.getBrandDaily(day);
	}
	public List<WmsPrdOutDetl> getBatchWeightDaily(String day){
		return this.dao.getBatchWeightDaily(day);
	}
	public List<WmsPrdOutDetl> getClassWeightDaily(String day){
		return this.dao.getClassWeightDaily(day);
	}
	
	public List<WmsPrdOutDetl> getNewDetailYearly(String year){
		return this.dao.getNewDetailYearly(year);
	}
	public List<WmsPrdOutDetl> getNewDetailDaily(String day){
		return this.dao.getNewDetailDaily(day);
	}
	public List<WmsPrdOutDetl> getBatchWeightYearly(String year){
		return this.dao.getBatchWeightYearly(year);
	}
	public List<WmsPrdOutDetl> getClassWeightYearly(String year){
		return this.dao.getClassWeightYearly(year);
	}
	
	public List<WmsPrdOutDetl> getBatchCountYearly(@Param("classTeam") String classTeam, @Param("year") String year){
		return this.dao.getBatchCountYearly(classTeam, year);
	}
	public List<WmsPrdOutDetl> getBatchCountMonthly(@Param("classTeam") String classTeam, @Param("month") String month){
		return this.dao.getBatchCountMonthly(classTeam, month);
	}
	public List<WmsPrdOutDetl> getBatchCountDaily(@Param("classTeam") String classTeam, @Param("day") String day){
		return this.dao.getBatchCountDaily(classTeam, day);
	}
	
	public List<WmsPrdOutDetl> getProductInfoCountBrandYearly(@Param("year") String year){
		return this.dao.getProductInfoCountBrandYearly(year);
	}
	public List<WmsPrdOutDetl> getProductInfoCountBrandMonthly(@Param("month") String month){
		return this.dao.getProductInfoCountBrandMonthly(month);
	}
	public List<WmsPrdOutDetl> getProductInfoCountBrandDaily(@Param("day") String day){
		return this.dao.getProductInfoCountBrandDaily(day);
	}
	public List<WmsPrdOutDetl> getProductInfoCountClassYearly(@Param("year") String year){
		return this.dao.getProductInfoCountClassYearly(year);
	}
	public List<WmsPrdOutDetl> getProductInfoCountClassMonthly(@Param("month") String month){
		return this.dao.getProductInfoCountClassMonthly(month);
	}
	public List<WmsPrdOutDetl> getProductInfoCountClassDaily(@Param("day") String day){
		return this.dao.getProductInfoCountClassDaily(day);
	}
	
	public List<WmsPrdOutDetl> getProductOutByEqu(@Param("day") String day){
		return this.dao.getProductOutByEqu(day);
	}
}