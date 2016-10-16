package com.second.credit.api.base;

/**
 * result code，message错误码
 */
public class ResultCodeConstants {
    /**
     * 定义规范 code定义规范：以CODE开头，字段之间以_隔开。code 目前只可定义正整数，不可定义负数 且code值不可重复。
     * code目前分为参数，银行卡，用户，标，标的申请 等几个大类，定义时需要在大类下定义，未找到相应的大类，需要新添加大类。
     * code常量修饰词：public static final int message定义规范：以MSG开头，字段之间以_隔开。
     * message常量修饰词：public static final String
     */

    /*
     * ---------------------返回code定义----------------------------------
     */

    /**
     * code:1表示成功
     */
    public static final int CODE_SUCCESS = 1;
    /**
     * code:-1表示失败
     */
    public static final int CODE_FAILED = -1;
    /*
     * ---------------------------返回message定义----------------------------------
     */

    public static final String MSG_SUCCESS = "操作成功";

    public static final String MSG_FAILED = "操作失败";

}
