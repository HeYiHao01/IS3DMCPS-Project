package com.jeesite.modules.is3dmcps.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jeesite.common.collect.ListUtils;
import com.jeesite.common.collect.MapUtils;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.twms.entity.WmsGdxdIn;
import com.jeesite.modules.twms.entity.WmsGdxdOut;
import com.jeesite.modules.twms.entity.WmsPrdInDetl;
import com.jeesite.modules.twms.entity.WmsPrdOutDetl;
import com.jeesite.modules.twms.service.WmsGdxdInService;
import com.jeesite.modules.twms.service.WmsGdxdOutService;
import com.jeesite.modules.twms.service.WmsPrdInDetlService;
import com.jeesite.modules.twms.service.WmsPrdOutDetlService;

/**
 * 新的服务器上的大数据相关接口
 * @author HAO
 *
 */
@CrossOrigin
@RestController
@RequestMapping(value = "static")
public class PageNewBigData extends BaseController {
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
	 *{“analysisType”:”InventoryInfo”,”timeCoordinate”:” Monthly”,” timeDomainYear”:2018}
	 *或
	 *{“analysisType”:”InventoryInfo”,”timeCoordinate”:”Daily”,”timeDomainYear”:2019,”timeDomainMonth”:5}
	 *Json:
	 *[{“brand”:”兰州（细支珍品）烟丝”,“totalWeightThis”:25000,“totalWeightLast”:24000,”finishWeight”:23000,”timeVariable”:10},
	 *{“brand”:”兰州（细支珍品）烟丝”,“totalWeightThis”:25000,“totalWeightLast”:24000,”finishWeight”:23000,”timeVariable”:11}]
	 * @param request
	 * @return
	 */
	@RequestMapping(value = {"newProductInfoAnalyse", ""})
	public List<Map<String, Object>> newProductInfoAnalyse(HttpServletRequest request) {
		String analysisType = request.getParameter("analysisType");
		String timeCoordinate = request.getParameter("timeCoordinate");
		String timeDomainYear = request.getParameter("timeDomainYear");		
		List<Map<String, Object>> mapList = ListUtils.newArrayList();
		if (timeCoordinate.equals("Monthly")) {
			switch (analysisType) {
			case "InventoryInfo":
				Map<String, Object> map = MapUtils.newHashMap();
				map.put("Error", "库存功能已砍");
				mapList.add(map);
				break;
			case "DeliveryInfo":
				for (int j = 1; j < 13; j++) {
					String dateThis = new String();
					String dateLast = new String();
					dateThis += timeDomainYear + ".";
					dateLast += (Integer.parseInt(timeDomainYear) - 1) + ".";
					if (j < 10) {
						dateThis += "0" + j + "";
						dateLast += "0" + j + "";
					} else {
						dateThis += j + "";
						dateLast += j + "";
					}
					List<Map<String, Object>> mapListThis = ListUtils.newArrayList();
					List<Map<String, Object>> mapListLast = ListUtils.newArrayList();
					int finishWeight = 0;
					for (int k = 1; k < 32; k++) {
						String dateForFinishWeight = new String();
						dateForFinishWeight = dateThis + ".";
						if (k < 10) {
							dateForFinishWeight += "0" + k + "";
						} else {
							dateForFinishWeight += k + "";
						}
						System.err.println(dateForFinishWeight);
						for (WmsGdxdOut wmsGdxdOut : wmsGdxdOutService.getNewAllOut(dateForFinishWeight)) {
							if (wmsGdxdOut.getBoxtotalnum() != null) {
								finishWeight += wmsGdxdOut.getBoxtotalnum();
							}							
						}
					}
					for (WmsPrdOutDetl wmsPrdOutDetl : wmsPrdOutDetlService.getNewDetailMonthly(dateThis)) {
						Map<String, Object> mapThis = MapUtils.newHashMap();
						mapThis.put("brand", wmsPrdOutDetl.getMatNm());
						mapThis.put("totalWeightThis", Double.valueOf(wmsPrdOutDetl.getWeight()));
						mapListThis.add(mapThis);
					}
					for (WmsPrdOutDetl wmsPrdOutDetl : wmsPrdOutDetlService.getNewDetailMonthly(dateLast)) {
						Map<String, Object> mapLast = MapUtils.newHashMap();
						mapLast.put("brand", wmsPrdOutDetl.getMatNm());
						mapLast.put("totalWeightLast", Double.valueOf(wmsPrdOutDetl.getWeight()));
						mapListLast.add(mapLast);
					}
					for (Map<String, Object> mapT : mapListThis) {
						if (mapListLast != null && !mapList.isEmpty()) {
							for (Map<String, Object> mapL : mapListLast) {
								if (mapT.get("brand").equals(mapL.get("brand"))) {
									Map<String, Object> mapF = MapUtils.newHashMap();
									mapF.put("timeVariable", j);
									mapF.put("brand", mapT.get("brand"));
									mapF.put("totalWeightThis", mapT.get("totalWeightThis"));
									mapF.put("finishWeight", finishWeight);
									mapF.put("totalWeightLast", mapL.get("totalWeightLast"));
									mapList.add(mapF);
								}
							}
						} else {
							Map<String, Object> mapF = MapUtils.newHashMap();
							mapF.put("timeVariable", j);
							mapF.put("brand", mapT.get("brand"));
							mapF.put("totalWeightThis", mapT.get("totalWeightThis"));
							mapF.put("finishWeight", finishWeight);
							mapF.put("totalWeightLast", 0);
							mapList.add(mapF);
						}

					}
				}
				break;
			case "IncomingInfo":
				for (int j = 1; j < 13; j++) {
					String dateThis = new String();
					String dateLast = new String();
					dateThis += timeDomainYear + ".";
					dateLast += (Integer.parseInt(timeDomainYear) - 1) + ".";
					if (j < 10) {
						dateThis += "0" + j + "";
						dateLast += "0" + j + "";
					} else {
						dateThis += j + "";
						dateLast += j + "";
					}
					List<Map<String, Object>> mapListThis = ListUtils.newArrayList();
					List<Map<String, Object>> mapListLast = ListUtils.newArrayList();
					int finishWeight = 0;
					for (int k = 1; k < 32; k++) {
						String dateForFinishWeight = new String();
						dateForFinishWeight = dateThis + ".";
						if (k < 10) {
							dateForFinishWeight += "0" + k + "";
						} else {
							dateForFinishWeight += k + "";
						}
						// System.err.println(dateForFinishWeight);
						for (WmsGdxdIn wmsGdxdIn : wmsGdxdInService.getNewAllIn(dateForFinishWeight)) {
							if (wmsGdxdIn.getBoxtotalnum() != null) 
								finishWeight += wmsGdxdIn.getBoxtotalnum();
						}
					}
					// System.err.println(dateThis);
					for (WmsPrdInDetl wmsPrdInDetl : wmsPrdInDetlService.getNewDetailMonthly(dateThis)) {
						Map<String, Object> mapThis = MapUtils.newHashMap();
						mapThis.put("brand", wmsPrdInDetl.getMatNm());
						mapThis.put("finishWeight", finishWeight);
						mapThis.put("totalWeightThis", Double.valueOf(wmsPrdInDetl.getWeight()));
						mapListThis.add(mapThis);
					}
					for (WmsPrdInDetl wmsPrdOutDetl : wmsPrdInDetlService.getNewDetailMonthly(dateLast)) {
						Map<String, Object> mapLast = MapUtils.newHashMap();
						mapLast.put("brand", wmsPrdOutDetl.getMatNm());
						mapLast.put("totalWeightLast", Double.valueOf(wmsPrdOutDetl.getWeight()));
						mapListLast.add(mapLast);
					}
					for (Map<String, Object> mapT : mapListThis) {
						if (mapListLast != null && !mapList.isEmpty()) {
							for (Map<String, Object> mapL : mapListLast) {
								if (mapT.get("brand").equals(mapL.get("brand"))) {
									Map<String, Object> mapF = MapUtils.newHashMap();
									mapF.put("timeVariable", j);
									mapF.put("brand", mapT.get("brand"));
									mapF.put("totalWeightThis", mapT.get("totalWeightThis"));
									mapF.put("finishWeight", finishWeight);
									mapF.put("totalWeightLast", mapL.get("totalWeightLast"));
									mapList.add(mapF);
								}
							}
						} else {
							Map<String, Object> mapF = MapUtils.newHashMap();
							mapF.put("timeVariable", j);
							mapF.put("brand", mapT.get("brand"));
							mapF.put("totalWeightThis", mapT.get("totalWeightThis"));
							mapF.put("finishWeight", finishWeight);
							mapF.put("totalWeightLast", 0);
							mapList.add(mapF);
						}
					}
				}
				break;
			case "ProductionInfo":
				for (int j = 1; j < 13; j++) {
					String dateThis = new String();
					String dateLast = new String();
					dateThis += timeDomainYear + ".";
					dateLast += (Integer.parseInt(timeDomainYear) - 1) + ".";
					if (j < 10) {
						dateThis += "0" + j + "";
						dateLast += "0" + j + "";
					} else {
						dateThis += j + "";
						dateLast += j + "";
					}
					List<Map<String, Object>> mapListThis = ListUtils.newArrayList();
					List<Map<String, Object>> mapListLast = ListUtils.newArrayList();
					int finishWeight = 0;
					for (int k = 1; k < 32; k++) {
						String dateForFinishWeight = new String();
						dateForFinishWeight = dateThis + ".";
						if (k < 10) {
							dateForFinishWeight += "0" + k + "";
						} else {
							dateForFinishWeight += k + "";
						}
						// System.err.println(dateForFinishWeight);
						for (WmsGdxdIn wmsGdxdIn : wmsGdxdInService.getNewAllIn(dateForFinishWeight)) {
							if (wmsGdxdIn.getBoxtotalnum() != null) 
								finishWeight += wmsGdxdIn.getBoxtotalnum();
						}
					}
					// System.err.println(dateThis);
					for (WmsPrdInDetl wmsPrdInDetl : wmsPrdInDetlService.getNewDetailMonthly(dateThis)) {
						Map<String, Object> mapThis = MapUtils.newHashMap();
						mapThis.put("brand", wmsPrdInDetl.getMatNm());
						mapThis.put("finishWeight", finishWeight);
						mapThis.put("totalWeightThis", Double.valueOf(wmsPrdInDetl.getWeight()));
						mapListThis.add(mapThis);
					}
					for (WmsPrdInDetl wmsPrdOutDetl : wmsPrdInDetlService.getNewDetailMonthly(dateLast)) {
						Map<String, Object> mapLast = MapUtils.newHashMap();
						mapLast.put("brand", wmsPrdOutDetl.getMatNm());
						mapLast.put("totalWeightLast", Double.valueOf(wmsPrdOutDetl.getWeight()));
						mapListLast.add(mapLast);
					}
					for (Map<String, Object> mapT : mapListThis) {
						if (mapListLast != null && !mapList.isEmpty()) {
							for (Map<String, Object> mapL : mapListLast) {
								if (mapT.get("brand").equals(mapL.get("brand"))) {
									Map<String, Object> mapF = MapUtils.newHashMap();
									mapF.put("timeVariable", j);
									mapF.put("brand", mapT.get("brand"));
									mapF.put("totalWeightThis", mapT.get("totalWeightThis"));
									mapF.put("finishWeight", finishWeight);
									mapF.put("totalWeightLast", mapL.get("totalWeightLast"));
									mapList.add(mapF);
								}
							}
						} else {
							Map<String, Object> mapF = MapUtils.newHashMap();
							mapF.put("timeVariable", j);
							mapF.put("brand", mapT.get("brand"));
							mapF.put("totalWeightThis", mapT.get("totalWeightThis"));
							mapF.put("finishWeight", finishWeight);
							mapF.put("totalWeightLast", 0);
							mapList.add(mapF);
						}
					}
				}
				break;
			default:
				Map<String, Object> map1 = MapUtils.newHashMap();
				map1.put("Error", "analysisType传参错误");
				mapList.add(map1);
				break;
			}
		}else if (timeCoordinate.equals("Daily")) {
			switch (analysisType) {
			case "InventoryInfo":
				Map<String, Object> map = MapUtils.newHashMap();
				map.put("Error", "库存功能已砍");
				mapList.add(map);
				break;				
			case "DeliveryInfo":
				for (int i = 1; i < 32; i++) {
					String dateThis = new String();
					String dateLast = new String();
					dateThis += timeDomainYear + ".";
					dateLast += timeDomainYear + ".";
					String timeDomainMonth = request.getParameter("timeDomainMonth");
					if (Integer.parseInt(timeDomainMonth) < 10) {
						dateThis += "0" + timeDomainMonth + ".";
						dateLast += "0" + (Integer.parseInt(timeDomainMonth) - 1) + ".";
					} else {
						dateThis += timeDomainMonth + ".";
						dateLast += (Integer.parseInt(timeDomainMonth) - 1) + ".";
					}
					if (i < 10) {
						dateThis += "0" + i;
						dateLast += "0" + i;
					} else {
						dateThis += i + "";
						dateLast += i + "";
					}
					List<Map<String, Object>> mapListThis = ListUtils.newArrayList();
					for (WmsGdxdOut wmsGdxdOut : wmsGdxdOutService.getNewAllOut(dateThis)) {
						Map<String, Object> mapThis = MapUtils.newHashMap();
						String brandThis;
						double totalWeightThis = 0;						
						int finishWeight = 0;
						if (wmsGdxdOut.getBoxtotalnum() != null) 
							finishWeight = wmsGdxdOut.getBoxtotalnum();
						brandThis = wmsGdxdOut.getMatNm();
						for (WmsPrdOutDetl wmsPrdOutDetl : wmsPrdOutDetlService.getNewDetailByWN(wmsGdxdOut.getWoNo())) {
							totalWeightThis += Double.valueOf(wmsPrdOutDetl.getWeight());
						}
						mapThis.put("brand", brandThis);
						mapThis.put("finishWeight", finishWeight);
						mapThis.put("totalWeightThis", totalWeightThis);
						mapListThis.add(mapThis);
					}
					List<Map<String, Object>> mapListLast = ListUtils.newArrayList();
					for (WmsGdxdOut wmsGdxdOut : wmsGdxdOutService.getNewAllOut(dateLast)) {
						Map<String, Object> mapLast = MapUtils.newHashMap();
						String brandLast;
						double totalWeightLast = 0;
						brandLast = wmsGdxdOut.getMatNm();
						for (WmsPrdOutDetl wmsPrdOutDetl : wmsPrdOutDetlService.getNewDetailByWN(wmsGdxdOut.getWoNo())) {
							totalWeightLast += Double.valueOf(wmsPrdOutDetl.getWeight());
						}
						mapLast.put("brand", brandLast);
						mapLast.put("totalWeightLast", totalWeightLast);
						mapListLast.add(mapLast);
					}
					for (Map<String, Object> mapT : mapListThis) {
						if (mapListLast != null && !mapListLast.isEmpty()) {
							for (Map<String, Object> mapL : mapListLast) {
								if (mapT.get("brand").equals(mapL.get("brand"))) {
									Map<String, Object> mapF = MapUtils.newHashMap();
									mapF.put("timeVariable", i);
									mapF.put("brand", mapT.get("brand"));
									mapF.put("totalWeightThis", mapT.get("totalWeightThis"));
									mapF.put("finishWeight", mapT.get("finishWeight"));
									mapF.put("totalWeightLast", mapL.get("totalWeightLast"));
									mapList.add(mapF);
								}
							}
						} else {
							Map<String, Object> mapF = MapUtils.newHashMap();
							mapF.put("timeVariable", i);
							mapF.put("brand", mapT.get("brand"));
							mapF.put("totalWeightThis", mapT.get("totalWeightThis"));
							mapF.put("finishWeight", mapT.get("finishWeight"));
							mapF.put("totalWeightLast", 0);
							mapList.add(mapF);
						}
					}
				}				
				break;
			case "IncomingInfo":
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
					List<Map<String, Object>> mapListThis = ListUtils.newArrayList();
					for(WmsGdxdIn wmsGdxdIn:wmsGdxdInService.getNewAllIn(dateThis)){					
						Map<String, Object> mapThis = MapUtils.newHashMap();
						String brandThis;
				        double totalWeightThis = 0;
				        brandThis = wmsGdxdIn.getMatNm();
				        int finishWeight = 0;
				        if(wmsGdxdIn.getBoxtotalnum() != null)
				        	finishWeight=wmsGdxdIn.getBoxtotalnum();
				        for(WmsPrdInDetl wmsPrdInDetl:wmsPrdInDetlService.getNewDetailByWN(wmsGdxdIn.getWoNo())){
				        	totalWeightThis += Double.valueOf(wmsPrdInDetl.getWeight());
				        }
				        mapThis.put("brand", brandThis);
				        mapThis.put("finishWeight", finishWeight);
				        mapThis.put("totalWeightThis", totalWeightThis);
				        mapListThis.add(mapThis);
					}
					List<Map<String, Object>> mapListLast = ListUtils.newArrayList();
					for(WmsGdxdIn wmsGdxdIn:wmsGdxdInService.getNewAllIn(dateLast)){						
						Map<String, Object> mapLast = MapUtils.newHashMap();
						String brandLast;
				        double totalWeightLast = 0;
				        brandLast = wmsGdxdIn.getMatNm();
				        for(WmsPrdInDetl wmsPrdInDetl:wmsPrdInDetlService.getNewDetailByWN(wmsGdxdIn.getWoNo())){
				        	totalWeightLast += Double.valueOf(wmsPrdInDetl.getWeight());
				        }
				        mapLast.put("brand", brandLast);
				        mapLast.put("totalWeightLast", totalWeightLast);
				        mapListLast.add(mapLast);
					}
					for(Map<String, Object> mapT:mapListThis){
						if(mapListLast != null && !mapListLast.isEmpty()){
							for(Map<String, Object> mapL:mapListLast){							
								if (mapT.get("brand").equals(mapL.get("brand"))) {
									Map<String, Object> mapF = MapUtils.newHashMap();
									mapF.put("timeVariable", i);
									mapF.put("brand", mapT.get("brand"));
									mapF.put("totalWeightThis", mapT.get("totalWeightThis"));
									mapF.put("finishWeight", mapT.get("finishWeight"));
									mapF.put("totalWeightLast", mapL.get("totalWeightLast"));
									mapList.add(mapF);
								}
							}
						} else {
							Map<String, Object> mapF = MapUtils.newHashMap();
							mapF.put("timeVariable", i);
							mapF.put("brand", mapT.get("brand"));
							mapF.put("totalWeightThis", mapT.get("totalWeightThis"));
							mapF.put("finishWeight", mapT.get("finishWeight"));
							mapF.put("totalWeightLast", 0);
							mapList.add(mapF);
						}
					}				
				}
				break;
			case "ProductionInfo":
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
					List<Map<String, Object>> mapListThis = ListUtils.newArrayList();
					for(WmsGdxdIn wmsGdxdIn:wmsGdxdInService.getNewAllIn(dateThis)){					
						Map<String, Object> mapThis = MapUtils.newHashMap();
						String brandThis;
				        double totalWeightThis = 0;
				        brandThis = wmsGdxdIn.getMatNm();
				        int finishWeight = 0;
				        if(wmsGdxdIn.getBoxtotalnum() != null)
				        	finishWeight=wmsGdxdIn.getBoxtotalnum();
				        for(WmsPrdInDetl wmsPrdInDetl:wmsPrdInDetlService.getNewDetailByWN(wmsGdxdIn.getWoNo())){
				        	totalWeightThis += Double.valueOf(wmsPrdInDetl.getWeight());
				        }
				        mapThis.put("brand", brandThis);
				        mapThis.put("finishWeight", finishWeight);
				        mapThis.put("totalWeightThis", totalWeightThis);
				        mapListThis.add(mapThis);
					}
					List<Map<String, Object>> mapListLast = ListUtils.newArrayList();
					for(WmsGdxdIn wmsGdxdIn:wmsGdxdInService.getNewAllIn(dateLast)){						
						Map<String, Object> mapLast = MapUtils.newHashMap();
						String brandLast;
				        double totalWeightLast = 0;
				        brandLast = wmsGdxdIn.getMatNm();
				        for(WmsPrdInDetl wmsPrdInDetl:wmsPrdInDetlService.getNewDetailByWN(wmsGdxdIn.getWoNo())){
				        	totalWeightLast += Double.valueOf(wmsPrdInDetl.getWeight());
				        }
				        mapLast.put("brand", brandLast);
				        mapLast.put("totalWeightLast", totalWeightLast);
				        mapListLast.add(mapLast);
					}
					for(Map<String, Object> mapT:mapListThis){
						if(mapListLast != null && !mapListLast.isEmpty()){
							for(Map<String, Object> mapL:mapListLast){							
								if (mapT.get("brand").equals(mapL.get("brand"))) {
									Map<String, Object> mapF = MapUtils.newHashMap();
									mapF.put("timeVariable", i);
									mapF.put("brand", mapT.get("brand"));
									mapF.put("totalWeightThis", mapT.get("totalWeightThis"));
									mapF.put("finishWeight", mapT.get("finishWeight"));
									mapF.put("totalWeightLast", mapL.get("totalWeightLast"));
									mapList.add(mapF);
								}
							}
						} else {
							Map<String, Object> mapF = MapUtils.newHashMap();
							mapF.put("timeVariable", i);
							mapF.put("brand", mapT.get("brand"));
							mapF.put("totalWeightThis", mapT.get("totalWeightThis"));
							mapF.put("finishWeight", mapT.get("finishWeight"));
							mapF.put("totalWeightLast", 0);
							mapList.add(mapF);
						}
					}				
				}
				break;
			default:
				Map<String, Object> map1 = MapUtils.newHashMap();
				map1.put("Error", "analysisType传参错误");
				mapList.add(map1);
				break;
			}
		}else {
			Map<String, Object> map = MapUtils.newHashMap();
			map.put("Error", "timeCoordinate传参错误");
			mapList.add(map);			
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
    @RequestMapping(value = {"newProductionLine", ""})
    public List<Map<String, Object>> newProductionLine(HttpServletRequest request) {
        List<Map<String, Object>> mapList = ListUtils.newArrayList();
        String timeDomainYear = request.getParameter("timeDomainYear");
        String timeDomainMonth = request.getParameter("timeDomainMonth");        
        int timeVariable;
        Double productionCapacity = 0.0;
        for(int i=1;i<32;i++){
        	String date = timeDomainYear+".";
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
            Map<String, Object> map = MapUtils.newHashMap();
            String productionLineName;
            timeVariable = i;
            for(WmsGdxdIn wmsGdxdIn:wmsGdxdInService.getNewAllIn(date)){
            	productionLineName = wmsGdxdIn.getInLine();
            	timeVariable=i;
            	if (productionLineName.equals("1")) {
            		productionLineName = "生产线一";
            		for(WmsPrdInDetl wmsPrdInDetl:wmsPrdInDetlService.getNewDetailByBatchNo(wmsGdxdIn.getBatchNo()))
            				productionCapacity += Double.valueOf(wmsPrdInDetl.getWeight());
            		map.put("productionLineName",productionLineName);
                    map.put("timeVariable",timeVariable);
                    map.put("productionCapacity",productionCapacity);
                    mapList.add(map);
				}else if(productionLineName.equals("2")){
					productionLineName = "生产线二";
					for(WmsPrdInDetl wmsPrdInDetl:wmsPrdInDetlService.getNewDetailByBatchNo(wmsGdxdIn.getBatchNo()))
        				productionCapacity += Double.valueOf(wmsPrdInDetl.getWeight());
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
    @RequestMapping(value = {"newMonthlyBatchTime", ""})
    public List<Map<String, Object>> newMonthlyBatchTime(HttpServletRequest request) {
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
                for(WmsGdxdIn wmsGdxdIn:wmsGdxdInService.getNewBatchAndTime(date)){
                	batch = wmsGdxdIn.getBatchNo();
                	if(wmsGdxdIn.getBoxtotalnum() != null)
                		timeCost=wmsGdxdIn.getBoxtotalnum();
                	else
                		timeCost=0;
                	for(WmsPrdInDetl wmsPrdInDetl:wmsPrdInDetlService.getNewDetailByBatchNo(batch)){
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
                int timeCost;
                for(WmsGdxdOut wmsGdxdOut:wmsGdxdOutService.getNewBatchAndTime(date)){
                	batch = wmsGdxdOut.getBatchNo();   
                	if(wmsGdxdOut.getBoxtotalnum() != null)
                		timeCost=wmsGdxdOut.getBoxtotalnum();
                	else
                		timeCost=0;
                	for(WmsPrdOutDetl wmsPrdOutDetl:wmsPrdOutDetlService.getNewDetailByBatchNo(batch)){
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
