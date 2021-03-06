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
import com.jeesite.modules.is3dmcps.entity.IsDeviceCode;
import com.jeesite.modules.is3dmcps.entity.IsPatrol;
import com.jeesite.modules.is3dmcps.entity.IsPatrolRec;
import com.jeesite.modules.is3dmcps.service.IsDeviceCodeService;
import com.jeesite.modules.is3dmcps.service.IsDeviceService;
import com.jeesite.modules.is3dmcps.service.IsPatrolRecService;
import com.jeesite.modules.is3dmcps.service.IsPatrolService;
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
 *巡检
 */
@CrossOrigin
@RestController
@RequestMapping(value = "static")
public class PagePatrolManController extends BaseController{
    @Autowired
    IsPatrolRecService isPatrolRecService;
    @Autowired
    IsPatrolService isPatrolService;
    @Autowired
    IsDeviceService isDeviceService;
    @Autowired
    IsDeviceCodeService isDeviceCodeService;
    /**
     * 巡检弹窗
     * Post:deviceID or deviceName
     * @return
     * Json:
     * [{ “patrolID”:”xxxx”,“ patrolName”:”xxx”,” patrolContent”:”xxx….” },{ “patrolID”:”xxxx”,“ patrolName”:”xxx”,” patrolContent”:”xxx….” }]
     */
	@RequestMapping(value = {"patrolPop", ""})
	public List<Map<String, Object>> patrolPop(HttpServletRequest request) {
		List<Map<String, Object>> mapList = ListUtils.newArrayList();
        Map<String, Object> map = MapUtils.newHashMap();
        //String deviceId = request.getParameter("deviceID");
        String deviceName  = request.getParameter("deviceName");
		String patrolID;
        String patrolName;
        String patrolContent;
		for(IsPatrol isPatrol : isPatrolService.getPatrolByName(deviceName)){
			patrolID = isPatrol.getId();
			patrolName = isPatrol.getName();
			patrolContent = isPatrol.getContent();
			map.put("patrolID", patrolID);
			map.put("patrolName", patrolName);
			map.put("patrolContent", patrolContent);
			mapList.add(map);
		}		
		return mapList;
	}

    /**
     *
     * @param request
     * Post(Put):
     * {“deviceName”:”xxx”,” patrolName”:”xxx”,” patrolPerson”:”xxx”,”state”:true,”remark”:”xxxxxxxxxxxx”}
     * 修改
     * {“deviceName”:”xxx”,” patrolName”:”xxx”,” patrolPerson”:”xxx”,”state”:true,”record”:”xxxxxxxxxxxx”,”remark”:”xxxxxxxxxxxx”}
     * @return
     */
    @RequestMapping(value = {"postPatrol", ""})
    public Map<String,Object> postPatrol(HttpServletRequest request){
        String deviceName  = request.getParameter("deviceName");
        String patrolPersion=request.getParameter("patrolPerson");
        String state=request.getParameter("state");
        String remark=request.getParameter("remark");
        Date date=new Date();
        if (state.equals("true")) {
			state = "正常";
		}else if (state.equals("false")){
			state = "异常";
		}
        Map<String,Object> map=new HashMap<>();
        try{
        	String deviceId = ""; 
        	String patrolID = "";
        	String patrolName= "";
        	for(IsDevice isDevice: isDeviceService.getDeviceByDeviceNo(deviceName))
        		deviceId = isDevice.getId();
        	for(IsPatrol isPatrol: isPatrolService.getPatrolByDeviceId(deviceId)){
        		patrolID = isPatrol.getId();
        		patrolName = isPatrol.getName();
        	}        	
        	IsPatrolRec isPatrolRec=new IsPatrolRec(patrolID,patrolName,state,patrolPersion,date,remark);
            isPatrolRecService.save(isPatrolRec);
        }catch(Exception exception){
            map.put("result",exception.toString());
            return map;
        }
        map.put("result","ok");
        return map;
    }

    /**
     * 近7天巡检情况
     * @return
     * Json：
     * [{“date”:1,” patrolCount”:123},{“date”:2,” patrolCount”:123} ,…]
     */
    @RequestMapping(value = {"sevenPatrol", ""})
    public List<Map<String, Object>> sevenPatrol() {
        List<Map<String, Object>> mapList = ListUtils.newArrayList();        
        //SimpleDateFormat format = new SimpleDateFormat("MM.dd");
        int patrolCount;
        for(String date:CompareDate.getSevenDate())	{
        	Map<String, Object> map = MapUtils.newHashMap();
            patrolCount=isPatrolRecService.getPatrolCount(date);
            map.put("date",CompareDate.simplifyDate(date));
            map.put("patrolCount",patrolCount);
            mapList.add(map);
        }
        return mapList;
    }
    
