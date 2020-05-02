package com.jeesite.modules.is3dmcps.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import com.jeesite.common.collect.MapUtils;
import com.jeesite.modules.is3dmcps.entity.IsDevice;
import com.jeesite.modules.is3dmcps.entity.IsDeviceStatusInfo;
import com.jeesite.modules.is3dmcps.entity.IsFaults;
import com.jeesite.modules.is3dmcps.entity.IsFaultsInfo;
import com.jeesite.modules.is3dmcps.entity.IsKnowledge;
import com.jeesite.modules.is3dmcps.entity.IsRepairRec;
import com.jeesite.modules.is3dmcps.service.IsDeviceService;
import com.jeesite.modules.is3dmcps.service.IsDeviceStatusInfoService;
import com.jeesite.modules.is3dmcps.service.IsFaultsInfoService;
import com.jeesite.modules.is3dmcps.service.IsFaultsService;
import com.jeesite.modules.is3dmcps.service.IsKnowledgeService;
import com.jeesite.modules.is3dmcps.service.IsRepairRecService;
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
 *故障维修
 */
@CrossOrigin
@RestController
@RequestMapping(value = "static")
public class PageFaultRepairController extends BaseController{

    @Autowired
    IsFaultsService isFaultsService;
    @Autowired
    IsFaultsInfoService isFaultsInfoService;
    @Autowired
    IsRepairRecService isRepairRecService;
    @Autowired
    IsKnowledgeService isKnowledgeService;
    @Autowired
    IsDeviceService isDeviceService;
    @Autowired
    IsDeviceStatusInfoService isDeviceStatusInfoService;
    /**
     * 故障及维修的提交
     * @return
     */
	@RequestMapping(value = {"faultSubmit", ""})
	public List<Map<String, Object>> faultSubmit(HttpServletRequest request) {
		List<Map<String, Object>> mapList = ListUtils.newArrayList();
        Map<String, Object> map = MapUtils.newHashMap();
        String deviceName = request.getParameter("deviceName");
        String state;
        Map<String, Object> infoJsonStr = MapUtils.newHashMap();
        String faultId;
        String faultResult;
        for(IsDevice isDevice:isDeviceService.getDeviceByDeviceNo(deviceName)){
        	IsFaults isFaults = isFaultsService.getFaultsStateDetails(isDevice.getId());
        	if (isFaults == null) {
    			state = "Normal";
    		}else {
    			String s = isRepairRecService.getRepairResult(isFaults.getId());
    			if (s == null || s.equals("")) {
    				if (isFaults.getStatus().equals("0")) {
						state = "Fault";
					}else {
						state = "Normal";	
					}
				}else if (s.equals("2") || s.equals("3")) {
    				state = "Normal";				
    			}else if(s.equals("0") || s.equals("4")) {
    				state = "Fault";
    			}else {
    				state = "Error";				
    			}
    		}   
            if (state.equals("Normal")) {
            	List<Map<String, Object>> info = ListUtils.newArrayList();
    			for(IsKnowledge isKnowledge:isKnowledgeService.getAll()){        				
    				Map<String, Object> infoJson = MapUtils.newHashMap();
    				infoJsonStr.put("solutionID", isKnowledge.getId());
    				infoJson.put("recommendedSolution", isKnowledge.getTitle());
    				info.add(infoJson);
    			}
    			map.put("state",state);
                map.put("infoJsonStr",info);
    		}else if (state.equals("Fault")) {	
    			faultId = isFaults.getId();
    			faultResult = isFaults.getReason();
				infoJsonStr.put("faultID", faultId);
				if (faultResult != null) {
					infoJsonStr.put("faultResult", faultResult);
				}else {
					infoJsonStr.put("faultResult", "none");
				}
				
    			for(IsKnowledge isKnowledge:isKnowledgeService.getKnowledgeById(isFaults.getKnowledgeId())){    				
    				infoJsonStr.put("solutionID", isKnowledge.getId());
    				infoJsonStr.put("recommendedSolution", isKnowledge.getTitle());
    				infoJsonStr.put("recommendedSolutionContent", isKnowledge.getContent());
    			}
    			map.put("state",state);
                map.put("infoJsonStr",infoJsonStr);
    		}else {		
    			map.put("Error", "状态参数错误");
    		}                 
            mapList.add(map);
        }                
		return mapList;
	}

