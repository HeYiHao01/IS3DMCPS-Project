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
import com.jeesite.modules.isopc.entity.IsBatteryStatus;
import com.jeesite.modules.isopc.service.IsBatteryStatusService;

/**
 * 电池状态Controller
 * @author xx
 * @version 2019-03-12
 */
@Controller
@RequestMapping(value = "${adminPath}/isopc/isBatteryStatus")
public class IsBatteryStatusController extends BaseController {

	@Autowired
	private IsBatteryStatusService isBatteryStatusService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public IsBatteryStatus get(String deviceId, boolean isNewRecord) {
		return isBatteryStatusService.get(deviceId, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("isopc:isBatteryStatus:view")
	@RequestMapping(value = {"list", ""})
	public String list(IsBatteryStatus isBatteryStatus, Model model) {
		model.addAttribute("isBatteryStatus", isBatteryStatus);
		return "modules/isopc/isBatteryStatusList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("isopc:isBatteryStatus:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<IsBatteryStatus> listData(IsBatteryStatus isBatteryStatus, HttpServletRequest request, HttpServletResponse response) {
		Page<IsBatteryStatus> page = isBatteryStatusService.findPage(new Page<IsBatteryStatus>(request, response), isBatteryStatus); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("isopc:isBatteryStatus:view")
	@RequestMapping(value = "form")
	public String form(IsBatteryStatus isBatteryStatus, Model model) {
		model.addAttribute("isBatteryStatus", isBatteryStatus);
		return "modules/isopc/isBatteryStatusForm";
	}

	/**
	 * 保存电池状态
	 */
	@RequiresPermissions("isopc:isBatteryStatus:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated IsBatteryStatus isBatteryStatus) {
		isBatteryStatusService.save(isBatteryStatus);
		return renderResult(Global.TRUE, text("保存电池状态成功！"));
	}
	
}