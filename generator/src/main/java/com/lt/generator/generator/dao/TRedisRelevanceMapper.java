package com.lt.generator.generator.dao;

import com.lt.generator.generator.entity.TRedisRelevance;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 赋码和红字申请日志表(TRedisRelevance)表数据库访问层
 *
 * @author tong.luo
 * @since 2021-02-01 10:09:04
 */
public interface TRedisRelevanceMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TRedisRelevance queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<TRedisRelevance> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param tRedisRelevance 实例对象
     * @return 对象列表
     */
    List<TRedisRelevance> queryAll(TRedisRelevance tRedisRelevance);

    /**
     * 新增数据
     *
     * @param tRedisRelevance 实例对象
     * @return 影响行数
     */
    int insert(TRedisRelevance tRedisRelevance);

    /**
     * 修改数据
     *
     * @param tRedisRelevance 实例对象
     * @return 影响行数
     */
    int update(TRedisRelevance tRedisRelevance);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}