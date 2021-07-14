package com.lt.dailytest.dao;

import com.lt.dailytest.entity.StockBuyTable;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * stock_buy_table(StockBuyTable)表数据库访问层
 *
 * @author tong.luo
 * @since 2021-07-14 15:17:47
 */
@Mapper
public interface StockBuyTableMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    StockBuyTable queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<StockBuyTable> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param stockBuyTable 实例对象
     * @return 对象列表
     */
    List<StockBuyTable> queryAll(StockBuyTable stockBuyTable);

    /**
     * 新增数据
     *
     * @param stockBuyTable 实例对象
     * @return 影响行数
     */
    int insert(StockBuyTable stockBuyTable);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<StockBuyTable> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<StockBuyTable> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<StockBuyTable> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<StockBuyTable> entities);

    /**
     * 修改数据
     *
     * @param stockBuyTable 实例对象
     * @return 影响行数
     */
    int update(StockBuyTable stockBuyTable);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    void deleteBystockNums(@Param("StockNums") List<String> collect,@Param("formatDate") String formatDate);
}

