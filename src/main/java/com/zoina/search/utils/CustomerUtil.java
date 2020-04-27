/*
 * projectName: zoina-search
 * fileName: CustomerUtil.java
 * packageName: com.zoina.search.utils
 * date: 2020-04-17 9:55
 */
package com.zoina.search.utils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @version: V1.0
 * @author: 吴洪阳
 * @className: CustomerUtil
 * @packageName: com.zoina.search.utils
 * @description:
 * @data: 2020-04-17 9:55
 **/
public class CustomerUtil {
    /**
     * 判断是否为null 或者""
     *
     * @param objects
     * @return
     */
    public static boolean objIsEmpty(Object... objects) {
        for (Object obj : objects) {
            if (null == obj || "".equals(obj)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 校验手机号
     *
     * @param phone
     * @return
     */
    public static boolean isPhoneNO(String phone) {
        //String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";
        String regex="(^$)|^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8}$|^0\\d{2,3}-?\\d{7,8}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(phone);
        boolean isMatch = m.matches();
        return isMatch ? true : false;
    }

//    /**
//     * 根据请求获取浏览器名称
//     *
//     * @param request
//     * @return
//     */
//    public static String getBrowserName(HttpServletRequest request) {
//        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
//        //获取浏览器信息
//        Browser browser = userAgent.getBrowser();
//        return browser.getName();
//    }

//    /**
//     * 获取操作系统名称
//     *
//     * @param request
//     * @return
//     */
//    public static String getOSName(HttpServletRequest request) {
//        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
//        //获取操作系统信息
//        OperatingSystem operatingSystem = userAgent.getOperatingSystem();
//        return operatingSystem.getName();
//    }


    /**
     * object 转换为 String
     *
     * @param obj
     * @return
     */
    public static String Obj2Str(Object obj) {
        if (null == obj) {
            return "";
        }
        return obj.toString();
    }

    /**
     * 判断 object 是否 == 0
     *
     * @param object
     * @return
     */
    public static boolean objIsEq0(Object object) {
        final String val = "0";
        if (val.equals(CustomerUtil.Obj2Str(object))) {
            return true;
        }
        return false;
    }

    /**
     * 判断对象中的所有属性是否有值
     * 所有属性都为null 或者 "" 返回 true 其他返回false
     *
     * @param obj
     * @return
     */
    public static boolean objAllValisEmpty(Object obj) {

        if (null == obj) {
            return true;
        }

        Field[] fields = obj.getClass().getDeclaredFields();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                if (objIsEmpty(field.get(obj))) {
                    return true;
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 多值返回第一个有值参数的值
     * ex: "",null,1,2,3  --->  1
     *
     * @param objects
     * @return
     */
    public static Object getVal(Object... objects) {
        for (Object obj : objects) {
            if (null != obj || !"".equals(obj)) {
                return obj;
            }
        }
        return "";
    }

    /**
     * 多值返回第一个有值参数的值
     *
     * @param nums
     * @return
     */
    public static Integer getVal(Integer... nums) {
        for (Integer num : nums) {
            if (num != null) {
                return num;
            }
        }
        return -1;
    }
}