package com.second.credit.core.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @note 生成excel工具类
 * @author wangmeng
 * @date 2016年6月26日 下午3:55:43
 */
public class ExcelUtils {
    private static DateFormat dateformate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static DateFormat dateformate2 = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 生成excel文件 titleMp 对应结果集的字段名称为Key
     * ,显示在Excel标题的内容为值.例：name-->姓名,日期类型调用dateformate
     * 
     * @param titleMp
     * @param recordLt
     *            输出的结果集
     * @param file
     *            文件
     * @return
     * @throws Exception
     */
    public static Workbook createFile(Workbook wb, Map<String, String> titleMp, List<?> recordLt, String sheetName)
            throws Exception { // NOSONAR
        CreationHelper createHelper = wb.getCreationHelper();
        String safeName = WorkbookUtil.createSafeSheetName(sheetName);
        // 创建sheet
        Sheet sheet1 = wb.createSheet(safeName);

        // 设置标题单元格样式
        CellStyle titlestyle = wb.createCellStyle();
        // 读写excel很耗内存，顾需要将内存调大
        Row row = sheet1.createRow((int) 0);
        // 生成标题
        Iterator iter = titleMp.entrySet().iterator();
        int cellNum = 0;

        List<String> cellNameLt = new ArrayList<String>();

        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            Object val = entry.getValue();
            Cell cell = row.createCell(cellNum);
            cell.setCellValue(createHelper.createRichTextString(val.toString()));
            cell.setCellStyle(titlestyle);
            // title列宽自适应
            sheet1.setColumnWidth(cellNum, val.toString().getBytes().length * 2 * 256);
            ++cellNum;
            cellNameLt.add(entry.getKey().toString());
        }
        int rowNum = 1;
        // 生成行列表

