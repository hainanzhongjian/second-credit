package com.second.credit.core.model;

/**
 * @note comm 节点内容
 * @author wangmeng
 * @date 2015年6月14日 上午12:44:58
 */
public class CommData extends BaseModel {

    private static final long serialVersionUID = 20150614003330000L;

    private String source; // 来源

    private String ip; // ip地址

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
