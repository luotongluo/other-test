package excl;

import com.lt.springcloudtest.utils.ExclUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.assertj.core.util.Strings;
import org.checkerframework.checker.units.qual.A;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author tong.luo
 * @description ExportExclFrom
 * @date 2021/1/27 11:19
 */
public class ExportExclFrom {
    public static void main(String[] args) throws Exception {
        ExclUtils exclUtils = new ExclUtils();
        String[] heardList = exclUtils.getHeardList() == null ? new String[64] : exclUtils.getHeardList();

        exclUtils.setTitle("test-title");
        List<Map> data = exclUtils.getData() == null ? new ArrayList<>() : exclUtils.getData();
        String[] heardKey = exclUtils.getHeardKey() == null ? new String[64] : exclUtils.getHeardKey();
        int loop = 10;
        for (int a = 0; a < loop; a++) {
            HashMap<String, Object> hashMap = new HashMap<>(16);
            heardList[a] = "loop" + a + loop + "headerList";
            heardKey[a] = "loop" + a + loop;
            hashMap.put(heardKey[a], heardKey[a]);
            data.add(hashMap);
        }
        exclUtils.setHeardKey(heardKey);
        exclUtils.setHeardList(heardList);
        exclUtils.setData(data);
        byte[] bytes = exportExcl(exclUtils);
        FileOutputStream outputStream = new FileOutputStream("bytes.xls", false);
        outputStream.write(bytes);
    }

