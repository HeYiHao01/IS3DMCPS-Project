package com.jeesite.modules.is3dmcps.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.jeesite.common.collect.MapUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jeesite.common.collect.ListUtils;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.is3dmcps.entity.IsDevice;
import com.jeesite.modules.is3dmcps.service.IsDeviceService;
import com.jeesite.modules.isopc.entity.CarCount;
import com.jeesite.modules.isopc.entity.IsCarCount;
import com.jeesite.modules.isopc.service.IsCarCountService;
import com.jeesite.modules.twms.entity.WmsGdxdIn;
import com.jeesite.modules.twms.entity.WmsGdxdOut;
import com.jeesite.modules.twms.entity.WmsPrdInDetl;
import com.jeesite.modules.twms.entity.WmsPrdOutDetl;
import com.jeesite.modules.twms.service.WmsGdxdInService;
import com.jeesite.modules.twms.service.WmsGdxdOutService;
import com.jeesite.modules.twms.service.WmsPrdInDetlService;
import com.jeesite.modules.twms.service.WmsPrdOutDetlService;
import com.jeesite.utils.CompareDate;

/**
 * 
 * @author ZX
 *大数据统计分析展示界面
 */
@CrossOrigin
@RestController
@RequestMapping(value = "static")
public class PageBigDataController extends BaseController{
	@Autowired
	private IsCarCountService isCarCountService;
	@Autowired
	private IsDeviceService isDeviceService;
	@Autowired
	private WmsGdxdInService wmsGdxdInService;
	@Autowired
	private WmsPrdInDetlService wmsPrdInDetlService;
	@Autowired
	private WmsGdxdOutService wmsGdxdOutService;
	@Autowired
	private WmsPrdOutDetlService wmsPrdOutDetlService;
	
