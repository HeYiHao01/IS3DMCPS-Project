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
import com.jeesite.modules.twms.entity.WmsGdxdIn;
import com.jeesite.modules.twms.entity.WmsGdxdOut;
import com.jeesite.modules.twms.dao.WmsGdxdOutDao;

/**
 * wms_gdxd_outService
 * @author xx
 * @version 2019-05-14
 */
@Service
@Transactional(readOnly=true)
public class WmsGdxdOutService extends CrudService<WmsGdxdOutDao, WmsGdxdOut> {
	
	/**
	 * 获取单条数据
	 * @param wmsGdxdOut
	 * @return
	 */
	@Override
	public WmsGdxdOut get(WmsGdxdOut wmsGdxdOut) {
		return super.get(wmsGdxdOut);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param wmsGdxdOut
	 * @return
	 */
	@Override
	public Page<WmsGdxdOut> findPage(Page<WmsGdxdOut> page, WmsGdxdOut wmsGdxdOut) {
		return super.findPage(page, wmsGdxdOut);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param wmsGdxdOut
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(WmsGdxdOut wmsGdxdOut) {
		super.save(wmsGdxdOut);
	}
	
	/**
	 * 更新状态
	 * @param wmsGdxdOut
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(WmsGdxdOut wmsGdxdOut) {
		super.updateStatus(wmsGdxdOut);
	}
	
	/**
	 * 删除数据
	 * @param wmsGdxdOut
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(WmsGdxdOut wmsGdxdOut) {
		super.delete(wmsGdxdOut);
	}

	public WmsGdxdOut get(Integer id, boolean isNewRecord) {
		return super.get(String.valueOf(id), isNewRecord);
	}
	
	/**
	 * 根据日期获取出库工单信息
	 * @param date
	 * @return
	 */
	public List<WmsGdxdOut> getAllOut(String date){
		return this.dao.getAllOut(date);
	}
	/**
	 * 根据日期获取出库batch_no和timeCost
	 * @param date
	 * @return
	 */
	public List<WmsGdxdOut> getBatchAndTime(String date){
		return this.dao.getBatchAndTime(date);
	}
	
	public List<WmsGdxdOut> getByWN(String woNo){
		return this.dao.getByWN(woNo);
	}
	
	/**
	 * 根据日期获取出库工单信息
	 * @param date
	 * @return
	 */
	public List<WmsGdxdOut> getNewAllOut(String date){
		return this.dao.getNewAllOut(date);
	}
	/**
	 * 根据日期获取出库batch_no和timeCost
	 * @param date
	 * @return
	 */
	public List<WmsGdxdOut> getNewBatchAndTime(String date){
		return this.dao.getNewBatchAndTime(date);
	}
	
	public List<WmsGdxdOut> getNewByWN(String woNo){
		return this.dao.getNewByWN(woNo);
	}
	
	public List<WmsGdxdOut> getEquId(){
		return this.dao.getEquId();
	}
	public List<WmsGdxdOut> getAllByEquId(@Param("equId")String equId, @Param("count")int count){
		return this.dao.getAllByEquId(equId, count);
	}
	
	public List<WmsGdxdOut> getClassTeam(){
		return this.dao.getClassTeam();
	}
	public List<WmsGdxdOut> getAllByClassTeam(String teamCd){
		return this.dao.getAllByClassTeam(teamCd);
	}
	
	public List<WmsGdxdOut> getBrands(){
		return this.dao.getBrands();
	}
	public List<WmsGdxdOut> filterWorkOrderOut(@Param("equId") String equId, @Param("brand") String brand, @Param("startTime") String startTime, @Param("endTime") String endTime, @Param("rangeStart") int rangeStart, @Param("rangeEnd") int rangeEnd){
		return this.dao.filterWorkOrderOut(equId, brand, startTime, endTime, rangeStart, rangeEnd);
	}

	public List<WmsGdxdOut> filterByClassTeam(@Param("teamCd") String teamCd, @Param("brand") String brand, @Param("startTime") String startTime, @Param("endTime") String endTime, @Param("rangeStart") int rangeStart, @Param("rangeEnd") int rangeEnd){
		return this.dao.filterByClassTeam(teamCd, brand, startTime, endTime, rangeStart, rangeEnd);
	}
	
	public int getGdxdOutLength()
	{
		return this.dao.getGdxdOutLength();
	}
	public List<WmsGdxdOut> getByWoState(@Param("woState") String woState, @Param("length") int length){
		return this.dao.getByWoState(woState, length);
	}
	
	public List<WmsGdxdOut> getNewAllOutYearly(@Param("year") String year){
		return this.dao.getNewAllOutYearly(year);
	}
	public List<WmsGdxdOut> getNewAllOutMonthly(@Param("month") String month){
		return this.dao.getNewAllOutMonthly(month);
	}
	
	public List<WmsGdxdOut> filterWorkOrderOutBatch(@Param("equId") String equId, @Param("brand") String brand, @Param("batch") String batch, @Param("startTime") String startTime, @Param("endTime") String endTime, @Param("rangeStart") int rangeStart, @Param("rangeEnd") int rangeEnd){
		return this.dao.filterWorkOrderOutBatch(equId, brand, batch, startTime, endTime, rangeStart, rangeEnd);
	}
	public List<WmsGdxdOut> filterByClassTeamBatch(@Param("teamCd") String teamCd, @Param("brand") String brand, @Param("batch") String batch, @Param("startTime") String startTime, @Param("endTime") String endTime, @Param("rangeStart") int rangeStart, @Param("rangeEnd") int rangeEnd){
		return this.dao.filterByClassTeamBatch(teamCd, brand, batch, startTime, endTime, rangeStart, rangeEnd);
	}

}