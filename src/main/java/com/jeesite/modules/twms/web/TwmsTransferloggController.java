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
import com.jeesite.modules.twms.entity.TwmsTransferlogg;
import com.jeesite.modules.twms.service.TwmsTransferloggService;

/**
 * twms_transferloggController
 * @author xx
 * @version 2019-05-24
 */
@Controller
@RequestMapping(value = "${adminPath}/twms/twmsTransferlogg")
public class TwmsTransferloggController extends BaseController {

	@Autowired
	private TwmsTransferloggService twmsTransferloggService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public TwmsTransferlogg get(Long loggnum, boolean isNewRecord) {
		return twmsTransferloggService.get(loggnum, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("twms:twmsTransferlogg:view")
	@RequestMapping(value = {"list", ""})
	public String list(TwmsTransferlogg twmsTransferlogg, Model model) {
		model.addAttribute("twmsTransferlogg", twmsTransferlogg);
		return "modules/twms/twmsTransferloggList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("twms:twmsTransferlogg:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<TwmsTransferlogg> listData(TwmsTransferlogg twmsTransferlogg, HttpServletRequest request, HttpServletResponse response) {
		Page<TwmsTransferlogg> page = twmsTransferloggService.findPage(new Page<TwmsTransferlogg>(request, response), twmsTransferlogg); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("twms:twmsTransferlogg:view")
	@RequestMapping(value = "form")
	public String form(TwmsTransferlogg twmsTransferlogg, Model model) {
		model.addAttribute("twmsTransferlogg", twmsTransferlogg);
		return "modules/twms/twmsTransferloggForm";
	}

	/**
	 * 保存twms_transferlogg
	 */
	@RequiresPermissions("twms:twmsTransferlogg:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated TwmsTransferlogg twmsTransferlogg) {
		twmsTransferloggService.save(twmsTransferlogg);
		return renderResult(Global.TRUE, text("保存twms_transferlogg成功！"));
	}
	
	/**
	 * 删除twms_transferlogg
	 */
	@RequiresPermissions("twms:twmsTransferlogg:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(TwmsTransferlogg twmsTransferlogg) {
		twmsTransferloggService.delete(twmsTransferlogg);
		return renderResult(Global.TRUE, text("删除twms_transferlogg成功！"));
	}
	
}