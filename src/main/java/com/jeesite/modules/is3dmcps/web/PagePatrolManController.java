package com.jeesite.modules.is3dmcps.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jeesite.common.collect.MapUtils;
import com.jeesite.modules.is3dmcps.entity.IsDevice;
import com.jeesite.modules.is3dmcps.entity.IsPatrol;
import com.jeesite.modules.is3dmcps.entity.IsPatrolRec;
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
     * @return
     */
    @RequestMapping(value = {"postPatrol", ""})
    public Map<String,Object> postPatrol(HttpServletRequest request){
        //String deviceID=request.getParameter("deviceID");
        String deviceName  = request.getParameter("deviceName");
        //String patrolID=request.getParameter("patrolID");
        String patrolName=request.getParameter("patrolName");
        String patrolPersion=request.getParameter("patrolPerson");
        String state=request.getParameter("state");
        String remark=request.getParameter("remark");
        System.out.println(deviceName+patrolName+patrolPersion+state+remark);
        Date date=new Date();
        Map<String,Object> map=new HashMap<>();
        try{
        	String patrolID = "";
        	for(IsPatrol isPatrol:isPatrolService.getPatrolByName(deviceName))
        		patrolID=isPatrol.getId();
            IsPatrolRec isPatrolRec=new IsPatrolRec(patrolID,patrolName,remark,patrolPersion,date);
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
			for (IsDevice isDevice : isDeviceService.getDeviceById(isPatrol.getDeviceId())) {
				Map<String, Object> map = MapUtils.newHashMap();
				map.put("deviceName", isDevice.getDeviceNo());
				mapList.add(map);
			}
		}
    	return mapList;
    }
}
	
	