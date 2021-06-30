package com.lt.dailytest.other;

import com.lt.dailytest.utils.DateUtils;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author tong.luo
 * @description ExclTest
 * 单元格格式参考地址：https://www.cnblogs.com/yanjie-java/p/8329276.html
 * @date 2021/6/18 11:21
 */
@SpringBootTest
public class ExclTest {
    private final static Logger logger = LoggerFactory.getLogger(ExclTest.class);
    private String writefileName = "d:\\excl-test %s ";
    private ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 10, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>());

    @Test
    public void test1() throws Exception {
        String fileName = "templates/电票.xlsx";
        //InputStream resourceAsStream = this.getClass().getResourceAsStream(fileName);
        Workbook wb = this.dogetExclContent(fileName);
        Sheet sheet = wb.getSheetAt(0);
        Row row = sheet.getRow(4);

        short lastCellNum = row.getLastCellNum();
        for (int i = 0; i < lastCellNum; i++) {
            //根据类型获取excl表格中的数据
            Cell cell = row.getCell(i, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
            if (null == cell) {
                //对于空的单元格的处理方式
                continue;
            }
            CellType cellType = cell.getCellType();
            if (cellType.compareTo(CellType.NUMERIC) == 0) {
                double numericCellValue = cell.getNumericCellValue();
                logger.debug("row :[{}],number value:[{}]", i, numericCellValue);
            } else if (cellType.compareTo(CellType.STRING) == 0) {
                String stringCellValue = cell.getStringCellValue();
                logger.debug("row :[{}],string value:[{}]", i, stringCellValue);
            } else {
                String stringCellValue = cell.getStringCellValue();
                logger.debug("row :[{}],else value:[{}]", i, stringCellValue);

            }
        }
        CellStyle cellStyle = wb.createCellStyle();
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
        Cell rowCell = row.createCell(lastCellNum);
        rowCell.setCellValue("错误原因");
        rowCell.setCellStyle(cellStyle);
        for (int i = 3; i <= 5; i++) {
            row = sheet.getRow(i - 1);
            Cell rowCell1 = row.createCell(lastCellNum);
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

    @Test
    public void test2() throws Exception {
        String fileName = "templates/电票.xlsx";
        Workbook wb = this.dogetExclContent(fileName);
        Sheet sheet = wb.getSheetAt(0);
        //删除的是下面一行的数据 行索引从0开始，比实际行数少1,这是删除第四行
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(3); // 3 的下一个4是真实行
        integers.add(5);
        integers.add(8);
        for (Integer integer : integers) {
            Row row = sheet.getRow(integer);
            sheet.removeRow(row);
        }
        //删除 exclindex的行数  integers index 需要删除的索引 如果要删除第四行 则 integer中的值为3
        int rowNum = sheet.getLastRowNum();
        for (int i = 0; i < integers.size(); i++) {
            Integer integer = integers.get(i);
            Integer num = integer + 1 - i;
            logger.info("i == [{}], num : == [{}],integer val : == [{}]",i,integer);
            sheet.shiftRows(num, rowNum, -1);
        }
        String formateDate = DateUtils.formatDate(new Date(), DateUtils.yyyyMMddHHmmss);
        //输出Excel文件
        FileOutputStream output = new FileOutputStream(String.format(writefileName, "电票") + formateDate + ".xls");
        wb.write(output);

        output.flush();
        output.close();
        wb.close();
        logger.info("write ok ");
    }

    private Workbook dogetExclContent(String fileName) throws IOException {
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(fileName);
        if (null == resourceAsStream) {
            logger.error("文件获取为空，【{}】", fileName);
        }

        Workbook wb = null; //new XSSFWorkbook(resourceAsStream);
        if (fileName.endsWith(".xls")) {
            wb = new HSSFWorkbook(resourceAsStream);
        }
        if (fileName.endsWith(".xlsx")) {
            wb = new XSSFWorkbook(resourceAsStream);
        }
        return wb;
    }

    public void readfile() throws Exception {
        String fileName = "";
        File file = new File("");
        if (fileName.endsWith(".xls")) {
            Workbook workbook = new HSSFWorkbook(new ByteArrayInputStream(FileUtils.readFileToByteArray(file)));
        }
        if (fileName.endsWith(".xlsx")) {
            Workbook workbook = new XSSFWorkbook(new ByteArrayInputStream(FileUtils.readFileToByteArray(file)));
        }

    }

    public static void test1111() {
        logger.error("123");
        new Runnable() {
            @Override
            public void run() {

            }
        }.run();
        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();
    }
}
