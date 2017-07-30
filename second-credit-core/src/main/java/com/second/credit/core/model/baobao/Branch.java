package com.second.credit.core.model.baobao;

import java.io.Serializable;

/**
 * @note 部门
 * @author wangmeng
 * @date 2017年7月31日 上午12:12:19
 */
public class Branch implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * @note 部门名称
     * @author wangmeng
     * @date 2017年7月31日 上午12:15:42
     */
    public String branchName;

    /**
     * @note 1，2，3，4，5，6，7分别代表周一到周日
     * @author wangmeng
     * @date 2017年7月31日 上午12:15:56
     */
    public int week;

    /**
     * @note 是否休息 默认true
     * @author wangmeng
     * @date 2017年7月31日 上午12:16:24
     */
    public boolean sleepTrue;

    /**
     * @note 上班时间
     * @author wangmeng
     * @date 2017年7月31日 上午12:16:49
     */
    public String upTime;

    /**
     * @note 下班时间
     * @author wangmeng
     * @date 2017年7月31日 上午12:16:58
     */
    public String downTime;

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public boolean isSleepTrue() {
        return sleepTrue;
    }

    public void setSleepTrue(boolean sleepTrue) {
        this.sleepTrue = sleepTrue;
    }

    public String getUpTime() {
        return upTime;
    }

    public void setUpTime(String upTime) {
        this.upTime = upTime;
    }

    public String getDownTime() {
        return downTime;
    }

    public void setDownTime(String downTime) {
        this.downTime = downTime;
    }
}