        // modify by huguoxing 解决空置指针异常问题
        if (recordLt != null && recordLt.size() > 0) {
            for (int k = 0; k < recordLt.size(); k++) {
                // 创建行
                row = sheet1.createRow((int) rowNum);
                int cellNo = 0;
                for (int i = 0; i < cellNameLt.size(); i++) {
                    Cell cell = row.createCell(cellNo);
                    // 获取对应的列的值
                    if (recordLt.get(k) != null) {
                        Object value = PropertyUtils.getProperty(recordLt.get(k), cellNameLt.get(i));
                        String data = formateExcelData(value);
                        cell.setCellValue(createHelper.createRichTextString(data));
                    } else {
                        cell.setCellValue("");
                    }

                    ++cellNo;
                }
                ++rowNum;
            }
        }
        return wb;

    }

    /**
     * 生成excel文件 titleMp 对应结果集的字段名称为Key
     * ,显示在Excel标题的内容为值.例：name-->姓名,日期类型调用dateformate2
     * 
     * @param titleMp
     * @param recordLt
     *            输出的结果集
     * @param file
     *            文件
     * @return
     * @throws Exception
     */
    public static Workbook createFile2(Workbook wb, Map<String, String> titleMp, List<?> recordLt, String sheetName)
            throws Exception { // NOSONAR
        CreationHelper createHelper = wb.getCreationHelper();
        String safeName = WorkbookUtil.createSafeSheetName(sheetName);
        // 创建sheet
        Sheet sheet1 = wb.createSheet(safeName);

        // 设置标题单元格样式
        CellStyle titlestyle = wb.createCellStyle();
        Row row = sheet1.createRow((int) 0);
        // 生成标题
        Iterator iter = titleMp.entrySet().iterator();
        int cellNum = 0;

        List<String> cellNameLt = new ArrayList<String>();

        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            Object val = entry.getValue();
            Cell cell = row.createCell(cellNum);
            cell.setCellValue(createHelper.createRichTextString(val.toString()));
            cell.setCellStyle(titlestyle);
            // title列宽自适应
            sheet1.setColumnWidth(cellNum, val.toString().getBytes().length * 2 * 256);
            ++cellNum;
            cellNameLt.add(entry.getKey().toString());
        }
        int rowNum = 1;
        // 生成行列表
        for (int k = 0; k < recordLt.size(); k++) {
            // 创建行
            row = sheet1.createRow((int) rowNum);
            int cellNo = 0;
            for (int i = 0; i < cellNameLt.size(); i++) {
                Cell cell = row.createCell(cellNo);
                // 获取对应的列的值
                if (recordLt.get(k) != null) {
                    Object value = PropertyUtils.getProperty(recordLt.get(k), cellNameLt.get(i));
                    String data = formateExcelData2(value);
                    cell.setCellValue(createHelper.createRichTextString(data));
                } else {
                    cell.setCellValue("");
                }
                ++cellNo;
            }
            ++rowNum;
        }
        return wb;

    }

    /**
     * 生成文件
     * 
     * @author wangmeng
     * @time 2015年1月28日下午4:17:39
     */
    public static void workbookToFile(Workbook wb, File file) throws Exception { // NOSONAR
        FileOutputStream fileOut = new FileOutputStream(file);
        wb.write(fileOut);
        fileOut.close();
    }

    /**
     * 创建Workbook对象
     * 
     * @return
     * @author wangmeng
     * @time 2015年1月28日下午4:20:19
     */
    public static Workbook createWorkbook() {
        return new XSSFWorkbook();
    }

    /**
     * 创建Workbook对象(一般处理大数量的)
     * 
     * @return
     * @author wangmeng
     * @time 2015年1月28日下午4:20:19
     */
    public static Workbook createWorkbookBig() {
        SXSSFWorkbook wb = new SXSSFWorkbook(10000);// 内存中保留 10000
                                                    // 条数据，以免内存溢出，其余写入 硬盘
        return wb;
    }

    /**
     * 格式化excel导出数据
     * 
     * @param value
     */
    private static String formateExcelData(Object value) {
        if (null == value) {
            return null;
        } else {
            if (value instanceof Date) {
                value = dateformate.format(value);
            }
            if (value instanceof Double) {
                value = NumberUtils.formatNumber(Double.parseDouble(value.toString()));
            }
            return value.toString();
        }
    }

    /**
     * 格式化excel导出数据
     * 
     * @param value
     */
    private static String formateExcelData2(Object value) {
        if (null == value) {
            return null;
        } else {
            if (value instanceof Date) {
                value = dateformate2.format(value);
            }
            if (value instanceof Double) {
                value = NumberUtils.formatNumber(Double.parseDouble(value.toString()));
            }
            return value.toString();
        }
    }

    /**
     * 文件下载
     * 
     * @author: yinjunlu
     * @Date: 2014年12月22日
     * @param response
     * @param file
     */
    public static void downFile(HttpServletResponse response, File file) {
        try {
            FileInputStream fs = new FileInputStream(file); // NOSONAR
            response.reset();
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition",
                    "attachment; filename=\"" + new String(file.getName().getBytes("gbk"), "iso8859-1") + "\"");
            PrintWriter out = response.getWriter();
            int b = 0;
            while ((b = fs.read()) != -1) {
                out.write(b);
            }
            fs.close();
            out.close();
        } catch (Exception e) { // NOSONAR
            e.printStackTrace(); // NOSONAR
        }
    }

    /**
     * 读取Excel的内容，
     * 
     * @param file
     *            读取数据的源Excel
     * @param ignoreRows
     *            读取数据忽略的行数，比喻行头不需要读入 忽略的行数为1
     * @return 读出的Excel中数据的内容
     * @throws FileNotFoundException
     * @throws IOException
     */
    @SuppressWarnings("deprecation")
    public static List<String[]> readExcel(File file, int ignoreRows) throws FileNotFoundException, IOException {
        List<String[]> result = new ArrayList<String[]>();
        int rowSize = 0;
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
        // 打开HSSFWorkbook
        POIFSFileSystem fs = new POIFSFileSystem(in);
        HSSFWorkbook wb = new HSSFWorkbook(fs);
        HSSFCell cell = null;
        for (int sheetIndex = 0; sheetIndex < wb.getNumberOfSheets(); sheetIndex++) {
            HSSFSheet st = wb.getSheetAt(sheetIndex);
            // 第一行为标题，不取
            for (int rowIndex = ignoreRows; rowIndex <= st.getLastRowNum(); rowIndex++) {
                HSSFRow row = st.getRow(rowIndex);
                if (row == null) {
                    continue;
                }
                int tempRowSize = row.getLastCellNum() + 1;
                if (tempRowSize > rowSize) {
                    rowSize = tempRowSize;
                }
                String[] values = new String[rowSize];
                Arrays.fill(values, "");
                boolean hasValue = false;
                for (short columnIndex = 0; columnIndex <= row.getLastCellNum(); columnIndex++) {
                    String value = "";
                    cell = row.getCell(columnIndex);
                    if (cell != null) {
                        // 注意：一定要设成这个，否则可能会出现乱码
                        // cell.setEncoding(HSSFCell.ENCODING_UTF_16);
                        switch (cell.getCellType()) {
                            case HSSFCell.CELL_TYPE_STRING:
                                value = cell.getStringCellValue();
                                break;
                            case HSSFCell.CELL_TYPE_NUMERIC:
                                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                                    Date date = cell.getDateCellValue();
                                    if (date != null) {
                                        value = new SimpleDateFormat("yyyy-MM-dd").format(date);
                                    } else {
                                        value = "";
                                    }
                                } else {
                                    value = new DecimalFormat("0").format(cell.getNumericCellValue());
                                }
                                break;
                            case HSSFCell.CELL_TYPE_FORMULA:
                                // 导入时如果为公式生成的数据则无值
                                if (!cell.getStringCellValue().equals("")) {
                                    value = cell.getStringCellValue();
                                } else {
                                    value = cell.getNumericCellValue() + "";
                                }
                                break;
                            case HSSFCell.CELL_TYPE_BLANK:
                                break;
                            case HSSFCell.CELL_TYPE_ERROR:
                                value = "";
                                break;
                            case HSSFCell.CELL_TYPE_BOOLEAN:
                                value = (cell.getBooleanCellValue() == true ? "Y" : "N");
                                break;
                            default:
                                value = "";
                        }
                    }
                    if (columnIndex == 0 && value.trim().equals("")) {
                        break;
                    }
                    values[columnIndex] = rightTrim(value);
                    hasValue = true;
                }
                if (hasValue) {
                    result.add(values);
                }
            }
        }
        in.close();
        // String[][] returnArray = new String[result.size()][rowSize];
        // for (int i = 0; i < returnArray.length; i++) {
        // returnArray[i] = (String[]) result.get(i);
        // }
        return result;
    }

    /**
     * 去掉字符串右边的空格
     * 
     * @param str
     *            要处理的字符串
     * @return 处理后的字符串
     */
    public static String rightTrim(String str) {
        if (str == null) {
            return "";
        }
        int length = str.length();
        for (int i = length - 1; i >= 0; i--) {
            if (str.charAt(i) != 0x20) {
                break;
            }
            length--;
        }
        return str.substring(0, length);
    }
}
