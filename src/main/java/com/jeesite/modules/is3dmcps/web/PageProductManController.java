package com.jeesite.modules.is3dmcps.web;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.jeesite.modules.is3dmcps.entity.*;
import com.jeesite.modules.is3dmcps.service.*;
import com.jeesite.modules.twms.entity.TwmsPltitem;
import com.jeesite.modules.twms.entity.TwmsTransferlogg;
import com.jeesite.modules.twms.entity.WmsGdxdIn;
import com.jeesite.modules.twms.entity.WmsGdxdOut;
import com.jeesite.modules.twms.service.TwmsPltitemService;
import com.jeesite.modules.twms.service.TwmsTransferloggService;
import com.jeesite.modules.twms.service.WmsGdxdInService;
import com.jeesite.modules.twms.service.WmsGdxdOutService;
import com.jeesite.utils.CompareDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jeesite.common.collect.ListUtils;
import com.jeesite.common.collect.MapUtils;
import com.jeesite.common.web.BaseController;

/**
 *
 * @author ZX
 *生产管理主界面
 */
@CrossOrigin
@RestController
@RequestMapping(value = "static")
public class PageProductManController extends BaseController{
    @Autowired
    private IsDeviceService isDeviceService;
    @Autowired
    private IsDeviceCodeService isDeviceCodeService;
    @Autowired
    private IsMaintainRecService isMaintainRecService;
    @Autowired
    private IsPatrolRecService isPatrolRecService;
    @Autowired
    private IsMaintainService isMaintainService;
    @Autowired
    private IsPatrolService isPatrolService;
    @Autowired
    private IsFaultsService isFaultsService;
    @Autowired
    private IsFaultsInfoService isFaultsInfoService;
    @Autowired
    private TwmsPltitemService twmsPltitemService;
    @Autowired
    private WmsGdxdInService wmsGdxdInService;
    @Autowired
    private WmsGdxdOutService wmsGdxdOutService;
    @Autowired
    private TwmsTransferloggService twmsTransferloggService;

    /**
     * 设备运行数量；设备故障数量；维修设备数量；
     * @return
     * Json:
     * {“deviceRuningCount”:210,”deviceFaultsCount”:4,”deviceRepairCount”:2}
     * 具体三个数量需计算出
     */
    @RequestMapping(value = {"totalQuantity", ""})
    public Map<String, Object> totalQuantity() {
        Map<String, Object> map = MapUtils.newHashMap();
        int deviceRuningCount;
        int deviceFaultsCount;
        int deviceRepairCount;
        deviceRuningCount=isDeviceService.getAll()-isFaultsService.getAllFaultsCount();
        deviceFaultsCount=isFaultsService.getAllFaultsCount();
        deviceRepairCount=isMaintainRecService.need_maintain();
        map.put("deviceRuningCount",deviceRuningCount);
        map.put("deviceFaultsCount",deviceFaultsCount);
        map.put("deviceRepairCount",deviceRepairCount);
        return map;
    }

