package com.second.credit.spider.capture.model.fetcher;

import java.io.Serializable;

/**
 * @note HTTP结果DTO
 * @author wangmeng
 * @date 2017年4月7日 上午11:00:44
 */
public class HttpResultDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * @note 结果
     */
    public String result; // NOSONAR

    /**
     * @note 重定向次数
     */
    public int redirectCounts = 0; // NOSONAR

}
