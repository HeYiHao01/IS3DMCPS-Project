package com.jeesite.modules.is3dmcps.web;

import com.jeesite.common.collect.ListUtils;
import com.jeesite.common.collect.MapUtils;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.is3dmcps.entity.BoxStatics;
import com.jeesite.modules.is3dmcps.entity.IsLocation;
import com.jeesite.modules.is3dmcps.entity.Position;
import com.jeesite.modules.is3dmcps.service.*;
import com.jeesite.modules.twms.entity.TwmsLoc;
import com.jeesite.modules.twms.entity.TwmsPltitem;
import com.jeesite.modules.twms.entity.TwmsTransferlogg;
import com.jeesite.modules.twms.service.TwmsLocService;
import com.jeesite.modules.twms.service.TwmsPltitemService;
import com.jeesite.modules.twms.service.TwmsTransferloggService;
import com.jeesite.utils.CompareDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 货物状态
 */
@CrossOrigin
@RestController
@RequestMapping(value = "static")
public class PagePackingBoxController extends BaseController{
	@Autowired
	private TwmsLocService twmsLocService;
	@Autowired
	private IsLocationService isLocationService;
	@Autowired
	private TwmsPltitemService twmsPltitemService;
	@Autowired
	private TwmsTransferloggService twmsTransferloggService;
	/**
	 * 货位状态数据
	 * @return
	 * Json:
	 * [{"locNum":1,"row":1,"col":13,"layer":1,"location_X":-1.2400000095367432,"location_Y":-0.41100001335144043,"location_Z":-59.194000244140625},
	 * {"locNum":19,"row":5,"col":13,"layer":1,"location_X":-1.2400000095367432,"location_Y":-0.41100001335144043,"location_Z":-53.07400131225586 },
	 * {"locNum":37,"row":10,"col":13,"layer":1,"location_X":-1.2400000095367432,"location_Y":-0.41100001335144043,"location_Z":-43.56800079345703}]
	 */
	@RequestMapping(value = {"packingBox", ""})
	public List<Map<String, Object>> packingBox() {
		List<Map<String, Object>> mapList = ListUtils.newArrayList();
		String locNum;
		int row;
		int col;
		int layer;
		double location_X = 0.0;
		double location_Y = 0.0;
		double location_Z = 0.0;
		Position position = new Position();
		IsLocation isLocation = new IsLocation();
		for(TwmsLoc twmsLoc:twmsLocService.getNewAll()){
			Map<String, Object> map = MapUtils.newHashMap();
			locNum=twmsLoc.getLocnum();
			row = twmsLoc.getLine();
			col = twmsLoc.getLie();
			layer = twmsLoc.getLayer();
			position.setRow(row);
			position.setCol(col);
			position.setLayer(layer);
			isLocation = isLocationService.getLocationByPosition(position);
			if (isLocation != null) {
				location_X = isLocation.getLocationX();
				location_Y = isLocation.getLocationY();
				location_Z = isLocation.getLocationZ();
			}
			map.put("locNum", locNum);
			map.put("row", row);
			map.put("col", col);
			map.put("layer", layer);
			map.put("location_X", location_X);
			map.put("location_Y", location_Y);
			map.put("location_Z", location_Z);
			mapList.add(map);
		}		
		return mapList;
	}
	
	/**
	 * 静态货箱刷新
	 * @param request
	 * @return
	 * Json:
	 *[{“VPLTNUM”:”64581”,”CURRLOC”:”OME01_00112801100100”,"LINE":1,"LIE":13,"LAYER":1,"location_X":-1.2400000095367432,"location_Y":-0.41100001335144043,"location_Z":-59.194000244140625,}]
	 */
	/*public List<Map<String, Object>> containLocationStatic(){
		List<Map<String, Object>> mapList = ListUtils.newArrayList();
    	int line = 0;
    	int lie = 0;
    	int layer = 0;
    	double location_X = 0.0;
    	double location_Y = 0.0;
    	double location_Z = 0.0;
    	String VPLTNUM = "";
    	String CURRLOC = "";
    	for(BoxStatics boxStatics : twmsPltitemService.getContainLocation()){
    		Map<String, Object> map = MapUtils.newHashMap();
    		line = boxStatics.getLine();
    		lie = boxStatics.getLie();
    		layer = boxStatics.getLayer();
    		location_X = boxStatics.getLocationX();
    		location_Y = boxStatics.getLocationY();
    		location_Z = boxStatics.getLocationZ();
    		VPLTNUM = boxStatics.getVplnum();
    		CURRLOC = boxStatics.getCurrloc();
    		map.put("line", line);
        	map.put("lie", lie);
        	map.put("layer", layer);
        	map.put("location_X", location_X);
        	map.put("location_Y", location_Y);
        	map.put("location_Z", location_Z);
        	map.put("VPLTNUM", VPLTNUM);
        	map.put("CURRLOC", CURRLOC);
        	mapList.add(map);
    	}
    	return mapList;    	
	}*/
	/**
	 * 1.1.	静态货箱刷新
		空/实箱颜色标记区分，增加 ITEMDESC字段
		CURRLOC的值需要解析为几行几列几层
		Json:
		[{“VPLTNUM”:”64581”,”CURRLOC”:”OME01_00112801100100”,”ITEMDESC”:”兰州(细支珍)烟丝”,"LINE":1,"LIE":13,"LAYER":1,"location_X":-1.2400000095367432,"location_Y":-0.41100001335144043,"location_Z":-59.194000244140625,}]

	 * @return
	 */
	@RequestMapping(value = {"containLocationStatic", ""})
	public List<Map<String, Object>> containLocationStatic(){
		List<Map<String, Object>> mapList = ListUtils.newArrayList();
    	int line = 0;
    	int lie = 0;
    	int layer = 0;
    	double location_X = 0.0;
    	double location_Y = 0.0;
    	double location_Z = 0.0;
    	String VPLTNUM = "";
    	String CURRLOC = "";
    	String currloc = "";
    	String ITEMDESC = "";
    	for(BoxStatics boxStatics : twmsPltitemService.getContainLocation()){
    		Map<String, Object> map = MapUtils.newHashMap();
    		line = boxStatics.getLine();
    		lie = boxStatics.getLie();
    		layer = boxStatics.getLayer();
    		location_X = boxStatics.getLocationX();
    		location_Y = boxStatics.getLocationY();
    		location_Z = boxStatics.getLocationZ();
    		VPLTNUM = boxStatics.getVplnum();
    		ITEMDESC = boxStatics.getItemdesc().trim();
    		currloc = boxStatics.getCurrloc();
    		CURRLOC = CompareDate.formatCurrLoc(currloc);
    		map.put("line", line);
        	map.put("lie", lie);
        	map.put("layer", layer);
        	map.put("location_X", location_X);
        	map.put("location_Y", location_Y);
        	map.put("location_Z", location_Z);
        	map.put("VPLTNUM", VPLTNUM);
        	map.put("currloc", currloc);
        	map.put("CURRLOC", CURRLOC);
        	map.put("ITEMDESC", ITEMDESC);
        	mapList.add(map);
    	}
    	return mapList;    	
	}
	
