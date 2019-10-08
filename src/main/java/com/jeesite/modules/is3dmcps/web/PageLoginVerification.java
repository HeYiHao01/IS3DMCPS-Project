package com.jeesite.modules.is3dmcps.web;

import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jeesite.common.collect.MapUtils;
import com.jeesite.common.web.BaseController;

/**
 * 用户验证模块
 * @author HAO
 *
 */
@CrossOrigin
@RestController
@RequestMapping(value = "static")
public class PageLoginVerification extends BaseController {
	/**
	 * 測試登陸驗證方法
	 * @return
	 */
	public Map<String, Object> loginConfirm() {
		Map<String, Object> map = MapUtils.newHashMap();
		map.put("status", "OK");
		return map;
	}
}