    /**
     *
     * @param request
     * @return
     * （1）报故障：
     * {”deviceName”:”xxxxx”,”person”:”xxxxx”,”faultResult”:”xxx损坏”,”recommendedSolution”:”xxx”}
     * （2）维修：（state：true代表完成，false代表未完成。false时，content仍会发送,但不用管content字段， content字段可能包含脏数据）
     * Post:
     * {”deviceName”:”xxxxx”, “faultID”:”xxx”,”person”:”xxxxx”,”state”:false,”content”,”xxxxxxxxxx”}
     * 修改为：
     * {”deviceName”:”xxxxx”, “faultID”:”xxx”,”person”:”xxxxx”,”state”:false,”content”,”xxxxxxxxxx”,"remark":"xxxxxxx"}
     */
    @RequestMapping(value = {"postFault", ""})
    public Map<String,Object> postFault(HttpServletRequest request){        
        String deviceName=request.getParameter("deviceName");
        String persion=request.getParameter("person");
        String faultResult=request.getParameter("faultResult");                 
        String state=request.getParameter("state");
        String content=request.getParameter("content");
        String remark = request.getParameter("remark");        
        Date date=new Date();
        Map<String,Object> map=new HashMap<>();
        if(state==null){
        	String recommendedSolution=isKnowledgeService.getKnowledgeByTitle(request.getParameter("recommendedSolution").toString().trim()).getId();
        	for(IsDevice isDevice:isDeviceService.getDeviceByDeviceNo(deviceName)){
	            IsFaults isFaults=new IsFaults(deviceName+"故障",isDevice.getId(),isDevice.getDeviceCodeName(),null,date,persion,faultResult,recommendedSolution,remark);
	            try{
	                isFaultsService.save(isFaults);
	            }catch(Exception exception){
	                map.put("result",exception.toString());
	                return map;
	            }
	            map.put("result","ok");
        	}
            return map;
        }else{
            if(state.equals("true")){
                state="2";	//维修完成
                String deviceId = "";
                for(IsDevice isDevice: isDeviceService.getDeviceByDeviceNo(deviceName))
                	deviceId = isDevice.getId();
    			IsFaults isFaults = isFaultsService.getFaultsStateDetails(deviceId);
    			if (isFaults != null) {
    				String faultID = isFaults.getId();
    				IsRepairRec isRepairRec = new IsRepairRec(faultID, faultResult, content, state, persion, date);    				
    				try {
    					isRepairRecService.save(isRepairRec);
    				} catch (Exception exception) {
    					map.put("result", exception.toString());
    					return map;
    				}
    				//更新故障表和维修表
    				isRepairRecService.updateRepairRec(faultID, "2", "2");
    				isFaultsService.updateIsFaults(deviceId, "3");
    				map.put("result", "ok");
    			}else {
    				map.put("result", "未故障");
    			}
            }else{
                state="0";	//维修中
                String deviceId = "";
                for(IsDevice isDevice: isDeviceService.getDeviceByDeviceNo(deviceName))
                	deviceId = isDevice.getId();
    			IsFaults isFaults = isFaultsService.getFaultsStateDetails(deviceId);
    			if (isFaults != null) {
    				String faultID = isFaults.getId();
    				IsRepairRec isRepairRec = new IsRepairRec(faultID, faultResult, content, state, persion, date);
    				isRepairRec.setStatus("3");  //状态值有矛盾，此处增加一个字典键值表"维修中"
    				try {
    					isRepairRecService.save(isRepairRec);
    				} catch (Exception exception) {
    					map.put("result", exception.toString());
    					return map;
    				}
    				//更新故障表
    				isFaultsService.updateIsFaults(deviceId, "2");
    				map.put("result", "ok");
    			}else {
    				map.put("result", "未故障");
    			}
            }            
			return map;
        }
    }

    /**
     * 近7天故障情况
     * Post:deviceID or deviceName
     * @return
     * Json：
     * [{“date”:1,” faultCount”:123,”repairedCount”:54},{“date”:2,” faultCount”:123,” repairedCount”:54},…]
     */
    @RequestMapping(value = {"sevenFault", ""})
    public List<Map<String, Object>> sevenFault() {
        List<Map<String, Object>> mapList = ListUtils.newArrayList();        
        //SimpleDateFormat format = new SimpleDateFormat("MM.dd");
        int faultCount;
        int repairedCount;
        for(String date:CompareDate.getSevenDate())	{
        	Map<String, Object> map = MapUtils.newHashMap();
            faultCount=isFaultsService.getFaultsCount(date);
            repairedCount=isRepairRecService.getRepairCount(date);
            map.put("date",CompareDate.simplifyDate(date));
            map.put("faultCount",faultCount);
            map.put("repairedCount",repairedCount);
            mapList.add(map);
        }
        return mapList;
    }
    
