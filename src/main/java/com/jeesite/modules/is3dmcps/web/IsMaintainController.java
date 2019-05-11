/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.is3dmcps.web;

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
import com.jeesite.common.lang.StringUtils;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.is3dmcps.entity.IsDeviceCode;
import com.jeesite.modules.is3dmcps.entity.IsKnowledge;
import com.jeesite.modules.is3dmcps.entity.IsMaintain;
import com.jeesite.modules.is3dmcps.service.IsDeviceCodeService;
import com.jeesite.modules.is3dmcps.service.IsKnowledgeService;
import com.jeesite.modules.is3dmcps.service.IsMaintainService;

/**
 * 保养点Controller
 * @author xx
 * @version 2019-03-06
 */
@Controller
@RequestMapping(value = "${adminPath}/is3dmcps/isMaintain")
public class IsMaintainController extends BaseController {

	@Autowired
	private IsMaintainService isMaintainService;
	@Autowired
	private IsKnowledgeService isKnowledgeService;
	@Autowired
	private IsDeviceCodeService isDeviceCodeService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public IsMaintain get(String id, boolean isNewRecord) {
		return isMaintainService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("is3dmcps:isMaintain:view")
	@RequestMapping(value = {"list", ""})
	public String list(IsMaintain isMaintain, Model model) {
		model.addAttribute("isMaintain", isMaintain);
		return "modules/is3dmcps/isMaintainList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("is3dmcps:isMaintain:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<IsMaintain> listData(IsMaintain isMaintain, HttpServletRequest request, HttpServletResponse response) {
		Page<IsMaintain> page = isMaintainService.findPage(new Page<IsMaintain>(request, response), isMaintain); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("is3dmcps:isMaintain:view")
	@RequestMapping(value = "form")
	public String form(IsMaintain isMaintain, Model model) {
		model.addAttribute("isMaintain", isMaintain);
		//知识选择
		IsKnowledge isKnowledge = new IsKnowledge();
		isKnowledge.setType("04");
		List<IsKnowledge> isKnowledgeList =isKnowledgeService.findList(isKnowledge);
		model.addAttribute("isKnowledgeList", isKnowledgeList);
		return "modules/is3dmcps/isMaintainForm";
	}

	/**
	 * 保存保养点
	 */
	@RequiresPermissions("is3dmcps:isMaintain:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated IsMaintain isMaintain) {
		isMaintainService.save(isMaintain);
		return renderResult(Global.TRUE, text("保存保养点成功！"));
	}
	
	/**
	 * 删除保养点
	 */
	@RequiresPermissions("is3dmcps:isMaintain:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(IsMaintain isMaintain) {
		isMaintainService.delete(isMaintain);
		return renderResult(Global.TRUE, text("删除保养点成功！"));
	}
	/**
	 * 获取树结构数据
	 * @param excludeCode 排除的Code
	 * @param isShowCode 是否显示编码（true or 1：显示在左侧；2：显示在右侧；false or null：不显示）
	 * @return
	 */
	@RequiresPermissions("is3dmcps:isMaintain:view")
	@RequestMapping(value = "treeData")
	@ResponseBody
	public List<Map<String, Object>> treeData(String excludeCode, String isShowCode) {
		List<Map<String, Object>> mapList = ListUtils.newArrayList();
		List<IsDeviceCode> isDeviceCodelist =isDeviceCodeService.findList(new IsDeviceCode());
		for (int i=0; i<isDeviceCodelist.size(); i++){
			IsDeviceCode c =isDeviceCodelist.get(i);
			// 过滤非正常的数据
			if (!IsDeviceCode.STATUS_NORMAL.equals(c.getStatus())){
				continue;
			}
			// 过滤被排除的编码（包括所有子级）
			if (StringUtils.isNotBlank(excludeCode)){
				if (c.getId().equals(excludeCode)){
					continue;
				}
			}
			IsMaintain isMaintain =new IsMaintain();
			isMaintain.setDeviceCodeId(c.getId());
			List<IsMaintain> list = isMaintainService.findList(isMaintain);
			if(list.size()==0) {
				continue;
			}
			for (int j=0; j<list.size(); j++){
				IsMaintain e = list.get(j);
				// 过滤非正常的数据
				if (!IsDeviceCode.STATUS_NORMAL.equals(e.getStatus())){
					continue;
				}
				// 过滤被排除的编码（包括所有子级）
				if (StringUtils.isNotBlank(excludeCode)){
					if (e.getId().equals(excludeCode)){
						continue;
					}
				}
				Map<String, Object> map = MapUtils.newHashMap();
				map.put("id", e.getId());
				map.put("pId", e.getDeviceCodeId());
				map.put("name", e.getName());
				mapList.add(map);
			}
			Map<String, Object> map = MapUtils.newHashMap();
			map.put("id", c.getId());
			map.put("pId", c.getParentCode());
			map.put("name", c.getName());
			mapList.add(map);			
		}	
		return mapList;
	}

}