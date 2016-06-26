package com.second.credit.core.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wangmeng
 * @note 行政区
 * @date 2015年8月1日下午4:44:19
 */
public class Cdistrict implements Serializable {

    private static final long serialVersionUID = 1l;

    private Long id;

    /**
     * 编码
     */
    private String code;

    /**
     * 省，市，县，乡镇，村
     */
    private String name;

    /**
     * 左标记
     */
    private Long left;

    /**
     * 右标记
     */
    private Long right;

    /**
     * 层级
     */
    private Integer layer;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getLeft() {
        return left;
    }

    public void setLeft(Long left) {
        this.left = left;
    }

    public Long getRight() {
        return right;
    }

    public void setRight(Long right) {
        this.right = right;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getLayer() {
        return layer;
    }

    public void setLayer(Integer layer) {
        this.layer = layer;
    }
}