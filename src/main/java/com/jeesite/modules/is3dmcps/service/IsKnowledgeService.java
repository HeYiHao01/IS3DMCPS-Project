/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.is3dmcps.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.is3dmcps.entity.IsKnowledge;
import com.jeesite.modules.is3dmcps.dao.IsKnowledgeDao;

/**
 * 知识库Service
 * @author xx
 * @version 2019-03-06
 */
@Service
@Transactional(readOnly=true)
public class IsKnowledgeService extends CrudService<IsKnowledgeDao, IsKnowledge> {
	
	/**
	 * 获取单条数据
	 * @param isKnowledge
	 * @return
	 */
	@Override
	public IsKnowledge get(IsKnowledge isKnowledge) {
		return super.get(isKnowledge);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param isKnowledge
	 * @return
	 */
	@Override
	public Page<IsKnowledge> findPage(Page<IsKnowledge> page, IsKnowledge isKnowledge) {
		return super.findPage(page, isKnowledge);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param isKnowledge
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(IsKnowledge isKnowledge) {
		super.save(isKnowledge);
	}
	
	/**
	 * 更新状态
	 * @param isKnowledge
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(IsKnowledge isKnowledge) {
		super.updateStatus(isKnowledge);
	}
	
	/**
	 * 删除数据
	 * @param isKnowledge
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(IsKnowledge isKnowledge) {
		super.delete(isKnowledge);
	}
	
	/**
	 * 获取知识库所有内容
	 */
	public List<IsKnowledge> getAll(){
		return this.dao.getAll();
	}
	
	/**
	 * 根据ID查找知识库
	 */
	public List<IsKnowledge> getKnowledgeById(String knowledgeId){
		return this.dao.getKnowledgeById(knowledgeId);
	}
	
	public IsKnowledge getKnowledgeByTitle(@Param("title") String title){
		return this.dao.getKnowledgeByTitle(title);
	}
}