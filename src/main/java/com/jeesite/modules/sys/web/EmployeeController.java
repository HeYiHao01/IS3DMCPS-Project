/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.sys.web;

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
import com.jeesite.modules.sys.entity.Employee;
import com.jeesite.modules.sys.service.EmployeeService;

/**
 * 员工表Controller
 * @author hy
 * @version 2019-09-25
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/employee")
public class EmployeeController extends BaseController {

	@Autowired
	private EmployeeService employeeService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public Employee get(String empCode, boolean isNewRecord) {
		return employeeService.get(empCode, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("sys:employee:view")
	@RequestMapping(value = {"list", ""})
	public String list(Employee employee, Model model) {
		model.addAttribute("employee", employee);
		return "modules/sys/employeeList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("sys:employee:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<Employee> listData(Employee employee, HttpServletRequest request, HttpServletResponse response) {
		Page<Employee> page = employeeService.findPage(new Page<Employee>(request, response), employee); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("sys:employee:view")
	@RequestMapping(value = "form")
	public String form(Employee employee, Model model) {
		model.addAttribute("employee", employee);
		return "modules/sys/employeeForm";
	}

	/**
	 * 保存员工表
	 */
	@RequiresPermissions("sys:employee:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated Employee employee) {
		employeeService.save(employee);
		return renderResult(Global.TRUE, text("保存员工表成功！"));
	}
	
	/**
	 * 删除员工表
	 */
	@RequiresPermissions("sys:employee:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(Employee employee) {
		employeeService.delete(employee);
		return renderResult(Global.TRUE, text("删除员工表成功！"));
	}
	
}