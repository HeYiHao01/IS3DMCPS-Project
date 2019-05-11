/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.is3dmcps.dao;

import java.util.List;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.is3dmcps.entity.IsKnowledge;

/**
 * 知识库DAO接口
 * @author xx
 * @version 2019-03-06
 */
@MyBatisDao
public interface IsKnowledgeDao extends CrudDao<IsKnowledge> {
	public List<IsKnowledge> getAll();
	public List<IsKnowledge> getKnowledgeById(String knowledgeId);
}