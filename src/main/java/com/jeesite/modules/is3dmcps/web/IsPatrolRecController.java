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
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.is3dmcps.entity.IsPatrol;
import com.jeesite.modules.is3dmcps.entity.IsPatrolOperate;
import com.jeesite.modules.is3dmcps.entity.IsPatrolRec;
import com.jeesite.modules.is3dmcps.service.IsPatrolRecService;
import com.jeesite.modules.is3dmcps.service.IsPatrolService;

/**
 * 巡检记录Controller
 * @author xx
 * @version 2019-03-07
 */
@Controller
@RequestMapping(value = "${adminPath}/is3dmcps/isPatrolRec")
public class IsPatrolRecController extends BaseController {

	@Autowired
	private IsPatrolRecService isPatrolRecService;
	@Autowired
	private IsPatrolService isPatrolService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public IsPatrolRec get(String id, boolean isNewRecord) {
		return isPatrolRecService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("is3dmcps:isPatrolRec:view")
	@RequestMapping(value = {"list", ""})
	public String list(IsPatrolRec isPatrolRec, Model model) {
		model.addAttribute("isPatrolRec", isPatrolRec);
		return "modules/is3dmcps/isPatrolRecList";
	}
	
	/**
	 * 巡检操作
	 */
	@RequiresPermissions("is3dmcps:isPatrolRec:edit")
	@RequestMapping(value = "operateList")
	public String operaterList(IsPatrolOperate isPatrolOperate, Model model) {
		isPatrolOperate=new IsPatrolOperate();
		List<IsPatrolRec> isPatrolRecList= new ArrayList<IsPatrolRec>();
		List<IsPatrol> isPatrolLst = isPatrolService.findList(new IsPatrol());
		for(int i=0;i<isPatrolLst.size();i++) {
			IsPatrolRec isPatrolRec =new IsPatrolRec();
			IsPatrol isPatrol =isPatrolLst.get(i);
			isPatrolRec.setPatrolId(isPatrol.getId());
			isPatrolRec.setPatrolName(isPatrol.getName());
			isPatrolRecList.add(isPatrolRec);
		}
		isPatrolOperate.setIsPatrolRecList(isPatrolRecList);
		model.addAttribute("isPatrolOperate", isPatrolOperate);
		return "modules/is3dmcps/isPatrolOperateList";
	}
	/**
	 * 巡检操作保存
	 */
	@RequiresPermissions("is3dmcps:isPatrolRec:edit")
	@RequestMapping(value = "operateSave")
	@ResponseBody
	public String operaterSave(IsPatrolOperate isPatrolOperate, Model model) {
		List<IsPatrolRec> isPatrolRecList= isPatrolOperate.getIsPatrolRecList();
		for(int i=0;i<isPatrolRecList.size();i++) {
			IsPatrolRec isPatrolRec =isPatrolRecList.get(i);
			isPatrolRec.setOperator(isPatrolOperate.getOperator());
			isPatrolRec.setPatrolTime(isPatrolOperate.getPatrolTime());
			isPatrolRec.setIsNewRecord(true);
			if(StringUtils.isEmpty(isPatrolRec.getPatrolName())) {
				isPatrolRec.setPatrolName(isPatrolService.get(isPatrolRec.getPatrolId()).getName());
			}
			isPatrolRecService.save(isPatrolRec);
		}
		return renderResult(Global.TRUE, text("保存巡检记录成功！"));
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("is3dmcps:isPatrolRec:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<IsPatrolRec> listData(IsPatrolRec isPatrolRec, HttpServletRequest request, HttpServletResponse response) {
		Page<IsPatrolRec> page = isPatrolRecService.findPage(new Page<IsPatrolRec>(request, response), isPatrolRec); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("is3dmcps:isPatrolRec:view")
	@RequestMapping(value = "form")
	public String form(IsPatrolRec isPatrolRec, Model model) {
		model.addAttribute("isPatrolRec", isPatrolRec);
		//巡检点选择
		IsPatrol isPatrol = new IsPatrol();
		isPatrol.setStatus("0");
		List<IsPatrol> isPatrolList =isPatrolService.findList(isPatrol);
		model.addAttribute("isPatrolList", isPatrolList);
		return "modules/is3dmcps/isPatrolRecForm";
	}

	/**
	 * 保存巡检记录
	 */
	@RequiresPermissions("is3dmcps:isPatrolRec:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated IsPatrolRec isPatrolRec) {
		if(StringUtils.isEmpty(isPatrolRec.getPatrolName())) {
			isPatrolRec.setPatrolName(isPatrolService.get(isPatrolRec.getPatrolId()).getName());
		}
		isPatrolRecService.save(isPatrolRec);
		return renderResult(Global.TRUE, text("保存巡检记录成功！"));
	}
	
	/**
	 * 删除巡检记录
	 */
	@RequiresPermissions("is3dmcps:isPatrolRec:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(IsPatrolRec isPatrolRec) {
		isPatrolRecService.delete(isPatrolRec);
		return renderResult(Global.TRUE, text("删除巡检记录成功！"));
	}
	
}