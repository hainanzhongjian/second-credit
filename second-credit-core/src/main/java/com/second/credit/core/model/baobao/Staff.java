package com.second.credit.core.model.baobao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @note 员工
 * @author wangmeng
 * @date 2017年6月29日 下午7:53:00
 */
public class Staff implements Serializable {

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

    /**
     * @note 考勤
     */
    private Map<String, List<Integer>> attendance;

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

    public Map<String, List<Integer>> getAttendance() {
        return attendance;
    }

    public void setAttendance(Map<String, List<Integer>> attendance) {
        this.attendance = attendance;
    }

}
