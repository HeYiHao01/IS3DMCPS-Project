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
import com.jeesite.modules.twms.entity.TwmsTransferlogg;
import com.jeesite.modules.twms.dao.TwmsTransferloggDao;

/**
 * twms_transferloggService
 * @author xx
 * @version 2019-05-24
 */
@Service
@Transactional(readOnly=true)
public class TwmsTransferloggService extends CrudService<TwmsTransferloggDao, TwmsTransferlogg> {
	
	/**
	 * 获取单条数据
	 * @param twmsTransferlogg
	 * @return
	 */
	@Override
	public TwmsTransferlogg get(TwmsTransferlogg twmsTransferlogg) {
		return super.get(twmsTransferlogg);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param twmsTransferlogg
	 * @return
	 */
	@Override
	public Page<TwmsTransferlogg> findPage(Page<TwmsTransferlogg> page, TwmsTransferlogg twmsTransferlogg) {
		return super.findPage(page, twmsTransferlogg);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param twmsTransferlogg
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(TwmsTransferlogg twmsTransferlogg) {
		super.save(twmsTransferlogg);
	}
	
	/**
	 * 更新状态
	 * @param twmsTransferlogg
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(TwmsTransferlogg twmsTransferlogg) {
		super.updateStatus(twmsTransferlogg);
	}
	
	/**
	 * 删除数据
	 * @param twmsTransferlogg
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(TwmsTransferlogg twmsTransferlogg) {
		super.delete(twmsTransferlogg);
	}

	public TwmsTransferlogg get(Long loggnum, boolean isNewRecord) {		
		return super.get(String.valueOf(loggnum), isNewRecord);
	}
	
	/**
	 * 根据loggnum获取分页
	 */
	public TwmsTransferlogg getByLoggnum(@Param("loggNum")String loggNum,@Param("count")int count){
		return this.dao.getByLoggnum(loggNum, count);
	}
}