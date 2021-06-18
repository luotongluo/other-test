package com.lt.dailytest.other;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Color;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.SubstituteLogger;
import org.springframework.boot.test.context.SpringBootTest;

import java.awt.*;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * @author tong.luo
 * @description ExclTest
 * 单元格格式参考地址：https://www.cnblogs.com/yanjie-java/p/8329276.html
 * @date 2021/6/18 11:21
 */
@SpringBootTest
public class ExclTest {
    private final static Logger logger = LoggerFactory.getLogger(ExclTest.class);

    @Test
    public void test1() throws Exception {
        String fileName = "templates/纸片-批量.xlsx";
        //InputStream resourceAsStream = this.getClass().getResourceAsStream(fileName);
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(fileName);
        if (null == resourceAsStream) {
            logger.error("文件获取为空，【{}】", fileName);
        }
        XSSFWorkbook wb = new XSSFWorkbook(resourceAsStream);
        XSSFSheet sheet = wb.getSheetAt(0);
        XSSFRow row = sheet.getRow(1);

        short lastCellNum = row.getLastCellNum();
        for (int i = 0; i < lastCellNum; i++) {
            //根据类型获取excl表格中的数据
            XSSFCell cell = row.getCell(i, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
            int cellType = cell.getCellType();
            if(cellType == Cell.CELL_TYPE_NUMERIC){
                double numericCellValue = cell.getNumericCellValue();
            }
        }
        XSSFCellStyle cellStyle = wb.createCellStyle();
        short blackIndex = IndexedColors.BLACK.getIndex();
        BorderStyle borderStyle = BorderStyle.THIN;
        cellStyle.setBorderBottom(borderStyle);
        cellStyle.setBorderLeft(borderStyle);
        cellStyle.setBorderRight(borderStyle);
        cellStyle.setBorderTop(borderStyle);

        cellStyle.setBottomBorderColor(blackIndex);
        cellStyle.setTopBorderColor(blackIndex);
        cellStyle.setRightBorderColor(blackIndex);
        cellStyle.setLeftBorderColor(blackIndex);

        //在excl文件后面添加一列
        XSSFCell rowCell = row.createCell(lastCellNum);
        rowCell.setCellValue("错误原因");
        rowCell.setCellStyle(cellStyle);
        for (int i = 3; i <= 5; i++) {
             row = sheet.getRow(i - 1);
            XSSFCell rowCell1 = row.createCell(lastCellNum);
            rowCell1.setCellStyle(cellStyle);
            rowCell1.setCellValue("错误原因" + i);
        }
        //输出Excel文件
        FileOutputStream output = new FileOutputStream("d:\\workbook-zhi.xls");
        wb.write(output);
        output.flush();
        output.close();
        wb.close();
        logger.info("write ok ");
    }

    public static void test1111() {
        logger.error("123");
    }
}
