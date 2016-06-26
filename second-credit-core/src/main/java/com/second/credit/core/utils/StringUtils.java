package com.second.credit.core.utils;

import com.alibaba.fastjson.JSON;

/**
 * @note String工具类
 * @author wangmeng
 * @date 2015年6月14日 下午9:54:02
 */
public class StringUtils {

    /**
     * @note 字符串是否为空
     * @param str
     * @return
     * @author wangmeng
     * @date 2015年6月14日 下午9:54:20
     */
    public static boolean isEmpty(String str) {
        if (str == null || "".equals(str) || str.equals("null")) {
            return true;
        }
        return false;
    }

    /**
     * @note 字符串解析成long
     * @param str
     * @return
     * @author wangmeng
     * @date 2015年6月14日 下午9:54:47
     */
    public static boolean parseToLongTrue(String str) {
        try {
            Long.parseLong(str);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * @note 字符串解析成int
     * @param str
     * @return
     * @author wangmeng
     * @date 2015年6月14日 下午9:55:20
     */
    public static boolean parseToIntTrue(String str) {
        try {
            Integer.parseInt(str);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * @note 字符串解析成double
     * @param str
     * @return
     * @author wangmeng
     * @date 2015年6月14日 下午9:55:42
     */
    public static boolean parseToDoubleTrue(String str) {
        try {
            Double.parseDouble(str);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * @note 字符串解析成json
     * @param josnStr
     * @return
     * @author wangmeng
     * @date 2015年6月14日 下午9:56:12
     */
    public static boolean parseToJsonTrue(String josnStr) {
        try {
            JSON.parseObject(josnStr);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * @note 验证值是否在合理的范围内
     * @param str
     * @param strArray
     * @return
     * @author wangmeng
     * @date 2015年6月14日 下午9:57:24
     */
    public static boolean validStrValueTrue(String str, String[] strArray) {
        if (!isEmpty(str) && strArray.length != 0) {
            for (int i = 0; i < strArray.length; i++) {
                if (str.equals(strArray[i]))
                    return true;
            }
        }
        return false;
    }
}
