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
import com.jeesite.modules.is3dmcps.entity.IsDevice;
import com.jeesite.modules.is3dmcps.entity.IsDeviceCode;
import com.jeesite.modules.is3dmcps.service.IsDeviceCodeService;
import com.jeesite.modules.is3dmcps.service.IsDeviceService;

/**
 * 备件Controller
 * @author xx
 * @version 2019-03-07
 */
@Controller
@RequestMapping(value = "${adminPath}/is3dmcps/isAttachment")
public class IsAttachmentController extends BaseController {

	@Autowired
	private IsDeviceService isDeviceService;
	@Autowired
	private IsDeviceCodeService isDeviceCodeService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public IsDevice get(String id, boolean isNewRecord) {
		return isDeviceService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("is3dmcps:isDevice:view")
	@RequestMapping(value = {"list", ""})
	public String list(IsDevice isDevice, Model model) {
		model.addAttribute("isDevice", isDevice);
		return "modules/is3dmcps/isAttachmentList";
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("is3dmcps:isDevice:view")
	@RequestMapping(value = "index")
	public String index(IsDevice isDevice, Model model) {
		model.addAttribute("isDevice", isDevice);
		return "modules/is3dmcps/isAttachmentIndex";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("is3dmcps:isDevice:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<IsDevice> listData(IsDevice isDevice, HttpServletRequest request, HttpServletResponse response) {
		isDevice.setStatus("0");
		Page<IsDevice> page = isDeviceService.findPage(new Page<IsDevice>(request, response), isDevice); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("is3dmcps:isDevice:view")
	@RequestMapping(value = "form")
	public String form(IsDevice isDevice, Model model) {
		model.addAttribute("isDevice", isDevice);
		return "modules/is3dmcps/isAttachmentForm";
	}

	/**
	 * 保存设备
	 */
	@RequiresPermissions("is3dmcps:isDevice:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated IsDevice isDevice) {
		isDeviceService.save(isDevice);
		return renderResult(Global.TRUE, text("保存设备成功！"));
	}
	
	/**
	 * 报修设备
	 */
	@RequiresPermissions("is3dmcps:isDevice:edit")
	@RequestMapping(value = "faults")
	@ResponseBody
	public String faults(IsDevice isDevice) {
		isDevice.setStatus("4");
		isDeviceService.updateStatus(isDevice);
		return renderResult(Global.TRUE, text("报修设备成功"));
	}
	
	/**
	 * 启用设备
	 */
	@RequiresPermissions("is3dmcps:isDevice:edit")
	@RequestMapping(value = "scrap")
	@ResponseBody
	public String scrap(IsDevice isDevice) {
		isDevice.setStatus("9");
		isDeviceService.updateStatus(isDevice);
		return renderResult(Global.TRUE, text("报废设备成功"));
	}
	
	/**
	 * 删除设备
	 */
	@RequiresPermissions("is3dmcps:isDevice:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(IsDevice isDevice) {
		isDeviceService.delete(isDevice);
		return renderResult(Global.TRUE, text("删除设备成功！"));
	}
	
	/**
	 * 获取树结构数据
	 * @param excludeCode 排除的Code
	 * @param isShowCode 是否显示编码（true or 1：显示在左侧；2：显示在右侧；false or null：不显示）
	 * @return
	 */
	@RequiresPermissions("is3dmcps:isDevice:view")
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
			IsDevice isDevice =new IsDevice();
			isDevice.setDeviceCodeId(c.getId());
			List<IsDevice> list = isDeviceService.findList(isDevice);
			if(list.size()==0) {
				continue;
			}
			for (int j=0; j<list.size(); j++){
				IsDevice e = list.get(j);
				// 过滤非正常的数据
				if (!"0".equals(e.getStatus())){
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
				map.put("name", e.getDeviceCodeName()+e.getDeviceNo());
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