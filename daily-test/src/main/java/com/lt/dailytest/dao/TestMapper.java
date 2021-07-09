package com.lt.dailytest.dao;


import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author tong.luo
 * @description TestMapper
 * @date 2021/6/19 12:02
 */
@Mapper
public interface TestMapper {
    public List<Object> selectAll();
}
