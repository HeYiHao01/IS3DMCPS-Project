/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.isopc.web;

import java.util.List;
import java.util.Map;

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

import com.jeesite.common.collect.ListUtils;
import com.jeesite.common.collect.MapUtils;
import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.isopc.entity.IsCarCount;
import com.jeesite.modules.isopc.service.IsCarCountService;

/**
 * 穿梭车运行统计Controller
 * @author xx
 * @version 2019-03-12
 */
@Controller
@RequestMapping(value = "${adminPath}/isopc/isCarCount")
public class IsCarCountController extends BaseController {

	@Autowired
	private IsCarCountService isCarCountService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public IsCarCount get(String id, boolean isNewRecord) {
		return isCarCountService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("isopc:isCarCount:view")
	@RequestMapping(value = {"list", ""})
	public String list(IsCarCount isCarCount, Model model) {
		model.addAttribute("isCarCount", isCarCount);
		return "modules/isopc/isCarCountList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("isopc:isCarCount:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<IsCarCount> listData(IsCarCount isCarCount, HttpServletRequest request, HttpServletResponse response) {
		Page<IsCarCount> page = isCarCountService.findPage(new Page<IsCarCount>(request, response), isCarCount); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("isopc:isCarCount:view")
	@RequestMapping(value = "form")
	public String form(IsCarCount isCarCount, Model model) {
		model.addAttribute("isCarCount", isCarCount);
		return "modules/isopc/isCarCountForm";
	}

	/**
	 * 保存穿梭车运行统计
	 */
	@RequiresPermissions("isopc:isCarCount:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated IsCarCount isCarCount) {
		isCarCountService.save(isCarCount);
		return renderResult(Global.TRUE, text("保存穿梭车运行统计成功！"));
	}
}