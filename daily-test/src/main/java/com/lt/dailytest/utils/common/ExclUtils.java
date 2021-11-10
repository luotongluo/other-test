package com.lt.dailytest.utils.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tong.luo
 * @description ExclUtils
 * @date 2021/5/22 16:26
 */
public class ExclUtils {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    Workbook workbook;

    public void createExcl(String filename, byte[] bytes) throws Exception {
//        if (filename.endsWith(".xls")) {
//             workbook = new HSSFWorkbook(new ByteArrayInputStream(bytes));
//        }
//        if (filename.endsWith(".xlsx")) {
//            workbook = new XSSFWorkbook(new ByteArrayInputStream(bytes));
//        }
        workbook = WorkbookFactory.create(new ByteArrayInputStream(bytes));
    }

    public static void export(String[][] columnNames,
                              List<?> items, String fileName, HttpServletResponse response) {

        JSONArray arr = JSONArray.parseArray(JSON.toJSONString(items));

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map0 = new HashMap<String, Object>();
        map0.put("sheetName", "sheet1");
        list.add(map0);
        for (int i = 0; i < arr.size(); i++) {
            JSONObject obj = arr.getJSONObject(i);
            Map<String, Object> map = (Map<String, Object>) JSON.parseObject(JSON.toJSONString(JSON.parseObject(JSON.toJSONString(obj), items.get(0).getClass())), Map.class);
            list.add(map);
        }

        try {
            fileName = fileName + ".xls";
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ExportToExcelUtil.createWorkBook(list, columnNames).write(baos);
            byte[] content = baos.toByteArray();
            InputStream is = new ByteArrayInputStream(content);
            response.reset();
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("UTF-8"), "ISO8859-1"));
            ServletOutputStream sos = response.getOutputStream();
            BufferedInputStream bis = null;
            BufferedOutputStream bos = null;
            try {
                bis = new BufferedInputStream(is);
                bos = new BufferedOutputStream(sos);
                byte[] buff = new byte[2048];
                int bytesRead;
                while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                    bos.write(buff, 0, bytesRead);
                }
            } catch (Exception e) {
                throw e;
            } finally {
                if (bis != null) {
                    bis.close();
                }
                if (bos != null) {
                    bos.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            //logger.error(e);
        }
    }

    /**
     * 将单元格数据转换为String
     *
     * @param cell
     * @return
     */
    public static String getValue(Cell cell) {
        String cellValue = "";
        if (null != cell) {
            //判断数据类型，防止报错
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_NUMERIC: // 数字
                    DecimalFormat df = new DecimalFormat("0.########");
                    cellValue = df.format(cell.getNumericCellValue());
                    break;

                case Cell.CELL_TYPE_STRING: // 字符串
                    cellValue = cell.getStringCellValue();
                    break;

                case Cell.CELL_TYPE_BOOLEAN: // Boolean
                    cellValue = cell.getBooleanCellValue() + "";
                    break;

                case Cell.CELL_TYPE_FORMULA: // 公式
                    try {
                        cellValue = String.valueOf(cell.getNumericCellValue());
                    } catch (IllegalStateException e) {
                        cellValue = String.valueOf(cell.getRichStringCellValue());
                    }
                    break;

                case Cell.CELL_TYPE_BLANK: // 空值
                    cellValue = "";
                    break;

                case Cell.CELL_TYPE_ERROR: // 故障
                    cellValue = "非法字符";
                    break;

                default:
                    cellValue = "未知类型";
                    break;
            }
        }
        return cellValue;
    }
}
