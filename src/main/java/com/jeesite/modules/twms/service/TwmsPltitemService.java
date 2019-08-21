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
import com.jeesite.modules.twms.entity.TwmsPltitem;
import com.jeesite.modules.is3dmcps.entity.BoxStatics;
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
	
	/**
	 * 获取货箱状态数据
	 */
	public List<BoxStatics> getBoxStatics(@Param("vpltnum") String vpltnum){
		return this.dao.getBoxStatics(vpltnum);
	}
	
	/**
	 * 获取各品牌当前库存数量
	 */
	public List<TwmsPltitem> getNewBrandCount() {
		return this.dao.getNewBrandCount();
	}
	
	/**
	 * 货箱刷新
	 * @return
	 */
	public List<BoxStatics> getContainLocation(){
		return this.dao.getContainLocation();
	}
	
	/**
	 * 获取货箱状态数据
	 */
	public List<BoxStatics> getNewBoxStatics(@Param("vpltnum") String vpltnum){
		return this.dao.getNewBoxStatics(vpltnum);
	}
	
	/**
	 * 获取实箱数
	 * @return
	 */
	public int getRealCase() {
		return this.dao.getRealCase();
	}
	
	/**
	 * 获取空箱数
	 * @return
	 */
	public int getEmptyCase() {
		return this.dao.getEmptyCase();
	}
	
	public List<BoxStatics> filterNewBoxStatics(@Param("brand") String brand,@Param("rangeStart")  int rangeStart,@Param("rangeEnd")  int rangeEnd){
		return this.dao.filterNewBoxStatics(brand, rangeStart, rangeEnd);
	}
}