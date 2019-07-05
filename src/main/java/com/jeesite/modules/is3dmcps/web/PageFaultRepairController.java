package com.jeesite.modules.is3dmcps.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jeesite.common.collect.MapUtils;
import com.jeesite.modules.is3dmcps.entity.IsDevice;
import com.jeesite.modules.is3dmcps.entity.IsFaults;
import com.jeesite.modules.is3dmcps.entity.IsKnowledge;
import com.jeesite.modules.is3dmcps.entity.IsRepairRec;
import com.jeesite.modules.is3dmcps.service.IsDeviceService;
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
    IsRepairRecService isRepairRecService;
    @Autowired
    IsKnowledgeService isKnowledgeService;
    @Autowired
    IsDeviceService isDeviceService;
    /**
     * 故障及维修的提交
     * @return
     */
	@RequestMapping(value = {"faultSubmit", ""})
	public List<Map<String, Object>> faultSubmit(HttpServletRequest request) {
		List<Map<String, Object>> mapList = ListUtils.newArrayList();
        Map<String, Object> map = MapUtils.newHashMap();
        //String deviceId = request.getParameter("deviceID");
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
    			if (isRepairRecService.getRepairResult(isFaults.getId()).equals("2") || 
    					isRepairRecService.getRepairResult(isFaults.getId()).equals("3")) {
    				state = "Normal";				
    			}else if(isRepairRecService.getRepairResult(isFaults.getId()).equals("0") || 
    					isRepairRecService.getRepairResult(isFaults.getId()).equals("4")) {
    				state = "Fault";
    			}else {
    				state = "Error";				
    			}
    		}   
            if (state.equals("Normal")) {
            	List<Map<String, Object>> info = ListUtils.newArrayList();
    			for(IsKnowledge isKnowledge:isKnowledgeService.getAll()){    
    				//System.err.println(isKnowledge.getTitle());
    				Map<String, Object> infoJson = MapUtils.newHashMap();
    				infoJson.put("recommendedSolution", isKnowledge.getTitle());
    				info.add(infoJson);
    			}
    			map.put("state",state);
                map.put("infoJsonStr",info);
    		}else if (state.equals("Fault")) {						
    			for(IsKnowledge isKnowledge:isKnowledgeService.getKnowledgeById(isFaults.getKnowledgeId())){
    				faultId = isFaults.getId();
    				faultResult = isFaults.getReason();
    				infoJsonStr.put("faultID", faultId);
    				infoJsonStr.put("faultResult", faultResult);
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
     */
    @RequestMapping(value = {"postFault", ""})
    public Map<String,Object> postFault(HttpServletRequest request){
        //String deviceID=request.getParameter("deviceID");
        String deviceName=request.getParameter("deviceName");
        String persion=request.getParameter("person");
        String faultResult=request.getParameter("faultResult");
        String recommendedSolution=request.getParameter("recommendedSolution");
        //String faultID=request.getParameter("faultID");       
        String state=request.getParameter("state");
        String content=request.getParameter("content");
        //System.out.println(deviceID+deviceName+persion+state+content);
        Date date=new Date();
        Map<String,Object> map=new HashMap<>();
        if(state==null){
        	for(IsDevice isDevice:isDeviceService.getDeviceByDeviceNo(deviceName)){
	            IsFaults isFaults=new IsFaults(deviceName,isDevice.getId(),deviceName,null,date,persion,faultResult,recommendedSolution);
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
                state="2";
            }else{
                state="0";
            }
            for(IsFaults isFaults:isFaultsService.getFaultsByName(faultResult)){
	            String faultID=isFaults.getId();
	            IsRepairRec isRepairRec=new IsRepairRec(faultID,faultResult,content,state,persion,date);
	            try{
	                isRepairRecService.save(isRepairRec);
	            }catch(Exception exception){
	                map.put("result",exception.toString());
	                return map;
	            }
	            map.put("result","ok");	            
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
     * 故障需要维修的设备
     */
    @RequestMapping(value = {"needRepair", ""})
    public List<Map<String, Object>> needRepair() {
    	List<Map<String, Object>> mapList = ListUtils.newArrayList();
    	for(IsFaults isFaults:isFaultsService.getNeedRepair()){
    		for(IsDevice isDevice:isDeviceService.getDeviceById(isFaults.getDeviceId())){
	    		Map<String, Object> map = MapUtils.newHashMap();
	    		map.put("deviceName", isDevice.getDeviceNo());
	    		mapList.add(map);
    		}
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
			for(IsDevice isDevice:isDeviceService.getDeviceById(isFaults.getDeviceId())){
				map.put("deviceName", isDevice.getDeviceCodeName());
				map.put("deviceNumber", isDevice.getDeviceNo());
			}
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
}
	
	