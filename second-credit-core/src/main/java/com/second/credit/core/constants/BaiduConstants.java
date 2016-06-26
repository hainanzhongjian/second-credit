package com.second.credit.core.constants;

import java.io.InputStream;
import java.net.URLDecoder;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @百度常量定义
 * @author wangmeng
 * @time 2015年5月4日下午9:46:17
 */
public class BaiduConstants {

    private static Logger logger = LoggerFactory.getLogger(BaiduConstants.class);

    private final static Properties prop = new Properties();

    static {
        try {
            // 从classpath加载baidu.properties文件
            InputStream in = BaiduConstants.class.getClassLoader().getResourceAsStream(
                    URLDecoder.decode("properties/baidu.properties", "UTF-8"));
            prop.load(in);
            in.close();

        } catch (Exception e) {
            logger.error("加载百度常量定义配置资源文件失败!{}", e.getMessage());
        }
    }

    // 地址解析——url
    public static final String BAIDU_GEOCODING_URL = prop.getProperty("baidu_geocoding_url");
    // 地址解析——秘钥
    public static final String BAIDU_GEOCODING_AK = prop.getProperty("baidu_geocoding_ak");
    // 地址解析——返回类型
    public static final String BAIDU_GEOCODING_OUTPUT = prop.getProperty("baidu_geocoding_output");
    // 地址解析——百度经纬度坐标
    public static final String BAIDU_GEOCODING_COORDTYPE = prop.getProperty("baidu_geocoding_coordtype");
    // 地址解析——是否显示指定位置周边的poi，0为不显示，1为显示。当值为1时，显示周边100米内的poi。
    public static final String BAIDU_GEOCODING_POIS = prop.getProperty("baidu_geocoding_pois");

    // 转换经纬度坐标——url
    public static final String BAIDU_GEOCONV_URL = prop.getProperty("baidu_geoconv_url");
    // 转换经纬度坐标——秘钥
    public static final String BAIDU_GEOCONV_AK = prop.getProperty("baidu_geoconv_ak");
    // 转换经纬度坐标——源坐标类型
    public static final String BAIDU_GEOCONV_FROM = prop.getProperty("baidu_geoconv_from");
    // 转换经纬度坐标——目的坐标类型
    public static final String BAIDU_GEOCONV_TO = prop.getProperty("baidu_geoconv_to");
    // 转换经纬度坐标——返回类型
    public static final String BAIDU_GEOCONV_OUTPUT = prop.getProperty("baidu_geoconv_output");
}
