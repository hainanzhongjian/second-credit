package com.second.credit.spider.task;

import com.second.credit.spider.capture.model.fetcher.HttpResultDto;
import com.second.credit.spider.utils.HttpUtils;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;

import java.util.HashMap;
import java.util.Map;

/**
 * Desc
 * Auther wangmeng
 * Date 2018/5/23 20:52
 */
public class TrafficPoliceAppRun {

    public static void main(String[] args) {
        // 获取 closeableHttpClient
        BasicCookieStore cookieStore = new BasicCookieStore();
        CloseableHttpClient httpClient = HttpUtils.getHttpClient(false, cookieStore);
        Map<String, Object> params = new HashMap<>();
        HttpResultDto resultDto = HttpUtils.executePostWithResult(httpClient, "",params);
    }
}
