package com.lt.generator.generator.config;

import org.mybatis.generator.api.FullyQualifiedTable;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.internal.DefaultCommentGenerator;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * @author tong.luo
 * @description NewbatisGeneratorConfig
 * @date 2021/1/26 11:06
 */
//@Configuration
public class NewbatisGenerator extends DefaultCommentGenerator {
    private Properties properties;
    private Properties systemPro;
    private boolean suppressDate;
    private boolean suppressAllComments;
    private String currentDateStr;

    public NewbatisGenerator() {
        super();
        this.properties = new Properties();
        this.systemPro = System.getProperties();
        this.suppressDate = false;
        this.suppressAllComments = false;
        this.currentDateStr = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

    /**
     * 对类到的注释
     *
     * @param topLevelClass
     * @param introspectedTable
     */
    @Override
    public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        topLevelClass.addJavaDocLine("/**");
        topLevelClass.addJavaDocLine("* 这是java 自动生成的 bean 实体");
        FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
        topLevelClass.addJavaDocLine("对应的数据表为:" + table);
        String remarks = introspectedTable.getRemarks();
        if (null != remarks && remarks.length() > 0) {
            topLevelClass.addJavaDocLine("数据库表注释:" + remarks);
        }
        topLevelClass.addJavaDocLine("* @author " + systemPro.getProperty("user.name"));
        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        topLevelClass.addJavaDocLine("* @date " + format);

        topLevelClass.addJavaDocLine("*/");

    }

    /**
     * 在实体字段中增加注释
     *
     * @param field
     * @param introspectedTable
     * @param introspectedColumn
     */
    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        if (suppressAllComments) {
            return;
        }
        field.addJavaDocLine("/**");
        field.addJavaDocLine("* " + introspectedColumn.getRemarks().replaceAll("\n", ""));
        field.addJavaDocLine("*/");
    }

    public static void main(String[] args) {
        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }
}
