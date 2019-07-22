package com.jeesite.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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
		System.out.println(simplifyDate("2019.07.17"));
	}
}
