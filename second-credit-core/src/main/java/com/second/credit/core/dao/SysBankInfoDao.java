package com.second.credit.core.dao;

import java.util.List;

import com.second.credit.core.model.SysBankInfo;

public interface SysBankInfoDao {

    public int insert(SysBankInfo record);

    public SysBankInfo selectByPrimaryKey(Long id);

    public List<SysBankInfo> selectAll();

    public int updateByPrimaryKey(SysBankInfo record);
}