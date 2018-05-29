package com.second.credit.spider.executer;

import com.second.credit.spider.capture.model.fetcher.FetcherResponse;
import com.second.credit.spider.capture.model.fetcher.FetcherResultDto;
import com.second.credit.spider.capture.model.fetcher.HttpResultDto;
import com.second.credit.spider.run.SpiderRunInterface;
import com.second.credit.spider.utils.HttpUtils;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.log4j.Logger;

import java.util.Map;

/**
 * Desc 北京交警客户端
 * Auther wangmeng
 * Date 2018/5/22 21:39
 */
public class TrafficPoliceApp_BeiJing implements SpiderRunInterface {
    private static final Logger logger = Logger.getLogger(TrafficPoliceApp_BeiJing.class);

    @Override
    public FetcherResponse login(Map<String, Object> params) {
        params.put("j_username", "");
        params.put("j_password", "");
        params.put("safecode", "");
        String applyNo = "";
        // 获取 closeableHttpClient
        BasicCookieStore cookieStore = new BasicCookieStore();
        CloseableHttpClient httpClient = HttpUtils.getHttpClient(false, cookieStore);

        HttpResultDto resultDto = HttpUtils.executePostWithResult(httpClient, "",params);

        // 结果处理
//        FetcherResultDto resultDTO = InsuranceFetcherResultParser.parserFetcherResult_BJ(applyNo, null);
//        logger.info("login: applyNo = " + applyNo + " is executed");
//        if (!resultDTO.success) {
//            return FetcherResponse.errorMessageShow(resultDTO.result);
//        }
        // 异步执行抓取
//        executeFetcher();
        return null;
    }

    @Override
    public FetcherResponse capture(Map<String, Object> params) {
        return null;
    }

    @Override
    public FetcherResponse parser(Map<String, Object> params) {
        return null;
    }
}
