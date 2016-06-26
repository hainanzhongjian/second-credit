package com.second.credit.core.model;

/**
 * @百度地址解析实体
 * @author wangmeng
 * @time 2015年5月4日下午10:09:56
 */
public class Geocoding {

    private String status; // 结果状态
    private String location; // 经纬度
    private Geolocation locationObj; // 经纬度对象
    private String formatted_address; // 结构化地址信息
    private String business; // 所在商圈信息
    private String addressComponent; // 具体信息
    private GeoAddressComponent addressComponentObj; // 具体信息对象
    private String pois; // （周边poi数组）

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getFormatted_address() {
        return formatted_address;
    }

    public void setFormatted_address(String formatted_address) {
        this.formatted_address = formatted_address;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public String getAddressComponent() {
        return addressComponent;
    }

    public void setAddressComponent(String addressComponent) {
        this.addressComponent = addressComponent;
    }

    public String getPois() {
        return pois;
    }

    public void setPois(String pois) {
        this.pois = pois;
    }

    public Geolocation getLocationObj() {
        return locationObj;
    }

    public void setLocationObj(Geolocation locationObj) {
        this.locationObj = locationObj;
    }

    public GeoAddressComponent getAddressComponentObj() {
        return addressComponentObj;
    }

    public void setAddressComponentObj(GeoAddressComponent addressComponentObj) {
        this.addressComponentObj = addressComponentObj;
    }
}
