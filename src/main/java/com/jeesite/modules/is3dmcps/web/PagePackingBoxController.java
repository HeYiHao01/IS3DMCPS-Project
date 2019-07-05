package com.jeesite.modules.is3dmcps.web;

import com.jeesite.common.collect.ListUtils;
import com.jeesite.common.collect.MapUtils;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.is3dmcps.entity.BoxStatics;
import com.jeesite.modules.is3dmcps.entity.IsLocation;
import com.jeesite.modules.is3dmcps.entity.Position;
import com.jeesite.modules.is3dmcps.service.*;
import com.jeesite.modules.twms.entity.TwmsLoc;
import com.jeesite.modules.twms.entity.TwmsTransferlogg;
import com.jeesite.modules.twms.service.TwmsLocService;
import com.jeesite.modules.twms.service.TwmsPltitemService;
import com.jeesite.modules.twms.service.TwmsTransferloggService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Map;

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
		for(int i = 0;i<200;i++){
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
    	String ITEMDESC = "";
    	String LOTNUM = "";
    	String ENTERDATE = "";
    	String upBoxNum = "";
    	String downBoxNum = "";
    	double upBoxWeight = 0.0;
    	double downBoxWeight = 0.0;
    	for(BoxStatics boxStatics : twmsPltitemService.getNewBoxStatics(request.getParameter("vpltnum"))){
    		Map<String, Object> map = MapUtils.newHashMap();
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
		}
		return mapList;
	}
}
