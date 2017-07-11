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
import com.second.credit.core.model.baobao.Staff;
import com.second.credit.core.model.baobao.StaffShow;
import com.second.credit.core.utils.ExcelUtils;

public class BaoBaoService {

    static Map<String, Map<String, String>> attendanceMap;

    private final static String MOUTNDATE = "2017-05";
    private final static int MONTH = 5;

    static {
        attendanceMap = new HashMap<>();

        // --财务部
        Map<String, String> caiwu = new HashMap<>();
        caiwu.put("startTime", "8:30");
        caiwu.put("endTime", "17:30");
        attendanceMap.put("财务部", caiwu);

        // --市场部
        caiwu.put("startTime", "8:30");
        caiwu.put("endTime", "17:30");
        attendanceMap.put("市场部", caiwu);

        // --网络部
        Map<String, String> wangluo = new HashMap<>();
        wangluo.put("startTime", "8:30");
        wangluo.put("endTime", "17:30");
        attendanceMap.put("网络部", wangluo);

        // --教务部
        Map<String, String> jiaoxue = new HashMap<>();
        jiaoxue.put("startTime", "8:30");
        jiaoxue.put("endTime", "17:30");
        attendanceMap.put("教务部", jiaoxue);

        // --留学部
        Map<String, String> liuxue = new HashMap<>();
        liuxue.put("startTime", "8:30");
        liuxue.put("endTime", "17:30");
        attendanceMap.put("留学部", liuxue);

        // --人事行政部
        Map<String, String> renshi = new HashMap<>();
        renshi.put("startTime", "8:30");
        renshi.put("endTime", "17:30");
        attendanceMap.put("人事行政部", renshi);

        // --意大利语部
        Map<String, String> yiyu = new HashMap<>();
        yiyu.put("startTime", "8:30");
        yiyu.put("endTime", "17:30");
        attendanceMap.put("意大利语部", yiyu);

        // --网咨部
        Map<String, String> wangzi = new HashMap<>();
        wangzi.put("startTime", "8:30");
        wangzi.put("endTime", "17:30");
        attendanceMap.put("网咨部", wangzi);

        // --西语课程部
        Map<String, String> xibuyiyu = new HashMap<>();
        xibuyiyu.put("startTime", "8:30");
        xibuyiyu.put("endTime", "17:30");
        attendanceMap.put("西语课程部", xibuyiyu);

        // --国际部
        Map<String, String> guoji = new HashMap<>();
        guoji.put("startTime", "9:00");
        guoji.put("endTime", "18:00");
        attendanceMap.put("国际部", guoji);

        // --产品开发部
        Map<String, String> chanpinkaifa = new HashMap<>();
        chanpinkaifa.put("startTime", "8:30");
        chanpinkaifa.put("endTime", "17:30");
        attendanceMap.put("产品开发部", chanpinkaifa);

        // --稽查部
        Map<String, String> jicha = new HashMap<>();
        jicha.put("startTime", "8:30");
        jicha.put("endTime", "17:30");
        attendanceMap.put("稽查部", jicha);

        // --营销中心
        Map<String, String> yingxiao = new HashMap<>();
        yingxiao.put("startTime", "8:30");
        yingxiao.put("endTime", "17:30");
        attendanceMap.put("营销中心", yingxiao);

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

            // 判断开始时间、接收时间
            Map<String, String> branchAttendance = attendanceMap.get(staff.getBranch());
            if (branchAttendance == null) {
                System.out.println("部门=" + staff.getBranch() + " == null");
                continue;
            }
            int startTime = Integer.parseInt(branchAttendance.get("startTime").replace(":", ""));
            show.setStartTime(branchAttendance.get("startTime"));
            int endTime = Integer.parseInt(branchAttendance.get("endTime").replace(":", ""));
            show.setEndTime(branchAttendance.get("endTime"));

            // 遍历每一天
            forEachDay(attendanceListMap, show, startTime, endTime, list);
        }
        return list;
    }

    /**
     * @note 遍历每一天
     */
    private static void forEachDay(Map<String, List<Integer>> attendanceListMap, StaffShow show, int startTime,
            int endTime, List<StaffShow> list) {
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
            if (ca.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || ca.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
                list.add(oneShow);
                continue;
            }

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
}
