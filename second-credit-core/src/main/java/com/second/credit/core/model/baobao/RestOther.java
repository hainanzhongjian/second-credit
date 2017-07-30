package com.second.credit.core.model.baobao;

import java.util.List;
import java.util.Map;

/**
 * @note 例外元素
 * @author wangmeng
 * @date 2017年7月12日 下午8:39:02
 */
public class RestOther {

    /**
     * @note 姓名
     */
    private String realName;

    /**
     * @note 休息日
     */
    private List<String> weekRest;

    /**
     * @note 上班时间
     */
    private Map<String, String> timeRest;

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public List<String> getWeekRest() {
        return weekRest;
    }

    public void setWeekRest(List<String> weekRest) {
        this.weekRest = weekRest;
    }

    public Map<String, String> getTimeRest() {
        return timeRest;
    }

    public void setTimeRest(Map<String, String> timeRest) {
        this.timeRest = timeRest;
    }

}
