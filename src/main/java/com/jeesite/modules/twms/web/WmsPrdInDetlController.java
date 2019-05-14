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
import com.jeesite.modules.twms.entity.WmsPrdInDetl;
import com.jeesite.modules.twms.service.WmsPrdInDetlService;

/**
 * wms_prd_in_detlController
 * @author xx
 * @version 2019-05-14
 */
@Controller
@RequestMapping(value = "${adminPath}/twms/wmsPrdInDetl")
public class WmsPrdInDetlController extends BaseController {

	@Autowired
	private WmsPrdInDetlService wmsPrdInDetlService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public WmsPrdInDetl get(String woNo, boolean isNewRecord) {
		return wmsPrdInDetlService.get(woNo, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("twms:wmsPrdInDetl:view")
	@RequestMapping(value = {"list", ""})
	public String list(WmsPrdInDetl wmsPrdInDetl, Model model) {
		model.addAttribute("wmsPrdInDetl", wmsPrdInDetl);
		return "modules/twms/wmsPrdInDetlList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("twms:wmsPrdInDetl:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<WmsPrdInDetl> listData(WmsPrdInDetl wmsPrdInDetl, HttpServletRequest request, HttpServletResponse response) {
		Page<WmsPrdInDetl> page = wmsPrdInDetlService.findPage(new Page<WmsPrdInDetl>(request, response), wmsPrdInDetl); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("twms:wmsPrdInDetl:view")
	@RequestMapping(value = "form")
	public String form(WmsPrdInDetl wmsPrdInDetl, Model model) {
		model.addAttribute("wmsPrdInDetl", wmsPrdInDetl);
		return "modules/twms/wmsPrdInDetlForm";
	}

	/**
	 * 保存wms_prd_in_detl
	 */
	@RequiresPermissions("twms:wmsPrdInDetl:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated WmsPrdInDetl wmsPrdInDetl) {
		wmsPrdInDetlService.save(wmsPrdInDetl);
		return renderResult(Global.TRUE, text("保存wms_prd_in_detl成功！"));
	}
	
	/**
	 * 删除wms_prd_in_detl
	 */
	@RequiresPermissions("twms:wmsPrdInDetl:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(WmsPrdInDetl wmsPrdInDetl) {
		wmsPrdInDetlService.delete(wmsPrdInDetl);
		return renderResult(Global.TRUE, text("删除wms_prd_in_detl成功！"));
	}
	
}