package com.lt.dailytest.other;

import com.lt.dailytest.utils.common.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author tong.luo
 * @description InsertSqlTest
 * @date 2021/6/3 14:17
 */
@SpringBootTest
public class InsertSqlTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final String invoiceHeadSql = "INSERT INTO `busi_invoice_head`\n" +
            "(`invoice_head_id`, `invoice_req_serial_no`, `platform_code`, `platform_alias`, `taxpayer_num`, `taxpayer_name`,\n" +
            "`taxpayer_archive_no`, `agent_invoice_flag`, `item_name`, `coding_table_version`, `seller_taxpayer_num`,\n" +
            "`seller_name`, `seller_address`, `seller_tel`, `seller_bank_name`, `seller_bank_account`, `buyer_taxpayer_num`,\n" +
            " `buyer_name`, `buyer_address`, `buyer_tel`, `buyer_bank_name`, `buyer_bank_account`, `buyer_enterprise_type`,\n" +
            "  `drawer_id`, `drawer_name`, `casher_name`, `reviewer_name`, `invoice_date`, `invoice_type`, `invoice_kind_code`,\n" +
            "  `special_red_invoice_sign`, `operation_code`, `goods_list_sign`, `goods_list_name`, `total_mount`, `no_tax_amount`,\n" +
            "  `tax_amount`, `surplus_no_tax_amount`, `surplus_tax_amount`, `qr_code`, `machine_code`, `disk_type`, `extension_num`,\n" +
            "  `terminal_type`, `check_code`, `invoice_ciphertext`, `old_invoice_code`, `old_invoice_no`, `red_invoice_reason`, `remarks`,\n" +
            "   `invoice_code`, `invoice_num`, `pdf_id`, `pdf_image_id`, `enter_account_sign`, `order_no`, `red_flag`, `destroy_flag`,\n" +
            "   `operation_failed_reason`, `destroy_no`, `taker_email`, `invoice_failed_reason`, `request_time`, `first_request_time`,\n" +
            "   `receive_time`, `taker_name`, `taker_phone`, `invoice_status`, `delete_flag`, `time_stamp`, `special_invoice_kind`, `tax_rate`,\n" +
            "    `org_id`, `shop_name`, `email_status`, `sms_status`, `red_invoice_bill_no`, `invoice_issue_source`, `invoice_issue_platform_name`,\n" +
            "     `group_id`, `hierarchy_id`, `print_count`, `batch_id`, `update_time`, `backup1`, `backup2`, `backup3`, `backup4`, `backup5`,\n" +
            "      `backup6`, `backup7`, `backup8`, `backup9`, `backup10`) VALUES (${invoice_head_id}, '${invoice_req_serial_no}', '${platform_code}',\n" +
            "       '${platform_alias}', '${taxpayer_num}', '${taxpayer_name}', '${taxpayer_archive_no}', '${agent_invoice_flag}', '${item_name}', " +
            "'${coding_table_version}', '${seller_taxpayer_num}', '${seller_name}',\n" +
            "        '${seller_address}', '${seller_tel}', '${seller_bank_name}', '${seller_bank_account}', '${buyer_taxpayer_num}', '${buyer_name}', '${buyer_address}'," +
            " '', '${buyer_bank_name}',\n" +
            "        '', '${buyer_enterprise_type}', ${drawer_id}, '${drawer_name}', '${casher_name}', '${reviewer_name}', '${invoice_date}'," +
            " ${invoice_type}, '${invoice_kind_code}', '${special_red_invoice_sign}', '${operation_code}', '${goods_list_sign}', '${goods_list_name}', ${total_mount}, " +
            "${no_tax_amount},\n" +
            "        ${tax_amount}, ${surplus_no_tax_amount}, ${surplus_tax_amount}, '${qr_code}',\n" +
            "         '', '${disk_type}', '${extension_num}', '${terminal_type}', '${check_code}', '${invoice_ciphertext}', '${old_invoice_code}', '${old_invoice_no}'" +
            ", '${red_invoice_reason}', " +
            "'${remarks}', '${invoice_code}', '${invoice_num}',\n" +
            "         ${pdf_id}, ${pdf_image_id}, '${enter_account_sign}', '${order_no}', ${red_flag}, ${destroy_flag}, '${operation_failed_reason}', '${destroy_no}', '${taker_email}'," +
            " ${invoice_failed_reason},\n" +
            "         ${request_time}, ${first_request_time}, ${receive_time}, '${taker_name}', '${taker_phone}', " +
            "${invoice_status}, '${delete_flag}', ${time_stamp},\n" +
            "          '${special_invoice_kind}', ${tax_rate}, ${org_id}, ${shop_name}, ${email_status}, ${sms_status}," +
            " ${red_invoice_bill_no}, '${invoice_issue_source}', '${invoice_issue_platform_name}', ${group_id}, '${hierarchy_id}', ${print_count}," +
            " ${batch_id}, ${update_time}, ${backup1},\n" +
            "          ${backup2}, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);";

    private final String detailSql = "INSERT INTO `busi_invoice_detail`\n" +
            "(`invoice_detail_id`, `invoice_req_serial_no`, `goods_name`, `metering_unit`, `specification_model`, `tax_flag`, " +
            "`quantity`, `invoice_row_attr`, `unit_price`,\n" +
            " `tax_classification_code`, `goods_code`, `preferential_policy_flag`, `zero_tax_flag`, `vat_special_manage`," +
            " `deduction_amount`, `invoice_amount`, \n" +
            " `tax_rate_amount`, `tax_rate_value`, `item_no`, `discount_amount`, `discount_tax_rate_amount`, `backup1`," +
            " `backup2`, `backup3`, `backup4`,`backup5`) \n" +
            " VALUES (${invoice_detail_id}, '${invoice_req_serial_no}', '${goods_name}',\n" +
            " '${metering_unit}', '${specification_model}', '${tax_flag}', ${quantity},'${invoice_row_attr}', '${unit_price}', " +
            "${tax_classification_code}, \n" +
            " '', ${preferential_policy_flag}, ${zero_tax_flag}, ${vat_special_manage}, null," +
            " ${invoice_amount}, ${tax_rate_amount}, ${tax_rate_value}, ${item_no}, ${discount_amount},\n" +
            " ${discount_tax_rate_amount}, ${backup1},NULL, NULL, NULL, NULL);";
    private final String staticSql = "INSERT INTO `busi_invoice_statistics`\n" +
            "(`invoice_statistics_id`, `invoice_req_serial_no`, `group_id`, `org_id`, `hierarchy_id`, `platform_code`, `taxpayer_num`, `extension_num`,\n" +
            " `machine_code`, `seller_name`, `buyer_taxpayer_num`, `buyer_name`, `shop_name`, `drawer_id`, `drawer_name`, `invoice_date`,\n" +
            " `invoice_date_time`, `invoice_type`, `invoice_kind_code`, `invoice_code`, `invoice_num`, `total_mount`, `no_tax_amount`, \n" +
            " `tax_amount`, `destroy_flag`, `special_invoice_kind`, `insert_time`) " +
            "VALUES (${invoice_statistics_id}, '${invoice_req_serial_no}', \n" +
            " ${group_id}, ${org_id}, '${hierarchy_id}', '${platform_code}', '${taxpayer_num}',\n" +
            " '${extension_num}', '', '${seller_name}', '${buyer_taxpayer_num}',\n" +
            " '${buyer_name}', ${shop_name}, ${drawer_id}, '${drawer_name}', ${invoice_date}, '${invoice_date_time}', ${invoice_type}, \n" +
            " '${invoice_kind_code}', '${invoice_code}', '${invoice_num}', ${total_mount}, ${no_tax_amount}, ${tax_amount}, ${destroy_flag},\n" +
            " '${special_invoice_kind}', ${insert_time});";
    private final String prefix = "${";
    private final String endfix = "}";
