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
import com.jeesite.modules.is3dmcps.entity.IsDeviceUse;
import com.jeesite.modules.is3dmcps.service.IsDeviceUseService;

/**
 * 设备使用记录Controller
 * @author xx
 * @version 2019-03-12
 */
@Controller
@RequestMapping(value = "${adminPath}/is3dmcps/isDeviceUse")
public class IsDeviceUseController extends BaseController {

	@Autowired
	private IsDeviceUseService isDeviceUseService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public IsDeviceUse get(String id, boolean isNewRecord) {
		return isDeviceUseService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("is3dmcps:isDeviceUse:view")
	@RequestMapping(value = {"list", ""})
	public String list(IsDeviceUse isDeviceUse, Model model) {
		model.addAttribute("isDeviceUse", isDeviceUse);
		return "modules/is3dmcps/isDeviceUseList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("is3dmcps:isDeviceUse:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<IsDeviceUse> listData(IsDeviceUse isDeviceUse, HttpServletRequest request, HttpServletResponse response) {
		Page<IsDeviceUse> page = isDeviceUseService.findPage(new Page<IsDeviceUse>(request, response), isDeviceUse); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("is3dmcps:isDeviceUse:view")
	@RequestMapping(value = "form")
	public String form(IsDeviceUse isDeviceUse, Model model) {
		model.addAttribute("isDeviceUse", isDeviceUse);
		return "modules/is3dmcps/isDeviceUseForm";
	}

	/**
	 * 保存设备使用记录
	 */
	@RequiresPermissions("is3dmcps:isDeviceUse:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated IsDeviceUse isDeviceUse) {
		isDeviceUseService.save(isDeviceUse);
		return renderResult(Global.TRUE, text("保存设备使用记录成功！"));
	}
	
}