package com.second.credit.core.model;

/**
 * @note 公用参数
 * @author wangmeng
 * @date 2015年6月14日 上午12:23:17
 */
public class CommParams<T> extends BaseModel {

    private static final long serialVersionUID = 20150614003330000L;

    private String comm; // comm节点内容

    private CommData commData; // comm节点解析之后的内容

    private String token; // token节点内容

    private TokenData tokenData; // token解析之后节点内容

    private T body; // body解析之后节点内容

    private Integer loginStatus; // 登录状态

    public String getComm() {
        return comm;
    }

    public void setComm(String comm) {
        this.comm = comm;
    }

    public CommData getCommData() {
        return commData;
    }

    public void setCommData(CommData commData) {
        this.commData = commData;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public TokenData getTokenData() {
        return tokenData;
    }

    public void setTokenData(TokenData tokenData) {
        this.tokenData = tokenData;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public Integer getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(Integer loginStatus) {
        this.loginStatus = loginStatus;
    }

}
