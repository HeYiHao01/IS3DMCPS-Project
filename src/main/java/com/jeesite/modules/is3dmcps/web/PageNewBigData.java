package com.jeesite.modules.is3dmcps.web;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
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
import com.jeesite.utils.CompareDate;

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
	 *或
	 *{“analysisType”:”DeliveryInfo”,”timeCoordinate”:”Yearly”}
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
		if (timeCoordinate.equals("Yearly")) {		//请求参数为年	
			switch (analysisType) {
			case "InventoryInfo":
				Map<String, Object> map = MapUtils.newHashMap();
				map.put("Error", "库存功能已砍");
				mapList.add(map);
				break;
			case "DeliveryInfo":
				for(int i=0;i<20;i++){
					int finishWeight = 0;					
					timeDomainYear = String.valueOf(Calendar.getInstance().get(Calendar.YEAR)-i);
					List<Map<String, Object>> mapListGdxd = ListUtils.newArrayList();
					for(WmsGdxdOut wmsGdxdOut:wmsGdxdOutService.getNewAllOutYearly(timeDomainYear)){
						Map<String, Object> mapGdxd = MapUtils.newHashMap();						
						if (wmsGdxdOut.getBoxtotalnum() != null) {
							finishWeight = wmsGdxdOut.getBoxtotalnum();
						}
						mapGdxd.put("brand", wmsGdxdOut.getMatNm());
						mapGdxd.put("finishWeight", finishWeight);
						mapListGdxd.add(mapGdxd);
					}
					List<Map<String, Object>> mapListThis = ListUtils.newArrayList();
					for(WmsPrdOutDetl wmsPrdOutDetl : wmsPrdOutDetlService.getNewDetailYearly(timeDomainYear)){
						Map<String, Object> mapThis = MapUtils.newHashMap();
						mapThis.put("brand", wmsPrdOutDetl.getMatNm());
						mapThis.put("totalWeightThis", Double.valueOf(wmsPrdOutDetl.getWeight()));
						mapListThis.add(mapThis);
					}
					for(Map<String, Object> mapG:mapListGdxd){
						for(Map<String, Object> mapT:mapListThis){
							if (mapG.get("brand").equals(mapT.get("brand"))) {
								Map<String, Object> mapF = MapUtils.newHashMap();
								mapF.put("timeVariable", timeDomainYear);
								mapF.put("brand", mapT.get("brand"));
								mapF.put("totalWeightThis", mapT.get("totalWeightThis"));
								mapF.put("finishWeight", mapG.get("finishWeight"));								
								mapList.add(mapF);
							}
						}
					}
				}
				break;
			case "IncomingInfo":
				for(int i=0;i<20;i++){
					int finishWeight = 0;					
					timeDomainYear = String.valueOf(Calendar.getInstance().get(Calendar.YEAR)-i);
					List<Map<String, Object>> mapListGdxd = ListUtils.newArrayList();
					for(WmsGdxdIn wmsGdxdIn:wmsGdxdInService.getNewAllInYearly(timeDomainYear)){
						Map<String, Object> mapGdxd = MapUtils.newHashMap();						
						if (wmsGdxdIn.getBoxtotalnum() != null) {
							finishWeight = wmsGdxdIn.getBoxtotalnum();
						}
						mapGdxd.put("brand", wmsGdxdIn.getMatNm());
						mapGdxd.put("finishWeight", finishWeight);
						mapListGdxd.add(mapGdxd);
					}
					List<Map<String, Object>> mapListThis = ListUtils.newArrayList();
					for(WmsPrdInDetl wmsPrdInDetl:wmsPrdInDetlService.getNewDetailYearly(timeDomainYear)){
						Map<String, Object> mapThis = MapUtils.newHashMap();
						mapThis.put("brand", wmsPrdInDetl.getMatNm());
						mapThis.put("totalWeightThis", Double.valueOf(wmsPrdInDetl.getWeight()));
						mapListThis.add(mapThis);
					}
					for(Map<String, Object> mapG:mapListGdxd){
						for(Map<String, Object> mapT:mapListThis){
							if (mapG.get("brand").equals(mapT.get("brand"))) {
								Map<String, Object> mapF = MapUtils.newHashMap();
								mapF.put("timeVariable", timeDomainYear);
								mapF.put("brand", mapT.get("brand"));
								mapF.put("totalWeightThis", mapT.get("totalWeightThis"));
								mapF.put("finishWeight", mapG.get("finishWeight"));								
								mapList.add(mapF);
							}
						}
					}
				}
				break;
			case "ProductionInfo":	//已取消，暂定和入库一样
				for(int i=0;i<20;i++){
					int finishWeight = 0;					
					timeDomainYear = String.valueOf(Calendar.getInstance().get(Calendar.YEAR)-i);
					List<Map<String, Object>> mapListGdxd = ListUtils.newArrayList();
					for(WmsGdxdIn wmsGdxdIn:wmsGdxdInService.getNewAllInYearly(timeDomainYear)){
						Map<String, Object> mapGdxd = MapUtils.newHashMap();						
						if (wmsGdxdIn.getBoxtotalnum() != null) {
							finishWeight = wmsGdxdIn.getBoxtotalnum();
						}
						mapGdxd.put("brand", wmsGdxdIn.getMatNm());
						mapGdxd.put("finishWeight", finishWeight);
						mapListGdxd.add(mapGdxd);
					}
					List<Map<String, Object>> mapListThis = ListUtils.newArrayList();
					for(WmsPrdInDetl wmsPrdInDetl:wmsPrdInDetlService.getNewDetailYearly(timeDomainYear)){
						Map<String, Object> mapThis = MapUtils.newHashMap();
						mapThis.put("brand", wmsPrdInDetl.getMatNm());
						mapThis.put("totalWeightThis", Double.valueOf(wmsPrdInDetl.getWeight()));
						mapListThis.add(mapThis);
					}
					for(Map<String, Object> mapG:mapListGdxd){
						for(Map<String, Object> mapT:mapListThis){
							if (mapG.get("brand").equals(mapT.get("brand"))) {
								Map<String, Object> mapF = MapUtils.newHashMap();
								mapF.put("timeVariable", timeDomainYear);
								mapF.put("brand", mapT.get("brand"));
								mapF.put("totalWeightThis", mapT.get("totalWeightThis"));
								mapF.put("finishWeight", mapG.get("finishWeight"));								
								mapList.add(mapF);
							}
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
		}else if (timeCoordinate.equals("Monthly")) {  //请求参数为月
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
					List<Map<String, Object>> mapListGdxd = ListUtils.newArrayList();
					List<Map<String, Object>> mapListThis = ListUtils.newArrayList();
					List<Map<String, Object>> mapListLast = ListUtils.newArrayList();
					int finishWeight = 0;
					for (WmsGdxdOut wmsGdxdOut : wmsGdxdOutService.getNewAllOutMonthly(dateThis)) {
						Map<String, Object> mapGdxd = MapUtils.newHashMap();
						if (wmsGdxdOut.getBoxtotalnum() != null) {
							finishWeight = wmsGdxdOut.getBoxtotalnum();
						}
						mapGdxd.put("brand", wmsGdxdOut.getMatNm());
						mapGdxd.put("finishWeight", finishWeight);
						mapListGdxd.add(mapGdxd);
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
					for(Map<String, Object> mapG:mapListGdxd){
						for (Map<String, Object> mapT : mapListThis) {
							if (mapListLast != null && !mapListLast.isEmpty()) {
								for (Map<String, Object> mapL : mapListLast) {
									if (mapT.get("brand").equals(mapL.get("brand")) &&
											mapT.get("brand").equals(mapG.get("brand")) &&
											mapL.get("brand").equals(mapG.get("brand"))) {
										Map<String, Object> mapF = MapUtils.newHashMap();
										mapF.put("timeVariable", j);
										mapF.put("brand", mapT.get("brand"));
										mapF.put("totalWeightThis", mapT.get("totalWeightThis"));
										mapF.put("finishWeight", mapG.get("finishWeight"));
										mapF.put("totalWeightLast", mapL.get("totalWeightLast"));
										mapList.add(mapF);
									}
								}
							} else {
								if (mapT.get("brand").equals(mapG.get("brand"))) {
									Map<String, Object> mapF = MapUtils.newHashMap();
									mapF.put("timeVariable", j);
									mapF.put("brand", mapT.get("brand"));
									mapF.put("totalWeightThis", mapT.get("totalWeightThis"));
									mapF.put("finishWeight", mapG.get("finishWeight"));
									mapF.put("totalWeightLast", 0);
									mapList.add(mapF);
								}								
							}
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
					List<Map<String, Object>> mapListGdxd = ListUtils.newArrayList();
					List<Map<String, Object>> mapListThis = ListUtils.newArrayList();
					List<Map<String, Object>> mapListLast = ListUtils.newArrayList();
					int finishWeight = 0;
					for (WmsGdxdIn wmsGdxdIn : wmsGdxdInService.getNewAllInMonthly(dateThis)) {
						Map<String, Object> mapGdxd = MapUtils.newHashMap();
						if (wmsGdxdIn.getBoxtotalnum() != null) {
							finishWeight = wmsGdxdIn.getBoxtotalnum();
						}
						mapGdxd.put("brand", wmsGdxdIn.getMatNm());
						mapGdxd.put("finishWeight", finishWeight);
						mapListGdxd.add(mapGdxd);
					}
					for (WmsPrdInDetl wmsPrdInDetl : wmsPrdInDetlService.getNewDetailMonthly(dateThis)) {
						Map<String, Object> mapThis = MapUtils.newHashMap();
						mapThis.put("brand", wmsPrdInDetl.getMatNm());						
						mapThis.put("totalWeightThis", Double.valueOf(wmsPrdInDetl.getWeight()));
						mapListThis.add(mapThis);
					}
					for (WmsPrdInDetl wmsPrdInDetl : wmsPrdInDetlService.getNewDetailMonthly(dateLast)) {
						Map<String, Object> mapLast = MapUtils.newHashMap();
						mapLast.put("brand", wmsPrdInDetl.getMatNm());
						mapLast.put("totalWeightLast", Double.valueOf(wmsPrdInDetl.getWeight()));
						mapListLast.add(mapLast);
					}
					for(Map<String, Object> mapG:mapListGdxd){
						for (Map<String, Object> mapT : mapListThis) {
							if (mapListLast != null && !mapListLast.isEmpty()) {
								for (Map<String, Object> mapL : mapListLast) {
									if (mapT.get("brand").equals(mapL.get("brand")) &&
											mapT.get("brand").equals(mapG.get("brand")) &&
											mapL.get("brand").equals(mapG.get("brand"))) {
										Map<String, Object> mapF = MapUtils.newHashMap();
										mapF.put("timeVariable", j);
										mapF.put("brand", mapT.get("brand"));
										mapF.put("totalWeightThis", mapT.get("totalWeightThis"));
										mapF.put("finishWeight", mapG.get("finishWeight"));
										mapF.put("totalWeightLast", mapL.get("totalWeightLast"));
										mapList.add(mapF);
									}
								}
							} else {
								if (mapT.get("brand").equals(mapG.get("brand"))) {
									Map<String, Object> mapF = MapUtils.newHashMap();
									mapF.put("timeVariable", j);
									mapF.put("brand", mapT.get("brand"));
									mapF.put("totalWeightThis", mapT.get("totalWeightThis"));
									mapF.put("finishWeight", mapG.get("finishWeight"));
									mapF.put("totalWeightLast", 0);
									mapList.add(mapF);
								}								
							}
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
					List<Map<String, Object>> mapListGdxd = ListUtils.newArrayList();
					List<Map<String, Object>> mapListThis = ListUtils.newArrayList();
					List<Map<String, Object>> mapListLast = ListUtils.newArrayList();
					int finishWeight = 0;
					for (WmsGdxdIn wmsGdxdIn : wmsGdxdInService.getNewAllInMonthly(dateThis)) {
						Map<String, Object> mapGdxd = MapUtils.newHashMap();
						if (wmsGdxdIn.getBoxtotalnum() != null) {
							finishWeight = wmsGdxdIn.getBoxtotalnum();
						}
						mapGdxd.put("brand", wmsGdxdIn.getMatNm());
						mapGdxd.put("finishWeight", finishWeight);
						mapListGdxd.add(mapGdxd);
					}
					for (WmsPrdInDetl wmsPrdInDetl : wmsPrdInDetlService.getNewDetailMonthly(dateThis)) {
						Map<String, Object> mapThis = MapUtils.newHashMap();
						mapThis.put("brand", wmsPrdInDetl.getMatNm());						
						mapThis.put("totalWeightThis", Double.valueOf(wmsPrdInDetl.getWeight()));
						mapListThis.add(mapThis);
					}
					for (WmsPrdInDetl wmsPrdInDetl : wmsPrdInDetlService.getNewDetailMonthly(dateLast)) {
						Map<String, Object> mapLast = MapUtils.newHashMap();
						mapLast.put("brand", wmsPrdInDetl.getMatNm());
						mapLast.put("totalWeightLast", Double.valueOf(wmsPrdInDetl.getWeight()));
						mapListLast.add(mapLast);
					}
					for(Map<String, Object> mapG:mapListGdxd){
						for (Map<String, Object> mapT : mapListThis) {
							if (mapListLast != null && !mapListLast.isEmpty()) {
								for (Map<String, Object> mapL : mapListLast) {
									if (mapT.get("brand").equals(mapL.get("brand")) &&
											mapT.get("brand").equals(mapG.get("brand")) &&
											mapL.get("brand").equals(mapG.get("brand"))) {
										Map<String, Object> mapF = MapUtils.newHashMap();
										mapF.put("timeVariable", j);
										mapF.put("brand", mapT.get("brand"));
										mapF.put("totalWeightThis", mapT.get("totalWeightThis"));
										mapF.put("finishWeight", mapG.get("finishWeight"));
										mapF.put("totalWeightLast", mapL.get("totalWeightLast"));
										mapList.add(mapF);
									}
								}
							} else {
								if (mapT.get("brand").equals(mapG.get("brand"))) {
									Map<String, Object> mapF = MapUtils.newHashMap();
									mapF.put("timeVariable", j);
									mapF.put("brand", mapT.get("brand"));
									mapF.put("totalWeightThis", mapT.get("totalWeightThis"));
									mapF.put("finishWeight", mapG.get("finishWeight"));
									mapF.put("totalWeightLast", 0);
									mapList.add(mapF);
								}								
							}
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
		}else if (timeCoordinate.equals("Daily")) {  //请求参数为日
			switch (analysisType) {
			case "InventoryInfo":
				Map<String, Object> map = MapUtils.newHashMap();
				map.put("Error", "库存功能已砍");
				mapList.add(map);
				break;				
			case "DeliveryInfo":
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
					List<Map<String, Object>> mapListGdxd = ListUtils.newArrayList();
					List<Map<String, Object>> mapListThis = ListUtils.newArrayList();
					for(WmsGdxdOut wmsGdxdOut:wmsGdxdOutService.getNewAllOut(dateThis)){					
						Map<String, Object> mapGdxd = MapUtils.newHashMap();				        				       
				        int finishWeight = 0;
				        if(wmsGdxdOut.getBoxtotalnum() != null)
				        	finishWeight=wmsGdxdOut.getBoxtotalnum();			
				        mapGdxd.put("brand", wmsGdxdOut.getMatNm());
				        mapGdxd.put("finishWeight", finishWeight);				        
				        mapListGdxd.add(mapGdxd);
					}					
					for(WmsPrdOutDetl wmsPrdOutDetl:wmsPrdOutDetlService.getNewDetailDaily(dateThis)){						
						Map<String, Object> mapThis = MapUtils.newHashMap();
						mapThis.put("brand", wmsPrdOutDetl.getMatNm());						
						mapThis.put("totalWeightThis", Double.valueOf(wmsPrdOutDetl.getWeight()));
						mapListThis.add(mapThis);
					}
					List<Map<String, Object>> mapListLast = ListUtils.newArrayList();
					for(WmsPrdOutDetl wmsPrdOutDetl:wmsPrdOutDetlService.getNewDetailDaily(dateLast)){						
						Map<String, Object> mapLast = MapUtils.newHashMap();
						mapLast.put("brand", wmsPrdOutDetl.getMatNm());						
						mapLast.put("totalWeightLast", Double.valueOf(wmsPrdOutDetl.getWeight()));
						mapListLast.add(mapLast);
					}
					for(Map<String, Object> mapG:mapListGdxd){
						for (Map<String, Object> mapT : mapListThis) {
							if (mapListLast != null && !mapListLast.isEmpty()) {
								for (Map<String, Object> mapL : mapListLast) {
									if (mapT.get("brand").equals(mapL.get("brand")) &&
											mapT.get("brand").equals(mapG.get("brand")) &&
											mapL.get("brand").equals(mapG.get("brand"))) {
										Map<String, Object> mapF = MapUtils.newHashMap();
										mapF.put("timeVariable", i);
										mapF.put("brand", mapT.get("brand"));
										mapF.put("totalWeightThis", mapT.get("totalWeightThis"));
										mapF.put("finishWeight", mapG.get("finishWeight"));
										mapF.put("totalWeightLast", mapL.get("totalWeightLast"));
										mapList.add(mapF);
									}
								}
							} else {
								if (mapT.get("brand").equals(mapG.get("brand"))) {
									Map<String, Object> mapF = MapUtils.newHashMap();
									mapF.put("timeVariable", i);
									mapF.put("brand", mapT.get("brand"));
									mapF.put("totalWeightThis", mapT.get("totalWeightThis"));
									mapF.put("finishWeight", mapG.get("finishWeight"));
									mapF.put("totalWeightLast", 0);
									mapList.add(mapF);
								}								
							}
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
					List<Map<String, Object>> mapListGdxd = ListUtils.newArrayList();
					List<Map<String, Object>> mapListThis = ListUtils.newArrayList();
					for(WmsGdxdIn wmsGdxdIn:wmsGdxdInService.getNewAllIn(dateThis)){					
						Map<String, Object> mapGdxd = MapUtils.newHashMap();				        				       
				        int finishWeight = 0;
				        if(wmsGdxdIn.getBoxtotalnum() != null)
				        	finishWeight=wmsGdxdIn.getBoxtotalnum();			
				        mapGdxd.put("brand", wmsGdxdIn.getMatNm());
				        mapGdxd.put("finishWeight", finishWeight);				        
				        mapListGdxd.add(mapGdxd);
					}					
					for(WmsPrdInDetl wmsPrdInDetl:wmsPrdInDetlService.getNewDetailDaily(dateThis)){						
						Map<String, Object> mapThis = MapUtils.newHashMap();
						mapThis.put("brand", wmsPrdInDetl.getMatNm());						
						mapThis.put("totalWeightThis", Double.valueOf(wmsPrdInDetl.getWeight()));
						mapListThis.add(mapThis);
					}
					List<Map<String, Object>> mapListLast = ListUtils.newArrayList();
					for(WmsPrdInDetl wmsPrdInDetl:wmsPrdInDetlService.getNewDetailDaily(dateLast)){						
						Map<String, Object> mapLast = MapUtils.newHashMap();
						mapLast.put("brand", wmsPrdInDetl.getMatNm());						
						mapLast.put("totalWeightLast", Double.valueOf(wmsPrdInDetl.getWeight()));
						mapListLast.add(mapLast);
					}
					for(Map<String, Object> mapG:mapListGdxd){
						for (Map<String, Object> mapT : mapListThis) {
							if (mapListLast != null && !mapListLast.isEmpty()) {
								for (Map<String, Object> mapL : mapListLast) {
									if (mapT.get("brand").equals(mapL.get("brand")) &&
											mapT.get("brand").equals(mapG.get("brand")) &&
											mapL.get("brand").equals(mapG.get("brand"))) {
										Map<String, Object> mapF = MapUtils.newHashMap();
										mapF.put("timeVariable", i);
										mapF.put("brand", mapT.get("brand"));
										mapF.put("totalWeightThis", mapT.get("totalWeightThis"));
										mapF.put("finishWeight", mapG.get("finishWeight"));
										mapF.put("totalWeightLast", mapL.get("totalWeightLast"));
										mapList.add(mapF);
									}
								}
							} else {
								if (mapT.get("brand").equals(mapG.get("brand"))) {
									Map<String, Object> mapF = MapUtils.newHashMap();
									mapF.put("timeVariable", i);
									mapF.put("brand", mapT.get("brand"));
									mapF.put("totalWeightThis", mapT.get("totalWeightThis"));
									mapF.put("finishWeight", mapG.get("finishWeight"));
									mapF.put("totalWeightLast", 0);
									mapList.add(mapF);
								}								
							}
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
					List<Map<String, Object>> mapListGdxd = ListUtils.newArrayList();
					List<Map<String, Object>> mapListThis = ListUtils.newArrayList();
					for(WmsGdxdIn wmsGdxdIn:wmsGdxdInService.getNewAllIn(dateThis)){					
						Map<String, Object> mapGdxd = MapUtils.newHashMap();				        				       
				        int finishWeight = 0;
				        if(wmsGdxdIn.getBoxtotalnum() != null)
				        	finishWeight=wmsGdxdIn.getBoxtotalnum();			
				        mapGdxd.put("brand", wmsGdxdIn.getMatNm());
				        mapGdxd.put("finishWeight", finishWeight);				        
				        mapListGdxd.add(mapGdxd);
					}					
					for(WmsPrdInDetl wmsPrdInDetl:wmsPrdInDetlService.getNewDetailDaily(dateThis)){						
						Map<String, Object> mapThis = MapUtils.newHashMap();
						mapThis.put("brand", wmsPrdInDetl.getMatNm());						
						mapThis.put("totalWeightThis", Double.valueOf(wmsPrdInDetl.getWeight()));
						mapListThis.add(mapThis);
					}
					List<Map<String, Object>> mapListLast = ListUtils.newArrayList();
					for(WmsPrdInDetl wmsPrdInDetl:wmsPrdInDetlService.getNewDetailDaily(dateLast)){						
						Map<String, Object> mapLast = MapUtils.newHashMap();
						mapLast.put("brand", wmsPrdInDetl.getMatNm());						
						mapLast.put("totalWeightLast", Double.valueOf(wmsPrdInDetl.getWeight()));
						mapListLast.add(mapLast);
					}
					for(Map<String, Object> mapG:mapListGdxd){
						for (Map<String, Object> mapT : mapListThis) {
							if (mapListLast != null && !mapListLast.isEmpty()) {
								for (Map<String, Object> mapL : mapListLast) {
									if (mapT.get("brand").equals(mapL.get("brand")) &&
											mapT.get("brand").equals(mapG.get("brand")) &&
											mapL.get("brand").equals(mapG.get("brand"))) {
										Map<String, Object> mapF = MapUtils.newHashMap();
										mapF.put("timeVariable", i);
										mapF.put("brand", mapT.get("brand"));
										mapF.put("totalWeightThis", mapT.get("totalWeightThis"));
										mapF.put("finishWeight", mapG.get("finishWeight"));
										mapF.put("totalWeightLast", mapL.get("totalWeightLast"));
										mapList.add(mapF);
									}
								}
							} else {
								if (mapT.get("brand").equals(mapG.get("brand"))) {
									Map<String, Object> mapF = MapUtils.newHashMap();
									mapF.put("timeVariable", i);
									mapF.put("brand", mapT.get("brand"));
									mapF.put("totalWeightThis", mapT.get("totalWeightThis"));
									mapF.put("finishWeight", mapG.get("finishWeight"));
									mapF.put("totalWeightLast", 0);
									mapList.add(mapF);
								}								
							}
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
            Double productionCapacity = 0.0;
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
        return CompareDate.removeRepeatMapByKey(mapList, "timeVariable");
    }
    
    /**
     * 新增出库能力分析
     * @param request
     * @return
     */
    @RequestMapping(value = {"newProductionFedWire", ""})
    public List<Map<String, Object>> newProductionFedWire(HttpServletRequest request) {
    	List<Map<String, Object>> mapList = ListUtils.newArrayList();
        String timeDomainYear = request.getParameter("timeDomainYear");
        String timeDomainMonth = request.getParameter("timeDomainMonth");        
        int timeVariable;
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
            Double productionCapacity = 0.0;
            
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
    
    /**
     * 数据中心新增部分
     */
    
    /**
     * 增加班组、批次信息管理
     * analysisType取值：”DeliveryInfo”、”IncomingInfo”，表示出库、入库
		timeCoordinate取值：”Daily”、”Monthly”，表示时间轴坐标为日或者月。
		timeDomainYear（年选择）、timeDomainMonth（月选择）：在时间轴为月（Monthly）时只发送timeDomainYear，时间轴为日（Daily）时发送timeDomainYear和timeDomainMonth。
		Post: 
		{“analysisType”:”DeliveryInfo”,”timeCoordinate”:” Monthly”,” timeDomainYear”:2018}
		或
		{“analysisType”:”DeliveryInfo”,”timeCoordinate”:”Daily”,”timeDomainYear”:2019,”timeDomainMonth”:5}
		增加年筛选
		或
		{“analysisType”:”DeliveryInfo”,”timeCoordinate”:”Yearly”}
	 *
	 *Json：
	 *品牌筛选使用以前的
		
		以批次筛选：
		[{ “batch”:”xxx”,“totalWeightThis”:25000,“totalWeightLast”:24000,”timeVariable”:10},{ “batch”:”xxx”,“totalWeightThis”:25000,“totalWeightLast”:24000,”timeVariable”:10}]
		以班组筛选：
		[{“classTeam”:”xxx”,“totalWeightThis”:25000,“totalWeightLast”:24000,”timeVariable”:10},{ “classTeam”:”xxx”,“totalWeightThis”:25000,“totalWeightLast”:24000,”timeVariable”:10}]
     * @return
     */
    @RequestMapping(value = "batchInfoManage")
    public List<Map<String, Object>> batchInfoManage(HttpServletRequest request) {
    	String analysisType = request.getParameter("analysisType");
		String timeCoordinate = request.getParameter("timeCoordinate");
		String timeDomainYear = request.getParameter("timeDomainYear");		
		List<Map<String, Object>> mapList = ListUtils.newArrayList();		
		if (timeCoordinate.equals("Yearly")) {		//请求参数为年	
			switch (analysisType) {			
			case "DeliveryInfo":
				for(int i=0;i<20;i++){									
					timeDomainYear = String.valueOf(Calendar.getInstance().get(Calendar.YEAR)-i);
					for (WmsPrdOutDetl wmsPrdOutDetl : wmsPrdOutDetlService.getBatchWeightYearly(timeDomainYear)) {
						Map<String, Object> mapThis = MapUtils.newHashMap();
						mapThis.put("batch", wmsPrdOutDetl.getBatchNo());
						mapThis.put("totalWeightThis", Double.valueOf(wmsPrdOutDetl.getWeight()));
						mapThis.put("timeVariable", timeDomainYear);
						mapList.add(mapThis);
					}
				}
			break;
			case "IncomingInfo":
				for(int i=0;i<20;i++){									
					timeDomainYear = String.valueOf(Calendar.getInstance().get(Calendar.YEAR)-i);
					for (WmsPrdInDetl wmsPrdInDetl : wmsPrdInDetlService.getBatchWeightYearly(timeDomainYear)) {
						Map<String, Object> mapThis = MapUtils.newHashMap();
						mapThis.put("batch", wmsPrdInDetl.getBatchNo());
						mapThis.put("totalWeightThis", Double.valueOf(wmsPrdInDetl.getWeight()));
						mapThis.put("timeVariable", timeDomainYear);
						mapList.add(mapThis);
					}
				}
			break;
			default:
				Map<String, Object> map1 = MapUtils.newHashMap();
				map1.put("Error", "analysisType传参错误");
				mapList.add(map1);
				break;
			}
		}else if (timeCoordinate.equals("Monthly")) {
			switch (analysisType) {			
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
					for (WmsPrdOutDetl wmsPrdOutDetl : wmsPrdOutDetlService.getBatchWeightMonthly(dateThis)) {
						Map<String, Object> mapThis = MapUtils.newHashMap();
						mapThis.put("batch", wmsPrdOutDetl.getBatchNo());
						mapThis.put("totalWeightThis", Double.valueOf(wmsPrdOutDetl.getWeight()));
						mapListThis.add(mapThis);
					}
					//System.err.println(mapListThis);
					for (WmsPrdOutDetl wmsPrdOutDetl : wmsPrdOutDetlService.getBatchWeightMonthly(dateLast)) {
						Map<String, Object> mapLast = MapUtils.newHashMap();
						mapLast.put("batch", wmsPrdOutDetl.getBatchNo());
						mapLast.put("totalWeightLast", Double.valueOf(wmsPrdOutDetl.getWeight()));
						mapListLast.add(mapLast);
					}
					//System.err.println(mapListLast);
					for (Map<String, Object> mapT : mapListThis) {
						if (mapListLast != null && !mapListLast.isEmpty()) {
							for (Map<String, Object> mapL : mapListLast) {
								if (mapT.get("batch").equals(mapL.get("batch"))) {
									Map<String, Object> mapF = MapUtils.newHashMap();
									mapF.put("timeVariable", j);
									mapF.put("batch", mapT.get("batch"));
									mapF.put("totalWeightThis", mapT.get("totalWeightThis"));									
									mapF.put("totalWeightLast", mapL.get("totalWeightLast"));
									mapList.add(mapF);
								}
							}
						} else {
							Map<String, Object> mapF = MapUtils.newHashMap();
							mapF.put("timeVariable", j);
							mapF.put("batch", mapT.get("batch"));
							mapF.put("totalWeightThis", mapT.get("totalWeightThis"));							
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
					// System.err.println(dateThis);
					for (WmsPrdInDetl wmsPrdInDetl : wmsPrdInDetlService.getBatchWeightMonthly(dateThis)) {
						Map<String, Object> mapThis = MapUtils.newHashMap();
						mapThis.put("batch", wmsPrdInDetl.getBatchNo());						
						mapThis.put("totalWeightThis", Double.valueOf(wmsPrdInDetl.getWeight()));
						mapListThis.add(mapThis);
					}
					for (WmsPrdInDetl wmsPrdOutDetl : wmsPrdInDetlService.getBatchWeightMonthly(dateLast)) {
						Map<String, Object> mapLast = MapUtils.newHashMap();
						mapLast.put("batch", wmsPrdOutDetl.getBatchNo());
						mapLast.put("totalWeightLast", Double.valueOf(wmsPrdOutDetl.getWeight()));
						mapListLast.add(mapLast);
					}
					for (Map<String, Object> mapT : mapListThis) {
						if (mapListLast != null && !mapListLast.isEmpty()) {
							for (Map<String, Object> mapL : mapListLast) {
								if (mapT.get("batch").equals(mapL.get("batch"))) {
									Map<String, Object> mapF = MapUtils.newHashMap();
									mapF.put("timeVariable", j);
									mapF.put("batch", mapT.get("batch"));
									mapF.put("totalWeightThis", mapT.get("totalWeightThis"));									
									mapF.put("totalWeightLast", mapL.get("totalWeightLast"));
									mapList.add(mapF);
								}
							}
						} else {
							Map<String, Object> mapF = MapUtils.newHashMap();
							mapF.put("timeVariable", j);
							mapF.put("batch", mapT.get("batch"));
							mapF.put("totalWeightThis", mapT.get("totalWeightThis"));							
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
					for(WmsPrdOutDetl wmsPrdOutDetl:wmsPrdOutDetlService.getBatchWeightDaily(dateThis)){
						Map<String, Object> mapThis = MapUtils.newHashMap();
						String brandThis;
						double totalWeightThis = 0;																		
						brandThis = wmsPrdOutDetl.getBatchNo();						
						totalWeightThis = Double.valueOf(wmsPrdOutDetl.getWeight());						
						mapThis.put("batch", brandThis);						
						mapThis.put("totalWeightThis", totalWeightThis);
						mapListThis.add(mapThis);
					}
					List<Map<String, Object>> mapListLast = ListUtils.newArrayList();
					for(WmsPrdOutDetl wmsPrdOutDetl:wmsPrdOutDetlService.getBatchWeightDaily(dateLast)){
						Map<String, Object> mapLast = MapUtils.newHashMap();
						String brandLast;
						double totalWeightLast = 0;
						brandLast = wmsPrdOutDetl.getBatchNo();					
						totalWeightLast = Double.valueOf(wmsPrdOutDetl.getWeight());						
						mapLast.put("batch", brandLast);
						mapLast.put("totalWeightLast", totalWeightLast);
						mapListLast.add(mapLast);
					}
					for (Map<String, Object> mapT : mapListThis) {
						if (mapListLast != null && !mapListLast.isEmpty()) {
							for (Map<String, Object> mapL : mapListLast) {
								if (mapT.get("batch").equals(mapL.get("batch"))) {
									Map<String, Object> mapF = MapUtils.newHashMap();
									mapF.put("timeVariable", i);
									mapF.put("batch", mapT.get("batch"));
									mapF.put("totalWeightThis", mapT.get("totalWeightThis"));									
									mapF.put("totalWeightLast", mapL.get("totalWeightLast"));
									mapList.add(mapF);
								}
							}
						} else {
							Map<String, Object> mapF = MapUtils.newHashMap();
							mapF.put("timeVariable", i);
							mapF.put("batch", mapT.get("batch"));
							mapF.put("totalWeightThis", mapT.get("totalWeightThis"));							
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
					for(WmsPrdInDetl wmsPrdInDetl:wmsPrdInDetlService.getBatchWeightDaily(dateThis)){					
						Map<String, Object> mapThis = MapUtils.newHashMap();
						String brandThis;
				        double totalWeightThis = 0;
				        brandThis = wmsPrdInDetl.getBatchNo();				        				        				        
				        totalWeightThis = Double.valueOf(wmsPrdInDetl.getWeight());				        
				        mapThis.put("batch", brandThis);				        
				        mapThis.put("totalWeightThis", totalWeightThis);
				        mapListThis.add(mapThis);
					}
					List<Map<String, Object>> mapListLast = ListUtils.newArrayList();
					for(WmsPrdInDetl wmsPrdInDetl:wmsPrdInDetlService.getBatchWeightDaily(dateLast)){						
						Map<String, Object> mapLast = MapUtils.newHashMap();
						String brandLast;
				        double totalWeightLast = 0;
				        brandLast = wmsPrdInDetl.getBatchNo();				        
				        totalWeightLast += Double.valueOf(wmsPrdInDetl.getWeight());				        
				        mapLast.put("batch", brandLast);
				        mapLast.put("totalWeightLast", totalWeightLast);
				        mapListLast.add(mapLast);
					}
					for(Map<String, Object> mapT:mapListThis){
						if(mapListLast != null && !mapListLast.isEmpty()){
							for(Map<String, Object> mapL:mapListLast){							
								if (mapT.get("batch").equals(mapL.get("batch"))) {
									Map<String, Object> mapF = MapUtils.newHashMap();
									mapF.put("timeVariable", i);
									mapF.put("batch", mapT.get("batch"));
									mapF.put("totalWeightThis", mapT.get("totalWeightThis"));									
									mapF.put("totalWeightLast", mapL.get("totalWeightLast"));
									mapList.add(mapF);
								}
							}
						} else {
							Map<String, Object> mapF = MapUtils.newHashMap();
							mapF.put("timeVariable", i);
							mapF.put("batch", mapT.get("batch"));
							mapF.put("totalWeightThis", mapT.get("totalWeightThis"));							
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
     * 班组弹窗
     * Post: 
	{“classTeam”:”classTeam”,“analysisType”:”DeliveryInfo”,”timeCoordinate”:” Monthly”,” timeDomainYear”:2018}
	或
	{“classTeam”:”classTeam”,“analysisType”:”DeliveryInfo”,”timeCoordinate”:”Daily”,”timeDomainYear”:2019,”timeDomainMonth”:5}
	或
	{“classTeam”:”classTeam”,“analysisType”:”DeliveryInfo”,”timeCoordinate”:”Yearly”}
	Json:  
	{”brand”:”XXXX”,batchNumber”:50}

     * @param request
     * @return
     */
    @RequestMapping(value = "classInfoPop")
    public List<Map<String, Object>> classInfoPop(HttpServletRequest request) {
    	String analysisType = request.getParameter("analysisType");
		String timeCoordinate = request.getParameter("timeCoordinate");
		String timeDomainYear = request.getParameter("timeDomainYear");	
		String classTeam = request.getParameter("classTeam");
		List<Map<String, Object>> mapList = ListUtils.newArrayList();
		if (timeCoordinate.equals("Yearly")) {		//请求参数为年	
			switch (analysisType) {			
			case "DeliveryInfo":
				for(int i=0;i<20;i++){									
					timeDomainYear = String.valueOf(Calendar.getInstance().get(Calendar.YEAR)-i);
					for (WmsPrdOutDetl wmsPrdOutDetl : wmsPrdOutDetlService.getBatchCountYearly(classTeam, timeDomainYear)) {
						Map<String, Object> mapThis = MapUtils.newHashMap();
						mapThis.put("brand", wmsPrdOutDetl.getMatNm());
						mapThis.put("batchNumber", Integer.parseInt(wmsPrdOutDetl.getBatchNo()));						
						mapList.add(mapThis);
					}
				}
			break;
			case "IncomingInfo":
				for(int i=0;i<20;i++){									
					timeDomainYear = String.valueOf(Calendar.getInstance().get(Calendar.YEAR)-i);
					for (WmsPrdInDetl wmsPrdInDetl : wmsPrdInDetlService.getBatchCountYearly(classTeam, timeDomainYear)) {
						Map<String, Object> mapThis = MapUtils.newHashMap();
						mapThis.put("brand", wmsPrdInDetl.getMatNm());
						mapThis.put("batchNumber", Integer.parseInt(wmsPrdInDetl.getBatchNo()));
						mapList.add(mapThis);
					}
				}
			break;
			default:
				Map<String, Object> map1 = MapUtils.newHashMap();
				map1.put("Error", "analysisType传参错误");
				mapList.add(map1);
				break;
			}
		}else if (timeCoordinate.equals("Monthly")) {
			switch (analysisType) {			
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
					for (WmsPrdOutDetl wmsPrdOutDetl : wmsPrdOutDetlService.getBatchCountYearly(classTeam, dateThis)) {
						Map<String, Object> mapThis = MapUtils.newHashMap();
						mapThis.put("brand", wmsPrdOutDetl.getMatNm());
						mapThis.put("batchNumber", Integer.parseInt(wmsPrdOutDetl.getBatchNo()));						
						mapList.add(mapThis);
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
					for (WmsPrdInDetl wmsPrdInDetl : wmsPrdInDetlService.getBatchCountMonthly(classTeam, dateThis)) {
						Map<String, Object> mapThis = MapUtils.newHashMap();
						mapThis.put("brand", wmsPrdInDetl.getMatNm());
						mapThis.put("batchNumber", Integer.parseInt(wmsPrdInDetl.getBatchNo()));						
						mapList.add(mapThis);
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
					for(WmsPrdOutDetl wmsPrdOutDetl:wmsPrdOutDetlService.getBatchCountDaily(classTeam, dateThis)){
						Map<String, Object> mapThis = MapUtils.newHashMap();
						mapThis.put("brand", wmsPrdOutDetl.getMatNm());
						mapThis.put("batchNumber", Integer.parseInt(wmsPrdOutDetl.getBatchNo()));						
						mapList.add(mapThis);
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
					for(WmsPrdInDetl wmsPrdInDetl:wmsPrdInDetlService.getBatchCountDaily(classTeam, dateThis)){					
						Map<String, Object> mapThis = MapUtils.newHashMap();										       
				        mapThis.put("brand", wmsPrdInDetl.getMatNm());
						mapThis.put("batchNumber", Integer.parseInt(wmsPrdInDetl.getBatchNo()));						
						mapList.add(mapThis);
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
     * 列举所有装箱线
	 *Json:
	 *{"packingLine":["xxx","xxx"]}
	 *
	 *2.1.1.	装箱线
	（1）列举所有装箱线、装箱线对应的所有品牌
	Json:
	{"packingLine":["xxx","xxx"],"brand":["xxx","xxx"]}

     * @return
     */
    @RequestMapping(value = "packingLine")
    public Map<String, Object> packingLine() {
		Map<String, Object> map = MapUtils.newHashMap();
		List<String> list1 = new ArrayList<>();
		List<String> list2 = new ArrayList<>();
		for(WmsGdxdIn wmsGdxdIn:wmsGdxdInService.getPackingLine()){
			list1.add(wmsGdxdIn.getInLine());
		}
		for(WmsGdxdIn wmsGdxdIn:wmsGdxdInService.getBrands()){
			list2.add(wmsGdxdIn.getMatNm());
		}
		map.put("packingLine", list1);
		map.put("brand", list2);
		return map;
	}
    /*public Map<String, Object> packingLine() {
		Map<String, Object> map = MapUtils.newHashMap();
		List<String> list = new ArrayList<>();
		for(WmsGdxdIn wmsGdxdIn:wmsGdxdInService.getPackingLine()){
			list.add(wmsGdxdIn.getInLine());
		}
		map.put("packingLine", list);
		return map;
	}*/
    
    /**
     * 通过装箱线筛选数据
	 *Post：packingLine
	 *
	 *通过装箱线筛选数据，增加品牌、开始时间、结束时间和分页范围rangeStart、rangeEnd
	Post：{"packingLine":"xxx","brand":"xxx","startTime":"xxx","endTime":"xxx","rangeStart":1,"rangeEnd":500}

	 *Json:
	 *[{"workOrderNumber": "xxx","batchNumber": "xxx","processCoding": "XXX","workOrderStatus": "xxx","brandClassificationName": "xxx","brandName": "XXX","stock": "XXX","sendTime": "XXX","state": "XXX","startTime": "XXX","endTime": "XXX","boxTotalNum": "XXX","inStation": "XXX","batchWeight": "XXX","boxLoadWeight": "XXX","classShift": "XXX","classTeam": "XXX","inTotalWeight": "XXX"}]
     * @return
     */
    @RequestMapping(value = {"filterByPackingLine",""})
    public List<Map<String, Object>> filterByPackingLine(HttpServletRequest request) {
    	List<Map<String, Object>> mapList = ListUtils.newArrayList();
    	String packingLine = request.getParameter("packingLine");
    	String brand = request.getParameter("brand");
    	if (brand == null || brand.equals("")) {
    		for(WmsGdxdIn wmsGdxdIn:wmsGdxdInService.getAllByPackingLine(packingLine)){
        		Map<String, Object> map = MapUtils.newHashMap();
        		String workOrderNumber = wmsGdxdIn.getWoNo();
        		String batchNumber = wmsGdxdIn.getBatchNo();
        		String processCoding = wmsGdxdIn.getTechSectionCd();
        		String workOrderStatus = wmsGdxdIn.getWoState();
        		String brandClassificationName = wmsGdxdIn.getMatClassNm();
        		String brandName = wmsGdxdIn.getMatNm();
        		Double Stocking = wmsGdxdIn.getPlanAmount();
        		/*System.err.println(wmsGdxdIn.getWoSendTime());
        		System.err.println(wmsGdxdIn.getWoStartTime());
        		System.err.println(wmsGdxdIn.getWoEndTime());*/
        		String sendTime = CompareDate.simplifyDate(wmsGdxdIn.getWoSendTime());
        		String state = wmsGdxdIn.getState();
        		String startTime = CompareDate.simplifyDate(wmsGdxdIn.getWoStartTime());    		    		   		
        		String endTime = CompareDate.simplifyDate(wmsGdxdIn.getWoEndTime());
        		int boxTotalNum = wmsGdxdIn.getBoxtotalnum();
        		String inStation = "";
        		if (wmsGdxdIn.getStation() != null) {
    				inStation = wmsGdxdIn.getStation();
    			}
        		double batchWeight = 0;
        		if (wmsGdxdIn.getBatchweight() != null) {
    				batchWeight = wmsGdxdIn.getBatchweight();
    			}
        		double boxLoadWeight = 0;
        		if (wmsGdxdIn.getBoxloadweight() != null) {
    				boxLoadWeight = wmsGdxdIn.getBoxloadweight();
    			}
        		String classShift = "null";
        		if (wmsGdxdIn.getShiftCd() != null) {
    				classShift = wmsGdxdIn.getShiftCd();
    			}
        		String classTeam = "null";
        		if (wmsGdxdIn.getTeamCd() != null) {
    				classTeam = wmsGdxdIn.getTeamCd();
    			}
        		double inTotalWeight = 0;
        		if (wmsGdxdIn.getTotalweightIn() != null) {
    				inTotalWeight = wmsGdxdIn.getTotalweightIn();
    			}
        		map.put("workOrderNumber", workOrderNumber);
        		map.put("batchNumber", batchNumber);
        		map.put("processCoding", processCoding);
        		map.put("workOrderStatus", workOrderStatus);
        		map.put("brandClassificationName", brandClassificationName);
        		map.put("brandName", brandName);
        		map.put("Stocking", Stocking);
        		map.put("state", state);
        		map.put("startTime", startTime);
        		map.put("endTime", endTime);
        		map.put("sendTime", sendTime);
        		map.put("boxTotalNum", boxTotalNum);
        		map.put("inStation", inStation);
        		map.put("batchWeight", batchWeight);
        		map.put("boxLoadWeight", boxLoadWeight);
        		map.put("classShift", classShift);
        		map.put("classTeam", classTeam);
        		map.put("inTotalWeight", inTotalWeight);
        		
        		mapList.add(map);
        	}
		}else {
			String sTime = request.getParameter("startTime");
			String eTime = request.getParameter("endTime");
			int rangeStart = Integer.valueOf(request.getParameter("rangeStart"));
			int rangeEnd = Integer.valueOf(request.getParameter("rangeEnd"));
			String batch = request.getParameter("batch");
			for(WmsGdxdIn wmsGdxdIn:wmsGdxdInService.filterWorkOrderInBatch(packingLine, brand, batch, sTime, eTime, rangeStart, rangeEnd)){
        		Map<String, Object> map = MapUtils.newHashMap();
        		String workOrderNumber = wmsGdxdIn.getWoNo();
        		String batchNumber = wmsGdxdIn.getBatchNo();
        		String processCoding = wmsGdxdIn.getTechSectionCd();
        		String workOrderStatus = wmsGdxdIn.getWoState();
        		String brandClassificationName = wmsGdxdIn.getMatClassNm();
        		String brandName = wmsGdxdIn.getMatNm();
        		Double Stocking = wmsGdxdIn.getPlanAmount();
        		String sendTime = CompareDate.simplifyDate(wmsGdxdIn.getWoSendTime());
        		String state = wmsGdxdIn.getState();
        		String startTime = CompareDate.simplifyDate(wmsGdxdIn.getWoStartTime());    		    		   		
        		String endTime = CompareDate.simplifyDate(wmsGdxdIn.getWoEndTime());
        		int boxTotalNum = wmsGdxdIn.getBoxtotalnum();
        		String inStation = "";
        		if (wmsGdxdIn.getStation() != null) {
    				inStation = wmsGdxdIn.getStation();
    			}
        		double batchWeight = 0;
        		if (wmsGdxdIn.getBatchweight() != null) {
    				batchWeight = wmsGdxdIn.getBatchweight();
    			}
        		double boxLoadWeight = 0;
        		if (wmsGdxdIn.getBoxloadweight() != null) {
    				boxLoadWeight = wmsGdxdIn.getBoxloadweight();
    			}
        		String classShift = "null";
        		if (wmsGdxdIn.getShiftCd() != null) {
    				classShift = wmsGdxdIn.getShiftCd();
    			}
        		String classTeam = "null";
        		if (wmsGdxdIn.getTeamCd() != null) {
    				classTeam = wmsGdxdIn.getTeamCd();
    			}
        		double inTotalWeight = 0;
        		if (wmsGdxdIn.getTotalweightIn() != null) {
    				inTotalWeight = wmsGdxdIn.getTotalweightIn();
    			}
        		map.put("workOrderNumber", workOrderNumber);
        		map.put("batchNumber", batchNumber);
        		map.put("processCoding", processCoding);
        		map.put("workOrderStatus", workOrderStatus);
        		map.put("brandClassificationName", brandClassificationName);
        		map.put("brandName", brandName);
        		map.put("Stocking", Stocking);
        		map.put("state", state);
        		map.put("startTime", startTime);
        		map.put("endTime", endTime);
        		map.put("sendTime", sendTime);
        		map.put("boxTotalNum", boxTotalNum);
        		map.put("inStation", inStation);
        		map.put("batchWeight", batchWeight);
        		map.put("boxLoadWeight", boxLoadWeight);
        		map.put("classShift", classShift);
        		map.put("classTeam", classTeam);
        		map.put("inTotalWeight", inTotalWeight);        		
        		mapList.add(map);
        	}
		}    	
    	return mapList;
	}
    
    /**
     * 列举所有喂丝机号
	 *Json:
	 *{"wireFeederNum":["xxx","xxx"]}
	 *
	 *2.1.2.	喂丝机号
	（3）列举所有喂丝机号、喂丝机对应的所有品牌
	Json:
	{"wireFeederNum":["xxx","xxx"],"brand":["xxx","xxx"]}

     * @return
     */
    @RequestMapping(value = {"wireFeederNum",""})
    public Map<String, Object> wireFeederNum() {
		Map<String, Object> map = MapUtils.newHashMap();
		List<String> list1 = new ArrayList<>();
		List<String> list2 = new ArrayList<>();
		for(WmsGdxdOut wmsGdxdOut:wmsGdxdOutService.getEquId()){
			list1.add(wmsGdxdOut.getEquId());
		}
		for(WmsGdxdOut wmsGdxdOut:wmsGdxdOutService.getBrands()){
			list2.add(wmsGdxdOut.getMatNm());
		}
		map.put("wireFeederNum", list1);
		map.put("brand", list2);
		return map;
	}
    
    /**
     * 通过喂丝机号筛选数据
     * Post：wireFeederNum
     * 
     * 通过喂丝机号筛选数据，增加品牌、开始时间、结束时间和和分页范围rangeStart、rangeEnd
	Post：
	{"wireFeederNum":"xxx","brand":"xxx","startTime":"xxx","endTime":"xxx","rangeStart":1,"rangeEnd":500}

	 *Json:
	 *[{"workOrderNumber": "XXX","batchNumber": "XXX","outStation": "XXX","workOrderStatus": "XXX","brandName": "XXX","planAmount": "XXX","sendTime": "XXX","endTime": "XXX","startTime": "XXX","boxTotalNum": "XXX","classShift": "XXX","classTeam": "XXX"}]
     * @return
     */
    @RequestMapping(value = "filterByWireFeeder")
    public List<Map<String, Object>> filterByWireFeeder(HttpServletRequest request) {
    	List<Map<String, Object>> mapList = ListUtils.newArrayList();
		String equId = request.getParameter("wireFeederNum");
		String brand = request.getParameter("brand");
		if (brand == null || brand.equals("")) {
			for (WmsGdxdOut wmsGdxdOut : wmsGdxdOutService.getAllByEquId(equId, 9)) {
				Map<String, Object> map = MapUtils.newHashMap();
				String workOrderNumber = wmsGdxdOut.getWoNo();
				String batchNumber = wmsGdxdOut.getBatchNo();
				String outStation = wmsGdxdOut.getOutstation();
				String workOrderStatus = wmsGdxdOut.getWoState();
				String brandName = wmsGdxdOut.getMatNm();
				double planAmount = wmsGdxdOut.getPlanAmount();
				String sendTime = CompareDate.simplifyDate(wmsGdxdOut.getWoSendTime());
				String endTime = CompareDate.simplifyDate(wmsGdxdOut.getWoEndTime());
				String startTime = CompareDate.simplifyDate(wmsGdxdOut.getWoStartTime());
				int boxTotalNum = wmsGdxdOut.getBoxtotalnum();
				String classShift = wmsGdxdOut.getShiftCd();
				String classTeam = wmsGdxdOut.getTeamCd();

				map.put("workOrderNumber", workOrderNumber);
				map.put("batchNumber", batchNumber);
				map.put("outStation", outStation);
				map.put("brandName", brandName);
				map.put("workOrderStatus", workOrderStatus);
				map.put("planAmount", planAmount);
				map.put("sendTime", sendTime);
				map.put("endTime", endTime);
				map.put("boxTotalNum", boxTotalNum);
				map.put("classShift", classShift);
				map.put("startTime", startTime);
				map.put("classTeam", classTeam);
				mapList.add(map);
			}
		}else {
			String sTime = request.getParameter("startTime");
			String eTime = request.getParameter("endTime");
			int rangeStart = Integer.valueOf(request.getParameter("rangeStart"));
			int rangeEnd = Integer.valueOf(request.getParameter("rangeEnd"));
			String batch = request.getParameter("batch");
			for (WmsGdxdOut wmsGdxdOut : wmsGdxdOutService.filterWorkOrderOutBatch(equId, brand, batch, sTime, eTime, rangeStart, rangeEnd)) {
				Map<String, Object> map = MapUtils.newHashMap();
				String workOrderNumber = wmsGdxdOut.getWoNo();
				String batchNumber = wmsGdxdOut.getBatchNo();
				String outStation = wmsGdxdOut.getOutstation();
				String workOrderStatus = wmsGdxdOut.getWoState();
				String brandName = wmsGdxdOut.getMatNm();
				double planAmount = wmsGdxdOut.getPlanAmount();
				String sendTime = CompareDate.simplifyDate(wmsGdxdOut.getWoSendTime());
				String endTime = CompareDate.simplifyDate(wmsGdxdOut.getWoEndTime());
				String startTime = CompareDate.simplifyDate(wmsGdxdOut.getWoStartTime());
				int boxTotalNum = wmsGdxdOut.getBoxtotalnum();
				String classShift = wmsGdxdOut.getShiftCd();
				String classTeam = wmsGdxdOut.getTeamCd();

				map.put("workOrderNumber", workOrderNumber);
				map.put("batchNumber", batchNumber);
				map.put("outStation", outStation);
				map.put("brandName", brandName);
				map.put("workOrderStatus", workOrderStatus);
				map.put("planAmount", planAmount);
				map.put("sendTime", sendTime);
				map.put("endTime", endTime);
				map.put("boxTotalNum", boxTotalNum);
				map.put("classShift", classShift);
				map.put("startTime", startTime);
				map.put("classTeam", classTeam);
				mapList.add(map);
			}
		}
		return mapList;
	}
    
    /**
     * 列举所有班组
	 *Json:
	 *{"classTeam":["xxx","xxx"]}
	 *
	 *2.1.3.	班组
	（5）列举所有班组、班组对应的所有品牌
	Json:
	{"classTeam":["xxx","xxx"],"brand":["xxx","xxx"]}}

     * @return
     */
    @RequestMapping(value = "classTeamList")
    public Map<String, Object> classTeamList() {
		Map<String, Object> map = MapUtils.newHashMap();
		List<String> list1 = new ArrayList<>();
		List<String> list2 = new ArrayList<>();	
		for(WmsGdxdOut wmsGdxdOut:wmsGdxdOutService.getClassTeam()){
			list1.add(wmsGdxdOut.getTeamCd());
		}
		System.err.println(list1);
		/*for(WmsGdxdIn wmsGdxdIn:wmsGdxdInService.classTeamList()){
			list.add(wmsGdxdIn.getTeamCd());
		}		
		System.err.println(list);*/
		System.err.println(wmsGdxdInService.classTeamList());
		list1.add("null");
		List<String> listWithoutDup1 = new ArrayList<>(new HashSet<>(list1));
		System.err.println(listWithoutDup1);
		
		for(WmsGdxdOut wmsGdxdOut:wmsGdxdOutService.getBrands()){
			list2.add(wmsGdxdOut.getMatNm());
		}
		for(WmsGdxdIn wmsGdxdIn:wmsGdxdInService.getBrands()){
			list2.add(wmsGdxdIn.getMatNm());
		}
		List<String> listWithoutDup2 = new ArrayList<>(new HashSet<>(list2));
		
		map.put("classTeam", listWithoutDup1);
		map.put("brand", listWithoutDup2);
		return map;
	}
    
    /**
     * 通过班组筛选数据
	 *Post：classTeam
	 *（确认需要的字段 批次、品牌、开始时间、完成时间、用时、类型：出库/入库）
	 *
	 *（6）通过班组筛选数据，增加品牌、开始时间、结束时间和和分页范围rangeStart、rangeEnd
	Post：
	{"classTeam":"xxx","brand":"xxx","startTime":"xxx","endTime":"xxx","range":"xxx","rangeStart":1,"rangeEnd":500}

	 *Json:
	 *[{"batch": "XXX","brand": "XXX","startTime": "XXX","finishTime": "XXX","useTime": "XXX","type": "XXX"}]
     * @param request
     * @return
     */
    @RequestMapping(value = "filterByClassTeam")
    public List<Map<String, Object>> filterByClassTeam(HttpServletRequest request) {
		List<Map<String, Object>> mapList = ListUtils.newArrayList();
		String classTeam = request.getParameter("classTeam");
		String brand = request.getParameter("brand");
		if (brand == null || brand.equals("")) {
			if (classTeam.equals("null")) {
				for(WmsGdxdIn wmsGdxdIn:wmsGdxdInService.getAllByClassTeamNull()){
					Map<String, Object> map = MapUtils.newHashMap();
					map.put("batch", wmsGdxdIn.getBatchNo());
					map.put("brand", wmsGdxdIn.getMatNm());
					map.put("startTime", CompareDate.simplifyDate(wmsGdxdIn.getWoStartTime()));
					map.put("finishTime", CompareDate.simplifyDate(wmsGdxdIn.getWoEndTime()));
					long diff = wmsGdxdIn.getWoEndTime().getTime()-wmsGdxdIn.getWoStartTime().getTime();
					map.put("useTime", (diff/(1000*60)));
					map.put("type", "入库");
					mapList.add(map);
				}
			}else {
				for(WmsGdxdIn wmsGdxdIn:wmsGdxdInService.getAllByClassTeam(classTeam)){
					Map<String, Object> map = MapUtils.newHashMap();
					map.put("batch", wmsGdxdIn.getBatchNo());
					map.put("brand", wmsGdxdIn.getMatNm());
					map.put("startTime", CompareDate.simplifyDate(wmsGdxdIn.getWoStartTime()));
					map.put("finishTime", CompareDate.simplifyDate(wmsGdxdIn.getWoEndTime()));
					long diff = wmsGdxdIn.getWoEndTime().getTime()-wmsGdxdIn.getWoStartTime().getTime();
					map.put("useTime", (diff/(1000*60)));
					map.put("type", "入库");
					mapList.add(map);
				}
				for(WmsGdxdOut wmsGdxdOut:wmsGdxdOutService.getAllByClassTeam(classTeam)){
					Map<String, Object> map = MapUtils.newHashMap();
					map.put("batch", wmsGdxdOut.getBatchNo());
					map.put("brand", wmsGdxdOut.getMatNm());
					map.put("startTime", CompareDate.simplifyDate(wmsGdxdOut.getWoStartTime()));
					map.put("finishTime", CompareDate.simplifyDate(wmsGdxdOut.getWoEndTime()));
					long diff = wmsGdxdOut.getWoEndTime().getTime()-wmsGdxdOut.getWoStartTime().getTime();
					map.put("useTime", (diff/(1000*60)));
					map.put("type", "出库");
					mapList.add(map);
				}
			}
		}else {  //改进后的接口
			String sTime = request.getParameter("startTime");
			String eTime = request.getParameter("endTime");
			int rangeStart = Integer.valueOf(request.getParameter("rangeStart"));
			int rangeEnd = Integer.valueOf(request.getParameter("rangeEnd"));
			String batch = request.getParameter("batch");
			if (classTeam.equals("null")) {
				for(WmsGdxdIn wmsGdxdIn:wmsGdxdInService.getAllByClassTeamNull()){
					Map<String, Object> map = MapUtils.newHashMap();
					map.put("batch", wmsGdxdIn.getBatchNo());
					map.put("brand", wmsGdxdIn.getMatNm());
					map.put("startTime", CompareDate.simplifyDate(wmsGdxdIn.getWoStartTime()));
					map.put("finishTime", CompareDate.simplifyDate(wmsGdxdIn.getWoEndTime()));
					long diff = wmsGdxdIn.getWoEndTime().getTime()-wmsGdxdIn.getWoStartTime().getTime();
					map.put("useTime", (diff/(1000*60)));
					map.put("type", "入库");
					mapList.add(map);
				}
			}else {
				for(WmsGdxdIn wmsGdxdIn:wmsGdxdInService.filterByClassTeamBatch(classTeam, brand, batch, sTime, eTime, rangeStart, rangeEnd)){
					Map<String, Object> map = MapUtils.newHashMap();
					map.put("batch", wmsGdxdIn.getBatchNo());
					map.put("brand", wmsGdxdIn.getMatNm());
					map.put("startTime", CompareDate.simplifyDate(wmsGdxdIn.getWoStartTime()));
					map.put("finishTime", CompareDate.simplifyDate(wmsGdxdIn.getWoEndTime()));
					long diff = wmsGdxdIn.getWoEndTime().getTime()-wmsGdxdIn.getWoStartTime().getTime();
					map.put("useTime", (diff/(1000*60)));
					map.put("type", "入库");
					mapList.add(map);
				}
				for(WmsGdxdOut wmsGdxdOut:wmsGdxdOutService.filterByClassTeamBatch(classTeam, brand, batch, sTime, eTime, rangeStart, rangeEnd)){
					Map<String, Object> map = MapUtils.newHashMap();
					map.put("batch", wmsGdxdOut.getBatchNo());
					map.put("brand", wmsGdxdOut.getMatNm());
					map.put("startTime", CompareDate.simplifyDate(wmsGdxdOut.getWoStartTime()));
					map.put("finishTime", CompareDate.simplifyDate(wmsGdxdOut.getWoEndTime()));
					long diff = wmsGdxdOut.getWoEndTime().getTime()-wmsGdxdOut.getWoStartTime().getTime();
					map.put("useTime", (diff/(1000*60)));
					map.put("type", "出库");
					mapList.add(map);
				}
			}
		}
		return mapList;
	}
    
  //班组筛选
    @RequestMapping(value = "classInfoManage")
    public List<Map<String, Object>> classInfoManage(HttpServletRequest request) {
    	String analysisType = request.getParameter("analysisType");
		String timeCoordinate = request.getParameter("timeCoordinate");
		String timeDomainYear = request.getParameter("timeDomainYear");		
		List<Map<String, Object>> mapList = ListUtils.newArrayList();
		if (timeCoordinate.equals("Yearly")) {		//请求参数为年	
			switch (analysisType) {			
			case "DeliveryInfo":
				for(int i=0;i<20;i++){									
					timeDomainYear = String.valueOf(Calendar.getInstance().get(Calendar.YEAR)-i);
					for (WmsPrdOutDetl wmsPrdOutDetl : wmsPrdOutDetlService.getClassWeightYearly(timeDomainYear)) {
						Map<String, Object> mapThis = MapUtils.newHashMap();
						mapThis.put("classTeam", wmsPrdOutDetl.getTeamCd());
						mapThis.put("totalWeightThis", Double.valueOf(wmsPrdOutDetl.getWeight()));
						mapThis.put("timeVariable", timeDomainYear);
						mapList.add(mapThis);
					}
				}
			break;
			case "IncomingInfo":
				for(int i=0;i<20;i++){									
					timeDomainYear = String.valueOf(Calendar.getInstance().get(Calendar.YEAR)-i);
					for (WmsPrdInDetl wmsPrdInDetl : wmsPrdInDetlService.getClassWeightYearly(timeDomainYear)) {
						Map<String, Object> mapThis = MapUtils.newHashMap();
						mapThis.put("classTeam", wmsPrdInDetl.getTeamCd());
						mapThis.put("totalWeightThis", Double.valueOf(wmsPrdInDetl.getWeight()));
						mapThis.put("timeVariable", timeDomainYear);
						mapList.add(mapThis);
					}
				}
			break;
			default:
				Map<String, Object> map1 = MapUtils.newHashMap();
				map1.put("Error", "analysisType传参错误");
				mapList.add(map1);
				break;
			}
		}else if (timeCoordinate.equals("Monthly")) {
			switch (analysisType) {			
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
					for (WmsPrdOutDetl wmsPrdOutDetl : wmsPrdOutDetlService.getClassWeightMonthly(dateThis)) {
						Map<String, Object> mapThis = MapUtils.newHashMap();
						mapThis.put("classTeam", wmsPrdOutDetl.getTeamCd());
						mapThis.put("totalWeightThis", Double.valueOf(wmsPrdOutDetl.getWeight()));
						mapListThis.add(mapThis);
					}
					for (WmsPrdOutDetl wmsPrdOutDetl : wmsPrdOutDetlService.getClassWeightMonthly(dateLast)) {
						Map<String, Object> mapLast = MapUtils.newHashMap();
						mapLast.put("classTeam", wmsPrdOutDetl.getTeamCd());
						mapLast.put("totalWeightLast", Double.valueOf(wmsPrdOutDetl.getWeight()));
						mapListLast.add(mapLast);
					}
					for (Map<String, Object> mapT : mapListThis) {
						if (mapListLast != null && !mapListLast.isEmpty()) {
							for (Map<String, Object> mapL : mapListLast) {
								if (mapT.get("classTeam").equals(mapL.get("classTeam"))) {
									Map<String, Object> mapF = MapUtils.newHashMap();
									mapF.put("timeVariable", j);
									mapF.put("classTeam", mapT.get("classTeam"));
									mapF.put("totalWeightThis", mapT.get("totalWeightThis"));									
									mapF.put("totalWeightLast", mapL.get("totalWeightLast"));
									mapList.add(mapF);
								}
							}
						} else {
							Map<String, Object> mapF = MapUtils.newHashMap();
							mapF.put("timeVariable", j);
							mapF.put("classTeam", mapT.get("classTeam"));
							mapF.put("totalWeightThis", mapT.get("totalWeightThis"));							
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
					// System.err.println(dateThis);
					for (WmsPrdInDetl wmsPrdInDetl : wmsPrdInDetlService.getClassWeightMonthly(dateThis)) {
						Map<String, Object> mapThis = MapUtils.newHashMap();
						mapThis.put("classTeam", wmsPrdInDetl.getTeamCd());						
						mapThis.put("totalWeightThis", Double.valueOf(wmsPrdInDetl.getWeight()));
						mapListThis.add(mapThis);
					}
					for (WmsPrdInDetl wmsPrdOutDetl : wmsPrdInDetlService.getClassWeightMonthly(dateLast)) {
						Map<String, Object> mapLast = MapUtils.newHashMap();
						mapLast.put("classTeam", wmsPrdOutDetl.getTeamCd());
						mapLast.put("totalWeightLast", Double.valueOf(wmsPrdOutDetl.getWeight()));
						mapListLast.add(mapLast);
					}
					for (Map<String, Object> mapT : mapListThis) {
						if (mapListLast != null && !mapListLast.isEmpty()) {
							for (Map<String, Object> mapL : mapListLast) {
								if (mapT.get("classTeam").equals(mapL.get("classTeam"))) {
									Map<String, Object> mapF = MapUtils.newHashMap();
									mapF.put("timeVariable", j);
									mapF.put("classTeam", mapT.get("classTeam"));
									mapF.put("totalWeightThis", mapT.get("totalWeightThis"));									
									mapF.put("totalWeightLast", mapL.get("totalWeightLast"));
									mapList.add(mapF);
								}
							}
						} else {
							Map<String, Object> mapF = MapUtils.newHashMap();
							mapF.put("timeVariable", j);
							mapF.put("classTeam", mapT.get("classTeam"));
							mapF.put("totalWeightThis", mapT.get("totalWeightThis"));							
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
					for(WmsPrdOutDetl wmsPrdOutDetl:wmsPrdOutDetlService.getClassWeightDaily(dateThis)){
						Map<String, Object> mapThis = MapUtils.newHashMap();
						String brandThis;
						double totalWeightThis = 0;																		
						brandThis = wmsPrdOutDetl.getTeamCd();						
						totalWeightThis = Double.valueOf(wmsPrdOutDetl.getWeight());						
						mapThis.put("classTeam", brandThis);						
						mapThis.put("totalWeightThis", totalWeightThis);
						mapListThis.add(mapThis);
					}
					List<Map<String, Object>> mapListLast = ListUtils.newArrayList();
					for(WmsPrdOutDetl wmsPrdOutDetl:wmsPrdOutDetlService.getClassWeightDaily(dateLast)){
						Map<String, Object> mapLast = MapUtils.newHashMap();
						String brandLast;
						double totalWeightLast = 0;
						brandLast = wmsPrdOutDetl.getTeamCd();					
						totalWeightLast = Double.valueOf(wmsPrdOutDetl.getWeight());						
						mapLast.put("classTeam", brandLast);
						mapLast.put("totalWeightLast", totalWeightLast);
						mapListLast.add(mapLast);
					}
					for (Map<String, Object> mapT : mapListThis) {
						if (mapListLast != null && !mapListLast.isEmpty()) {
							for (Map<String, Object> mapL : mapListLast) {
								if (mapT.get("classTeam").equals(mapL.get("classTeam"))) {
									Map<String, Object> mapF = MapUtils.newHashMap();
									mapF.put("timeVariable", i);
									mapF.put("classTeam", mapT.get("classTeam"));
									mapF.put("totalWeightThis", mapT.get("totalWeightThis"));									
									mapF.put("totalWeightLast", mapL.get("totalWeightLast"));
									mapList.add(mapF);
								}
							}
						} else {
							Map<String, Object> mapF = MapUtils.newHashMap();
							mapF.put("timeVariable", i);
							mapF.put("classTeam", mapT.get("classTeam"));
							mapF.put("totalWeightThis", mapT.get("totalWeightThis"));							
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
					for(WmsPrdInDetl wmsPrdInDetl:wmsPrdInDetlService.getClassWeightDaily(dateThis)){					
						Map<String, Object> mapThis = MapUtils.newHashMap();
						String brandThis;
				        double totalWeightThis = 0;
				        brandThis = wmsPrdInDetl.getTeamCd();				        				        				        
				        totalWeightThis = Double.valueOf(wmsPrdInDetl.getWeight());				        
				        mapThis.put("classTeam", brandThis);				        
				        mapThis.put("totalWeightThis", totalWeightThis);
				        mapListThis.add(mapThis);
					}
					List<Map<String, Object>> mapListLast = ListUtils.newArrayList();
					for(WmsPrdInDetl wmsPrdInDetl:wmsPrdInDetlService.getClassWeightDaily(dateLast)){						
						Map<String, Object> mapLast = MapUtils.newHashMap();
						String brandLast;
				        double totalWeightLast = 0;
				        brandLast = wmsPrdInDetl.getTeamCd();				        
				        totalWeightLast += Double.valueOf(wmsPrdInDetl.getWeight());				        
				        mapLast.put("classTeam", brandLast);
				        mapLast.put("totalWeightLast", totalWeightLast);
				        mapListLast.add(mapLast);
					}
					for(Map<String, Object> mapT:mapListThis){
						if(mapListLast != null && !mapListLast.isEmpty()){
							for(Map<String, Object> mapL:mapListLast){							
								if (mapT.get("classTeam").equals(mapL.get("classTeam"))) {
									Map<String, Object> mapF = MapUtils.newHashMap();
									mapF.put("timeVariable", i);
									mapF.put("classTeam", mapT.get("classTeam"));
									mapF.put("totalWeightThis", mapT.get("totalWeightThis"));									
									mapF.put("totalWeightLast", mapL.get("totalWeightLast"));
									mapList.add(mapF);
								}
							}
						} else {
							Map<String, Object> mapF = MapUtils.newHashMap();
							mapF.put("timeVariable", i);
							mapF.put("classTeam", mapT.get("classTeam"));
							mapF.put("totalWeightThis", mapT.get("totalWeightThis"));							
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
     * 2.2.4 生产信息统计
     * analysisType取值：”DeliveryInfo”、”IncomingInfo”，表示出库、入库
	analysisModule取值：”Brand”、”Class”，表示品牌、班组
	timeCoordinate取值：”Daily”、”Monthly”，”Yearly”，表示时间轴坐标为日、月、年。
	Post: 
	{“analysisModule”:”Brand”,“analysisType”:”DeliveryInfo”,”timeCoordinate”:” Monthly”,” timeDomainYear”:2018}
	或
	{“analysisModule”:”Brand” ,“analysisType”:”DeliveryInfo”,”timeCoordinate”:”Daily”,”timeDomainYear”:2019,”timeDomainMonth”:5}
	或
	{“analysisModule”:”Brand” ,“analysisType”:”DeliveryInfo”,”timeCoordinate”:”Yearly”}

	public string selectedName;
	public double brandWeight;
	public double batchNumber;

     * @param request
     * @return
     */
    @RequestMapping(value = "productInfoCount")
    public List<Map<String, Object>> productInfoCount(HttpServletRequest request) {
    	String analysisType = request.getParameter("analysisType");
		String timeCoordinate = request.getParameter("timeCoordinate");
		String timeDomainYear = request.getParameter("timeDomainYear");	
		String analysisModule = request.getParameter("analysisModule");
		List<Map<String, Object>> mapList = ListUtils.newArrayList();
		if (timeCoordinate.equals("Yearly")) {		//请求参数为年	
			switch (analysisType) {			
			case "DeliveryInfo":
				for(int i=0;i<20;i++){									
					timeDomainYear = String.valueOf(Calendar.getInstance().get(Calendar.YEAR)-i);
					if (analysisModule.equals("Brand")) {
						for (WmsPrdOutDetl wmsPrdOutDetl : wmsPrdOutDetlService.getProductInfoCountBrandYearly(timeDomainYear)) {
							Map<String, Object> mapThis = MapUtils.newHashMap();
							mapThis.put("selectedName", wmsPrdOutDetl.getMatNm());
							mapThis.put("brandWeight", Double.valueOf(wmsPrdOutDetl.getWeight()));	
							mapThis.put("batchNumber", Integer.parseInt(wmsPrdOutDetl.getBatchNo()));						
							mapList.add(mapThis);
						}
					}else if (analysisModule.equals("Class")) {
						for (WmsPrdOutDetl wmsPrdOutDetl : wmsPrdOutDetlService.getProductInfoCountClassYearly(timeDomainYear)) {
							Map<String, Object> mapThis = MapUtils.newHashMap();
							mapThis.put("selectedName", wmsPrdOutDetl.getTeamCd());
							mapThis.put("brandWeight", Double.valueOf(wmsPrdOutDetl.getWeight()));	
							mapThis.put("batchNumber", Integer.parseInt(wmsPrdOutDetl.getBatchNo()));						
							mapList.add(mapThis);
						}
					}else {
						Map<String, Object> map1 = MapUtils.newHashMap();
						map1.put("Error", "analysisModule传参错误");
						mapList.add(map1);
						break;
					}
				}
			break;
			case "IncomingInfo":
				for(int i=0;i<20;i++){									
					timeDomainYear = String.valueOf(Calendar.getInstance().get(Calendar.YEAR)-i);
					if (analysisModule.equals("Brand")) {
						for (WmsPrdInDetl wmsPrdInDetl : wmsPrdInDetlService.getProductInfoCountBrandYearly(timeDomainYear)) {
							Map<String, Object> mapThis = MapUtils.newHashMap();
							mapThis.put("selectedName", wmsPrdInDetl.getMatNm());
							mapThis.put("brandWeight", Double.valueOf(wmsPrdInDetl.getWeight()));	
							mapThis.put("batchNumber", Integer.parseInt(wmsPrdInDetl.getBatchNo()));						
							mapList.add(mapThis);
						}
					}else if (analysisModule.equals("Class")) {
						for (WmsPrdInDetl wmsPrdInDetl : wmsPrdInDetlService.getProductInfoCountClassYearly(timeDomainYear)) {
							Map<String, Object> mapThis = MapUtils.newHashMap();
							mapThis.put("selectedName", wmsPrdInDetl.getTeamCd());
							mapThis.put("brandWeight", Double.valueOf(wmsPrdInDetl.getWeight()));	
							mapThis.put("batchNumber", Integer.parseInt(wmsPrdInDetl.getBatchNo()));						
							mapList.add(mapThis);
						}
					}else {
						Map<String, Object> map1 = MapUtils.newHashMap();
						map1.put("Error", "analysisModule传参错误");
						mapList.add(map1);
						break;
					}
				}
			break;
			default:
				Map<String, Object> map1 = MapUtils.newHashMap();
				map1.put("Error", "analysisType传参错误");
				mapList.add(map1);
				break;
			}
		}else if (timeCoordinate.equals("Monthly")) {
			switch (analysisType) {			
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
					if (analysisModule.equals("Brand")) {
						for (WmsPrdOutDetl wmsPrdOutDetl : wmsPrdOutDetlService.getProductInfoCountBrandMonthly(dateThis)) {
							Map<String, Object> mapThis = MapUtils.newHashMap();
							mapThis.put("selectedName", wmsPrdOutDetl.getMatNm());
							mapThis.put("brandWeight", Double.valueOf(wmsPrdOutDetl.getWeight()));	
							mapThis.put("batchNumber", Integer.parseInt(wmsPrdOutDetl.getBatchNo()));						
							mapList.add(mapThis);
						}
					}else if (analysisModule.equals("Class")) {
						for (WmsPrdOutDetl wmsPrdOutDetl : wmsPrdOutDetlService.getProductInfoCountClassMonthly(dateThis)) {
							Map<String, Object> mapThis = MapUtils.newHashMap();
							mapThis.put("selectedName", wmsPrdOutDetl.getTeamCd());
							mapThis.put("brandWeight", Double.valueOf(wmsPrdOutDetl.getWeight()));	
							mapThis.put("batchNumber", Integer.parseInt(wmsPrdOutDetl.getBatchNo()));						
							mapList.add(mapThis);
						}
					}else {
						Map<String, Object> map1 = MapUtils.newHashMap();
						map1.put("Error", "analysisModule传参错误");
						mapList.add(map1);
						break;
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
					if (analysisModule.equals("Brand")) {
						for (WmsPrdInDetl wmsPrdInDetl : wmsPrdInDetlService.getProductInfoCountBrandMonthly(dateThis)) {
							Map<String, Object> mapThis = MapUtils.newHashMap();
							mapThis.put("selectedName", wmsPrdInDetl.getMatNm());
							mapThis.put("brandWeight", Double.valueOf(wmsPrdInDetl.getWeight()));	
							mapThis.put("batchNumber", Integer.parseInt(wmsPrdInDetl.getBatchNo()));						
							mapList.add(mapThis);
						}
					}else if (analysisModule.equals("Class")) {
						for (WmsPrdInDetl wmsPrdInDetl : wmsPrdInDetlService.getProductInfoCountClassMonthly(dateThis)) {
							Map<String, Object> mapThis = MapUtils.newHashMap();
							mapThis.put("selectedName", wmsPrdInDetl.getTeamCd());
							mapThis.put("brandWeight", Double.valueOf(wmsPrdInDetl.getWeight()));	
							mapThis.put("batchNumber", Integer.parseInt(wmsPrdInDetl.getBatchNo()));						
							mapList.add(mapThis);
						}
					}else {
						Map<String, Object> map1 = MapUtils.newHashMap();
						map1.put("Error", "analysisModule传参错误");
						mapList.add(map1);
						break;
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
					if (analysisModule.equals("Brand")) {
						for (WmsPrdOutDetl wmsPrdOutDetl : wmsPrdOutDetlService.getProductInfoCountBrandDaily(dateThis)) {
							Map<String, Object> mapThis = MapUtils.newHashMap();
							mapThis.put("selectedName", wmsPrdOutDetl.getMatNm());
							mapThis.put("brandWeight", Double.valueOf(wmsPrdOutDetl.getWeight()));	
							mapThis.put("batchNumber", Integer.parseInt(wmsPrdOutDetl.getBatchNo()));						
							mapList.add(mapThis);
						}
					}else if (analysisModule.equals("Class")) {
						for (WmsPrdOutDetl wmsPrdOutDetl : wmsPrdOutDetlService.getProductInfoCountClassDaily(dateThis)) {
							Map<String, Object> mapThis = MapUtils.newHashMap();
							mapThis.put("selectedName", wmsPrdOutDetl.getTeamCd());
							mapThis.put("brandWeight", Double.valueOf(wmsPrdOutDetl.getWeight()));	
							mapThis.put("batchNumber", Integer.parseInt(wmsPrdOutDetl.getBatchNo()));						
							mapList.add(mapThis);
						}
					}else {
						Map<String, Object> map1 = MapUtils.newHashMap();
						map1.put("Error", "analysisModule传参错误");
						mapList.add(map1);
						break;
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
					if (analysisModule.equals("Brand")) {
						for (WmsPrdInDetl wmsPrdInDetl : wmsPrdInDetlService.getProductInfoCountBrandDaily(dateThis)) {
							Map<String, Object> mapThis = MapUtils.newHashMap();
							mapThis.put("selectedName", wmsPrdInDetl.getMatNm());
							mapThis.put("brandWeight", Double.valueOf(wmsPrdInDetl.getWeight()));	
							mapThis.put("batchNumber", Integer.parseInt(wmsPrdInDetl.getBatchNo()));						
							mapList.add(mapThis);
						}
					}else if (analysisModule.equals("Class")) {
						for (WmsPrdInDetl wmsPrdInDetl : wmsPrdInDetlService.getProductInfoCountClassDaily(dateThis)) {
							Map<String, Object> mapThis = MapUtils.newHashMap();
							mapThis.put("selectedName", wmsPrdInDetl.getTeamCd());
							mapThis.put("brandWeight", Double.valueOf(wmsPrdInDetl.getWeight()));	
							mapThis.put("batchNumber", Integer.parseInt(wmsPrdInDetl.getBatchNo()));						
							mapList.add(mapThis);
						}
					}else {
						Map<String, Object> map1 = MapUtils.newHashMap();
						map1.put("Error", "analysisModule传参错误");
						mapList.add(map1);
						break;
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
     * 2.4.	出库能力分析
     * Post：
	{”timeDomainYear”:2019,”timeDomainMonth”:5}
	Json:
	 [{“productionLineName”:”一号翻箱机”,“timeVariable”:11,“productionWeight”:22000},
	{“productionLineName”:”二号翻箱机”, “timeVariable”:11,“productionWeight”:22000}}]

     * @param request
     * @return
     */
    @RequestMapping(value = {"productOutAnalyse", ""})
    public List<Map<String, Object>> productOutAnalyse(HttpServletRequest request) {
        List<Map<String, Object>> mapList = ListUtils.newArrayList();
        String timeDomainYear = request.getParameter("timeDomainYear");
        String timeDomainMonth = request.getParameter("timeDomainMonth");        
        int timeVariable;        
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
            String equId;
            String productionLineName;
            timeVariable = i;
            Double productionCapacity = 0.0;
            for(WmsGdxdOut wmsGdxdOut:wmsGdxdOutService.getNewAllOut(date)){
            	equId = wmsGdxdOut.getEquId();
            	timeVariable=i;
            	if (equId.equals("S01") || equId.equals("S02") ||
            			equId.equals("S03") || equId.equals("S04")) {
            		productionLineName = "一号翻箱机";
            		for(WmsPrdOutDetl wmsPrdOutDetl:wmsPrdOutDetlService.getNewDetailByBatchNo(wmsGdxdOut.getBatchNo()))
            				productionCapacity += Double.valueOf(wmsPrdOutDetl.getWeight());
            		map.put("productionLineName",productionLineName);
                    map.put("timeVariable",timeVariable);
                    map.put("productionCapacity",productionCapacity);
                    mapList.add(map);
				}else if(equId.equals("S05") || equId.equals("S06") ||
            			equId.equals("S07") || equId.equals("S08")){					
					productionLineName = "二号翻箱机";
            		for(WmsPrdOutDetl wmsPrdOutDetl:wmsPrdOutDetlService.getNewDetailByBatchNo(wmsGdxdOut.getBatchNo()))
            				productionCapacity += Double.valueOf(wmsPrdOutDetl.getWeight());
            		map.put("productionLineName",productionLineName);
                    map.put("timeVariable",timeVariable);
                    map.put("productionCapacity",productionCapacity);
                    mapList.add(map);
				}else if(equId.equals("S09") || equId.equals("S10") ||
            			equId.equals("S11") || equId.equals("S12")){					
					productionLineName = "三号翻箱机";
            		for(WmsPrdOutDetl wmsPrdOutDetl:wmsPrdOutDetlService.getNewDetailByBatchNo(wmsGdxdOut.getBatchNo()))
            				productionCapacity += Double.valueOf(wmsPrdOutDetl.getWeight());
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
        return CompareDate.removeRepeatMapByKey(mapList, "timeVariable");
    }
}
