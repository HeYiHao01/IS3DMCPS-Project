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
import com.jeesite.modules.is3dmcps.entity.SelfDefEmployee;
import com.jeesite.modules.is3dmcps.service.SelfDefEmployeeService;

/**
 * 员工表Controller
 * @author hy
 * @version 2019-12-25
 */
@Controller
@RequestMapping(value = "${adminPath}/is3dmcps/selfDefEmployee")
public class SelfDefEmployeeController extends BaseController {

	@Autowired
	private SelfDefEmployeeService selfDefEmployeeService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public SelfDefEmployee get(String empCode, boolean isNewRecord) {
		return selfDefEmployeeService.get(empCode, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("is3dmcps:selfDefEmployee:view")
	@RequestMapping(value = {"list", ""})
	public String list(SelfDefEmployee selfDefEmployee, Model model) {
		model.addAttribute("selfDefEmployee", selfDefEmployee);
		return "modules/is3dmcps/selfDefEmployeeList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("is3dmcps:selfDefEmployee:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<SelfDefEmployee> listData(SelfDefEmployee selfDefEmployee, HttpServletRequest request, HttpServletResponse response) {
		Page<SelfDefEmployee> page = selfDefEmployeeService.findPage(new Page<SelfDefEmployee>(request, response), selfDefEmployee); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("is3dmcps:selfDefEmployee:view")
	@RequestMapping(value = "form")
	public String form(SelfDefEmployee selfDefEmployee, Model model) {
		model.addAttribute("selfDefEmployee", selfDefEmployee);
		return "modules/is3dmcps/selfDefEmployeeForm";
	}

	/**
	 * 保存员工表
	 */
	@RequiresPermissions("is3dmcps:selfDefEmployee:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated SelfDefEmployee selfDefEmployee) {
		selfDefEmployeeService.save(selfDefEmployee);
		return renderResult(Global.TRUE, text("保存员工表成功！"));
	}
	
	/**
	 * 删除员工表
	 */
	@RequiresPermissions("is3dmcps:selfDefEmployee:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(SelfDefEmployee selfDefEmployee) {
		selfDefEmployeeService.delete(selfDefEmployee);
		return renderResult(Global.TRUE, text("删除员工表成功！"));
	}
	
}