package com.second.credit.spider.capture.model.fetcher;

import java.io.Serializable;

/**
 * @note 抓取结果DTO
 * @author wangmeng
 * @date 2017年4月7日 上午11:00:44
 */
public class FetcherResultDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * @note 成功与否
     */
    public Boolean success; // NOSONAR

    /**
     * @note 结果
     */
    public String result; // NOSONAR

}
