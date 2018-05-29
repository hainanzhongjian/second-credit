package com.second.credit.spider.capture.model.fetcher;

import java.io.Serializable;
import java.util.List;

/**
 * @note 抓取结果数据结构构建
 * @author wangmeng
 * @date 2017年4月25日 上午11:17:12
 */
public class FetcherResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    // --------------------------CODE值定义--------------------------
    /**
     * 请求成功
     */
    public static final String CODE_SUCCESS = "00000";
    /**
     * 请求成功(伪成功-图片验证码)
     */
    public static final String CODE_FAKE_SUCCESS = "11111";
    /**
     * 系统级别的error
     */
    public static final String CODE_SYSTEM_ERROR = "90000";
    /**
     * 接口参数错误
     */
    public static final String CODE_INTER_PRAM_ERROR = "90002";
    /**
     * 获取验证码图片失败
     */
    public static final String CODE_CAPTURE_ERROR = "10003";
    /**
     * 不支持城市错误码
     */
    public static final String CODE_NOT_SUPPORT_CITYS = "10050";
    /**
     * 错误信息展示
     */
    public static final String CODE_ERROR_MESSAGE_SHOW = "20000";
    /**
     * 不需要图片验证码
     */
    public static final String CODE_NOT_NEED_CAPTCHA = "20001";

    // --------------------------常量类定义--------------------------
    /**
     * @note 错误码 :00000执行nextStep 其他：执行result
     */
    private String errorCode = "";

    /**
     * @note 下一个步骤
     */
    private NextStep nextStep; // NOSONAR

    /**
     * @note 结果
     */
    private Object result; // NOSONAR

    /**
     * @note 下一个步骤
     */
    public class NextStep {

        /**
         * @note 请求方式
         */
        public String requestType; // NOSONAR
        /**
         * @note 请求方法名
         */
        public String requestMethod; // NOSONAR
        /**
         * @note 请求参数
         */
        public List<Param> requestParams; // NOSONAR
    }

    /**
     * @note 参数
     */
    public class Param {

        /**
         * @note 描述
         */
        public String title; // NOSONAR

        /**
         * @note 键值
         */
        public String key; // NOSONAR

        /**
         * @note 类型
         */
        public String type; // NOSONAR
    }

    // --------------------------构造方法--------------------------

    /**
     * @note 构造方法中只存在下面四种构造方法，如果增加的话，请联系王猛（在这里做了一个构造方法约束）。
     * @author wangmeng
     * @date 2017年4月27日 下午5:58:27
     */
    public FetcherResponse() {
        this.setErrorCode(CODE_SUCCESS);
        this.setNextStep(null);
        this.setResult("登录成功");
    }

    public FetcherResponse(Object object) {
        this.setErrorCode(CODE_SUCCESS);
        this.setNextStep(null);
        this.setResult(object);
    }

    public FetcherResponse(String errorCode, Object result) {
        this.setErrorCode(errorCode);
        this.setNextStep(null);
        this.setResult(result);
    }

    public FetcherResponse(Object result, NextStep nextStep) {
        this.setErrorCode(CODE_SUCCESS);
        this.setNextStep(nextStep);
        this.setResult(result);
    }

    public FetcherResponse(String errorCode, Object result, NextStep nextStep) {
        this.setErrorCode(errorCode);
        this.setNextStep(nextStep);
        this.setResult(result);
    }

    // --------------------------参数方法--------------------------

    public static FetcherResponse errorParams() {
        return new FetcherResponse(CODE_INTER_PRAM_ERROR, "接口参数错误");
    }

    public static FetcherResponse errorCapture() {
        return new FetcherResponse(CODE_CAPTURE_ERROR, "图片验证码错误");
    }

    public static FetcherResponse errorNoSupportCitys() {
        return new FetcherResponse(CODE_NOT_SUPPORT_CITYS, "该城市不支持");
    }

    public static FetcherResponse errorMessageShow(String message) {
        return new FetcherResponse(CODE_ERROR_MESSAGE_SHOW, message);
    }

    // --------------------------set/get--------------------------

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public NextStep getNextStep() {
        return nextStep;
    }

    public void setNextStep(NextStep nextStep) {
        this.nextStep = nextStep;
    }

}