	/**
	 * 动态货箱刷新
	 * Post: LOGGNUM
	 * Json:
	 * [{“VPLTNUM”:”64581”,"LOTNUM":”YXZZP1801001”,"CONVEYTYPE":”OME01_投料出库”,"SLOC ":”OME01_00111601900200”,"DLOC":”OME01_5365”,"STATUS":”Over”}]
	 * @return
	 */
	@RequestMapping(value = {"containLocationDynamic", ""})
	public List<Map<String, Object>> containLocationDynamic(HttpServletRequest request){
		List<Map<String, Object>> mapList = ListUtils.newArrayList();
		String loggnum = request.getParameter("loggnum");
		String VPLTNUM = "";
		String LOTNUM = "";
		String CONVEYTYPE = "";
		String SLOC = "";
		String DLOC = "";
		String STATUS = "";	
		TwmsTransferlogg transferlogg = new TwmsTransferlogg();
		int count = twmsTransferloggService.getCount()/1000;
		for(int i = 0;i<=count;i++){
			transferlogg = twmsTransferloggService.getByLoggnum(loggnum, i);
			if (transferlogg != null && transferlogg.getVpltnum() != null) {
				Map<String, Object> map = MapUtils.newHashMap();
				VPLTNUM = transferlogg.getVpltnum();
				LOTNUM = transferlogg.getLotnum();
				CONVEYTYPE = transferlogg.getConveytype();
				SLOC = transferlogg.getSloc();
				DLOC = transferlogg.getDloc();
				STATUS = transferlogg.getStatus();
				map.put("VPLTNUM", VPLTNUM);
				map.put("LOTNUM", LOTNUM);
				map.put("CONVEYTYPE", CONVEYTYPE);
				map.put("SLOC", SLOC);
				map.put("DLOC", DLOC);
				map.put("STATUS", STATUS);
				mapList.add(map);
				break;
			}
		}		
		return mapList;
	}

