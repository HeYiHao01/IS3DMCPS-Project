/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.twms.web;

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
import com.jeesite.modules.twms.entity.WmsGdxdIn;
import com.jeesite.modules.twms.service.WmsGdxdInService;

/**
 * wms_gdxd_inController
 * @author xx
 * @version 2019-05-10
 */
@Controller
@RequestMapping(value = "${adminPath}/twms/wmsGdxdIn")
public class WmsGdxdInController extends BaseController {

	@Autowired
	private WmsGdxdInService wmsGdxdInService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public WmsGdxdIn get(Integer id, boolean isNewRecord) {
		return wmsGdxdInService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("twms:wmsGdxdIn:view")
	@RequestMapping(value = {"list", ""})
	public String list(WmsGdxdIn wmsGdxdIn, Model model) {
		model.addAttribute("wmsGdxdIn", wmsGdxdIn);
		return "modules/twms/wmsGdxdInList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("twms:wmsGdxdIn:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<WmsGdxdIn> listData(WmsGdxdIn wmsGdxdIn, HttpServletRequest request, HttpServletResponse response) {
		Page<WmsGdxdIn> page = wmsGdxdInService.findPage(new Page<WmsGdxdIn>(request, response), wmsGdxdIn); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("twms:wmsGdxdIn:view")
	@RequestMapping(value = "form")
	public String form(WmsGdxdIn wmsGdxdIn, Model model) {
		model.addAttribute("wmsGdxdIn", wmsGdxdIn);
		return "modules/twms/wmsGdxdInForm";
	}

	/**
	 * 保存wms_gdxd_in
	 */
	@RequiresPermissions("twms:wmsGdxdIn:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated WmsGdxdIn wmsGdxdIn) {
		wmsGdxdInService.save(wmsGdxdIn);
		return renderResult(Global.TRUE, text("保存wms_gdxd_in成功！"));
	}
	
	/**
	 * 删除wms_gdxd_in
	 */
	@RequiresPermissions("twms:wmsGdxdIn:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(WmsGdxdIn wmsGdxdIn) {
		wmsGdxdInService.delete(wmsGdxdIn);
		return renderResult(Global.TRUE, text("删除wms_gdxd_in成功！"));
	}
	
}