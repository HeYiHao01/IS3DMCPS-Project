/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.is3dmcps.web;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.is3dmcps.entity.IsDevice;
import com.jeesite.modules.is3dmcps.entity.IsDeviceUse;
import com.jeesite.modules.is3dmcps.entity.IsFaults;
import com.jeesite.modules.is3dmcps.entity.IsRepairRec;
import com.jeesite.modules.is3dmcps.service.IsDeviceService;
import com.jeesite.modules.is3dmcps.service.IsDeviceUseService;
import com.jeesite.modules.is3dmcps.service.IsFaultsService;
import com.jeesite.modules.is3dmcps.service.IsRepairRecService;
import com.jeesite.modules.sys.utils.UserUtils;

/**
 * 维修记录Controller
 * @author xx
 * @version 2019-03-08
 */
@Controller
@RequestMapping(value = "${adminPath}/is3dmcps/isRepairRec")
public class IsRepairRecController extends BaseController {

	@Autowired
	private IsRepairRecService isRepairRecService;
	@Autowired
	private IsFaultsService isFaultsService;
	@Autowired
	private IsDeviceService isDeviceService;
	@Autowired
	private IsDeviceUseService isDeviceUseService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public IsRepairRec get(String id, boolean isNewRecord) {
		return isRepairRecService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("is3dmcps:isRepairRec:view")
	@RequestMapping(value = {"list", ""})
	public String list(IsRepairRec isRepairRec, Model model) {
		model.addAttribute("isRepairRec", isRepairRec);
		return "modules/is3dmcps/isRepairRecList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("is3dmcps:isRepairRec:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<IsRepairRec> listData(IsRepairRec isRepairRec, HttpServletRequest request, HttpServletResponse response) {
		Page<IsRepairRec> page = isRepairRecService.findPage(new Page<IsRepairRec>(request, response), isRepairRec); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("is3dmcps:isRepairRec:view")
	@RequestMapping(value = "form")
	public String form(IsRepairRec isRepairRec, Model model) {
		model.addAttribute("isRepairRec", isRepairRec);
		//故障选择
		IsFaults isFaults = new IsFaults();
		//isFaults.setStatus("0");
		List<IsFaults> isFaultsList =isFaultsService.findList(isFaults);
		model.addAttribute("isFaultsList", isFaultsList);
		return "modules/is3dmcps/isRepairRecForm";
	}

	/**
	 * 保存维修记录
	 */
	@RequiresPermissions("is3dmcps:isRepairRec:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated IsRepairRec isRepairRec) {
		if(StringUtils.isEmpty(isRepairRec.getFaultsName())) {
			isRepairRec.setFaultsName(isFaultsService.get(isRepairRec.getFaultsId()).getName());
		}
		if("0".equals(isRepairRec.getResults())) {
			IsFaults isFaults =isFaultsService.get(isRepairRec.getFaultsId());
			isFaults.setStatus("2");
			isFaultsService.save(isFaults);
		}
		if("2".equals(isRepairRec.getResults())) {
			IsFaults isFaults =isFaultsService.get(isRepairRec.getFaultsId());
			isFaults.setStatus("3");
			isFaultsService.save(isFaults);
			IsDevice isDevice =isDeviceService.get(isFaults.getDeviceId());
			isDevice.setDeviceStatus("1");
			isDeviceService.save(isDevice);
			isRepairRec.setStatus("2");
		}
		if("3".equals(isRepairRec.getResults())) {
			IsFaults isFaults =isFaultsService.get(isRepairRec.getFaultsId());
			isFaults.setStatus("3");
			isFaultsService.save(isFaults);
			IsDevice isDevice =isDeviceService.get(isFaults.getDeviceId());
			isDevice.setDeviceStatus("0");
			isDeviceService.save(isDevice);
			isRepairRec.setStatus("2");
		}
		if("4".equals(isRepairRec.getResults())) {
			IsFaults isFaults =isFaultsService.get(isRepairRec.getFaultsId());
			isFaults.setStatus("4");
			isFaultsService.save(isFaults);
			IsDevice isDevice =isDeviceService.get(isFaults.getDeviceId());
			isDevice.setDeviceStatus("9");
			isDeviceService.save(isDevice);
			//设备使用记录
			IsDeviceUse isDeviceUse=new IsDeviceUse();
			isDeviceUse.setPartId(isDevice.getId());
			isDeviceUse.setPartName(isDevice.getDeviceCodeName()+isDevice.getDeviceNo());
			isDeviceUse.setDeviceId(isDevice.getDeviceId());
			isDeviceUse.setDeviceName(isDevice.getDeviceName());
			isDeviceUse.setType("9");	
			isDeviceUse.setOperator(UserUtils.getUser().getUserName());
			isDeviceUse.setOperateTime(new Date());
			isDeviceUseService.save(isDeviceUse);
			isRepairRec.setStatus("1");
		}
		isRepairRecService.save(isRepairRec);
		return renderResult(Global.TRUE, text("保存维修记录成功！"));
	}
	
	/**
	 * 删除维修记录
	 */
	@RequiresPermissions("is3dmcps:isRepairRec:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(IsRepairRec isRepairRec) {
		isRepairRecService.delete(isRepairRec);
		return renderResult(Global.TRUE, text("删除维修记录成功！"));
	}
	
}