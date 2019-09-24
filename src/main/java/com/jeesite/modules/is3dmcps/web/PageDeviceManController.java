package com.jeesite.modules.is3dmcps.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jeesite.common.collect.MapUtils;
import com.jeesite.modules.is3dmcps.entity.Device;
import com.jeesite.modules.is3dmcps.entity.DeviceUse;
import com.jeesite.modules.is3dmcps.entity.IsDevice;
import com.jeesite.modules.is3dmcps.entity.IsDeviceCode;
import com.jeesite.modules.is3dmcps.entity.IsDeviceProperties;
import com.jeesite.modules.is3dmcps.entity.NewDevice;
import com.jeesite.modules.is3dmcps.entity.PartsConsumption;
import com.jeesite.modules.is3dmcps.entity.SpareParts;
import com.jeesite.modules.is3dmcps.service.IsDeviceCodeService;
import com.jeesite.modules.is3dmcps.service.IsDevicePropertiesService;
import com.jeesite.modules.is3dmcps.service.IsDeviceService;
import com.jeesite.modules.is3dmcps.service.IsDeviceUseService;
import com.jeesite.modules.is3dmcps.service.IsRepairRecService;
import com.jeesite.modules.isopc.entity.IsCarCount;
import com.jeesite.modules.isopc.service.IsCarCountService;
import com.jeesite.modules.isopc.service.IsDeviceRecService;
import com.jeesite.modules.twms.service.TwmsLocService;
import com.jeesite.modules.twms.service.TwmsPltitemService;
import com.jeesite.utils.CompareDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jeesite.common.collect.ListUtils;
import com.jeesite.common.web.BaseController;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author ZX
 *设备管理主界面
 */
@CrossOrigin
@RestController
@RequestMapping(value = "static")
public class PageDeviceManController extends BaseController{
    @Autowired
    private IsDeviceService isDeviceService;
    @Autowired
    private IsDeviceCodeService isDeviceCodeService;
    @Autowired
    private IsCarCountService isCarCountService;
    @Autowired
    private IsDeviceRecService isDeviceRecService;
    @Autowired
    private IsDeviceUseService isDeviceUseService;
    @Autowired
    private IsRepairRecService isRepairRecService;
    @Autowired
    private IsDevicePropertiesService isDevicePropertiesService;
    @Autowired
    private TwmsLocService twmsLocService;
    @Autowired
    private TwmsPltitemService twmsPltitemService;

    /**
     * 场景设备都能点
     * @return
     * Json:
     * [{“deviceSceneParentPath”:”xxx/xxx/xxx/xxx”,”deviceID”:”xxx”,”deviceName”:”xxx”},
     * {“deviceSceneParentPath”:”xxx/xxx/xxx/xxx”,”deviceID”:”xxx”,”deviceName”:”xxx”}]
     */
    @RequestMapping(value = {"sceneDevice", ""})
    public List<Map<String, Object>> sceneDevice() {
        List<Map<String, Object>> mapList = ListUtils.newArrayList();
        String deviceSceneParentPath;
        String deviceID;
        String deviceName;
        deviceSceneParentPath="123/123/123/123";
        deviceID="12323123";
        deviceName="穿梭车";
        Map<String, Object> map = MapUtils.newHashMap();
        map.put("deviceSceneParentPath",deviceSceneParentPath);
        map.put("deviceID",deviceID);
        map.put("deviceName",deviceName);
        mapList.add(map);
        String deviceSceneParentPath1;
        String deviceID1;
        String deviceName1;
        deviceSceneParentPath1="123/123/123/123";
        deviceID1="12323123";
        deviceName1="穿梭车";
        Map<String, Object> map1 = MapUtils.newHashMap();
        map1.put("deviceSceneParentPath",deviceSceneParentPath1);
        map1.put("deviceID",deviceID1);
        map1.put("deviceName",deviceName1);
        mapList.add(map1);
        return mapList;
    }

