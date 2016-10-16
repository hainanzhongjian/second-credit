package com.second.credit.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.second.credit.core.dao.SysBankInfoDao;
import com.second.credit.core.model.SysBankInfo;

@Service
public class BankService {

    @Autowired
    private SysBankInfoDao sysBankInfoDao;

    public List<SysBankInfo> selectAll() {
        return sysBankInfoDao.selectAll();
    }

}
