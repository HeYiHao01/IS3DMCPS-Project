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
import com.jeesite.modules.twms.entity.WmsGdxdOut;
import com.jeesite.modules.twms.service.WmsGdxdOutService;

/**
 * wms_gdxd_outController
 * @author xx
 * @version 2019-05-14
 */
@Controller
@RequestMapping(value = "${adminPath}/twms/wmsGdxdOut")
public class WmsGdxdOutController extends BaseController {

	@Autowired
	private WmsGdxdOutService wmsGdxdOutService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public WmsGdxdOut get(Integer id, boolean isNewRecord) {
		return wmsGdxdOutService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("twms:wmsGdxdOut:view")
	@RequestMapping(value = {"list", ""})
	public String list(WmsGdxdOut wmsGdxdOut, Model model) {
		model.addAttribute("wmsGdxdOut", wmsGdxdOut);
		return "modules/twms/wmsGdxdOutList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("twms:wmsGdxdOut:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<WmsGdxdOut> listData(WmsGdxdOut wmsGdxdOut, HttpServletRequest request, HttpServletResponse response) {
		Page<WmsGdxdOut> page = wmsGdxdOutService.findPage(new Page<WmsGdxdOut>(request, response), wmsGdxdOut); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("twms:wmsGdxdOut:view")
	@RequestMapping(value = "form")
	public String form(WmsGdxdOut wmsGdxdOut, Model model) {
		model.addAttribute("wmsGdxdOut", wmsGdxdOut);
		return "modules/twms/wmsGdxdOutForm";
	}

	/**
	 * 保存wms_gdxd_out
	 */
	@RequiresPermissions("twms:wmsGdxdOut:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated WmsGdxdOut wmsGdxdOut) {
		wmsGdxdOutService.save(wmsGdxdOut);
		return renderResult(Global.TRUE, text("保存wms_gdxd_out成功！"));
	}
	
	/**
	 * 删除wms_gdxd_out
	 */
	@RequiresPermissions("twms:wmsGdxdOut:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(WmsGdxdOut wmsGdxdOut) {
		wmsGdxdOutService.delete(wmsGdxdOut);
		return renderResult(Global.TRUE, text("删除wms_gdxd_out成功！"));
	}
}