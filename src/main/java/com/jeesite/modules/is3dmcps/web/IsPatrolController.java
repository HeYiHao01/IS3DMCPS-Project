/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.is3dmcps.web;

import java.util.List;

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
import com.jeesite.modules.is3dmcps.entity.IsPatrol;
import com.jeesite.modules.is3dmcps.service.IsKnowledgeService;
import com.jeesite.modules.is3dmcps.service.IsPatrolService;

/**
 * 设备巡检点Controller
 * @author xx
 * @version 2019-03-07
 */
@Controller
@RequestMapping(value = "${adminPath}/is3dmcps/isPatrol")
public class IsPatrolController extends BaseController {

	@Autowired
	private IsPatrolService isPatrolService;
	@Autowired
	private IsKnowledgeService isKnowledgeService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public IsPatrol get(String id, boolean isNewRecord) {
		return isPatrolService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("is3dmcps:isPatrol:view")
	@RequestMapping(value = {"list", ""})
	public String list(IsPatrol isPatrol, Model model) {
		model.addAttribute("isPatrol", isPatrol);
		return "modules/is3dmcps/isPatrolList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("is3dmcps:isPatrol:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<IsPatrol> listData(IsPatrol isPatrol, HttpServletRequest request, HttpServletResponse response) {
		Page<IsPatrol> page = isPatrolService.findPage(new Page<IsPatrol>(request, response), isPatrol); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("is3dmcps:isPatrol:view")
	@RequestMapping(value = "form")
	public String form(IsPatrol isPatrol, Model model) {
		model.addAttribute("isPatrol", isPatrol);
		//知识选择
		IsKnowledge isKnowledge = new IsKnowledge();
		isKnowledge.setType("05");
		List<IsKnowledge> isKnowledgeList =isKnowledgeService.findList(isKnowledge);
		model.addAttribute("isKnowledgeList", isKnowledgeList);
		return "modules/is3dmcps/isPatrolForm";
	}

	/**
	 * 保存巡检点
	 */
	@RequiresPermissions("is3dmcps:isPatrol:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated IsPatrol isPatrol) {
		isPatrolService.save(isPatrol);
		return renderResult(Global.TRUE, text("保存巡检点成功！"));
	}
	
	/**
	 * 删除巡检点
	 */
	@RequiresPermissions("is3dmcps:isPatrol:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(IsPatrol isPatrol) {
		isPatrolService.delete(isPatrol);
		return renderResult(Global.TRUE, text("删除巡检点成功！"));
	}

}