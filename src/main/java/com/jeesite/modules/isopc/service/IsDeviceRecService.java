/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.isopc.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.isopc.entity.IsDeviceRec;
import com.jeesite.modules.isopc.dao.IsDeviceRecDao;

/**
 * 设备运行记录Service
 * @author zhangxu
 * @version 2019-04-25
 */
@Service
@Transactional(readOnly=true)
public class IsDeviceRecService extends CrudService<IsDeviceRecDao, IsDeviceRec> {
	
	/**
	 * 获取单条数据
	 * @param isDeviceRec
	 * @return
	 */
	@Override
	public IsDeviceRec get(IsDeviceRec isDeviceRec) {
		return super.get(isDeviceRec);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param isDeviceRec
	 * @return
	 */
	@Override
	public Page<IsDeviceRec> findPage(Page<IsDeviceRec> page, IsDeviceRec isDeviceRec) {
		return super.findPage(page, isDeviceRec);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param isDeviceRec
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(IsDeviceRec isDeviceRec) {
		super.save(isDeviceRec);
	}
	
	/**
	 * 更新状态
	 * @param isDeviceRec
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(IsDeviceRec isDeviceRec) {
		super.updateStatus(isDeviceRec);
	}
	
	/**
	 * 删除数据
	 * @param isDeviceRec
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(IsDeviceRec isDeviceRec) {
		super.delete(isDeviceRec);
	}

	public int getTimeById(String device_id){
		List<String> times=super.dao.getTimeById(device_id);
		int timesInt=0;
		if(times!=null){
			for(String time:times){
				timesInt+=Integer.valueOf(time);
			}
			return timesInt;
		}else{
			return 0;
		}
	}
}