package com.second.credit.comm.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @note 正则工具类
 * @author wangmeng
 * @date 2017年4月1日 下午6:06:34
 */
public class RegexUtils {

    /**
     * @note 输入正则表达式的正则
     */
    private static final String REGEX_REGEX = ".*?(<%.+?%>).*?";

    private RegexUtils() {
    }

    /**
     * @note 正则匹配出字符串
     */
    private static List<String> regexMatchList(String content, String regex) {
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
        Matcher matcher = pattern.matcher(content);
        List<String> strs = new ArrayList<>();
        while (matcher.find()) {
            strs.add(matcher.group(1));
        }
        return strs;
    }

    /**
     * @note 正则匹配出字符串
     */
    private static List<Map<String, String>> regexBaseMatch(String content, String regex) {

        // 添加代码块锁，防止并发
        synchronized (regex) { // NOSONAR

            // 获取正则表达式里面的keys
            List<String> regexKeys = regexMatchList(regex, REGEX_REGEX);
            StringBuilder regexBuilder = new StringBuilder(regex);
            for (int i = 0; i < regexKeys.size(); i++) {
                String regexKey = regexKeys.get(i);
                int startIndex = regexBuilder.indexOf(regexKey);
                regexBuilder.replace(startIndex, startIndex + regexKey.length(), "");
                String key = regexKey.replace("<%", "").replace("%>", "");
                regexKeys.set(i, key);
            }

            // 正则定义
            Pattern pattern = Pattern.compile(regexBuilder.toString(), Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
            Matcher matcher = pattern.matcher(content);
            List<Map<String, String>> returnList = new ArrayList<>();

            // 循环查找
            while (matcher.find()) {

                // 循环一组匹配出来数据
                Map<String, String> returnMap = new HashMap<>();
                for (int i = 0; i < matcher.groupCount() + 1; i++) {
                    switch (i) { // NOSONAR
                        case 0: // NOSONAR
                            continue;
                        default:
                            returnMap.put(regexKeys.get(i - 1), matcher.group(i));
                            break;
                    }
                }
                returnList.add(returnMap);
            }
            return returnList;
        }
    }

    /**
     * @note 正则匹配出字符串
     */
    private static Map<String, String> regexBaseMatchMap(String content, String regex) {

        // 添加代码块锁，防止并发
        synchronized (regex) { // NOSONAR

            // 获取正则表达式里面的keys
            List<String> regexKeys = regexMatchList(regex, REGEX_REGEX);
            StringBuilder regexBuilder = new StringBuilder(regex);
            for (int i = 0; i < regexKeys.size(); i++) {
                String regexKey = regexKeys.get(i);
                int startIndex = regexBuilder.indexOf(regexKey);
                regexBuilder.replace(startIndex, startIndex + regexKey.length(), "");
                String key = regexKey.replace("<%", "").replace("%>", "");
                regexKeys.set(i, key);
            }

            // 正则定义
            Pattern pattern = Pattern.compile(regexBuilder.toString(), Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
            Matcher matcher = pattern.matcher(content);
            Map<String, String> returnList = new HashMap<>();

            // 循环查找
            while (matcher.find()) {

                // 循环一组匹配出来数据
                for (int i = 0; i < matcher.groupCount() + 1; i++) {
                    switch (i) { // NOSONAR
                        case 0: // NOSONAR
                            continue;
                        default:
                            returnList.put(regexKeys.get(i - 1), matcher.group(i));
                            break;
                    }
                }
            }
            return returnList;
        }
    }

    /**
     * @note 匹配出一条记录结果
     */
    public static Map<String, String> regexParserMatchOne(String regex, String content) {
        try {
            return regexBaseMatch(content, regex).get(0);
        } catch (Exception e) {
            throw new RuntimeException("regexParserMatchOne: regex = " + regex + " content = " + content
                    + " have exception", e);
        }
    }

    /**
     * @note 匹配出多条记录结果
     */
    public static List<Map<String, String>> regexParserMatchList(String regex, String content) {
        try {
            return regexBaseMatch(content, regex);
        } catch (Exception e) {
            throw new RuntimeException("regexParserMatchList: regex = " + regex + " content = " + content
                    + " have exception", e);
        }
    }

    /**
     * @note 匹配出一到多条记录结果
     */
    public static Map<String, String> regexParserMatchMap(String regex, String content) {
        try {
            return regexBaseMatchMap(content, regex);
        } catch (Exception e) {
            throw new RuntimeException("regexParserMatchList: regex = " + regex + " content = " + content
                    + " have exception", e);
        }
    }

    /**
     * @note 正则匹配出字符串
     */
    public static boolean regexMatchTrue(String content, String regex) {
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
        Matcher matcher = pattern.matcher(content);
        return matcher.find();
    }
}
