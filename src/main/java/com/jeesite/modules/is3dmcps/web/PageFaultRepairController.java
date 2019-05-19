package com.jeesite.modules.is3dmcps.web;

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
    			for(IsKnowledge isKnowledge:isKnowledgeService.getAll()){
    				infoJsonStr.put("recommendedSolution", isKnowledge.getTitle());
    			}			
    		}else if (state.equals("Fault")) {						
    			for(IsKnowledge isKnowledge:isKnowledgeService.getKnowledgeById(isFaults.getKnowledgeId())){
    				faultId = isFaults.getId();
    				faultResult = isFaults.getReason();
    				infoJsonStr.put("faultID", faultId);
    				infoJsonStr.put("faultResult", faultResult);
    				infoJsonStr.put("recommendedSolution", isKnowledge.getTitle());
    				infoJsonStr.put("recommendedSolutionContent", isKnowledge.getContent());
    			}
    		}else {			
    		}     
            map.put("state",state);
            map.put("infoJsonStr",infoJsonStr);
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
}
	
	