    public static byte[] exportExcl(ExclUtils exclUtils) throws Exception {

        if (null == exclUtils) {
            exclUtils = new ExclUtils();
        }
        checkConfig(exclUtils);
        //创建工作簿
        HSSFWorkbook wb = new HSSFWorkbook();
        //创建工作表
        HSSFSheet wbSheet = wb.createSheet(exclUtils.getSheetName());
        //设置默认行宽
        wbSheet.setDefaultColumnWidth(20);

        // 标题样式（加粗，垂直居中）
        HSSFCellStyle cellStyle = wb.createCellStyle();
        //水平居中
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        //垂直居中
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        HSSFFont fontStyle = wb.createFont();
//        fontStyle.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        fontStyle.setBold(true);   //加粗
        fontStyle.setFontHeightInPoints((short) 16);  //设置标题字体大小
        cellStyle.setFont(fontStyle);

        //在第0行创建rows  (表标题)
        HSSFRow title = wbSheet.createRow((int) 0);
        title.setHeightInPoints(30);//行高
        HSSFCell cellValue = title.createCell(0);
        cellValue.setCellValue(exclUtils.getTitle());
        cellValue.setCellStyle(cellStyle);
        String[] heardList = exclUtils.getHeardList();
        wbSheet.addMergedRegion(new CellRangeAddress(0, 0, 0, (heardList.length - 1)));
        //设置表头样式，表头居中
        HSSFCellStyle style = wb.createCellStyle();
        //设置单元格样式
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        //设置字体
        HSSFFont font = wb.createFont();
        font.setFontHeightInPoints((short) exclUtils.getFontSize());
        style.setFont(font);
        //在第1行创建rows
        HSSFRow row = wbSheet.createRow((int) 1);
        //设置列头元素
        HSSFCell cellHead = null;
        for (int i = 0; i < heardList.length; i++) {
            cellHead = row.createCell(i);
            cellHead.setCellValue(heardList[i]);
            cellHead.setCellStyle(style);
        }

        //设置每格数据的样式 （字体红色）
        HSSFCellStyle cellParamStyle = wb.createCellStyle();
        HSSFFont ParamFontStyle = wb.createFont();
        cellParamStyle.setAlignment(HorizontalAlignment.CENTER);
        cellParamStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        ParamFontStyle.setColor((short) 255);   //设置字体颜色 (红色)
        ParamFontStyle.setFontHeightInPoints((short) exclUtils.getFontSize());
        cellParamStyle.setFont(ParamFontStyle);
        //设置每格数据的样式2（字体蓝色）
        HSSFCellStyle cellParamStyle2 = wb.createCellStyle();
        cellParamStyle2.setAlignment(HorizontalAlignment.CENTER);
        cellParamStyle2.setVerticalAlignment(VerticalAlignment.CENTER);
        HSSFFont ParamFontStyle2 = wb.createFont();
//        ParamFontStyle2.setColor(HSSFColor.BLUE.index);   //设置字体颜色 (蓝色)
        ParamFontStyle2.setFontHeightInPoints((short) exclUtils.getFontSize());
        cellParamStyle2.setFont(ParamFontStyle2);
        //开始写入实体数据信息
        int a = 2;
        List<Map> data = exclUtils.getData();
        String[] heardKey = exclUtils.getHeardKey();
        List<String> strings = Arrays.asList(heardKey);
        strings = strings.stream().filter(str -> StringUtils.isNotEmpty(str)).collect(Collectors.toList());
        for (int i = 0; i < data.size(); i++) {
            HSSFRow roww = wbSheet.createRow((int) a);
            Map map = data.get(i);
            HSSFCell cell = null;

            for (int j = 0; j < strings.size(); j++) {
                cell = roww.createCell(j);
                cell.setCellStyle(style);
                Object valueObject = map.get(heardKey[j]);
                String value = null;
                if (valueObject == null) {
                    valueObject = "";
                }
                if (valueObject instanceof String) {
                    //取出的数据是字符串直接赋值
                    value = (String) map.get(heardKey[j]);
                } else if (valueObject instanceof Integer) {
                    //取出的数据是Integer
                    value = String.valueOf(((Integer) (valueObject)).floatValue());
                } else if (valueObject instanceof BigDecimal) {
                    //取出的数据是BigDecimal
                    value = String.valueOf(((BigDecimal) (valueObject)).floatValue());
                } else {
                    value = valueObject.toString();
                }
                //设置单个单元格的字体颜色
                if (heardKey[j].equals("ddNum") || heardKey[j].equals("sjNum")) {
                    if ((Long) map.get("ddNum") != null) {
                        if ((Long) map.get("sjNum") == null) {
                            cell.setCellStyle(cellParamStyle);
                        } else if ((Long) map.get("ddNum") != (Long) map.get("sjNum")) {
                            if ((Long) map.get("ddNum") > (Long) map.get("sjNum")) {
                                cell.setCellStyle(cellParamStyle);
                            }
                            if ((Long) map.get("ddNum") < (Long) map.get("sjNum")) {
                                cell.setCellStyle(cellParamStyle2);
                            }
                        } else {
                            cell.setCellStyle(style);
                        }
                    }
                }
                cell.setCellValue(Strings.isNullOrEmpty(value) ? "" : value);
            }
            a++;

        }
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        wb.write(os);
        return os.toByteArray();
//        return wb.getBytes();
    }


    private static void checkConfig(ExclUtils exclUtils) {
        String[] heardList = exclUtils.getHeardList();
        String[] heardKey = exclUtils.getHeardKey();
        int fontSize = exclUtils.getFontSize();
        int rowHeight = exclUtils.getRowHeight();
        int columWidth = exclUtils.getColumWidth();
        String sheetName = exclUtils.getSheetName();
        if (heardKey == null || heardList.length == 0) {
//            throw new IOException("列名数组不能为空或者为NULL");
            System.out.println("列名数组不能为空或者为NULL");
        }

        if (fontSize < 0 || rowHeight < 0 || columWidth < 0) {
//            throw new IOException("字体、宽度或者高度不能为负值");
            System.out.println("字体、宽度或者高度不能为负值");
        }

        if (Strings.isNullOrEmpty(sheetName)) {
//            throw new IOException("工作表表名不能为NULL");
            System.out.println("工作表表名不能为NULL");
        }
    }
}


