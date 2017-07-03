package com.second.credit.core.model.baobao;

import java.io.Serializable;

public class StaffShow implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * @note 工号
     */
    private String jobNumber;

    /**
     * @note 姓名
     */
    private String realName;

    /**
     * @note 部门
     */
    private String branch;

    private String date;

    /**
     * @note 上班时间
     */
    private String startTime;

    /**
     * @note 下班时间
     */
    private String endTime;

    /**
     * @note 是否迟到
     */
    private boolean boo;

    /**
     * @note 迟到原因
     */
    private String reason;

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public boolean isBoo() {
        return boo;
    }

    public void setBoo(boolean boo) {
        this.boo = boo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
