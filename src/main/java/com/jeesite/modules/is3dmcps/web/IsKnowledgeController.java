/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.is3dmcps.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.is3dmcps.entity.IsKnowledge;
import com.jeesite.modules.is3dmcps.service.IsKnowledgeService;

/**
 * 知识库Controller
 * @author xx
 * @version 2019-03-06
 */
@Controller
@RequestMapping(value = "${adminPath}/is3dmcps/isKnowledge")
public class IsKnowledgeController extends BaseController {

	@Autowired
	private IsKnowledgeService isKnowledgeService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public IsKnowledge get(String id, boolean isNewRecord) {
		return isKnowledgeService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("is3dmcps:isKnowledge:view")
	@RequestMapping(value = {"list", ""})
	public String list(IsKnowledge isKnowledge, Model model) {
		model.addAttribute("isKnowledge", isKnowledge);
		return "modules/is3dmcps/isKnowledgeList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("is3dmcps:isKnowledge:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<IsKnowledge> listData(IsKnowledge isKnowledge, HttpServletRequest request, HttpServletResponse response) {
		Page<IsKnowledge> page = isKnowledgeService.findPage(new Page<IsKnowledge>(request, response), isKnowledge); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("is3dmcps:isKnowledge:view")
	@RequestMapping(value = "form")
	public String form(IsKnowledge isKnowledge, Model model) {
		model.addAttribute("isKnowledge", isKnowledge);
		return "modules/is3dmcps/isKnowledgeForm";
	}

	/**
	 * 保存知识库
	 */
	@RequiresPermissions("is3dmcps:isKnowledge:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated IsKnowledge isKnowledge) {
		isKnowledgeService.save(isKnowledge);
		return renderResult(Global.TRUE, text("保存知识库成功！"));
	}
	
	/**
	 * 删除知识库
	 */
	@RequiresPermissions("is3dmcps:isKnowledge:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(IsKnowledge isKnowledge) {
		isKnowledgeService.delete(isKnowledge);
		return renderResult(Global.TRUE, text("删除知识库成功！"));
	}
	
}