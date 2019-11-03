package com.jeesite.modules.is3dmcps.web;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jeesite.common.collect.ListUtils;
import com.jeesite.common.collect.MapUtils;
import com.jeesite.common.lang.StringUtils;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.is3dmcps.entity.IsDeviceCode;
import com.jeesite.modules.is3dmcps.entity.IsMaintain;
import com.jeesite.modules.is3dmcps.entity.IsMaintainRec;
import com.jeesite.modules.is3dmcps.service.IsDeviceCodeService;
import com.jeesite.modules.is3dmcps.service.IsKnowledgeService;
import com.jeesite.modules.is3dmcps.service.IsMaintainRecService;
import com.jeesite.modules.is3dmcps.service.IsMaintainService;

@CrossOrigin
@RestController
@RequestMapping(value = "static")
public class testController extends BaseController {
	@Autowired
	private IsMaintainRecService isMaintainRecService;
	
	@RequestMapping(value = {"maintainRecList", ""})
	public List<Map<String, Object>> maintainRecList() {
		List<IsMaintainRec> list = isMaintainRecService.findList(new IsMaintainRec());
		List<Map<String, Object>> mapList = ListUtils.newArrayList();
		for(IsMaintainRec ismaintainrec:list) {
			Map<String, Object> map = MapUtils.newHashMap();
			map.put("id", ismaintainrec.getId());
			map.put("maintainId", ismaintainrec.getMaintainId());
			map.put("deviceNo", ismaintainrec.getDeviceNo());
			map.put("planPerson", ismaintainrec.getPlanPerson());
			if(ismaintainrec.getPlanDate()!=null) {
				map.put("planDate", ismaintainrec.getPlanDate().getTime());
			}else {
				map.put("planDate", "");
			}
			
			map.put("record", ismaintainrec.getRecord());
			map.put("maintainPerson", ismaintainrec.getMaintainPerson());
			if(ismaintainrec.getMaintainTime()!=null) {
				map.put("maintainTime", ismaintainrec.getMaintainTime().getTime());
			}else {
				map.put("maintainTime", "");
			}
			
			mapList.add(map);	
		}
		return mapList;
	}
	
}