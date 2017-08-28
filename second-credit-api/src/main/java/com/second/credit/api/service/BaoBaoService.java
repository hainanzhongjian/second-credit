package com.second.credit.api.service;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;

import com.second.credit.comm.utils.DateUtils;
import com.second.credit.comm.utils.RegexUtils;
import com.second.credit.core.model.baobao.Branch;
import com.second.credit.core.model.baobao.Staff;
import com.second.credit.core.model.baobao.StaffShow;
import com.second.credit.core.utils.ExcelUtils;

public class BaoBaoService {

    static Map<String, Map<Integer, Branch>> branchMap;

    private final static String MOUTNDATE = "2017-05";
    private final static int MONTH = 5;

    static {
        // 加载部门考勤信息
        branchMap = readBranchDetailExcel();
    }

    public static void main(String[] args) {
        try {
            // 读excel
            List<Staff> staffList = readExcel();
            // 写入excel
            writeExcel(staffList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void writeExcel(List<Staff> staffList) {
        // model 转换
        List<StaffShow> showList = transform(staffList);
        System.out.println(11);

        String url = "F:/baobao/target.xls";
        Workbook wb = ExcelUtils.createWorkbook();
        Map<String, String> titleMap = new LinkedHashMap<>();
        titleMap.put("branch", "部门");
        titleMap.put("realName", "姓名");
        titleMap.put("jobNumber", "工号");
        titleMap.put("date", "日期");
        titleMap.put("startTime", "上班时间");
        titleMap.put("endTime", "下班时间");
        titleMap.put("record", "打卡记录");
        titleMap.put("reason", "原因");
        try {
            ExcelUtils.createFile(wb, titleMap, showList, "考勤表");
        } catch (Exception e) {
            e.printStackTrace();
        }
        ExcelUtils.workbookToFile(wb, new File(url));
    }

    private static List<StaffShow> transform(List<Staff> staffList) {
        List<StaffShow> list = new ArrayList<>();
        for (Staff staff : staffList) {
            StaffShow show = new StaffShow();
            show.setBranch(staff.getBranch());
            show.setJobNumber(staff.getJobNumber());
            show.setRealName(staff.getRealName());
            Map<String, List<Integer>> attendanceListMap = staff.getAttendance();

            // 获取上班时间节点
            Map<Integer, Branch> weekMap = branchMap.get(transformBranchName(staff.getBranch()));
            if (weekMap == null || weekMap.size() == 0) {
                System.out.println("部门=" + staff.getBranch() + " == null 考勤详细表中没有记录该部门");
                continue;
            }

            // 遍历每一天
            forEachDay(attendanceListMap, show, weekMap, list);
        }
        return list;
    }

    /**
     * @note 部门名称转换
     * @param branch
     * @return
     * @author wangmeng
     * @date 2017年8月28日 上午12:07:09
     */
    private static String transformBranchName(String branch) {
        if (StringUtils.isEmpty(branch)) {
            return "";
        }
        if (branch.equals("财务部") || branch.equals("财务")) {
            return "caiwu";
        }
        return "";
    }

    /**
     * @note 转换星期日期
     * @param javaWeek
     * @return
     * @author wangmeng
     * @date 2017年8月27日 下午11:37:34
     */
    private static int transformWeek(int javaWeek) {
        switch (javaWeek) {
            case Calendar.SUNDAY:
                return 7;
            case Calendar.MONDAY:
                return 1;
            case Calendar.TUESDAY:
                return 2;
            case Calendar.WEDNESDAY:
                return 3;
            case Calendar.THURSDAY:
                return 4;
            case Calendar.FRIDAY:
                return 5;
            case Calendar.SATURDAY:
                return 6;
        }
        return javaWeek;
    }

    /**
     * @note 遍历每一天
     */
    private static void forEachDay(Map<String, List<Integer>> attendanceListMap, StaffShow show,
            Map<Integer, Branch> weekMap, List<StaffShow> list) {
        for (Entry<String, List<Integer>> entry : attendanceListMap.entrySet()) {
            StaffShow oneShow = new StaffShow();
            try {
                BeanUtils.copyProperties(oneShow, show);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            List<Integer> oneDayAttendanceList = entry.getValue();
            oneShow.setRecord(integerListToString(oneDayAttendanceList));
            Date nowYearMonth = DateUtils.formatDateStr(MOUTNDATE, DateUtils.PATTERN_YEAR_MONTH);
            Date oneDay = DateUtils.addDays(nowYearMonth, Integer.parseInt(entry.getKey()) - 1);
            String nowDate = DateUtils.formatDate(oneDay, DateUtils.PATTERN_DEFAULT);
            oneShow.setDate(nowDate + "---" + entry.getKey());

            // 当前月份不是本月的话，不执行
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(oneDay);
            int month = calendar.get(calendar.MONTH) + 1;
            if (month != MONTH) {
                continue;
            }

            // 根据部门区分休息日
            Calendar ca = Calendar.getInstance();
            ca.setTime(oneDay);

            // 获取当天的考勤详细信息
            int weekDay = transformWeek(ca.get(Calendar.DAY_OF_WEEK));
            Branch branch = weekMap.get(weekDay);

            // 休息日判断
            if (branch.sleepTrue) {
                oneShow.setReason("休息日");
                System.out.println("今天是休息日");
                continue;
            }

            // 当天的上班时间和下班时间
            int startTime = Integer.parseInt(branch.getUpTime().replace(":", ""));
            int endTime = Integer.parseInt(branch.getDownTime().replace(":", ""));

            // 执行工作日
            if (oneDayAttendanceList.size() == 1) {
                int time = oneDayAttendanceList.get(0);
                if (time == 0) {
                    oneShow.setReason("没有考勤记录");
                } else if (time < 1200) {
                    if (time > startTime) {
                        oneShow.setReason("早晨迟到，晚上没有考勤记录");
                    } else {
                        oneShow.setReason("晚上没有考勤记录");
                    }
                } else {
                    if (time >= endTime) {
                        oneShow.setReason("早晨没有考勤记录");
                    } else {
                        oneShow.setReason("晚上早退，早晨没有考勤记录");
                    }
                }
            } else {
                int startTimeTarget = oneDayAttendanceList.get(0);
                int endTimeTarget = oneDayAttendanceList.get(0);
                for (Integer integer : oneDayAttendanceList) {
                    if (integer < startTimeTarget) {
                        startTimeTarget = integer;
                    }
                    if (integer > endTimeTarget) {
                        endTimeTarget = integer;
                    }
                }

                if (startTimeTarget > startTime) {
                    oneShow.setReason("迟到、");
                }
                if (endTimeTarget < endTime) {
                    oneShow.setReason(show.getReason() + "早退、");
                }
            }
            list.add(oneShow);
        }
    }

    /**
     * @note 读部门考勤详细excel
     */
    private static Map<String, Map<Integer, Branch>> readBranchDetailExcel() {
        // String url = "F:/baobao/kaoqin-detail.xls";
        String url = "F:/baobao/112.xls";
        File file = new File(url);
        List<String[]> excelList = ExcelUtils.readExcel(file, 1);

        Map<String, Map<Integer, Branch>> branchMap = new HashMap<>();
        Map<Integer, Branch> weekMap = new HashMap<>();
        for (int i = 0; i < excelList.size(); i++) {

            // 每7条记录作为一个周对象
            String[] branchDetailArray = excelList.get(i);
            int week = i % 7;
            if (week == 0 && i != 0) {
                branchMap.put(weekMap.get(1).getBranchName(), weekMap);
                weekMap = new HashMap<>();
            }
            Branch branch = new Branch();
            branch.setBranchName(branchDetailArray[0]);
            branch.setWeek(Integer.parseInt(branchDetailArray[1]));
            branch.setUpTime(branchDetailArray[2]);
            branch.setDownTime(branchDetailArray[3]);
            boolean boo = branchDetailArray[4].equals("Y") ? true : false;
            branch.setSleepTrue(boo);
            weekMap.put(week + 1, branch);
        }
        return branchMap;
    }

    /**
     * @note 读excel
     */
    private static List<Staff> readExcel() {
        String url = "F:/baobao/11.xls";
        File file = new File(url);
        List<String[]> excelList = ExcelUtils.readExcel(file, 4);
        List<Staff> staffList = new LinkedList<>();
        Staff staff = null;
        Map<String, List<Integer>> attendance = null;
        int attendanceCounts = 0;
        for (String[] recordCell : excelList) {
            String firstName = recordCell[0].trim().replace(" ", "");
            if (firstName.equals("员工")) {
                if (staff != null) {
                    staffList.add(staff);
                }
                // 参数初始化
                attendanceCounts = 0;
                staff = new Staff();
                attendance = new LinkedHashMap<>();
                addStaff(staff, recordCell);
            }
            if (firstName.equals("考勤")) {
                attendanceCounts++;
                addDateValueMap(attendanceCounts, attendance, recordCell);
                // 考勤执行两次之后添加到staff中
                if (attendanceCounts == 2) {
                    staff.setAttendance(attendance);
                }
            }
        }
        return staffList;
    }

    private static void addDateValueMap(int attendanceCounts, Map<String, List<Integer>> attendance, String[] recordCell) {
        for (int i = 1; i < recordCell.length - 1; i++) {
            List<Integer> list = new ArrayList<>();
            String attendanceStr = recordCell[i].trim().replace(" ", "");
            if (StringUtils.isEmpty(attendanceStr)) {
                list.add(0);
            } else {
                int size = attendanceStr.length() / 5;
                for (int j = 0; j < size; j++) {
                    list.add(Integer.parseInt(attendanceStr.substring(0, 5).replace(":", "")));
                    attendanceStr = attendanceStr.substring(5, attendanceStr.length());
                }
            }
            if (attendanceCounts > 1) {
                attendance.put(Integer.toString(i + 16), list);
            } else {
                attendance.put(Integer.toString(i), list);
            }
        }
    }

    /**
     * @note integerList转String
     */
    private static String integerListToString(List<Integer> oneDayAttendanceList) {
        StringBuilder builder = new StringBuilder();
        for (Integer integer : oneDayAttendanceList) {
            String sub = Integer.toString(integer);
            if (sub.length() == 1) {
                break;
            }
            builder.append(sub.substring(0, sub.length() - 2)).append(":")
                    .append(sub.substring(sub.length() - 2, sub.length())).append(",");
        }
        return builder.toString();
    }

    /**
     * @note 添加工号、姓名、部门
     */
    private static void addStaff(Staff staff, String[] recordCell) {
        for (int i = 1; i < recordCell.length; i++) {
            String cell = recordCell[i].trim().replace(" ", "");
            if (!StringUtils.isEmpty(cell)) {
                String regex = "工号:(<%jobNumber%>.+?)姓名:(<%realName%>[\u4e00-\u9fa5]{1,})部门:(<%branch%>[\u4e00-\u9fa5]{1,})";
                Map<String, String> regexMap = RegexUtils.regexParserMatchMap(regex, cell);
                staff.setJobNumber(regexMap.get("jobNumber"));
                staff.setRealName(regexMap.get("realName"));
                staff.setBranch(regexMap.get("branch"));
            }
        }
    }

    /**
     * @note 构造原始数据
     * @author wangmeng
     * @return
     * @date 2017年7月31日 上午12:18:29
     */
    public static Map<String, Map<String, Branch>> createSourceDate(String branchName) {
        Map<String, Map<String, Branch>> branchMap = new HashMap<>();

        if ("caiwu".equals(branchName)) {
            // 财务
            Map<String, Branch> caiwuDayMap = new HashMap<>();
            Branch caiwu = new Branch();
            caiwu.setBranchName("caiwu");
            caiwu.setWeek(1);
            caiwu.setSleepTrue(false);
            caiwu.setUpTime("08:30");
            caiwu.setDownTime("17:30");
            caiwuDayMap.put("1", caiwu);
            caiwu.setWeek(2);
            caiwuDayMap.put("2", caiwu);
            caiwu.setWeek(3);
            caiwuDayMap.put("3", caiwu);
            caiwu.setWeek(4);
            caiwuDayMap.put("4", caiwu);
            caiwu.setWeek(5);
            caiwuDayMap.put("5", caiwu);
            caiwu.setWeek(6);
            caiwu.setSleepTrue(true);
            caiwuDayMap.put("6", caiwu);
            caiwu.setWeek(7);
            caiwu.setSleepTrue(true);
            caiwuDayMap.put("7", caiwu);
            branchMap.put("caiwu", caiwuDayMap);
        }
        return branchMap;
    }
}