    /**
     * 设备故障提醒；设备保养提醒；设备巡检提醒
     * @return
     * Json:
     * [{“noticeType”:”Patrol”,”noticeTime”:”13:12”,”noticeDescribe”:”设备XX到达固定巡检时间”},
     * {“noticeType”:”Maintain”,”noticeTime”:”14:12”,”noticeDescribe”:”设备XX需要更换零件”},
     * {“noticeType”:”Fault”,”noticeTime”:”15:12”,”noticeDescribe”:”设备XX发现故障”}]
     * 这里的时间是消息内容应该是根据间隔时间和上次巡查的时间算出
     */
    @RequestMapping(value = {"deviceAlert", ""})
    public List<Map<String, Object>> deviceAlert() {
        List<Map<String, Object>> mapList = ListUtils.newArrayList();
        List<IsMaintainRec> maintains = isMaintainRecService.need_maintain_details();
        List<IsPatrol> Patrols = isPatrolService.findList(new IsPatrol());
        List<IsFaults> Faults = isFaultsService.getFaultsDetails();
        List<IsPatrol> patrols=isPatrolService.getPatrol();
        Date curTime=new Date();// new Date()为获取当前系统时间
        if(Patrols != null && Patrols.size()>= 1){
            Map<String, Object> map = MapUtils.newHashMap();
            Date noticeTime;//应该巡检时间
            String noticeDescribe;
            String deviceName;
            Date LastTime;//上次巡检时间
            //设置日期格式

            for(IsPatrol isPatrol:patrols){
                LastTime=isPatrolRecService.getLastTime(isPatrol.getId());
                System.out.println("lasttime"+LastTime);
                deviceName=isPatrol.getName();
                if(LastTime==null){
                    Date useDate=curTime;
                    noticeTime=CompareDate.getTargetDate(useDate,isPatrol.getInterval());
                    System.out.println("noticeTime"+noticeTime);
                }else{
                    Calendar c = Calendar.getInstance();
                    c.setTime(LastTime);
                    System.out.println("useDate"+LastTime);
                    c.add(Calendar.DAY_OF_MONTH,isPatrol.getInterval());
                    noticeTime=c.getTime();
                    System.out.println("useDate"+noticeTime);
                }

                if(noticeTime.getTime()<=curTime.getTime()){
                    noticeDescribe="巡检点"+deviceName+"到达固定巡检时间";
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    map.put("noticeTitle",NoticeItemTypeToTitle("Maintain"));
                    map.put("noticeColor",NoticeItemTypeToColor("Maintain"));
                    map.put("noticeTime",df.format(noticeTime));
                    map.put("noticeDescribe",noticeDescribe);
                    mapList.add(map);
                }
            }
        }else{
            Map<String, Object> map = MapUtils.newHashMap();
            map.put("noticeTitle",NoticeItemTypeToTitle("Maintain"));
            map.put("noticeColor",NoticeItemTypeToColor("Maintain"));
            map.put("noticeTime","");
            map.put("noticeDescribe","");
            mapList.add(map);
        }
        if( maintains!= null && maintains.size()>= 1){
            Map<String, Object> map = MapUtils.newHashMap();
            String noticeTime;
            String noticeDescribe;
            String deviceName;
            for(IsMaintainRec isMaintainrec:maintains){
                if(isMaintainrec.getPlanDate().getTime()<=curTime.getTime()){
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    noticeTime=df.format(isMaintainrec.getPlanDate());
                    deviceName=isMaintainrec.getDeviceNo();
                    noticeDescribe="设备"+deviceName+"需要更换零件";
                    map.put("noticeTitle",NoticeItemTypeToTitle("Patrol"));
                    map.put("noticeColor",NoticeItemTypeToColor("Patrol"));
                    map.put("noticeTime",noticeTime);
                    map.put("noticeDescribe",noticeDescribe);
                    mapList.add(map);
                }
            }

        }else{
            Map<String, Object> map = MapUtils.newHashMap();
            map.put("noticeTitle",NoticeItemTypeToTitle("Patrol"));
            map.put("noticeColor",NoticeItemTypeToColor("Patrol"));
            map.put("noticeTime","");
            map.put("noticeDescribe","");
            mapList.add(map);
        }
        if(Faults != null && Faults.size()>= 1){
            Map<String, Object> map = MapUtils.newHashMap();
            String noticeTime;
            String noticeDescribe;
            String deviceName;
            for( IsFaults faults:Faults){
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                noticeTime=df.format(faults.getFaultsTime().getTime());
                deviceName=faults.getName();
                noticeDescribe="设备"+deviceName+"发现故障";
                map.put("noticeTitle",NoticeItemTypeToTitle("Fault"));
                map.put("noticeColor",NoticeItemTypeToColor("Fault"));
                map.put("noticeTime",noticeTime);
                map.put("noticeDescribe",noticeDescribe);
                mapList.add(map);
            }

        }else{
            Map<String, Object> map = MapUtils.newHashMap();
            map.put("noticeTitle",NoticeItemTypeToTitle("Fault"));
            map.put("noticeColor",NoticeItemTypeToColor("Fault"));
            map.put("noticeTime","");
            map.put("noticeDescribe","");
            mapList.add(map);
        }
        return mapList;
    }
    /**
     * 各品牌当前（今日）库存数量
     * json:
     *  [{“brand”:”兰州细支珍品”,”count”:10},{“brand”:”XXXXXX”,”count”:20}]
     */
    @RequestMapping(value = {"stockAmount", ""})
    public List<Map<String, Object>> stockAmount() {
        List<Map<String, Object>> mapList = ListUtils.newArrayList();        
        String brand;
        double count;
        for(TwmsPltitem twmsPltitem:twmsPltitemService.getBrandCount()){
        	Map<String, Object> map = MapUtils.newHashMap();
        	brand=twmsPltitem.getItemdesc();
            count=twmsPltitem.getWeight();
            map.put("brand",brand);
            map.put("count",count);
            mapList.add(map);
        }                        
        return mapList;
    }

