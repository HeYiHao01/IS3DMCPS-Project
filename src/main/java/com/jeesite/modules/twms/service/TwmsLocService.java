/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.twms.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.twms.entity.TwmsLoc;
import com.jeesite.modules.twms.dao.TwmsLocDao;

/**
 * twms_locService
 * @author xx
 * @version 2019-05-13
 */
@Service
@Transactional(readOnly=true)
public class TwmsLocService extends CrudService<TwmsLocDao, TwmsLoc> {
	
	/**
	 * 获取单条数据
	 * @param twmsLoc
	 * @return
	 */
	@Override
	public TwmsLoc get(TwmsLoc twmsLoc) {
		return super.get(twmsLoc);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param twmsLoc
	 * @return
	 */
	@Override
	public Page<TwmsLoc> findPage(Page<TwmsLoc> page, TwmsLoc twmsLoc) {
		return super.findPage(page, twmsLoc);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param twmsLoc
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(TwmsLoc twmsLoc) {
		super.save(twmsLoc);
	}
	
	/**
	 * 更新状态
	 * @param twmsLoc
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(TwmsLoc twmsLoc) {
		super.updateStatus(twmsLoc);
	}
	
	/**
	 * 删除数据
	 * @param twmsLoc
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(TwmsLoc twmsLoc) {
		super.delete(twmsLoc);
	}
	
	/**
	 * 获取货位状态表详情
	 */
	public List<TwmsLoc> getAll(){
		return this.dao.getAll();
	}
}