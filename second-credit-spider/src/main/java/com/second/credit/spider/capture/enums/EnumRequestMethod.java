package com.second.credit.spider.capture.enums;

import java.util.EnumSet;

/**
 * @note 请求方法
 * @author wangmeng
 * @date 2017年4月26日 下午3:31:21
 */
public enum EnumRequestMethod {

    LOGININFO(1, "loginInfo", "登录信息接口"),

    LOADCAPTCHA(2, "loadCaptcha", "加载验证码接口"),

    LOGIN(3, "login", "登录接口");

    private final int code;
    private final String name;
    private final String desc;

    EnumRequestMethod(int code, String name, String desc) {
        this.code = code;
        this.name = name;
        this.desc = desc;
    }

    public static EnumRequestMethod getEnumByCode(int code) {
        for (EnumRequestMethod myEnum : EnumSet.allOf(EnumRequestMethod.class)) {
            if (myEnum.getCode() == code) {
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