	/**
	 * 货箱入库时间，所属品牌相关数据
	 * Post:locNum
	 * @return
	 * json:
	 * { ”locNum”:”OME01_001165654987454”, “inTime”:”2019-1-19”,”brand”:”兰州细支珍品”,
	 * ”batch”:”YXZZP19021” ,”upBoxCtnno”:”201314”,”upBoxWeight”:101.5 ,
	 * ”downBoxCtnno”:”201314”,”downBoxWeight”:101.5}
	 * 
	 * Post: VPLTNUM  
	 *Json：
	 *{"CURRLOC":”OME01_00110401600100”,"ITEMDESC":”兰州(细支珍品)”,"LOTNUM":”YXZZP1801001”,"ENTERDATE":”2018-1-13 18:59:39”,"upBoxNum":”200714”,"downBoxNum":”200084”,"upBoxWeight":”200.20”,"downBoxWeight":”200.56”}

	 */
	@RequestMapping(value = {"boxStatics", ""})
	public List<Map<String, Object>> boxStatics(HttpServletRequest request){
    	List<Map<String, Object>> mapList = ListUtils.newArrayList();    	
    	String CURRLOC = "";
    	String currloc = "";
    	String ITEMDESC = "";
    	String LOTNUM = "";
    	String ENTERDATE = "";
    	String upBoxNum = "";
    	String downBoxNum = "";
    	double upBoxWeight = 0.0;
    	double downBoxWeight = 0.0;
    	for(BoxStatics boxStatics : twmsPltitemService.getNewBoxStatics(request.getParameter("vpltnum"))){
    		Map<String, Object> map = MapUtils.newHashMap();
    		currloc = boxStatics.getCurrloc();
    		CURRLOC = CompareDate.formatCurrLoc(currloc);
    		ITEMDESC = boxStatics.getItemdesc();
    		LOTNUM = boxStatics.getLotnum();
    		ENTERDATE = boxStatics.getEnterdate();
    		if (boxStatics.getLineNum() == 10) {
				upBoxNum = boxStatics.getBoxNum();
				upBoxWeight = boxStatics.getWeight();
				if (downBoxNum.equals("") && downBoxWeight == 0) 
					continue;
				else {
					map.put("currloc", currloc);
					map.put("CURRLOC", CURRLOC);
		        	map.put("ITEMDESC", ITEMDESC);
		        	map.put("LOTNUM", LOTNUM);
		        	map.put("ENTERDATE", ENTERDATE);
		        	map.put("upBoxNum", upBoxNum);
		        	map.put("downBoxNum", downBoxNum);
		        	map.put("upBoxWeight", upBoxWeight);
		        	map.put("downBoxWeight", downBoxWeight);
				}
			}else if (boxStatics.getLineNum() == 20) {
				downBoxNum = boxStatics.getBoxNum();
				downBoxWeight = boxStatics.getWeight();
				if (upBoxNum.equals("") && upBoxWeight == 0) 
					continue;
				else {
					map.put("currloc", currloc);
					map.put("CURRLOC", CURRLOC);
		        	map.put("ITEMDESC", ITEMDESC);
		        	map.put("LOTNUM", LOTNUM);
		        	map.put("ENTERDATE", ENTERDATE);
		        	map.put("upBoxNum", upBoxNum);
		        	map.put("downBoxNum", downBoxNum);
		        	map.put("upBoxWeight", upBoxWeight);
		        	map.put("downBoxWeight", downBoxWeight);
				}
			}else {
				map.put("Error", "数据库缺少相关数据");
			}        	
        	mapList.add(map);
    	}    	
    	return mapList;
    }
	
	/**
     * 添加货位、空箱和实箱数据
     */
    
    /**
     * 最新的库存信息
	 *Json：
	 *{"normalGoodsAllocation": 580,"goodsAllocation": 1000,"realCase": 500,"emptyCase ": 500}
     */
    @RequestMapping(value = {"goodsAllocationCount", ""})
    public Map<String, Object> goodsAllocationCount() {
		Map<String, Object> map = MapUtils.newHashMap();
		int goodsAllocation = twmsLocService.getGoodsAllocation();
		int normalGoodsAllocation = twmsLocService.getNormalGoodsAllocation();
		int realCase = twmsPltitemService.getRealCase();
		int emptyCase = twmsPltitemService.getEmptyCase();
		map.put("goodsAllocation", goodsAllocation);
		map.put("normalGoodsAllocation", normalGoodsAllocation);
		map.put("realCase", realCase);
		map.put("emptyCase", emptyCase);
		return map;
	}
    
