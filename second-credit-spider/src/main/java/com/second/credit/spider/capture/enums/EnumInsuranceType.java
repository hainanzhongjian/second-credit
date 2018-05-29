package com.second.credit.spider.capture.enums;

import java.util.EnumSet;

/**
 * @note 保险类型
 * @author wangmeng
 * @date 2017年3月24日 上午11:56:51
 */
public enum EnumInsuranceType {

    OLDAGE(1, "oldage", "养老保险"),

    UNEMPLOYMENT(2, "unemployment", "失业保险"),

    INJURIES(3, "injuries", "工伤保险"),

    MATERNITY(4, "maternity", "生育保险"),

    MEDICALCARE(5, "medicalcare", "医疗保险");

    private final int code;
    private final String name;
    private final String desc;

    EnumInsuranceType(int code, String name, String desc) {
        this.code = code;
        this.name = name;
        this.desc = desc;
    }

    public static EnumInsuranceType getEnumByCode(int code) {
        for (EnumInsuranceType myEnum : EnumSet.allOf(EnumInsuranceType.class)) {
            if (myEnum.getCode() == code) {
                return myEnum;
            }
        }
        return null;
    }

    public static EnumInsuranceType getEnumByName(String name) {
        for (EnumInsuranceType myEnum : EnumSet.allOf(EnumInsuranceType.class)) {
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
