package com.second.credit.core.model;

/**
 * @note token 节点内容
 * @author wangmeng
 * @date 2015年6月14日 上午12:44:33
 */
public class TokenData extends BaseModel {

    private static final long serialVersionUID = 20150614003330000L;

    private Long userId; // 用户id
    private Long time; // token时间

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}
