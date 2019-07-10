/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.is3dmcps.service;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.is3dmcps.entity.IsPatrolRec;
import com.jeesite.modules.is3dmcps.dao.IsPatrolRecDao;

import java.util.Date;
import java.util.List;

/**
 * 巡检记录Service
 * @author xx
 * @version 2019-03-07
 */
@Service
@Transactional(readOnly=true)
public class IsPatrolRecService extends CrudService<IsPatrolRecDao, IsPatrolRec> {
	
	/**
	 * 获取单条数据
	 * @param isPatrolRec
	 * @return
	 */
	@Override
	public IsPatrolRec get(IsPatrolRec isPatrolRec) {
		return super.get(isPatrolRec);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param isPatrolRec
	 * @return
	 */
	@Override
	public Page<IsPatrolRec> findPage(Page<IsPatrolRec> page, IsPatrolRec isPatrolRec) {
		return super.findPage(page, isPatrolRec);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param isPatrolRec
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(IsPatrolRec isPatrolRec) {
		super.save(isPatrolRec);
	}
	
	/**
	 * 更新状态
	 * @param isPatrolRec
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(IsPatrolRec isPatrolRec) {
		super.updateStatus(isPatrolRec);
	}
	
	/**
	 * 删除数据
	 * @param isPatrolRec
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(IsPatrolRec isPatrolRec) {
		super.delete(isPatrolRec);
	}


	/**
	 * 获得最后时间
	 */
	public Date getLastTime(String id){return super.dao.getLastTime(id);}
	
	/**
	 * 获取巡检次数
	 */
	public Integer getPatrolCount(String date){
		return super.dao.getPatrolCount(date);
	}
	
	/**
	 * 获取所有巡检记录
	 * @return
	 */
	public List<IsPatrolRec> patrolList(){
		return this.dao.patrolList();
	}
	
	public List<IsPatrolRec> patrolPlanList(){
		return this.dao.patrolPlanList();
	}
	
	public List<IsPatrolRec> patrolLogList(){
		return this.dao.patrolLogList();
	}
	
	public List<IsPatrolRec> filterPatrolLog(@Param("patrolName")String patrolName, @Param("operator")String operator, @Param("startTime")String startTime, @Param("endTime")String endTime){
		return this.dao.filterPatrolLog(patrolName, operator, startTime, endTime);
	}
}