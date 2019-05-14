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
import com.jeesite.modules.twms.entity.WmsPrdOutDetl;
import com.jeesite.modules.twms.service.WmsPrdOutDetlService;

/**
 * wms_prd_out_detlController
 * @author xx
 * @version 2019-05-14
 */
@Controller
@RequestMapping(value = "${adminPath}/twms/wmsPrdOutDetl")
public class WmsPrdOutDetlController extends BaseController {

	@Autowired
	private WmsPrdOutDetlService wmsPrdOutDetlService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public WmsPrdOutDetl get(String woNo, boolean isNewRecord) {
		return wmsPrdOutDetlService.get(woNo, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("twms:wmsPrdOutDetl:view")
	@RequestMapping(value = {"list", ""})
	public String list(WmsPrdOutDetl wmsPrdOutDetl, Model model) {
		model.addAttribute("wmsPrdOutDetl", wmsPrdOutDetl);
		return "modules/twms/wmsPrdOutDetlList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("twms:wmsPrdOutDetl:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<WmsPrdOutDetl> listData(WmsPrdOutDetl wmsPrdOutDetl, HttpServletRequest request, HttpServletResponse response) {
		Page<WmsPrdOutDetl> page = wmsPrdOutDetlService.findPage(new Page<WmsPrdOutDetl>(request, response), wmsPrdOutDetl); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("twms:wmsPrdOutDetl:view")
	@RequestMapping(value = "form")
	public String form(WmsPrdOutDetl wmsPrdOutDetl, Model model) {
		model.addAttribute("wmsPrdOutDetl", wmsPrdOutDetl);
		return "modules/twms/wmsPrdOutDetlForm";
	}

	/**
	 * 保存wms_prd_out_detl
	 */
	@RequiresPermissions("twms:wmsPrdOutDetl:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated WmsPrdOutDetl wmsPrdOutDetl) {
		wmsPrdOutDetlService.save(wmsPrdOutDetl);
		return renderResult(Global.TRUE, text("保存wms_prd_out_detl成功！"));
	}
	
	/**
	 * 删除wms_prd_out_detl
	 */
	@RequiresPermissions("twms:wmsPrdOutDetl:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(WmsPrdOutDetl wmsPrdOutDetl) {
		wmsPrdOutDetlService.delete(wmsPrdOutDetl);
		return renderResult(Global.TRUE, text("删除wms_prd_out_detl成功！"));
	}
	
}