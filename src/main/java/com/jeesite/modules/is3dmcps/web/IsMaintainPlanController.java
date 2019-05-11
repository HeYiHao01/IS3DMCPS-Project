/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.is3dmcps.web;

import java.util.ArrayList;
import java.util.List;

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

import com.alibaba.druid.util.StringUtils;
import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.is3dmcps.entity.IsDevice;
import com.jeesite.modules.is3dmcps.entity.IsMaintainRec;
import com.jeesite.modules.is3dmcps.service.IsDeviceService;
import com.jeesite.modules.is3dmcps.service.IsMaintainRecService;
import com.jeesite.modules.is3dmcps.service.IsMaintainService;

/**
 * 保养记录Controller
 * @author xx
 * @version 2019-03-06
 */
@Controller
@RequestMapping(value = "${adminPath}/is3dmcps/isMaintainPlan")
public class IsMaintainPlanController extends BaseController {

	@Autowired
	private IsMaintainRecService isMaintainRecService;
	@Autowired
	private IsMaintainService isMaintainService;
	@Autowired
	private IsDeviceService isDeviceService;

	/**
	 * 获取数据
	 */
	@ModelAttribute
	public IsMaintainRec get(String id, boolean isNewRecord) {
		return isMaintainRecService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("is3dmcps:isMaintainRec:view")
	@RequestMapping(value = {"list", ""})
	public String list(IsMaintainRec isMaintainRec, Model model) {
		model.addAttribute("isMaintainRec", isMaintainRec);
		return "modules/is3dmcps/isMaintainPlanList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("is3dmcps:isMaintainRec:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<IsMaintainRec> listData(IsMaintainRec isMaintainRec, HttpServletRequest request, HttpServletResponse response) {
		isMaintainRec.setRecStatus("0");
		Page<IsMaintainRec> page = isMaintainRecService.findPage(new Page<IsMaintainRec>(request, response), isMaintainRec); 
		return page;
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("is3dmcps:isMaintainRec:view")
	@RequestMapping(value = "getDeviceNo")
	@ResponseBody
	public List<String> getDeviceNo(IsMaintainRec isMaintainRec, HttpServletRequest request, HttpServletResponse response) {
		List<String> deviceNoList=new ArrayList<String>();
		String deviceCodeId=request.getParameter("deviceCodeId"); 
		if(StringUtils.isEmpty(deviceCodeId)) {
			String maintainId=request.getParameter("maintainId"); 
			if(StringUtils.isEmpty(maintainId)) {
				return deviceNoList;
			}
			deviceCodeId = isMaintainService.get(maintainId).getDeviceCodeId();
			IsDevice isDevice =new IsDevice();
			isDevice.setDeviceCodeId(deviceCodeId);
			isDevice.setDeviceStatus("1");
			List<IsDevice> isDeviceList=isDeviceService.findList(isDevice);
			for(int i=0;i<isDeviceList.size();i++) {
				if(!StringUtils.isEmpty(isDeviceList.get(i).getDeviceNo())) {
					deviceNoList.add(isDeviceList.get(i).getDeviceNo());
				}
			}
		}
		return deviceNoList;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("is3dmcps:isMaintainRec:view")
	@RequestMapping(value = "form")
	public String form(IsMaintainRec isMaintainRec, Model model) {
		model.addAttribute("isMaintainRec", isMaintainRec);
		return "modules/is3dmcps/isMaintainPlanForm";
	}
	
	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("is3dmcps:isMaintainRec:view")
	@RequestMapping(value = "operateForm")
	public String operateForm(IsMaintainRec isMaintainRec, Model model) {
		model.addAttribute("isMaintainRec", isMaintainRec);
		return "modules/is3dmcps/isMaintainOperateForm";
	}

	/**
	 * 保存保养记录
	 */
	@RequiresPermissions("is3dmcps:isMaintainRec:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated IsMaintainRec isMaintainRec) {
		if(isMaintainRec.getIsNewRecord()) {
			isMaintainRec.setRecStatus("0");
		}
		if(!StringUtils.isEmpty(isMaintainRec.getMaintainPerson())) {
			isMaintainRec.setRecStatus("1");
		}
		if(StringUtils.isEmpty(isMaintainRec.getMaintainName())) {
			isMaintainRec.setMaintainName(isMaintainService.get(isMaintainRec.getMaintainId()).getName());
		}
		isMaintainRecService.save(isMaintainRec);
		return renderResult(Global.TRUE, text("保存保养计划成功！"));
	}
	
}