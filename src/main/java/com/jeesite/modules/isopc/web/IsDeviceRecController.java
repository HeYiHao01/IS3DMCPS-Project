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
import com.jeesite.modules.isopc.entity.IsDeviceRec;
import com.jeesite.modules.isopc.service.IsDeviceRecService;

/**
 * 设备运行记录Controller
 * @author zhangxu
 * @version 2019-04-25
 */
@Controller
@RequestMapping(value = "${adminPath}/isopc/isDeviceRec")
public class IsDeviceRecController extends BaseController {

	@Autowired
	private IsDeviceRecService isDeviceRecService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public IsDeviceRec get(String id, boolean isNewRecord) {
		return isDeviceRecService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("isopc:isDeviceRec:view")
	@RequestMapping(value = {"list", ""})
	public String list(IsDeviceRec isDeviceRec, Model model) {
		model.addAttribute("isDeviceRec", isDeviceRec);
		return "modules/isopc/isDeviceRecList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("isopc:isDeviceRec:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<IsDeviceRec> listData(IsDeviceRec isDeviceRec, HttpServletRequest request, HttpServletResponse response) {
		Page<IsDeviceRec> page = isDeviceRecService.findPage(new Page<IsDeviceRec>(request, response), isDeviceRec); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("isopc:isDeviceRec:view")
	@RequestMapping(value = "form")
	public String form(IsDeviceRec isDeviceRec, Model model) {
		model.addAttribute("isDeviceRec", isDeviceRec);
		return "modules/isopc/isDeviceRecForm";
	}

	/**
	 * 保存设备运行记录
	 */
	@RequiresPermissions("isopc:isDeviceRec:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated IsDeviceRec isDeviceRec) {
		isDeviceRecService.save(isDeviceRec);
		return renderResult(Global.TRUE, text("保存设备运行记录成功！"));
	}
	
	/**
	 * 删除设备运行记录
	 */
	@RequiresPermissions("isopc:isDeviceRec:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(IsDeviceRec isDeviceRec) {
		isDeviceRecService.delete(isDeviceRec);
		return renderResult(Global.TRUE, text("删除设备运行记录成功！"));
	}
	
}