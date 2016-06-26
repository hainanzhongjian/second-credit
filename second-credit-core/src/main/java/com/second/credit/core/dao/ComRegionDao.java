package com.second.credit.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.second.credit.core.model.ComRegion;

public interface ComRegionDao {

    public int insert(ComRegion record);

    public ComRegion selectByPrimaryKey(Long id);

    /**
     * @note 获取根节点
     * @return
     */
    public ComRegion selectRootPoints();

    public ComRegion selectOneByPointsLayerName(@Param("left") int left, @Param("right") int right,
            @Param("layer") int layer, @Param("name") String name);

    public List<ComRegion> selectByNameLayer(@Param("name") String name, @Param("layer") int layer);

    /**
     * @note 依据父point(left,right)和layer获取节点的子节点列表
     * @param left
     * @param right
     * @param layer
     * @return
     */
    public List<ComRegion> selectNextByPointsLayer(@Param("left") int left, @Param("right") int right,
            @Param("layer") int layer);

    /**
     * @note 依据父point(left,right)和layer和子point、name获取节点的子节点
     * @param left
     * @param right
     * @param layer
     * @param name
     * @return
     */
    public ComRegion selectNextByPointsLayerName(@Param("left") int left, @Param("right") int right,
            @Param("layer") int layer, @Param("name") String name);

    public List<ComRegion> selectAll();

    public int updateByPrimaryKey(ComRegion record);

}