//    private final String detailPre = "detail_";
//    private final String staticlPre = "staticl_";

    @Test
    public void insertSql() throws Exception {
        String fileName = "templates/增值税专普发票数据导出20210506.xlsx";
        //InputStream resourceAsStream = this.getClass().getResourceAsStream(fileName);
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(fileName);
        if (null == resourceAsStream) {
            logger.error("文件获取为空，【{}】", fileName);
        }
        XSSFWorkbook wb = new XSSFWorkbook(resourceAsStream);
        XSSFSheet sheet = wb.getSheetAt(0);
        int startNum = 14;
        int endNum = 15/*sheet.getLastRowNum()*/;
        List<String> headids = Arrays.asList(
                "1415962522899058688","1415962522899058689","1415962522899058690");
        List<String> detailsNos = Arrays.asList(
                "1415962524207681536","1415962524207681537","1415962524207681538");
        List<String> staticNos = Arrays.asList("1415962524207681546","1415962524207681547","1415962524207681548");
        List<String> serialNos = Arrays.asList("JTSX8827611834044724","JTSX2844444471104581","JTSX5248608874843577");
        for (int j = startNum; j < endNum; j++) {
            XSSFRow row = sheet.getRow(j);
            if (row == null) {
                continue;
            }
            short lastCellNum = row.getLastCellNum();
            if (lastCellNum < 0) {
                continue;
            }
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put(prefix + "invoice_head_id" + endfix, headids.get(j - startNum));
            hashMap.put(prefix + "invoice_req_serial_no" + endfix, serialNos.get(j - startNum));
            hashMap.put(prefix + "invoice_detail_id" + endfix, detailsNos.get(j - startNum));
            hashMap.put(prefix + "invoice_statistics_id" + endfix, staticNos.get(j - startNum));
            this.initMap(hashMap);
            for (int i = 0; i < lastCellNum; i++) {
                XSSFCell cell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                //int cellType = cell.getCellType();
                CellType cellCellType = cell.getCellType();

                String stringCellValue = null;
                if (cellCellType.compareTo(CellType.NUMERIC) == 0) {
                    double numericCellValue = cell.getNumericCellValue();
                    stringCellValue = String.valueOf(numericCellValue);
                } else {
                    stringCellValue = cell.getStringCellValue();
                }
                if (null == stringCellValue) {
                    continue;
                }
                this.extracted(hashMap, i, stringCellValue);
            }
            if (StringUtils.isEmpty(hashMap.get(prefix + "buyer_enterprise_type" + endfix))) {
                hashMap.put(prefix + "buyer_enterprise_type" + endfix, "2");
            }
            hashMap.put(prefix + "item_no" + endfix, "1");

            hashMap.put(prefix + "invoice_kind_code" + endfix, "01");

            hashMap.put(prefix + "first_request_time" + endfix, hashMap.get(prefix + "invoice_date" + endfix));
            hashMap.put(prefix + "insert_time" + endfix, hashMap.get(prefix + "invoice_date" + endfix));

            String taxAmount = hashMap.get(prefix + "tax_amount" + endfix);
            String totalAmount = hashMap.get(prefix + "no_tax_amount" + endfix);
            if (null != taxAmount && null != totalAmount) {
                String noTaxAmount = new BigDecimal(totalAmount).add(new BigDecimal(taxAmount)).toString();
                hashMap.put(prefix + "total_mount" + endfix, noTaxAmount);
            }
            //如果金额小于0 则为红票
            if(new BigDecimal(hashMap.get(prefix + "no_tax_amount" + endfix)).compareTo(BigDecimal.ZERO) < 0){
                hashMap.put(prefix + "invoice_type" + endfix, "2");
            }
            String endRow = invoiceHeadSql;
            String detailRow = detailSql;
            String staticRow = staticSql;
            for (Map.Entry<String, String> stringEntry : hashMap.entrySet()) {
                String key = stringEntry.getKey();
                String value = stringEntry.getValue();
                if( StringUtils.isNotEmpty(key)){
                    endRow = endRow.replace(key, value);
                    detailRow = detailRow.replace(key, value);
                    staticRow = staticRow.replace(key, value);
                }

            }
            System.out.println(endRow );
            System.out.println(detailRow );
            System.out.println(staticRow );
//            logger.info("end invoice-head sql ---> [{}]", endRow);
//            logger.info("end invoice-head--detail sql ---> [{}]", detailRow);
//            logger.info("end static sql ---> [{}]", staticRow);
        }

    }

    private void initMap(HashMap<String, String> hashMap) {
        hashMap.put(prefix + "platform_code" + endfix, "85ZBRn8g");
        hashMap.put(prefix + "platform_alias" + endfix, "JTBP");
        hashMap.put(prefix + "taxpayer_num" + endfix, "91320594MA1NHMCA0T");
        hashMap.put(prefix + "taxpayer_name" + endfix, "苏州优钵花开文化发展有限公司");
        hashMap.put(prefix + "taxpayer_archive_no" + endfix, "91320594MA1NHMCA0T");
        hashMap.put(prefix + "seller_taxpayer_num" + endfix, "91320594MA1NHMCA0T");
        hashMap.put(prefix + "seller_name" + endfix, "苏州优钵花开文化发展有限公司");
        hashMap.put(prefix + "seller_address" + endfix, "苏州工业园区旺墩路135号融盛商务中心1幢1526室");
        hashMap.put(prefix + "seller_tel" + endfix, "13936814949");
        hashMap.put(prefix + "seller_bank_name" + endfix, "兴业银行股份有限公司苏州分行营业部");
        hashMap.put(prefix + "seller_bank_account" + endfix, "206610100100533716");
        hashMap.put(prefix + "org_id" + endfix, "1392663093010264064");
        hashMap.put(prefix + "invoice_issue_source" + endfix, "FPXF");
        hashMap.put(prefix + "invoice_issue_platform_name" + endfix, "发票修复");
        hashMap.put(prefix + "group_id" + endfix, "1307954712300630016");
        hashMap.put(prefix + "hierarchy_id" + endfix, "100220");
        hashMap.put(prefix + "coding_table_version" + endfix, "");
        hashMap.put(prefix + "enter_account_sign" + endfix, "0");
        hashMap.put(prefix + "red_flag" + endfix, "0");
        hashMap.put(prefix + "destroy_flag" + endfix, "0");
        hashMap.put(prefix + "request_time" + endfix, "now()");

        hashMap.put(prefix + "time_stamp" + endfix, "now()");
        hashMap.put(prefix + "receive_time" + endfix, "now()");
        hashMap.put(prefix + "update_time" + endfix, "now()");

        hashMap.put(prefix + "invoice_status" + endfix, "2");
        hashMap.put(prefix + "delete_flag" + endfix, "0");
        hashMap.put(prefix + "special_invoice_kind" + endfix, "00");
        hashMap.put(prefix + "tax_rate" + endfix, "0");
        hashMap.put(prefix + "old_invoice_code" + endfix, "");
        hashMap.put(prefix + "old_invoice_no" + endfix, "");
        hashMap.put(prefix + "invoice_type" + endfix, "1");
        hashMap.put(prefix + "special_red_invoice_sign" + endfix, "0");
        hashMap.put(prefix + "agent_invoice_flag" + endfix, "0");
        hashMap.put(prefix + "qr_code" + endfix, "");
        hashMap.put(prefix + "extension_num" + endfix, "0");
        hashMap.put(prefix + "check_code" + endfix, "");
        hashMap.put(prefix + "invoice_ciphertext" + endfix, "");
        hashMap.put(prefix + "red_invoice_reason" + endfix, "");
        hashMap.put(prefix + "remarks" + endfix, "");
        hashMap.put(prefix + "pdf_id" + endfix, "null");
        hashMap.put(prefix + "pdf_image_id" + endfix, "null");
        hashMap.put(prefix + "order_no" + endfix, hashMap.get(prefix + "invoice_req_serial_no" + endfix)); //流水号
        hashMap.put(prefix + "operation_failed_reason" + endfix, "");
        hashMap.put(prefix + "destroy_no" + endfix, "");
        hashMap.put(prefix + "taker_email" + endfix, "");
        hashMap.put(prefix + "invoice_failed_reason" + endfix, "null");
        hashMap.put(prefix + "taker_name" + endfix, "");
        hashMap.put(prefix + "taker_phone" + endfix, "");
        hashMap.put(prefix + "shop_name" + endfix, "null");
        hashMap.put(prefix + "email_status" + endfix, "0");
        hashMap.put(prefix + "sms_status" + endfix, "0");
        hashMap.put(prefix + "red_invoice_bill_no" + endfix, "null");
        hashMap.put(prefix + "print_count" + endfix, "0");
        hashMap.put(prefix + "batch_id" + endfix, "null");
        hashMap.put(prefix + "backup1" + endfix, "null");
        hashMap.put(prefix + "backup2" + endfix, "11");
        hashMap.put(prefix + "operation_code" + endfix, "10");
        //清单标识：0 非清单，1清单  大于 8 清单
        hashMap.put(prefix + "goods_list_sign" + endfix, "0");
        hashMap.put(prefix + "deduction_amount" + endfix, "null");
        hashMap.put(prefix + "discount_amount" + endfix, "null");
        hashMap.put(prefix + "preferential_policy_flag" + endfix, "0");
        hashMap.put(prefix + "goods_code" + endfix, "");
        hashMap.put(prefix + "invoice_row_attr" + endfix, "0");
        hashMap.put(prefix + "tax_flag" + endfix, "0");
        hashMap.put(prefix + "vat_special_manage" + endfix, "null");
        hashMap.put(prefix + "discount_tax_rate_amount" + endfix, "null");
        hashMap.put(prefix + "machine_code" + endfix, "");
        hashMap.put(prefix + "drawer_id" + endfix, "null");
        hashMap.put(prefix + "reviewer_name" + endfix, "");
        hashMap.put(prefix + "casher_name" + endfix, "");
        hashMap.put(prefix + "drawer_name" + endfix, "");
        hashMap.put(prefix + "terminal_type" + endfix, "");
        hashMap.put(prefix + "disk_type" + endfix, "");
        hashMap.put(prefix + "goods_list_name" + endfix, " ");
    }

    private void extracted(HashMap<String, String> hashMap, int i, String stringCellValue) {
        switch (i) {
            case 0:
                //发票代码
                hashMap.put(prefix + "invoice_code" + endfix, stringCellValue);
                break;
            case 1:
                //发票号码
                hashMap.put(prefix + "invoice_num" + endfix, stringCellValue);
                break;
            case 2:
                //购方企业名称
                hashMap.put(prefix + "buyer_name" + endfix, stringCellValue);
                break;
            case 3:
                //购方税号
                hashMap.put(prefix + "buyer_taxpayer_num" + endfix, stringCellValue);
                hashMap.put(prefix + "buyer_enterprise_type" + endfix, "1");

                break;
            case 4:
                //银行账号
//                String[] split = stringCellValue.split(" ");
//                if (split.length == 2) {
//                    hashMap.put(prefix + "buyer_bank_name" + endfix, split[0]);
//                    hashMap.put(prefix + "buyer_bank_account" + endfix, split[1]);
//                }
                hashMap.put(prefix + "buyer_bank_name" + endfix, stringCellValue);
                break;
            case 5:
                //地址电话
//                split = stringCellValue.split(" ");
//                if (split.length == 2) {
//                    hashMap.put(prefix + "buyer_address" + endfix, split[0]);
//                    hashMap.put(prefix + "buyer_tel" + endfix, split[1]);
//                }
                hashMap.put(prefix + "buyer_address" + endfix, stringCellValue);
                break;
            case 6:
                //开票日期
                hashMap.put(prefix + "invoice_date" + endfix, stringCellValue + " 00:00:00");
                hashMap.put(prefix + "invoice_date_time" + endfix, hashMap.get(prefix + "invoice_date" + endfix));
                if (StringUtils.isNotEmpty(stringCellValue)) {
                    String formatDate = DateUtils.formatDate(DateUtils.parseDate(hashMap.get(prefix + "invoice_date" + endfix), DateUtils.yyyy_MM_dd), DateUtils.yyyyMMdd);
                    hashMap.put(prefix + "invoice_date" + endfix, formatDate);
                }
                break;
            case 7:
                //商品编码版本号
                hashMap.put(prefix + "coding_table_version" + endfix, stringCellValue);
                break;
            case 8:
                //单据号
                hashMap.put(prefix + "bill_no" + endfix, stringCellValue);
                break;
            case 9:
                //商品名称

                hashMap.put(prefix + "item_name" + endfix, stringCellValue);
                hashMap.put(prefix + "goods_name" + endfix, stringCellValue);
                break;
            case 10:
                //规格
                hashMap.put(prefix + "specification_model" + endfix, stringCellValue);
                break;
            case 11:
                //单位
                hashMap.put(prefix + "metering_unit" + endfix, stringCellValue);
                break;
            case 12:
                //数量
                hashMap.put(prefix + "quantity" + endfix, stringCellValue);
                break;
            case 13:
                //单价
                hashMap.put(prefix + "unit_price" + endfix, stringCellValue);
                break;
            case 14:
                //金额
                hashMap.put(prefix + "no_tax_amount" + endfix, stringCellValue);
                hashMap.put(prefix + "surplus_no_tax_amount" + endfix, stringCellValue);
                hashMap.put(prefix + "invoice_amount" + endfix, stringCellValue);
                break;
            case 15:
                //税率
                if ("3%".equals(stringCellValue)) {
                    hashMap.put(prefix + "tax_rate_value" + endfix, "0.03");
                }
                if ("1%".equals(stringCellValue)) {
                    hashMap.put(prefix + "tax_rate_value" + endfix, "0.01");
                }
                if ("5%".equals(stringCellValue)) {
                    hashMap.put(prefix + "tax_rate_value" + endfix, "0.05");
                }
                if (StringUtils.isEmpty(stringCellValue) || "0%".equals(stringCellValue)) {
                    hashMap.put(prefix + "tax_rate_value" + endfix, "0");
                    hashMap.put(prefix + "zero_tax_flag" + endfix, "3");
                } else {
                    hashMap.put(prefix + "zero_tax_flag" + endfix, "null");
                }
                break;
            case 16:
                //税额
                hashMap.put(prefix + "tax_rate_amount" + endfix, stringCellValue);
                hashMap.put(prefix + "surplus_tax_amount" + endfix, stringCellValue);
                hashMap.put(prefix + "tax_amount" + endfix, stringCellValue);
                break;
            case 17:
                //税收分类编码
                hashMap.put(prefix + "tax_rate_value_code" + endfix, stringCellValue);
                hashMap.put(prefix + "tax_classification_code" + endfix, stringCellValue);
                break;
        }
    }

    @Test
    public void test2() throws Exception {
        String fileName = "templates/template2.xls";
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(fileName);
        if (null == resourceAsStream) {
            logger.error("文件获取为空，【{}】", fileName);
        }
        XSSFWorkbook wb = new XSSFWorkbook(resourceAsStream);
        XSSFSheet sheet = wb.getSheetAt(0);
        int startNum = 5;
        int endNum = sheet.getLastRowNum();
        List<String> headids = Arrays.asList("1401852529596043264","1401852529596043265","1401852529596043266","1401852529596043267","1401852529596043268","1401852529596043269","1401852529596043270","1401852529596043271","1401852529596043272","1401852529596043273","1401852529596043274","1401852529596043275","1401852529596043276","1401852529596043277","1401852529596043278","1401852529596043279","1401852529596043280","1401852529596043281","1401852529596043282","1401852529596043283","1401852529596043284","1401852529596043285","1401852529596043286","1401852529596043287","1401852529596043288","1401852529596043289","1401852529596043290","1401852529596043291","1401852529596043292","1401852529596043293","1401852529596043294","1401852529596043295","1401852529596043296","1401852529596043297","1401852529596043298","1401852529596043299",
                "1401852529596043300","1401852529596043301","1401852529596043302","1401852529596043303");
        List<String> detailids = Arrays.asList("1401852530430709760","1401852530430709761","1401852530430709762","1401852530430709763","1401852530430709764","1401852530430709765","1401852530430709766","1401852530430709767","1401852530430709768","1401852530430709769","1401852530430709770","1401852530430709771","1401852530430709772","1401852530430709773","1401852530430709774","1401852530430709775","1401852530430709776","1401852530430709777","1401852530430709778","1401852530430709779","1401852530430709780","1401852530430709781","1401852530430709782","1401852530430709783","1401852530430709784","1401852530430709785","1401852530430709786","1401852530430709787","1401852530430709788","1401852530430709789","1401852530430709790","1401852530430709791","1401852530430709792","1401852530430709793","1401852530430709794","1401852530430709795",
                "1401852530430709796","1401852530430709797","1401852530430709798","1401852530430709799");
        List<String> staticlids = Arrays.asList("1401852530443292672","1401852530443292673","1401852530443292674","1401852530443292675","1401852530443292676","1401852530443292677","1401852530443292678","1401852530443292679","1401852530443292680","1401852530443292681","1401852530443292682","1401852530443292683","1401852530443292684","1401852530443292685","1401852530443292686","1401852530443292687","1401852530443292688","1401852530443292689","1401852530443292690","1401852530443292691","1401852530443292692","1401852530443292693","1401852530443292694","1401852530443292695","1401852530443292696","1401852530443292697","1401852530443292698","1401852530443292699","1401852530443292700","1401852530443292701","1401852530443292702","1401852530443292703","1401852530443292704","1401852530443292705","1401852530443292706","1401852530443292707",
                "1401852530443292708","1401852530443292709","1401852530443292710","1401852530443292711");
        List<String> serialNums = Arrays.asList("JTSX5452502727766526","JTSX5035143022103373","JTSX8861552372235450","JTSX4835186521105607","JTSX4303655687466012","JTSX7613631454318221","JTSX8372724670322674","JTSX6828052788088488","JTSX1713705587552047","JTSX8246244214327474","JTSX7652413188510184","JTSX7840381212182533","JTSX1808257426531470","JTSX1747021881136626","JTSX0710816423106480","JTSX2247716310817123","JTSX5624703363074867","JTSX5224814204622425","JTSX0024622804035768","JTSX6004506542861458","JTSX5045083614166435","JTSX3187116551636574","JTSX2205740687412657","JTSX6575751537128018","JTSX4423167070812450","JTSX5414538110611773","JTSX7506478022332088","JTSX2616777613215333","JTSX2878008348882772","JTSX2310877253888840","JTSX6560848666118684","JTSX8720882272186673","JTSX0643222588345341","JTSX8075638088162662","JTSX4062435155632745",
                "JTSX8248513726540574","JTSX7017641663834632","JTSX6176207420858711","JTSX0787426042140082","JTSX3484563587067251");
        for (int j = startNum; j < endNum; j++) {
            XSSFRow row = sheet.getRow(j);
            if (row == null) {
                continue;
            }
            short lastCellNum = row.getLastCellNum();
            if (lastCellNum < 0) {
                continue;
            }
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put(prefix + "invoice_head_id" + endfix, headids.get(j - startNum));
            hashMap.put(prefix + "invoice_req_serial_no" + endfix, serialNums.get(j - startNum));
            hashMap.put(prefix + "invoice_detail_id" + endfix, detailids.get(j - startNum));
            hashMap.put(prefix + "invoice_statistics_id" + endfix, staticlids.get(j - startNum));
            this.initTest2Map(hashMap);
            for (int i = 0; i < lastCellNum; i++) {
                XSSFCell cell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                CellType cellType = cell.getCellType();
                String stringCellValue = null;
                if (cellType.compareTo(CellType.NUMERIC) == 0) {
                    double numericCellValue = cell.getNumericCellValue();
                    stringCellValue = String.valueOf(numericCellValue);
                } else {
                    stringCellValue = cell.getStringCellValue();
                }
                if (null == stringCellValue) {
                    continue;
                }
                this.test2extracted(hashMap, i, stringCellValue);
            }


            if (StringUtils.isEmpty(hashMap.get(prefix + "buyer_enterprise_type" + endfix))) {
                hashMap.put(prefix + "buyer_enterprise_type" + endfix, "2");
            }
            hashMap.put(prefix + "item_no" + endfix, "1");
            hashMap.put(prefix + "invoice_kind_code" + endfix, "04");

            hashMap.put(prefix + "first_request_time" + endfix, hashMap.get(prefix + "invoice_date" + endfix));
            hashMap.put(prefix + "insert_time" + endfix, hashMap.get(prefix + "invoice_date" + endfix));
            String taxAmount = hashMap.get(prefix + "tax_amount" + endfix);
            String totalAmount = hashMap.get(prefix + "no_tax_amount" + endfix);
            if (null != taxAmount && null != totalAmount) {
                String noTaxAmount = new BigDecimal(totalAmount).add(new BigDecimal(taxAmount)).toString();
                hashMap.put(prefix + "total_mount" + endfix, noTaxAmount);
            }
            //如果金额小于0 则为红票
            if(new BigDecimal(hashMap.get(prefix + "no_tax_amount" + endfix)).compareTo(BigDecimal.ZERO) < 0){
                hashMap.put(prefix + "invoice_type" + endfix, "2");
            }
            String endRow = invoiceHeadSql;
            String detailRow = detailSql;
            String staticRow = staticSql;
            for (Map.Entry<String, String> stringEntry : hashMap.entrySet()) {
                String key = stringEntry.getKey();
                String value = stringEntry.getValue();
                endRow = endRow.replace(key, value);
                detailRow = detailRow.replace(key, value);
                staticRow = staticRow.replace(key, value);
            }
            System.out.println(endRow );
            System.out.println(detailRow );
            System.out.println(staticRow );
        }
    }

    private void initTest2Map(HashMap<String, String> hashMap) {
        hashMap.put(prefix + "platform_code" + endfix, "85ZBRn8g");
        hashMap.put(prefix + "platform_alias" + endfix, "JTBP");
       /* hashMap.put(prefix + "taxpayer_num" + endfix, "91320594MA1NHMCA0T");
        hashMap.put(prefix + "taxpayer_name" + endfix, "苏州优钵花开文化发展有限公司");
        hashMap.put(prefix + "taxpayer_archive_no" + endfix, "91320594MA1NHMCA0T");
        hashMap.put(prefix + "seller_taxpayer_num" + endfix, "91320594MA1NHMCA0T");
        hashMap.put(prefix + "seller_name" + endfix, "苏州优钵花开文化发展有限公司");
        hashMap.put(prefix + "seller_address" + endfix, "苏州工业园区旺墩路135号融盛商务中心1幢1526室");
        hashMap.put(prefix + "seller_tel" + endfix, "13936814949");
        hashMap.put(prefix + "seller_bank_name" + endfix, "兴业银行股份有限公司苏州分行营业部");
        hashMap.put(prefix + "seller_bank_account" + endfix, "206610100100533716");*/
        hashMap.put(prefix + "org_id" + endfix, "1392663093010264064");
        hashMap.put(prefix + "invoice_issue_source" + endfix, "FPXF");
        hashMap.put(prefix + "invoice_issue_platform_name" + endfix, "发票修复");
        hashMap.put(prefix + "group_id" + endfix, "1307954712300630016");
        hashMap.put(prefix + "hierarchy_id" + endfix, "100220");
        hashMap.put(prefix + "coding_table_version" + endfix, "");
        hashMap.put(prefix + "enter_account_sign" + endfix, "0");
        hashMap.put(prefix + "red_flag" + endfix, "0");
        hashMap.put(prefix + "destroy_flag" + endfix, "0");
        hashMap.put(prefix + "request_time" + endfix, "now()");
        hashMap.put(prefix + "first_request_time" + endfix, "now()");
        hashMap.put(prefix + "time_stamp" + endfix, "now()");
        hashMap.put(prefix + "receive_time" + endfix, "now()");
        hashMap.put(prefix + "update_time" + endfix, "now()");
        hashMap.put(prefix + "insert_time" + endfix, "now()");
        hashMap.put(prefix + "invoice_status" + endfix, "2");
        hashMap.put(prefix + "delete_flag" + endfix, "0");
        hashMap.put(prefix + "special_invoice_kind" + endfix, "00");
        hashMap.put(prefix + "tax_rate" + endfix, "0");
        hashMap.put(prefix + "old_invoice_code" + endfix, "");
        hashMap.put(prefix + "old_invoice_no" + endfix, "");
        hashMap.put(prefix + "invoice_type" + endfix, "1");
        hashMap.put(prefix + "special_red_invoice_sign" + endfix, "0");
        hashMap.put(prefix + "agent_invoice_flag" + endfix, "0");
        hashMap.put(prefix + "qr_code" + endfix, "");
        hashMap.put(prefix + "extension_num" + endfix, "0");
        hashMap.put(prefix + "check_code" + endfix, "");
        hashMap.put(prefix + "invoice_ciphertext" + endfix, "");
        hashMap.put(prefix + "red_invoice_reason" + endfix, "");
        hashMap.put(prefix + "remarks" + endfix, "");
        hashMap.put(prefix + "pdf_id" + endfix, "null");
        hashMap.put(prefix + "pdf_image_id" + endfix, "null");
        hashMap.put(prefix + "order_no" + endfix, hashMap.get(prefix + "invoice_req_serial_no" + endfix)); //流水号
        hashMap.put(prefix + "operation_failed_reason" + endfix, "");
        hashMap.put(prefix + "destroy_no" + endfix, "");
        hashMap.put(prefix + "taker_email" + endfix, "");
        hashMap.put(prefix + "invoice_failed_reason" + endfix, "null");
        hashMap.put(prefix + "taker_name" + endfix, "");
        hashMap.put(prefix + "taker_phone" + endfix, "");
        hashMap.put(prefix + "shop_name" + endfix, "null");
        hashMap.put(prefix + "email_status" + endfix, "0");
        hashMap.put(prefix + "sms_status" + endfix, "0");
        hashMap.put(prefix + "red_invoice_bill_no" + endfix, "null");
        hashMap.put(prefix + "print_count" + endfix, "0");
        hashMap.put(prefix + "batch_id" + endfix, "null");
        hashMap.put(prefix + "backup1" + endfix, "null");
        hashMap.put(prefix + "backup2" + endfix, "11");
        hashMap.put(prefix + "operation_code" + endfix, "10");
        //清单标识：0 非清单，1清单  大于 8 清单
        hashMap.put(prefix + "goods_list_sign" + endfix, "0");
        hashMap.put(prefix + "deduction_amount" + endfix, "null");
        hashMap.put(prefix + "discount_amount" + endfix, "null");
        hashMap.put(prefix + "preferential_policy_flag" + endfix, "0");
        hashMap.put(prefix + "goods_code" + endfix, "");
        hashMap.put(prefix + "invoice_row_attr" + endfix, "0");
        hashMap.put(prefix + "tax_flag" + endfix, "0");
        hashMap.put(prefix + "vat_special_manage" + endfix, "null");
        hashMap.put(prefix + "discount_tax_rate_amount" + endfix, "null");
        hashMap.put(prefix + "machine_code" + endfix, "");
        hashMap.put(prefix + "drawer_id" + endfix, "null");
        hashMap.put(prefix + "reviewer_name" + endfix, "");
        hashMap.put(prefix + "casher_name" + endfix, "");
        hashMap.put(prefix + "drawer_name" + endfix, "");
        hashMap.put(prefix + "terminal_type" + endfix, "");
        hashMap.put(prefix + "disk_type" + endfix, "");
        hashMap.put(prefix + "goods_list_name" + endfix, " ");
    }

    private void test2extracted(HashMap<String, String> hashMap, int i, String stringCellValue) {
        switch (i) {
            case 0:
                //发票代码
                hashMap.put(prefix + "invoice_code" + endfix, stringCellValue);
                break;
            case 1:
                //发票号码
                hashMap.put(prefix + "invoice_num" + endfix, stringCellValue);
                break;
            case 2:
                //发票类别
                hashMap.put(prefix + "invoice_kind_code" + endfix, stringCellValue);
                break;
            case 3:
                //销方企业名称
                hashMap.put(prefix + "seller_name" + endfix, stringCellValue);
                hashMap.put(prefix + "taxpayer_name" + endfix, hashMap.get(prefix + "seller_name" + endfix));
                break;
            case 4:
                //销方税号
                hashMap.put(prefix + "seller_taxpayer_num" + endfix, stringCellValue);
                hashMap.put(prefix + "taxpayer_archive_no" + endfix, stringCellValue);
                hashMap.put(prefix + "taxpayer_num" + endfix, hashMap.get(prefix + "seller_taxpayer_num" + endfix));
                break;
            case 5:
                //销方银行账号
                String[] split = stringCellValue.split(" ");
                if (split.length == 2) {
                    hashMap.put(prefix + "seller_bank_name" + endfix, split[0]);
                    hashMap.put(prefix + "seller_bank_account" + endfix, split[1]);
                }
                break;
            case 6:
                //销方地址电话
                split = stringCellValue.split(" ");
                if (split.length == 2) {
                    hashMap.put(prefix + "seller_address" + endfix, split[0]);
                    hashMap.put(prefix + "seller_tel" + endfix, split[1]);
                }
                break;

            case 7:
                //购方企业名称
                hashMap.put(prefix + "buyer_name" + endfix, stringCellValue);
                break;
            case 8:
                //购方税号
                hashMap.put(prefix + "buyer_taxpayer_num" + endfix, stringCellValue);
                hashMap.put(prefix + "buyer_enterprise_type" + endfix, "1");
                break;
            case 9:
                //购方银行账号
//                split = stringCellValue.split(" ");
//                if (split.length == 2) {
//                    hashMap.put(prefix + "buyer_bank_name" + endfix, split[0]);
//                    hashMap.put(prefix + "buyer_bank_account" + endfix, split[1]);
//                }
                hashMap.put(prefix + "buyer_bank_name" + endfix, stringCellValue);
                break;
            case 10:
                //地址电话
//                split = stringCellValue.split(" ");
//                if (split.length == 2) {
//                    hashMap.put(prefix + "buyer_address" + endfix, split[0]);
//                    hashMap.put(prefix + "buyer_tel" + endfix, split[1]);
//                }
                hashMap.put(prefix + "buyer_address" + endfix, stringCellValue);
                break;

            case 11:
                //备注
                hashMap.put(prefix + "remarks" + endfix, stringCellValue);
                break;
            case 12:
                //开票日期
                hashMap.put(prefix + "invoice_date" + endfix, stringCellValue + " 00:00:00");
                hashMap.put(prefix + "invoice_date_time" + endfix, hashMap.get(prefix + "invoice_date" + endfix));
                String formatDate = DateUtils.formatDate(DateUtils.parseDate(hashMap.get(prefix + "invoice_date" + endfix), DateUtils.yyyy_MM_dd), DateUtils.yyyyMMdd);
                hashMap.put(prefix + "invoice_date" + endfix, formatDate);
                break;
            case 13:
                //商品名称

                hashMap.put(prefix + "item_name" + endfix, stringCellValue);
                hashMap.put(prefix + "goods_name" + endfix, stringCellValue);
                break;

            case 14:
                //规格
                hashMap.put(prefix + "specification_model" + endfix, stringCellValue);
                break;

            case 15:
                //单位
                hashMap.put(prefix + "metering_unit" + endfix, stringCellValue);
                break;

            case 16:
                //数量
                hashMap.put(prefix + "quantity" + endfix, stringCellValue);
                break;

            case 17:
                //单价
                hashMap.put(prefix + "unit_price" + endfix, stringCellValue);
                break;

            case 18:
                //金额
                hashMap.put(prefix + "no_tax_amount" + endfix, stringCellValue);
                hashMap.put(prefix + "surplus_no_tax_amount" + endfix, stringCellValue);
                hashMap.put(prefix + "invoice_amount" + endfix, stringCellValue);
                break;

            case 19:
                hashMap.put(prefix + "tax_rate_value" + endfix, stringCellValue);
                //税率
                if (StringUtils.isEmpty(stringCellValue) || "0%".equals(stringCellValue)) {
                    hashMap.put(prefix + "tax_rate_value" + endfix, "0");
                    hashMap.put(prefix + "zero_tax_flag" + endfix, "3");
                } else {
                    hashMap.put(prefix + "zero_tax_flag" + endfix, "null");
                }
                break;

            case 20:
                //税额
                hashMap.put(prefix + "tax_rate_amount" + endfix, stringCellValue);
                hashMap.put(prefix + "surplus_tax_amount" + endfix, stringCellValue);
                hashMap.put(prefix + "tax_amount" + endfix, stringCellValue);
                break;

            case 21:
                //税收分类编码
                hashMap.put(prefix + "tax_rate_value_code" + endfix, stringCellValue);
                hashMap.put(prefix + "tax_classification_code" + endfix, stringCellValue);
                break;
        }

    }

    @Test
    public void test3() throws Exception {
        String fileName = "templates/batch-inport.xlsx";
        //InputStream resourceAsStream = this.getClass().getResourceAsStream(fileName);
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(fileName);
        if (null == resourceAsStream) {
            logger.error("文件获取为空，【{}】", fileName);
        }
        List<String> headids = Arrays.asList("1401835198644305920","1401835198644305921","1401835198644305922","1401835198644305923","1401835198644305924","1401835198644305925","1401835198644305926","1401835198644305927","1401835198644305928","1401835198644305929","1401835198644305930","1401835198644305931","1401835198644305932","1401835198644305933","1401835198644305934","1401835198644305935","1401835198644305936","1401835198644305937","1401835198644305938","1401835198644305939","1401835198644305940","1401835198644305941","1401835198644305942","1401835198644305943","1401835198644305944","1401835198644305945","1401835198644305946","1401835198644305947","1401835198644305948","1401835198644305949","1401835198644305950","1401835198644305951","1401835198644305952","1401835198644305953","1401835198644305954",
                "1401835198644305955","1401835198644305956","1401835198644305957","1401835198644305958","1401835198644305959");
        List<String> detailsNos = Arrays.asList("1401835199139233792","1401835199139233793","1401835199139233794","1401835199139233795","1401835199139233796","1401835199139233797","1401835199139233798","1401835199139233799","1401835199139233800","1401835199139233801","1401835199139233802","1401835199139233803","1401835199139233804","1401835199139233805","1401835199139233806","1401835199139233807","1401835199139233808","1401835199139233809","1401835199139233810","1401835199139233811","1401835199139233812","1401835199139233813","1401835199139233814","1401835199139233815","1401835199139233816","1401835199139233817","1401835199139233818","1401835199139233819","1401835199139233820","1401835199139233821","1401835199139233822","1401835199139233823","1401835199139233824","1401835199139233825","1401835199139233826",
                "1401835199139233827","1401835199139233828","1401835199139233829","1401835199139233830","1401835199139233831");
        List<String> staticNos = Arrays.asList("1401835199139233832","1401835199139233833","1401835199139233834","1401835199139233835","1401835199139233836","1401835199139233837","1401835199139233838","1401835199139233839","1401835199139233840","1401835199139233841","1401835199139233842","1401835199139233843","1401835199139233844","1401835199139233845","1401835199139233846","1401835199139233847","1401835199139233848","1401835199139233849","1401835199139233850","1401835199139233851","1401835199139233852","1401835199139233853","1401835199139233854","1401835199139233855","1401835199139233856","1401835199139233857","1401835199139233858","1401835199139233859","1401835199139233860","1401835199139233861","1401835199139233862","1401835199139233863","1401835199139233864","1401835199139233865","1401835199139233866",
                "1401835199139233867","1401835199139233868","1401835199139233869","1401835199139233870","1401835199139233871");
        List<String> serialNos = Arrays.asList("JTSX8774633244226707","JTSX0735614536471466","JTSX4377115210060060","JTSX2424343056280032","JTSX3220146870841320","JTSX8804083774105231","JTSX0514251775814678","JTSX0203362624007850","JTSX5023272088373865","JTSX6164570527585356","JTSX5058745613126700","JTSX6230850306732606","JTSX6275633857084013","JTSX8242562486486562","JTSX4227642154628105","JTSX3584247543116585","JTSX1282425167652057","JTSX8646664187227317","JTSX7532268613862706","JTSX0527615258186140","JTSX1556270515570731","JTSX6456102730665124","JTSX3202477280873158","JTSX4532601660236715","JTSX5733325083722235","JTSX6783286471626060","JTSX1201311606148587","JTSX1256131684827065","JTSX7868550200407788","JTSX1320056041224313","JTSX1561053681153587","JTSX2210347863771065","JTSX7357353847248041","JTSX2587788478114460","JTSX8266711617580654",
                "JTSX3282365432746004","JTSX2778463466588777","JTSX2832322035575615","JTSX8128731143866432","JTSX7054827352414408");


        XSSFWorkbook wb = new XSSFWorkbook(resourceAsStream);
        List<HashMap<String, String>> hashMapList = new ArrayList<>();
        XSSFSheet sheet = wb.getSheetAt(0);
        int startNum = 6;
        int endNum = sheet.getLastRowNum();
        for (int j = startNum; j < endNum; j++) {

            XSSFRow row = sheet.getRow(j);
            if (row == null) {
                continue;
            }
            short lastCellNum = row.getLastCellNum();
            if (lastCellNum < 0) {
                continue;
            }
            HashMap<String, String> hashMap = new HashMap<>();
            for (int i = 0; i < lastCellNum; i++) {
                XSSFCell cell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                CellType cellType = cell.getCellType();
                String stringCellValue = null;
                if (cellType.compareTo(CellType.NUMERIC) == 0) {
                    double numericCellValue = cell.getNumericCellValue();
                    stringCellValue = String.valueOf(numericCellValue);
                } else {
                    stringCellValue = cell.getStringCellValue();
                }
                if (null == stringCellValue) {
                    continue;
                }
                this.sql3extracted(hashMap, i, stringCellValue);
                hashMap.put(prefix + "invoice_head_id" + endfix, headids.get(j - startNum));
                hashMap.put(prefix + "invoice_req_serial_no" + endfix, serialNos.get(j - startNum));
                hashMap.put(prefix + "invoice_detail_id" + endfix, detailsNos.get(j - startNum));
                hashMap.put(prefix + "invoice_statistics_id" + endfix, staticNos.get(j - startNum));

                this.defaultNumSet(hashMap);
            }
            hashMapList.add(hashMap);
        }

        List<Integer> noinvoiceNumList = new ArrayList<>();
        for (int i = 0; i < hashMapList.size(); i++) {
            String invoiceNum = hashMapList.get(i).get(prefix + "invoice_num" + endfix);
            String invoiceCode = hashMapList.get(i).get(prefix + "invoice_code" + endfix);
            if (StringUtils.isEmpty(invoiceNum) && StringUtils.isEmpty(invoiceCode)) {
                noinvoiceNumList.add(i);
            }
        }

        List<Integer> alreadyContain = new ArrayList<>();
        for (int i = 0; i < hashMapList.size(); i++) {
            HashMap<String, String> hashMap = hashMapList.get(i);
            this.initMap(hashMap);
            hashMap.put(prefix + "item_no" + endfix, "1");

            hashMap.put(prefix + "invoice_kind_code" + endfix, "01");

            hashMap.put(prefix + "first_request_time" + endfix, hashMap.get(prefix + "invoice_date" + endfix));
            hashMap.put(prefix + "insert_time" + endfix, hashMap.get(prefix + "invoice_date" + endfix));
            if (StringUtils.isEmpty(hashMap.get(prefix + "buyer_enterprise_type" + endfix))) {
                hashMap.put(prefix + "buyer_enterprise_type" + endfix, "2");
            }
            //如果金额小于0 则为红票
            if(new BigDecimal(hashMap.get(prefix + "no_tax_amount" + endfix)).compareTo(BigDecimal.ZERO) < 0){
                hashMap.put(prefix + "invoice_type" + endfix, "2");
            }
            boolean judge = true;
            int loopNum = i + 1;
            //更改这条对应的发票表中的商品金额
            int lessNum = -1;
            //计算总金额和税额
            BigDecimal totalMon = new BigDecimal(hashMap.get(prefix + "no_tax_amount" + endfix));
            BigDecimal totalRateMon =new BigDecimal(hashMap.get(prefix + "tax_rate_amount" + endfix));
            //说明这条是商品行
//            if (noinvoiceNumList.contains(i) && !alreadyContain.contains(i)) {
//                alreadyContain.add(i);
//            }
            //获取到发票代码和发票号码对应的excl行 当前行的下一行
            while (judge) {
                if (noinvoiceNumList.contains(loopNum)) {
//                    if(lessNum == -1 ){
//                        lessNum = loopNum;
//                    }
                    HashMap<String, String> stringHashMap = hashMapList.get(i);
                    if(hashMap.get(prefix + "item_name" + endfix).equals("小计")){
                        continue;
                    }
                    stringHashMap.put(prefix + "goods_code" + endfix, hashMap.get(prefix + "goods_code" + endfix));
                    stringHashMap.put(prefix + "invoice_req_serial_no" + endfix, hashMap.get(prefix + "invoice_req_serial_no" + endfix));
                    stringHashMap.put(prefix + "specification_model" + endfix, hashMap.get(prefix + "specification_model" + endfix));
                    stringHashMap.put(prefix + "invoice_amount" + endfix, stringHashMap.get(prefix + "no_tax_amount" + endfix));
                    stringHashMap.put(prefix + "invoice_req_serial_no" + endfix, stringHashMap.get(prefix + "invoice_req_serial_no" + endfix));

                    stringHashMap.put(prefix + "backup1" + endfix, "null");
                    this.defaultNumSet(stringHashMap);

                    totalMon = totalMon.add(new BigDecimal(stringHashMap.get(prefix + "no_tax_amount" + endfix)));
                    totalRateMon = totalRateMon.add(new BigDecimal(stringHashMap.get(prefix + "tax_rate_amount" + endfix)));

                    String detailRow = detailSql;
                    for (Map.Entry<String, String> stringEntry : stringHashMap.entrySet()) {
                        String key = stringEntry.getKey();
                        String value = stringEntry.getValue();
                        if(StringUtils.isNotEmpty(value)){
                            detailRow = detailRow.replace(key, value);
                        }
                    }
                    System.out.println(detailRow );
                    //logger.info("end invoice-head sql ---> [{}]", detailRow);
                    alreadyContain.add(loopNum);
                    loopNum = i = i + 1;
                    //获取最小的循环数
//                    lessNum = lessNum > loopNum ? loopNum : lessNum;
                }else {
                    judge = false;
                }
            }


            if (null != totalMon && null != totalRateMon) {
                String noTaxAmount = totalRateMon.add(totalRateMon).toString();
                hashMap.put(prefix + "total_mount" + endfix, noTaxAmount);
            }

            String endRow = invoiceHeadSql;
            String detailRow = detailSql;
            String staticRow = staticSql;
            for (Map.Entry<String, String> stringEntry : hashMap.entrySet()) {
                String key = stringEntry.getKey();
                String value = stringEntry.getValue();
                if (StringUtils.isNotEmpty(value)) {
                    endRow = endRow.replace(key, value);
                    detailRow = detailRow.replace(key, value);
                    staticRow = staticRow.replace(key, value);
                }

            }
            System.out.println(endRow );
            System.out.println(detailRow );
            System.out.println(staticRow );
//            logger.info("end invoice-head sql ---> [{}]", endRow);
//            logger.info("end invoice-head--detail sql ---> [{}]", detailRow);
//            logger.info("end static sql ---> [{}]", staticRow);
        }
    }

    private void defaultNumSet(HashMap<String, String> hashMap) {
        hashMap.put(prefix + "machine_code" + endfix, " ");
        hashMap.put(prefix + "drawer_name" + endfix, " ");
        hashMap.put(prefix + "casher_name" + endfix, " ");
        hashMap.put(prefix + "reviewer_name" + endfix, " ");
        hashMap.put(prefix + "disk_type" + endfix, " ");
        hashMap.put(prefix + "terminal_type" + endfix, " ");
        hashMap.put(prefix + "goods_list_name" + endfix, " ");
        hashMap.put(prefix + "qr_code" + endfix, " ");
        hashMap.put(prefix + "check_code" + endfix, " ");
        hashMap.put(prefix + "invoice_ciphertext" + endfix, " ");
        hashMap.put(prefix + "old_invoice_code" + endfix, " ");
        hashMap.put(prefix + "old_invoice_no" + endfix, " ");
        hashMap.put(prefix + "red_invoice_reason" + endfix, " ");
        hashMap.put(prefix + "remarks" + endfix, " ");
        hashMap.put(prefix + "taker_name" + endfix, " ");
        hashMap.put(prefix + "taker_phone" + endfix, " ");
        hashMap.put(prefix + "coding_table_version" + endfix, " ");
        hashMap.put(prefix + "operation_failed_reason" + endfix, " ");
        hashMap.put(prefix + "destroy_no" + endfix, " ");
        hashMap.put(prefix + "taker_email" + endfix, " ");
        hashMap.put(prefix + "drawer_id" + endfix, "null");

        hashMap.put(prefix + "discount_tax_rate_amount" + endfix, "null");
        hashMap.put(prefix + "discount_amount" + endfix, "null");
        hashMap.put(prefix + "vat_special_manage" + endfix, "null");
        hashMap.put(prefix + "preferential_policy_flag" + endfix, "0");

        hashMap.put(prefix + "tax_flag" + endfix, "0");
        hashMap.put(prefix + "invoice_row_attr" + endfix, "0");


    }

    private void sql3extracted(HashMap<String, String> hashMap, int i, String stringCellValue) {
        switch (i) {
            case 0:
                //发票代码
                hashMap.put(prefix + "invoice_code" + endfix, stringCellValue);
                hashMap.put(prefix +/*staticlPre+*/ "invoice_code" + endfix, stringCellValue);
                break;
            case 1:
                //发票号码
                hashMap.put(prefix + "invoice_num" + endfix, stringCellValue);
                hashMap.put(prefix + /*staticlPre+*/"invoice_num" + endfix, stringCellValue);
                break;
            case 2:
                //购方企业名称
                hashMap.put(prefix + "buyer_name" + endfix, stringCellValue);
                break;
            case 3:
                //购方税号
                hashMap.put(prefix + "buyer_taxpayer_num" + endfix, stringCellValue);
                hashMap.put(prefix +/*staticlPre+*/ "buyer_taxpayer_num" + endfix, stringCellValue);
                hashMap.put(prefix + "buyer_enterprise_type" + endfix, "1");

                break;
            case 4:
                //银行账号
//                String[] split = stringCellValue.split(" ");
//                if (split.length == 2) {
//                    hashMap.put(prefix + "buyer_bank_name" + endfix, split[0]);
//                    hashMap.put(prefix + "buyer_bank_account" + endfix, split[1]);
//                }else {
                    hashMap.put(prefix + "buyer_bank_name" + endfix, stringCellValue);
//                }
                break;
            case 5:
                //地址电话
//                split = stringCellValue.split(" ");
//                if (split.length == 2) {
//                    hashMap.put(prefix + "buyer_address" + endfix, split[0]);
//                    hashMap.put(prefix + "buyer_tel" + endfix, split[1]);
//                }
                hashMap.put(prefix + "buyer_address" + endfix, stringCellValue);
                break;
            case 6:
                //开票日期
                hashMap.put(prefix + "invoice_date" + endfix, stringCellValue + " 00:00:00");
                hashMap.put(prefix +/*staticlPre+*/ "invoice_date" + endfix, stringCellValue + " 00:00:00");
                hashMap.put(prefix +/*staticlPre+*/ "invoice_date_time" + endfix, hashMap.get(prefix + "invoice_date" + endfix));
                if (StringUtils.isNotEmpty(stringCellValue)) {
                    String formatDate = DateUtils.formatDate(DateUtils.parseDate(hashMap.get(prefix + "invoice_date" + endfix), DateUtils.yyyy_MM_dd), DateUtils.yyyyMMdd);
                    hashMap.put(prefix +/*staticlPre+*/ "invoice_date" + endfix, formatDate);
                }
                break;
            case 7:
                //商品编码版本号
                hashMap.put(prefix + "coding_table_version" + endfix, stringCellValue);
                break;
            case 8:
                //单据号
                hashMap.put(prefix + "bill_no" + endfix, stringCellValue);
                break;
            case 9:
                //商品名称

                hashMap.put(prefix + "item_name" + endfix, stringCellValue);
                hashMap.put(prefix +/*detailPre+*/ "goods_name" + endfix, stringCellValue);
                break;
            case 10:
                //规格
                hashMap.put(prefix +/*detailPre+ */"specification_model" + endfix, stringCellValue);
                break;
            case 11:
                //单位
                hashMap.put(prefix +/*detailPre+*/ "metering_unit" + endfix, stringCellValue);
                break;
            case 12:
                //数量
                hashMap.put(prefix +/*detailPre+*/ "quantity" + endfix, stringCellValue);
                break;
            case 13:
                //单价
                hashMap.put(prefix +/*detailPre+*/ "unit_price" + endfix, stringCellValue);
                break;
            case 14:
                //金额
                hashMap.put(prefix + "no_tax_amount" + endfix, stringCellValue);
                hashMap.put(prefix +/*staticlPre+*/ "no_tax_amount" + endfix, stringCellValue);
                hashMap.put(prefix + "surplus_no_tax_amount" + endfix, stringCellValue);
                break;
            case 15:
                //税率
                if ("3%".equals(stringCellValue)) {
                    hashMap.put(prefix +/*detailPre+*/ "tax_rate_value" + endfix, "0.03");
                }
                if ("1%".equals(stringCellValue)) {
                    hashMap.put(prefix +/*detailPre+*/ "tax_rate_value" + endfix, "0.01");
                }
                if ("5%".equals(stringCellValue)) {
                    hashMap.put(prefix +/*detailPre+ */"tax_rate_value" + endfix, "0.05");
                }
                if (StringUtils.isEmpty(stringCellValue) || "0%".equals(stringCellValue)) {
                    hashMap.put(prefix +/*detailPre+*/ "tax_rate_value" + endfix, "0");
                    hashMap.put(prefix + "zero_tax_flag" + endfix, "3");
                } else {
                    hashMap.put(prefix +/*detailPre+ */"zero_tax_flag" + endfix, "null");
                }
                break;
            case 16:
                //税额
                hashMap.put(prefix +/*detailPre+ */"tax_rate_amount" + endfix, stringCellValue);
                hashMap.put(prefix + "surplus_tax_amount" + endfix, stringCellValue);
                hashMap.put(prefix + "tax_amount" + endfix, stringCellValue);
                hashMap.put(prefix +/*staticlPre+ */"tax_amount" + endfix, stringCellValue);
                break;
            case 17:
                //税收分类编码
                hashMap.put(prefix + "tax_rate_value_code" + endfix, stringCellValue);
                hashMap.put(prefix +/*detailPre+*/ "tax_classification_code" + endfix, stringCellValue);
                break;
        }
    }
}