    /**
     * 生产信息分析界面
     * Post:
     * {“analysisType”:”InventoryInfo”,”timeCoordinate”:” Monthly”,” timeDomainYear”:2018}
     * 或
     * {“analysisType”:”InventoryInfo”,”timeCoordinate”:”Daily”,”timeDomainYear”:2019,”timeDomainMonth”:5}
     * @return
     * Json:
     *  [{“brand”:”兰州（细支珍品）烟丝”,“totalWeightThis”:25000,“totalWeightLast”:24000,”finishWeight”:23000,”timeVariable”:10},
     * {“brand”:”兰州（细支珍品）烟丝”,“totalWeightThis”:25000,“totalWeightLast”:24000,”finishWeight”:23000,”timeVariable”:11}]
     */
	@RequestMapping(value = {"productInfoAnalyse", ""})
	public List<Map<String, Object>> productInfoAnalyse(HttpServletRequest request) {
		String analysisType = request.getParameter("analysisType");
		String timeCoordinate = request.getParameter("timeCoordinate");
		String timeDomainYear = request.getParameter("timeDomainYear");		
		List<Map<String, Object>> mapList = ListUtils.newArrayList();
		if (timeCoordinate.equals("Monthly")) {
			switch (analysisType) {
			case "InventoryInfo":  //库存
				
				break;
			case "DeliveryInfo": //出库
				break;
			case "IncomingInfo": //入库
				break;
			case "ProductionInfo":  //产量信息
				break;
			default:
				break;
			}
		}else if (timeCoordinate.equals("Daily")) {
			for(int i = 1;i<32;i++){
				String dateThis = new String();
				String dateLast = new String();
				dateThis += timeDomainYear+".";
				dateLast += timeDomainYear+".";
				String timeDomainMonth = request.getParameter("timeDomainMonth");
				if (Integer.parseInt(timeDomainMonth) < 10) {
					dateThis += "0"+timeDomainMonth+".";
					dateLast += "0"+(Integer.parseInt(timeDomainMonth)-1)+".";
				}else {
					dateThis += timeDomainMonth+".";
					dateLast += (Integer.parseInt(timeDomainMonth)-1)+".";
				}	
				if(i<10){
					dateThis += "0"+i; 
					dateLast += "0"+i;
				}else {
					dateThis += i+"";
					dateLast += i+"";
				}
				
			}
		}else {
			Map<String, Object> map = MapUtils.newHashMap();
			map.put("Error", "传参错误");
			mapList.add(map);
		}
        Map<String, Object> map = MapUtils.newHashMap();
        String brand;
        int totalWeightThis;
        int totalWeightLast;
        int finishWeight;
        int timeVariable;
        brand="兰州（细支珍品）烟丝";
        totalWeightThis=25000;
        totalWeightLast=24000;
        finishWeight=23000;
        timeVariable=10;
        map.put("brand",brand);
        map.put("totalWeightThis",totalWeightThis);
        map.put("totalWeightLast",totalWeightLast);
        map.put("finishWeight",finishWeight);
        map.put("timeVariable",timeVariable);
        mapList.add(map);             
        
		return mapList;
	}
    /**
     * 小车运行统计
     * Json:
 	 *{“timeVariable”:11,“taskTimeCountThis”:22,”taskTimeCountLast”:33}
     * @return
     */
    @RequestMapping(value = {"carRunStatics", ""})
    public List<Map<String, Object>> carRunStatics(HttpServletRequest request) {
        List<Map<String, Object>> mapList = ListUtils.newArrayList();
        String deviceID = request.getParameter("deviceID");
        String timeCoordinate = request.getParameter("timeCoordinate");
        String timeDomainYear = request.getParameter("timeDomainYear");
        if (timeCoordinate.equals("Monthly")) {        							
			CarCount carCountThis = new CarCount();
			CarCount carCountLast = new CarCount();
			int taskTimeCountThis = 0;
			int taskTimeCountLast = 0;
			for(int j = 1;j<13;j++ ){				
				for(int i = 1;i<32;i++){
					String dateThis = new String();
					String dateLast = new String();
					dateThis += timeDomainYear+".";
					dateLast += (Integer.parseInt(timeDomainYear)-1)+".";
					if (j<10) {
						dateThis += "0"+j+".";
						dateLast += "0"+j+".";
					}else {
						dateThis += j+".";
						dateLast += j+".";
					}
					if(i<10){
						dateThis += "0"+i; 
						dateLast += "0"+i;
					}else {
						dateThis += i+"";
						dateLast += i+"";
					}
					carCountThis = isCarCountService.getByDaily(dateThis, deviceID);
					carCountLast = isCarCountService.getByDaily(dateLast, deviceID);
					if (carCountThis != null || carCountLast != null) {
						taskTimeCountThis += carCountThis.getTaskTimeCount();
						taskTimeCountLast += carCountLast.getTaskTimeCount();
					}
				}
				Map<String, Object> map = MapUtils.newHashMap();
				map.put("timeVariable", j);
				map.put("taskTimeCountThis",taskTimeCountThis);
				map.put("taskTimeCountLast",taskTimeCountLast);
				mapList.add(map);
			}
		}else if (timeCoordinate.equals("Daily")) {			
			CarCount carCountThis = new CarCount();
			CarCount carCountLast = new CarCount();
			for(int i = 1;i<32;i++){
				String dateThis = new String();
				String dateLast = new String();
				dateThis += timeDomainYear+".";
				dateLast += timeDomainYear+".";
				String timeDomainMonth = request.getParameter("timeDomainMonth");
				if (Integer.parseInt(timeDomainMonth) < 10) {
					dateThis += "0"+timeDomainMonth+".";
					dateLast += "0"+(Integer.parseInt(timeDomainMonth)-1)+".";
				}else {
					dateThis += timeDomainMonth+".";
					dateLast += (Integer.parseInt(timeDomainMonth)-1)+".";
				}	
				if(i<10){
					dateThis += "0"+i; 
					dateLast += "0"+i;
				}else {
					dateThis += i+"";
					dateLast += i+"";
				}
				carCountThis = isCarCountService.getByDaily(dateThis, deviceID);
				carCountLast = isCarCountService.getByDaily(dateLast, deviceID);
				if (carCountThis != null || carCountLast != null) {
					Map<String, Object> map = MapUtils.newHashMap();
					map.put("timeVariable", i);
					map.put("taskTimeCountThis",carCountThis.getTaskTimeCount());
					map.put("taskTimeCountLast",carCountLast.getTaskTimeCount());
					mapList.add(map);
				}
			}						
		}
        return mapList;
    }