    /**
     * 当前正在执行的工单信息
     * @return
     * json:
     * [{“completionRatio”:25,”workOrderNumber”:”XXXXXXXXXX”,”startTimeHour”:”08”, ”startTimeMinute”:”56”},
     * {“completionRatio”:65,”workOrderNumber”:”XXXXXXXXXX” ,”startTimeHour”:”08”, ”startTimeMinute”:”56”}]
     */
    @RequestMapping(value = {"workOrder", ""})
    public List<Map<String, Object>> workOrder() {
        List<Map<String, Object>> mapList = ListUtils.newArrayList();        
        int completionRatio;
        String workOrderNumber;
        String startTime; //回头格式化
        String startTimeHour;
        String startTimeMinute;        
        for(WmsGdxdIn wmsGdxdIn:wmsGdxdInService.getNewWorkInfo(CompareDate.getPastDate(0))){
        	Map<String, Object> map = MapUtils.newHashMap();
        	workOrderNumber = wmsGdxdIn.getWoNo();
        	startTime = CompareDate.simplifyTime(wmsGdxdIn.getWoStartTime());
        	System.err.println(startTime);
        	int index = startTime.indexOf(":");
    		startTimeHour = startTime.substring(0,index);
    		startTimeMinute = startTime.substring(index+1);
        	completionRatio = (int)(wmsGdxdIn.getPlanAmount() * 100);        	
        	map.put("completionRatio",completionRatio);
            map.put("workOrderNumber",workOrderNumber);
            map.put("startTimeHour",startTimeHour);
            map.put("startTimeMinute",startTimeMinute);
            //map.put("startTime", startTime);
            mapList.add(map);
        }                          
        return mapList;
    }
    
    /**
     * （当前正在执行的工单出入库信息）
	 * Json：
	 * [{"incomingLine": "XXXX","workOrderNumber": "XXXX","batchNumber": "XXX","brand": "XXX","batchTotalWeight": "XXX","boxSum": "XXX","finishWeight": "XXX","finishBoxNumber,": "XXXX","workOrderStatus": "XXX"}]
     * @return
     */
    @RequestMapping(value = "workOrderInList")
    public List<Map<String, Object>> workOrderInList() {
    	List<Map<String, Object>> mapList = ListUtils.newArrayList();
    	for(WmsGdxdIn wmsGdxdIn:wmsGdxdInService.getNewAllIn(new SimpleDateFormat("yyyy").format(new Date())+"."+CompareDate.getPastDate(0))){
    		Map<String, Object> map = MapUtils.newHashMap();
    		String incomingLine = wmsGdxdIn.getInLine();
    		String workOrderNumber = wmsGdxdIn.getWoNo();
    		String batchNumber = wmsGdxdIn.getBatchNo();
    		String brand = wmsGdxdIn.getMatNm();
    		double batchTotalWeight = wmsGdxdIn.getBatchweight();
    		int boxSum = wmsGdxdIn.getBoxtotalnum();
    		double finishWeight = batchTotalWeight * 0.8;
    		int finishBoxNumber = (int)(boxSum * 0.8);
    		String workOrderStatus = wmsGdxdIn.getState();    		
    		map.put("incomingLine", incomingLine);
    		map.put("workOrderNumber", workOrderNumber);
    		map.put("batchNumber", batchNumber);
    		map.put("brand", brand);
    		map.put("batchTotalWeight", batchTotalWeight);
    		map.put("boxSum", boxSum);
    		map.put("finishWeight", finishWeight);
    		map.put("finishBoxNumber", finishBoxNumber);
    		map.put("workOrderStatus", workOrderStatus);
    		mapList.add(map);
    	}
    	return mapList;
	}
    
