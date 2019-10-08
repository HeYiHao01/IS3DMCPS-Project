package com.jeesite.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class CompareDate {
    public static int compare_date(String DATE1, String DATE2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                System.out.println("dt1 在dt2前");
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                System.out.println("dt1在dt2后");
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }
    public static Date getTargetDate(Date LastTime,int interval){
        Calendar c = Calendar.getInstance();
        c.setTime(LastTime);
        c.add(Calendar.DAY_OF_MONTH,  interval);
        return c.getTime();
    }
    /**
	 * 获取当前和前七天的日期
	 */
	public static ArrayList<String> getSevenDate() {
		ArrayList<String> pastDaysList = new ArrayList<>();
		for (int i = 0; i < 7; i++) {			
			pastDaysList.add(String.valueOf(Calendar.getInstance().get(Calendar.YEAR))+"."+getPastDate(i));
		}
		return pastDaysList;
	}

	/**
	 * 获取过去第几天的日期
	 * 
	 * @param past
	 * @return
	 */
	public static String getPastDate(int past) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) - past);
		Date today = calendar.getTime();
		SimpleDateFormat format = new SimpleDateFormat("MM.dd");
		String result = format.format(today);
		return result;
	}
	
	/**
	 * 格式化日期字符串 05.01-->5.1
	 */
	/*public static String simplifyDate(String date) {
		int index = date.indexOf(".");
		int month;
		int day;
		if (date.substring(index - 2, index + 1).equals("0")) {
			month = Integer.parseInt(date.substring(index - 1, index ));
			day = Integer.parseInt(date.substring(index + 2));
		}else {
			month = Integer.parseInt(date.substring(index - 2, index ));
			day = Integer.parseInt(date.substring(index + 1));
		}
		String date1 = month + "." + day + "";
		return date1;
	}*/
	/**
	 * 格式化日期字符串2019.07.17-->7.17
	 */
	public static String simplifyDate(String date) {
		int index = date.lastIndexOf(".");
		int month;
		int day;
		if (date.substring(index - 2, index + 1).equals("0")) {
			month = Integer.parseInt(date.substring(index - 1, index ));
			day = Integer.parseInt(date.substring(index + 2));
		}else {
			month = Integer.parseInt(date.substring(index - 2, index ));
			day = Integer.parseInt(date.substring(index + 1));
		}
		String date1 = month + "." + day + "";
		return date1;
	}
	
	/**
	 * 格式化日期
	 */
	public static String simplifyDate(Date date) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(date);
	}
	
	public static String formatDate(String date) {
		Date dt = new Date(date);
		DateFormat df = new SimpleDateFormat("yyyy.MM.dd");
		return df.format(dt);
	}
	
	public static int dateCount(Date date) {
		Date now = new Date();
//		DateFormat df = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
//		Date dt = new Date(df.format(date));
//		long diff = now.getTime() - dt.getTime();		
		long diff = now.getTime() - date.getTime();
		long days = diff / (1000 * 60 * 60 * 24);
		return (int)days;
	}
	
	public static int dateCount(String date) {
		Date now = new Date();
		long days = 0;
		try {
			long diff = now.getTime() - new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").parse(date).getTime();
			days = diff / (1000 * 60 * 60 * 24);
		} catch (ParseException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}		
		return (int)days;
	}
	
	/**
	 * 格式化timestamp
	 * 2019.05.11.12:16:00.000000-->12:16
	 */
	public static String simplifyTime(Timestamp time) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd.HH:mm:ss");
		String newTime = format.format(time);
		int index = newTime.indexOf(":");
		int hour = Integer.parseInt(newTime.substring(index-2,index));
		int min = Integer.parseInt(newTime.substring(index+1,index+3));
		return hour+":"+min+"";
	}
	public static String simplifyTime(Date time) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd.HH:mm:ss");
		String newTime = format.format(time);
		int index = newTime.indexOf(":");
		int hour = Integer.parseInt(newTime.substring(index-2,index));
		int min = Integer.parseInt(newTime.substring(index+1,index+3));
		return hour+":"+min+"";
	}
	
	public static String formatCurrLoc(String currLoc) { 
		String loc = currLoc.trim();
		String result = "";
		int index = loc.indexOf("_");
		if (loc != null && loc.length()>0) {
			if ((index+5)>0 && (index+7)>0 && (index+10)>0 &&
					(index+7)<loc.length() && (index+10)<loc.length() && (index+13)<loc.length()) {
				String line = loc.substring(index+5, index+7);
				String lie = loc.substring(index+7, index+10);
				String layer = loc.substring(index+10, index+13);
				result = line+"行"+lie+"列"+layer+"层";
			}else {
				result = loc;
			}			
		}else {
			result = loc;
		}
		return result;
	}
	
	/**
	 * timestamp转为分钟
	 * 08:26:25.820000 -》 506
	 * @param time
	 * @return
	 */
	/*public static double getTimeCost(Timestamp time){
		SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd.HH:mm:ss");
		String newTime = format.format(time);
		int index1 = newTime.indexOf(":");
		int index2 = newTime.lastIndexOf(":");
		int hour = Integer.parseInt(newTime.substring(index1-2,index1));
		int min = Integer.parseInt(newTime.substring(index1+1,index2));
		double sec = Double.valueOf(newTime.substring(index2+1));
		return (hour * 60 + min + sec / 60);
	}
	public static double getTimeCost(Date time){
		SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd.HH:mm:ss");
		String newTime = format.format(time);
		int index1 = newTime.indexOf(":");
		int index2 = newTime.lastIndexOf(":");
		int hour = Integer.parseInt(newTime.substring(index1-2,index1));
		int min = Integer.parseInt(newTime.substring(index1+1,index2));
		double sec = Double.valueOf(newTime.substring(index2+1));
		return (hour * 60 + min + sec / 60);
	}*/
	
	public static void main(String[] args) {		
//		for(String date:CompareDate.getSevenDate())	{
//			System.out.println(simplifyDate(date));
//			Timestamp timestamp = Timestamp.valueOf("11-5月 -19 12.16.00.000000  下午");
//			System.out.println(simplifyTime((timestamp)));
//		}
//		System.out.println(getTimeCost(new Date("+00 08:26:25.820000")));
//		System.out.println(simplifyDate(new Date("11-9月 -17")));
		
		//Date date = new Date("2019/03/13 02:03:16");		
		//System.out.println(dateCount("2019.03.13 02:03:16"));
		//System.out.println(simplifyDate("2019.07.17"));
		//System.out.println(formatCurrLoc("OME01_00111401500100"));
		//System.out.println(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)-1));
	}
	
	/**
	 * MapList去重
	 */
	public static List<Map<String, Object>> removeRepeatMapByKey(List<Map<String, Object>> list, String mapKey) {
		if (list == null || list.isEmpty())
			return null;
		List<Map<String, Object>> listMap = new ArrayList<>();
		Map<String, Map> msp = new HashMap<>();
		for (int i = list.size() - 1; i >= 0; i--) {
			Map map = list.get(i);
			//String id = (String) map.get(mapKey);
			String id = String.valueOf(map.get(mapKey));
			map.remove(mapKey);
			msp.put(id, map);
		}
		Set<String> mspKey = msp.keySet();
		for (String key : mspKey) {
			Map newMap = msp.get(key);
			newMap.put(mapKey, key);
			listMap.add(newMap);
		}
		return listMap;
	}		
	
	/**
	 * Maplist根据多个key去重map
	 */
	public static List<Map<String, Object>> removeRepeatMaps(List<Map<String, Object>> list) {
		//新建set集合，利用set集合元素不能重复特点，找出重复数据
    	Set<ProductInfoMap> keysSet = new HashSet<ProductInfoMap>();
    	String brand;
    	int finishWeight;
    	int timeVariable;
    	double totalWeightThis;
    	double totalWeightLast;
    	Iterator<Map<String, Object>> it=list.iterator();
        while(it.hasNext()) {
        	Map<String, Object> map=it.next();
        	brand = (String)map.get("brand");
        	finishWeight = (int)map.get("finishWeight");
        	timeVariable = (int)map.get("timeVariable");
        	totalWeightThis = (double)map.get("totalWeightThis");
        	if (map.get("totalWeightLast") != null) {
        		totalWeightLast = (double)map.get("totalWeightLast");
			}else {
				totalWeightLast = 0.0;
			}
        	
        	ProductInfoMap productInfoMap = new ProductInfoMap();
        	productInfoMap.setBrand(brand);
        	productInfoMap.setFinishWeight(finishWeight);
        	productInfoMap.setTimeVariable(timeVariable);
        	productInfoMap.setTotalWeightLast(totalWeightLast);
        	productInfoMap.setTotalWeightThis(totalWeightThis);
        	
        	int beforeSize = keysSet.size();
            keysSet.add(productInfoMap);
            int afterSize = keysSet.size();
           //判断当前对象是否保存进set集合中（若未保存说明set集合已存在该数据，删除该条map数据）
            if(afterSize != (beforeSize + 1)) {
                it.remove();
            }
        }
        return list;
	}		
	
	public static List<Map<String, Object>> removeRepeatMapsForProductOut(List<Map<String, Object>> list) {
		List<Map<String, Object>> countList = new ArrayList<Map<String,Object>>();  
        for (int i = 0; i < list.size(); i++) {    
            String productionLineName = String.valueOf(list.get(i).get("productionLineName"));    
            String timeVariable = String.valueOf(list.get(i).get("timeVariable"));
            
            int flag = 0;//0为新增数据，1为增加count    
            for (int j = 0; j < countList.size(); j++) {    
                String newProductionLineName = String.valueOf(countList.get(j).get("productionLineName")); 
                String newTimeVariable = String.valueOf(countList.get(j).get("timeVariable"));
                if (newProductionLineName.equals(productionLineName) && newTimeVariable.equals(timeVariable)) {  
                    //做累加的操作  
                    double productionCapacity = Double.parseDouble(String.valueOf(list.get(i).get("productionCapacity"))) + Double.parseDouble(String.valueOf(countList.get(j).get("productionCapacity")));  
                    countList.get(j).put("productionCapacity", productionCapacity);    
                    flag = 1; 
                    break;    
                }    
            }    
            if (flag == 0) {    
                countList.add(list.get(i));    
            }    
        }    
        return countList;  		
	}		
	
	public static List<Map<String, Object>> removeRepeatMapsForProductInfo(List<Map<String, Object>> list) {
		List<Map<String, Object>> countList = new ArrayList<Map<String,Object>>();  
        for (int i = 0; i < list.size(); i++) {    
            String selectedName = String.valueOf(list.get(i).get("selectedName"));                
            
            int flag = 0;//0为新增数据，1为增加count    
            for (int j = 0; j < countList.size(); j++) {    
                String newSelectedName = String.valueOf(countList.get(j).get("selectedName"));                 
                if (newSelectedName.equals(selectedName)) {  
                    //做累加的操作  
                    double brandWeight = Double.parseDouble(String.valueOf(list.get(i).get("brandWeight"))) + Double.parseDouble(String.valueOf(countList.get(j).get("brandWeight")));
                    int batchNumber = Integer.parseInt(String.valueOf(list.get(i).get("batchNumber"))) + Integer.parseInt(String.valueOf(countList.get(j).get("batchNumber")));
                    countList.get(j).put("brandWeight", brandWeight);
                    countList.get(j).put("batchNumber", batchNumber);
                    flag = 1; 
                    break;    
                }    
            }    
            if (flag == 0) {    
                countList.add(list.get(i));    
            }    
        }    
        return countList;  		
	}	
	
	/**
	 * List合并
	 * @param m1
	 * @param m2
	 * @return
	 */
	public static List<Map<String, Object>> listMerge(List<Map<String, Object>> m1, List<Map<String, Object>> m2){
        
	    m1.addAll(m2);
	    
	    Set<String> set = new HashSet<>();
	    
	    return m1.stream()
	            .collect(Collectors.groupingBy(o->{
	                //暂存所有key
	                set.addAll(o.keySet());
	                //按brand分组
	                return o.get("brand");
	            })).entrySet().stream().map(o->{
	                
	                //合并
	                Map<String, Object> map = o.getValue().stream().flatMap(m->{
	                    return m.entrySet().stream();
	                }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a,b)->b));
	                
	                //为没有的key赋值0
	                set.stream().forEach(k->{
	                    if(!map.containsKey(k)) map.put(k, 0);
	                });
	                
	                return map;
	            }).collect(Collectors.toList());
	}
}
