/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.is3dmcps.web;

import java.util.List;
import java.util.Map;

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
import com.jeesite.common.collect.ListUtils;
import com.jeesite.common.collect.MapUtils;
import com.jeesite.common.lang.StringUtils;
import com.jeesite.modules.sys.utils.UserUtils;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.is3dmcps.entity.IsDeviceCode;
import com.jeesite.modules.is3dmcps.service.IsDeviceCodeService;

/**
 * 设备代码Controller
 * @author xx
 * @version 2019-03-03
 */
@Controller
@RequestMapping(value = "${adminPath}/is3dmcps/isDeviceCode")
public class IsDeviceCodeController extends BaseController {

	@Autowired
	private IsDeviceCodeService isDeviceCodeService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public IsDeviceCode get(String id, boolean isNewRecord) {
		return isDeviceCodeService.get(id, isNewRecord);
	}
	
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("is3dmcps:isDeviceCode:view")
	@RequestMapping(value = {"list", ""})
	public String list(IsDeviceCode isDeviceCode, Model model) {
		model.addAttribute("isDeviceCode", isDeviceCode);
		return "modules/is3dmcps/isDeviceCodeList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("is3dmcps:isDeviceCode:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public List<IsDeviceCode> listData(IsDeviceCode isDeviceCode) {
		if (StringUtils.isBlank(isDeviceCode.getParentCode())) {
			isDeviceCode.setParentCode(IsDeviceCode.ROOT_CODE);
		}
		if (StringUtils.isNotBlank(isDeviceCode.getCode())){
			isDeviceCode.setParentCode(null);
		}
		if (StringUtils.isNotBlank(isDeviceCode.getName())){
			isDeviceCode.setParentCode(null);
		}
		if (StringUtils.isNotBlank(isDeviceCode.getType())){
			isDeviceCode.setParentCode(null);
		}
		if (StringUtils.isNotBlank(isDeviceCode.getStatus())){
			isDeviceCode.setParentCode(null);
		}
		List<IsDeviceCode> list = isDeviceCodeService.findList(isDeviceCode);
		return list;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("is3dmcps:isDeviceCode:view")
	@RequestMapping(value = "form")
	public String form(IsDeviceCode isDeviceCode, Model model) {
		// 创建并初始化下一个节点信息
		isDeviceCode = createNextNode(isDeviceCode);
		model.addAttribute("isDeviceCode", isDeviceCode);
		return "modules/is3dmcps/isDeviceCodeForm";
	}
	
	/**
	 * 创建并初始化下一个节点信息，如：排序号、默认值
	 */
	@RequiresPermissions("is3dmcps:isDeviceCode:edit")
	@RequestMapping(value = "createNextNode")
	@ResponseBody
	public IsDeviceCode createNextNode(IsDeviceCode isDeviceCode) {
		if (StringUtils.isNotBlank(isDeviceCode.getParentCode())){
			isDeviceCode.setParent(isDeviceCodeService.get(isDeviceCode.getParentCode()));
		}
		if (isDeviceCode.getIsNewRecord()) {
			IsDeviceCode where = new IsDeviceCode();
			where.setParentCode(isDeviceCode.getParentCode());
			IsDeviceCode last = isDeviceCodeService.getLastByParentCode(where);
			// 获取到下级最后一个节点
			if (last != null){
				isDeviceCode.setTreeSort(last.getTreeSort() + 30);
			}
		}
		// 以下设置表单默认数据
		if (isDeviceCode.getTreeSort() == null){
			isDeviceCode.setTreeSort(IsDeviceCode.DEFAULT_TREE_SORT);
		}
		return isDeviceCode;
	}

	/**
	 * 保存设备代码
	 */
	@RequiresPermissions("is3dmcps:isDeviceCode:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated IsDeviceCode isDeviceCode) {
		if(StringUtils.isEmpty(isDeviceCode.getParentCode())) {
			isDeviceCode.setType("0");
		}else {
			isDeviceCode.setType("1");
		}
		isDeviceCodeService.save(isDeviceCode);
		return renderResult(Global.TRUE, text("保存设备代码成功！"));
	}
	
	/**
	 * 停用设备代码
	 */
	@RequiresPermissions("is3dmcps:isDeviceCode:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(IsDeviceCode isDeviceCode) {
		IsDeviceCode where = new IsDeviceCode();
		where.setStatus(IsDeviceCode.STATUS_NORMAL);
		where.setParentCodes("," + isDeviceCode.getId() + ",");
		long count = isDeviceCodeService.findCount(where);
		if (count > 0) {
			return renderResult(Global.FALSE, text("该设备代码包含未停用的子设备代码！"));
		}
		isDeviceCode.setStatus(IsDeviceCode.STATUS_DISABLE);
		isDeviceCodeService.updateStatus(isDeviceCode);
		return renderResult(Global.TRUE, text("停用设备代码成功"));
	}
	
	/**
	 * 启用设备代码
	 */
	@RequiresPermissions("is3dmcps:isDeviceCode:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(IsDeviceCode isDeviceCode) {
		isDeviceCode.setStatus(IsDeviceCode.STATUS_NORMAL);
		isDeviceCodeService.updateStatus(isDeviceCode);
		return renderResult(Global.TRUE, text("启用设备代码成功"));
	}
	
	/**
	 * 删除设备代码
	 */
	@RequiresPermissions("is3dmcps:isDeviceCode:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(IsDeviceCode isDeviceCode) {
		isDeviceCodeService.delete(isDeviceCode);
		return renderResult(Global.TRUE, text("删除设备代码成功！"));
	}
	
	/**
	 * 获取树结构数据
	 * @param excludeCode 排除的Code
	 * @param isShowCode 是否显示编码（true or 1：显示在左侧；2：显示在右侧；false or null：不显示）
	 * @return
	 */
	@RequiresPermissions("is3dmcps:isDeviceCode:view")
	@RequestMapping(value = "deviceTreeData")
	@ResponseBody
	public List<Map<String, Object>> deviceTreeData(String excludeCode, String isShowCode) {
		List<Map<String, Object>> mapList = ListUtils.newArrayList();
		IsDeviceCode isDeviceCode=new IsDeviceCode();
		isDeviceCode.setType("0");
		List<IsDeviceCode> list = isDeviceCodeService.findList(isDeviceCode);
		for (int i=0; i<list.size(); i++){
			IsDeviceCode e = list.get(i);
			// 过滤非正常的数据
			if (!IsDeviceCode.STATUS_NORMAL.equals(e.getStatus())){
				continue;
			}
			// 过滤被排除的编码（包括所有子级）
			if (StringUtils.isNotBlank(excludeCode)){
				if (e.getId().equals(excludeCode)){
					continue;
				}
				if (e.getParentCodes().contains("," + excludeCode + ",")){
					continue;
				}
			}
			Map<String, Object> map = MapUtils.newHashMap();
			map.put("id", e.getId());
			map.put("pId", e.getParentCode());
			map.put("name", StringUtils.getTreeNodeName(isShowCode, e.getCode(), e.getName()));
			mapList.add(map);
		}
		return mapList;
	}

	/**
	 * 获取树结构数据
	 * @param excludeCode 排除的Code
	 * @param isShowCode 是否显示编码（true or 1：显示在左侧；2：显示在右侧；false or null：不显示）
	 * @return
	 */
	@RequiresPermissions("is3dmcps:isDeviceCode:view")
	@RequestMapping(value = "partTreeData")
	@ResponseBody
	public List<Map<String, Object>> PartTreeData(String excludeCode, String isShowCode) {
		List<Map<String, Object>> mapList = ListUtils.newArrayList();
		List<IsDeviceCode> list = isDeviceCodeService.findList(new IsDeviceCode());
		for (int i=0; i<list.size(); i++){
			IsDeviceCode e = list.get(i);
			// 过滤非正常的数据
			if (!IsDeviceCode.STATUS_NORMAL.equals(e.getStatus())){
				continue;
			}
			// 过滤被排除的编码（包括所有子级）
			if (StringUtils.isNotBlank(excludeCode)){
				if (e.getId().equals(excludeCode)){
					continue;
				}
				if (e.getParentCodes().contains("," + excludeCode + ",")){
					continue;
				}
			}
			//排除没有部件的设备
			if("0".equals(e.getType())){
				boolean ishaspart=false;  
				for (int j=0; j<list.size(); j++){
					if(list.get(j).getParentCode().equals(e.getId())){
						ishaspart=true;
						break;
					}
				}
				if(!ishaspart) {
					continue;
				}
			}
			Map<String, Object> map = MapUtils.newHashMap();
			map.put("id", e.getId());
			map.put("pId", e.getParentCode());
			map.put("name", StringUtils.getTreeNodeName(isShowCode, e.getCode(), e.getName()));
			mapList.add(map);
		}
		return mapList;
	}

	/**
	 * 获取树结构数据
	 * @param excludeCode 排除的Code
	 * @param isShowCode 是否显示编码（true or 1：显示在左侧；2：显示在右侧；false or null：不显示）
	 * @return
	 */
	@RequiresPermissions("is3dmcps:isDeviceCode:view")
	@RequestMapping(value = "treeData")
	@ResponseBody
	public List<Map<String, Object>> treeData(String excludeCode, String isShowCode) {
		List<Map<String, Object>> mapList = ListUtils.newArrayList();
		List<IsDeviceCode> list = isDeviceCodeService.findList(new IsDeviceCode());
		for (int i=0; i<list.size(); i++){
			IsDeviceCode e = list.get(i);
			// 过滤非正常的数据
			if (!IsDeviceCode.STATUS_NORMAL.equals(e.getStatus())){
				continue;
			}
			// 过滤被排除的编码（包括所有子级）
			if (StringUtils.isNotBlank(excludeCode)){
				if (e.getId().equals(excludeCode)){
					continue;
				}
				if (e.getParentCodes().contains("," + excludeCode + ",")){
					continue;
				}
			}
			Map<String, Object> map = MapUtils.newHashMap();
			map.put("id", e.getId());
			map.put("pId", e.getParentCode());
			map.put("name", StringUtils.getTreeNodeName(isShowCode, e.getCode(), e.getName()));
			mapList.add(map);
		}
		return mapList;
	}

	/**
	 * 修复表结构相关数据
	 */
	@RequiresPermissions("is3dmcps:isDeviceCode:edit")
	@RequestMapping(value = "fixTreeData")
	@ResponseBody
	public String fixTreeData(IsDeviceCode isDeviceCode){
		if (!UserUtils.getUser().isAdmin()){
			return renderResult(Global.FALSE, "操作失败，只有管理员才能进行修复！");
		}
		isDeviceCodeService.fixTreeData();
		return renderResult(Global.TRUE, "数据修复成功");
	}
	
}