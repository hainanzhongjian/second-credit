package com.second.credit.core.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

/**
 * @author quan
 */
public class NumberUtils {
    private static int SCALE = 16;
    public static final int SCALE_2 = 2;
    public static final int SCALE_6 = 6;
    private static NumberFormat nf = new DecimalFormat("0000");

    /**
     * 给定总数，返回页数
     * 
     * @param total
     * @param perPage
     * @return
     */
    public static int getPageCount(int total, int perPage) {
        return (int) Math.ceil((double) total / perPage);
    }

    /**
     * double 相加
     * 
     * @param dd
     * @return
     */
    public static double doubleAdd(double... dd) {
        BigDecimal result = BigDecimal.ZERO;
        for (double n : dd) {
            result = result.add(new BigDecimal("" + n));
        }

        return result.doubleValue();
    }

    public static double doubleAdd(List<Double> dd) {
        BigDecimal result = BigDecimal.ZERO;
        for (double n : dd) {
            result = result.add(new BigDecimal("" + n));
        }

        return result.doubleValue();
    }

    /**
     * double 相加 - 四舍五入
     * 
     * @param dd
     * @return
     */
    public static double doubleAddScaled(double... dd) {
        BigDecimal result = BigDecimal.ZERO;
        for (double n : dd) {
            result = result.add(new BigDecimal("" + n));
        }

        return scale(result);
    }

    /**
     * double 相减
     * 
     * @param d1
     * @param d2
     * @return
     */
    public static double doubleSubtract(double d1, double d2) {
        return new BigDecimal("" + d1).subtract(new BigDecimal("" + d2)).doubleValue();
    }

    /**
     * double 相减 - 四舍五入
     * 
     * @param d1
     * @param d2
     * @return
     */
    public static double doubleSubtractScaled(double d1, double d2) {
        return scale(new BigDecimal("" + d1).subtract(new BigDecimal("" + d2)));
    }