    /**
     * 1.3.2.	入库生产线任务信息
	Json：
	[{"VPLTNUM":"XXXX","taskNumber":"XXXX","boxNo":"XXX","origin": XXX","destination": "XXX"},{"VPLTNUM":"XXXX","taskNumber":"XXXX","boxNo":"XXX","origin":"XXX","destination": "XXX"}]

     * @return
     */
    @RequestMapping(value = "workOrderInTask")
    public List<Map<String, Object>> workOrderInTask() {
    	List<Map<String, Object>> mapList = ListUtils.newArrayList();
    	String VPLTNUM = "";
    	String taskNumber = "";
    	String boxNo = "";
    	String origin = "";
    	String destination = "";
    	int count = twmsTransferloggService.getCount();
    	System.err.println(count);
    	for(WmsGdxdIn wmsGdxdIn:wmsGdxdInService.getNewAllIn(new SimpleDateFormat("yyyy").format(new Date())+"."+CompareDate.getPastDate(0))){
    		String batchNumber = wmsGdxdIn.getBatchNo();
    		for(TwmsTransferlogg twmsTransferlogg:twmsTransferloggService.getByLotnum(batchNumber, count)){
    			Map<String, Object> map = MapUtils.newHashMap();
    			VPLTNUM = twmsTransferlogg.getVpltnum();
    			taskNumber = String.valueOf(twmsTransferlogg.getLoggnum());
    			boxNo = twmsTransferlogg.getPltnum();
    			origin = CompareDate.formatCurrLoc(twmsTransferlogg.getSloc());
    			destination = CompareDate.formatCurrLoc(twmsTransferlogg.getDloc());
    			map.put("VPLTNUM", VPLTNUM);
    			map.put("taskNumber", taskNumber);
    			map.put("boxNo", boxNo);
    			map.put("origin", origin);
    			map.put("destination", destination);
    			mapList.add(map);
    		}
    	}
    	return mapList;
    }
    
    /**
     * 1.4.	增加“出库生产信息” -12个喂丝机（显示工单号、批次号、品牌）
     * Json：
	[{"FedWireNumber":"XXXX","workorder":"XXXX","batch":"XXX","brand": XXX"},{"FedWireNumber":"XXXX","workorder":"XXXX","batch":"XXX","brand": XXX"}]

     * @return
     */
    @RequestMapping(value = "workOrderOutList")
    public List<Map<String, Object>> workOrderOutList() {
    	List<Map<String, Object>> mapList = ListUtils.newArrayList();
    	for(WmsGdxdOut wmsGdxdOut:wmsGdxdOutService.getNewAllOut(new SimpleDateFormat("yyyy").format(new Date())+"."+CompareDate.getPastDate(0))){
    		Map<String, Object> map = MapUtils.newHashMap();
    		String FedWireNumber = wmsGdxdOut.getEquId();
    		String workorder = wmsGdxdOut.getWoNo();
    		String batch = wmsGdxdOut.getBatchNo();
    		String brand = wmsGdxdOut.getMatNm();
    		map.put("FedWireNumber", FedWireNumber);
    		map.put("workorder", workorder);
    		map.put("batch", batch);
    		map.put("brand", brand);
    		mapList.add(map);
    	}
    	return mapList;
    }
    
    /**
     * 1.5.	新增小车弹窗数据信息
     *
     * @return
     */
    @RequestMapping(value = "carTransferPos")
    public Map<String, Object> carTransferPos(HttpServletRequest request) {
    	Map<String, Object> map = MapUtils.newHashMap();
    	String taskNumber = request.getParameter("taskNumber");
		int count = twmsTransferloggService.getCount() / 1000;
		TwmsTransferlogg transferlogg = twmsTransferloggService.getByLoggnum(taskNumber, count);
		if (transferlogg != null && transferlogg.getVpltnum() != null) {
			System.err.println(transferlogg.getSloc() + " " + transferlogg.getDloc());
			String origin = CompareDate.formatCurrLoc(transferlogg.getSloc().trim());
			String destination = CompareDate.formatCurrLoc(transferlogg.getDloc().trim());
			map.put("origin", origin);
			map.put("destination", destination);
			return map;
		}  	
    	return map;
    }
    
    /**
     * 1.5
     * post:faultCode
	json:{"faultInfo":"行走不到位"}
     * @param request
     * @return
     */
    @RequestMapping(value = "carFaultsInfo")
    public Map<String, Object> carFaultsInfo(HttpServletRequest request) {
    	Map<String, Object> map = MapUtils.newHashMap();
    	String faultCode = request.getParameter("faultCode");
    	IsFaultsInfo isFaultsInfo = isFaultsInfoService.getFaultsInfo(faultCode);
    	if (isFaultsInfo != null) {
			map.put("alarmInfo", isFaultsInfo.getInfo());
		}
    	return map;
    }
    
    private String NoticeItemTypeToColor(String type)
    {
        switch(type){
            case "Patrol":
                return "E09F2DFF";
            case "Maintain":
                return "00B4EBFF";
            case "Fault":
                return "E82E2EFF";
            default:
                return "FFFFFFFF";
        }
    }

    private String NoticeItemTypeToTitle(String type)
    {
        switch (type){
            case "Patrol":
                return "巡检提醒";
            case "Maintain":
                return "维保提醒";
            case "Fault":
                return "设备故障";
            default:
                return "NaN";
        }
    }
}
