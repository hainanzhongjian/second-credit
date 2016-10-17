package com.second.credit.core.dao;

import java.util.List;

import com.second.credit.core.model.Cdistrict;

public interface CdistrictDao {

    int deleteByPrimaryKey(Long id);

    int insert(Cdistrict record);

    Cdistrict selectByPrimaryKey(Long id);

    List<Cdistrict> selectAll();

    int updateByPrimaryKey(Cdistrict record);

}