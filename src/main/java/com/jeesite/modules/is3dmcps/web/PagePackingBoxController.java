package com.jeesite.modules.is3dmcps.web;

import com.jeesite.common.collect.ListUtils;
import com.jeesite.common.collect.MapUtils;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.is3dmcps.entity.BoxStatics;
import com.jeesite.modules.is3dmcps.entity.IsFaults;
import com.jeesite.modules.is3dmcps.entity.IsLocation;
import com.jeesite.modules.is3dmcps.entity.IsMaintain;
import com.jeesite.modules.is3dmcps.entity.IsPatrol;
import com.jeesite.modules.is3dmcps.entity.Position;
import com.jeesite.modules.is3dmcps.service.*;
import com.jeesite.modules.twms.entity.TwmsLoc;
import com.jeesite.modules.twms.service.TwmsLocService;
import com.jeesite.modules.twms.service.TwmsPltitemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import java.util.Iterator;
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
		for(TwmsLoc twmsLoc:twmsLocService.getAll()){
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
	 * 货箱入库时间，所属品牌相关数据
	 * Post:locNum
	 * @return
	 * json:
	 * { ”locNum”:”OME01_001165654987454”, “inTime”:”2019-1-19”,”brand”:”兰州细支珍品”,
	 * ”batch”:”YXZZP19021” ,”upBoxCtnno”:”201314”,”upBoxWeight”:101.5 ,
	 * ”downBoxCtnno”:”201314”,”downBoxWeight”:101.5}
	 */
	@RequestMapping(value = {"boxStatics", ""})
	public List<Map<String, Object>> boxStatics(HttpServletRequest request){
    	List<Map<String, Object>> mapList = ListUtils.newArrayList();
    	int line = 0;
    	int lie = 0;
    	int layer = 0;
    	double location_X = 0.0;
    	double location_Y = 0.0;
    	double location_Z = 0.0;    	
    	String VPLTNUM = "";
    	String PLTNUM = "";
    	String CURRLOC = "";
    	String ITEMDESC = "";
    	String LOTNUM = "";
    	String ENTERDATE = "";
    	for(BoxStatics boxStatics : twmsPltitemService.getBoxStatics(request.getParameter("vpltnum"))){
    		Map<String, Object> map = MapUtils.newHashMap();
    		line = boxStatics.getLine();
    		lie = boxStatics.getLie();
    		layer = boxStatics.getLayer();
    		location_X = boxStatics.getLocation_x();
    		location_Y = boxStatics.getLocation_y();
    		location_Z = boxStatics.getLocation_z();
    		VPLTNUM = boxStatics.getVplnum();
    		PLTNUM = boxStatics.getPltnum();
    		CURRLOC = boxStatics.getCurrloc();
    		ITEMDESC = boxStatics.getItemdesc();
    		LOTNUM = boxStatics.getLotnum();
    		ENTERDATE = boxStatics.getEnterdate();
        	map.put("line", line);
        	map.put("lie", lie);
        	map.put("layer", layer);
        	map.put("location_X", location_X);
        	map.put("location_X", location_Y);
        	map.put("location_X", location_Z);
        	map.put("VPLTNUM", VPLTNUM);
        	map.put("PLTNUM", PLTNUM);
        	map.put("CURRLOC", CURRLOC);
        	map.put("ITEMDESC", ITEMDESC);
        	map.put("LOTNUM", LOTNUM);
        	map.put("ENTERDATE", ENTERDATE);
        	mapList.add(map);
    	}    	
    	return mapList;
    }
	/*
	public Map<String, Object> maintainRecList(HttpServletRequest request) {
		String locNum=request.getParameter("locNum");
		System.out.println("参数："+locNum);
		//String locNum;
		String inTime;
		String brand;
		String batch;
		String upBoxCtnno;
		double upBoxWeight;
		String downBoxCtnno;
		double downBoxWeight;
		locNum="OME01_001165654987454";
		inTime="2019-1-19";
		brand="兰州细支珍品";
		batch="YXZZP19021";
		upBoxCtnno="201314";
		upBoxWeight=101.5;
		downBoxCtnno="201314";
		downBoxWeight=101.5;
		Map<String, Object> map = MapUtils.newHashMap();
		map.put("locNum",locNum);
		map.put("inTime",inTime);
		map.put("brand",brand);
		map.put("batch",batch);
		map.put("upBoxCtnno",upBoxCtnno);
		map.put("upBoxWeight",upBoxWeight);
		map.put("downBoxCtnno",downBoxCtnno);
		map.put("downBoxWeight",downBoxWeight);
		return map;
	}*/
}