    /**
     * 小车运行情况
     * Post:deviceID or deviceName
     * @return
     * Json:
     * {“runDistance”:11114582,”liftCount”: 255456,”veerCount”:3646863,”runFaultsCount”:2648,”liftFaultsCount”:687,”veerFaultsCount”:54,”allFaultsCount”:3389}
     */
    @RequestMapping(value = {"carRunStatus", ""})
    public List<Map<String, Object>> carRunStatus(HttpServletRequest request) {
        List<Map<String, Object>> mapList = ListUtils.newArrayList();        
        Double runDistance;
        int liftCount;
        int veerCount;
        int runFaultsCount;
        int liftFaultsCount;
        int veerFaultsCount;
        int allFaultsCount;
        for(IsCarCount isCarCount:isCarCountService.getAllByDeviceId(request.getParameter("deviceID"))){
        	Map<String, Object> map = MapUtils.newHashMap();
        	runDistance=isCarCount.getMoveMileage();
            liftCount=isCarCount.getUpdownCount();
            veerCount=isCarCount.getTurnCount();
            runFaultsCount=isCarCount.getMoveerrCount();
            liftFaultsCount=isCarCount.getUpdownerrCount();
            veerFaultsCount=isCarCount.getTurnerrCount();
            allFaultsCount=isCarCount.getErrCount();
            map.put("runDistance",runDistance);
            map.put("liftCount",liftCount);
            map.put("veerCount",veerCount);
            map.put("runFaultsCount",runFaultsCount);
            map.put("liftFaultsCount",liftFaultsCount);
            map.put("veerFaultsCount",veerFaultsCount);
            map.put("allFaultsCount",allFaultsCount);
            mapList.add(map);
        }                
        return mapList;
    }

    /**
     * 小车历史故障统计
     * @return
     * Json：
     * [{“deviceName”:”CAR01”,” allFaultsCount”:76},{“deviceName”:”CAR02”,” allFaultsCount”:54}]
     */
    @RequestMapping(value = {"carHistoryFault", ""})
    public List<Map<String, Object>> carHistoryFault(HttpServletRequest request) {
    	List<Map<String, Object>> mapList = ListUtils.newArrayList();	
    	String deviceName;
		int allFaultsCount;
		for(IsDevice isDevice:isDeviceService.getDeviceByCodeName(request.getParameter("deviceTypeName"))){
			for(IsCarCount isCarCount:isCarCountService.getAllByDeviceName(isDevice.getDeviceNo())){
				Map<String, Object> map = MapUtils.newHashMap();				
				deviceName=isCarCount.getDeviceName();
				allFaultsCount=isCarCount.getErrCount();
				map.put("deviceName",deviceName);
				map.put("allFaultsCount",allFaultsCount);		
				mapList.add(map);
			}			
		}
		return mapList;			
    }

