package com.jeesite.modules.is3dmcps.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jeesite.common.collect.MapUtils;
import com.jeesite.modules.is3dmcps.entity.IsDevice;
import com.jeesite.modules.is3dmcps.entity.IsMaintain;
import com.jeesite.modules.is3dmcps.entity.IsMaintainRec;
import com.jeesite.modules.is3dmcps.service.IsDeviceService;
import com.jeesite.modules.is3dmcps.service.IsMaintainRecService;
import com.jeesite.modules.is3dmcps.service.IsMaintainService;
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
 *维护保养
 */
@CrossOrigin
@RestController
@RequestMapping(value = "static")
public class PageMaintainController extends BaseController{
    @Autowired
    IsMaintainRecService isMaintainRecService;
    @Autowired
	IsMaintainService isMaintainService;
    @Autowired
    IsDeviceService isDeviceService;
	/**
	 * 保养弹窗
	 * @return
	 */
	@RequestMapping(value = {"maintainPop", ""})
	public List<Map<String, Object>> maintainPop(HttpServletRequest request) {
		List<Map<String, Object>> mapList = ListUtils.newArrayList();		
		String deviceId  = request.getParameter("deviceID");
		String maintainID;
		String maintainName;
		String maintainContent;	
		for(IsDevice isDevice:isDeviceService.getDeviceById(deviceId)){
			for (IsMaintain maintain : isMaintainService.getMaintainPopContent(isDevice.getDeviceCodeId())){
				Map<String, Object> map = MapUtils.newHashMap();
				maintainID = maintain.getId();						
				maintainContent = maintain.getContent();
				maintainName = maintain.getName();
				//System.out.println(maintain.getId()+" "+maintain.getName()+" "+maintain.getContent());
				map.put("maintainID", maintainID);
				map.put("maintainName", maintainName);
				map.put("maintainContent", maintainContent);
				mapList.add(map);
			}
		}								
		return mapList;
	}

    /**
     * 保养录入
     * Post(Put):
     * {“deviceID”:”xxx”,” maintainID”:”xxx”,”miantainPersion”:”xxx”,”state”:true,”remark”:”xxxxxxxxxxxx”}
     * @return
     */
    @RequestMapping(value = {"postMaintain", ""})
    public Map<String,Object> postMaintain(HttpServletRequest request){
        String deviceID=request.getParameter("deviceID");
        String maintainID=request.getParameter("maintainID");
        String maintainPersion=request.getParameter("maintainPersion");
        String state=request.getParameter("state");
        if(state.equals("true")){
        	state="1";
		}else{
        	state="0";
		}
        String remark=request.getParameter("remark");
		System.out.println(deviceID+maintainID+maintainPersion+state+remark);
        Date date=new Date();
		Map<String,Object> map=new HashMap<>();
        try{
			String maintainName=isMaintainService.get(maintainID).getName();
			IsMaintainRec isMaintainRec=new IsMaintainRec(maintainID,maintainName,deviceID,null,null,remark,maintainPersion,date,state);
            isMaintainRecService.save(isMaintainRec);
        }catch(Exception exception){
			map.put("result",exception.toString());
			return map;
		}
		map.put("result","ok");
        return map;
    }

	/**
	 * 七天维保
	 * 注意传入的date字符串格式为“yyyy.mm.dd”
	 * @return
	 */
	@RequestMapping(value = {"sevenMaintain", ""})
	public List<Map<String, Object>> sevenMaintain() {
		List<Map<String, Object>> mapList = ListUtils.newArrayList();
		int maintainPlanCount;
		int finishCount;		
		for(String date:CompareDate.getSevenDate())	{	
			Map<String, Object> map = MapUtils.newHashMap();
			System.out.println(date);
			maintainPlanCount=isMaintainRecService.getMaintainPlanCount(date);
			finishCount=isMaintainRecService.getFinishCount(date);
			map.put("date",CompareDate.simplifyDate(date));
			System.out.println(date);
			map.put("maintainPlanCount",maintainPlanCount);
			map.put("finishCount",finishCount);
			mapList.add(map);
		}
		return mapList;
	}
}
	
	