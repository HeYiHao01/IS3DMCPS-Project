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
import com.jeesite.modules.isopc.entity.IsRebatStatus;
import com.jeesite.modules.isopc.service.IsRebatStatusService;

/**
 * 快换电池装置状态Controller
 * @author xx
 * @version 2019-03-12
 */
@Controller
@RequestMapping(value = "${adminPath}/isopc/isRebatStatus")
public class IsRebatStatusController extends BaseController {

	@Autowired
	private IsRebatStatusService isRebatStatusService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public IsRebatStatus get(String deviceId, boolean isNewRecord) {
		return isRebatStatusService.get(deviceId, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("isopc:isRebatStatus:view")
	@RequestMapping(value = {"list", ""})
	public String list(IsRebatStatus isRebatStatus, Model model) {
		model.addAttribute("isRebatStatus", isRebatStatus);
		return "modules/isopc/isRebatStatusList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("isopc:isRebatStatus:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<IsRebatStatus> listData(IsRebatStatus isRebatStatus, HttpServletRequest request, HttpServletResponse response) {
		Page<IsRebatStatus> page = isRebatStatusService.findPage(new Page<IsRebatStatus>(request, response), isRebatStatus); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("isopc:isRebatStatus:view")
	@RequestMapping(value = "form")
	public String form(IsRebatStatus isRebatStatus, Model model) {
		model.addAttribute("isRebatStatus", isRebatStatus);
		return "modules/isopc/isRebatStatusForm";
	}

	/**
	 * 保存快换电池装置状态
	 */
	@RequiresPermissions("isopc:isRebatStatus:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated IsRebatStatus isRebatStatus) {
		isRebatStatusService.save(isRebatStatus);
		return renderResult(Global.TRUE, text("保存快换电池装置状态成功！"));
	}
	
}