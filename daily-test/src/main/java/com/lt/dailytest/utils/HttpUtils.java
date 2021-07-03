package com.lt.dailytest.utils;

import com.alibaba.fastjson.JSON;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;
import org.apache.http.client.fluent.Request;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.springframework.util.Assert;

/**
 * @author tong.luo
 * @description HttpUtils
 * @date 2021/7/2 16:52
 */
public class HttpUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpUtils.class);

    public static final int timeout = 10;
    public static final int connectionTimeout = 10000;
    public static final int requestTimeout = 20000;
    public static final int readTimeout = 20000;

    /**
     * decode解码函数
     *
     * @param param
     * @return
     * @throws java.io.UnsupportedEncodingException
     */
    public static String decodeParm(String param) throws UnsupportedEncodingException {
        param = URLDecoder.decode(param, "UTF-8");
        return param;
    }

    /**
     * post 请求
     *
     * @param url
     * @return
     */
    public static String post(String url) {
        return post(url, "");
    }

    /**
     * post请求
     *
     * @param url
     * @param data
     * @return
     */
    public static String post(String url, String data) {
        return httpPost(url, data);
    }

    /**
     * 发送http post请求
     *
     * @param url      url
     * @param instream post数据的字节流
     * @return
     */
    public static String post(String url, InputStream instream) {

        Assert.notNull(url, "请求url不允许为空");
        Assert.notNull(instream, "请求内容不允许为空");
        try {
            HttpEntity entity = Request.Post(url).bodyStream(instream, ContentType.create("text/html", Consts.UTF_8))
                    .execute().returnResponse().getEntity();
            return entity != null ? EntityUtils.toString(entity) : null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * get请求
     *
     * @param url
     * @return
     */
    public static String get(String url) {
        return httpGet(url);
    }

    /**
     * get请求
     *
     * @param url
     * @return
     */
    public static <T> String get(String url, T t) {
        Assert.notNull(url, "请求url不允许为空");
        Assert.notNull(t, "请求内容不允许为空");
        String jsonStr = JSON.toJSONString(t);
        Map<String, String> parameters = JSON.parseObject(jsonStr, Map.class);
        //Map<String, String> parameters = JsonConvertUtil.json2Map(jsonStr);
        String parametersStr = getParameters(parameters);
        url = url + parametersStr;
        // LoggerUtil.log(Level.DEBUG, "生成的GET请求地址信息为 :{}", url);
        LOGGER.debug("生成的GET请求地址信息为 :{}", url);
        return httpGet(url);
    }

    private static String getParameters(Map<String, String> parameters) {

        StringBuffer param = new StringBuffer();
        int i = 0;
        for (String key : parameters.keySet()) {
            if (i == 0)
                param.append("?");
            else
                param.append("&");
            try {
                param.append(key).append("=").append(URLEncoder.encode(parameters.get(key), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            i++;
        }

        return param.toString();
    }


    /**
     * post 请求
     *
     * @param url
     * @param data
     * @return
     */
    private static String httpPost(String url, String data) {
        Assert.notNull(url, "请求url不允许为空");
        Assert.notNull(data, "请求内容不允许为空");
        try {
            HttpEntity entity = Request.Post(url).connectTimeout(connectionTimeout).socketTimeout(requestTimeout)
                    .bodyString(data, ContentType.create("text/html", Consts.UTF_8))
                    .execute().returnResponse().getEntity();
            return entity != null ? EntityUtils.toString(entity) : null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * post 请求
     *
     * @param url
     * @param data
     * @return
     */
    private static String httpPost(String url, String data, Map<String, Object> headers) {
        Assert.notNull(url, "请求url不允许为空");
        Assert.notNull(data, "请求内容不允许为空");
        Assert.notEmpty(headers, "请求头不允许为空");
        try {
            Request request = Request.Post(url).connectTimeout(connectionTimeout).socketTimeout(requestTimeout)

                    .bodyString(data, ContentType.create("text/html", Consts.UTF_8));
            for (Map.Entry<String, Object> entry : headers.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                request.addHeader(key, String.valueOf(value));
            }
            HttpEntity entity = request.execute().returnResponse().getEntity();
            return entity != null ? EntityUtils.toString(entity) : null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 上传文件
     *
     * @param url  URL
     * @param file 需要上传的文件
     * @return
     */
    public static String postFile(String url, File file) {
        return postFile(url, null, file);
    }

    /**
     * 上传文件
     *
     * @param url  URL
     * @param name 文件的post参数名称
     * @param file 上传的文件
     * @return
     */
    public static String postFile(String url, String name, File file) {


        Assert.notNull(url, "请求url不允许为空");
        Assert.notNull(name, "请求内容不允许为空");
        Assert.notNull(file, "请求内容不允许为空");
        try {
            HttpEntity reqEntity = MultipartEntityBuilder.create().addBinaryBody(name, file).build();
            Request request = Request.Post(url).connectTimeout(connectionTimeout).socketTimeout(requestTimeout);
            request.body(reqEntity);
            HttpEntity resEntity = request.execute().returnResponse().getEntity();
            return resEntity != null ? EntityUtils.toString(resEntity) : null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Json请求
     *
     * @param url  请求路径
     * @param json json格式的字符串
     * @return 请求结果
     */
    public static String postJson(String url, String json) {

        Assert.notNull(url, "请求url不允许为空");
        Assert.notNull(json, "请求内容不允许为空");
        try {
            HttpEntity entity = Request.Post(url).socketTimeout(requestTimeout).connectTimeout(connectionTimeout)
                    .bodyString(json, ContentType.create("application/json", Consts.UTF_8))
                    .execute().returnResponse().getEntity();
            return entity != null ? EntityUtils.toString(entity) : null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * post方式请求json格式数据
     *
     * @param
     * @return String 返回类型
     * @title postJson
     * @description post方式请求json格式数据
     */
    public static String postJson(String url, String data, int connectTimeout, int socketTimeout) throws Exception {

        Assert.notNull(url, "请求url不允许为空");
        Assert.notNull(data, "请求内容不允许为空");
        HttpEntity entity = Request.Post(url).connectTimeout(connectTimeout).socketTimeout(socketTimeout)
                .bodyString(data, ContentType.create("application/json", Consts.UTF_8)).execute().returnResponse()
                .getEntity();
        return entity != null ? EntityUtils.toString(entity) : null;
    }

    /**
     * 下载文件
     *
     * @param url URL
     * @return 文件的二进制流，客户端使用outputStream输出为文件
     */
    public static byte[] getFile(String url) {
        Assert.notNull(url, "请求url不允许为空");
        try {
            Request request = Request.Get(url);
            request.connectTimeout(connectionTimeout).socketTimeout(requestTimeout);
            HttpEntity resEntity = request.execute().returnResponse().getEntity();
            return EntityUtils.toByteArray(resEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 发送get请求
     *
     * @param url
     * @return
     */
    private static String httpGet(String url) {

        Assert.notNull(url, "请求url不允许为空");
        try {
            HttpEntity entity = Request.Get(url).connectTimeout(connectionTimeout).socketTimeout(requestTimeout).execute().returnResponse().getEntity();
            return entity != null ? EntityUtils.toString(entity, "UTF-8") : null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static class TrustAnyTrustManager implements X509TrustManager {

        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[]{};
        }
    }

    private static class TrustAnyHostnameVerifier implements HostnameVerifier {

        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }

    /**
     * post方式请求服务器(https协议)
     *
     * @param url     请求地址
     * @param content 参数
     * @param charset 编码
     * @return
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     * @throws IOException
     */
    public static String postJsonHttps(String url, String content, String charset) {
        Assert.notNull(url,"请求url不允许为空");
        Assert.notNull(content,"请求url不允许为空");
        Assert.notNull(charset,"请求url不允许为空");
        SSLContext sc = null;
        try {
            // content = URLEncoder.encode(content, charset);
            //LoggerUtil.log(Level.INFO, "message===" + content);
            LOGGER.info("message===" + content);
            sc = SSLContext.getInstance("SSL");
            sc.init(null, new TrustManager[]{new TrustAnyTrustManager()}, new java.security.SecureRandom());
            URL console = new URL(url);
            HttpsURLConnection conn = (HttpsURLConnection) console.openConnection();
            conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            conn.setSSLSocketFactory(sc.getSocketFactory());
            conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
            conn.setDoOutput(true);
            conn.connect();
            conn.setConnectTimeout(connectionTimeout);
            conn.setReadTimeout(readTimeout);
            DataOutputStream out = new DataOutputStream(conn.getOutputStream());
            out.write(content.getBytes(charset));
            // 刷新、关闭
            out.flush();
            out.close();
            InputStream is = conn.getInputStream();
            if (is != null) {
                ByteArrayOutputStream outStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len = 0;
                while ((len = is.read(buffer)) != -1) {
                    outStream.write(buffer, 0, len);
                }
                is.close();
                byte[] byteArray = outStream.toByteArray();
                String result = new String(byteArray);
                return result;
            }
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * post请求
     *
     * @param url    请求url
     * @param params 请求参数
     * @return 返回结果
     * @throws IOException 请求异常
     */
    public static String post(String url, List<NameValuePair> params) throws IOException {
        Assert.notNull(url,"请求url不允许为空");
        Assert.notNull(params,"请求url不允许为空");
        String result = null;
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(connectionTimeout).setConnectionRequestTimeout(connectionTimeout)
                .setSocketTimeout(requestTimeout).build();
        post.setConfig(requestConfig);
        if (null != params) {
            post.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
        }
        CloseableHttpResponse response = httpclient.execute(post);
        //LoggerUtil.log(Level.DEBUG, "请求链接：{}，返回结果====>{}", url, response.getEntity());
        LOGGER.info("请求链接：{}，返回结果====>{}", url, response.getEntity());
        StatusLine statusLine = response.getStatusLine();
        if (statusLine.getStatusCode() == 200) {
            HttpEntity httpEntity = response.getEntity();
            result = EntityUtils.toString(httpEntity);
        } else {
            int statusCode = statusLine.getStatusCode();
            String reasonPhrase = statusLine.getReasonPhrase();
            //LoggerUtil.log(Level.ERROR, "请求失败！请求链接为：{}，返回的请求代码为：{}，错误原因为:{}", url, statusCode, reasonPhrase);
            LOGGER.error("请求失败！请求链接为：{}，返回的请求代码为：{}，错误原因为:{}", url, statusCode, reasonPhrase);
            throw new IOException("请求失败！请求链接为：" + url + "，返回的请求代码为：" + statusCode + "，错误原因为:" + reasonPhrase);
        }
        return result;
    }

    /**
     * @param
     * @return String 返回类型
     * @title doPost
     * @description
     */
    @SuppressWarnings("unchecked")
    public static String doPost(String url, String json) throws IOException {
        // 设置参数
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        Map<String, String> map = JSON.parseObject(json, Map.class);
        Set<String> keySet = map.keySet();
        for (String key : keySet) {
            list.add(new BasicNameValuePair(key, map.get(key)));
        }
        if (list.size() > 0) {
            return post(url, list);
        } else {
            throw new IOException("请求失败！请求链接为：" + url + "，请求参数为：" + json);
        }
    }

    public static void main(String[] args) {
        String url = "http://localhost:8888/vpt_invoice_groupadmin_war_exploded/openInvoiceSetting/selectOrgTaxRateByOrgId.pt";
        String data = "{\"sellerId\":\"1308305247930126336\"}";
        String post = post(url, data);
        System.out.println(post);
    }
}
