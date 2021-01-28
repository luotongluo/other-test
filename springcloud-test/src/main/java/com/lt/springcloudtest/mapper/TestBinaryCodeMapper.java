package com.lt.springcloudtest.mapper;

import com.lt.springcloudtest.bean.TestBinaryCode;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * (TestBinaryCode)表数据库访问层
 *
 * @author tong.luo
 * @since 2021-01-27 16:35:33
 */
//@Mapper
public interface TestBinaryCodeMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TestBinaryCode queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TestBinaryCode> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param testBinaryCode 实例对象
     * @return 对象列表
     */
    List<TestBinaryCode> queryAll(TestBinaryCode testBinaryCode);

    /**
     * 新增数据
     *
     * @param testBinaryCode 实例对象
     * @return 影响行数
     */
    int insert(TestBinaryCode testBinaryCode);

    /**
     * 修改数据
     *
     * @param testBinaryCode 实例对象
     * @return 影响行数
     */
    int update(TestBinaryCode testBinaryCode);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}