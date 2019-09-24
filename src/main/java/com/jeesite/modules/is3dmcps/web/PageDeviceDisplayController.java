package com.jeesite.modules.is3dmcps.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.jeesite.common.collect.MapUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jeesite.common.collect.ListUtils;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.is3dmcps.entity.IsDevice;
import com.jeesite.modules.is3dmcps.entity.IsDeviceCode;
import com.jeesite.modules.is3dmcps.entity.IsSceneDeviceComponent;
import com.jeesite.modules.is3dmcps.service.IsDeviceCodeService;
import com.jeesite.modules.is3dmcps.service.IsDeviceService;
import com.jeesite.modules.is3dmcps.service.IsSceneDeviceComponentService;

/**
 * 
 * @author ZX
 *设备展示页面
 */
@CrossOrigin
@RestController
@RequestMapping(value = "static")
public class PageDeviceDisplayController extends BaseController{
	@Autowired
    private IsDeviceCodeService isDeviceCodeService;
	@Autowired
    private IsDeviceService isDeviceService;
	@Autowired
    private IsSceneDeviceComponentService isSceneDeviceComponentService;
	
    /**
     * 所有设备型号
     * @return
     * Json:
     * [{“deviceTypeID”:”xxx”,“deviceTypeName”:”xxx”,”deviceCategoryName”:”XXX”},{“deviceTypeID”:”xxx”,“deviceTypeName”:”xxx”,”d
     * eviceCategoryName”:”XXX”}]
     */
	@RequestMapping(value = {"allDeviceType", ""})
	public List<Map<String, Object>> allDeviceType() {
		List<Map<String, Object>> mapList = ListUtils.newArrayList();        
        String deviceTypeID;
        String deviceTypeName;
        String deviceCategoryName;
        for(IsDeviceCode isDeviceCode:isDeviceCodeService.getDeviceTypeDetail()){
        	Map<String, Object> map = MapUtils.newHashMap();
	        deviceTypeID=isDeviceCode.getId();
	        deviceTypeName=isDeviceCode.getModel();
	        deviceCategoryName=isDeviceCode.getName();
	        map.put("deviceTypeID",deviceTypeID);
	        map.put("deviceTypeName",deviceTypeName);
	        map.put("deviceCategoryName",deviceCategoryName);
	        mapList.add(map);
        }
		return mapList;
	}

    /**
     * 零件信息
     * Post:devicePartID or devicePartName
     * @return
     * Json:
     * {“devicePartID ”:”xxxx”,“devicePartName”:”ASSEM10”,”devicePartDetailedName”:”货物托盘检测传感器”,”devicePartCount”:2,”devicePartDescription”:”利用托盘传感器XXXXXXXXXXXXXXXXXXX”}
     */
    @RequestMapping(value = {"partInfo", ""})
    public List<Map<String, Object>> partInfo(HttpServletRequest request) {
        List<Map<String, Object>> mapList = ListUtils.newArrayList();        
        String devicePartID;
        String devicePartName;
        String devicePartDetailedName;
        int devicePartCount;
        String devicePartDescription;
        for(IsDevice isDevice:isDeviceService.getDeviceByDeviceNo(request.getParameter("devicePartName"))){
        	Map<String, Object> map = MapUtils.newHashMap();
        	devicePartID=isDevice.getDeviceCodeId();
            devicePartName=isDevice.getDeviceNo();
            devicePartDetailedName=isDevice.getDeviceCodeName();
            devicePartCount=isDeviceService.getPartCountByCodeId(devicePartID);
            devicePartDescription=isDeviceCodeService.getPartApplicationById(devicePartID).getApplication();
            map.put("devicePartID",devicePartID);
            map.put("devicePartName",devicePartName);
            map.put("devicePartDetailedName",devicePartDetailedName);
            map.put("devicePartCount",devicePartCount);
            map.put("devicePartDescription",devicePartDescription);
            mapList.add(map);
        }        
        return mapList;
    }

    /**
     * 组件的点击
     * Post:deviceTypeID or deviceTypeName
     * @return
     *Json:
     * [{“deviceComponentID”:”xxx”, “deviceComponentName”:”组件xxx”,”deviceComponentSceneParentPath”:”xxx/xxx/xxx/xxx” },
     * {“deviceComponentID”:”xxx”,“deviceComponentName”:”组件xxx”,”deviceComponentSceneParentPath”:”xxx/xxx/xxx/xxx” }]
     */
    @RequestMapping(value = {"componentClick", ""})
    public List<Map<String, Object>> componentClick(HttpServletRequest request) {
        List<Map<String, Object>> mapList = ListUtils.newArrayList();
        String deviceComponentID;
        String deviceComponentName;
        String deviceComponentSceneParentPath="";
        for(IsDeviceCode isDeviceCode:isDeviceCodeService.getPartByModel(request.getParameter("deviceTypeName"))){
        	Map<String, Object> map = MapUtils.newHashMap();            
            deviceComponentID=isDeviceCode.getId();
            deviceComponentName=isDeviceCode.getName();
            for(IsSceneDeviceComponent isSceneDeviceComponent:isSceneDeviceComponentService.getComponentByDeviceTypeId(deviceComponentID))
            	deviceComponentSceneParentPath= isSceneDeviceComponent.getSceneParentPath();
            map.put("deviceComponentID",deviceComponentID);
            map.put("deviceComponentName",deviceComponentName);
            map.put("deviceComponentSceneParentPath",deviceComponentSceneParentPath);
            mapList.add(map);
        }        
        return mapList;
    }

    /**
     *零件列表
     * @return
     * Json:
     * [{“devicePartID”:”xxx”,”devicePartName”:”XXXxx”,“deviceComponentID”:”xxx”,“deviceComponentName”:”组件xxx” },
     * {“devicePartID”:”xxx”,”devicePartName”:”XXXxx”, “deviceComponentID”:”xxx”, “deviceComponentName”:”组件xxx”]
     */
    @RequestMapping(value = {"partList", ""})
    public List<Map<String, Object>> partList(HttpServletRequest request) {
        List<Map<String, Object>> mapList = ListUtils.newArrayList();        
        String devicePartID;
        String devicePartName;
        String deviceComponentID;
        String deviceComponentName;
        for(IsDeviceCode isDeviceCode:isDeviceCodeService.getPartByModel(request.getParameter("deviceTypeName"))){
        	Map<String, Object> map = MapUtils.newHashMap();            
        	devicePartID=isDeviceCode.getId();
        	devicePartName=isDeviceCode.getName();
        	deviceComponentID=isDeviceCode.getParentCode();
        	System.err.println(deviceComponentID);
        	deviceComponentName = isDeviceCodeService.getPartApplicationById(deviceComponentID).getName();
            map.put("devicePartID",devicePartID);
            map.put("devicePartName",devicePartName);
            map.put("deviceComponentID",deviceComponentID);
            map.put("deviceComponentName", deviceComponentName);
            mapList.add(map);
        }   
        return mapList;
    }
}
