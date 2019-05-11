/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.isopc.web;

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
import com.jeesite.modules.isopc.entity.IsCarStatus;
import com.jeesite.modules.isopc.service.IsCarStatusService;

/**
 * 穿梭车状态Controller
 * @author xx
 * @version 2019-03-12
 */
@Controller
@RequestMapping(value = "${adminPath}/isopc/isCarStatus")
public class IsCarStatusController extends BaseController {

	@Autowired
	private IsCarStatusService isCarStatusService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public IsCarStatus get(String deviceId, boolean isNewRecord) {
		return isCarStatusService.get(deviceId, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("isopc:isCarStatus:view")
	@RequestMapping(value = {"list", ""})
	public String list(IsCarStatus isCarStatus, Model model) {
		model.addAttribute("isCarStatus", isCarStatus);
		return "modules/isopc/isCarStatusList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("isopc:isCarStatus:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<IsCarStatus> listData(IsCarStatus isCarStatus, HttpServletRequest request, HttpServletResponse response) {
		Page<IsCarStatus> page = isCarStatusService.findPage(new Page<IsCarStatus>(request, response), isCarStatus); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("isopc:isCarStatus:view")
	@RequestMapping(value = "form")
	public String form(IsCarStatus isCarStatus, Model model) {
		model.addAttribute("isCarStatus", isCarStatus);
		return "modules/isopc/isCarStatusForm";
	}

	/**
	 * 保存穿梭车状态
	 */
	@RequiresPermissions("isopc:isCarStatus:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated IsCarStatus isCarStatus) {
		isCarStatusService.save(isCarStatus);
		return renderResult(Global.TRUE, text("保存穿梭车状态成功！"));
	}
	
}