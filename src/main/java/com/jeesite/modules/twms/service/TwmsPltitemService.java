/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.twms.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.twms.entity.TwmsPltitem;
import com.jeesite.modules.twms.dao.TwmsPltitemDao;

/**
 * twms_pltitemService
 * @author xx
 * @version 2019-05-10
 */
@Service
@Transactional(readOnly=true)
public class TwmsPltitemService extends CrudService<TwmsPltitemDao, TwmsPltitem> {
	
	/**
	 * 获取单条数据
	 * @param twmsPltitem
	 * @return
	 */
	@Override
	public TwmsPltitem get(TwmsPltitem twmsPltitem) {
		return super.get(twmsPltitem);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param twmsPltitem
	 * @return
	 */
	@Override
	public Page<TwmsPltitem> findPage(Page<TwmsPltitem> page, TwmsPltitem twmsPltitem) {
		return super.findPage(page, twmsPltitem);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param twmsPltitem
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(TwmsPltitem twmsPltitem) {
		super.save(twmsPltitem);
	}
	
	/**
	 * 更新状态
	 * @param twmsPltitem
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(TwmsPltitem twmsPltitem) {
		super.updateStatus(twmsPltitem);
	}
	
	/**
	 * 删除数据
	 * @param twmsPltitem
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(TwmsPltitem twmsPltitem) {
		super.delete(twmsPltitem);
	}
	
	/**
	 * 获取各品牌当前库存数量
	 */
	public List<TwmsPltitem> getBrandCount() {
		return this.dao.getBrandCount();
	}
}