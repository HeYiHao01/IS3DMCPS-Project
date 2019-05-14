package com.jeesite.modules.is3dmcps.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jeesite.common.collect.MapUtils;
import com.jeesite.modules.is3dmcps.entity.IsPatrol;
import com.jeesite.modules.is3dmcps.entity.IsPatrolRec;
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
        String deviceId = request.getParameter("deviceID");
		String patrolID;
        String patrolName;
        String patrolContent;
		for(IsPatrol isPatrol : isPatrolService.getPatrolByDeviceId(deviceId)){
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
     * {“deviceID”:”xxx”,” patrolID”:”xxx”,” patrolPersion”:”xxx”,”state”:true,”remark”:”xxxxxxxxxxxx”}
     * @return
     */
    @RequestMapping(value = {"postPatrol", ""})
    public Map<String,Object> postPatrol(HttpServletRequest request){
        String deviceID=request.getParameter("deviceID");
        String patrolID=request.getParameter("patrolID");
        String patrolPersion=request.getParameter("patrolPersion");
        String state=request.getParameter("state");
        String remark=request.getParameter("remark");
        System.out.println(deviceID+patrolID+patrolPersion+state+remark);
        Date date=new Date();
        Map<String,Object> map=new HashMap<>();
        try{
            String patrolName=isPatrolService.get(patrolID).getName();
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
     * 需要进行人工巡检的设备
     */
    @RequestMapping(value = {"needPatrol", ""})
    public List<Map<String, Object>> needPatrol() {
    	List<Map<String, Object>> mapList = ListUtils.newArrayList();
    	for(IsPatrol isPatrol:isPatrolService.getNeedPatrol()){
    		Map<String, Object> map = MapUtils.newHashMap();
    		map.put("deviceName", isPatrol.getDeviceName());
    		mapList.add(map);
    	}
    	return mapList;
    }
}
	
	