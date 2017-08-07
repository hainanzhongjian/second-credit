package com.second.credit.api.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.second.credit.core.model.baobao.Branch;

public class BaoBaoUtils {

    private BaoBaoUtils() {
    }

    /**
     * @note 设置财务部门
     * @author wangmeng
     * @date 2017年8月6日 上午12:49:34
     */
    public static void setCaiwu(Map<String, Map<String, Branch>> branchMap) {
        try {
            // 财务
            Map<String, Branch> branchDayMap = new HashMap<>();
            // 设置周一
            Branch caiwu = new Branch();
            caiwu.setBranchName("caiwu");
            caiwu.setWeek(1);
            caiwu.setSleepTrue(false);
            caiwu.setUpTime("08:30");
            caiwu.setDownTime("17:30");
            branchDayMap.put("1", caiwu);

            // 设置周二
            Branch caiwu2 = new Branch();
            BeanUtils.copyProperties(caiwu2, caiwu);
            caiwu2.setWeek(2);
            branchDayMap.put("2", caiwu2);

            // 设置周三
            Branch caiwu3 = new Branch();
            BeanUtils.copyProperties(caiwu3, caiwu);
            caiwu3.setWeek(3);
            branchDayMap.put("3", caiwu3);

            // 设置周四
            Branch caiwu4 = new Branch();
            BeanUtils.copyProperties(caiwu4, caiwu);
            caiwu4.setWeek(4);
            branchDayMap.put("4", caiwu4);

            // 设置周五
            Branch caiwu5 = new Branch();
            BeanUtils.copyProperties(caiwu5, caiwu);
            caiwu5.setWeek(5);
            branchDayMap.put("5", caiwu5);

            // 设置周六
            Branch caiwu6 = new Branch();
            BeanUtils.copyProperties(caiwu6, caiwu);
            caiwu6.setWeek(6);
            caiwu6.setSleepTrue(true);
            branchDayMap.put("6", caiwu6);

            // 设置周日
            Branch caiwu7 = new Branch();
            BeanUtils.copyProperties(caiwu7, caiwu);
            caiwu7.setWeek(7);
            caiwu7.setSleepTrue(true);
            branchDayMap.put("7", caiwu7);

            branchMap.put("caiwu", branchDayMap);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * @note 设置市场部门
     * @author wangmeng
     * @date 2017年8月6日 上午12:49:34
     */
    public static void setShiChang(Map<String, Map<String, Branch>> branchMap) {
        try {
            Map<String, Branch> branchDayMap = new HashMap<>();
            // 设置周一
            Branch caiwu = new Branch();
            caiwu.setBranchName("shichang");
            caiwu.setWeek(1);
            caiwu.setSleepTrue(false);
            caiwu.setUpTime("08:30");
            caiwu.setDownTime("17:30");
            branchDayMap.put("1", caiwu);

            // 设置周二
            Branch caiwu2 = new Branch();
            BeanUtils.copyProperties(caiwu2, caiwu);
            caiwu2.setWeek(2);
            branchDayMap.put("2", caiwu2);

            // 设置周三
            Branch caiwu3 = new Branch();
            BeanUtils.copyProperties(caiwu3, caiwu);
            caiwu3.setWeek(3);
            branchDayMap.put("3", caiwu3);

            // 设置周四
            Branch caiwu4 = new Branch();
            BeanUtils.copyProperties(caiwu4, caiwu);
            caiwu4.setWeek(4);
            branchDayMap.put("4", caiwu4);

            // 设置周五
            Branch caiwu5 = new Branch();
            BeanUtils.copyProperties(caiwu5, caiwu);
            caiwu5.setWeek(5);
            branchDayMap.put("5", caiwu5);

            // 设置周六
            Branch caiwu6 = new Branch();
            BeanUtils.copyProperties(caiwu6, caiwu);
            caiwu6.setWeek(6);
            caiwu6.setSleepTrue(true);
            branchDayMap.put("6", caiwu6);

            // 设置周日
            Branch caiwu7 = new Branch();
            BeanUtils.copyProperties(caiwu7, caiwu);
            caiwu7.setWeek(7);
            caiwu7.setSleepTrue(true);
            branchDayMap.put("7", caiwu7);

            branchMap.put("shichang", branchDayMap);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * @note 设置网络部门
     * @author wangmeng
     * @date 2017年8月6日 上午12:49:34
     */
    public static void setWangLuo(Map<String, Map<String, Branch>> branchMap) {
        try {
            Map<String, Branch> branchDayMap = new HashMap<>();
            // 设置周一
            Branch caiwu = new Branch();
            caiwu.setBranchName("wangluo");
            caiwu.setWeek(1);
            caiwu.setSleepTrue(false);
            caiwu.setUpTime("08:30");
            caiwu.setDownTime("17:30");
            branchDayMap.put("1", caiwu);

            // 设置周二
            Branch caiwu2 = new Branch();
            BeanUtils.copyProperties(caiwu2, caiwu);
            caiwu2.setWeek(2);
            branchDayMap.put("2", caiwu2);

            // 设置周三
            Branch caiwu3 = new Branch();
            BeanUtils.copyProperties(caiwu3, caiwu);
            caiwu3.setWeek(3);
            branchDayMap.put("3", caiwu3);

            // 设置周四
            Branch caiwu4 = new Branch();
            BeanUtils.copyProperties(caiwu4, caiwu);
            caiwu4.setWeek(4);
            branchDayMap.put("4", caiwu4);

            // 设置周五
            Branch caiwu5 = new Branch();
            BeanUtils.copyProperties(caiwu5, caiwu);
            caiwu5.setWeek(5);
            branchDayMap.put("5", caiwu5);

            // 设置周六
            Branch caiwu6 = new Branch();
            BeanUtils.copyProperties(caiwu6, caiwu);
            caiwu6.setWeek(6);
            caiwu6.setSleepTrue(true);
            branchDayMap.put("6", caiwu6);

            // 设置周日
            Branch caiwu7 = new Branch();
            BeanUtils.copyProperties(caiwu7, caiwu);
            caiwu7.setWeek(7);
            caiwu7.setSleepTrue(true);
            branchDayMap.put("7", caiwu7);

            branchMap.put("wangluo", branchDayMap);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * @note 设置教务部门
     * @author wangmeng
     * @date 2017年8月6日 上午12:49:34
     */
    public static void setJiaoWu(Map<String, Map<String, Branch>> branchMap) {
        try {
            Map<String, Branch> branchDayMap = new HashMap<>();
            // 设置周一
            Branch caiwu = new Branch();
            caiwu.setBranchName("jiaowu");
            caiwu.setWeek(1);
            caiwu.setSleepTrue(false);
            caiwu.setUpTime("08:30");
            caiwu.setDownTime("17:30");
            branchDayMap.put("1", caiwu);

            // 设置周二
            Branch caiwu2 = new Branch();
            BeanUtils.copyProperties(caiwu2, caiwu);
            caiwu2.setWeek(2);
            branchDayMap.put("2", caiwu2);

            // 设置周三
            Branch caiwu3 = new Branch();
            BeanUtils.copyProperties(caiwu3, caiwu);
            caiwu3.setWeek(3);
            branchDayMap.put("3", caiwu3);

            // 设置周四
            Branch caiwu4 = new Branch();
            BeanUtils.copyProperties(caiwu4, caiwu);
            caiwu4.setWeek(4);
            branchDayMap.put("4", caiwu4);

            // 设置周五
            Branch caiwu5 = new Branch();
            BeanUtils.copyProperties(caiwu5, caiwu);
            caiwu5.setWeek(5);
            branchDayMap.put("5", caiwu5);

            // 设置周六
            Branch caiwu6 = new Branch();
            BeanUtils.copyProperties(caiwu6, caiwu);
            caiwu6.setWeek(6);
            caiwu6.setSleepTrue(true);
            branchDayMap.put("6", caiwu6);

            // 设置周日
            Branch caiwu7 = new Branch();
            BeanUtils.copyProperties(caiwu7, caiwu);
            caiwu7.setWeek(7);
            caiwu7.setSleepTrue(true);
            branchDayMap.put("7", caiwu7);

            branchMap.put("jiaowu", branchDayMap);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * @note 设置留学部门
     * @author wangmeng
     * @date 2017年8月6日 上午12:49:34
     */
    public static void setLiuxue(Map<String, Map<String, Branch>> branchMap) {
        try {
            Map<String, Branch> branchDayMap = new HashMap<>();
            // 设置周一
            Branch caiwu = new Branch();
            caiwu.setBranchName("liuxue");
            caiwu.setWeek(1);
            caiwu.setSleepTrue(false);
            caiwu.setUpTime("08:30");
            caiwu.setDownTime("17:30");
            branchDayMap.put("1", caiwu);

            // 设置周二
            Branch caiwu2 = new Branch();
            BeanUtils.copyProperties(caiwu2, caiwu);
            caiwu2.setWeek(2);
            branchDayMap.put("2", caiwu2);

            // 设置周三
            Branch caiwu3 = new Branch();
            BeanUtils.copyProperties(caiwu3, caiwu);
            caiwu3.setWeek(3);
            branchDayMap.put("3", caiwu3);

            // 设置周四
            Branch caiwu4 = new Branch();
            BeanUtils.copyProperties(caiwu4, caiwu);
            caiwu4.setWeek(4);
            branchDayMap.put("4", caiwu4);

            // 设置周五
            Branch caiwu5 = new Branch();
            BeanUtils.copyProperties(caiwu5, caiwu);
            caiwu5.setWeek(5);
            branchDayMap.put("5", caiwu5);

            // 设置周六
            Branch caiwu6 = new Branch();
            BeanUtils.copyProperties(caiwu6, caiwu);
            caiwu6.setWeek(6);
            caiwu6.setSleepTrue(true);
            branchDayMap.put("6", caiwu6);

            // 设置周日
            Branch caiwu7 = new Branch();
            BeanUtils.copyProperties(caiwu7, caiwu);
            caiwu7.setWeek(7);
            caiwu7.setSleepTrue(true);
            branchDayMap.put("7", caiwu7);

            branchMap.put("liuxue", branchDayMap);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * @note 设置人事部门
     * @author wangmeng
     * @date 2017年8月6日 上午12:49:34
     */
    public static void setRenShi(Map<String, Map<String, Branch>> branchMap) {
        try {
            Map<String, Branch> branchDayMap = new HashMap<>();
            // 设置周一
            Branch caiwu = new Branch();
            caiwu.setBranchName("renshi");
            caiwu.setWeek(1);
            caiwu.setSleepTrue(false);
            caiwu.setUpTime("08:30");
            caiwu.setDownTime("17:30");
            branchDayMap.put("1", caiwu);

            // 设置周二
            Branch caiwu2 = new Branch();
            BeanUtils.copyProperties(caiwu2, caiwu);
            caiwu2.setWeek(2);
            branchDayMap.put("2", caiwu2);

            // 设置周三
            Branch caiwu3 = new Branch();
            BeanUtils.copyProperties(caiwu3, caiwu);
            caiwu3.setWeek(3);
            branchDayMap.put("3", caiwu3);

            // 设置周四
            Branch caiwu4 = new Branch();
            BeanUtils.copyProperties(caiwu4, caiwu);
            caiwu4.setWeek(4);
            branchDayMap.put("4", caiwu4);

            // 设置周五
            Branch caiwu5 = new Branch();
            BeanUtils.copyProperties(caiwu5, caiwu);
            caiwu5.setWeek(5);
            branchDayMap.put("5", caiwu5);

            // 设置周六
            Branch caiwu6 = new Branch();
            BeanUtils.copyProperties(caiwu6, caiwu);
            caiwu6.setWeek(6);
            caiwu6.setSleepTrue(true);
            branchDayMap.put("6", caiwu6);

            // 设置周日
            Branch caiwu7 = new Branch();
            BeanUtils.copyProperties(caiwu7, caiwu);
            caiwu7.setWeek(7);
            caiwu7.setSleepTrue(true);
            branchDayMap.put("7", caiwu7);

            branchMap.put("renshi", branchDayMap);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * @note 设置意大利语部门
     * @author wangmeng
     * @date 2017年8月6日 上午12:49:34
     */
    public static void setYidali(Map<String, Map<String, Branch>> branchMap) {
        try {
            Map<String, Branch> branchDayMap = new HashMap<>();
            // 设置周一
            Branch caiwu = new Branch();
            caiwu.setBranchName("yidali");
            caiwu.setWeek(1);
            caiwu.setSleepTrue(false);
            caiwu.setUpTime("08:30");
            caiwu.setDownTime("17:30");
            branchDayMap.put("1", caiwu);

            // 设置周二
            Branch caiwu2 = new Branch();
            BeanUtils.copyProperties(caiwu2, caiwu);
            caiwu2.setWeek(2);
            branchDayMap.put("2", caiwu2);

            // 设置周三
            Branch caiwu3 = new Branch();
            BeanUtils.copyProperties(caiwu3, caiwu);
            caiwu3.setWeek(3);
            branchDayMap.put("3", caiwu3);

            // 设置周四
            Branch caiwu4 = new Branch();
            BeanUtils.copyProperties(caiwu4, caiwu);
            caiwu4.setWeek(4);
            branchDayMap.put("4", caiwu4);

            // 设置周五
            Branch caiwu5 = new Branch();
            BeanUtils.copyProperties(caiwu5, caiwu);
            caiwu5.setWeek(5);
            branchDayMap.put("5", caiwu5);

            // 设置周六
            Branch caiwu6 = new Branch();
            BeanUtils.copyProperties(caiwu6, caiwu);
            caiwu6.setWeek(6);
            caiwu6.setSleepTrue(true);
            branchDayMap.put("6", caiwu6);

            // 设置周日
            Branch caiwu7 = new Branch();
            BeanUtils.copyProperties(caiwu7, caiwu);
            caiwu7.setWeek(7);
            caiwu7.setSleepTrue(true);
            branchDayMap.put("7", caiwu7);

            branchMap.put("yidali", branchDayMap);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * @note 设置网络咨询部门
     * @author wangmeng
     * @date 2017年8月6日 上午12:49:34
     */
    public static void setWangZi(Map<String, Map<String, Branch>> branchMap) {
        try {
            Map<String, Branch> branchDayMap = new HashMap<>();
            // 设置周一
            Branch caiwu = new Branch();
            caiwu.setBranchName("wangzi");
            caiwu.setWeek(1);
            caiwu.setSleepTrue(false);
            caiwu.setUpTime("08:30");
            caiwu.setDownTime("17:30");
            branchDayMap.put("1", caiwu);

            // 设置周二
            Branch caiwu2 = new Branch();
            BeanUtils.copyProperties(caiwu2, caiwu);
            caiwu2.setWeek(2);
            branchDayMap.put("2", caiwu2);

            // 设置周三
            Branch caiwu3 = new Branch();
            BeanUtils.copyProperties(caiwu3, caiwu);
            caiwu3.setWeek(3);
            branchDayMap.put("3", caiwu3);

            // 设置周四
            Branch caiwu4 = new Branch();
            BeanUtils.copyProperties(caiwu4, caiwu);
            caiwu4.setWeek(4);
            branchDayMap.put("4", caiwu4);

            // 设置周五
            Branch caiwu5 = new Branch();
            BeanUtils.copyProperties(caiwu5, caiwu);
            caiwu5.setWeek(5);
            branchDayMap.put("5", caiwu5);

            // 设置周六
            Branch caiwu6 = new Branch();
            BeanUtils.copyProperties(caiwu6, caiwu);
            caiwu6.setWeek(6);
            caiwu6.setSleepTrue(true);
            branchDayMap.put("6", caiwu6);

            // 设置周日
            Branch caiwu7 = new Branch();
            BeanUtils.copyProperties(caiwu7, caiwu);
            caiwu7.setWeek(7);
            caiwu7.setSleepTrue(true);
            branchDayMap.put("7", caiwu7);

            branchMap.put("wangzi", branchDayMap);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * @note 设置西语教学部门
     * @author wangmeng
     * @date 2017年8月6日 上午12:49:34
     */
    public static void setXiYu(Map<String, Map<String, Branch>> branchMap) {
        try {
            Map<String, Branch> branchDayMap = new HashMap<>();
            // 设置周一
            Branch caiwu = new Branch();
            caiwu.setBranchName("xiyu");
            caiwu.setWeek(1);
            caiwu.setSleepTrue(false);
            caiwu.setUpTime("08:30");
            caiwu.setDownTime("17:30");
            branchDayMap.put("1", caiwu);

            // 设置周二
            Branch caiwu2 = new Branch();
            BeanUtils.copyProperties(caiwu2, caiwu);
            caiwu2.setWeek(2);
            branchDayMap.put("2", caiwu2);

            // 设置周三
            Branch caiwu3 = new Branch();
            BeanUtils.copyProperties(caiwu3, caiwu);
            caiwu3.setWeek(3);
            branchDayMap.put("3", caiwu3);

            // 设置周四
            Branch caiwu4 = new Branch();
            BeanUtils.copyProperties(caiwu4, caiwu);
            caiwu4.setWeek(4);
            branchDayMap.put("4", caiwu4);

            // 设置周五
            Branch caiwu5 = new Branch();
            BeanUtils.copyProperties(caiwu5, caiwu);
            caiwu5.setWeek(5);
            branchDayMap.put("5", caiwu5);

            // 设置周六
            Branch caiwu6 = new Branch();
            BeanUtils.copyProperties(caiwu6, caiwu);
            caiwu6.setWeek(6);
            caiwu6.setSleepTrue(true);
            branchDayMap.put("6", caiwu6);

            // 设置周日
            Branch caiwu7 = new Branch();
            BeanUtils.copyProperties(caiwu7, caiwu);
            caiwu7.setWeek(7);
            caiwu7.setSleepTrue(true);
            branchDayMap.put("7", caiwu7);

            branchMap.put("xiyu", branchDayMap);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * @note 设置国际部门
     * @author wangmeng
     * @date 2017年8月6日 上午12:49:34
     */
    public static void setGuoJi(Map<String, Map<String, Branch>> branchMap) {
        try {
            Map<String, Branch> branchDayMap = new HashMap<>();
            // 设置周一
            Branch caiwu = new Branch();
            caiwu.setBranchName("guoji");
            caiwu.setWeek(1);
            caiwu.setSleepTrue(false);
            caiwu.setUpTime("08:30");
            caiwu.setDownTime("17:30");
            branchDayMap.put("1", caiwu);

            // 设置周二
            Branch caiwu2 = new Branch();
            BeanUtils.copyProperties(caiwu2, caiwu);
            caiwu2.setWeek(2);
            branchDayMap.put("2", caiwu2);

            // 设置周三
            Branch caiwu3 = new Branch();
            BeanUtils.copyProperties(caiwu3, caiwu);
            caiwu3.setWeek(3);
            branchDayMap.put("3", caiwu3);

            // 设置周四
            Branch caiwu4 = new Branch();
            BeanUtils.copyProperties(caiwu4, caiwu);
            caiwu4.setWeek(4);
            branchDayMap.put("4", caiwu4);

            // 设置周五
            Branch caiwu5 = new Branch();
            BeanUtils.copyProperties(caiwu5, caiwu);
            caiwu5.setWeek(5);
            branchDayMap.put("5", caiwu5);

            // 设置周六
            Branch caiwu6 = new Branch();
            BeanUtils.copyProperties(caiwu6, caiwu);
            caiwu6.setWeek(6);
            caiwu6.setSleepTrue(true);
            branchDayMap.put("6", caiwu6);

            // 设置周日
            Branch caiwu7 = new Branch();
            BeanUtils.copyProperties(caiwu7, caiwu);
            caiwu7.setWeek(7);
            caiwu7.setSleepTrue(true);
            branchDayMap.put("7", caiwu7);

            branchMap.put("guoji", branchDayMap);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * @note 设置产品开发部门
     * @author wangmeng
     * @date 2017年8月6日 上午12:49:34
     */
    public static void setProduct(Map<String, Map<String, Branch>> branchMap) {
        try {
            Map<String, Branch> branchDayMap = new HashMap<>();
            // 设置周一
            Branch caiwu = new Branch();
            caiwu.setBranchName("product");
            caiwu.setWeek(1);
            caiwu.setSleepTrue(false);
            caiwu.setUpTime("08:30");
            caiwu.setDownTime("17:30");
            branchDayMap.put("1", caiwu);

            // 设置周二
            Branch caiwu2 = new Branch();
            BeanUtils.copyProperties(caiwu2, caiwu);
            caiwu2.setWeek(2);
            branchDayMap.put("2", caiwu2);

            // 设置周三
            Branch caiwu3 = new Branch();
            BeanUtils.copyProperties(caiwu3, caiwu);
            caiwu3.setWeek(3);
            branchDayMap.put("3", caiwu3);

            // 设置周四
            Branch caiwu4 = new Branch();
            BeanUtils.copyProperties(caiwu4, caiwu);
            caiwu4.setWeek(4);
            branchDayMap.put("4", caiwu4);

            // 设置周五
            Branch caiwu5 = new Branch();
            BeanUtils.copyProperties(caiwu5, caiwu);
            caiwu5.setWeek(5);
            branchDayMap.put("5", caiwu5);

            // 设置周六
            Branch caiwu6 = new Branch();
            BeanUtils.copyProperties(caiwu6, caiwu);
            caiwu6.setWeek(6);
            caiwu6.setSleepTrue(true);
            branchDayMap.put("6", caiwu6);

            // 设置周日
            Branch caiwu7 = new Branch();
            BeanUtils.copyProperties(caiwu7, caiwu);
            caiwu7.setWeek(7);
            caiwu7.setSleepTrue(true);
            branchDayMap.put("7", caiwu7);

            branchMap.put("product", branchDayMap);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * @note 设置稽查部门
     * @author wangmeng
     * @date 2017年8月6日 上午12:49:34
     */
    public static void setJiCha(Map<String, Map<String, Branch>> branchMap) {
        try {
            Map<String, Branch> branchDayMap = new HashMap<>();
            // 设置周一
            Branch caiwu = new Branch();
            caiwu.setBranchName("jicha");
            caiwu.setWeek(1);
            caiwu.setSleepTrue(false);
            caiwu.setUpTime("08:30");
            caiwu.setDownTime("17:30");
            branchDayMap.put("1", caiwu);

            // 设置周二
            Branch caiwu2 = new Branch();
            BeanUtils.copyProperties(caiwu2, caiwu);
            caiwu2.setWeek(2);
            branchDayMap.put("2", caiwu2);

            // 设置周三
            Branch caiwu3 = new Branch();
            BeanUtils.copyProperties(caiwu3, caiwu);
            caiwu3.setWeek(3);
            branchDayMap.put("3", caiwu3);

            // 设置周四
            Branch caiwu4 = new Branch();
            BeanUtils.copyProperties(caiwu4, caiwu);
            caiwu4.setWeek(4);
            branchDayMap.put("4", caiwu4);

            // 设置周五
            Branch caiwu5 = new Branch();
            BeanUtils.copyProperties(caiwu5, caiwu);
            caiwu5.setWeek(5);
            branchDayMap.put("5", caiwu5);

            // 设置周六
            Branch caiwu6 = new Branch();
            BeanUtils.copyProperties(caiwu6, caiwu);
            caiwu6.setWeek(6);
            caiwu6.setSleepTrue(true);
            branchDayMap.put("6", caiwu6);

            // 设置周日
            Branch caiwu7 = new Branch();
            BeanUtils.copyProperties(caiwu7, caiwu);
            caiwu7.setWeek(7);
            caiwu7.setSleepTrue(true);
            branchDayMap.put("7", caiwu7);

            branchMap.put("jicha", branchDayMap);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * @note 设置营销部门
     * @author wangmeng
     * @date 2017年8月6日 上午12:49:34
     */
    public static void setYingXiao(Map<String, Map<String, Branch>> branchMap) {
        try {
            Map<String, Branch> branchDayMap = new HashMap<>();
            // 设置周一
            Branch caiwu = new Branch();
            caiwu.setBranchName("yingxiao");
            caiwu.setWeek(1);
            caiwu.setSleepTrue(false);
            caiwu.setUpTime("08:30");
            caiwu.setDownTime("17:30");
            branchDayMap.put("1", caiwu);

            // 设置周二
            Branch caiwu2 = new Branch();
            BeanUtils.copyProperties(caiwu2, caiwu);
            caiwu2.setWeek(2);
            branchDayMap.put("2", caiwu2);

            // 设置周三
            Branch caiwu3 = new Branch();
            BeanUtils.copyProperties(caiwu3, caiwu);
            caiwu3.setWeek(3);
            branchDayMap.put("3", caiwu3);

            // 设置周四
            Branch caiwu4 = new Branch();
            BeanUtils.copyProperties(caiwu4, caiwu);
            caiwu4.setWeek(4);
            branchDayMap.put("4", caiwu4);

            // 设置周五
            Branch caiwu5 = new Branch();
            BeanUtils.copyProperties(caiwu5, caiwu);
            caiwu5.setWeek(5);
            branchDayMap.put("5", caiwu5);

            // 设置周六
            Branch caiwu6 = new Branch();
            BeanUtils.copyProperties(caiwu6, caiwu);
            caiwu6.setWeek(6);
            caiwu6.setSleepTrue(true);
            branchDayMap.put("6", caiwu6);

            // 设置周日
            Branch caiwu7 = new Branch();
            BeanUtils.copyProperties(caiwu7, caiwu);
            caiwu7.setWeek(7);
            caiwu7.setSleepTrue(true);
            branchDayMap.put("7", caiwu7);

            branchMap.put("yingxiao", branchDayMap);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
