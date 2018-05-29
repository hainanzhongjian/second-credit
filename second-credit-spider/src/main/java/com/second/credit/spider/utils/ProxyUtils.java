//package com.second.credit.spider.utils;
//
//import com.finup.datapi.insurance.common.utils.SpringContextHelper;
//import com.finup.datapi.insurance.fetcher.service.ProxyHostService;
//import org.apache.commons.lang3.StringUtils;
//import org.apache.commons.lang3.math.NumberUtils;
//import org.apache.http.HttpHost;
//import org.apache.log4j.Logger;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//import java.util.Random;
//
//public class ProxyUtils {
//
//    private ProxyUtils() {
//    }
//
//    private static final List<String> HTTP_PROXIES = Collections.synchronizedList(new ArrayList<String>()); // NOSONAR
//    private static volatile boolean inited = false; // NOSONAR
//    private static final Logger LOG = Logger.getLogger(ProxyUtils.class); // NOSONAR
//
//    public static synchronized void init() {
//        try {
//            HTTP_PROXIES.clear();
//            ProxyHostService proxyHostService = (ProxyHostService) SpringContextHelper.getBean("proxyHostService");
//            String hosts = proxyHostService.queryById("taobao");
//            if (StringUtils.isBlank(hosts)) {
//                return;
//            }
//            for (String proxyString : hosts.split(",")) {
//                proxyString = proxyString.trim();
//                if (proxyString.isEmpty()) {
//                    continue;
//                }
//                String[] addr = proxyString.split(":");
//                if (addr.length == 2) {
//                    HTTP_PROXIES.add(proxyString);
//                } else if (addr.length == 3) {
//                    HTTP_PROXIES.add(proxyString);
//                }
//            }
//        } catch (Exception e) {
//            LOG.error(e.getMessage(), e);
//        } finally {
//            inited = true;
//        }
//    }
//
//    /**
//     * 随机获取ip
//     *
//     * @return host
//     */
//    public static HttpHost getProxyHost() {
//        if (!inited) {
//            init();
//        }
//        int size = HTTP_PROXIES.size();
//        if (size < 1) {
//            return null;
//        }
//        int index = new Random().nextInt(size);// NOSONAR
//
//        String proxy = HTTP_PROXIES.get(index);
//        if (StringUtils.isNotBlank(proxy) && proxy.contains(":")) {
//            String arr[] = proxy.split(":"); // NOSONAR
//            return new HttpHost(arr[0], NumberUtils.toInt(arr[1]));
//        }
//        return null;
//    }
//
//}
