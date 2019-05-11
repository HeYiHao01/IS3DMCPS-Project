package com.jeesite.utils;

import java.util.Calendar;

public class certificateNoUtil {
    /**
     * 通过身份证号码获取出生日期
     * @param certificateNo
     * @return 返回的出生日期格式：1990-01-01   性别格式：F-女，M-男
     */
    public static String getBirthdate(String certificateNo) {
        String birthday = "";

       char[] number = certificateNo.toCharArray();
        boolean flag = true;
        if (number.length == 15) {
            for (int x = 0; x < number.length; x++) {
                if (!flag) return  "";
                flag = Character.isDigit(number[x]);
            }
        } else if (number.length == 18) {
            for (int x = 0; x < number.length - 1; x++) {
                if (!flag) return "";
                flag = Character.isDigit(number[x]);
            }
        }
        if (flag && certificateNo.length() == 15) {
            birthday = "19" + certificateNo.substring(6, 8) + "-"
                    + certificateNo.substring(8, 10) + "-"
                    + certificateNo.substring(10, 12);
        } else if (flag && certificateNo.length() == 18) {
            birthday = certificateNo.substring(6, 10) + "-"
                    + certificateNo.substring(10, 12) + "-"
                    + certificateNo.substring(12, 14);
       }
        return  birthday;
    }
    
    /**
     * 通过身份证号码获取出生日期、性别、年龄
     * @param certificateNo
     * @return 返回的出生日期格式：1990-01-01   性别格式：F-女，M-男
     */
    public static String getAge(String certificateNo) {
        String age = "";
 
        int year = Calendar.getInstance().get(Calendar.YEAR);
        char[] number = certificateNo.toCharArray();
        boolean flag = true;
        if (number.length == 15) {
            for (int x = 0; x < number.length; x++) {
                if (!flag) return "";
                flag = Character.isDigit(number[x]);
            }
        } else if (number.length == 18) {
            for (int x = 0; x < number.length - 1; x++) {
                if (!flag) return "";
                flag = Character.isDigit(number[x]);
            }
        }
        if (flag && certificateNo.length() == 15) {
            age = (year - Integer.parseInt("19" + certificateNo.substring(6, 8))) + "";
        } else if (flag && certificateNo.length() == 18) {
            age = (year - Integer.parseInt(certificateNo.substring(6, 10))) + "";
        }
        return  age;
    }
    /**
     * 通过身份证号码获取出生日期、性别、年龄
     * @param certificateNo
     * @return 返回的出生日期格式：1990-01-01   性别格式：F-女，M-男
     */
    public static String getSex(String certificateNo) {
        String sexCode = "";

        char[] number = certificateNo.toCharArray();
        boolean flag = true;
        if (number.length == 15) {
            for (int x = 0; x < number.length; x++) {
                if (!flag) return "";
                flag = Character.isDigit(number[x]);
            }
        } else if (number.length == 18) {
            for (int x = 0; x < number.length - 1; x++) {
                if (!flag) return "";
                flag = Character.isDigit(number[x]);
            }
        }
        if (flag && certificateNo.length() == 15) {
            sexCode = Integer.parseInt(certificateNo.substring(certificateNo.length() - 3, certificateNo.length())) % 2 == 0 ? "F" : "M";
        } else if (flag && certificateNo.length() == 18) {
            sexCode = Integer.parseInt(certificateNo.substring(certificateNo.length() - 4, certificateNo.length() - 1)) % 2 == 0 ? "F" : "M";
        }
        return sexCode;
    }

}
