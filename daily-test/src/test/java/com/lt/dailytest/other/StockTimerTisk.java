package com.lt.dailytest.other;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;

/**
 * @author tong.luo
 * @description StockTimerTisk
 * @date 2021/7/12 17:17
 */
public class StockTimerTisk {
    static Connection conn;
    static Statement st;
    static SimpleDateFormat dtf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        java.util.Timer timer = new Timer(true);
        timer.schedule(new MyTimerTask(), 0, 10 * 1000);//创建定时器，每隔10秒钟执行一次
        while (true)
            try {
                Thread.currentThread().sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }

    static class MyTimerTask extends java.util.TimerTask {
        public void run() {
            try {
                //连接数据库
                if (conn == null || conn.isClosed()) {
                    String dburl = "jdbc:mysql://localhost:3306/daily-test";
                    Class.forName("com.mysql.jdbc.Driver");
                    conn = DriverManager.getConnection(dburl, "root", "root");
                    st = conn.createStatement();
                }
                mGetStockPrice(st, "0000011");//取得指定股票的价格，注意股票代码7为，最后一位表示，1表示上证，2表示深证
                mGetStockPrice(st, "3990012");
                mGetStockPrice(st, "0000392");
                mGetStockPrice(st, "2000392");
                mGetStockPrice(st, "601006");
                mGetStockPrice(st, "601006");
                mGetStockPrice(st, "601001");
                System.out.println();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        public void mGetStockPrice(Statement st, String stockCode) {
            String str = "";
            try {
                System.out.print("+");
                str = mGetUrlHtml("http://hqdigi2.eastmoney.com/EM_Quote2010NumericApplication/CompatiblePage.aspx?Type=ZT&jsName=js_fav&fav=" + stockCode, "UTF-8");
                //网址返回的数据格式
                //var js_fav={favif:["1,000001,上证指数,2765.92,-1.31%,1003.01"]};
                //如果股票信息不为空
                if (str.indexOf(",") > -1) {
                    String[] arr = str.split(",");
                    //如果股票信息是有效的
                    if (arr.length == 6) {
                        //如果上涨就是红色，如果下跌就是绿色
                        if (arr[4].startsWith("-"))
                            str = "<span style=\"color:green;font-size:10pt;\">" + arr[2] + " " + arr[3] + " " + arr[4] + "</span>";
                        else
                            str = "<span style=\"color:red;font-size:10pt;\">" + arr[2] + " " + arr[3] + " " + arr[4] + "</span>";
                        str = str.replaceAll("'", "\'");
                        //判断数据库中是否已经存在股票代码，存在即新增，如果不存在就更新价格
                        ResultSet rs = st.executeQuery("select count(*) from stock_prop where propType='stockPrice' and propName='" + stockCode + "'");
                        rs.next();
                        if ("0".equals(String.valueOf(rs.getObject(1))))
                            st.executeUpdate("insert into stock_prop(propType,propName,propValue) values('stockPrice','" + stockCode + "','" + str + "')");
                        else
                            st.executeUpdate("update stock_prop set propValue='" + str + "' where propType='stockPrice' and propName='" + stockCode + "'");
                        rs.close();
                    }
                }
                System.out.print("*");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        public String mGetUrlHtml(String urlStr, String charSet) {
            String html = "";
            try {
                //读取网址中的信息
                URL url = new URL(urlStr);
                URLConnection connection = url.openConnection();
                String sCurrentLine = "";
                StringBuffer sTotalString = new StringBuffer();
                BufferedReader l_reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), charSet));
                while ((sCurrentLine = l_reader.readLine()) != null)
                    sTotalString.append(sCurrentLine + "\r\n");
                html = sTotalString.toString();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return html;
        }
    }
}
