package com.second.credit.core.utils;

import java.util.ArrayList;
import java.util.List;

import com.second.credit.core.model.LonLat;

/**
 * @note 圆算法
 * @author wangmeng
 * @date 2015年8月2日下午5:48:07
 */
public class CircleUtils {

    /*
     * @note 中国经度范围 维度：3.52 到 53.33 经度：73.40 到 135.02
     * 
     * @note 坐标原点 西安 108.856778,34.36275
     */

    private static double initRadius = 0.1; // 初始化半径
    private static double initLon = 108.856778; // 初始化经度
    private static double initLat = 34.36275; // 初始化维度
    private static double MaxLon = 135.02; // 最大经度
    private static double MinLon = 73.40; // 最小经度
    private static double MaxLat = 53.33; // 最大经度
    private static double MinLat = 3.52; // 最小经度
    private static double MaxAngle = 360; // 最大角度
    private static double initAngle = 36; // 初始化角度
    private static double initMultiple = 1; // 半径倍数

    /**
     * @note 获取一个半径圆的经纬度列表信息
     * @param multiple
     * @return
     * @author wangmeng
     * @date 2015年8月16日 下午1:44:11
     */
    public static List<LonLat> getOneCircleLonLatList(double multiple) {
        // 1:初始化数据
        List<LonLat> llList = new ArrayList<LonLat>();
        double angle = DoubleUtils.divideScale(initAngle, multiple, 2); // 角度大小
        long counts = Math.round(DoubleUtils.divideScale(MaxAngle, angle, 2)); // 最大循环次数
        // 2:获取经纬度信息
        for (long i = 0; i < counts; i++) {
            // 2.1:每次角度信息
            double perAngle = DoubleUtils.multiply(angle, multiple);
            // 2.2:每次经纬度信息
            LonLat ll = getOneCircleLonLat(perAngle, multiple);
            if (ll == null) {
                continue;
            }
            System.out.println("第" + i * multiple + "次定位:角度=" + perAngle + ",精度=" + ll.getLon() + ",纬度=" + ll.getLat());
            // 2.3:每次经纬度信息是否在中国经纬度范围内
            if (!validChinaLonLat(ll.getLon(), ll.getLat())) {
                continue;
            }
            llList.add(ll);
        }
        return llList;
    }

    /**
     * @note 获取一个半径圆某一个点的经纬度信息
     * @param angle
     *            (角度：从竖轴开始顺时针计算 数值最大为360度)
     * @param multiple
     *            (倍数)
     * @return
     * @author wangmeng
     * @date 2015年8月9日下午5:35:47
     */
    private static LonLat getOneCircleLonLat(double angle, double multiple) {
        // 0:初始化数据
        LonLat ll = null;
        double lon = 0;
        double lat = 0;
        double radius = DoubleUtils.multiplyScale(initRadius, multiple, 2); // 半径大小
        // 1:验证角度是否在0到360度之间
        if (!DoubleUtils.validDoubleValue(360, 0, angle) || multiple < 0) {
            return ll;
        }
        // 2:处理经度信息
        // y=cos(angle)*斜边长(multiple*initRadius:初始半径*半径倍数)
        double y = DoubleUtils.multiply(Math.cos(angle), radius);
        if (DoubleUtils.validDoubleValue(90, 0, angle) || DoubleUtils.validDoubleValue(360, 270, angle)) {
            lon = DoubleUtils.add(initLon, y);
        } else {
            lon = DoubleUtils.subtract(initLon, y);
        }
        // 3:处理纬度信息
        // x=sin(angle)*斜边长(multiple*initRadius:初始半径*半径倍数)
        double x = DoubleUtils.multiply(Math.sin(angle), radius);
        if (DoubleUtils.validDoubleValue(180, 0, angle)) {
            lat = DoubleUtils.add(initLat, x);
        } else {
            lat = DoubleUtils.subtract(initLat, x);
        }
        // 4:封装经纬度信息
        ll = new LonLat();
        ll.setLon(lon);
        ll.setLat(lat);
        return ll;
    }

    /**
     * @note 检验经纬度是否在中国范围内
     * @param lon
     * @param lat
     * @return
     * @author wangmeng
     * @date 2015年8月9日下午5:27:26
     */
    private static boolean validChinaLonLat(double lon, double lat) {
        if (!DoubleUtils.validDoubleValue(MaxLon, MinLon, lon)) {
            return false;
        }
        if (!DoubleUtils.validDoubleValue(MaxLat, MinLat, lat)) {
            return false;
        }
        return true;
    }
}