    /**
     * 一个月内故障情况
     * @return
     */
    @RequestMapping(value = {"monthFault", ""})
    public List<Map<String, Object>> monthFault() {
        List<Map<String, Object>> mapList = ListUtils.newArrayList();        
        //SimpleDateFormat format = new SimpleDateFormat("MM.dd");
        int faultCount;
        int repairedCount;
        Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM");		
		String date = sdf.format(today);
		Map<String, Object> map = MapUtils.newHashMap();		
		repairedCount = isRepairRecService.getRepairCountMonth(date);
		faultCount = isFaultsService.getFaultsCountMonth(date) + repairedCount;
		map.put("faultCount", faultCount);
		map.put("repairedCount", repairedCount);
		mapList.add(map);        
        return mapList;
    }
    
    /**
     * 故障需要维修的设备
     */
    @RequestMapping(value = {"needRepair", ""})
    public List<Map<String, Object>> needRepair() {
    	List<Map<String, Object>> mapList = ListUtils.newArrayList();
    	for(IsFaults isFaults:isFaultsService.getNeedRepair()){
			IsDevice isDevice = isDeviceService.getDeviceById(isFaults.getDeviceId());
			Map<String, Object> map = MapUtils.newHashMap();
			map.put("deviceName", isDevice.getDeviceNo());
			mapList.add(map);   		
    	}
    	return mapList;
    }
    
