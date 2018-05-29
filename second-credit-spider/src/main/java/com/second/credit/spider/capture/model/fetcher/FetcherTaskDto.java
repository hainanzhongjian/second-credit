package com.second.credit.spider.capture.model.fetcher;

import org.apache.http.impl.client.CloseableHttpClient;

import java.io.Serializable;

/**
 * @note 爬取参数model
 * @author wangmeng
 * @date 2017年3月21日 上午11:22:50
 */
public class FetcherTaskDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * @note 申请编号
     */
    private String applyNo;
    /**
     * @note 产品类型
     */
    private String productType;
    /**
     * @note 身份证号
     */
    private String idNo;
    /**
     * @note client
     */
    private CloseableHttpClient httpClient;
    /**
     * @note 请求地址
     */
    private String url;

    /**
     * @note 城市编码
     */
    private int cityCode;

    /**
     * @note 爬取类型
     */
    private int fetcherType;

    public String getApplyNo() {
        return applyNo;
    }

    public void setApplyNo(String applyNo) {
        this.applyNo = applyNo;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public CloseableHttpClient getHttpClient() {
        return httpClient;
    }

    public void setHttpClient(CloseableHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public int getCityCode() {
        return cityCode;
    }

    public void setCityCode(int cityCode) {
        this.cityCode = cityCode;
    }

    public int getFetcherType() {
        return fetcherType;
    }

    public void setFetcherType(int fetcherType) {
        this.fetcherType = fetcherType;
    }

}
