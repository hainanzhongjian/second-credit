package com.second.credit.core.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class DoubleUtils {

    private static int SCALE_2 = 2;
    private static int SCALE_6 = 6;
    private static int SCALE_16 = 16;
    private static NumberFormat nf = new DecimalFormat("0000");

    /**
     * @note 四舍五入(保留scaleNum位小数)
     * @param decimal
     * @param scaleNum
     * @return
     * @author wangmeng
     * @date 2015年8月9日下午4:17:46
     */
    public static double scale(BigDecimal decimal, int scaleNum) {
        return decimal.setScale(scaleNum, RoundingMode.HALF_UP).doubleValue();
    }

    /**
     * @note double 类型的数据相加(多个)
     * @param dd
     * @return
     * @author wangmeng
     * @date 2015年8月9日下午4:14:56
     */
    public static double add(double... dd) {
        BigDecimal result = BigDecimal.ZERO;
        for (double n : dd) {
            result = result.add(new BigDecimal("" + n));
        }
        return result.doubleValue();
    }

    /**
     * @note double 类型的数据相加(小数个数：scaleNum 四舍五入)
     * @param scaleNum
     * @param dd
     * @return
     * @author wangmeng
     * @date 2015年8月9日下午4:22:42
     */
    public static double addScale(int scaleNum, double... dd) {
        BigDecimal result = BigDecimal.ZERO;
        for (double n : dd) {
            result = result.add(new BigDecimal("" + n));
        }
        return scale(result, scaleNum);
    }

    /**
     * @note double 类型的数据相减
     * @param d1
     * @param d2
     * @return
     * @author wangmeng
     * @date 2015年8月9日下午4:24:41
     */
    public static double subtract(double d1, double d2) {
        return new BigDecimal("" + d1).subtract(new BigDecimal("" + d2)).doubleValue();
    }

    /**
     * @note double 类型的数据相减(小数个数：scaleNum 四舍五入)
     * @param d1
     * @param d2
     * @return
     * @author wangmeng
     * @date 2015年8月9日下午4:24:41
     */
    public static double subtractScale(int scaleNum, double d1, double d2) {
        return scale(new BigDecimal("" + d1).subtract(new BigDecimal("" + d2)), scaleNum);
    }

    /**
     * @note double 类型的数据相除
     * @param d1
     * @param d2
     * @return
     * @author wangmeng
     * @date 2015年8月9日下午4:31:25
     */
    public static double divide(double d1, double d2) {
        return new BigDecimal(d1).divide(new BigDecimal(d2), SCALE_16, RoundingMode.HALF_UP).doubleValue();
    }

    /**
     * @note 类型的数据相除(小数个数：scaleNum 四舍五入)
     * @param d1
     * @param d2
     * @param scaleNum
     * @return
     * @author wangmeng
     * @date 2015年8月9日下午4:39:49
     */
    public static double divideScale(double d1, double d2, int scaleNum) {
        return scale(new BigDecimal(d1).divide(new BigDecimal(d2), SCALE_16, RoundingMode.HALF_UP), scaleNum);
    }

    /**
     * @note double 类型的数据相乘
     * @param d1
     * @param d2
     * @return
     * @author wangmeng
     * @date 2015年8月9日下午4:39:31
     */
    public static double multiply(double d1, double d2) {
        return new BigDecimal(Double.toString(d1)).multiply(new BigDecimal(Double.toString(d2))).doubleValue();
    }

    /**
     * @note double 类型的数据相乘(多个)
     * @param dd
     * @return
     * @author wangmeng
     * @date 2015年8月9日下午4:48:05
     */
    public static double multiply(double... dd) {
        BigDecimal result = BigDecimal.ONE;
        for (double n : dd) {
            result = result.multiply(new BigDecimal(n));
        }
        return result.doubleValue();
    }

    /**
     * @note double 类型的数据相乘(小数个数：scaleNum 四舍五入)
     * @param d1
     * @param d2
     * @param scaleNum
     * @return
     * @author wangmeng
     * @date 2015年8月9日下午4:42:06
     */
    public static double multiplyScale(double d1, double d2, int scaleNum) {
        return scale(new BigDecimal(Double.toString(d1)).multiply(new BigDecimal(Double.toString(d2))), scaleNum);
    }

    /**
     * @note double 类型的数据相乘(小数个数：scaleNum 四舍五入)(多个)
     * @param scaleNum
     * @param dd
     * @return
     * @author wangmeng
     * @date 2015年8月9日下午4:50:02
     */
    public static double multiplyScale(int scaleNum, double... dd) {
        BigDecimal result = BigDecimal.ONE;
        for (double n : dd) {
            result = result.multiply(new BigDecimal(n));
        }
        return scale(result, scaleNum);
    }

    /**
     * @note 验证value 是否在{maxValue,minValue}区间
     * @param maxValue
     * @param minValue
     * @param value
     * @return
     * @author wangmeng
     * @date 2015年8月9日下午5:49:22
     */
    public static boolean validDoubleValue(double maxValue, double minValue, double value) {
        if (DoubleUtils.subtract(maxValue, value) < 0 || DoubleUtils.subtract(minValue, value) > 0) {
            return false;
        }
        return true;
    }

}
