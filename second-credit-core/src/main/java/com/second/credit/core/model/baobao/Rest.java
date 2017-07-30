package com.second.credit.core.model.baobao;

import java.util.List;

public class Rest {

    /**
     * @note 部门
     */
    private String branch;

    /**
     * @note 上班时间
     */
    private String startTime;
    /**
     * @note 下班时间
     */
    private String endTime;

    /**
     * @note 1:双休 2：单双轮休 3：单休 4：不休
     */
    private int rest;

    /**
     * @note 例外元素
     */
    private List<RestOther> otherList;

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

    public int getRest() {
        return rest;
    }

    public void setRest(int rest) {
        this.rest = rest;
    }

    public List<RestOther> getOtherList() {
        return otherList;
    }

    public void setOtherList(List<RestOther> otherList) {
        this.otherList = otherList;
    }

}
