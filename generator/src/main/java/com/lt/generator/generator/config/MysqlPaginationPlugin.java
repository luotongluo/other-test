package com.lt.generator.generator.config;

import org.mybatis.generator.api.GeneratedXmlFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author tong.luo
 * @description MysqlPaginationPlugin
 * @date 2021/1/26 11:35
 */
@Configuration
public class MysqlPaginationPlugin extends PluginAdapter {
    public MysqlPaginationPlugin() {
    }

    @Override
    public boolean validate(List<String> list) {
        return false;
    }

    /**
     * 生成mapper.xml,文件内容会被清空再写入
     */
    @Override
    public boolean sqlMapGenerated(GeneratedXmlFile sqlMap, IntrospectedTable introspectedTable) {
        sqlMap.setMergeable(false);
        return super.sqlMapGenerated(sqlMap, introspectedTable);
    }
}
