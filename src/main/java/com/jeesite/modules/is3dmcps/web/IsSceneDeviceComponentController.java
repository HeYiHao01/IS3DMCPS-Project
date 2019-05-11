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
import com.jeesite.modules.is3dmcps.entity.IsSceneDeviceComponent;
import com.jeesite.modules.is3dmcps.service.IsSceneDeviceComponentService;

/**
 * is_scene_device_componentController
 * @author xx
 * @version 2019-05-10
 */
@Controller
@RequestMapping(value = "${adminPath}/is3dmcps/isSceneDeviceComponent")
public class IsSceneDeviceComponentController extends BaseController {

	@Autowired
	private IsSceneDeviceComponentService isSceneDeviceComponentService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public IsSceneDeviceComponent get(String deviceTypeId, boolean isNewRecord) {
		return isSceneDeviceComponentService.get(deviceTypeId, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("is3dmcps:isSceneDeviceComponent:view")
	@RequestMapping(value = {"list", ""})
	public String list(IsSceneDeviceComponent isSceneDeviceComponent, Model model) {
		model.addAttribute("isSceneDeviceComponent", isSceneDeviceComponent);
		return "modules/is3dmcps/isSceneDeviceComponentList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("is3dmcps:isSceneDeviceComponent:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<IsSceneDeviceComponent> listData(IsSceneDeviceComponent isSceneDeviceComponent, HttpServletRequest request, HttpServletResponse response) {
		Page<IsSceneDeviceComponent> page = isSceneDeviceComponentService.findPage(new Page<IsSceneDeviceComponent>(request, response), isSceneDeviceComponent); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("is3dmcps:isSceneDeviceComponent:view")
	@RequestMapping(value = "form")
	public String form(IsSceneDeviceComponent isSceneDeviceComponent, Model model) {
		model.addAttribute("isSceneDeviceComponent", isSceneDeviceComponent);
		return "modules/is3dmcps/isSceneDeviceComponentForm";
	}

	/**
	 * 保存is_scene_device_component
	 */
	@RequiresPermissions("is3dmcps:isSceneDeviceComponent:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated IsSceneDeviceComponent isSceneDeviceComponent) {
		isSceneDeviceComponentService.save(isSceneDeviceComponent);
		return renderResult(Global.TRUE, text("保存is_scene_device_component成功！"));
	}
	
	/**
	 * 删除is_scene_device_component
	 */
	@RequiresPermissions("is3dmcps:isSceneDeviceComponent:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(IsSceneDeviceComponent isSceneDeviceComponent) {
		isSceneDeviceComponentService.delete(isSceneDeviceComponent);
		return renderResult(Global.TRUE, text("删除is_scene_device_component成功！"));
	}
	
}