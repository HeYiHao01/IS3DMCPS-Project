/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.is3dmcps.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.is3dmcps.entity.IsMaintain;
import com.jeesite.modules.is3dmcps.dao.IsMaintainDao;

/**
 * 保养点Service
 * @author xx
 * @version 2019-03-06
 */
@Service
@Transactional(readOnly=true)
public class IsMaintainService extends CrudService<IsMaintainDao, IsMaintain> {
	
	/**
	 * 获取单条数据
	 * @param isMaintain
	 * @return
	 */
	@Override
	public IsMaintain get(IsMaintain isMaintain) {
		return super.get(isMaintain);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param isMaintain
	 * @return
	 */
	@Override
	public Page<IsMaintain> findPage(Page<IsMaintain> page, IsMaintain isMaintain) {
		return super.findPage(page, isMaintain);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param isMaintain
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(IsMaintain isMaintain) {
		super.save(isMaintain);
	}
	
	/**
	 * 更新状态
	 * @param isMaintain
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(IsMaintain isMaintain) {
		super.updateStatus(isMaintain);
	}
	
	/**
	 * 删除数据
	 * @param isMaintain
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(IsMaintain isMaintain) {
		super.delete(isMaintain);
	}
	
	/**
	 * 获取保养弹窗的content
	 */
	@Transactional(readOnly=false)
	public List<IsMaintain> getMaintainPopContent(String deviceCodeId){
		return this.dao.getMaintainPopContent(deviceCodeId);
	}
	
	/**
	 * 根据name获取
	 */
	public IsMaintain getByName(String name){
		return this.dao.getByName(name);
	}
	
	public IsMaintain getById(String id){
		return this.dao.getById(id);
	}
	
	/**
	 * 根据维保种类统计
	 * @return
	 */
	public List<IsMaintain> maintainTypeCount(){
		return this.dao.maintainTypeCount();
	}
}