    /**
     * 所有设备列表
     * @return
     * Json：
     * {[”deviceID”:”xxx”,”deviceName”:”Car01”,”deviceCategoryName”:”智能双向小车”,”deviceTypeName”:”SFTA003” ,”runState”:”Run”],
     *  [”deviceID”:”xxx”,”deviceName”:”XXX”,”deviceCategoryName”:”XXX” ,”deviceTypeName”:”xxxx” ,”runState”:”Sleep”],
     *  [”deviceID”:”xxx”,”deviceName”:”XXX”,”deviceCategoryName”:”XXX” ,”deviceTypeName”:”xxxx” ,”runState”:”Fault”]}
     *  
     *  删去runstate，增加固定资产编号assetsNo
     */
    @RequestMapping(value = {"deviceList", ""})
    public List<Map<String, Object>> deviceList() {
        List<Map<String, Object>> mapList = ListUtils.newArrayList();
        String deviceID;
        String deviceName;
        String deviceCategoryName;
        String deviceTypeName;
        //String runState;
        List<IsDevice> isDevices=isDeviceService.findList(new IsDevice());
        for(IsDevice isDevice:isDevices){
            deviceID= isDevice.getId();
            deviceName=isDevice.getDeviceNo();
            deviceCategoryName=isDevice.getDeviceCodeName();
            String device_code_id=isDevice.getDeviceCodeId();
            String assetsNo = "";
            IsDeviceCode isDeviceCode=isDeviceCodeService.get(device_code_id);
            if(isDeviceCode!=null){
                deviceTypeName=isDeviceCode.getModel();
            }else{
                deviceTypeName="";
            }
            if (isDevice.getAssetsNo() != null) {
            	assetsNo = isDevice.getAssetsNo();
			}
            /*runState=isDevice.getDeviceStatus();
            System.out.println(runState);
            switch (runState){
                case "0":
                    runState="Sleep";
                    break;
                case "1":
                    runState="Run";
                    break;
                case "2":
                    runState="Fault";
                    break;
                case "9":
                    runState="abandon";
                    break;
            }*/

            Map<String, Object> map = MapUtils.newHashMap();
            map.put("deviceID",deviceID);
            map.put("deviceName",deviceName);
            map.put("deviceCategoryName",deviceCategoryName);
            map.put("deviceTypeName",deviceTypeName);
            map.put("assetsNo",assetsNo);
            //map.put("runState",runState);
            mapList.add(map);
        }

        return mapList;
    }

    /**
     * 设备点击
     * Post: deviceID or deviceName
     * @return
     * Json:
     * {”deviceName”:”xxx”,” deviceCategoryName”:”XXXXX” ,”deviceTypeName”:”xxxx”,
     * “runState”:”正常”,”FaultCount”:46,”runTimeCount”:10.5,”useTime”:256}
     */
    @RequestMapping(value = {"deviceClick", ""})
    public List<Map<String, Object>> deviceClick(HttpServletRequest request) {
        //String deviceID=request.getParameter("deviceID");
    	List<Map<String, Object>> mapList = ListUtils.newArrayList();
    	String deviceName=request.getParameter("deviceName");
        for(IsDevice isDevice:isDeviceService.getDeviceByDeviceNo(deviceName)){
			String deviceID;
			String deviceCategoryName;
			String deviceTypeName;
			String runState;
			int FaultCount;
			double runTimeCount;
			double useTime;
			deviceID = isDevice.getId();
			deviceCategoryName = isDevice.getDeviceCodeName();
			String device_code_id = isDevice.getDeviceCodeId();
			IsDeviceCode isDeviceCode = isDeviceCodeService.get(device_code_id);
			if (isDeviceCode != null) {
				deviceTypeName = isDeviceCode.getModel();
			} else {
				deviceTypeName = "";
			}
			runState = isDevice.getDeviceStatus();
			System.out.println(runState);
			switch (runState) {
			case "0":
				runState = "Sleep";
				break;
			case "1":
				runState = "Run";
				break;
			case "2":
				runState = "Fault";
				break;
			case "9":
				runState = "abandon";
				break;
			}
			IsCarCount isCarCount = null;
			useTime = isDeviceRecService.getTimeById(deviceID);
			for (IsCarCount isCarCount2:isCarCountService.getAllByDeviceId(deviceID)) {
				isCarCount = isCarCount2;
			}
			if (isCarCount != null) {					
				if(isCarCount.getErrCount() != null)
					FaultCount = isCarCount.getErrCount();
				else
					FaultCount = 0;
				runTimeCount = isCarCountService.get(isCarCount).getWorkTime();
				Map<String, Object> map = MapUtils.newHashMap();
				map.put("deviceName", deviceName);
				map.put("deviceCategoryName", deviceCategoryName);
				map.put("deviceTypeName", deviceTypeName);
				map.put("runState", runState);
				map.put("FaultCount", FaultCount);
				map.put("runTimeCount", runTimeCount);
				map.put("useTime", useTime);
				mapList.add(map);
			} else {
				Map<String, Object> map = MapUtils.newHashMap();
				map.put("deviceName", deviceName);
				map.put("deviceCategoryName", deviceCategoryName);
				map.put("deviceTypeName", deviceTypeName);
				map.put("runState", runState);
				map.put("FaultCount", 0);
				map.put("runTimeCount", 0.0);
				map.put("useTime", 0.0);
				mapList.add(map);
			} 
        }
        return mapList;
    }