    /**
     * 主界面实时库存信息列表
     * Json:
	 *[{“VPLTNUM”:”64581”,”CURRLOC”:”OME01_00112801100100”,"LINE":1,"LIE":13,"LAYER":1, "ITEMDESC":”兰州(细支珍品)”,"LOTNUM":”YXZZP1801001”,"ENTERDATE":”2018-1-13 18:59:39”,"upBoxNum":”200714”,"downBoxNum":”200084”,"upBoxWeight":”200.20”,"downBoxWeight":”200.56”}]
     * @return
     */
    @RequestMapping(value = {"goodsInfoList",""})
	public List<Map<String, Object>> goodsInfoList() {
		List<Map<String, Object>> mapList = ListUtils.newArrayList();
		int line = 0;
		int lie = 0;
		int layer = 0;
		String VPLTNUM = "";
		String CURRLOC = "";
		String ITEMDESC = "";
		String LOTNUM = "";
		String ENTERDATE = "";
		String upBoxNum = "";
		String downBoxNum = "";
		double upBoxWeight = 0.0;
		double downBoxWeight = 0.0;
		for (BoxStatics boxStatics1 : twmsPltitemService.getContainLocation()) {
			Map<String, Object> map = MapUtils.newHashMap();
			line = boxStatics1.getLine();
			lie = boxStatics1.getLie();
			layer = boxStatics1.getLayer();
			VPLTNUM = boxStatics1.getVplnum();
			for (BoxStatics boxStatics : twmsPltitemService.getNewBoxStatics(VPLTNUM)) {
				CURRLOC = boxStatics.getCurrloc();
				ITEMDESC = boxStatics.getItemdesc();
				LOTNUM = boxStatics.getLotnum();
				ENTERDATE = boxStatics.getEnterdate();
				if (boxStatics.getLineNum() == 10) {
					upBoxNum = boxStatics.getBoxNum();
					upBoxWeight = boxStatics.getWeight();
					if (downBoxNum.equals("") && downBoxWeight == 0)
						continue;
					else {
						map.put("line", line);
						map.put("lie", lie);
						map.put("layer", layer);
						map.put("VPLTNUM", VPLTNUM);
						map.put("CURRLOC", CURRLOC);
						map.put("ITEMDESC", ITEMDESC);
						map.put("LOTNUM", LOTNUM);
						map.put("ENTERDATE", ENTERDATE);
						map.put("upBoxNum", upBoxNum);
						map.put("downBoxNum", downBoxNum);
						map.put("upBoxWeight", upBoxWeight);
						map.put("downBoxWeight", downBoxWeight);
					}
				} else if (boxStatics.getLineNum() == 20) {
					downBoxNum = boxStatics.getBoxNum();
					downBoxWeight = boxStatics.getWeight();
					if (upBoxNum.equals("") && upBoxWeight == 0)
						continue;
					else {
						map.put("line", line);
						map.put("lie", lie);
						map.put("layer", layer);
						map.put("VPLTNUM", VPLTNUM);
						map.put("CURRLOC", CURRLOC);
						map.put("ITEMDESC", ITEMDESC);
						map.put("LOTNUM", LOTNUM);
						map.put("ENTERDATE", ENTERDATE);
						map.put("upBoxNum", upBoxNum);
						map.put("downBoxNum", downBoxNum);
						map.put("upBoxWeight", upBoxWeight);
						map.put("downBoxWeight", downBoxWeight);
					}
				} else {
					map.put("Error", "数据库缺少相关数据");
				}
				mapList.add(map);
			}
			mapList.add(map);
		}
		return CompareDate.removeRepeatMapByKey(mapList, "VPLTNUM");
	}  
    
    
    /**
     * 1.2.1.	即时库存信息情况
		每一个品牌总库存在所有品牌总库存的占比。
		Json：
	[{"brand":"xxx","sum":1500,"singleBrandTotalWeight":50.50,"allBrandTotalWeight": 100.5},{"brand":"xxx","sum":1500,"singleBrandTotalWeight": 50.50,"allBrandTotalWeight": 100.5}]

     */
    @RequestMapping(value = {"brandProportion",""})
	public List<Map<String, Object>> brandProportion() {
		List<Map<String, Object>> mapList = ListUtils.newArrayList();	
		List<Map<String, Object>> mapListCount = ListUtils.newArrayList();	
		String VPLTNUM = "";		
		String ITEMDESC = "";		
		String upBoxNum = "";
		String downBoxNum = "";
		double upBoxWeight = 0.0;
		double downBoxWeight = 0.0;
		for (BoxStatics boxStatics1 : twmsPltitemService.getContainLocation()) {
			Map<String, Object> map = MapUtils.newHashMap();
			VPLTNUM = boxStatics1.getVplnum();
			for (BoxStatics boxStatics : twmsPltitemService.getNewBoxStatics(VPLTNUM)) {
				ITEMDESC = boxStatics.getItemdesc().trim();
				if (boxStatics.getLineNum() == 10) {
					upBoxNum = boxStatics.getBoxNum();
					upBoxWeight = boxStatics.getWeight();
					if (downBoxNum.equals("") && downBoxWeight == 0)
						continue;
					else {
						map.put("VPLTNUM", VPLTNUM);
						map.put("ITEMDESC", ITEMDESC);
						map.put("upBoxWeight", upBoxWeight);
						map.put("downBoxWeight", downBoxWeight);
					}
				} else if (boxStatics.getLineNum() == 20) {
					downBoxNum = boxStatics.getBoxNum();
					downBoxWeight = boxStatics.getWeight();
					if (upBoxNum.equals("") && upBoxWeight == 0)
						continue;
					else {
						map.put("VPLTNUM", VPLTNUM);
						map.put("ITEMDESC", ITEMDESC);
						map.put("upBoxWeight", upBoxWeight);
						map.put("downBoxWeight", downBoxWeight);
					}
				} else {
					map.put("Error", "数据库缺少相关数据");
				}
				mapList.add(map);
			}
		}		
		mapList = CompareDate.removeRepeatMapByKey(mapList, "VPLTNUM");		
		List<String> list = new ArrayList<String>();		
		for(Map<String, Object> map: mapList){
			list.add(map.get("ITEMDESC").toString());
		}
		List<String> newList = new ArrayList<String>(new HashSet<>(list));
		System.err.println(newList);
		for(String brand: newList){
			int sum = 0;	
			double singleBrandTotalWeight = 0.0;
			for(Map<String, Object> map: mapList){
				if (map.get("ITEMDESC").equals(brand)) {
					++sum;
					singleBrandTotalWeight = (double) map.get("upBoxWeight") + (double) map.get("downBoxWeight");					
				}
			}
			Map<String, Object> temp = MapUtils.newHashMap();
			temp.put("brand", brand);
			temp.put("sum", sum);
			temp.put("singleBrandTotalWeight", singleBrandTotalWeight);
			mapListCount.add(temp);
		}
		return mapListCount;
    }
    
