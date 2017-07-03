package com.second.credit.api.service;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;

import com.second.credit.comm.utils.RegexUtils;
import com.second.credit.core.model.baobao.Staff;
import com.second.credit.core.model.baobao.StaffShow;
import com.second.credit.core.utils.ExcelUtils;

public class BaoBaoService {

    static Map<String, Map<String, Integer>> attendanceMap;

    static {
        attendanceMap = new HashMap<>();
        Map<String, Integer> branchAttendance = new HashMap<>();
        branchAttendance.put("startTime", 830);
        branchAttendance.put("endTime", 1730);
        attendanceMap.put("财务部", branchAttendance);
        attendanceMap.put("市场部", branchAttendance);
        attendanceMap.put("留学部", branchAttendance);
        attendanceMap.put("教学部", branchAttendance);
        attendanceMap.put("意语部课程", branchAttendance);
        attendanceMap.put("产品创新", branchAttendance);
        attendanceMap.put("国际部", branchAttendance);
        attendanceMap.put("人事行政部", branchAttendance);
        attendanceMap.put("网络部", branchAttendance);
        attendanceMap.put("稽查部", branchAttendance);
        attendanceMap.put("西语部课程", branchAttendance);
        attendanceMap.put("西语留学部", branchAttendance);
        attendanceMap.put("教务部", branchAttendance);
        attendanceMap.put("办公室", branchAttendance);
        attendanceMap.put("网咨部", branchAttendance);
        attendanceMap.put("策划部", branchAttendance);
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
            Map<String, Integer> branchAttendance = attendanceMap.get(staff.getBranch());
            if (branchAttendance == null) {
                System.out.println("部门=" + staff.getBranch() + " == null");
                continue;
            }
            int startTime = branchAttendance.get("startTime");
            show.setStartTime(Integer.toString(startTime));
            int endTime = branchAttendance.get("endTime");
            show.setEndTime(Integer.toString(endTime));

            // 遍历每一天
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
                oneShow.setDate(entry.getKey());
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
        return list;
    }

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
                attendance = new HashMap<>();
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

    private static void addDateMap(Map<String, List<Integer>> attendance, String[] recordCell) {
        for (int i = 1; i < recordCell.length; i++) {
            attendance.put(recordCell[i], null);
        }
    }

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
