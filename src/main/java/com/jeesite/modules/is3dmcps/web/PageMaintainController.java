package com.jeesite.modules.is3dmcps.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import com.jeesite.common.collect.MapUtils;
import com.jeesite.modules.is3dmcps.entity.IsDevice;
import com.jeesite.modules.is3dmcps.entity.IsMaintain;
import com.jeesite.modules.is3dmcps.entity.IsMaintainRec;
import com.jeesite.modules.is3dmcps.entity.MaintainPersonInfo;
import com.jeesite.modules.is3dmcps.service.IsDeviceService;
import com.jeesite.modules.is3dmcps.service.IsMaintainRecService;
import com.jeesite.modules.is3dmcps.service.IsMaintainService;
import com.jeesite.modules.is3dmcps.service.SelfDefEmployeeService;
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
    @Autowired
    IsDeviceService isDeviceCodeService;
    @Autowired
    SelfDefEmployeeService selfDefEmployeeService;
	/**
	 * 保养弹窗
	 * @return
	 */
	@RequestMapping(value = {"maintainPop", ""})
	public List<Map<String, Object>> maintainPop(HttpServletRequest request) {
		List<Map<String, Object>> mapList = ListUtils.newArrayList();				
		String deviceName  = request.getParameter("deviceName");
		String maintainID;
		String maintainName;
		String maintainContent;	
		for(IsDevice isDevice:isDeviceService.getDeviceByDeviceNo(deviceName)){
			for (IsMaintainRec maintainRec : isMaintainRecService.getMaintainPop(isDevice.getDeviceNo())){
				Map<String, Object> map = MapUtils.newHashMap();
				maintainID = maintainRec.getMaintainId();	
				if(isMaintainService.getById(maintainRec.getMaintainId()) != null)
					maintainContent = isMaintainService.getById(maintainRec.getMaintainId()).getContent();
				else
					maintainContent = "";
				maintainName = maintainRec.getMaintainName();				
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
    	String deviceName  = request.getParameter("deviceName");
        String maintainPersion=request.getParameter("maintainPerson");
        String state=request.getParameter("state");
        String remark=request.getParameter("remark");		
        Date date=new Date();
        if(state.equals("true")){
        	state="1";	//保养记录
        	Map<String,Object> map=new HashMap<>();
            try{
            	String deviceCodeId = "";
            	for(IsDevice isDevice: isDeviceService.getDeviceByDeviceNo(deviceName))
            		deviceCodeId = isDevice.getDeviceCodeId();        	
    			String maintainID=isMaintainService.getByCodeId(deviceCodeId).getId();    			
    			//更新维保计划为维保记录
    			isMaintainRecService.updateMaintainRec(maintainID, deviceName, remark, maintainPersion, date, state);
            }catch(Exception exception){
    			map.put("result",exception.toString());
    			return map;
    		}
    		map.put("result","ok");
            return map;
		}else{
        	state="0";	//保养计划
        	Map<String,Object> map=new HashMap<>();
            try{
            	String deviceCodeId = "";
            	for(IsDevice isDevice: isDeviceService.getDeviceByDeviceNo(deviceName))
            		deviceCodeId = isDevice.getDeviceCodeId();        	
    			String maintainID=isMaintainService.getByCodeId(deviceCodeId).getId();
    			String maintainName = isMaintainService.getByCodeId(deviceCodeId).getName();
    			IsMaintainRec isMaintainRec=new IsMaintainRec(maintainID,maintainName,deviceName,maintainPersion,date,remark,null,null,state);			
                isMaintainRecService.save(isMaintainRec);
            }catch(Exception exception){
    			map.put("result",exception.toString());
    			return map;
    		}
    		map.put("result","ok");
            return map;
		}                		
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
	
	/**
	 * 一个月内的维保情况统计
	 * @return
	 */
	@RequestMapping(value = {"monthMaintain", ""})
	public List<Map<String, Object>> monthMaintain() {
		List<Map<String, Object>> mapList = ListUtils.newArrayList();
		int maintainPlanCount;
		int finishCount;		
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM");		
		String date = sdf.format(today);
		Map<String, Object> map = MapUtils.newHashMap();
		System.out.println(date);		
		finishCount = isMaintainRecService.getFinishCountMonth(date);
		maintainPlanCount = isMaintainRecService.getMaintainPlanCountMonth(date) + finishCount;
		System.out.println(date);
		map.put("maintainPlanCount", maintainPlanCount);
		map.put("finishCount", finishCount);
		mapList.add(map);
		return mapList;
	}
	
	/**
	 * 设备维保记录展示与查询
	 * Json:
	 *[{"maintenanceName": "拆码垛机保养","deviceNumber": "cmd1","planners": "兰州烟草","plannedMaintenanceTime": "2019-05-2 00::00:00 ","maintenancePersonnel": "兰州烟草","maintenanceTime": "2019-05-21 01:00:00","status": "保养计划"}]
	 * @return
	 */
	@RequestMapping(value = {"maintainList",""})
	public List<Map<String, Object>> maintainList() {
		List<Map<String, Object>> mapList = ListUtils.newArrayList();
		for(IsMaintainRec isMaintainRec:isMaintainRecService.maintainList()){
			Map<String, Object> map = MapUtils.newHashMap();
			map.put("maintenanceName", isMaintainRec.getMaintainName());
			map.put("deviceNumber", isMaintainRec.getDeviceNo());
			map.put("planners", isMaintainRec.getPlanPerson());
			//map.put("plannedMaintenanceTime", isMaintainRec.getPlanDate());
			map.put("plannedMaintenanceTime", String.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(isMaintainRec.getPlanDate())));
			map.put("maintenancePersonnel", isMaintainRec.getMaintainPerson());
			//map.put("maintenanceTime", isMaintainRec.getMaintainTime());
			map.put("maintenanceTime", String.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(isMaintainRec.getMaintainTime())));
			map.put("status", isMaintainRec.getStatus());
			mapList.add(map);
		}
		return mapList;
	}
	
	/**
	 * 需要进行维护保养的设备
	 */
	@RequestMapping(value = {"needMaintain", ""})
	public List<Map<String, Object>> needMaintain() {
		List<Map<String, Object>> mapList = ListUtils.newArrayList();
		for(IsMaintainRec isMaintainRec:isMaintainRecService.need_maintain_details()){
			for(IsDevice device:isDeviceService.getDeviceByDeviceNo(isMaintainRec.getDeviceNo())){
				Map<String, Object> map = MapUtils.newHashMap();				
				map.put("deviceName", device.getDeviceNo());
				mapList.add(map);
			}			
		}		
		return mapList;
	}
	
	/**
	 * 维保计划情况
	 */
	
	/**
	 * 维保计划统计
	 * Post: 
	 *{”timeDomainYear”:2019,”timeDomainMonth”:7}
	 *Json：
	 *[{"timeVariable":"xxx","planNumber":"xxx"},{"timeVariable":"xxx","planNumber":"xxx"}]
	 * @param request
	 * @return
	 */
	@RequestMapping(value = {"planNumberCount",""})
	public List<Map<String, Object>> planNumberCount(HttpServletRequest request) {
		List<Map<String, Object>> mapList = ListUtils.newArrayList();
		String timeDomainYear = request.getParameter("timeDomainYear");
		String timeDomainMonth = request.getParameter("timeDomainMonth");
		for(int i=1;i<32;i++){
			Map<String, Object> map = MapUtils.newHashMap();
			String date = new String();
			date += timeDomainYear+".";
			if (Integer.parseInt(timeDomainMonth) < 10) {
				date += "0"+timeDomainMonth+".";				
			}else {
				date += timeDomainMonth+".";				
			}	
			if(i<10){
				date += "0"+i; 				
			}else {
				date += i+"";
			}
			int timeVariable = i;
			int planNumber = isMaintainRecService.getMaintainPlanCount(date);
			map.put("timeVariable", timeVariable);
			map.put("planNumber", planNumber);
			mapList.add(map);
		}
		return mapList;	
	}
	
	/**
	 * （1）计划筛选
		Json：（默认给到所有的筛选条件）
		{"maintenanceName": ["xxx", "xxx", "xxx"],"deviceNumber": ["xxx", "xxx", "xxx"],"planners": ["xxx", "xxx", "xxx"]}
		
		planners改成MaintainPerson（执行人）
	 * @return
	 */
	@RequestMapping(value = "maintenanceNameList")
	public Map<String, Object> maintenanceNameList() {
		Map<String, Object> map = MapUtils.newHashMap();
		List<String> maintenanceName = new ArrayList<>();
		List<String> deviceNumber = new ArrayList<>();
		List<String> planners = new ArrayList<>();
		for(IsMaintainRec isMaintainRec:isMaintainRecService.need_maintain_details()){
			maintenanceName.add(isMaintainRec.getMaintainName());
			deviceNumber.add(isMaintainRec.getDeviceNo());
			//planners.add(isMaintainRec.getPlanPerson());
			planners.add(isMaintainRec.getMaintainPerson());
		}
		List<String> maintenanceNameSet = new ArrayList<>(new HashSet<>(maintenanceName));
		List<String> deviceNumberSet = new ArrayList<>(new HashSet<>(deviceNumber));
		List<String> plannersSet = new ArrayList<>(new HashSet<>(planners));
		map.put("maintenanceName", maintenanceNameSet);
		map.put("deviceNumber", deviceNumberSet);
		map.put("planners", plannersSet);
		return map;
	}
	
	/**
	 * Post:
	 * {"maintenanceName":"xxx","deviceNumber":"xxx","planners":"xxx"}	
	 * Json:（根据条件请求筛选的数据）
	 *[{"maintenanceName": "拆码垛机保养","deviceNumber": "cmd1","planners": "兰州烟草","plannedMaintenanceTime": "2019-05-2 00::00:00 "}]
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "filterMaintainRec")
	public List<Map<String, Object>> filterMaintainRec(HttpServletRequest request) {
		List<Map<String, Object>> mapList = ListUtils.newArrayList();
		for(IsMaintainRec isMaintainRec:isMaintainRecService.filterMaintainRec(request.getParameter("maintenanceName"), request.getParameter("deviceNumber"), request.getParameter("planners"))){
			Map<String, Object> map = MapUtils.newHashMap();
			String maintenanceName = isMaintainRec.getMaintainName();
			String deviceNumber = isMaintainRec.getDeviceNo();
			//String planners = isMaintainRec.getPlanPerson();
			String planners = isMaintainRec.getMaintainPerson();
			String plannedMaintenanceTime = CompareDate.simplifyDate(isMaintainRec.getPlanDate());
			map.put("maintenanceName", maintenanceName);
			map.put("deviceNumber", deviceNumber);
			map.put("planners", planners);
			map.put("plannedMaintenanceTime", plannedMaintenanceTime);
			mapList.add(map);
		}
		return mapList;
	}
	
	/**
	 * 维保值班情况
	 * Json：
	 * [{"name":"xxx","deviceTotalNumber":100,"maintenanceDeviceNumber":45,"maintenanceFinishNumber":45,"score":"96"},{"name":"xxx","deviceTotalNumber":100,"maintenanceDeviceNumber":45,"maintenanceFinishNumber":45,"score":"96"}	 * @return
	 */
	@RequestMapping(value = "maintainDutyInfo")
	public List<Map<String, Object>> maintainDutyInfo() {	
		List<Map<String, Object>> mapListAll = ListUtils.newArrayList();
		for(MaintainPersonInfo maintainPersonInfo:isMaintainRecService.getMaintainPersonAll()){
			Map<String, Object> map = MapUtils.newHashMap();
			String name = maintainPersonInfo.getMaintainPerson();
			int deviceTotalNumber = 0;
			int maintenanceDeviceNumber = 0;
			int maintenanceFinishNumber = 0;
			map.put("name", name);						
			MaintainPersonInfo maintainPersonInfoPlan = isMaintainRecService.getMaintainPersonPlanByName(name);			
			if (maintainPersonInfoPlan != null) {
				String namePlan = maintainPersonInfoPlan.getMaintainPerson();
				maintenanceDeviceNumber = maintainPersonInfoPlan.getCountNum();
				map.put("name", namePlan);
				map.put("maintenanceDeviceNumber", maintenanceDeviceNumber);
			}else {
				String namePlan = name;
				maintenanceDeviceNumber = 0;
				map.put("name", namePlan);
				map.put("maintenanceDeviceNumber", maintenanceDeviceNumber);
			}			
			MaintainPersonInfo maintainPersonInfoFinish = isMaintainRecService.getMaintainPersonFinishByName(name);
			if (maintainPersonInfoFinish != null) {
				String nameFinish = maintainPersonInfoFinish.getMaintainPerson();
				maintenanceFinishNumber = maintainPersonInfoFinish.getCountNum();
				map.put("name", nameFinish);
				map.put("maintenanceFinishNumber", maintenanceFinishNumber);
			}else {
				String nameFinish = name;
				maintenanceFinishNumber = 0;
				map.put("name", nameFinish);
				map.put("maintenanceFinishNumber", maintenanceFinishNumber);
			}	
			String jobNumber = selfDefEmployeeService.getEmployeeByName(name).getEmpCode();
			deviceTotalNumber = maintenanceDeviceNumber + maintenanceFinishNumber;
			map.put("jobNumber", jobNumber);
			map.put("deviceTotalNumber", deviceTotalNumber);
			mapListAll.add(map);
		}
		return mapListAll;
	}
	
	/**
	 * 昨天计划完成情况
	 * Json:
	 * {"finishNumber":70"unFinishNumber":30}
	 * @return
	 */
	@RequestMapping(value={"finishNumberCount",""})
	public Map<String, Object> finishNumberCount() {
		Map<String, Object> map = MapUtils.newHashMap();
		Calendar calendar = Calendar.getInstance();			
		String date = calendar.get(calendar.YEAR)+"."+CompareDate.getPastDate(1);
		int finishNumber = isMaintainRecService.getFinishCount(date);
		int unFinishNumber = isMaintainRecService.getMaintainPlanCount(date) - finishNumber;
		map.put("finishNumber", finishNumber);
		map.put("unFinishNumber", unFinishNumber);
		return map;
	}
	
	/**
	 * 维保种类统计
	 * Json：
	 * [{"name":"xxx","typeNumber":920,"totalNumber":1000},{"name":"xxx","typeNumber":920,"totalNumber":1000}]
	 * @return
	 */
	@RequestMapping(value = {"maintainTypeCount",""})
	public List<Map<String, Object>> maintainTypeCount() {
		List<Map<String, Object>> mapList = ListUtils.newArrayList();
		int totalCount = 0;
		for(IsMaintain isMaintain:isMaintainService.maintainTypeCount()){
			totalCount += isMaintain.getInterval();			
		}
		String name = "";
		int typeNumber = 0;
		for(IsMaintain isMaintain:isMaintainService.maintainTypeCount()){
			Map<String, Object> map = MapUtils.newHashMap();			
			switch (isMaintain.getType()) {			
			case "1":
				name = "清洁";
				typeNumber = isMaintain.getInterval();
				map.put("name", name);
				map.put("typeNumber", typeNumber);
				map.put("totalCount", totalCount);
				mapList.add(map);
				break;
			case "2":
				name = "润滑";
				typeNumber = isMaintain.getInterval();
				map.put("name", name);
				map.put("typeNumber", typeNumber);
				map.put("totalCount", totalCount);
				mapList.add(map);
				break;
			case "3":
				name = "紧固";
				typeNumber = isMaintain.getInterval();
				map.put("name", name);
				map.put("typeNumber", typeNumber);
				map.put("totalCount", totalCount);
				mapList.add(map);
				break;
			case "9":
				name = "更换备件";
				typeNumber = isMaintain.getInterval();
				map.put("name", name);
				map.put("typeNumber", typeNumber);
				map.put("totalCount", totalCount);
				mapList.add(map);
				break;
			default:
				name = "其他";
				typeNumber = isMaintain.getInterval();
				map.put("name", name);
				map.put("typeNumber", typeNumber);
				map.put("totalCount", totalCount);
				mapList.add(map);
				break;
			}
		}
		return mapList;
	}
	
	/**
     * 维保记录条件筛选
     * （1）Json：（默认给到所有的筛选条件）
	{"maintenanceName": ["智能双向穿梭车保养", "提升机保养", "拆码垛机保养"],"deviceNumber": ["upaler001", "Car1", "cmd1"] ,"maintenancePersonnel": ["xxx", "xxx", "xxx"],"status": ["xxx", "xxx", "xxx"]}

     * @return
     */
	@RequestMapping(value = "maintenanceRecNameList")
	public Map<String, Object> maintenanceRecNameList() {
		Map<String, Object> map = MapUtils.newHashMap();
		List<String> maintenanceName = new ArrayList<>();
		List<String> deviceNumber = new ArrayList<>();
		List<String> maintenancePersonnel = new ArrayList<>();
		List<String> status = new ArrayList<>();
		for(IsMaintainRec isMaintainRec:isMaintainRecService.maintainList()){
			maintenanceName.add(isMaintainRec.getMaintainName());
			deviceNumber.add(isMaintainRec.getDeviceNo());
			maintenancePersonnel.add(isMaintainRec.getPlanPerson());
			if (isMaintainRec.getStatus().equals("0")) {
				status.add("维保计划");
			}else if (isMaintainRec.getStatus().equals("1")) {
				status.add("维保记录");
			}else {
				status.add("Unknown");
			}			
		}
		List<String> maintenanceNameSet = new ArrayList<>(new HashSet<>(maintenanceName));
		List<String> deviceNumberSet = new ArrayList<>(new HashSet<>(deviceNumber));
		List<String> maintenancePersonnelSet = new ArrayList<>(new HashSet<>(maintenancePersonnel));
		List<String> statusSet = new ArrayList<>(new HashSet<>(status));
		map.put("maintenanceName", maintenanceNameSet);
		map.put("deviceNumber", deviceNumberSet);
		map.put("maintenancePersonnel", maintenancePersonnelSet);
		map.put("status", statusSet);
		return map;
	}
	
	/**
	 * 4.1.	(2)设备维保增加维保日志界面
	 * Post：
	{"maintenanceName":"xxx","deviceNumber":"xxx","maintenancePersonnel":"xxx",status="xxx","startTime":"xxx","endTime":"xxx","rangeStart":1,"rangeEnd":500}
	Json:
	[{"maintenanceName": "拆码垛机保养","deviceNumber": "cmd1","planners": "兰州烟草","plannedMaintenanceTime": "2019-05-2 00::00:00 ","maintenancePersonnel": "兰州烟草","maintenanceTime": "2019-05-21 01:00:00","status": "保养计划"}]

	 */
	@RequestMapping(value = "filterMaintainRecLog")
	public List<Map<String, Object>> filterMaintainRecLog(HttpServletRequest request) {
		List<Map<String, Object>> mapList = ListUtils.newArrayList();
		String status;
		status = request.getParameter("status");
		if (status != null) {
			if (status.equals("维保计划")) {
				status = "0";
			}else if (status.equals("维保记录")) {
				status = "1";
			}else {
				status = null;
			}	
		}
		String startTime = CompareDate.formatDate(request.getParameter("startTime"), false);
		String endTime = CompareDate.formatDate(request.getParameter("endTime"), true);
		for(IsMaintainRec isMaintainRec:isMaintainRecService.filterMaintainRecPage(request.getParameter("maintenanceName"), request.getParameter("deviceNumber"), request.getParameter("maintenancePersonnel"), status, startTime, endTime, Integer.valueOf(request.getParameter("rangeStart")), Integer.valueOf(request.getParameter("rangeEnd")))){
			Map<String, Object> map = MapUtils.newHashMap();
			String maintenanceName = isMaintainRec.getMaintainName();
			String deviceNumber = isMaintainRec.getDeviceNo();
			String planners = isMaintainRec.getPlanPerson();
			String plannedMaintenanceTime = CompareDate.simplifyDate(isMaintainRec.getPlanDate());
			String maintenancePersonnel = isMaintainRec.getMaintainPerson();
			String maintenanceTime = CompareDate.simplifyDate(isMaintainRec.getMaintainTime());
			String status1 = isMaintainRec.getStatus().equals("0")?"保养计划":"保养记录";
			String remarks = isMaintainRec.getRemarks();
			String record = isMaintainRec.getRecord();
			map.put("maintenanceName", maintenanceName);
			map.put("deviceNumber", deviceNumber);
			map.put("planners", planners);
			map.put("plannedMaintenanceTime", plannedMaintenanceTime);
			map.put("maintenancePersonnel", maintenancePersonnel);
			map.put("maintenanceTime", maintenanceTime);
			map.put("status", status1);
			if (remarks != null) {
				map.put("remarks", remarks);
			}else {
				map.put("remarks", "null");
			}
			if (record != null) {
				map.put("record", record);
			}else {
				map.put("record", "null");
			}
			mapList.add(map);
		}
		return mapList;
	}
	
}
		