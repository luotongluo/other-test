package com.lt.dailytest.dao;

import com.lt.dailytest.entity.StockMainTable;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * stock_main_table(StockMainTable)表数据库访问层
 *
 * @author tong.luo
 * @since 2021-07-14 15:17:49
 */
@Mapper
public interface StockMainTableMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    StockMainTable queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<StockMainTable> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param stockMainTable 实例对象
     * @return 对象列表
     */
    List<StockMainTable> queryAll(StockMainTable stockMainTable);

    /**
     * 新增数据
     *
     * @param stockMainTable 实例对象
     * @return 影响行数
     */
    int insert(StockMainTable stockMainTable);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<StockMainTable> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<StockMainTable> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<StockMainTable> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<StockMainTable> entities);

    /**
     * 修改数据
     *
     * @param stockMainTable 实例对象
     * @return 影响行数
     */
    int update(StockMainTable stockMainTable);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);
    int deleteBystockNums(@Param("StockNums") List<String> StockNums, @Param("formatDate") String formatDate);

}

