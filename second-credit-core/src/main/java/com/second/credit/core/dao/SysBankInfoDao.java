package com.second.credit.core.dao;

import com.second.credit.core.model.SysBankInfo;

public interface SysBankInfoDao {

    public int insert(SysBankInfo record);

    public SysBankInfo selectByPrimaryKey(Long id);

    public int updateByPrimaryKey(SysBankInfo record);
}