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
import com.jeesite.modules.is3dmcps.entity.IsFaultsInfo;
import com.jeesite.modules.is3dmcps.service.IsFaultsInfoService;

/**
 * is_faults_infoController
 * @author yh
 * @version 2019-08-19
 */
@Controller
@RequestMapping(value = "${adminPath}/is3dmcps/isFaultsInfo")
public class IsFaultsInfoController extends BaseController {

	@Autowired
	private IsFaultsInfoService isFaultsInfoService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public IsFaultsInfo get(String id, boolean isNewRecord) {
		return isFaultsInfoService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("is3dmcps:isFaultsInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(IsFaultsInfo isFaultsInfo, Model model) {
		model.addAttribute("isFaultsInfo", isFaultsInfo);
		return "modules/is3dmcps/isFaultsInfoList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("is3dmcps:isFaultsInfo:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<IsFaultsInfo> listData(IsFaultsInfo isFaultsInfo, HttpServletRequest request, HttpServletResponse response) {
		Page<IsFaultsInfo> page = isFaultsInfoService.findPage(new Page<IsFaultsInfo>(request, response), isFaultsInfo); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("is3dmcps:isFaultsInfo:view")
	@RequestMapping(value = "form")
	public String form(IsFaultsInfo isFaultsInfo, Model model) {
		model.addAttribute("isFaultsInfo", isFaultsInfo);
		return "modules/is3dmcps/isFaultsInfoForm";
	}

	/**
	 * 保存is_faults_info
	 */
	@RequiresPermissions("is3dmcps:isFaultsInfo:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated IsFaultsInfo isFaultsInfo) {
		isFaultsInfoService.save(isFaultsInfo);
		return renderResult(Global.TRUE, text("保存is_faults_info成功！"));
	}
	
	/**
	 * 删除is_faults_info
	 */
	@RequiresPermissions("is3dmcps:isFaultsInfo:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(IsFaultsInfo isFaultsInfo) {
		isFaultsInfoService.delete(isFaultsInfo);
		return renderResult(Global.TRUE, text("删除is_faults_info成功！"));
	}
	
}