    /**
     * 其他参数
     * Post: deviceID or deviceName
     * @return
     * Json:
     * {“installationSite”:”XXXX”,”department”:”XXX”,” manufacturer”:”XXX”,” purpose”:” XXX”,
     * ” startWorkDate”:”2019-1-1”,”madeDate”:”2018-1-1”,”yearlyDepreciation”:53,”durableYears”:3.5}
     */
    @RequestMapping(value = {"otherParameter", ""})
    public List<Map<String, Object>> otherParameter(HttpServletRequest request) {
        //String deviceID=request.getParameter("deviceID");
    	String deviceName=request.getParameter("deviceName");
        List<Map<String, Object>> mapList = ListUtils.newArrayList();
        String installationSite;
        String department;
        String manufacturer;
        String purpose;
        String startWorkDate;
        String madeDate;
        double yearlyDepreciation;
        double durableYears;
        for(IsDevice isDevice:isDeviceService.getDeviceByDeviceNo(deviceName)){
        	installationSite=isDevice.getInstallLocation();
            department=isDevice.getUseOffice();
            manufacturer=isDevice.getManufacturer();
            IsDeviceCode isDeviceCode=isDeviceCodeService.get(isDevice.getDeviceCodeId());
            purpose=isDeviceCode.getApplication();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            startWorkDate=df.format(isDevice.getUseDate());
            madeDate=df.format(isDevice.getProductionDate());
            yearlyDepreciation=Double.parseDouble(isDeviceCode.getDepreciation());
            durableYears=Double.parseDouble(isDeviceCode.getLife());
            Map<String, Object> map = MapUtils.newHashMap();
            map.put("installationSite",installationSite);
            map.put("department",department);
            map.put("manufacturer",manufacturer);
            map.put("purpose",purpose);
            map.put("startWorkDate",startWorkDate);
            map.put("madeDate",madeDate);
            map.put("yearlyDepreciation",yearlyDepreciation);
            map.put("durableYears",durableYears);
            mapList.add(map);
        }                
        return mapList;
    }

    /**
     * 设备参数
     * Post: deviceID or deviceName
     * @return
     * Json:
     * [{“property”:”xxxxxx”,”value”:”xxxxxxxxxxx” },{“property”:”xxxxxx”,”value”:”xxxxxxxxxxx” }]
     */
    @RequestMapping(value = {"deviceParameter", ""})
    public List<Map<String, Object>> deviceParameter(HttpServletRequest request) {
        List<Map<String, Object>> mapList = ListUtils.newArrayList();        
        String property;
        String value;
        for(IsDeviceProperties isDeviceProperties:isDevicePropertiesService.getDevicePropertiesDetail(request.getParameter("deviceName"))){
        	Map<String, Object> map = MapUtils.newHashMap();
        	property=isDeviceProperties.getProperty();
            value=isDeviceProperties.getPropertyValue();            
            map.put("property",property);
            map.put("value",value);
            mapList.add(map);
        }
        return mapList;
    }
    
