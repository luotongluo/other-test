package com.lt.springcloudtest.excl;


import com.lt.springcloudtest.bean.TestBean;
import com.lt.springcloudtest.utils.TimeUtils;
import org.apache.poi.hssf.record.ExtendedFormatRecord;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Color;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author tong.luo
 * @description TestExportExcl
 * @date 2021/2/22 15:23
 */
public class TestExportExcl {
    public static void main(String[] args) {
        try {
            long start = System.currentTimeMillis();
//            byte[] bytes = getexclBytes();
            String name = "test1-奥术大师大多所.xls";
            byte[] bytes = getdownByte(name);
            FileOutputStream fileOutputStream = new FileOutputStream(name);
            fileOutputStream.write(bytes);
            long endtime = System.currentTimeMillis();
            System.out.println(endtime - start);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static byte[] getdownByte(String name) throws Exception{
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(name);

        //初始化单元格格式对象
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        //设置对齐方式
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        //设置垂直对齐方式
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setFillBackgroundColor(IndexedColors.PINK.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        //开启自动换行
        cellStyle.setWrapText(true);

        HSSFRow row = sheet.createRow(0);
        //设置行高
        row.setHeight((short) 400);
        row.createCell(0).setCellValue("第一列");
        row.createCell(1).setCellValue("第2列");
        row.createCell(2).setCellValue("第3列");
        row.createCell(3).setCellValue("第4列");
        row.createCell(4).setCellValue("第5列");

        for (int i = 0; i < 4; i++) {
            HSSFRow row1 = sheet.createRow(i + 1);
            row1.createCell(0).setCellValue(i + "--123--" + UUID.randomUUID());
            row1.createCell(1).setCellValue(i + "--123--" + UUID.randomUUID());
            row1.createCell(2).setCellValue(i + "--123--" + UUID.randomUUID());
            row1.createCell(3).setCellValue(i + "--123--" + UUID.randomUUID());
            row1.createCell(4).setCellValue(i + "--123--" + TimeUtils.getDate(System.currentTimeMillis(),TimeUtils.FORMAT_DEFAULT_TIMESTAMP));
        }
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        workbook.write(os);
        return os.toByteArray();
    }

    private static byte[] getexclBytes() throws Exception {
        HSSFWorkbook workbook = new HSSFWorkbook();
        String fileName = "test-导出";
        HSSFSheet sheet = workbook.createSheet(fileName);
        //设置默认行宽
        sheet.setDefaultColumnWidth(20);
        sheet.autoSizeColumn(0);
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 15));
        HSSFCellStyle cellStylehook = workbook.createCellStyle();
        cellStylehook.setAlignment(HorizontalAlignment.CENTER);
        cellStylehook.setVerticalAlignment(VerticalAlignment.CENTER);
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 16);
        cellStylehook.setFont(font);

        HSSFRow row = sheet.createRow(0);
        row.setHeightInPoints(30);
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("test-sell 月汇总信息查询表 asdasd!@#$%^^#@\"{}|");
        HSSFCellStyle cellStyle = cell.getCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cell.setCellStyle(cellStyle);

        HSSFRow row1 = sheet.createRow(1);
        HSSFCell cell1 = row1.createCell(0);
        cell1.getCellStyle().setAlignment(HorizontalAlignment.RIGHT);
//        cell1.setCellValue("制表日期：" + TimeUtils.getDate(System.currentTimeMillis(),TimeUtils.FORMAT_DEFAULT_TIMESTAMP));

        HSSFRow row2 = sheet.createRow(2);
        HSSFCell cell2 = row2.createCell(2);
        cell2.getCellStyle().setAlignment(HorizontalAlignment.LEFT);
        cell2.setCellValue("制表日期：tests");

        HSSFRow row3 = sheet.createRow(3);
        row3.createCell(0).setCellValue("姓名");
        row3.createCell(1).setCellValue("年龄");
        row3.createCell(2).setCellValue("序号");

        List<TestBean> testBeanList = new ArrayList<>();
        TestBean testBean = new TestBean();
        testBean.setAge(12);
        testBean.setName("name12");
        testBeanList.add(testBean);
        testBean = new TestBean();
        testBean.setAge(13);
        testBean.setName("name13");
        testBeanList.add(testBean);

        for (int a = 0; a < testBeanList.size(); a++) {
            HSSFRow row4 = sheet.createRow(4 + a);
            row4.createCell(0).setCellValue(4 + a);
            TestBean bean = testBeanList.get(a);
            row4.createCell(0).setCellValue(bean.getName());
            row4.createCell(1).setCellValue(bean.getAge());
            row4.createCell(2).setCellValue(a + 1);

        }


        ByteArrayOutputStream os = new ByteArrayOutputStream();
        workbook.write(os);
        return os.toByteArray();
    }
}