    /**
     * 设备巡检记录展示与查询
     * Json:
	 *[{"inspectionName": "拆码垛机保养","inspectionRecord": "加快检修","inspectionPersonnel": "兰州烟草","inspectionTime": "2019-05-2 00::00:00 "}]
     * @return
     */
    @RequestMapping(value = {"patrolList", ""})
    public List<Map<String, Object>> patrolList() {
    	List<Map<String, Object>> mapList = ListUtils.newArrayList(); 
    	for(IsPatrolRec isPatrolRec:isPatrolRecService.patrolList()){
    		Map<String, Object> map = MapUtils.newHashMap();
    		map.put("inspectionName", isPatrolRec.getPatrolName());
    		if (isPatrolRec.getRecord() != null) {
				map.put("inspectionRecord", isPatrolRec.getRecord());
			}else {
				map.put("inspectionRecord", "");
			}
    		map.put("inspectionPersonnel", isPatrolRec.getOperator());
    		map.put("inspectionTime", String.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(isPatrolRec.getPatrolTime())));
    		mapList.add(map);
    	}
    	return mapList;
	}
    
    /**
     * 需要进行人工巡检的设备
     */
    @RequestMapping(value = {"needPatrol", ""})
    public List<Map<String, Object>> needPatrol() {
    	List<Map<String, Object>> mapList = ListUtils.newArrayList();
		for (IsPatrol isPatrol : isPatrolService.getNeedPatrol()) {
			IsDevice isDevice = isDeviceService.getDeviceById(isPatrol.getDeviceId());
			Map<String, Object> map = MapUtils.newHashMap();
			map.put("deviceName", isDevice.getDeviceNo());
			mapList.add(map);			
		}
    	return mapList;
    }
    
    /**
     * 巡检点数据
	 * Json:
	 * [{"deviceType":"xxx","deviceName":"xxx"},{"deviceType":"xxx","deviceName":"xxx"}]
     * @return
     */
    @RequestMapping(value = "patrolPointList")
    public List<Map<String, Object>> patrolPointList() {
		List<Map<String, Object>> mapList = ListUtils.newArrayList();
		for(IsDeviceCode isDeviceCode:isDeviceCodeService.getPatrolPoint()){
			Map<String, Object> map = MapUtils.newHashMap();
			String deviceType = isDeviceCode.getModel();
			String deviceName = isDeviceCode.getName();
			map.put("deviceType", deviceType);
			map.put("deviceName", deviceName);
			mapList.add(map);
		}
		return mapList;
	}
    
    /**
     * 巡检计划数据
	 * Json：
	 * [{"planTime":"xxx","deviceName":"xxx"},{"planTime":"xxx","deviceName":"xxx"}]
     * @return
     */
    @RequestMapping(value = "patrolPlanList")
    public List<Map<String, Object>> patrolPlanList() {
    	List<Map<String, Object>> mapList = ListUtils.newArrayList();
    	for(IsPatrolRec isPatrolRec:isPatrolRecService.patrolPlanList()){
    		Map<String, Object> map = MapUtils.newHashMap();
    		String planTime = CompareDate.simplifyDate(isPatrolRec.getPatrolTime());
    		String deviceName = isPatrolRec.getPatrolName();
    		map.put("planTime", planTime);
    		map.put("deviceName", deviceName);
    		mapList.add(map);
    	}
    	return mapList;
	}
    
    /**
     * 巡检日志查询界面
     * （1）Json：
	 * {"inspectionName": ["xxx", "xxx", "xxx"],"inspectionPersonnel": ["xxx", "xxx", "xxx"]}
	 * 修改为
	 * {"inspectionName":["xxx","xxx","xxx"],"inspectionPersonnel":["xxx", "xxx"],"inspectionRecord": ["xxx", "xxx"]}
     * @return
     */
    @RequestMapping(value = "patrolLogList")
    public Map<String, Object> patrolLogList() {
		Map<String, Object> map = MapUtils.newHashMap();
		List<String> inspectionName = new ArrayList<>();
		List<String> inspectionPersonnel = new ArrayList<>();
		List<String> inspectionRecord = new ArrayList<>();
		for(IsPatrolRec isPatrolRec:isPatrolRecService.patrolLogList()){
			inspectionName.add(isPatrolRec.getPatrolName());
			inspectionPersonnel.add(isPatrolRec.getOperator());
			//String remarks = isPatrolRec.getRemarks();
			String record = isPatrolRec.getRecord();
			if (record != null) {
				inspectionRecord.add(record);
			}else {
				inspectionRecord.add("null");
			}
			
		}
		List<String> inspectionNameSet = new ArrayList<>(new HashSet<>(inspectionName));
		List<String> inspectionPersonnelSet = new ArrayList<>(new HashSet<>(inspectionPersonnel));
		List<String> inspectionRecordSet = new ArrayList<>(new HashSet<>(inspectionRecord));
		map.put("inspectionName", inspectionNameSet);
		map.put("inspectionPersonnel", inspectionPersonnelSet);
		map.put("inspectionRecord", inspectionRecordSet);
		return map;
	}
    
    /**
     * （2）Post:
	 * {"inspectionName":"xxx","inspectionPersonnel":"xxx","startTime":"xxx","endTime":"xxx"}
	 * 
	 * 4.2.1.	巡检日志
	Post:   增加分页范围rangeStart、rangeEnd
	{"inspectionName":"xxx","inspectionPersonnel":"xxx","startTime":"xxx","endTime":"xxx","rangeStart":1,"rangeEnd":500}

	
	 *Json:（根据条件请求筛选的数据）
	 *[{"inspectionName": "拆码垛机保养","inspectionRecord": "加快检修","inspectionPersonnel": "兰州烟草","inspectionTime": "2019-05-2 00::00:00 "}]

	修改为：
	Post:     {"inspectionName":"xxx","inspectionPersonnel":"xxx","inspectionRecord":"xxx","startTime":"xxx","endTime":"xxx","rangeStart":1,"rangeEnd":500}

	Json:（根据条件请求筛选的数据）
	[{"inspectionName": "拆码垛机保养","inspectionRecord": "加快检修","inspectionPersonnel": "兰州烟草","inspectionTime": "2019-05-2 00::00:00 ","remarks": "xxx"}]

     * @return
     */
    @RequestMapping(value = "filterPatrolLog")
    public List<Map<String, Object>> filterPatrolLog(HttpServletRequest request) {
    	List<Map<String, Object>> mapList = ListUtils.newArrayList();
    	String inspectionName = request.getParameter("inspectionName");
    	String inspectionPersonnel = request.getParameter("inspectionPersonnel");
    	String inspectionRecord = request.getParameter("inspectionRecord");
    	String startTime = request.getParameter("startTime");
    	String endTime = request.getParameter("endTime");
    	if (startTime != null) {
    		startTime = CompareDate.formatDate(request.getParameter("startTime"), false);
		}  
    	if (endTime != null) {
    		endTime = CompareDate.formatDate(request.getParameter("endTime"), true);
		}
    	//System.err.println(startTime+" "+endTime);    	
    	String rangeStart = request.getParameter("rangeStart");
    	String rangeEnd = request.getParameter("rangeEnd");
    	if (rangeEnd == null || rangeEnd.equals("")) {
    		for(IsPatrolRec isPatrolRec:isPatrolRecService.filterPatrolLog(inspectionName, inspectionPersonnel, startTime, endTime)){
        		Map<String, Object> map = MapUtils.newHashMap();
        		map.put("inspectionName", isPatrolRec.getPatrolName());
            	map.put("inspectionPersonnel", isPatrolRec.getOperator());
            	map.put("inspectionTime", CompareDate.simplifyDate(isPatrolRec.getPatrolTime()));
            	if (isPatrolRec.getRecord() != null) {
            		map.put("inspectionRecord", isPatrolRec.getRecord());
    			}else {
    				map.put("inspectionRecord", "null");
    			}
        		mapList.add(map);
        	}
		}else {
			for(IsPatrolRec isPatrolRec:isPatrolRecService.filterPatrolLogPageRemark(inspectionName, inspectionPersonnel,inspectionRecord, startTime, endTime, Integer.valueOf(rangeStart), Integer.valueOf(rangeEnd))){
	    		Map<String, Object> map = MapUtils.newHashMap();
	    		map.put("inspectionName", isPatrolRec.getPatrolName());
            	map.put("inspectionPersonnel", isPatrolRec.getOperator());
	        	map.put("inspectionTime", CompareDate.simplifyDate(isPatrolRec.getPatrolTime()));
	        	if (isPatrolRec.getRecord() != null) {
	        		map.put("inspectionRecord", isPatrolRec.getRecord());
				}else {
					map.put("inspectionRecord", "null");
				}
	        	if (isPatrolRec.getRemarks() != null) {
	        		map.put("remarks", isPatrolRec.getRemarks());
				}else {
					map.put("remarks", "null");
				}
	    		mapList.add(map);
	    	}
		}    	
    	return mapList;
    }
}
		