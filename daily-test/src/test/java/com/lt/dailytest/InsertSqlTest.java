package com.lt.dailytest;

import com.lt.dailytest.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
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
        String fileName = "templates/single-inport.xlsx";
        //InputStream resourceAsStream = this.getClass().getResourceAsStream(fileName);
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(fileName);
        if (null == resourceAsStream) {
            logger.error("文件获取为空，【{}】", fileName);
        }
        XSSFWorkbook wb = new XSSFWorkbook(resourceAsStream);
        XSSFSheet sheet = wb.getSheetAt(0);
        int startNum = 6;
        int endNum = sheet.getLastRowNum();
        List<String> headids = Arrays.asList(
                "1401817279734829056","1401817279734829057","1401817279734829058","1401817279734829059","1401817279734829060","1401817279734829061","1401817279734829062","1401817279734829063","1401817279734829064","1401817279734829065","1401817279734829066","1401817279734829067","1401817279734829068","1401817279734829069","1401817279734829070","1401817279734829071","1401817279734829072","1401817279734829073","1401817279734829074","1401817279734829075","1401817279734829076","1401817279734829077","1401817279734829078","1401817279734829079","1401817279734829080","1401817279734829081","1401817279734829082","1401817279734829083","1401817279734829084","1401817279734829085","1401817279734829086","1401817279734829087","1401817279734829088","1401817279734829089","1401817279734829090","1401817279734829091","1401817279734829092","1401817279734829093","1401817279734829094","1401817279734829095","1401817279734829096","1401817279734829097","1401817279734829098","1401817279734829099","1401817279734829100","1401817279734829101","1401817279734829102","1401817279734829103","1401817279734829104","1401817279734829105","1401817279734829106","1401817279734829107","1401817279734829108","1401817279734829109","1401817279734829110","1401817279734829111","1401817279734829112","1401817279734829113","1401817279734829114","1401817279734829115","1401817279734829116","1401817279734829117","1401817279734829118","1401817279734829119","1401817279734829120","1401817279734829121","1401817279734829122","1401817279734829123","1401817279734829124","1401817279734829125","1401817279734829126","1401817279734829127","1401817279734829128","1401817279734829129","1401817279734829130","1401817279734829131","1401817279734829132","1401817279734829133","1401817279734829134","1401817279734829135","1401817279734829136","1401817279734829137","1401817279734829138","1401817279734829139","1401817279734829140","1401817279734829141","1401817279734829142","1401817279734829143","1401817279734829144","1401817279734829145","1401817279734829146","1401817279734829147","1401817279734829148","1401817279734829149","1401817279734829150","1401817279734829151",
                "1401817279734829152","1401817279734829153","1401817279734829154","1401817279734829155");
        List<String> detailsNos = Arrays.asList(
                "1401874029867827200","1401874029867827201","1401874029867827202","1401874029867827203","1401874029867827204","1401874029867827205","1401874029867827206","1401874029867827207","1401874029867827208","1401874029867827209","1401874029867827210","1401874029867827211","1401874029867827212","1401874029867827213","1401874029867827214","1401874029867827215","1401874029867827216","1401874029867827217","1401874029867827218","1401874029867827219","1401874029867827220","1401874029867827221","1401874029867827222","1401874029867827223","1401874029867827224","1401874029867827225","1401874029867827226","1401874029867827227","1401874029867827228","1401874029867827229","1401874029867827230","1401874029867827231","1401874029867827232","1401874029867827233","1401874029867827234","1401874029867827235","1401874029867827236","1401874029867827237","1401874029867827238","1401874029867827239","1401874029867827240","1401874029867827241","1401874029867827242","1401874029867827243","1401874029867827244","1401874029867827245","1401874029867827246","1401874029867827247","1401874029867827248","1401874029867827249","1401874029867827250","1401874029867827251","1401874029867827252","1401874029867827253","1401874029867827254","1401874029867827255","1401874029867827256","1401874029867827257","1401874029867827258","1401874029867827259","1401874029867827260","1401874029867827261","1401874029867827262","1401874029867827263","1401874029867827264","1401874029867827265","1401874029867827266","1401874029867827267","1401874029867827268","1401874029867827269","1401874029867827270","1401874029867827271","1401874029867827272","1401874029867827273","1401874029867827274","1401874029867827275","1401874029867827276","1401874029867827277","1401874029867827278","1401874029867827279","1401874029867827280","1401874029867827281","1401874029867827282","1401874029867827283","1401874029867827284","1401874029867827285","1401874029867827286","1401874029867827287","1401874029867827288","1401874029867827289","1401874029867827290","1401874029867827291","1401874029867827292","1401874029867827293","1401874029867827294","1401874029867827295","1401874029867827296","1401874029867827297","1401874029867827298","1401874029867827299");
        List<String> staticNos = Arrays.asList("1401817280162648164","1401817280162648165","1401817280162648166","1401817280162648167","1401817280162648168","1401817280162648169","1401817280162648170","1401817280162648171","1401817280162648172","1401817280162648173","1401817280162648174","1401817280162648175","1401817280162648176","1401817280162648177","1401817280162648178","1401817280162648179","1401817280162648180","1401817280162648181","1401817280162648182","1401817280162648183","1401817280162648184","1401817280162648185","1401817280162648186","1401817280162648187","1401817280162648188","1401817280162648189","1401817280162648190","1401817280162648191","1401817280162648192","1401817280162648193","1401817280162648194","1401817280162648195","1401817280162648196","1401817280162648197","1401817280162648198","1401817280162648199","1401817280162648200","1401817280162648201","1401817280162648202","1401817280162648203","1401817280162648204","1401817280162648205","1401817280162648206","1401817280162648207","1401817280162648208","1401817280162648209","1401817280162648210","1401817280162648211","1401817280162648212","1401817280162648213","1401817280162648214","1401817280162648215","1401817280162648216","1401817280162648217","1401817280162648218","1401817280162648219","1401817280162648220","1401817280162648221","1401817280162648222","1401817280162648223","1401817280162648224","1401817280162648225","1401817280162648226","1401817280162648227","1401817280162648228","1401817280162648229","1401817280162648230","1401817280162648231","1401817280162648232","1401817280162648233","1401817280162648234","1401817280162648235","1401817280162648236","1401817280162648237","1401817280162648238","1401817280162648239","1401817280162648240","1401817280162648241","1401817280162648242","1401817280162648243","1401817280162648244","1401817280162648245","1401817280162648246","1401817280162648247","1401817280162648248","1401817280162648249","1401817280162648250","1401817280162648251","1401817280162648252","1401817280162648253","1401817280162648254","1401817280162648255","1401817280162648256","1401817280162648257","1401817280162648258",
                "1401817280162648259","1401817280162648260","1401817280162648261","1401817280162648262","1401817280162648263");
        List<String> serialNos = Arrays.asList("JTSX5514161574045013","JTSX2223858257681623","JTSX6328170586750686","JTSX7347427087503452","JTSX0070520375550456","JTSX0781563853686250","JTSX7664355657013180","JTSX6560217428131802","JTSX6546384716221157","JTSX2472155425420472","JTSX8878087234885202","JTSX1000184157056046","JTSX3535626083671460","JTSX4163824746106744","JTSX0172228265150858","JTSX4276243261412120","JTSX6147185305023508","JTSX2645053626277200","JTSX0108370714218875","JTSX1060887036205774","JTSX2502164541262073","JTSX4760076023621761","JTSX1561264640152362","JTSX7362475256336413","JTSX0386026706270068","JTSX1078332040214747","JTSX7813331528814064","JTSX6282502268842675","JTSX6167272845781574","JTSX7713782780328006","JTSX1743683843768015","JTSX8056256557370202","JTSX5840681187266634","JTSX8152242551278387","JTSX2338087503046743","JTSX0330347854804200","JTSX2083432808677571","JTSX5155421731421663","JTSX3120445241535751","JTSX1458256133421874","JTSX1143425136017522","JTSX7377206745434701","JTSX2161887100881224","JTSX6638572178284710","JTSX6181352212288246","JTSX3630740250400121","JTSX0870470104658086","JTSX8821744747354605","JTSX7442065416364413","JTSX2010807580154563","JTSX0350386175734276","JTSX3678243607534520","JTSX7605567523112472","JTSX8648167224074575","JTSX3818461130117111","JTSX1334228582421132","JTSX5360151733248720","JTSX8486831177056445","JTSX4088687560741188","JTSX6068816012613073","JTSX7050522737650103","JTSX4256251851115778","JTSX6218806748587875","JTSX4553651842301128","JTSX3233703037363725","JTSX8233847068372122","JTSX4546174842245117","JTSX5678166302708482","JTSX5370820550702876","JTSX3506627266323715","JTSX1105561776744415","JTSX8720318473565207","JTSX4113354115822743","JTSX2876353521088730","JTSX0600356658464765","JTSX1323340003571448","JTSX5760700250117517","JTSX4585122415164050","JTSX3115186404471545","JTSX3051878658706551","JTSX5158131666236012","JTSX4537248637733803","JTSX5381876027518308","JTSX3533273820267206","JTSX2624663778257462","JTSX5785448858464803","JTSX5466047054287724","JTSX1658310316564668","JTSX5341511337617073","JTSX2381458661086106","JTSX3734428311640007","JTSX8652381718368627","JTSX3215626661242776","JTSX3611770018557412","JTSX4673071567065074",
                "JTSX3372572414822882","JTSX4113106545203258","JTSX3371523021347756","JTSX3781466870132851","JTSX3801813662545233");
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
                int cellType = cell.getCellType();
                String stringCellValue = null;
                if (Cell.CELL_TYPE_NUMERIC == cellType) {
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
        String fileName = "templates/template2.xlsx";
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
                int cellType = cell.getCellType();
                String stringCellValue = null;
                if (Cell.CELL_TYPE_NUMERIC == cellType) {
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
                int cellType = cell.getCellType();
                String stringCellValue = null;
                if (Cell.CELL_TYPE_NUMERIC == cellType) {
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