    /**
     * 3.4.设备管理-备件库界面修改确认
     */
    /**
     * 备件基本信息列表
     * @return
     * Json：
	 * [{"deviceName":"xxx","deviceType":"xxx","deviceCategory":"xxx"},{"deviceName":"xxx","deviceType":"xxx","deviceCategory":"xxx"}]
     */
    @RequestMapping(value = "sparePartsList")
    public List<Map<String, Object>> sparePartsList() {
    	List<Map<String, Object>> mapList = ListUtils.newArrayList();
    	for(Device device:isDeviceService.sparePartsList()){
    		Map<String, Object> map = MapUtils.newHashMap();
    		map.put("deviceName", device.getDeviceNo());
    		map.put("deviceType",device.getDeviceCodeName());
    		map.put("deviceCategory", device.getModel());
    		mapList.add(map);
    	}
    	return mapList;
	}
    
    /**
     * 通讯控制组件信息
	 *Post:deviceName或者deviceCode
	 *Json
	 *{"useDate":"xxx","manufacturer ":"xxx","supplier":"xxx"}
     * @param request
     * @return
     */
    @RequestMapping(value = "componentInfo")
    public Map<String, Object> componentInfo(HttpServletRequest request) {
		Map<String, Object> map = MapUtils.newHashMap();
		String deviceName = request.getParameter("deviceName");
		for(IsDevice isDevice:isDeviceService.getDeviceByDeviceNo(deviceName)){
			map.put("useDate", CompareDate.simplifyDate(isDevice.getUseDate()));
			map.put("manufacturer", isDevice.getManufacturer());
			String supplier = "null";
			if (isDevice.getSupplier() != null) {
				supplier = isDevice.getSupplier();
			}
			map.put("supplier", supplier);
		}
		return map;
	}
    
    /**
     * 零件生命周期
     * 
     * （1）	设备种类下拉列表数据
     * （查到组件一层）
	 *Json:
	 *[{"idName":"id","deviceCategory":"智能双向穿梭车"},{"idName":"id","deviceCategory":"智能双向穿梭车"}]
     */
    @RequestMapping(value = "deviceTypeList")
    public List<Map<String, Object>> deviceTypeList() {
    	List<Map<String, Object>> mapList = ListUtils.newArrayList();
    	for(IsDeviceCode isDeviceCode:isDeviceCodeService.getDeviceTypeDetail()){
    		Map<String, Object> map = MapUtils.newHashMap();
    		String id = isDeviceCode.getId();
    		String deviceCategory = isDeviceCode.getName();
    		map.put("idName", id);
    		map.put("deviceCategory", deviceCategory);
    		mapList.add(map);
    	}
    	return mapList;
	}
    
    /**
     * 设备编号下拉列表数据
	 * Post:id
	 * Json：
	 * {"deviceName":["Car1","Car2","Car3","Car4","Car5"]}
     * @param request
     * @return
     */
    @RequestMapping(value = "deviceListByType")
    public Map<String, Object> deviceListByType(HttpServletRequest request) {
		Map<String, Object> map = MapUtils.newHashMap();
		String id = request.getParameter("id");
		List<String> list = new ArrayList<>();
		for(IsDevice isDevice:isDeviceService.getDeviceByCodeId(id)){
			list.add(isDevice.getDeviceNo());
		}
		map.put("deviceName", list);
		return map;
	}
    
