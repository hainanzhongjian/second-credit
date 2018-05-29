package com.second.credit.spider.run;

import com.second.credit.spider.capture.model.fetcher.FetcherResponse;
import org.apache.log4j.Logger;

import java.util.Map;

/**
 * Desc 定义爬虫接口
 * Auther wangmeng
 * Date 2018/5/22 21:30
 */
public interface SpiderRunInterface {

    FetcherResponse login(Map<String, Object> params);

    FetcherResponse capture(Map<String, Object> params);

    FetcherResponse parser(Map<String, Object> params);
}