    /**
     * 四舍五入 - RoundingMode.HALF_UP
     * 
     * @param decimal
     * @return
     */
    public static double scale(BigDecimal decimal) {
        return decimal.setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    /**
     * 四舍五入 - RoundingMode.HALF_UP
     * 
     * @author: yinjunlu
     * @Date: 2015年3月28日
     * @param value
     *            double
     * @return
     */
    public static double doubleScale(double value) {
        return doubleScale(value, SCALE_2);
    }

    /**
     * 四舍五入 - RoundingMode.HALF_UP
     * 
     * @author: yinjunlu
     * @Date: 2015年3月28日
     * @param value
     *            double
     * @return
     */
    public static double doubleScale(double value, int scale) {
        return BigDecimal.valueOf(value).setScale(scale, RoundingMode.HALF_UP).doubleValue();
    }

    /**
     * 货币形式格式字符串
     * 
     * @param ####,###0.00
     * @param value
     */
    public static String formatNumberString(String format, String value) {
        BigDecimal bd = new BigDecimal(value);
        DecimalFormat df = new DecimalFormat(format);
        return df.format(bd);
    }

    /**
     * 相除 取位 d1/d2 - 四舍五入
     * 
     * @param d1
     * @param d2
     * @param scale
     * @return
     */
    public static double doubleDivide(double d1, double d2, int scale) {
        BigDecimal bd1 = BigDecimal.valueOf(d1);
        BigDecimal bd2 = BigDecimal.valueOf(d2);
        BigDecimal bd3 = bd1.divide(bd2, 16, RoundingMode.HALF_UP);
        return bd3.setScale(scale, RoundingMode.HALF_UP).doubleValue();
    }

    public static double doubleDivide(double d1, double d2) {
        return doubleDivide(d1, d2, SCALE);
    }

    public static double doubleDivideDown(double d1, double d2, int scale) {
        BigDecimal bd1 = BigDecimal.valueOf(d1);
        BigDecimal bd2 = BigDecimal.valueOf(d2);
        BigDecimal bd3 = bd1.divide(bd2, 16, RoundingMode.HALF_UP);
        return bd3.setScale(scale, RoundingMode.DOWN).doubleValue();
    }

    /**
     * 相乘 取位 d1*d2 - 四舍五入
     * 
     * @param d1
     * @param d2
     * @param scale
     * @return
     */
    public static double doubleMultiply(double d1, double d2, int scale) {
        BigDecimal bd1 = new BigDecimal(Double.toString(d1));
        BigDecimal bd2 = new BigDecimal(Double.toString(d2));
        return bd1.multiply(bd2).setScale(scale, RoundingMode.HALF_UP).doubleValue();
    }

    public static double doubleMultiply(double d1, double d2) {
        return doubleMultiply(d1, d2, SCALE);
    }

    /**
     * 相乘 取位 d1*d2 - 四舍五入
     * 
     * @param scale
     * @param dd
     * @return
     */
    public static double doubleMultiplyScale(int scale, double... dd) {
        BigDecimal result = BigDecimal.ONE;
        for (double n : dd) {
            result = result.multiply(new BigDecimal("" + n)).setScale(16, RoundingMode.HALF_UP);
        }
        return result.setScale(scale, RoundingMode.HALF_UP).doubleValue();
    }

    /**
     * 将整数转化为四位数字
     * 
     * @param number
     * @return
     */
    public static String formatFourNumber(Integer number) {
        return nf.format(number);
    }

    /**
     * 获取带千分符并保留两位小数的金额
     * 
     * @author zhoue
     * @param amount
     * @return
     */
    public static String formatNumber(double amount) {
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        nf.setMaximumFractionDigits(6);
        return nf.format(amount).substring(1);
    }

    /***
     * 取模
     * 
     * @author radish
     * @param d1
     * @param d2
     * @return d1%d2
     */
    public static double doubleMod(double d1, double d2) {
        BigDecimal bd1 = new BigDecimal(Double.toString(d1));
        BigDecimal bd2 = new BigDecimal(Double.toString(d2));
        return bd1.remainder(bd2).doubleValue();
    }

    /**
     * 四舍五入金额
     * 
     * @param amount
     * @return
     */
    public static Double formatDouble2Scale(Double amount) {
        return scale(new BigDecimal(amount));
    }

    /**
     * total减去dd的和
     * 
     * @param total
     * @param dd
     * @return
     * @author liujinjie
     */
    public static double doubleSubtractMore(double total, double... dd) {
        double add = 0.0d;
        for (double n : dd) {
            add = doubleAdd(add, n);
        }
        return doubleSubtract(total, add);
    }

    public static void main(String args[]) {
        System.out.println("10-5=" + doubleSubtract(10, 5));
        // System.out.println("1011.25%50=" + doubleMod(1011.25, 50));
        // System.out.println("1011.75%50=" + doubleMod(1011.75, 50));
        // System.out.println("-1011.25%50=" + doubleMod(-1011.25, 50));
        // System.out.println("-1011.75%50=" + doubleMod(-1011.75, 50));
        // System.out.println("0/100=" + doubleDivide(0, 100));
        // // System.out.println("100/0=" + doubleDivide(100,0));
        // System.out.println("1011.25/50=" + doubleDivide(1011.25, 50, 0));
        // System.out.println("1011.75/50=" + doubleDivide(1011.75, 50, 0));
        // System.out.println("-1011.25/50=" + doubleDivide(-1011.25, 50, 0));
        // System.out.println("-1011.75/50=" + doubleDivide(-1011.75, 50, 0));
        // System.out.println("3243.234/23.23=" + doubleDivide(3243.234,
        // 23.23));
        // System.out.println("-3243.234/23.23=" + doubleDivide(-3243.234,
        // 23.23));
        // System.out.println("3243.234/23.23=" + doubleDivideDown(3243.234,
        // 23.23, 0));
        // System.out.println("-3243.234/23.23=" + doubleDivideDown(-3243.234,
        // 23.23, 0));
    }
}
