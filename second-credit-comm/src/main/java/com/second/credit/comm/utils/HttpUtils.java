package com.second.credit.comm.utils;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class HttpUtils {

    // 获取IP
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * map转化
     * 
     * @param map
     * @return
     * @author wangmeng
     * @time 2014年12月16日下午4:17:12
     */
    @SuppressWarnings("rawtypes")
    public static Map getParameterMap(Map<String, String[]> map) {
        Map<String, Object> para = new HashMap<String, Object>();
        Iterator it = map.keySet().iterator();
        String key;
        String[] strings;
        for (; it.hasNext();) {
            key = (String) it.next();
            strings = map.get(key);
            para.put(key, strings[0]);
        }
        return para;
    }

    /**
     * map转化
     * 
     * @param srcPara
     * @return
     * @author wangmeng
     * @time 2014年12月16日下午4:17:12
     */
    @SuppressWarnings("rawtypes")
    public static Map<String, Object> getParameterObjectMap(Map srcPara) {
        Map<String, Object> para = new HashMap<String, Object>();
        Iterator it = srcPara.keySet().iterator();
        String key;
        String[] strings;
        for (; it.hasNext();) {
            key = (String) it.next();
            strings = (String[]) srcPara.get(key);
            para.put(key, strings[0]);
        }
        return para;
    }

    /**
     * 输出json对象
     * 
     * @param response
     * @param map
     * @author wangmeng
     * @time 2014年12月16日下午4:18:05
     */
    @SuppressWarnings("rawtypes")
    public static void responseMsg(HttpServletResponse response, Map map) {
        try {
            // res.setCharacterEncoding("utf-8");
            response.setContentType("text/html; charset=utf-8");
            PrintWriter out = response.getWriter();
            Gson gson = new GsonBuilder().serializeNulls().create();
            out.print(gson.toJson(map));
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
