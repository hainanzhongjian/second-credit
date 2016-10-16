package com.second.credit.api.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.second.credit.api.base.Result;
import com.second.credit.api.base.ResultCodeConstants;
import com.second.credit.api.service.BankService;
import com.second.credit.core.model.SysBankInfo;

@Controller("/file")
public class FileController {

    @Autowired
    private BankService bankService;

    /**
     * @note 查询所有的银行卡信息
     * @param request
     * @param response
     * @return
     * @author wangmeng
     * @date 2016年10月16日 下午6:03:49
     */
    @RequestMapping("/selectSysBankInfo")
    public String selectSysBankInfo(HttpServletRequest request, HttpServletResponse response) {
        List<SysBankInfo> bankList = bankService.selectAll();
        Result<List<SysBankInfo>> result = new Result<>();
        result.setCode(ResultCodeConstants.CODE_SUCCESS);
        result.setMessage(ResultCodeConstants.MSG_SUCCESS);
        result.setBean(bankList);
        return JSON.toJSONString(result);
    }
}
