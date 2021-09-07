//package com.lt.dailytest.utils;
//
//import org.apache.commons.lang3.StringUtils;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.CellStyle;
//import org.apache.poi.ss.usermodel.Font;
//import org.apache.poi.ss.usermodel.IndexedColors;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.ss.util.CellRangeAddress;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//public class ExportToExcelUtil {
//
//    /**
//     * 创建excel文档，
//     *
//     * @param list        数据
//     * @param columnNames excel的列名
//     */
//    public static Workbook createWorkBook(List<Map<String, Object>> list, String[][] columnNames) {
//        // 创建excel工作簿
//        Workbook wb = new HSSFWorkbook();
//        // 创建第一个sheet（页），并命名
//        Sheet sheet = wb.createSheet(list.get(0).get("sheetName").toString());
//        // 手动设置列宽。第一个参数表示要为第几列设；，第二个参数表示列的宽度，n为列高的像素数。
//        for (int i = 1; i < columnNames.length + 1; i++) {
//            sheet.setColumnWidth((short) i, (short) (35.7 * 150));
//        }
//
//        // 创建第一行
//        Row row = sheet.createRow(0);
//
//        // 创建两种单元格格式
//        CellStyle cs = wb.createCellStyle();
//        CellStyle cs2 = wb.createCellStyle();
//
//        // 创建两种字体
//        Font f = wb.createFont();
//        Font f2 = wb.createFont();
//
//        // 创建第一种字体样式（用于列名）
//        f.setFontHeightInPoints((short) 10);
//        f.setColor(IndexedColors.BLACK.getIndex());
//        f.setBoldweight(Font.BOLDWEIGHT_BOLD);
//
//        // 创建第二种字体样式（用于值）
//        f2.setFontHeightInPoints((short) 10);
//        f2.setColor(IndexedColors.BLACK.getIndex());
//
////        Font f3=wb.createFont();
////        f3.setFontHeightInPoints((short) 10);
////        f3.setColor(IndexedColors.RED.getIndex());
//
//        // 设置第一种单元格的样式（用于列名）
//        cs.setFont(f);
//        cs.setBorderLeft(CellStyle.BORDER_THIN);
//        cs.setBorderRight(CellStyle.BORDER_THIN);
//        cs.setBorderTop(CellStyle.BORDER_THIN);
//        cs.setBorderBottom(CellStyle.BORDER_THIN);
//        cs.setAlignment(CellStyle.ALIGN_CENTER);
//
//        // 设置第二种单元格的样式（用于值）
//        cs2.setFont(f2);
//        cs2.setBorderLeft(CellStyle.BORDER_THIN);
//        cs2.setBorderRight(CellStyle.BORDER_THIN);
//        cs2.setBorderTop(CellStyle.BORDER_THIN);
//        cs2.setBorderBottom(CellStyle.BORDER_THIN);
//        cs2.setAlignment(CellStyle.ALIGN_CENTER);
//        //设置列名
//        Cell cell0 = row.createCell(0);
//        cell0.setCellValue("序号");
//        cell0.setCellStyle(cs2);
//
//        for (int i = 1; i < columnNames.length + 1; i++) {
//            Cell cell = row.createCell(i);
//            cell.setCellValue(columnNames[i - 1][1]);
//            cell.setCellStyle(cs2);
//        }
//        //设置每行每列的值
//        for (short i = 1; i < list.size(); i++) {
//            // Row 行,Cell 方格 , Row 和 Cell 都是从0开始计数的
//            // 创建一行，在页sheet上
//            Row row1 = sheet.createRow(i);
//            // 在row行上创建一个方格
//
//            Cell cell1 = row1.createCell(0);
//            cell1.setCellValue(i);
//            cell1.setCellStyle(cs2);
//
//            for (short j = 1; j < columnNames.length + 1; j++) {
//                Cell cell = row1.createCell(j);
//                cell.setCellValue(list.get(i).get(columnNames[j - 1][0]) == null ? " " : list.get(i).get(columnNames[j - 1][0]).toString());
//                cell.setCellStyle(cs2);
//            }
//        }
//        return wb;
//    }
//
//    /**
//     * 创建excel文档，
//     *
//     * @param list        数据
//     * @param paraMap     参数（可选）
//     *                    key           value
//     *                    describe      描述内容
//     * @param columnNames excel的列名
//     */
//    public static Workbook createWorkBook(List<Map<String, Object>> list, String[][] columnNames, Map<String, String> paraMap, ArrayList<BigDecimal> bigDecimallist) {
//        // 创建excel工作簿
//        Workbook wb = new HSSFWorkbook();
//        // 创建第一个sheet（页），并命名
//        Sheet sheet = wb.createSheet(list.get(0).get("sheetName").toString());
//        // 手动设置列宽。第一个参数表示要为第几列设；，第二个参数表示列的宽度，n为列高的像素数。
//        for (int i = 1; i < columnNames.length + 1; i++) {
//            sheet.setColumnWidth((short) i, (short) (35.7 * 150));
//        }
//        // 创建第一行，标注
//        Row rowFirst = sheet.createRow(0);
//        // 创建第二行，列名
//        Row row = sheet.createRow(1);
//
//        // 创建三种单元格格式
//        CellStyle cs = wb.createCellStyle();
//        CellStyle cs2 = wb.createCellStyle();
//        CellStyle cs3 = wb.createCellStyle();
//
//        // 创建两种字体
//        Font f = wb.createFont();
//        Font f2 = wb.createFont();
//
//        // 创建第一种字体样式（用于列名）
//        f.setFontHeightInPoints((short) 10);
//        f.setColor(IndexedColors.BLACK.getIndex());
//        f.setBoldweight(Font.BOLDWEIGHT_BOLD);
//
//        // 创建第二种字体样式（用于值）
//        f2.setFontHeightInPoints((short) 10);
//        f2.setColor(IndexedColors.BLACK.getIndex());
//
//        // 设置第一种单元格的样式（用于列名）
//        cs.setFont(f);
//        cs.setBorderLeft(CellStyle.BORDER_THIN);
//        cs.setBorderRight(CellStyle.BORDER_THIN);
//        cs.setBorderTop(CellStyle.BORDER_THIN);
//        cs.setBorderBottom(CellStyle.BORDER_THIN);
//        cs.setAlignment(CellStyle.ALIGN_CENTER);
//
//        // 设置第二种单元格的样式（用于值）
//        cs2.setFont(f2);
//        cs2.setBorderLeft(CellStyle.BORDER_THIN);
//        cs2.setBorderRight(CellStyle.BORDER_THIN);
//        cs2.setBorderTop(CellStyle.BORDER_THIN);
//        cs2.setBorderBottom(CellStyle.BORDER_THIN);
//        cs2.setAlignment(CellStyle.ALIGN_CENTER);
//
//        // 设置第三种单元格的样式
//        cs3.setFont(f2);
//        cs3.setBorderLeft(CellStyle.BORDER_THIN);
//        cs3.setBorderRight(CellStyle.BORDER_THIN);
//        cs3.setBorderTop(CellStyle.BORDER_THIN);
//        cs3.setBorderBottom(CellStyle.BORDER_THIN);
//        cs3.setAlignment(CellStyle.VERTICAL_CENTER);
//        //设置列名
//        Cell cell0 = row.createCell(0);
//        cell0.setCellValue("序号");
//        cell0.setCellStyle(cs2);
//
//        for (int i = 1; i < columnNames.length + 1; i++) {
//            Cell cell = row.createCell(i);
//            cell.setCellValue(columnNames[i - 1][1]);
//            cell.setCellStyle(cs2);
//        }
//        if (paraMap != null && StringUtils.isNotEmpty(paraMap.get("tableHead"))) {
//            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, columnNames.length));
//            // 在row行上创建一个方格
//            Cell cellDescribe = rowFirst.createCell(0);
//            cellDescribe.setCellValue(paraMap.get("tableHead"));
//            cellDescribe.setCellStyle(cs3);
//        }
//        //设置每行每列的值
//        for (short i = 1; i <= list.size(); i++) {
//            // Row 行,Cell 方格 , Row 和 Cell 都是从0开始计数的
//            // 创建一行，在页sheet上
//            Row row1 = sheet.createRow(i + 1);
//            // 在row行上创建一个方格
//
//            Cell cell1 = row1.createCell(0);
//            cell1.setCellValue(i);
//            cell1.setCellStyle(cs2);
//            //合计行
//            if (i == list.size()) {
//                cell1.setCellValue("合计");
//                cell1.setCellStyle(cs2);
//                for (short j = 1; j < columnNames.length + 1; j++) {
//                    //合计列
//                    Cell cell = row1.createCell(j);
//                    cell.setCellStyle(cs2);
//                    if (j >= 4) {
//                        cell.setCellValue(bigDecimallist.get(j - 4).toString());
//                    }
//                }
//            } else {
//                //正常行数
//                for (short j = 1; j < columnNames.length + 1; j++) {
//                    Cell cell = row1.createCell(j);
//                    cell.setCellValue(list.get(i).get(columnNames[j - 1][0]) == null ? " " : list.get(i).get(columnNames[j - 1][0]).toString());
//                    cell.setCellStyle(cs2);
//                }
//            }
//        }
//        return wb;
//    }
//
//    /**
//     * 生成表格
//     *
//     * @param list
//     * @param columnNames
//     * @param rowNum
//     * @param stringList
//     * @return
//     */
//    public static Workbook createWorkBook(List<Map<String, Object>> list, String[][] columnNames, int rowNum, List<String> stringList) {
//        // 创建excel工作簿
//        Workbook wb = new HSSFWorkbook();
//        // 创建第一个sheet（页），并命名
//        Sheet sheet = wb.createSheet(list.get(0).get("sheetName").toString());
//        // 手动设置列宽。第一个参数表示要为第几列设；，第二个参数表示列的宽度，n为列高的像素数。
//        for (int i = 1; i < columnNames.length + 1; i++) {
//            sheet.setColumnWidth((short) i, (short) (35.7 * 150));
//        }
//
//        // 创建三种单元格格式
//        CellStyle cs = wb.createCellStyle();
//        CellStyle cs2 = wb.createCellStyle();
//        CellStyle cs3 = wb.createCellStyle();
//
//        // 创建两种字体
//        Font f = wb.createFont();
//        Font f2 = wb.createFont();
//
//        // 创建第一种字体样式（用于列名）
//        f.setFontHeightInPoints((short) 10);
//        f.setColor(IndexedColors.BLACK.getIndex());
//        f.setBoldweight(Font.BOLDWEIGHT_BOLD);
//
//        // 创建第二种字体样式（用于值）
//        f2.setFontHeightInPoints((short) 10);
//        f2.setColor(IndexedColors.BLACK.getIndex());
//
//        // 设置第一种单元格的样式（用于列名）
//        cs.setFont(f);
//        cs.setBorderLeft(CellStyle.BORDER_THIN);
//        cs.setBorderRight(CellStyle.BORDER_THIN);
//        cs.setBorderTop(CellStyle.BORDER_THIN);
//        cs.setBorderBottom(CellStyle.BORDER_THIN);
//        cs.setAlignment(CellStyle.ALIGN_CENTER);
//
//        // 设置第二种单元格的样式（用于值）
//        cs2.setFont(f2);
//        cs2.setBorderLeft(CellStyle.BORDER_THIN);
//        cs2.setBorderRight(CellStyle.BORDER_THIN);
//        cs2.setBorderTop(CellStyle.BORDER_THIN);
//        cs2.setBorderBottom(CellStyle.BORDER_THIN);
//        cs2.setAlignment(CellStyle.ALIGN_CENTER);
//
//        // 设置第三种单元格的样式
//        cs3.setFont(f2);
//        cs3.setAlignment(CellStyle.VERTICAL_CENTER);
//
//        for (int i = 0; i < rowNum; i++) {
//            sheet.addMergedRegion(new CellRangeAddress(i, i, 0, columnNames.length));
//            // 在row行上创建一个方格
//            Row row = sheet.createRow(i);
//            Cell cellDescribe = row.createCell(0);
//            if (i >= stringList.size()) {
//                cellDescribe.setCellValue("");
//            } else {
//                cellDescribe.setCellValue(stringList.get(i));
//            }
//            cellDescribe.setCellStyle(cs3);
//        }
//
//        Row row = sheet.createRow(rowNum);
//        //设置列名
//        Cell cell0 = row.createCell(0);
//        cell0.setCellValue("序号");
//        cell0.setCellStyle(cs2);
//
//        for (int i = 1; i < columnNames.length + 1; i++) {
//            Cell cell = row.createCell(i);
//            cell.setCellValue(columnNames[i - 1][1]);
//            cell.setCellStyle(cs2);
//        }
//
//        //设置每行每列的值
//        for (short i = 1; i < list.size(); i++) {
//            // Row 行,Cell 方格 , Row 和 Cell 都是从0开始计数的
//            // 创建一行，在页sheet上
//            Row row1 = sheet.createRow(i + rowNum);
//            // 在row行上创建一个方格
//
//            Cell cell1 = row1.createCell(0);
//            cell1.setCellValue(i);
//            cell1.setCellStyle(cs2);
//
//            for (short j = 1; j < columnNames.length + 1; j++) {
//                Cell cell = row1.createCell(j);
//                cell.setCellValue(list.get(i).get(columnNames[j - 1][0]) == null ? " " : list.get(i).get(columnNames[j - 1][0]).toString());
//                cell.setCellStyle(cs2);
//            }
//        }
//        return wb;
//    }
//
//    /**
//     * 生成表格
//     *
//     * @param list
//     * @param columnNames
//     * @param stringList
//     * @return
//     */
//    public static Workbook createTaxRateWorkBook(List<Map<String, Object>> list, String[][] columnNames, List<String> stringList) {
//        // 创建excel工作簿
//        Workbook wb = new HSSFWorkbook();
//        // 创建第一个sheet（页），并命名
//        Sheet sheet = wb.createSheet(list.get(0).get("sheetName").toString());
//        // 手动设置列宽。第一个参数表示要为第几列设；，第二个参数表示列的宽度，n为列高的像素数。
//        for (int i = 0; i < columnNames.length + 1; i++) {
//            sheet.setColumnWidth((short) i, (short) (35.7 * 150));
//        }
//
//        // 创建三种单元格格式
//        CellStyle cs = wb.createCellStyle();
//        CellStyle cs2 = wb.createCellStyle();
//        CellStyle cs3 = wb.createCellStyle();
//
//        // 创建两种字体
//        Font f = wb.createFont();
//        Font f2 = wb.createFont();
//
//        // 创建第一种字体样式（用于列名）
//        f.setFontHeightInPoints((short) 10);
//        f.setColor(IndexedColors.BLACK.getIndex());
//        f.setBoldweight(Font.BOLDWEIGHT_BOLD);
//
//        // 创建第二种字体样式（用于值）
//        f2.setFontHeightInPoints((short) 10);
//        f2.setColor(IndexedColors.BLACK.getIndex());
//
//        // 设置第一种单元格的样式（用于列名）
//        cs.setFont(f);
//        cs.setBorderLeft(CellStyle.BORDER_THIN);
//        cs.setBorderRight(CellStyle.BORDER_THIN);
//        cs.setBorderTop(CellStyle.BORDER_THIN);
//        cs.setBorderBottom(CellStyle.BORDER_THIN);
//        cs.setAlignment(CellStyle.ALIGN_CENTER);
//
//        // 设置第二种单元格的样式（用于值）
//        cs2.setFont(f2);
//        cs2.setBorderLeft(CellStyle.BORDER_THIN);
//        cs2.setBorderRight(CellStyle.BORDER_THIN);
//        cs2.setBorderTop(CellStyle.BORDER_THIN);
//        cs2.setBorderBottom(CellStyle.BORDER_THIN);
//        cs2.setAlignment(CellStyle.ALIGN_CENTER);
//
//        // 设置第三种单元格的样式
//        cs3.setFont(f2);
//        cs3.setAlignment(CellStyle.VERTICAL_CENTER);
//
//        int rowNum = stringList.size() + 1;
//
//        for (int i = 0; i < rowNum; i++) {
//            sheet.addMergedRegion(new CellRangeAddress(i, i, 0, columnNames.length));
//            // 在row行上创建一个方格
//            Row row = sheet.createRow(i);
//            Cell cellDescribe = row.createCell(0);
//            if (i >= stringList.size()) {
//                cellDescribe.setCellValue("");
//            } else {
//                cellDescribe.setCellValue(stringList.get(i));
//            }
//            cellDescribe.setCellStyle(cs3);
//        }
//
//        sheet.addMergedRegion(new CellRangeAddress(8, 8, 0, columnNames.length));
//        Row row4 = sheet.createRow(8);
//        Cell cellDescribe4 = row4.createCell(0);
//        cellDescribe4.setCellValue("☆销项情况☆");
//        cellDescribe4.setCellStyle(cs3);
//
//        sheet.addMergedRegion(new CellRangeAddress(9, 9, 0, columnNames.length));
//        Row row5 = sheet.createRow(9);
//        Cell cellDescribe5 = row5.createCell(0);
//        cellDescribe5.setCellValue("金额单位：元");
//        cellDescribe5.setCellStyle(cs3);
//
//        rowNum = rowNum + 2;
//
//        Row row = sheet.createRow(rowNum);
//        //设置列名
//        Cell cell0 = row.createCell(0);
//        cell0.setCellValue("序号");
//        cell0.setCellStyle(cs2);
//
//        for (int i = 1; i < columnNames.length + 1; i++) {
//            Cell cell = row.createCell(i);
//            cell.setCellValue(columnNames[i - 1][1]);
//            cell.setCellStyle(cs2);
//        }
//
//        //设置每行每列的值
//        for (short i = 1; i < list.size(); i++) {
//            // Row 行,Cell 方格 , Row 和 Cell 都是从0开始计数的
//            // 创建一行，在页sheet上
//            Row row1 = sheet.createRow(i + rowNum);
//            // 在row行上创建一个方格
//
//            Cell cell1 = row1.createCell(0);
//            cell1.setCellValue(i);
//            cell1.setCellStyle(cs2);
//
//            for (short j = 1; j < columnNames.length + 1; j++) {
//                Cell cell = row1.createCell(j);
//                cell.setCellValue(list.get(i).get(columnNames[j - 1][0]) == null ? " " : list.get(i).get(columnNames[j - 1][0]).toString());
//                cell.setCellStyle(cs2);
//            }
//        }
//        return wb;
//    }
//
//
//    /**
//     * 生成表格
//     *
//     * @param list
//     * @param columnNames
//     * @param rowNum
//     * @param stringList
//     * @return
//     */
//    public static Workbook createWorkBookInvoiceData(List<Map<String, Object>> list, String[][] columnNames, int rowNum, List<String> stringList) {
//        // 创建excel工作簿
//        Workbook wb = new HSSFWorkbook();
//        // 创建第一个sheet（页），并命名
//        Sheet sheet = wb.createSheet(list.get(0).get("sheetName").toString());
//        // 手动设置列宽。第一个参数表示要为第几列设；，第二个参数表示列的宽度，n为列高的像素数。
//        for (int i = 1; i < columnNames.length + 1; i++) {
//            sheet.setColumnWidth((short) i, (short) (35.7 * 150));
//        }
//
//        // 创建三种单元格格式
//        CellStyle cs = wb.createCellStyle();
//        CellStyle cs2 = wb.createCellStyle();
//        CellStyle cs3 = wb.createCellStyle();
//        CellStyle cs4 = wb.createCellStyle();
//        CellStyle cs5 = wb.createCellStyle();
//
//
//        // 创建两种字体
//        Font f = wb.createFont();
//        Font f2 = wb.createFont();
//        Font f3 = wb.createFont();
//        // 创建第一种字体样式（用于列名）
//        f.setFontName("宋体");
//        f.setFontHeightInPoints((short) 9);
//        f.setColor(IndexedColors.BLACK.getIndex());
//        f.setBoldweight(Font.BOLDWEIGHT_BOLD);
//
//        // 创建第二种字体样式（用于值）
//        f2.setFontHeightInPoints((short) 10);
//        f2.setColor(IndexedColors.BLACK.getIndex());
//        //表名
//        f3.setFontHeightInPoints((short) 16);
//        f3.setBoldweight(Font.BOLDWEIGHT_BOLD);
//
//        // 设置第一种单元格的样式（用于列名）
//        cs.setFont(f);
//        cs.setBorderLeft(CellStyle.BORDER_THIN);
//        cs.setBorderRight(CellStyle.BORDER_THIN);
//        cs.setBorderTop(CellStyle.BORDER_THIN);
//        cs.setBorderBottom(CellStyle.BORDER_THIN);
//        cs.setAlignment(CellStyle.ALIGN_CENTER);
//
//        //列名
//        cs5.setFont(f);
//        cs5.setAlignment(CellStyle.ALIGN_CENTER);
////        cs5.setFillBackgroundColor(IndexedColors.GREY_40_PERCENT.getIndex());
//        cs5.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
//        cs5.setFillPattern(cs5.SOLID_FOREGROUND);
//
//        // 设置第二种单元格的样式（用于值）
//        cs2.setFont(f2);
//        cs2.setAlignment(CellStyle.ALIGN_CENTER);
//        //表名
//        cs4.setAlignment(CellStyle.ALIGN_CENTER);
//        cs4.setFont(f3);
//        // 设置第三种单元格的样式
//        cs3.setFont(f2);
//        cs3.setAlignment(CellStyle.VERTICAL_CENTER);
//
//
//        for (int i = 0; i < rowNum; i++) {
//            sheet.addMergedRegion(new CellRangeAddress(i, i, 0, columnNames.length));
//            // 在row行上创建一个方格
//            Row row = sheet.createRow(i);
//            Cell cellDescribe = row.createCell(0);
//            if (i >= stringList.size()) {
//                cellDescribe.setCellValue("");
//            } else {
//                cellDescribe.setCellValue(stringList.get(i));
//            }
//            cellDescribe.setCellStyle(cs3);
//            if (i == 0) {
//                cellDescribe.setCellStyle(cs4);
//            }
//        }
//
//        Row row = sheet.createRow(rowNum);
//        //设置列名
//        Cell cell0 = row.createCell(0);
//        cell0.setCellValue("序号");
//        cell0.setCellStyle(cs5);
//
//        for (int i = 1; i < columnNames.length + 1; i++) {
//            Cell cell = row.createCell(i);
//            cell.setCellValue(columnNames[i - 1][1]);
//            cell.setCellStyle(cs5);
//        }
//
//        //设置每行每列的值
//        for (int i = 1; i < list.size(); i++) {
//            // Row 行,Cell 方格 , Row 和 Cell 都是从0开始计数的
//            // 创建一行，在页sheet上
//            Row row1 = sheet.createRow(i + rowNum);
//            // 在row行上创建一个方格
//
//            Cell cell1 = row1.createCell(0);
//            cell1.setCellValue(i);
//            cell1.setCellStyle(cs2);
//
//            for (short j = 1; j < columnNames.length + 1; j++) {
//                Cell cell = row1.createCell(j);
//                if (list.get(i).get(columnNames[j - 1][0]) instanceof BigDecimal) {
//                    cell.setCellValue(list.get(i).get(columnNames[j - 1][0]) == null ? 0 : ((BigDecimal) list.get(i).get(columnNames[j - 1][0])).doubleValue());
//                    cell.setCellStyle(cs2);
//                } else {
//                    cell.setCellValue(list.get(i).get(columnNames[j - 1][0]) == null ? " " : list.get(i).get(columnNames[j - 1][0]).toString());
//                    cell.setCellStyle(cs2);
//                }
//
//            }
//        }
//        return wb;
//    }
//}