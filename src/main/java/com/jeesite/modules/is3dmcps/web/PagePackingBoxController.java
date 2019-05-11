package com.jeesite.modules.is3dmcps.web;

import com.jeesite.common.collect.ListUtils;
import com.jeesite.common.collect.MapUtils;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.is3dmcps.entity.IsFaults;
import com.jeesite.modules.is3dmcps.entity.IsMaintain;
import com.jeesite.modules.is3dmcps.entity.IsPatrol;
import com.jeesite.modules.is3dmcps.service.*;
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
		double location_X;
		double location_Y;
		double location_Z;
		locNum="39";
		row=1;
		col=2;
		layer=4;
		location_X=-1.2400000095367432;
		location_Y=-0.41100001335144043;
		location_Z=-59.194000244140625;
		Map<String, Object> map = MapUtils.newHashMap();
		map.put("locNum",locNum);
		map.put("row",row);
		map.put("col",col);
		map.put("layer",layer);
		map.put("location_X",location_X);
		map.put("location_Y",location_Y);
		map.put("location_Z",location_Z);
		mapList.add(map);
		String locNum1;
		int row1;
		int col1;
		int layer1;
		double location_X1;
		double location_Y1;
		double location_Z1;
		locNum1="39";
		row1=1;
		col1=2;
		layer1=4;
		location_X1=-1.2400000095367432;
		location_Y1=-0.41100001335144043;
		location_Z1=-59.194000244140625;
		Map<String, Object> map1 = MapUtils.newHashMap();
		map1.put("locNum",locNum1);
		map1.put("row",row1);
		map1.put("col",col1);
		map1.put("layer",layer1);
		map1.put("location_X",location_X1);
		map1.put("location_Y",location_Y1);
		map1.put("location_Z",location_Z1);
		mapList.add(map1);
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
	}
}
