package com.lt.dailytest.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lt.dailytest.entity.StockTable;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * stock table(StockTable)表数据库访问层
 *
 * @author tong.luo
 * @since 2021-07-12 19:25:09
 */
@Mapper
public interface StockTableMapper extends BaseMapper<StockTable> {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    StockTable queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<StockTable> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param stockTable 实例对象
     * @return 对象列表
     */
    List<StockTable> queryAll(StockTable stockTable);

    /**
     * 新增数据
     *
     * @param stockTable 实例对象
     * @return 影响行数
     */
    int insert(StockTable stockTable);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<StockTable> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<StockTable> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<StockTable> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<StockTable> entities);

    /**
     * 修改数据 根据stocknum
     *
     * @param stockTable 实例对象
     * @return 影响行数
     */
    int update(StockTable stockTable);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}

