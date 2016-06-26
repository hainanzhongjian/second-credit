package com.second.credit.core.utils;

import com.alibaba.fastjson.JSON;

/**
 * @note JSON工具类
 * @author wangmeng
 * @date 2015年7月29日下午8:44:34
 */
public class JsonUtils {

    /**
     * @json解析是否正确
     * @param result
     * @return
     * @author wangmeng
     * @time 2015年5月4日下午9:58:49
     */
    public static boolean parseObjectTrue(String result) {
        try {
            JSON.parseObject(result);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
