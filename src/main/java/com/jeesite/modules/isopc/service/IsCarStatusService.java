/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.isopc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.isopc.dao.IsCarRecDao;
import com.jeesite.modules.isopc.dao.IsCarStatusDao;
import com.jeesite.modules.isopc.entity.IsCarRec;
import com.jeesite.modules.isopc.entity.IsCarStatus;

/**
 * 穿梭车状态Service
 * @author xx
 * @version 2019-03-12
 */
@Service
@Transactional(readOnly=true)
public class IsCarStatusService extends CrudService<IsCarStatusDao, IsCarStatus> {
	
	@Autowired
	private IsCarRecDao isCarRecDao;
	
	/**
	 * 获取单条数据
	 * @param isCarStatus
	 * @return
	 */
	@Override
	public IsCarStatus get(IsCarStatus isCarStatus) {
		IsCarStatus entity = super.get(isCarStatus);
		if (entity != null){
			IsCarRec isCarRec = new IsCarRec(entity);
			isCarRec.setStatus(IsCarRec.STATUS_NORMAL);
			entity.setIsCarRecList(isCarRecDao.findList(isCarRec));
		}
		return entity;
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param isCarStatus
	 * @return
	 */
	@Override
	public Page<IsCarStatus> findPage(Page<IsCarStatus> page, IsCarStatus isCarStatus) {
		return super.findPage(page, isCarStatus);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param isCarStatus
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(IsCarStatus isCarStatus) {
		super.save(isCarStatus);
		// 保存 IsCarStatus子表
		for (IsCarRec isCarRec : isCarStatus.getIsCarRecList()){
			if (!IsCarRec.STATUS_DELETE.equals(isCarRec.getStatus())){
				isCarRec.setDeviceId(isCarStatus);
				if (isCarRec.getIsNewRecord()){
					isCarRec.preInsert();
					isCarRecDao.insert(isCarRec);
				}else{
					isCarRec.preUpdate();
					isCarRecDao.update(isCarRec);
				}
			}else{
				isCarRecDao.delete(isCarRec);
			}
		}
	}
	
	/**
	 * 更新状态
	 * @param isCarStatus
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(IsCarStatus isCarStatus) {
		super.updateStatus(isCarStatus);
	}
	
	/**
	 * 删除数据
	 * @param isCarStatus
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(IsCarStatus isCarStatus) {
		super.delete(isCarStatus);
		IsCarRec isCarRec = new IsCarRec();
		isCarRec.setDeviceId(isCarStatus);
		isCarRecDao.delete(isCarRec);
	}
	
}