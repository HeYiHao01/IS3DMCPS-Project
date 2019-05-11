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
import com.jeesite.modules.twms.entity.TwmsPltitem;
import com.jeesite.modules.twms.service.TwmsPltitemService;

/**
 * twms_pltitemController
 * @author xx
 * @version 2019-05-10
 */
@Controller
@RequestMapping(value = "${adminPath}/twms/twmsPltitem")
public class TwmsPltitemController extends BaseController {

	@Autowired
	private TwmsPltitemService twmsPltitemService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public TwmsPltitem get(String vpltnum, boolean isNewRecord) {
		return twmsPltitemService.get(vpltnum, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("twms:twmsPltitem:view")
	@RequestMapping(value = {"list", ""})
	public String list(TwmsPltitem twmsPltitem, Model model) {
		model.addAttribute("twmsPltitem", twmsPltitem);
		return "modules/twms/twmsPltitemList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("twms:twmsPltitem:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<TwmsPltitem> listData(TwmsPltitem twmsPltitem, HttpServletRequest request, HttpServletResponse response) {
		Page<TwmsPltitem> page = twmsPltitemService.findPage(new Page<TwmsPltitem>(request, response), twmsPltitem); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("twms:twmsPltitem:view")
	@RequestMapping(value = "form")
	public String form(TwmsPltitem twmsPltitem, Model model) {
		model.addAttribute("twmsPltitem", twmsPltitem);
		return "modules/twms/twmsPltitemForm";
	}

	/**
	 * 保存twms_pltitem
	 */
	@RequiresPermissions("twms:twmsPltitem:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated TwmsPltitem twmsPltitem) {
		twmsPltitemService.save(twmsPltitem);
		return renderResult(Global.TRUE, text("保存twms_pltitem成功！"));
	}
	
	/**
	 * 删除twms_pltitem
	 */
	@RequiresPermissions("twms:twmsPltitem:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(TwmsPltitem twmsPltitem) {
		twmsPltitemService.delete(twmsPltitem);
		return renderResult(Global.TRUE, text("删除twms_pltitem成功！"));
	}
	
}