    /**
     * 生产线平均生产能力表
     * @return
     * Json:
     *  [{“productionLineName”:”生产线一”,“timeVariable”:11,“productionCapacity”:22000},
     * {“productionLineName”:”生产线一”, “timeVariable”:12,“productionCapacity”:25000}]
     */
    @RequestMapping(value = {"productionLine", ""})
    public List<Map<String, Object>> productionLine(HttpServletRequest request) {
        List<Map<String, Object>> mapList = ListUtils.newArrayList();
        String timeDomainYear = request.getParameter("timeDomainYear");
        String timeDomainMonth = request.getParameter("timeDomainMonth");
        String date = timeDomainYear+".";
        if (Integer.parseInt(timeDomainMonth) < 10) {
			date += "0"+timeDomainMonth+".";			
		}else {
			date += timeDomainMonth+".";		
		}	
        int timeVariable;
        int productionCapacity;
        for(int i=1;i<32;i++){
        	if(i<10){
				date += "0"+i; 				
			}else {
				date += i+"";				
			}
            Map<String, Object> map = MapUtils.newHashMap();
            String productionLineName;
            timeVariable = i;
            for(WmsGdxdIn wmsGdxdIn:wmsGdxdInService.getAllIn(date)){
            	productionLineName = wmsGdxdIn.getInLine();
            	timeVariable=i;
            	if (productionLineName.equals("1")) {
            		productionLineName = "生产线一";
            		productionCapacity = Integer.parseInt(wmsPrdInDetlService.getDetailByWB(wmsGdxdIn.getWoNo(), wmsGdxdIn.getBatchNo()).getWeight());
            		map.put("productionLineName",productionLineName);
                    map.put("timeVariable",timeVariable);
                    map.put("productionCapacity",productionCapacity);
                    mapList.add(map);
				}else if(productionLineName.equals("1")){
					productionLineName = "生产线二";
					productionCapacity = Integer.parseInt(wmsPrdInDetlService.getDetailByWB(wmsGdxdIn.getWoNo(), wmsGdxdIn.getBatchNo()).getWeight());
					map.put("productionLineName",productionLineName);
		            map.put("timeVariable",timeVariable);
		            map.put("productionCapacity",productionCapacity);
		            mapList.add(map);
				}else {
					productionLineName = "None";
					map.put("productionLineName",productionLineName);
		            mapList.add(map);
				}
            }                                   
        }        
        return mapList;
    }

    /**
     * 月批次出入库耗时情况表
     * Post:
     * {”timeDomainYear”:2019,”timeDomainMonth”:5}
     * @return
     * Json:
     * [{“batch”:”YXZZ19010”,”weight”:9845.11,”timeCost”:18},
     * {“batch”:”YXZZ19011”,”weight”:4235.15,”timeCost”:13}]
     */
    @RequestMapping(value = {"monthlyBatchTime", ""})
    public List<Map<String, Object>> monthlyBatchTime(HttpServletRequest request) {
        List<Map<String, Object>> mapList = ListUtils.newArrayList();
        String timeDomainYear = request.getParameter("timeDomainYear");
        String timeDomainMonth = request.getParameter("timeDomainMonth");  
        String type = request.getParameter("type");
        for(int i=1;i<32;i++){
        	String date = new String();
        	date = timeDomainYear+".";
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
        	if (type.equals("In")) {
        		Map<String, Object> map = MapUtils.newHashMap();
                String batch;
                double weight = 0.0;
                double timeCost;
                for(WmsGdxdIn wmsGdxdIn:wmsGdxdInService.getBatchAndTime(date)){
                	batch = wmsGdxdIn.getBatchNo();
                	timeCost=CompareDate.getTimeCost(wmsGdxdIn.getWoStartTime());
                	for(WmsPrdInDetl wmsPrdInDetl:wmsPrdInDetlService.getDetailByBatchNo(batch)){
                		weight += Double.valueOf(wmsPrdInDetl.getWeight());
                	}
                	map.put("batch",batch);
                    map.put("weight",weight);
                    map.put("timeCost",timeCost);
                    mapList.add(map);
                }                          
			}else if (type.equals("Out")) {
				Map<String, Object> map = MapUtils.newHashMap();
                String batch;
                double weight = 0.0;
                double timeCost;
                for(WmsGdxdOut wmsGdxdOut:wmsGdxdOutService.getBatchAndTime(date)){
                	batch = wmsGdxdOut.getBatchNo();
                	timeCost=CompareDate.getTimeCost(wmsGdxdOut.getWoStartTime());
                	for(WmsPrdOutDetl wmsPrdOutDetl:wmsPrdOutDetlService.getDetailByBatchNo(batch)){
                		weight += Double.valueOf(wmsPrdOutDetl.getWeight());
                	}
                	map.put("batch",batch);
                    map.put("weight",weight);
                    map.put("timeCost",timeCost);
                    mapList.add(map);
                }  
			}else {
				Map<String, Object> map = MapUtils.newHashMap();
				map.put("Error", "type参数错误");
				mapList.add(map);
			}        	
        }
        return mapList;       
    }
}
	
	