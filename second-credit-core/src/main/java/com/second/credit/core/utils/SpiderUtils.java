package com.second.credit.core.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @note 网络爬虫抓取工具
 * @author wangmeng
 * @date 2016-1-2 下午12:26:33
 */
public class SpiderUtils {

    // URLConnection conn = null;
    HttpURLConnection conn = null;

    public void spiderData(String urlStr) {
        try {
            URL url = new URL(urlStr);
            // conn = url.openConnection();
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("CONTENT_TYPE", "application/octet-stream");
            conn.setUseCaches(false);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            InputStream in = conn.getInputStream();
            InputStreamReader inReader = new InputStreamReader(in);
            BufferedReader bufferReader = new BufferedReader(inReader);
            char[] cArray = new char[100];
            while (bufferReader.read() == -1) {
                bufferReader.read(cArray);
            }
            in.close();
            inReader.close();
            bufferReader.close();
            String ss = new String(cArray);

            System.out.println(ss);
            // OutputStream out = conn.getOutputStream();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接
        }
    }

    public void test() {
        spiderData("http://www.iqianjin.com/");
    }

    public void test1() {
        HttpClientUtils http = new HttpClientUtils("http://www.iqianjin.com/");
        try {
            String str = http.doPost();
            System.out.println(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