    /**
     * 选择某一个设备编号
	 *Post：deviceName
	 *Json：
	 *[{“partName”:"Car1",”usedLlifecycle":"820","lifecycle":"1200"}]
     * @param request
     * @return
     */
    @RequestMapping(value = "lifeCycleDiagram")
    public List<Map<String, Object>> lifeCycleDiagram(HttpServletRequest request) {
    	List<Map<String, Object>> mapList = ListUtils.newArrayList();
    	String deviceName = request.getParameter("deviceName");
//    	List<Map<String, Object>> deviceUseList = ListUtils.newArrayList();
//    	List<Map<String, Object>> partOfDeviceList = ListUtils.newArrayList();
//    	List<Map<String, Object>> partOfPartList = ListUtils.newArrayList();
    	for(DeviceUse deviceUse:isDeviceUseService.deviceUseList()){
    		Map<String, Object> map = MapUtils.newHashMap();
    		map.put("deviceNo", deviceUse.getDeviceNo());
    		map.put("lifecycle", (Double.valueOf(deviceUse.getLife())*365.25));
    		map.put("usedLlifecycle", CompareDate.dateCount(deviceUse.getCreateDate()));
    		map.put("type", deviceUse.getType());
    		map.put("partId", deviceUse.getPartId());
    	}
    	DeviceUse deviceUse1 = isDeviceUseService.getDeviceUseByNo(deviceName);
    	if(deviceUse1 != null){
    		Map<String, Object> map = MapUtils.newHashMap();
    		map.put("partName", deviceUse1.getDeviceNo());
    		if (deviceUse1.getType().equals("2")) {
    			map.put("usedLlifecycle", CompareDate.dateCount(deviceUse1.getCreateDate()));
			}else {
				map.put("usedLlifecycle", 0);
			}    		
    		map.put("lifecycle", (Double.valueOf(deviceUse1.getLife())*365.25));
    		mapList.add(map);
    	}
    	for(IsDeviceCode isDeviceCode:isDeviceCodeService.partOfDevice(deviceName)){
    		Map<String, Object> map = MapUtils.newHashMap();
    		map.put("partName", isDeviceCode.getName());
    		map.put("lifecycle", (Double.valueOf(isDeviceCode.getLife())*365.25));
    		DeviceUse deviceUse = isDeviceUseService.getDeviceUseByPartId(isDeviceCode.getId());
    		if (deviceUse!=null && deviceUse.getType().equals("2")) {
    			map.put("usedLlifecycle", CompareDate.dateCount(deviceUse.getCreateDate()));
			}else {
				map.put("usedLlifecycle", 0);
			}     		
    		mapList.add(map);
    	}
    	for(IsDeviceCode isDeviceCode:isDeviceCodeService.partOfPart(deviceName)){
    		Map<String, Object> map = MapUtils.newHashMap();
    		map.put("partName", isDeviceCode.getName());
    		map.put("lifecycle", (Double.valueOf(isDeviceCode.getLife())*365.25));
    		DeviceUse deviceUse = isDeviceUseService.getDeviceUseByPartId(isDeviceCode.getId());
    		if (deviceUse!=null && deviceUse.getType().equals("2")) {
    			map.put("usedLlifecycle", CompareDate.dateCount(deviceUse.getCreateDate()));
			}else {
				map.put("usedLlifecycle", 0);
			}     		
    		mapList.add(map);
    	}
    	return mapList;
	}
    
    /**
     * 点击柱状图请求数据
	Post:
	{“partName”:"Car1","deviceName":"Car1"}
	Json:
	{"partName":"SFTR003","lifecycle":"1200","useTime":"820"}
     * @param request
     * @return
     */
    @RequestMapping(value = "getHistogram")
    public Map<String, Object> getHistogram(HttpServletRequest request) {
    	Map<String, Object> map = MapUtils.newHashMap();
    	String partName = request.getParameter("partName");
    	String deviceName = request.getParameter("deviceName");
    	DeviceUse deviceUse1 = isDeviceUseService.getDeviceUseByNo(partName);
    	if(deviceUse1 != null){    		
    		map.put("partName", deviceUse1.getDeviceNo());
    		if (deviceUse1.getType().equals("2")) {
    			map.put("usedLlifecycle", CompareDate.dateCount(deviceUse1.getCreateDate()));
			}else {
				map.put("usedLlifecycle", 0);
			}    		
    		map.put("lifecycle", (Double.valueOf(deviceUse1.getLife())*365.25));    		
    	}else {
			if (isDeviceCodeService.partOfDevice(deviceName) != null) {
				for(IsDeviceCode isDeviceCode:isDeviceCodeService.partOfDevice(deviceName)){
					if (isDeviceCode.getName().equals(partName)) {
						map.put("partName", isDeviceCode.getName());
			    		map.put("lifecycle", (Double.valueOf(isDeviceCode.getLife())*365.25));
			    		DeviceUse deviceUse = isDeviceUseService.getDeviceUseByPartId(isDeviceCode.getId());
			    		if (deviceUse!=null && deviceUse.getType().equals("2")) {
			    			map.put("usedLlifecycle", CompareDate.dateCount(deviceUse.getCreateDate()));
						}else {
							map.put("usedLlifecycle", 0);
						} 
					}		    		    		    		
		    	}
			}else if (isDeviceCodeService.partOfPart(deviceName) != null) {
				for(IsDeviceCode isDeviceCode:isDeviceCodeService.partOfPart(deviceName)){
					if (isDeviceCode.getName().equals(partName)) {
						map.put("partName", isDeviceCode.getName());
			    		map.put("lifecycle", (Double.valueOf(isDeviceCode.getLife())*365.25));
			    		DeviceUse deviceUse = isDeviceUseService.getDeviceUseByPartId(isDeviceCode.getId());
			    		if (deviceUse!=null && deviceUse.getType().equals("2")) {
			    			map.put("usedLlifecycle", CompareDate.dateCount(deviceUse.getCreateDate()));
						}else {
							map.put("usedLlifecycle", 0);
						} 
					}		    		    		    		
		    	}
			}else {
				map.put("fail", "未找到相关数据");
			}
		}    	    	
    	return map;
	}
    
