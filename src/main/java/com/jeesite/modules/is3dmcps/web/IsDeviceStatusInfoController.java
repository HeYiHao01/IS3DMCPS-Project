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
import com.jeesite.modules.is3dmcps.entity.IsDeviceStatusInfo;
import com.jeesite.modules.is3dmcps.service.IsDeviceStatusInfoService;

/**
 * is_device_status_infoController
 * @author hh
 * @version 2020-04-08
 */
@Controller
@RequestMapping(value = "${adminPath}/is3dmcps/isDeviceStatusInfo")
public class IsDeviceStatusInfoController extends BaseController {

	@Autowired
	private IsDeviceStatusInfoService isDeviceStatusInfoService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public IsDeviceStatusInfo get(String id, String enField, boolean isNewRecord) {
		return isDeviceStatusInfoService.get(new Class<?>[]{String.class, String.class},
				new Object[]{id, enField}, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("is3dmcps:isDeviceStatusInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(IsDeviceStatusInfo isDeviceStatusInfo, Model model) {
		model.addAttribute("isDeviceStatusInfo", isDeviceStatusInfo);
		return "modules/is3dmcps/isDeviceStatusInfoList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("is3dmcps:isDeviceStatusInfo:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<IsDeviceStatusInfo> listData(IsDeviceStatusInfo isDeviceStatusInfo, HttpServletRequest request, HttpServletResponse response) {
		Page<IsDeviceStatusInfo> page = isDeviceStatusInfoService.findPage(new Page<IsDeviceStatusInfo>(request, response), isDeviceStatusInfo); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("is3dmcps:isDeviceStatusInfo:view")
	@RequestMapping(value = "form")
	public String form(IsDeviceStatusInfo isDeviceStatusInfo, Model model) {
		model.addAttribute("isDeviceStatusInfo", isDeviceStatusInfo);
		return "modules/is3dmcps/isDeviceStatusInfoForm";
	}

	/**
	 * 保存is_device_status_info
	 */
	@RequiresPermissions("is3dmcps:isDeviceStatusInfo:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated IsDeviceStatusInfo isDeviceStatusInfo) {
		isDeviceStatusInfoService.save(isDeviceStatusInfo);
		return renderResult(Global.TRUE, text("保存is_device_status_info成功！"));
	}
	
	/**
	 * 删除is_device_status_info
	 */
	@RequiresPermissions("is3dmcps:isDeviceStatusInfo:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(IsDeviceStatusInfo isDeviceStatusInfo) {
		isDeviceStatusInfoService.delete(isDeviceStatusInfo);
		return renderResult(Global.TRUE, text("删除is_device_status_info成功！"));
	}
	
}