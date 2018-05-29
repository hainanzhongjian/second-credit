package com.second.credit.spider.capture.enums;

import java.util.EnumSet;

/**
 * @note 请求类型
 * @author wangmeng
 * @date 2017年4月26日 下午3:31:21
 */
public enum EnumFetcherParams {

    APPLYNO(1, "applyNo", "申请编号"),

    PRODUCTTYPE(2, "productType", "产品类型"),

    CITYCODE(3, "cityCode", "城市编码"),

    IDNUM(4, "idNum", "业务方身份证号"),

    CAPTCHA(5, "captcha", "图片验证码"),

    SMS_CAPTCHA(6, "smsCaptcha", "短信验证码"),

    LOGINNAME(7, "loginName", "登录名"),

    IDNO(8, "idNo", "身份证号"),

    PASSWORD(9, "password", "密码"),

    SERIALNUMBER(10, "serialNumber", "社保编号"),

    REALNAME(11, "realName", "真实姓名");

    private final int code;
    private final String name;
    private final String desc;

    EnumFetcherParams(int code, String name, String desc) {
        this.code = code;
        this.name = name;
        this.desc = desc;
    }

    public static EnumFetcherParams getEnumByCode(int code) {
        for (EnumFetcherParams myEnum : EnumSet.allOf(EnumFetcherParams.class)) {
            if (myEnum.getCode() == code) {
                return myEnum;
            }
        }
        return null;
    }

    public static EnumFetcherParams getEnumByName(String name) {
        for (EnumFetcherParams myEnum : EnumSet.allOf(EnumFetcherParams.class)) {
            if (myEnum.getName().equals(name)) {
                return myEnum;
            }
        }
        return null;
    }

    public String getDesc() {
        return desc;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

}
