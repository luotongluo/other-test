package com.lt.conbase.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author tong.luo
 * @description TestMapper
 * @date 2021/6/18 20:46
 */
@Mapper
public interface TestMapper {
    List<Object> selectAll();

}
