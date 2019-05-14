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
import com.jeesite.modules.twms.entity.TwmsLoc;
import com.jeesite.modules.twms.service.TwmsLocService;

/**
 * twms_locController
 * @author xx
 * @version 2019-05-13
 */
@Controller
@RequestMapping(value = "${adminPath}/twms/twmsLoc")
public class TwmsLocController extends BaseController {

	@Autowired
	private TwmsLocService twmsLocService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public TwmsLoc get(String locnum, boolean isNewRecord) {
		return twmsLocService.get(locnum, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("twms:twmsLoc:view")
	@RequestMapping(value = {"list", ""})
	public String list(TwmsLoc twmsLoc, Model model) {
		model.addAttribute("twmsLoc", twmsLoc);
		return "modules/twms/twmsLocList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("twms:twmsLoc:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<TwmsLoc> listData(TwmsLoc twmsLoc, HttpServletRequest request, HttpServletResponse response) {
		Page<TwmsLoc> page = twmsLocService.findPage(new Page<TwmsLoc>(request, response), twmsLoc); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("twms:twmsLoc:view")
	@RequestMapping(value = "form")
	public String form(TwmsLoc twmsLoc, Model model) {
		model.addAttribute("twmsLoc", twmsLoc);
		return "modules/twms/twmsLocForm";
	}

	/**
	 * 保存twms_loc
	 */
	@RequiresPermissions("twms:twmsLoc:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated TwmsLoc twmsLoc) {
		twmsLocService.save(twmsLoc);
		return renderResult(Global.TRUE, text("保存twms_loc成功！"));
	}
	
	/**
	 * 删除twms_loc
	 */
	@RequiresPermissions("twms:twmsLoc:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(TwmsLoc twmsLoc) {
		twmsLocService.delete(twmsLoc);
		return renderResult(Global.TRUE, text("删除twms_loc成功！"));
	}
	
}