    /**
     * 设备故障信息展示与查询
     * Json:
	[{"faultName": "升降输送机故障","deviceName": "智能双向穿梭车","deviceNumber": "Car1","faultCode": "fault001","faultTime": "2019-05-2 00::00:00 ","repairsPersonnel": "兰州烟草","status": "新故障"}]
     * @return
     */
    @RequestMapping(value = {"faultsInfoList", ""})
    public List<Map<String, Object>> faultsInfoList() {
		List<Map<String, Object>> mapList = ListUtils.newArrayList();
		for(IsFaults isFaults:isFaultsService.faultsList()){
			Map<String, Object> map = MapUtils.newHashMap();
			map.put("faultName", isFaults.getName());
			IsDevice isDevice = isDeviceService.getDeviceById(isFaults.getDeviceId());
			map.put("deviceName", isDevice.getDeviceCodeName());
			map.put("deviceNumber", isDevice.getDeviceNo());		
			map.put("faultCode", isFaults.getFaultsCode());
			map.put("faultTime", String.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(isFaults.getFaultsTime())));
			map.put("repairsPersonnel", isFaults.getOperator());
			if (isFaults.getStatus().equals("0")) {
				map.put("status", "新故障");
			}else if (isFaults.getStatus().equals("2")) {
				map.put("status", "已受理");
			}else if (isFaults.getStatus().equals("3")) {
				map.put("status", "维修完成");
			}else if (isFaults.getStatus().equals("4")) {
				map.put("status", "报废处理");
			}else {
				map.put("status", "Error");
			}
			mapList.add(map);
		}
		return mapList;
	}
    
    /**
     * 维修记录信息展示与查询 
     * Json:
	 *[{"faultName": "升降输送机故障","repairResults": "维修失败","repairPersonnel": "兰州烟草","repairTime": "2019-05-2 00::00:00 "}]
     * @return
     */
    @RequestMapping(value = {"faultsRepairList", ""})
    public List<Map<String, Object>> faultsRepairList() {
    	List<Map<String, Object>> mapList = ListUtils.newArrayList();
    	for(IsRepairRec isRepairRec:isRepairRecService.repairList()){
    		Map<String, Object> map = MapUtils.newHashMap();
    		map.put("faultName", isRepairRec.getFaultsName());
    		if (isRepairRec.getResults().equals("0")) {
				map.put("repairResults", "维修中");
			}else if(isRepairRec.getResults().equals("2")) {
				map.put("repairResults", "维修完成，恢复使用");
			}else if(isRepairRec.getResults().equals("3")) {
				map.put("repairResults", "维修完成，设备闲置");
			}else if(isRepairRec.getResults().equals("4")) {
				map.put("repairResults", "维修失败，设备报废");
			}else {
				map.put("repairResults", "Error");
			} 
    		map.put("repairPersonnel", isRepairRec.getOperator());
    		map.put("faultTime", String.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(isRepairRec.getRepairTime())));
    		mapList.add(map);
    	}
		return mapList;
	}
    
    /**
     * 6.4.1.故障日志界面：
     * Json：
	 * {"faultName": ["智能双向穿梭车故障", "提升机故障", "拆码垛机故障"],"deviceName": ["智能双向穿梭车", "提升机", "拆码垛机"],"servicePersonnel": ["xxx", "xxx", "xxx"],"status": ["xxx", "xxx", "xxx"]}
     * @return
     */
    @RequestMapping(value = {"faultLogList", ""})
    public Map<String, Object> faultLogList() {
    	Map<String, Object> map = MapUtils.newHashMap();
    	List<String> faultName = new ArrayList<>();
    	List<String> deviceName = new ArrayList<>();
    	List<String> servicePersonnel = new ArrayList<>();
    	List<String> status = new ArrayList<>();
    	for(IsFaults isFaults:isFaultsService.faultsLogList()){
    		faultName.add(isFaults.getName());
    		deviceName.add(isFaults.getDeviceName());
    		servicePersonnel.add(isFaults.getOperator());
    		switch (isFaults.getStatus()) {
			case "0":
				status.add("新故障");
				break;
			case "2":
				status.add("已受理");
				break;
			case "3":
				status.add("维修完成");
				break;
			case "4":
				status.add("报废处理");
				break;
			default:
				status.add("statusError");
				break;
			}
    	}
    	List<String> faultNameSet = new ArrayList<>(new HashSet<>(faultName));
    	List<String> deviceNameSet = new ArrayList<>(new HashSet<>(deviceName));
    	List<String> servicePersonnelSet = new ArrayList<>(new HashSet<>(servicePersonnel));
    	List<String> statusSet = new ArrayList<>(new HashSet<>(status));
    	map.put("faultName", faultNameSet);
    	map.put("deviceName", deviceNameSet);
    	map.put("servicePersonnel", servicePersonnelSet);
    	map.put("status", statusSet);
    	return map;
	}
    
    /**
     * Post:	
	 *{"faultName":"xxx","deviceName":"xxx","servicePersonnel":"xxx" ,"status":"xxx","startTime":"xxx","endTime":"xxx"}
	
	 *Json:（根据条件请求筛选的数据）
	 *[{"faultName": "智能双向穿梭车故障","deviceName": "智能双向穿梭车","deviceNumber": "Car1","faultCode": "fault001","faultTime": "2019-05-2 00::00:00 ","servicePersonnel": "兰州烟草","status": "保养计划"}]

     * @param request
     * @return
     */
    @RequestMapping(value = "filterFaultsLog")
    public List<Map<String, Object>> filterFaultsLog(HttpServletRequest request) {
    	List<Map<String, Object>> mapList = ListUtils.newArrayList();
    	String faultName = request.getParameter("faultName");
    	String deviceName = request.getParameter("deviceName");
    	String servicePersonnel = request.getParameter("servicePersonnel");    	
    	String status = request.getParameter("status");
    	if (status != null) {
    		switch (status) {
        	case "新故障":
    			status = "0";
    			break;
    		case "已受理":
    			status = "2";
    			break;
    		case "维修完成":
    			status = "3";
    			break;
    		case "报废处理":
    			status = "4";
    			break;
    		default:
    			status = null;
    			break;
    		}
		}
    	String startTime = request.getParameter("startTime");
    	String endTime = request.getParameter("endTime");
    	if (startTime != null) {
    		startTime = CompareDate.formatDate(request.getParameter("startTime"),false);
		}  
    	if (endTime != null) {
    		endTime = CompareDate.formatDate(request.getParameter("endTime"),true);
		}
    	String rangeStart = request.getParameter("rangeStart");
    	String rangeEnd = request.getParameter("rangeEnd");  
    	String remark;
    		for(IsFaults isFaults:isFaultsService.filterFaultsLogPage(faultName, deviceName, servicePersonnel, status, startTime, endTime, Integer.valueOf(rangeStart), Integer.valueOf(rangeEnd))){
        		Map<String, Object> map = MapUtils.newHashMap();
        		map.put("faultName", isFaults.getName());
        		map.put("deviceName", isFaults.getDeviceName());
        		map.put("deviceNumber", isFaults.getDeviceId());
        		map.put("faultCode", isFaults.getFaultsCode());
        		map.put("faultTime", CompareDate.simplifyDate(isFaults.getFaultsTime()));
        		map.put("servicePersonnel", isFaults.getOperator());          		
        		remark = isFaults.getRemarks();
        		StringBuilder result = null;
        		if (remark != null && !remark.equals("") && remark.startsWith("{") 
        				&& remark.endsWith("}")) {
        			if (!remark.contains("\"")) {
        				result = deviceStatusString(remark, isFaults.getDeviceName());
					}else {
						result = deviceStatusJson(remark, isFaults.getDeviceName());
					}
				}else if (remark != null) {
					result = new StringBuilder(remark);
				}else {
					result = new StringBuilder("unknown");
				}        		         		
        		map.put("remark", result);
        		switch (isFaults.getStatus()) {
        		case "0":
    				map.put("status","新故障");
    				break;
    			case "2":
    				map.put("status","已受理");
    				break;
    			case "3":
    				map.put("status","维修完成");
    				break;
    			case "4":
    				map.put("status","报废处理");
    				break;
    			default:
    				map.put("status","statusError");
    				break;
				}   
        		mapList.add(map);
        	}
		
    	return mapList;
	}
    
    /**
     * 维修日志界面
     * Json：
	 *{"faultName": ["智能双向穿梭车故障", "提升机故障", "拆码垛机故障"],"repairResults": ["维修中", "维修完成", "维修失败"],"repairPersonnel": ["xxx", "xxx", "xxx"]}
	 *修改：
	 *Json： 	
	{"faultName": ["智能双向穿梭车故障", "提升机故障", "拆码垛机故障"],"repairResults": ["维修中", "维修完成", "维修失败"],"repairPersonnel": ["xxx", "xxx", "xxx"],"status": ["xxx", "xxx", "xxx"]}

     * @return
     */
    @RequestMapping(value = {"repairLogList", ""})
    public Map<String, Object> repairLogList() {
    	Map<String, Object> map = MapUtils.newHashMap();
    	List<String> faultName = new ArrayList<>();    	
    	List<String> repairPersonnel = new ArrayList<>();
    	List<String> repairResults = new ArrayList<>();
    	List<String> status = new ArrayList<>();
    	for(IsRepairRec isRepairRec:isRepairRecService.repairLogList()){
    		faultName.add(isRepairRec.getFaultsName());    		
    		repairPersonnel.add(isRepairRec.getOperator());
    		if (isRepairRec.getResults() != null) {
    			switch (isRepairRec.getResults()) {
    			case "0":
    				repairResults.add("维修中");
    				break;
    			case "2":
    				repairResults.add("修理完成恢复使用");
    				break;
    			case "3":
    				repairResults.add("维修完成设备闲置");
    				break;
    			case "4":
    				repairResults.add("维修失败设备报废");
    				break;
    			default:
    				//repairResults.add("resultsError");
    				break;
    			}
			}
    		if (isRepairRec.getStatus() != null) {
    			switch (isRepairRec.getStatus()) {
    			case "0":
    				status.add("未处理");
    				break;
    			case "1":
    				status.add("维修失败");
    				break;
    			case "2":
    				status.add("维修完成");
    				break;
    			case "3":
    				status.add("维修中");
    				break;
    			default:
    				break;
    			}
			}    		
    	}
    	List<String> faultNameSet = new ArrayList<>(new HashSet<>(faultName));    	
    	List<String> repairPersonnelSet = new ArrayList<>(new HashSet<>(repairPersonnel));
    	List<String> repairResultsSet = new ArrayList<>(new HashSet<>(repairResults));
    	List<String> statusSet = new ArrayList<>(new HashSet<>(status));
    	map.put("faultName", faultNameSet);    	
    	map.put("repairPersonnel", repairPersonnelSet);
    	map.put("repairResults", repairResultsSet);
    	map.put("status", statusSet);
    	return map;
	}
    
    /**
     * Post:
	 * {"faultName":"xxx","repairResults":"xxx","repairPersonnel":"xxx","startTime":"xxx","endTime":"xxx"}	
	 * Json:（根据条件请求筛选的数据）
	 * [{"faultName": "升降输送机故障","repairResults": "维修失败","repairPersonnel": "兰州烟草","repairTime": "2019-05-2 00::00:00 "}]
	 * 
	 * 4.3.2.	维修日志
	Post: 		 增加分页范围rangeStart、rangeEnd
	{"faultName":"xxx","repairResults":"xxx","repairPersonnel":"xxx","startTime":"xxx","endTime":"xxx","rangeStart":1,"rangeEnd":500}}
	
	Json:（根据条件请求筛选的数据）
	[{"faultName": "升降输送机故障","repairResults": "维修失败","handlingMethod": "清洁","repairPersonnel": "兰州烟草","repairTime": "2019-05-2 00::00:00 "}]

	增加状态
     * @param request
     * @return
     */
    @RequestMapping(value = {"filterRepairLog", ""})
    public List<Map<String, Object>> filterRepairLog(HttpServletRequest request) {
    	List<Map<String, Object>> mapList = ListUtils.newArrayList();
    	String faultName = request.getParameter("faultName");    	
    	String repairPersonnel = request.getParameter("repairPersonnel");    	
    	String repairResults = request.getParameter("repairResults");
    	String status = request.getParameter("status");
    	if (status != null) {
    		switch (status) {
        	case "未处理":
        		status = "0";
    			break;
    		case "维修失败":
    			status = "1";
    			break;
    		case "维修完成":
    			status = "2";
    			break;
    		case "维修中":
				status = "3";
				break;
    		default:
    			status = null;
    			break;
    		}
		}
    	if (repairResults != null) {
    		switch (repairResults) {
        	case "维修中":
        		repairResults = "0";
    			break;
    		case "修理完成恢复使用":
    			repairResults = "2";
    			break;
    		case "维修完成设备闲置":
    			repairResults = "3";
    			break;
    		case "维修失败设备报废":
    			repairResults = "4";
    			break;
    		default:
    			repairResults = null;
    			break;
    		}
		}    	
    	String startTime = request.getParameter("startTime");
    	String endTime = request.getParameter("endTime");
    	if (startTime != null) {
    		startTime = CompareDate.formatDate(request.getParameter("startTime"), false);
		}  
    	if (endTime != null) {
    		endTime = CompareDate.formatDate(request.getParameter("endTime"), true);
		}
    	String rangeStart = request.getParameter("rangeStart");
    	String rangeEnd = request.getParameter("rangeEnd");    	
    		for(IsRepairRec isRepairRec:isRepairRecService.filterRepairLogPage(faultName, repairPersonnel, repairResults, startTime, endTime, Integer.valueOf(rangeStart), Integer.valueOf(rangeEnd))){
        		Map<String, Object> map = MapUtils.newHashMap();
        		map.put("faultName", isRepairRec.getFaultsName());    		
        		//map.put("repairResults", request.getParameter("repairResults"));
        		map.put("repairPersonnel", isRepairRec.getOperator());
        		map.put("handlingMethod", isRepairRec.getRecord());
        		map.put("repairTime", CompareDate.simplifyDate(isRepairRec.getRepairTime())); 
        		switch (isRepairRec.getResults()) {
    			case "0":
    				map.put("repairResults","维修中");
    				break;
    			case "2":
    				map.put("repairResults","修理完成恢复使用");
    				break;
    			case "3":
    				map.put("repairResults","维修完成设备闲置");
    				break;
    			case "4":
    				map.put("repairResults","维修失败设备报废");
    				break;
    			default:
    				map.put("repairResults","resultsError");
    				break;
    			}
        		switch (isRepairRec.getStatus()) {
    			case "0":
    				map.put("status","未处理");
    				break;
    			case "1":
    				map.put("status","维修失败");
    				break;
    			case "2":
    				map.put("status","维修完成");
    				break;
    			case "3":
    				map.put("status","维修中");
    				break;
    			default:
    				map.put("status","statusError");
    				break;
    			}
        		mapList.add(map);
        	}		
    	return mapList;
	}
    
    /**
     * 3.2.3.	故障信息弹窗
     * Post： deviceName
	Json： 
 	[{”faultCode”:”xxx”,”faultInfo”:”XXX” ,”faultNumber”:”10”}]

     * @return
     */
    @RequestMapping("faultsPop")
    public List<Map<String, Object>> faultsPop(HttpServletRequest request) {
    	List<Map<String, Object>> mapList = ListUtils.newArrayList();
    	String deviceName = request.getParameter("deviceName");
    	for(IsFaults isFaults:isFaultsService.faultsPop(deviceName)){
    		Map<String, Object> map = MapUtils.newHashMap();
    		if (isFaults != null) {
    			map.put("faultCode", isFaults.getFaultsCode());
        		map.put("faultNumber", Integer.parseInt(isFaults.getName()));
        		IsFaultsInfo isFaultsInfo = isFaultsInfoService.getFaultsInfo(isFaults.getFaultsCode());
        		if (isFaultsInfo != null) {
        			map.put("faultInfo", isFaultsInfo.getInfo());
				}else {
					map.put("faultInfo", "无相关故障信息");
				}
			}  
    		mapList.add(map);
    	}
    	return mapList;
	}
    
    /**
     * 处理设备状态信息的工具方法
     */
    public StringBuilder deviceStatusString(String remark, String deviceName) {
		//{CarPlt:2,Car_RunState:2,CmdCode:0,Err02:0,Err01:1,Err04:1,Target_X:0,
    	//    Err03:1,Target_Y:0,Err05:0,MoveErr_Counts:0,Car_Angle:90,Bat_Current:120,
		//    	TurnErr_Counts:0,Move_Mileage:0,Car_Row:11,JobState:0,Car_Layer:2,
		//    	UpDown_Counts:0,Car_Column:31,Car_ID:1,Turn_Counts:0,Car_WorkState:1,
		//    	Target_Distance:0,Car_Zone:0,Bat_Voltage:5400,BoxCode:0,UpDownErr_Counts:0,
		//    	Err_Counts:0,Car_Mode:2,JobID:0,Car_Speed:0}
    	StringBuilder result = new StringBuilder();
    	result.append("{");
    	String s = remark.replaceAll("\\{", "").replaceAll("\\}", "");    	    	
    	String str[] = s.split(",");
    	for(String dic : str){
    		String key = dic.substring(0, dic.indexOf(":"));
    		String val = dic.substring(dic.indexOf(":")+1);
    		System.err.println(key+"---"+val);
    		IsDeviceStatusInfo isDeviceStatusInfo = null;
    		isDeviceStatusInfo = isDeviceStatusInfoService.getByEnDevice(key, deviceName);
    		if (isDeviceStatusInfo != null) {
    			key = isDeviceStatusInfo.getZhField();
			} 
    		result.append(key+":"+val+",");
    	}
    	result.deleteCharAt(result.length()-1).append("}");
    	System.err.println(result);
    	return result;
	}
    
    public StringBuilder deviceStatusJson(String remark, String deviceName) {
    	// 小车：{"location_X":0.0,"location_Z":0.0,"location_Y":0.0,"TargetLocalPosition_X":0.0,
		//    	"TargetLocalPosition_Y":0.0,"Car_ID":6,"Car_Id":"6","Car_Row":11,"Car_Column":34,
		//    	"Car_Layer":3,"Car_Angle":90,"CarPlt":2,"Car_RunState":2,"Car_WorkState":1,
		//    	"Bat_Current":120,"Bat_Voltage":5400,"Target_X":0,"Target_Y":0,"Target_Distance":0.0,
		//    	"Car_Speed":0.0,"BoxCode":0.0,"CmdCode":0,"JobState":0,"JobID":0,"Car_Mode":2,
		//    	"Err01":0,"Err02":0,"Err03":0,"Err04":0,"Err05":0}
    	// 注：key有双引号

    	StringBuilder result = new StringBuilder();
    	result.append("{");
    	String s = remark.replaceAll("\\{", "").replaceAll("\\}", "").replaceAll("\"", "");    	    	
    	String str[] = s.split(",");
    	for(String dic : str){
    		String key = dic.substring(0, dic.indexOf(":"));
    		String val = dic.substring(dic.indexOf(":")+1);
    		System.err.println(key+"---"+val);
    		IsDeviceStatusInfo isDeviceStatusInfo = null;
    		isDeviceStatusInfo = isDeviceStatusInfoService.getByEnDevice(key, deviceName);
    		if (isDeviceStatusInfo != null) {
    			key = isDeviceStatusInfo.getZhField();
			} 
    		result.append(key+":"+val+",");
    	}
    	result.deleteCharAt(result.length()-1).append("}");
    	System.err.println(result);
    	return result;
	}
}
		