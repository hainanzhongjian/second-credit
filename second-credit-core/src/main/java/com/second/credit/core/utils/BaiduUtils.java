package com.second.credit.core.utils;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.second.credit.core.constants.BaiduConstants;
import com.second.credit.core.model.GeoAddressComponent;
import com.second.credit.core.model.Geocoding;
import com.second.credit.core.model.Geolocation;

public class BaiduUtils {

    private static Logger logger = Logger.getLogger(BaiduUtils.class);

    /**
     * @百度地址解析（逆地理编码服务）使用百度经纬度
     * @param lnylat
     *            (百度经纬度) lny:维度值 lat：经度值 顺序：维度值/经度值
     * @param logger
     * @return
     * @author wangmeng
     * @time 2015年5月4日下午10:18:04
     */
    public static Geocoding parseLnyLat(String lnylat) {
        logger.info("百度地址解析接口：经维度=" + lnylat + "——解析地址start");
        Geocoding geocoding = null;
        if (StringUtils.isEmpty(lnylat)) {
            return geocoding;
        }
        HttpClientUtils hc = new HttpClientUtils(BaiduConstants.BAIDU_GEOCODING_URL);
        hc.setParams("ak", BaiduConstants.BAIDU_GEOCODING_AK);
        hc.setParams("output", BaiduConstants.BAIDU_GEOCODING_OUTPUT);
        hc.setParams("coordtype", BaiduConstants.BAIDU_GEOCODING_COORDTYPE);
        hc.setParams("pois", BaiduConstants.BAIDU_GEOCODING_POIS);
        hc.setParams("location", lnylat);
        String resJSON = hc.doPost();
        logger.info("百度地址解析接口：经维度=" + lnylat + "——解析地址的返回信息" + resJSON);
        if (!JsonUtils.parseObjectTrue(resJSON)) {// json解析不正确
            logger.info("百度地址解析接口：经维度=" + lnylat + "——解析地址JOSN解析错误");
            logger.info("百度地址解析接口：经维度=" + lnylat + "——解析地址end");
            return geocoding;
        }
        String retCode = JSON.parseObject(resJSON).getString("status");
        if (!StringUtils.isEmpty(retCode) && "0".equals(retCode)) {// retCode
                                                                   // ==0时 数据正常
            String result = JSON.parseObject(resJSON).getString("result");
            geocoding = JSONObject.parseObject(result, Geocoding.class);
            geocoding.setLocationObj(JSONObject.parseObject(JSON.parseObject(result).getString("location"),
                    Geolocation.class));
            geocoding.setAddressComponentObj(JSONObject.parseObject(
                    JSON.parseObject(result).getString("addressComponent"), GeoAddressComponent.class));
        }
        logger.info("百度地址解析接口：经维度=" + lnylat + "——解析地址end");
        return geocoding;
    }

    /**
     * @转换经纬度坐标（ios使用高德地图 转换为百度经纬度）
     * @param lny
     *            xy的值 经度值/维度值 返回的是维度/经度
     * @param lat
     * @param type
     * @param logger
     * @return
     * @author wangmeng
     * @time 2015年5月5日下午9:13:01
     */
    public static String convertLnyLat(String xy, Logger logger) {
        logger.info("百度转换经纬度坐标接口：经维度=" + xy + "——转换经纬度坐标start");
        String lnylat = null;
        if (StringUtils.isEmpty(xy)) {
            return lnylat;
        }
        HttpClientUtils hc = new HttpClientUtils(BaiduConstants.BAIDU_GEOCONV_URL);
        hc.setParams("ak", BaiduConstants.BAIDU_GEOCONV_AK);
        hc.setParams("output", BaiduConstants.BAIDU_GEOCONV_OUTPUT);
        hc.setParams("from", BaiduConstants.BAIDU_GEOCONV_FROM);
        hc.setParams("to", BaiduConstants.BAIDU_GEOCONV_TO);
        hc.setParams("coords", xy);
        String resJSON = hc.doPost();
        logger.info("百度转换经纬度坐标接口：经维度=" + xy + "——转换经纬度坐标返回信息" + resJSON);
        if (!JsonUtils.parseObjectTrue(resJSON)) {// json解析不正确
            logger.info("百度转换经纬度坐标接口：经维度=" + xy + "——转换经纬度坐标JOSN解析错误");
            logger.info("百度转换经纬度坐标接口：经维度=" + xy + "——转换经纬度坐标end");
            return lnylat;
        }
        String retCode = JSON.parseObject(resJSON).getString("status");
        if (!StringUtils.isEmpty(retCode) && "0".equals(retCode)) {// retCode
                                                                   // ==0时 数据正常
            String result = JSON.parseObject(resJSON).getString("result");
            // List<LnyLat> list = JSON.parseArray(result, LnyLat.class);//
            // 只有一条数据
            // lnylat = list.get(0).getY() + "," + list.get(0).getX();// 经度维度
        }
        logger.info("百度转换经纬度坐标接口：经维度=" + xy + "——转换经纬度坐标end");
        return lnylat;
    }
}