    /**
     * 备件消耗报表
     * Json:
	 * [{"partName":"xxx","deviceType":"SFTR003","replacementNumber":20,"deviceName":"Car1""replacementDate":"xxx","operatorPersonnel":"xxx"}]
     * @return
     */
    @RequestMapping(value = "partsConsumptionList")
    public List<Map<String, Object>> partsConsumptionList() {
    	List<Map<String, Object>> mapList = ListUtils.newArrayList();
    	for(PartsConsumption partsConsumption:isRepairRecService.partsConsumptionList()){    		
    		if (partsConsumption.getResults().equals("4")) {
    			Map<String, Object> map = MapUtils.newHashMap();
    			map.put("partName", partsConsumption.getDeviceCodeName());
    			map.put("deviceType", partsConsumption.getModel());
    			map.put("replacementNumber", 1);
    			map.put("deviceName", partsConsumption.getDeviceNo());
    			map.put("replacementDate", partsConsumption.getRepairTime());
    			map.put("operatorPersonnel", partsConsumption.getOperator());
    			mapList.add(map);
			}    		
    	}
    	return mapList;
	}
    
    /**
     * （2）备件基本信息列表
	Post：deviceCategory
	Json：
	[{"sparePartsID":"xxx","sparePartsName":"xxx","sparePartsType":"xxx","sparePartsNumber":10},{"sparePartsID":"xxx","sparePartsName":"xxx","sparePartsType":"xxx","sparePartsNumber":5}]
	此处的sparePartsID是is_device_code->deviceCode
     */
    @RequestMapping(value = "sparePartsInfoList")
    public List<Map<String, Object>> sparePartsInfoList(HttpServletRequest request) {
    	List<Map<String, Object>> mapList = ListUtils.newArrayList();
    	String deviceCategory = request.getParameter("deviceCategory");
    	String deviceNo = "";
    	String sparePartsID = "";
    	String sparePartsName = "";
    	String sparePartsType = "";
    	int sparePartsNumber = 0;
    	for(Device device:isDeviceService.sparePartsList()){
    		if (device.getDeviceCodeName().equals(deviceCategory)) {
				deviceNo = device.getDeviceNo();
				sparePartsID = device.getCode();
				sparePartsName = device.getDeviceCodeName();
				sparePartsType = device.getModel();
				sparePartsNumber++;
			}
    	}
    	Map<String, Object> map = MapUtils.newHashMap();
    	map.put("sparePartsID", sparePartsID);
    	map.put("sparePartsName", sparePartsName);
    	map.put("sparePartsType", sparePartsType);
    	map.put("sparePartsNumber", sparePartsNumber);
    	mapList.add(map);
    	
    	for(IsDeviceCode isDeviceCode:isDeviceCodeService.partOfDevice(deviceNo)){
    		sparePartsNumber = 0;
    		sparePartsID = isDeviceCode.getCode();
			sparePartsName = isDeviceCode.getName();
			sparePartsType = isDeviceCode.getModel();
    		for(Device device:isDeviceService.sparePartsList()){
        		if (isDeviceCode.getName().equals(device.getDeviceCodeName())) {    				
    				sparePartsNumber++;
    			}
        	}
    		Map<String, Object> map1 = MapUtils.newHashMap();
        	map1.put("sparePartsID", sparePartsID);
        	map1.put("sparePartsName", sparePartsName);
        	map1.put("sparePartsType", sparePartsType);
        	map1.put("sparePartsNumber", sparePartsNumber);
        	mapList.add(map1);
    	}
    	
		for (IsDeviceCode isDeviceCode : isDeviceCodeService.partOfPart(deviceNo)) {
			sparePartsNumber = 0;
    		sparePartsID = isDeviceCode.getCode();
			sparePartsName = isDeviceCode.getName();
			sparePartsType = isDeviceCode.getModel();
			for(Device device:isDeviceService.sparePartsList()){
        		if (isDeviceCode.getName().equals(device.getDeviceCodeName())) {
    				sparePartsID = device.getCode();
    				sparePartsName = device.getDeviceCodeName();
    				sparePartsType = device.getModel();
    				sparePartsNumber++;
    			}
        	}
			Map<String, Object> map2 = MapUtils.newHashMap();
	    	map2.put("sparePartsID", sparePartsID);
	    	map2.put("sparePartsName", sparePartsName);
	    	map2.put("sparePartsType", sparePartsType);
	    	map2.put("sparePartsNumber", sparePartsNumber);
	    	mapList.add(map2);
		}
    	
    	return mapList;
    }
    
