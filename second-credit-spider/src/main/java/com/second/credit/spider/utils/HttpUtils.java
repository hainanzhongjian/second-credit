package com.second.credit.spider.utils;

import com.second.credit.spider.capture.model.fetcher.HttpResultDto;
import com.second.credit.spider.utils.SSLUtils;
import com.second.credit.spider.utils.Base64Utils;
import com.second.credit.spider.utils.MyPlainConnectionSocketFactory;
import com.second.credit.spider.utils.MySSLConnectionSocketFactory;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.http.*;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
//import org.openqa.selenium.Proxy;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.firefox.FirefoxProfile;
//import org.openqa.selenium.phantomjs.PhantomJSDriver;
//import org.openqa.selenium.phantomjs.PhantomJSDriverService;
//import org.openqa.selenium.remote.CapabilityType;
//import org.openqa.selenium.remote.DesiredCapabilities;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HttpUtils {

    private static final Logger logger = Logger.getLogger(HttpUtils.class);

    private HttpUtils() {
    }

    private static final Logger LOG = Logger.getLogger(HttpUtils.class);

//    public static String taobaoProxyMode = PropertiesUtil.getProps("taobao.proxy.mode", "socks"); // NOSONAR
//    public static String CQFUND_IS_NOT_PROXY = PropertiesUtil.getProps("CQFUND_IS_NOT_PROXY");// NOSONAR

    private static final String DEFAULT_USER_AGENT = "Mozilla/5.0 (Windows NT 6.3; WOW64; rv:32.0) Gecko/20100101 Firefox/33.0";
    private static final String UTF_8 = "UTF-8";
    private static final String GBK = "GBK";
    private static final String IEPROXY_IP = "101.69.178.161"; // NOSONAR
    private static final int IEPROXY_PORT = 8888;
    private static final String FIDDLER_IP = "127.0.0.1"; // NOSONAR
    private static final int FIDDLER_PORT = 8888;
    private static final HttpHost PROXY_FIDDLER = new HttpHost(FIDDLER_IP, FIDDLER_PORT, "http");
    private static boolean userFiddler = false; // NOSONAR
    private static RequestConfig DEFAULT_REQUEST_CONFIG = null;
    private static int timeout = 60 * 1000;
    private static int defaultMaxPerRoute = 20;

    public static HttpPost post(String url) {
        return post(url, null);
    }

    public static HttpPost post(String url, Map<String, Object> params) {
        return post(url, params, DEFAULT_USER_AGENT);
    }

    public static HttpPost post(String url, Map<String, Object> params, String userAgent) {
        HttpPost result = new HttpPost(url);
        result.addHeader("User-Agent", userAgent == null ? DEFAULT_USER_AGENT : userAgent);
        if (params != null && !params.isEmpty()) {
            result.setEntity(buildParams(params));
        }
        result.setConfig(copyDefaultConfig().build());
        return result;
    }

    public static HttpConne

    public static HttpGet get(String url) {
        return get(url, null);
    }

    public static HttpGet get(String url, Map<String, Object> params) {
        url += buildParamString(params).replaceFirst("&", "?"); // NOSONAR
        HttpGet result = new HttpGet(url);
        result.addHeader("User-Agent", DEFAULT_USER_AGENT);
        result.setConfig(copyDefaultConfig().build());
        return result;
    }

    public static RequestConfig.Builder copyDefaultConfig() {
        RequestConfig.Builder builder = RequestConfig.copy(getDefaultRequestConfig());
        if (userFiddler || Boolean.valueOf(System.getProperty("use.fiddler", "false"))) {
            builder.setProxy(PROXY_FIDDLER);
        }
        return builder;
    }

    public static RequestConfig getDefaultRequestConfig() {
        timeout = 60 * 1000;
        if (DEFAULT_REQUEST_CONFIG == null) {
            synchronized (HttpUtils.class) {
                if (DEFAULT_REQUEST_CONFIG == null) {
                    RequestConfig.Builder builder = RequestConfig.custom();
                    builder.setRedirectsEnabled(false).setRelativeRedirectsAllowed(false);
                    builder.setCookieSpec(CookieSpecs.BROWSER_COMPATIBILITY);
                    // connect to a url 1min
                    builder.setConnectTimeout(timeout);
                    // socket inputstream.read() 2min
                    builder.setSocketTimeout(timeout * 2);
                    DEFAULT_REQUEST_CONFIG = builder.build();
                }
            }
        }
        return DEFAULT_REQUEST_CONFIG;
    }

    public static UrlEncodedFormEntity buildParams(Map<String, ? extends Object> params) {
        return buildParams(params, UTF_8);
    }

    @SuppressWarnings("rawtypes")
    public static UrlEncodedFormEntity buildParams(Map<String, ? extends Object> params, String encoding) {
        if (params == null || params.isEmpty()) {
            return null;
        }
        List<NameValuePair> parameters = new ArrayList<>();
        for (Entry<String, ? extends Object> entry : params.entrySet()) {
            Object value = entry.getValue();
            if (value != null) {
                if (value instanceof List) {
                    for (Object o : (List) value) { // NOSONAR
                        if (o != null) {
                            parameters.add(new BasicNameValuePair(entry.getKey(), o.toString()));
                        }
                    }
                } else {
                    parameters.add(new BasicNameValuePair(entry.getKey(), value.toString()));
                }
            } else {
                parameters.add(new BasicNameValuePair(entry.getKey(), null));
            }
        }
        return new UrlEncodedFormEntity(parameters, Charset.forName(encoding));
    }

    public static String buildParamString(Map<String, ? extends Object> params) {
        return buildParamString(params, UTF_8);
    }

    public static String buildParamString(Map<String, ? extends Object> params, String encoding) {
        if (params == null || params.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        try {
            for (Entry<String, ? extends Object> entry : params.entrySet()) {
                Object value = entry.getValue();
                value = value == null ? "" : value.toString();
                sb.append("&").append(URLEncoder.encode(entry.getKey(), encoding)).append("=")
                        .append(URLEncoder.encode((String) value, encoding));
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return sb.toString();
    }

    public static CloseableHttpClient getHttpClient() {
        return getHttpClient(false, null);
    }

    public static CloseableHttpClient getHttpClient(boolean trustAllSSL) {
        return getHttpClient(trustAllSSL, null);
    }

    public static CloseableHttpClient getHttpClient(SSLConnectionSocketFactory sslcsf, CookieStore cookieStore) {
        return getHttpClient(sslcsf, cookieStore, false);
    }

    public static CloseableHttpClient getHttpClient(SSLConnectionSocketFactory sslcsf, CookieStore cookieStore,
            boolean useProxy) {
        HttpClientBuilder builder = getBuilder();
        if (cookieStore != null) {
            builder.setDefaultCookieStore(cookieStore);
        }
        builder.setSSLSocketFactory(sslcsf);
        if (useProxy) {
//            builder.setProxy(ProxyUtils.getProxyHost());
        }
        return builder.build();
    }

    public static CloseableHttpClient getHttpClient(boolean trustAllSSL, CookieStore cookieStore) {
        HttpClientBuilder builder = getBuilder();
        if (cookieStore != null) {
            builder.setDefaultCookieStore(cookieStore);
        }
        if (trustAllSSL) {
            // 表示层采用TLS协议，保证数据的完整性和私密性
            builder.setSSLSocketFactory(SSLUtils.SSL_SOCKET_TLSV1);
        }
        return builder.build();
    }

    public static CloseableHttpClient getHttpClient(boolean trustAllSSL, CookieStore cookieStore, boolean isFiddler) {
        if (isFiddler) {
            userFiddler = true;
        }
        HttpClientBuilder builder = getBuilder();
        if (cookieStore != null) {
            builder.setDefaultCookieStore(cookieStore);
        }
        if (trustAllSSL) {
            builder.setSSLSocketFactory(SSLUtils.TRUAT_ALL_SSLSF);
        }
        return builder.build();
    }

    public static CloseableHttpClient getHttpClient(CookieStore cookieStore, InetSocketAddress socks5Proxy) {

        HttpClientBuilder builder = getBuilder(socks5Proxy);
        if (cookieStore != null) {
            builder.setDefaultCookieStore(cookieStore);
        }

        return builder.build();
    }

    public static CloseableHttpClient getHttpClient(CookieStore cookieStore, InetSocketAddress socks5Proxy,
                                                    boolean isHttpSameAsHttps) {

        HttpClientBuilder builder = getBuilder(socks5Proxy, isHttpSameAsHttps);
        if (cookieStore != null) {
            builder.setDefaultCookieStore(cookieStore);
        }
        return builder.build();
    }

    /**
     * 使用指定的sslcontext和socks proxy创建httpclient
     *
     * @author zhuyuhang
     * @param ssLContext
     * @param cookieStore
     * @param proxy
     * @return
     */
    public static CloseableHttpClient getHttpClientWithSocksProxy(SSLContext ssLContext, CookieStore cookieStore,
            InetSocketAddress proxy) {
        HttpClientBuilder builder;
        if (proxy != null) {
            LOG.info("use socks5 proxy " + proxy.toString());
            builder = getBuilder(new MySSLConnectionSocketFactory(ssLContext, proxy));
        } else {
            builder = getBuilder(new MySSLConnectionSocketFactory(ssLContext));
        }
        if (cookieStore != null) {
            builder.setDefaultCookieStore(cookieStore);
        }
        return builder.build();
    }

    private static HttpClientBuilder getBuilder(InetSocketAddress socks5Proxy) {
        return getBuilder(new MySSLConnectionSocketFactory(SSLUtils.gettrustAllSSLContext(), socks5Proxy));
    }

    private static HttpClientBuilder getBuilder(InetSocketAddress socks5Proxy, boolean isHttpSameAsHttps) {
        return getBuilder(new MySSLConnectionSocketFactory(SSLUtils.gettrustAllSSLContext(), socks5Proxy),
                isHttpSameAsHttps);
    }

    private static HttpClientBuilder getBuilder() {
        // 表示层采用TLS协议，保证数据的完整性和私密性
        return getBuilder(SSLUtils.SSL_SOCKET_TLSV1);
    }

    private static HttpClientBuilder getBuilder(LayeredConnectionSocketFactory sslSocketFactory) {
        return getBuilder(sslSocketFactory, true);
    }

    private static HttpClientBuilder getBuilder(LayeredConnectionSocketFactory sslSocketFactory, // NOSONAR
            boolean isHttpSameAsHttps) {
        HttpClientBuilder builder = HttpClients.custom();
        if (userFiddler || Boolean.valueOf(System.getProperty("use.fiddler", "false"))) {
            builder.setProxy(PROXY_FIDDLER);
        }
        builder.setRetryHandler(new DefaultHttpRequestRetryHandler(1, false));// 重试一次

        RegistryBuilder<ConnectionSocketFactory> registryBuilder = RegistryBuilder.<ConnectionSocketFactory> create()
                .register("https", sslSocketFactory);
        // 联通的http不能使用代理
        if (!isHttpSameAsHttps) {
            registryBuilder.register("http", PlainConnectionSocketFactory.getSocketFactory());
        } else {
            if (sslSocketFactory != null && sslSocketFactory instanceof MySSLConnectionSocketFactory) {
                InetSocketAddress proxy = ((MySSLConnectionSocketFactory) sslSocketFactory).getProxy();
                LOG.info("MyPlainConnectionSocketFactory " + proxy);
                registryBuilder.register("http", new MyPlainConnectionSocketFactory(proxy));
            } else {
                registryBuilder.register("http", PlainConnectionSocketFactory.getSocketFactory());
            }
        }
        final PoolingHttpClientConnectionManager poolingmgr = new PoolingHttpClientConnectionManager(
                registryBuilder.build());
        poolingmgr.closeIdleConnections(0, TimeUnit.SECONDS);
        poolingmgr.setDefaultMaxPerRoute(defaultMaxPerRoute);
        poolingmgr.setMaxTotal(defaultMaxPerRoute * 4);
        RequestConfig.Builder requestConfigBuilder = RequestConfig.custom();
        // 2min socket read
        requestConfigBuilder.setSocketTimeout(timeout * 2);
        // 1min connect to a url
        requestConfigBuilder.setConnectTimeout(timeout);
        // 30s get a connection from pool
        requestConfigBuilder.setConnectionRequestTimeout(timeout / 2);

        builder.setDefaultRequestConfig(requestConfigBuilder.build());
        builder.setConnectionManager(poolingmgr);
        // 链接保持策略
        ConnectionKeepAliveStrategy myStrategy = new ConnectionKeepAliveStrategy() { // NOSONAR
            public long getKeepAliveDuration(HttpResponse response, HttpContext context) { // NOSONAR
                // Honor 'keep-alive' header
                HeaderElementIterator it = new BasicHeaderElementIterator(response.headerIterator(HTTP.CONN_KEEP_ALIVE));
                while (it.hasNext()) {
                    HeaderElement he = it.nextElement();
                    String param = he.getName();
                    String value = he.getValue();
                    if (value != null && "timeout".equalsIgnoreCase(param)) {
                        try {
                            return Long.parseLong(value) * 1000;
                        } catch (NumberFormatException ignore) {
                            LOG.error("getBuilder", ignore);
                        }
                    }
                }
                // keep alive for 120 seconds
                return (long) 120 * 1000;
            }
        };
        builder.setKeepAliveStrategy(myStrategy);
        return builder;
    }

//    public static DesiredCapabilities getWebDriverByProxy() {
//        String PROXY = IEPROXY_IP + ":" + IEPROXY_PORT;
//        Proxy proxy = new Proxy();
//        proxy.setHttpProxy(PROXY);
//        proxy.setFtpProxy(PROXY);
//        proxy.setSslProxy(PROXY);
//        DesiredCapabilities cap = new DesiredCapabilities();
//        cap.setCapability(CapabilityType.PROXY, proxy);
//        return cap;
//    }

    public static String getFirstCookie(CookieStore cookieStore, String name) {
        List<String> values = getCookie(cookieStore, name);
        return values.isEmpty() ? null : values.get(0);
    }

    public static List<String> getCookie(CookieStore cookieStore, String name) {
        List<String> result = new ArrayList<>();
        if (cookieStore == null) {
            return result;
        }
        for (Cookie cookie : cookieStore.getCookies()) {
            if (name.equals(cookie.getName())) {
                result.add(cookie.getValue());
            }
        }
        return result;
    }

    public static HttpPost buildPostFromHtml(String html) {
        return buildPostFromHtml(html, "form");
    }

    public static HttpPost buildPostFromHtml(String html, String selector) {
        return buildPostFromHtml(html, selector, HttpUtils.GBK);
    }

    public static HttpPost buildPostFromHtml(String html, String selector, String charSet) {
        Document document = Jsoup.parse(html, charSet == null ? HttpUtils.GBK : charSet);
        Elements elements = document.select(selector);
        if (elements.size() > 0) {
            Element form = elements.get(0);
            String url = form.attr("action");
            Elements inputs = form.select("input[type=hidden]");
            Map<String, Object> params = new HashMap<>();
            for (int i = 0; i < inputs.size(); i++) {
                params.put(inputs.get(i).attr("name"), inputs.get(i).attr("value"));
            }
            return HttpUtils.post(url, params);
        }
        return null;
    }

    public static Map<String, Object> getFormUrlAndParamsFromHtml(String html, String selector) {
        return getFormUrlAndParamsFromHtml(html, selector, HttpUtils.GBK);
    }

    public static Map<String, Object> getFormUrlAndParamsFromHtml(String html, String selector, String charSet) {
        Document document = Jsoup.parse(html, charSet == null ? HttpUtils.GBK : charSet);
        Elements elements = document.select(selector);
        if (elements.size() > 0) {
            Element form = elements.get(0);
            String url = form.attr("action");
            Elements inputs = form.select("input[type=hidden]");
            Map<String, Object> params = new HashMap<>();
            for (int i = 0; i < inputs.size(); i++) {
                params.put(inputs.get(i).attr("name"), inputs.get(i).attr("value"));
            }
            Map<String, Object> result = new HashMap<>();
            result.put("url", url);
            result.put("params", params);
            return result;
        }
        return null;
    }

    /**
     * 获取input[type=hidden]
     * 
     * @author zhuyuhang
     * @param html
     * @return
     */
    public static Map<String, Object> buildHiddenInputParamsFromHtml(String html) {
        return buildHiddenInputParamsFromHtml(html, HttpUtils.GBK);
    }

    /**
     * 获取input[type=hidden]
     * 
     * @author zhuyuhang
     * @param html
     * @param charSet
     * @return
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> buildHiddenInputParamsFromHtml(String html, String charSet) {
        Document document = Jsoup.parse(html, charSet == null ? HttpUtils.GBK : charSet);
        Elements inputs = document.select("input[type=hidden]");
        Map<String, Object> params = new HashMap<>();
        for (int i = 0; i < inputs.size(); i++) {
            String name = inputs.get(i).attr("name");
            String value = inputs.get(i).attr("value");
            if (params.get(name) != null) {
                Object v = params.get(name);
                if (v instanceof List) {
                    ((List<Object>) v).add(value);
                } else {
                    List<Object> l = new ArrayList<>();
                    l.add(v);
                    l.add(value);
                    params.put(name, l);
                }
            } else {
                params.put(name, value);
            }
        }
        return params;
    }

    /**
     * 获取input[type=hidden]
     * 
     * @author zhuyuhang
     * @param html
     * @param charSet
     * @return
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> buildHiddenInputParamsFromHtmlById(String html, String charSet) {
        Document document = Jsoup.parse(html, charSet == null ? HttpUtils.GBK : charSet);
        Elements inputs = document.select("input[type=hidden]");
        Map<String, Object> params = new HashMap<>();
        for (int i = 0; i < inputs.size(); i++) {
            String name = inputs.get(i).attr("id");
            String value = inputs.get(i).attr("value");
            if (params.get(name) != null) {
                Object v = params.get(name);
                if (v instanceof List) {
                    ((List<Object>) v).add(value);
                } else {
                    List<Object> l = new ArrayList<>();
                    l.add(v);
                    l.add(value);
                    params.put(name, l);
                }
            } else {
                params.put(name, value);
            }
        }
        return params;
    }

    public static String getCharsetFromContentType(String contentType) {
        if (StringUtils.isBlank(contentType)) {
            return null;
        }
        String[] cts = contentType.toLowerCase().split(";");
        for (String s : cts) {
            if (StringUtils.isNotBlank(s) && s.contains("charset")) {
                return s.split("=")[1];
            }
        }
        return null;
    }

    /**
     * 解析出url参数中的键值对 如 "index.jsp?Action=del&id=123"，解析出Action:del,id:123存入map中
     * 
     * @param URL
     *            url地址
     * @return url请求参数部分
     */
    public static Map<String, String> URLRequest(String URL) {
        Map<String, String> paramsMap = new HashMap<>();
        try {
            URI url = new URI(URL);
            List<NameValuePair> list = URLEncodedUtils.parse(url.getRawQuery(), Charset.forName("UTF-8"));
            for (NameValuePair nameValue : list) {
                paramsMap.put(nameValue.getName(), nameValue.getValue());
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return paramsMap;
    }

    /**
     * @author zhuyuhang
     * @param response
     * @param name
     * @return
     */
    public static String getHeader(CloseableHttpResponse response, String name) {
        Header[] headers = response.getHeaders(name);
        if (headers.length > 0) {
            return headers[0].getValue();
        }
        return null;
    }

    /**
     * 从header里获取Location
     * 
     * @author zhuyuhang
     * @param response
     * @return
     */
    public static String getLocationFromHeader(CloseableHttpResponse response) {
        return getLocationFromHeader(response, false);
    }

    /**
     * @author zhuyuhang
     * @param name
     * @param value
     * @param path
     * @param domain
     * @return
     */
    public static BasicClientCookie getCookie(String name, String value, String domain, String path) {
        BasicClientCookie clientCookie = new BasicClientCookie(name, value);
        clientCookie.setDomain(domain);
        clientCookie.setPath(path);
        return clientCookie;
    }

    public static String getLocationFromHeader(CloseableHttpResponse response, boolean closeResponse) {
        String result = getHeader(response, "Location");
        if (closeResponse) {
            try {
                response.close();
            } catch (IOException e) {
                logger.error(e);
            }
        }
        return result;
    }

    private static final Pattern RE_UNICODE = Pattern.compile("\\\\u([0-9a-zA-Z]{4})");

    public static String unicodeToString(String s) {
        Matcher m = RE_UNICODE.matcher(s);
        StringBuffer sb = new StringBuffer(s.length()); // NOSONAR
        while (m.find()) {
            m.appendReplacement(sb, Character.toString((char) Integer.parseInt(m.group(1), 16)));
        }
        m.appendTail(sb);
        return sb.toString();
    }

    public static File getCaptchaCodeImage(CloseableHttpClient client, String url) {
        return getCaptchaCodeImage(client, url, 1);
    }

    public static File getCaptchaCodeImage(CloseableHttpClient client, String url, int times) {
        return getCaptchaCodeImage(client, HttpUtils.get(url), times);
    }

    public static File getCaptchaCodeImage(CloseableHttpClient client, HttpGet get, int times) {
        LOG.info("验证码图片url:" + get.getURI().toString());
        try {
            CloseableHttpResponse response = client.execute(get);
            // 加上次数，免得陷入死循环中了
            if (response.getStatusLine().getStatusCode() != 200 && times <= 3) {
                times++; // NOSONAR
                String url = HttpUtils.getLocationFromHeader(response);
                return HttpUtils.getCaptchaCodeImage(client, url, times);
            }
////            File codeFile = new File(PropertiesUtil.getProps("captcha.dir"), System.currentTimeMillis() + ".jpg");// NOSONAR
//            FileUtils.copyInputStreamToFile(response.getEntity().getContent(), codeFile);
//            response.close();
//            LOG.info("获取验证码成功,codeFile.length:" + codeFile.length());
            return null;
        } catch (Exception e) {
            LOG.error("获取验证码图片失败", e);
        }
        return null;
    }

//    /**
//     * @note 获取融360验证码
//     */
//    public static File getRong360CaptchaCodeImage(String inputStr) {
//        try {
//            long systemTimes = System.currentTimeMillis();
//            String dir = PropertiesUtil.getProps("captcha.dir");
//            File codeFile = new File(dir, systemTimes + ".jpg");// NOSONAR
//            Base64Utils.decodeToFile(dir + File.separator + systemTimes + ".jpg", inputStr);
//            LOG.info("获取图片验证码成功,codeFile.length:" + codeFile.length());
//            return codeFile;
//        } catch (Exception e) {
//            LOG.error("获取验证码图片失败", e);
//        }
//        return null;
//    }

//    /**
//     * from cookieStore to driver
//     *
//     * @author zhuyuhang
//     * @param cookieStore
//     * @param driver
//     */
//    public static void copyCookies(CookieStore cookieStore, WebDriver driver) {
//        copyCookies(cookieStore, driver, false);
//    }

//    /**
//     * from cookieStore to driver
//     *
//     * @author zhuyuhang
//     * @param cookieStore
//     * @param driver
//     * @param clear
//     *            true driver.manage().deleteAllCookies();
//     */
//    public static void copyCookies(CookieStore cookieStore, WebDriver driver, boolean clear) {
//        if (clear) {
//            driver.manage().deleteAllCookies();
//        }
//        for (Cookie cookie : cookieStore.getCookies()) {
//            try {
//                driver.manage().addCookie(
//                        new org.openqa.selenium.Cookie(cookie.getName(), cookie.getValue(), cookie.getDomain(), cookie
//                                .getPath(), cookie.getExpiryDate()));
//            } catch (Exception e) {
//                logger.error(e);
//            }
//        }
//    }

//    /**
//     * from cookies to cookieStore
//     *
//     * @author zhuyuhang
//     * @param cookies
//     * @param cookieStore
//     */
//    public static void copyCookies(Set<org.openqa.selenium.Cookie> cookies, CookieStore cookieStore) {
//        copyCookies(cookies, cookieStore, false);
//    }

//    /**
//     * from cookies to cookieStore
//     *
//     * @author zhuyuhang
//     * @param cookies
//     * @param cookieStore
//     * @param clear
//     *            true cookieStore.clear();
//     */
//    public static void copyCookies(Set<org.openqa.selenium.Cookie> cookies, CookieStore cookieStore, boolean clear) {
//        if (clear) {
//            cookieStore.clear();
//        }
//        for (org.openqa.selenium.Cookie cookie : cookies) {
//            BasicClientCookie basicClientCookie = new BasicClientCookie(cookie.getName(), cookie.getValue());
//            basicClientCookie.setDomain(cookie.getDomain());
//            basicClientCookie.setPath(cookie.getPath());
//            cookieStore.addCookie(basicClientCookie);
//        }
//    }
//
//    public static WebDriver getPhantomJs(String socksProxy) {
//        DesiredCapabilities caps = new DesiredCapabilities();
//        caps.setJavascriptEnabled(true);
//        caps.setCapability("takesScreenshot", true);
//        caps.setCapability("acceptSslCerts", true);
//        caps.setCapability("locationContextEnabled", true);
//        caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
//                PropertiesUtil.getProps("phantomjs_exec_path"));
//        caps.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
//        if (socksProxy != null) {
//            caps.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, new String[] { "--proxy-type=socks5",
//                    "--proxy=" + socksProxy + "" });
//        }
//        return new PhantomJSDriver(caps);
//    }
//
//    public static WebDriver getWebDriver() {
//        return getWebDriver(null);
//    }
//
//    public static WebDriver getWebDriver(String useragent) {
//        WebDriver result;
//        if ("firefox".equals(PropertiesUtil.getProps("webdriver"))) {
//            if (useragent != null) {
//                FirefoxProfile firefoxProfile = new FirefoxProfile();
//                firefoxProfile.setPreference("general.useragent.override", useragent);
//                result = new FirefoxDriver(firefoxProfile);
//            } else {
//                result = new FirefoxDriver();
//            }
//
//        } else {
//            // ChromeDriver 使用
//            System.setProperty(PropertiesUtil.getProps("webdriver_lib"), PropertiesUtil.getProps("webdriver_path"));
//            result = new ChromeDriver();
//        }
//        result.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
//        return result;
//    }
//
//    private static final FirefoxProfile DEFAULT_FIREFOX_PROFILE = new FirefoxProfile();
//    static {
//        DEFAULT_FIREFOX_PROFILE.setPreference("permissions.default.image", 2);
//    }
//
//    public static FirefoxDriver getFirefoxDriver() {
//        return getFirefoxDriver(DEFAULT_FIREFOX_PROFILE);
//    }
//
//    public static FirefoxDriver getFoxDriverForImage(boolean isLoadImage) {
//        if (isLoadImage) {
//            FirefoxProfile firefoxProfile = new FirefoxProfile();
//            firefoxProfile.setPreference("permissions.default.image", 1);
//            return new FirefoxDriver(firefoxProfile);
//        }
//        return getFirefoxDriver(DEFAULT_FIREFOX_PROFILE);
//    }
//
//    public static FirefoxDriver getFirefoxDriver(FirefoxProfile firefoxProfile) {
//        if (firefoxProfile != null) {
//            return new FirefoxDriver(firefoxProfile);
//        }
//        return new FirefoxDriver();
//    }

    public static String resolvePath(String src, String url) {
        try {
            return new URL(src).toURI().resolve(url).toString();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return url;
    }

    public static void addAjaxRequest(HttpPost post) {
        post.addHeader("X-Requested-With", "XMLHttpRequest");
        post.addHeader("Accept", "application/xml, text/xml, */*");
    }

    public static void addAjaxRequest(HttpGet get) {
        get.addHeader("X-Requested-With", "XMLHttpRequest");
        get.addHeader("Accept", "application/xml, text/xml, */*");
    }

    /**
     * @note 执行post请求
     */
    public static HttpResultDto executePostWithResult(CloseableHttpClient client, String url) {
        return executePostWithResult(client, url, null);
    }

    /**
     * @note 执行post请求
     */
    public static HttpResultDto executePostWithResult(CloseableHttpClient client, String url, Map<String, Object> params) {
        HttpResultDto resultDto = new HttpResultDto();
        executeWithResult(client, url, true, false, params, resultDto);
        LOG.info("executeWithResult: executed url = " + url + " and redirectCounts = " + resultDto.redirectCounts);
        return resultDto;
    }

    /**
     * @note 执行post请求(不重定向)
     */
    public static HttpResultDto executePostNotRedirectWithResult(CloseableHttpClient client, String url,
            Map<String, Object> params) {
        HttpResultDto resultDto = new HttpResultDto();
        executeWithResult(client, url, true, true, params, resultDto);
        LOG.info("executeWithResult: executed url = " + url + " and redirectCounts = " + resultDto.redirectCounts);
        return resultDto;
    }

    /**
     * @note 执行get请求
     */
    public static HttpResultDto executeGetWithResult(CloseableHttpClient client, String url) {
        return executeGetWithResult(client, url, null);
    }

    /**
     * @note 执行get请求
     */
    public static HttpResultDto executeGetWithResult(CloseableHttpClient client, String url, Map<String, Object> params) {
        HttpResultDto resultDto = new HttpResultDto();
        executeWithResult(client, url, false, false, params, resultDto);
        LOG.info("executeWithResult: executed url = " + url + " and redirectCounts = " + resultDto.redirectCounts);
        return resultDto;
    }

    /**
     * @note 执行请求，返回请求结果
     * @param client
     * @param url
     *            ：请求地址
     * @param postTrue
     *            ：post请求：true get请求：false
     * @param params
     *            ：请求参数
     * @param resultDto
     *            ：结果值--此处使用到引用传递
     * @return
     * @author wangmeng
     * @date 2017年4月11日 下午4:04:02
     */
    public static void executeWithResult(CloseableHttpClient client, String url, boolean postTrue, // NOSONAR
            boolean redirectTrue, Map<String, Object> params, HttpResultDto resultDto) {
        // 执行重定向后的地址
        CloseableHttpResponse redirectResp = null;
        try {
            if (postTrue) {
                HttpPost redirectPost = post(url, params);
                LOG.info("executeWithResult: execute post url= " + getUrlAll(url, params));
                redirectResp = client.execute(redirectPost);
            } else {
                HttpGet redirectGet = get(url, params);
                LOG.info("executeWithResult: execute get url= " + redirectGet.getURI());
                redirectResp = client.execute(redirectGet);
            }
        } catch (IOException e) {
//            throw new DatapiException("executeWithResult: execute url = " + url + " has exception", e);
        }

        // 不执行重定向
        if (redirectTrue) {
            // 获取结果，并关闭reponse
            resultDto.result = executeResponseWithResult(redirectResp);
            executeResponseClosed(redirectResp);
            return;
        }

        // 通过状态行或者状态码
        String statusCode = String.valueOf(redirectResp.getStatusLine().getStatusCode());
        // 如果发现循环重定向，则执行
        if (statusCode.startsWith("3")) {
            // 重定向次数+1
            resultDto.redirectCounts += 1;
            // 重定向超过10次，报错，防止虚拟机栈溢出
            if (resultDto.redirectCounts > 10) {
//                throw new DatapiException("executeWithResult: execute url = " + url + " has runned ten ");
            }
            // 重新调用本方法--注意栈溢出--获取重定向地址
            String criculRedirectUrl = getRedirectUrl(redirectResp);
            // 关闭当前response
            executeResponseClosed(redirectResp);
            // 重新调用本方法
            executeWithResult(client, criculRedirectUrl, postTrue, false, null, resultDto);
            return;
        } else if (statusCode.startsWith("2")) {// 客户端请求成功
            // 获取结果，并关闭reponse
            resultDto.result = executeResponseWithResult(redirectResp);
            executeResponseClosed(redirectResp);
            return;
        } else {// 服务端错误（5XX）、客户端错误（4XX）、1XX 暂时归为此类
            logger.error("executeWithResult: execute url = " + url + " and resultCode = " + statusCode
                    + " has exception ");
        }
    }

    /**
     * @note 获取重定向地址
     * @param resp
     * @return
     * @author wangmeng
     * @date 2017年4月11日 下午3:12:17
     */
    private static String getRedirectUrl(CloseableHttpResponse resp) {
        // reponse 响应报头
        Header[] headerArray = resp.getHeaders("Location");
        if (headerArray == null || headerArray.length == 0) {
//            throw new DatapiException("getRedirectUrl: redirect url has exception");
        }
        // 重定向地址
        return headerArray[0].getValue();
    }

    /**
     * @note 执行获取reponse结果,并关闭reponse
     * @param resp
     * @author wangmeng
     * @return
     * @date 2017年3月20日 下午3:30:03
     */
    private static String executeResponseWithResult(CloseableHttpResponse resp) {
        try {
            return EntityUtils.toString(resp.getEntity(), UTF_8);
        } catch (ParseException | IOException e) {
            throw new RuntimeException("HttpUtils-executeResponseClosed: has exception", e);
        }
    }

    /**
     * @note 执行关闭reponse
     * @param resp
     * @author wangmeng
     * @date 2017年3月20日 下午3:30:03
     */
    private static void executeResponseClosed(CloseableHttpResponse resp) {
        try {
            resp.close();
        } catch (IOException e) {
//            throw new DatapiException("HttpUtils-executeResponseClosed: has exception", e);
        }
    }

    /**
     * @note 获取全部地址
     * @param url
     * @param params
     * @return
     * @author wangmeng
     * @date 2017年5月5日 下午6:39:36
     */
    private static String getUrlAll(String url, Map<String, Object> params) {
        if (MapUtils.isEmpty(params)) {
            return url;
        }
        StringBuilder paramStr = new StringBuilder(url);
        paramStr.append("?");
        for (Entry<String, Object> entry : params.entrySet()) {
            paramStr = paramStr.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        paramStr.replace(paramStr.length() - 1, paramStr.length(), "");
        return paramStr.toString();
    }

    /**
     * @note 执行post请求
     * @param client
     * @param post
     * @param json
     * @return
     * @author wangmeng
     * @date 2017年5月15日 下午4:03:29
     */
    public static String excutePostWithJsonParams(CloseableHttpClient client, HttpPost post, Object json) {
        try {
            StringEntity entity = new StringEntity(json.toString(), "utf-8");
            post.setEntity(entity);
            CloseableHttpResponse resp = client.execute(post);
            String result = EntityUtils.toString(resp.getEntity(), "UTF-8");
            resp.close();
            return result;
        } catch (Exception e) {
            throw new RuntimeException("HttpUtils-excuteResultPost: has exception", e);
        }
    }
}
