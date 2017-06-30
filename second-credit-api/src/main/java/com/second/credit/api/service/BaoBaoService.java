package com.second.credit.api.service;

import java.io.File;
import java.util.List;

import com.second.credit.core.utils.ExcelUtils;

public class BaoBaoService {

    public static void main(String[] args) {
        String url = "F:/baobao/11.xls";
        File file = new File(url);
        List<String[]> excelList = ExcelUtils.readExcel(file, 4);
        excelList.stream().forEach(recordCell -> {
            System.out.println(recordCell[0]);
        });
    }
}