    /**
     * （2）备件基本信息列表(待修改，逻辑不正确)
	Post：deviceCategory
	Json：
	[{"sparePartsID":"xxx","sparePartsName":"xxx","sparePartsType":"xxx","sparePartsNumber":10},{"sparePartsID":"xxx","sparePartsName":"xxx","sparePartsType":"xxx","sparePartsNumber":5}]
	此处的sparePartsID是is_device->device_no,--->修改为所属设备
	sparePartsType--->改成deviceNo
     */
    @RequestMapping(value = "sparePartsIdInfoList")
    public List<Map<String, Object>> sparePartsIdInfoList(HttpServletRequest request) {
    	List<Map<String, Object>> mapList = ListUtils.newArrayList();
    	String deviceCategory = request.getParameter("deviceCategory");
    	String deviceNo = ""; 
    	    	
    	for(Device device:isDeviceService.sparePartsList()){ 
    		int sparePartsNumber = 0;
    		String sparePartsID = "";
        	String sparePartsName = "";
        	String sparePartsType = "";
    		if (device.getDeviceCodeName().equals(deviceCategory)) {
				deviceNo = device.getDeviceNo();
				//sparePartsID = device.getCode();
				sparePartsID = "null";
				sparePartsName = device.getDeviceCodeName();
				sparePartsType = device.getModel();
				sparePartsNumber++;
				Map<String, Object> map = MapUtils.newHashMap();
				map.put("sparePartsID", sparePartsID);
				map.put("sparePartsName", sparePartsName);
				map.put("sparePartsType", deviceNo);
				map.put("sparePartsNumber", sparePartsNumber);
				mapList.add(map);
			}    		  		
    	}    	    	
    	
		for (IsDeviceCode isDeviceCode : isDeviceCodeService.partOfDevice(deviceNo)) {
			int sparePartsNumber = 0;
			String sparePartsID = "";
			String sparePartsName = "";
			String sparePartsType = "";
			for (Device device : isDeviceService.sparePartsList()) {
				if (isDeviceCode.getName().equals(device.getDeviceCodeName())) {
					//sparePartsID = device.getDeviceNo();
					sparePartsID = isDeviceCodeService.getPartApplicationById(isDeviceCode.getParentCode()).getName();
					sparePartsName = device.getDeviceCodeName();
					sparePartsType = device.getModel();
					sparePartsNumber++;	
					Map<String, Object> map2 = MapUtils.newHashMap();
					map2.put("sparePartsID", sparePartsID);
					map2.put("sparePartsName", sparePartsName);
					map2.put("sparePartsType", device.getDeviceNo());
					map2.put("sparePartsNumber", sparePartsNumber);
					mapList.add(map2);
				} 				
			}
		}		    	
    	
		for (IsDeviceCode isDeviceCode : isDeviceCodeService.partOfPart(deviceNo)) {
			int sparePartsNumber = 0;
			String sparePartsID = "";
			String sparePartsName = "";
			String sparePartsType = "";
			for (Device device : isDeviceService.sparePartsList()) {
				if (isDeviceCode.getName().equals(device.getDeviceCodeName())) {
					//sparePartsID = device.getDeviceNo();
					sparePartsID = isDeviceCodeService.getPartApplicationById(isDeviceCode.getParentCode()).getName();
					sparePartsName = device.getDeviceCodeName();
					sparePartsType = device.getModel();
					sparePartsNumber++;	
					Map<String, Object> map2 = MapUtils.newHashMap();
					map2.put("sparePartsID", sparePartsID);
					map2.put("sparePartsName", sparePartsName);
					map2.put("sparePartsType", device.getDeviceNo());
					map2.put("sparePartsNumber", sparePartsNumber);
					mapList.add(map2);
				} 				
			}
		}	    	
    	return mapList;
    }
    