    /**
     * 1.2.2.	筛选之后的列表信息（新增分页范围参数）
	POST  rangeStart、rangeEnd
	{"brand":"XXX","rangeStart":1,"rangeEnd":500}

	新增排序
	{"brand":"XXX","rangeStart":1,"rangeEnd":500,”column”:”line/lie/layer”,”order”:”ascend/descend”}
	新增batch搜索
	
	Json:
	[{"VPLTNUM":"64581","CURRLOC":"OME01_00112801100100","LINE":1,"LIE":13,"LAYER":1,"ITEMDESC":"兰州(细支珍)","LOTNUM":"YXZZP1801001","ENTERDATE":"2018-1-13 18:59:39","upBoxNum":"200714","downBoxNum":"200084","upBoxWeight":"200.20","downBoxWeight":"200.56"}]

     * @param request
     * @return
     */
    @RequestMapping(value = {"goodsListFilter",""})
	public List<Map<String, Object>> goodsListFilter(HttpServletRequest request) {
		List<Map<String, Object>> mapList = ListUtils.newArrayList();
		int line = 0;
		int lie = 0;
		int layer = 0;
		String VPLTNUM = "";
		String CURRLOC = "";
		String ITEMDESC = "";
		String LOTNUM = "";
		String ENTERDATE = "";
		String upBoxNum = "";
		String downBoxNum = "";
		double upBoxWeight = 0.0;
		double downBoxWeight = 0.0;
		String column = request.getParameter("column");
		String order = request.getParameter("order");
		String batch = request.getParameter("batch");
		
		if (column == null || column.equals("")) {
			for (BoxStatics boxStatics1 : twmsPltitemService.filterNewBoxStatics(request.getParameter("brand"), Integer.parseInt(request.getParameter("rangeStart")), Integer.parseInt(request.getParameter("rangeEnd")))) {
				Map<String, Object> map = MapUtils.newHashMap();
				line = boxStatics1.getLine();
				lie = boxStatics1.getLie();
				layer = boxStatics1.getLayer();
				VPLTNUM = boxStatics1.getVplnum();
				for (BoxStatics boxStatics : twmsPltitemService.getNewBoxStatics(VPLTNUM)) {
					CURRLOC = CompareDate.formatCurrLoc(boxStatics.getCurrloc());
					ITEMDESC = boxStatics.getItemdesc();
					LOTNUM = boxStatics.getLotnum();
					ENTERDATE = boxStatics.getEnterdate();
					if (boxStatics.getLineNum() == 10) {
						upBoxNum = boxStatics.getBoxNum();
						upBoxWeight = boxStatics.getWeight();
						if (downBoxNum.equals("") && downBoxWeight == 0)
							continue;
						else {
							map.put("line", line);
							map.put("lie", lie);
							map.put("layer", layer);
							map.put("VPLTNUM", VPLTNUM);
							map.put("CURRLOC", CURRLOC);
							map.put("ITEMDESC", ITEMDESC);
							map.put("LOTNUM", LOTNUM);
							map.put("ENTERDATE", ENTERDATE);
							map.put("upBoxNum", upBoxNum);
							map.put("downBoxNum", downBoxNum);
							map.put("upBoxWeight", upBoxWeight);
							map.put("downBoxWeight", downBoxWeight);
						}
					} else if (boxStatics.getLineNum() == 20) {
						downBoxNum = boxStatics.getBoxNum();
						downBoxWeight = boxStatics.getWeight();
						if (upBoxNum.equals("") && upBoxWeight == 0)
							continue;
						else {
							map.put("line", line);
							map.put("lie", lie);
							map.put("layer", layer);
							map.put("VPLTNUM", VPLTNUM);
							map.put("CURRLOC", CURRLOC);
							map.put("ITEMDESC", ITEMDESC);
							map.put("LOTNUM", LOTNUM);
							map.put("ENTERDATE", ENTERDATE);
							map.put("upBoxNum", upBoxNum);
							map.put("downBoxNum", downBoxNum);
							map.put("upBoxWeight", upBoxWeight);
							map.put("downBoxWeight", downBoxWeight);
						}
					} else {
						map.put("Error", "数据库缺少相关数据");
					}
					mapList.add(map);
				}
				mapList.add(map);
			}
		}else if (column.equals("line")) {
			if (order.equals("descend")) {
				for (BoxStatics boxStatics1 : twmsPltitemService.filterNewBoxStaticsLineBatchDesc(request.getParameter("brand"), batch, Integer.parseInt(request.getParameter("rangeStart")), Integer.parseInt(request.getParameter("rangeEnd")))) {
					Map<String, Object> map = MapUtils.newHashMap();
					line = boxStatics1.getLine();
					lie = boxStatics1.getLie();
					layer = boxStatics1.getLayer();
					VPLTNUM = boxStatics1.getVplnum();
					for (BoxStatics boxStatics : twmsPltitemService.getNewBoxStatics(VPLTNUM)) {
						CURRLOC = CompareDate.formatCurrLoc(boxStatics.getCurrloc());
						ITEMDESC = boxStatics.getItemdesc();
						LOTNUM = boxStatics.getLotnum();
						ENTERDATE = boxStatics.getEnterdate();
						if (boxStatics.getLineNum() == 10) {
							upBoxNum = boxStatics.getBoxNum();
							upBoxWeight = boxStatics.getWeight();
							if (downBoxNum.equals("") && downBoxWeight == 0)
								continue;
							else {
								map.put("line", line);
								map.put("lie", lie);
								map.put("layer", layer);
								map.put("VPLTNUM", VPLTNUM);
								map.put("CURRLOC", CURRLOC);
								map.put("ITEMDESC", ITEMDESC);
								map.put("LOTNUM", LOTNUM);
								map.put("ENTERDATE", ENTERDATE);
								map.put("upBoxNum", upBoxNum);
								map.put("downBoxNum", downBoxNum);
								map.put("upBoxWeight", upBoxWeight);
								map.put("downBoxWeight", downBoxWeight);
							}
						} else if (boxStatics.getLineNum() == 20) {
							downBoxNum = boxStatics.getBoxNum();
							downBoxWeight = boxStatics.getWeight();
							if (upBoxNum.equals("") && upBoxWeight == 0)
								continue;
							else {
								map.put("line", line);
								map.put("lie", lie);
								map.put("layer", layer);
								map.put("VPLTNUM", VPLTNUM);
								map.put("CURRLOC", CURRLOC);
								map.put("ITEMDESC", ITEMDESC);
								map.put("LOTNUM", LOTNUM);
								map.put("ENTERDATE", ENTERDATE);
								map.put("upBoxNum", upBoxNum);
								map.put("downBoxNum", downBoxNum);
								map.put("upBoxWeight", upBoxWeight);
								map.put("downBoxWeight", downBoxWeight);
							}
						} else {
							map.put("Error", "数据库缺少相关数据");
						}
						mapList.add(map);
					}
					mapList.add(map);
				}
			}else {
				for (BoxStatics boxStatics1 : twmsPltitemService.filterNewBoxStaticsLineBatchAsc(request.getParameter("brand"), batch, Integer.parseInt(request.getParameter("rangeStart")), Integer.parseInt(request.getParameter("rangeEnd")))) {
					Map<String, Object> map = MapUtils.newHashMap();
					line = boxStatics1.getLine();
					lie = boxStatics1.getLie();
					layer = boxStatics1.getLayer();
					VPLTNUM = boxStatics1.getVplnum();
					for (BoxStatics boxStatics : twmsPltitemService.getNewBoxStatics(VPLTNUM)) {
						CURRLOC = CompareDate.formatCurrLoc(boxStatics.getCurrloc());
						ITEMDESC = boxStatics.getItemdesc();
						LOTNUM = boxStatics.getLotnum();
						ENTERDATE = boxStatics.getEnterdate();
						if (boxStatics.getLineNum() == 10) {
							upBoxNum = boxStatics.getBoxNum();
							upBoxWeight = boxStatics.getWeight();
							if (downBoxNum.equals("") && downBoxWeight == 0)
								continue;
							else {
								map.put("line", line);
								map.put("lie", lie);
								map.put("layer", layer);
								map.put("VPLTNUM", VPLTNUM);
								map.put("CURRLOC", CURRLOC);
								map.put("ITEMDESC", ITEMDESC);
								map.put("LOTNUM", LOTNUM);
								map.put("ENTERDATE", ENTERDATE);
								map.put("upBoxNum", upBoxNum);
								map.put("downBoxNum", downBoxNum);
								map.put("upBoxWeight", upBoxWeight);
								map.put("downBoxWeight", downBoxWeight);
							}
						} else if (boxStatics.getLineNum() == 20) {
							downBoxNum = boxStatics.getBoxNum();
							downBoxWeight = boxStatics.getWeight();
							if (upBoxNum.equals("") && upBoxWeight == 0)
								continue;
							else {
								map.put("line", line);
								map.put("lie", lie);
								map.put("layer", layer);
								map.put("VPLTNUM", VPLTNUM);
								map.put("CURRLOC", CURRLOC);
								map.put("ITEMDESC", ITEMDESC);
								map.put("LOTNUM", LOTNUM);
								map.put("ENTERDATE", ENTERDATE);
								map.put("upBoxNum", upBoxNum);
								map.put("downBoxNum", downBoxNum);
								map.put("upBoxWeight", upBoxWeight);
								map.put("downBoxWeight", downBoxWeight);
							}
						} else {
							map.put("Error", "数据库缺少相关数据");
						}
						mapList.add(map);
					}
					mapList.add(map);
				}
			}
		}else if (column.equals("lie")) { //列排序
			if (order.equals("descend")) {
				for (BoxStatics boxStatics1 : twmsPltitemService.filterNewBoxStaticsLieBatchDesc(request.getParameter("brand"), batch, Integer.parseInt(request.getParameter("rangeStart")), Integer.parseInt(request.getParameter("rangeEnd")))) {
					Map<String, Object> map = MapUtils.newHashMap();
					line = boxStatics1.getLine();
					lie = boxStatics1.getLie();
					layer = boxStatics1.getLayer();
					VPLTNUM = boxStatics1.getVplnum();
					for (BoxStatics boxStatics : twmsPltitemService.getNewBoxStatics(VPLTNUM)) {
						CURRLOC = CompareDate.formatCurrLoc(boxStatics.getCurrloc());
						ITEMDESC = boxStatics.getItemdesc();
						LOTNUM = boxStatics.getLotnum();
						ENTERDATE = boxStatics.getEnterdate();
						if (boxStatics.getLineNum() == 10) {
							upBoxNum = boxStatics.getBoxNum();
							upBoxWeight = boxStatics.getWeight();
							if (downBoxNum.equals("") && downBoxWeight == 0)
								continue;
							else {
								map.put("line", line);
								map.put("lie", lie);
								map.put("layer", layer);
								map.put("VPLTNUM", VPLTNUM);
								map.put("CURRLOC", CURRLOC);
								map.put("ITEMDESC", ITEMDESC);
								map.put("LOTNUM", LOTNUM);
								map.put("ENTERDATE", ENTERDATE);
								map.put("upBoxNum", upBoxNum);
								map.put("downBoxNum", downBoxNum);
								map.put("upBoxWeight", upBoxWeight);
								map.put("downBoxWeight", downBoxWeight);
							}
						} else if (boxStatics.getLineNum() == 20) {
							downBoxNum = boxStatics.getBoxNum();
							downBoxWeight = boxStatics.getWeight();
							if (upBoxNum.equals("") && upBoxWeight == 0)
								continue;
							else {
								map.put("line", line);
								map.put("lie", lie);
								map.put("layer", layer);
								map.put("VPLTNUM", VPLTNUM);
								map.put("CURRLOC", CURRLOC);
								map.put("ITEMDESC", ITEMDESC);
								map.put("LOTNUM", LOTNUM);
								map.put("ENTERDATE", ENTERDATE);
								map.put("upBoxNum", upBoxNum);
								map.put("downBoxNum", downBoxNum);
								map.put("upBoxWeight", upBoxWeight);
								map.put("downBoxWeight", downBoxWeight);
							}
						} else {
							map.put("Error", "数据库缺少相关数据");
						}
						mapList.add(map);
					}
					mapList.add(map);
				}
			}else {
				for (BoxStatics boxStatics1 : twmsPltitemService.filterNewBoxStaticsLieBatchAsc(request.getParameter("brand"), batch, Integer.parseInt(request.getParameter("rangeStart")), Integer.parseInt(request.getParameter("rangeEnd")))) {
					Map<String, Object> map = MapUtils.newHashMap();
					line = boxStatics1.getLine();
					lie = boxStatics1.getLie();
					layer = boxStatics1.getLayer();
					VPLTNUM = boxStatics1.getVplnum();
					for (BoxStatics boxStatics : twmsPltitemService.getNewBoxStatics(VPLTNUM)) {
						CURRLOC = CompareDate.formatCurrLoc(boxStatics.getCurrloc());
						ITEMDESC = boxStatics.getItemdesc();
						LOTNUM = boxStatics.getLotnum();
						ENTERDATE = boxStatics.getEnterdate();
						if (boxStatics.getLineNum() == 10) {
							upBoxNum = boxStatics.getBoxNum();
							upBoxWeight = boxStatics.getWeight();
							if (downBoxNum.equals("") && downBoxWeight == 0)
								continue;
							else {
								map.put("line", line);
								map.put("lie", lie);
								map.put("layer", layer);
								map.put("VPLTNUM", VPLTNUM);
								map.put("CURRLOC", CURRLOC);
								map.put("ITEMDESC", ITEMDESC);
								map.put("LOTNUM", LOTNUM);
								map.put("ENTERDATE", ENTERDATE);
								map.put("upBoxNum", upBoxNum);
								map.put("downBoxNum", downBoxNum);
								map.put("upBoxWeight", upBoxWeight);
								map.put("downBoxWeight", downBoxWeight);
							}
						} else if (boxStatics.getLineNum() == 20) {
							downBoxNum = boxStatics.getBoxNum();
							downBoxWeight = boxStatics.getWeight();
							if (upBoxNum.equals("") && upBoxWeight == 0)
								continue;
							else {
								map.put("line", line);
								map.put("lie", lie);
								map.put("layer", layer);
								map.put("VPLTNUM", VPLTNUM);
								map.put("CURRLOC", CURRLOC);
								map.put("ITEMDESC", ITEMDESC);
								map.put("LOTNUM", LOTNUM);
								map.put("ENTERDATE", ENTERDATE);
								map.put("upBoxNum", upBoxNum);
								map.put("downBoxNum", downBoxNum);
								map.put("upBoxWeight", upBoxWeight);
								map.put("downBoxWeight", downBoxWeight);
							}
						} else {
							map.put("Error", "数据库缺少相关数据");
						}
						mapList.add(map);
					}
					mapList.add(map);
				}
			}
		}else if (column.equals("layer")) { //层排序
			if (order.equals("descend")) {
				for (BoxStatics boxStatics1 : twmsPltitemService.filterNewBoxStaticsLayerBatchDesc(request.getParameter("brand"), batch, Integer.parseInt(request.getParameter("rangeStart")), Integer.parseInt(request.getParameter("rangeEnd")))) {
					Map<String, Object> map = MapUtils.newHashMap();
					line = boxStatics1.getLine();
					lie = boxStatics1.getLie();
					layer = boxStatics1.getLayer();
					VPLTNUM = boxStatics1.getVplnum();
					for (BoxStatics boxStatics : twmsPltitemService.getNewBoxStatics(VPLTNUM)) {
						CURRLOC = CompareDate.formatCurrLoc(boxStatics.getCurrloc());
						ITEMDESC = boxStatics.getItemdesc();
						LOTNUM = boxStatics.getLotnum();
						ENTERDATE = boxStatics.getEnterdate();
						if (boxStatics.getLineNum() == 10) {
							upBoxNum = boxStatics.getBoxNum();
							upBoxWeight = boxStatics.getWeight();
							if (downBoxNum.equals("") && downBoxWeight == 0)
								continue;
							else {
								map.put("line", line);
								map.put("lie", lie);
								map.put("layer", layer);
								map.put("VPLTNUM", VPLTNUM);
								map.put("CURRLOC", CURRLOC);
								map.put("ITEMDESC", ITEMDESC);
								map.put("LOTNUM", LOTNUM);
								map.put("ENTERDATE", ENTERDATE);
								map.put("upBoxNum", upBoxNum);
								map.put("downBoxNum", downBoxNum);
								map.put("upBoxWeight", upBoxWeight);
								map.put("downBoxWeight", downBoxWeight);
							}
						} else if (boxStatics.getLineNum() == 20) {
							downBoxNum = boxStatics.getBoxNum();
							downBoxWeight = boxStatics.getWeight();
							if (upBoxNum.equals("") && upBoxWeight == 0)
								continue;
							else {
								map.put("line", line);
								map.put("lie", lie);
								map.put("layer", layer);
								map.put("VPLTNUM", VPLTNUM);
								map.put("CURRLOC", CURRLOC);
								map.put("ITEMDESC", ITEMDESC);
								map.put("LOTNUM", LOTNUM);
								map.put("ENTERDATE", ENTERDATE);
								map.put("upBoxNum", upBoxNum);
								map.put("downBoxNum", downBoxNum);
								map.put("upBoxWeight", upBoxWeight);
								map.put("downBoxWeight", downBoxWeight);
							}
						} else {
							map.put("Error", "数据库缺少相关数据");
						}
						mapList.add(map);
					}
					mapList.add(map);
				}
			}else {
				for (BoxStatics boxStatics1 : twmsPltitemService.filterNewBoxStaticsLayerBatchAsc(request.getParameter("brand"), batch, Integer.parseInt(request.getParameter("rangeStart")), Integer.parseInt(request.getParameter("rangeEnd")))) {
					Map<String, Object> map = MapUtils.newHashMap();
					line = boxStatics1.getLine();
					lie = boxStatics1.getLie();
					layer = boxStatics1.getLayer();
					VPLTNUM = boxStatics1.getVplnum();
					for (BoxStatics boxStatics : twmsPltitemService.getNewBoxStatics(VPLTNUM)) {
						CURRLOC = CompareDate.formatCurrLoc(boxStatics.getCurrloc());
						ITEMDESC = boxStatics.getItemdesc();
						LOTNUM = boxStatics.getLotnum();
						ENTERDATE = boxStatics.getEnterdate();
						if (boxStatics.getLineNum() == 10) {
							upBoxNum = boxStatics.getBoxNum();
							upBoxWeight = boxStatics.getWeight();
							if (downBoxNum.equals("") && downBoxWeight == 0)
								continue;
							else {
								map.put("line", line);
								map.put("lie", lie);
								map.put("layer", layer);
								map.put("VPLTNUM", VPLTNUM);
								map.put("CURRLOC", CURRLOC);
								map.put("ITEMDESC", ITEMDESC);
								map.put("LOTNUM", LOTNUM);
								map.put("ENTERDATE", ENTERDATE);
								map.put("upBoxNum", upBoxNum);
								map.put("downBoxNum", downBoxNum);
								map.put("upBoxWeight", upBoxWeight);
								map.put("downBoxWeight", downBoxWeight);
							}
						} else if (boxStatics.getLineNum() == 20) {
							downBoxNum = boxStatics.getBoxNum();
							downBoxWeight = boxStatics.getWeight();
							if (upBoxNum.equals("") && upBoxWeight == 0)
								continue;
							else {
								map.put("line", line);
								map.put("lie", lie);
								map.put("layer", layer);
								map.put("VPLTNUM", VPLTNUM);
								map.put("CURRLOC", CURRLOC);
								map.put("ITEMDESC", ITEMDESC);
								map.put("LOTNUM", LOTNUM);
								map.put("ENTERDATE", ENTERDATE);
								map.put("upBoxNum", upBoxNum);
								map.put("downBoxNum", downBoxNum);
								map.put("upBoxWeight", upBoxWeight);
								map.put("downBoxWeight", downBoxWeight);
							}
						} else {
							map.put("Error", "数据库缺少相关数据");
						}
						mapList.add(map);
					}
					mapList.add(map);
				}
			}
		}else {
			Map<String, Object> map = MapUtils.newHashMap();
			map.put("Error", "传值错误");
		}
		return CompareDate.removeRepeatMapByKey(mapList, "VPLTNUM");
	}  
    
    /**
     * 1.2.3.3.	某个品牌的库存统计（增加请求条件brand）
	在下方能够添加所选品牌的统计数据总重量、箱数、批次数量
	Post：brand
	Json:  
	{“totalWeight”:1,”boxNumber”:5000,batchNumber”:50}

     * @param request
     * @return
     */
    @RequestMapping("brandWeightBatchCount")
    public Map<String, Object> brandWeightBatchCount(HttpServletRequest request) {
		Map<String, Object> map =  MapUtils.newHashMap();
		String brand = request.getParameter("brand");
		TwmsPltitem twmsPltitem = twmsPltitemService.brandWeightBatchCount(brand);
		double totalWeight = twmsPltitem.getWeight();
		int batchNumber = twmsPltitem.getLinenum();
		double boxNumber = twmsPltitem.getItemqty();
		if (brand.equals("空垛箱")) {
			map.put("totalWeight", totalWeight);
			map.put("boxNumber", boxNumber);
			map.put("batchNumber", batchNumber-1);
		}else {
			map.put("totalWeight", totalWeight);
			map.put("boxNumber", boxNumber);
			map.put("batchNumber", batchNumber);
		}
		return map;
	}
        
}
