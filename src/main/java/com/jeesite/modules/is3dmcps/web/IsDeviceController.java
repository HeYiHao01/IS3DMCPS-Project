/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.is3dmcps.web;

import java.util.Date;
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
import com.jeesite.modules.is3dmcps.entity.IsDeviceUse;
import com.jeesite.modules.is3dmcps.service.IsDeviceCodeService;
import com.jeesite.modules.is3dmcps.service.IsDeviceService;
import com.jeesite.modules.is3dmcps.service.IsDeviceUseService;
import com.jeesite.modules.sys.utils.UserUtils;

/**
 * 设备Controller
 * @author xx
 * @version 2019-03-07
 */
@Controller
@RequestMapping(value = "${adminPath}/is3dmcps/isDevice")
public class IsDeviceController extends BaseController {

	@Autowired
	private IsDeviceService isDeviceService;
	@Autowired
	private IsDeviceCodeService isDeviceCodeService;
	@Autowired
	private IsDeviceUseService isDeviceUseService;
	
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
		return "modules/is3dmcps/isDeviceList";
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("is3dmcps:isDevice:view")
	@RequestMapping(value = "index")
	public String index(IsDevice isDevice, Model model) {
		model.addAttribute("isDevice", isDevice);
		return "modules/is3dmcps/isDeviceIndex";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("is3dmcps:isDevice:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<IsDevice> listData(IsDevice isDevice, HttpServletRequest request, HttpServletResponse response) {
		isDevice.setType("0");
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
		return "modules/is3dmcps/isDeviceForm";
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("is3dmcps:isDevice:view")
	@RequestMapping(value = "newForm")
	public String newForm(IsDevice isDevice, Model model) {
		model.addAttribute("isDevice", isDevice);
		return "modules/is3dmcps/isDeviceNewForm";
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("is3dmcps:isDevice:view")
	@RequestMapping(value = "enableForm")
	public String enableForm(IsDevice isDevice, Model model) {
		model.addAttribute("isDevice", isDevice);
		return "modules/is3dmcps/isDeviceEnableForm";
	}

	/**
	 * 保存设备
	 */
	@RequiresPermissions("is3dmcps:isDevice:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated IsDevice isDevice) {
		isDevice.setType("0");
		if(isDevice.getIsNewRecord()) {
			isDevice.setDeviceStatus("0");
		}
		if("Car".equals(isDevice.getOpcType())) {
			//将OPCID写入is_car_status
		}
		isDeviceService.save(isDevice);
		return renderResult(Global.TRUE, text("保存设备成功！"));
	}
	
	/**
	 * 启用设备
	 */
	@RequiresPermissions("is3dmcps:isDevice:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(IsDevice isDevice) {
		IsDeviceUse isDeviceUse=new IsDeviceUse();
		isDeviceUse.setPartId(isDevice.getId());
		isDeviceUse.setPartName(isDevice.getDeviceCodeName()+isDevice.getDeviceNo());
		isDeviceUse.setDeviceId(isDevice.getDeviceId());
		isDeviceUse.setDeviceName(isDevice.getDeviceName());
		isDeviceUse.setType("2");	//启用
		isDeviceUse.setOperator(UserUtils.getUser().getUserName());
		isDeviceUse.setOperateTime(new Date());
		isDeviceUseService.save(isDeviceUse);

		isDevice.setDeviceStatus("1");
		isDeviceService.save(isDevice);
		return renderResult(Global.TRUE, text("启用设备成功"));
	}
	
	/**
	 * 停用设备
	 */
	@RequiresPermissions("is3dmcps:isDevice:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(IsDevice isDevice) {
		IsDeviceUse isDeviceUse=new IsDeviceUse();
		isDeviceUse.setPartId(isDevice.getId());
		isDeviceUse.setPartName(isDevice.getDeviceCodeName()+isDevice.getDeviceNo());
		isDeviceUse.setDeviceId(isDevice.getDeviceId());
		isDeviceUse.setDeviceName(isDevice.getDeviceName());
		isDeviceUse.setType("3");	//停用
		isDeviceUse.setOperator(UserUtils.getUser().getUserName());
		isDeviceUse.setOperateTime(new Date());
		isDeviceUseService.save(isDeviceUse);

		isDevice.setDeviceStatus("0");
		isDeviceService.save(isDevice);
		return renderResult(Global.TRUE, text("停用设备成功"));
	}
			
	/**
	 * 报修设备
	 */
	@RequiresPermissions("is3dmcps:isDevice:edit")
	@RequestMapping(value = "faults")
	@ResponseBody
	public String faults(IsDevice isDevice) {
		IsDeviceUse isDeviceUse=new IsDeviceUse();
		isDeviceUse.setPartId(isDevice.getId());
		isDeviceUse.setPartName(isDevice.getDeviceCodeName()+isDevice.getDeviceNo());
		isDeviceUse.setDeviceId(isDevice.getDeviceId());
		isDeviceUse.setDeviceName(isDevice.getDeviceName());
		isDeviceUse.setType("4");	
		isDeviceUse.setOperator(UserUtils.getUser().getUserName());
		isDeviceUse.setOperateTime(new Date());		
		isDeviceUseService.save(isDeviceUse);
		
		isDevice.setDeviceStatus("2");
		isDeviceService.save(isDevice);
		return renderResult(Global.TRUE, text("报修设备成功"));
	}
			
	/**
	 * 报修设备
	 */
	@RequiresPermissions("is3dmcps:isDevice:edit")
	@RequestMapping(value = "scrap")
	@ResponseBody
	public String scrap(IsDevice isDevice) {
		IsDeviceUse isDeviceUse=new IsDeviceUse();
		isDeviceUse.setPartId(isDevice.getId());
		isDeviceUse.setPartName(isDevice.getDeviceCodeName()+isDevice.getDeviceNo());
		isDeviceUse.setDeviceId(isDevice.getDeviceId());
		isDeviceUse.setDeviceName(isDevice.getDeviceName());
		isDeviceUse.setType("9");	
		isDeviceUse.setOperator(UserUtils.getUser().getUserName());
		isDeviceUse.setOperateTime(new Date());
		isDeviceUseService.save(isDeviceUse);
		
		isDevice.setDeviceStatus("9");
		isDeviceService.save(isDevice);
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
		IsDeviceCode isDeviceCode=new IsDeviceCode();
		isDeviceCode.setType("0");
		List<IsDeviceCode> isDeviceCodelist =isDeviceCodeService.findList(isDeviceCode);
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
				if ("9".equals(e.getStatus())){
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