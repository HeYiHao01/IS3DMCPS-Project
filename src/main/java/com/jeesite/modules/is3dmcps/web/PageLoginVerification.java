package com.jeesite.modules.is3dmcps.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.jeesite.common.codec.DesUtils;
import com.jeesite.common.collect.MapUtils;
import com.jeesite.common.config.Global;
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
	 *   secretKey: ~  
  	accessControlAllowOrigin: '*'
	 * @return
	 */
	@RequestMapping("loginConfirm")
	public Map<String, Object> loginConfirm(HttpServletRequest request) {
		Map<String, Object> map = MapUtils.newHashMap();										
		
		String secretKey = Global.getConfig("shiro.loginSubmit.secretKey");
		String username = DesUtils.encode(request.getParameter("userName"), secretKey);
		String password = DesUtils.encode(request.getParameter("password"), secretKey);
		System.err.println("&username=" + username + "&password=" + password);
		
		String address = "http://127.0.0.1:8390/js/a/login?__login=true&__ajax=json&username="+username
				+"&password="+password;
		try {
			URL url = new URL(address);
			HttpURLConnection connection = (HttpURLConnection)url.openConnection();
			connection.connect();
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));
			String line = null;
	        StringBuilder stringBuilder = new StringBuilder();
	        while ((line = br.readLine()) != null) {
	            stringBuilder.append(line);
	        }
	        br.close();
	        connection.disconnect();	        
	        JSONObject jsonObject = JSONObject.parseObject(stringBuilder.toString());	        
	        if (jsonObject.get("result").toString().equals("true")) {
	        	map.put("status", "OK");
			}else {
				map.put("status", "Error");
			}
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}								
		return map;
	}
}
