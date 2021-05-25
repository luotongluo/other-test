package com.lt.dailytest.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
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
}
