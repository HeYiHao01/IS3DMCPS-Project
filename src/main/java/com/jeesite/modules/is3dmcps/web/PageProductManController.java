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
     * 删去维修数量
     */
    @RequestMapping(value = {"totalQuantity", ""})
    public Map<String, Object> totalQuantity() {
        Map<String, Object> map = MapUtils.newHashMap();
        int deviceRuningCount;
        int deviceFaultsCount;
        //int deviceRepairCount;
        deviceRuningCount=isDeviceService.getAll()-isFaultsService.getAllFaultsCount();
        deviceFaultsCount=isFaultsService.getAllFaultsCount();
        //deviceRepairCount=isMaintainRecService.need_maintain();
        map.put("deviceRuningCount",deviceRuningCount);
        map.put("deviceFaultsCount",deviceFaultsCount);
        //map.put("deviceRepairCount",deviceRepairCount);
        return map;
    }

    /**
     * 设备故障提醒；设备保养提醒；设备巡检提醒
     * @return
     * Json:
     * [{“noticeType”:”Maintain”,”noticeTime”:”14:12”,”noticeDescribe”:”设备XX需要更换零件”},
     * {“noticeType”:”Fault”,”noticeTime”:”15:12”,”noticeDescribe”:”设备XX发现故障”}]
     * 这里的时间是消息内容应该是根据间隔时间和上次巡查的时间算出
     * 
     * 删去巡检提醒
     * {"noticeDescribe":"巡检点Beconvey01到达固定巡检时间","noticeColor":"00B4EBFF","noticeTime":"2019-12-28 18:41:00","noticeTitle":"维保提醒"},
     */
    @RequestMapping(value = {"deviceAlert", ""})
    public List<Map<String, Object>> deviceAlert() {
        List<Map<String, Object>> mapList = ListUtils.newArrayList();
        List<IsMaintainRec> maintains = isMaintainRecService.need_maintain_details();        
        List<IsFaults> Faults = isFaultsService.getFaultsDetails();        
        //Date curTime=new Date();// new Date()为获取当前系统时间
        int count = 0;
        if(maintains != null && maintains.size()>= 1){            
            String noticeTime;
            String noticeDescribe;
            String deviceName;

            for(IsMaintainRec isMaintainRec: maintains){                
                deviceName=isMaintainRec.getDeviceNo();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                noticeTime=df.format(isMaintainRec.getPlanDate().getTime());
                noticeDescribe = "设备" + deviceName + "需要维保";                
                Map<String, Object> map = MapUtils.newHashMap();
                map.put("priority", ++count);
                map.put("noticeTitle",NoticeItemTypeToTitle("Maintain"));
                map.put("noticeColor",NoticeItemTypeToColor("Maintain"));
                map.put("noticeTime",noticeTime);
                map.put("noticeDescribe",noticeDescribe);
                mapList.add(map);
            }
        }/*else{
            Map<String, Object> map = MapUtils.newHashMap();
            map.put("priority", Integer.MAX_VALUE);
            map.put("noticeTitle",NoticeItemTypeToTitle("Maintain"));
            map.put("noticeColor",NoticeItemTypeToColor("Maintain"));
            map.put("noticeTime","");
            map.put("noticeDescribe","");
            mapList.add(map);
        } */       
        if(Faults != null && Faults.size()>= 1){            
            String noticeTime;
            String noticeDescribe;
            String deviceName;
            for( IsFaults faults:Faults){
            	Map<String, Object> map = MapUtils.newHashMap();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                noticeTime=df.format(faults.getFaultsTime().getTime());
                deviceName=isDeviceService.getDeviceById(faults.getDeviceId()).getDeviceNo();
                noticeDescribe="设备"+deviceName+"发现故障";
                map.put("priority", ++count);
                map.put("noticeTitle",NoticeItemTypeToTitle("Fault"));
                map.put("noticeColor",NoticeItemTypeToColor("Fault"));
                map.put("noticeTime",noticeTime);
                map.put("noticeDescribe",noticeDescribe);
                mapList.add(map);
            }

        }/*else{
            Map<String, Object> map = MapUtils.newHashMap();
            map.put("priority", Integer.MAX_VALUE);
            map.put("noticeTitle",NoticeItemTypeToTitle("Fault"));
            map.put("noticeColor",NoticeItemTypeToColor("Fault"));
            map.put("noticeTime","");
            map.put("noticeDescribe","");
            mapList.add(map);
        }*/
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
        for(TwmsPltitem twmsPltitem:twmsPltitemService.getNewBrandCount()){
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
    /*@RequestMapping(value = {"workOrder", ""})
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
    }*/
    
    /**
     * 即时生产信息拆分成一号装箱线、二号装箱线、出库信息三组，样式保持不变。
     * 在一、二号装箱线中左边圆圈分别显示1和2，圆圈右边显示生产的品牌名称和批次以及开始时间；
     * 出库信息中左边圆圈分别显示风力送丝机编号1~12（喂丝机），圆圈右边显示生产的牌号和批次以及开始时间
     * json：
     * [{“brand”:"XXXX",”batchNo”:”XXXXXXXXXX”,”startTimeHour”:”08”, ”startTimeMinute”:”56”,"type":"1/2/出库","fedWireNum":"XXXtype为出库时才有这个字段XXXX"},]
     *
     * @return
     */
    @RequestMapping(value = {"workOrder", ""})
    public List<Map<String, Object>> workOrder() {
        List<Map<String, Object>> mapList = ListUtils.newArrayList();        
        String brand;
        String batchNo;
        String startTime; //回头格式化
        String startTimeHour;
        String startTimeMinute;  
        String type;
        String woState = "Doing";
        int length = wmsGdxdInService.getGdxdInLength();
        for(WmsGdxdIn wmsGdxdIn:wmsGdxdInService.getByWoState(woState, length)){
        	Map<String, Object> map = MapUtils.newHashMap();
        	batchNo = wmsGdxdIn.getBatchNo();
        	brand = wmsGdxdIn.getMatNm();
        	startTime = CompareDate.simplifyTime(wmsGdxdIn.getWoStartTime());
        	System.err.println(startTime);
        	int index = startTime.indexOf(":");
    		startTimeHour = startTime.substring(0,index);
    		startTimeMinute = startTime.substring(index+1);
    		if (wmsGdxdIn.getInLine().equals("1")) {
				type = "1";
				map.put("brand",brand);
				map.put("batchNo",batchNo);
	            map.put("startTimeHour",startTimeHour);
	            map.put("startTimeMinute",startTimeMinute);
	            map.put("type", type);
			}else if (wmsGdxdIn.getInLine().equals("2")){
				type = "2";
				map.put("brand",brand);
				map.put("batchNo",batchNo);
	            map.put("startTimeHour",startTimeHour);
	            map.put("startTimeMinute",startTimeMinute);
	            map.put("type", type);
			}else {				
				map.put("error", "入库线错误");
			}                        
            mapList.add(map);
        }     
        int length2 = wmsGdxdOutService.getGdxdOutLength();
        for(WmsGdxdOut wmsGdxdOut:wmsGdxdOutService.getByWoState(woState, length2)){
        	Map<String, Object> map = MapUtils.newHashMap();
        	batchNo = wmsGdxdOut.getBatchNo();
        	brand = wmsGdxdOut.getMatNm();
        	startTime = CompareDate.simplifyTime(wmsGdxdOut.getWoStartTime());
        	System.err.println(startTime);
        	int index = startTime.indexOf(":");
    		startTimeHour = startTime.substring(0,index);
    		startTimeMinute = startTime.substring(index+1);
    		type = "出库";
    		String fedWireNum = wmsGdxdOut.getEquId();
    		if (!fedWireNum.equals("手工喂丝")) {
    			map.put("brand",brand);
    			map.put("batchNo",batchNo);
                map.put("startTimeHour",startTimeHour);
                map.put("startTimeMinute",startTimeMinute);
                map.put("type", type);
                map.put("fedWireNum", fedWireNum);
                mapList.add(map);
			}    		
        }     
        return mapList;
    }
    
    /**
     * （当前正在执行的工单入库信息）
	 * Json：
	 * [{"incomingLine": "XXXX","workOrderNumber": "XXXX","batchNumber": "XXX","brand": "XXX","batchTotalWeight": "XXX","boxSum": "XXX","finishWeight": "XXX","finishBoxNumber,": "XXXX","workOrderStatus": "XXX"}]
     * @return
     */
    @RequestMapping(value = "workOrderInList")
    public List<Map<String, Object>> workOrderInList() {
    	List<Map<String, Object>> mapList = ListUtils.newArrayList();
    	int length = wmsGdxdInService.getGdxdInLength();
    	for(WmsGdxdIn wmsGdxdIn:wmsGdxdInService.getByWoState("Doing", length)){
    		Map<String, Object> map = MapUtils.newHashMap();
    		String incomingLine = wmsGdxdIn.getInLine();
    		String workOrderNumber = wmsGdxdIn.getWoNo();
    		String batchNumber = wmsGdxdIn.getBatchNo();
    		String brand = wmsGdxdIn.getMatNm();
    		double batchTotalWeight = wmsGdxdIn.getBatchweight();
    		int boxSum = wmsGdxdIn.getBoxtotalnum();
    		double finishWeight = batchTotalWeight * 0.8;
    		int finishBoxNumber = (int)(boxSum * 0.8);
    		String workOrderStatus = wmsGdxdIn.getWoState();    		
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
    	int length = wmsGdxdInService.getGdxdInLength();
    	for(WmsGdxdIn wmsGdxdIn:wmsGdxdInService.getByWoState("Doing", length)){
    		String batchNumber = wmsGdxdIn.getBatchNo();
    		for(TwmsTransferlogg twmsTransferlogg:twmsTransferloggService.getByLotnum(batchNumber, count)){    			
    			VPLTNUM = twmsTransferlogg.getVpltnum();
    			taskNumber = String.valueOf(twmsTransferlogg.getLoggnum());
    			boxNo = twmsTransferlogg.getPltnum();
    			origin = CompareDate.formatCurrLoc(twmsTransferlogg.getSloc());
    			destination = CompareDate.formatCurrLoc(twmsTransferlogg.getDloc());
    			if (origin.equals("OME01_5166") || origin.equals("OME01_5142")) {
    				Map<String, Object> map = MapUtils.newHashMap();
        			map.put("VPLTNUM", VPLTNUM);
        			map.put("taskNumber", taskNumber);
        			map.put("boxNo", boxNo);
        			map.put("origin", origin);
        			map.put("destination", destination);
        			mapList.add(map);
				}
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
    	int length = wmsGdxdInService.getGdxdInLength();
    	for(WmsGdxdOut wmsGdxdOut:wmsGdxdOutService.getByWoState("Doing", length)){
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
