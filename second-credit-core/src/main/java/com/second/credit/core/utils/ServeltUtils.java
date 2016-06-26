package com.second.credit.core.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.second.credit.core.model.CommParams;

/**
 * @note servelt 工具类
 * @author wangmeng
 * @date 2015年6月14日 上午12:16:27
 */
public class ServeltUtils {

    /**
     * @note 获取请求参数
     * @param request
     * @return
     * @author wangmeng
     * @date 2015年6月14日 上午1:27:16
     */
    public static CommParams<Map<String, Object>> getRequestParams(HttpServletRequest request) {
        String json = getRequestBody(request);
        CommParams<Map<String, Object>> appParams = JSON.parseObject(json, CommParams.class);
        return appParams;
    }

    /**
     * @note 通过流形式获取json格式的参数
     * @param request
     * @return
     * @author wangmeng
     * @date 2015年6月14日 上午1:09:08
     */
    private static String getRequestBody(HttpServletRequest request) {
        String json = null;
        InputStream is = null;
        BufferedReader br = null;
        try {
            is = request.getInputStream();
            StringBuilder messageBuffer = new StringBuilder();
            br = new BufferedReader(new InputStreamReader(is, "utf-8"));
            String line = null;
            while ((line = br.readLine()) != null) {
                messageBuffer.append(line);
            }
            json = messageBuffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeBufferedReader(br);
            closeInputStream(is);
        }
        return json;
    }

    /**
     * @note 关闭InputStream
     * @param is
     * @author wangmeng
     * @date 2015年6月14日 上午1:10:21
     */
    private static void closeInputStream(InputStream is) {
        try {
            if (is != null) {
                is.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @note 关闭BufferedReader
     * @param br
     * @author wangmeng
     * @date 2015年6月14日 上午1:10:38
     */
    private static void closeBufferedReader(BufferedReader br) {
        try {
            if (br != null) {
                br.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
     * @note 响应消息
     * @param response
     * @param msg
     * @throws IOException
     * @author wangmeng
     * @date 2015年6月14日 下午10:07:38
     */
    public static void sendResponse(HttpServletResponse response, String msg) throws IOException {
        response.setContentType("text/plain;charset=UTF-8");
        OutputStream out = response.getOutputStream();
        out.write(msg.getBytes("utf-8"));
        out.close();
    }

}
