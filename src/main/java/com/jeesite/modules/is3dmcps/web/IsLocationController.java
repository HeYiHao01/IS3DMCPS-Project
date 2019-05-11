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
import com.jeesite.modules.is3dmcps.entity.IsLocation;
import com.jeesite.modules.is3dmcps.service.IsLocationService;

/**
 * is_locationController
 * @author xx
 * @version 2019-05-11
 */
@Controller
@RequestMapping(value = "${adminPath}/is3dmcps/isLocation")
public class IsLocationController extends BaseController {

	@Autowired
	private IsLocationService isLocationService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public IsLocation get(Integer locationId, boolean isNewRecord) {
		return isLocationService.get(locationId, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("is3dmcps:isLocation:view")
	@RequestMapping(value = {"list", ""})
	public String list(IsLocation isLocation, Model model) {
		model.addAttribute("isLocation", isLocation);
		return "modules/is3dmcps/isLocationList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("is3dmcps:isLocation:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<IsLocation> listData(IsLocation isLocation, HttpServletRequest request, HttpServletResponse response) {
		Page<IsLocation> page = isLocationService.findPage(new Page<IsLocation>(request, response), isLocation); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("is3dmcps:isLocation:view")
	@RequestMapping(value = "form")
	public String form(IsLocation isLocation, Model model) {
		model.addAttribute("isLocation", isLocation);
		return "modules/is3dmcps/isLocationForm";
	}

	/**
	 * 保存is_location
	 */
	@RequiresPermissions("is3dmcps:isLocation:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated IsLocation isLocation) {
		isLocationService.save(isLocation);
		return renderResult(Global.TRUE, text("保存is_location成功！"));
	}
	
	/**
	 * 删除is_location
	 */
	@RequiresPermissions("is3dmcps:isLocation:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(IsLocation isLocation) {
		isLocationService.delete(isLocation);
		return renderResult(Global.TRUE, text("删除is_location成功！"));
	}
	
}