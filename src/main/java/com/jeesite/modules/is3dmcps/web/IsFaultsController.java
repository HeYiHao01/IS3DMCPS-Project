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
import com.jeesite.modules.is3dmcps.entity.IsDevice;
import com.jeesite.modules.is3dmcps.entity.IsFaults;
import com.jeesite.modules.is3dmcps.entity.IsKnowledge;
import com.jeesite.modules.is3dmcps.service.IsDeviceService;
import com.jeesite.modules.is3dmcps.service.IsFaultsService;
import com.jeesite.modules.is3dmcps.service.IsKnowledgeService;

/**
 * 设备故障Controller
 * @author xx
 * @version 2019-03-08
 */
@Controller
@RequestMapping(value = "${adminPath}/is3dmcps/isFaults")
public class IsFaultsController extends BaseController {

	@Autowired
	private IsFaultsService isFaultsService;
	@Autowired
	private IsDeviceService isDeviceService;
	@Autowired
	private IsKnowledgeService isKnowledgeService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public IsFaults get(String id, boolean isNewRecord) {
		return isFaultsService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("is3dmcps:isFaults:view")
	@RequestMapping(value = {"list", ""})
	public String list(IsFaults isFaults, Model model) {
		model.addAttribute("isFaults", isFaults);
		return "modules/is3dmcps/isFaultsList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("is3dmcps:isFaults:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<IsFaults> listData(IsFaults isFaults, HttpServletRequest request, HttpServletResponse response) {
		Page<IsFaults> page = isFaultsService.findPage(new Page<IsFaults>(request, response), isFaults); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("is3dmcps:isFaults:view")
	@RequestMapping(value = "form")
	public String form(IsFaults isFaults, Model model) {
		model.addAttribute("isFaults", isFaults);
		//知识选择
		IsKnowledge isKnowledge = new IsKnowledge();
		isKnowledge.setType("06");
		List<IsKnowledge> isKnowledgeList =isKnowledgeService.findList(isKnowledge);
		model.addAttribute("isKnowledgeList", isKnowledgeList);
		return "modules/is3dmcps/isFaultsForm";
	}

	/**
	 * 保存设备故障
	 */
	@RequiresPermissions("is3dmcps:isFaults:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated IsFaults isFaults) {
		if(isFaults.getIsNewRecord()) {
			IsDevice isDevice =isDeviceService.get(isFaults.getDeviceId());
			isDevice.setDeviceStatus("2");
			isDeviceService.save(isDevice);
		}
		isFaultsService.save(isFaults);
		return renderResult(Global.TRUE, text("保存设备故障成功！"));
	}
	
	/**
	 * 删除设备故障
	 */
	@RequiresPermissions("is3dmcps:isFaults:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(IsFaults isFaults) {
		isFaultsService.delete(isFaults);
		return renderResult(Global.TRUE, text("删除设备故障成功！"));
	}
	
}