    /**
     * 3.2.	备件库预警提示
     * Json：
	[{"sparePartsName":"xxx","sparePartsType":"xxx","affiliation":,"xxx","warningNumber":10,"currentNumber":8},{"sparePartsName":"xxx","sparePartsType":"xxx","affiliation":,"xxx","warningNumber":10,"currentNumber":8}]

     * @return
     */
    @RequestMapping(value = "sparePartsAlertList")
    public List<Map<String, Object>> sparePartsAlertList() {
    	List<Map<String, Object>> mapList = ListUtils.newArrayList();
    	String sparePartsName = "";
    	String sparePartsType = "";
    	String affiliation = "";
    	int warningNumber = 0;
    	int currentNumber = 0;
    	for(IsDeviceCode isDeviceCode:isDeviceCodeService.getDeviceTypeDetail()){
    		for(SpareParts spareParts:isDeviceService.sparePartsCount()){
    			if (isDeviceCode.getName().equals(spareParts.getCodeName()) && isDeviceCode.getModel().equals(spareParts.getModel())) {
					if (isDeviceCode.getStockWarn() >= spareParts.getCounts()) {
						Map<String, Object> map = MapUtils.newHashMap();				    	
						sparePartsName = isDeviceCode.getName();
						sparePartsType = isDeviceCode.getModel();
						affiliation = "null";
						warningNumber = isDeviceCode.getStockWarn();
						currentNumber = spareParts.getCounts();						
				    	map.put("sparePartsName", sparePartsName);
				    	map.put("sparePartsType", sparePartsType);
				    	map.put("affiliation", affiliation);
				    	map.put("warningNumber", warningNumber);
				    	map.put("currentNumber", currentNumber);
				    	mapList.add(map);
					}
				}
    		}
    	}
    	
    	for(IsDeviceCode isDeviceCode:isDeviceCodeService.getPartsTypeDetail()){
    		for(SpareParts spareParts:isDeviceService.sparePartsCount()){
    			if (isDeviceCode.getName().equals(spareParts.getCodeName()) && isDeviceCode.getModel().equals(spareParts.getModel())) {
					if (isDeviceCode.getStockWarn() >= spareParts.getCounts()) {
						Map<String, Object> map = MapUtils.newHashMap();				    	
						sparePartsName = isDeviceCode.getName();
						sparePartsType = isDeviceCode.getModel();
						affiliation = isDeviceCodeService.getPartApplicationById(isDeviceCode.getParentCode()).getName();
						warningNumber = isDeviceCode.getStockWarn();
						currentNumber = spareParts.getCounts();						
				    	map.put("sparePartsName", sparePartsName);
				    	map.put("sparePartsType", sparePartsType);
				    	map.put("affiliation", affiliation);
				    	map.put("warningNumber", warningNumber);
				    	map.put("currentNumber", currentNumber);
				    	mapList.add(map);
					}
				}
    		}
    	}    	
    	return mapList;
    }
}
		