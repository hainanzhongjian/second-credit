package com.second.credit.core.model;

/**
 * @百度地址解析-经纬度实体
 * @author wangmeng
 * @time 2015年5月4日下午10:21:24
 */
public class Geolocation {

    private String lat; // 纬度坐标
    private String lng; // 经度坐标

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }
}
