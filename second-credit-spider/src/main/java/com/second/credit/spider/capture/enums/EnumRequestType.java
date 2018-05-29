package com.second.credit.spider.capture.enums;

import java.util.EnumSet;

/**
 * @note 请求类型
 * @author wangmeng
 * @date 2017年4月26日 下午3:31:21
 */
public enum EnumRequestType {

    POST(1, "post", "post请求"),

    GET(2, "get", "get请求"),

    HEAD(3, "head", "head请求");

    private final int code;
    private final String name;
    private final String desc;

    EnumRequestType(int code, String name, String desc) {
        this.code = code;
        this.name = name;
        this.desc = desc;
    }

    public static EnumRequestType getEnumByCode(int code) {
        for (EnumRequestType myEnum : EnumSet.allOf(EnumRequestType.class)) {
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
