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
import com.jeesite.modules.is3dmcps.entity.IsDeviceProperties;
import com.jeesite.modules.is3dmcps.service.IsDevicePropertiesService;


/**
 * is_device_propertiesController
 * @author xx
 * @version 2019-05-10
 */
@Controller
@RequestMapping(value = "${adminPath}/is/is3dmcps/isDeviceProperties")
public class IsDevicePropertiesController extends BaseController {

	@Autowired
	private IsDevicePropertiesService isDevicePropertiesService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public IsDeviceProperties get(String deviceId, boolean isNewRecord) {
		return isDevicePropertiesService.get(deviceId, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("is:is3dmcps:isDeviceProperties:view")
	@RequestMapping(value = {"list", ""})
	public String list(IsDeviceProperties isDeviceProperties, Model model) {
		model.addAttribute("isDeviceProperties", isDeviceProperties);
		return "modules/is/is3dmcps/isDevicePropertiesList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("is:is3dmcps:isDeviceProperties:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<IsDeviceProperties> listData(IsDeviceProperties isDeviceProperties, HttpServletRequest request, HttpServletResponse response) {
		Page<IsDeviceProperties> page = isDevicePropertiesService.findPage(new Page<IsDeviceProperties>(request, response), isDeviceProperties); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("is:is3dmcps:isDeviceProperties:view")
	@RequestMapping(value = "form")
	public String form(IsDeviceProperties isDeviceProperties, Model model) {
		model.addAttribute("isDeviceProperties", isDeviceProperties);
		return "modules/is/is3dmcps/isDevicePropertiesForm";
	}

	/**
	 * 保存is_device_properties
	 */
	@RequiresPermissions("is:is3dmcps:isDeviceProperties:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated IsDeviceProperties isDeviceProperties) {
		isDevicePropertiesService.save(isDeviceProperties);
		return renderResult(Global.TRUE, text("保存is_device_properties成功！"));
	}
	
	/**
	 * 删除is_device_properties
	 */
	@RequiresPermissions("is:is3dmcps:isDeviceProperties:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(IsDeviceProperties isDeviceProperties) {
		isDevicePropertiesService.delete(isDeviceProperties);
		return renderResult(Global.TRUE, text("删除is_device_properties成功！"));
	}
	
}