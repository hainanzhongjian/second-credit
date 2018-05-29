package com.second.credit.spider.utils;

import org.apache.http.conn.ssl.*;
import org.apache.log4j.Logger;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * @Description:http ssl 认证握手
 * @author:lixin
 * @time:2016年8月31日 上午11:34:31
 */
public class SSLUtils {
    private static final Logger LOGGER = Logger.getLogger(SSLUtils.class);

    private SSLUtils() {
    }

    /**
     * @param file
     * @param password
     * @return
     * @throws Exception
     */
    public static SSLConnectionSocketFactory createSSLConnectionSocketFactory(File file, String password)
            throws Exception { // NOSONAR
        KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
        FileInputStream instream = new FileInputStream(file);
        try {
            trustStore.load(instream, password.toCharArray());
        } finally {
            instream.close();
        }

        // Trust own CA and all self-signed certs
        SSLContext sslcontext = SSLContexts.custom().loadTrustMaterial(trustStore, new TrustSelfSignedStrategy())
                .build();
        // Allow TLSv1 protocol only
        return new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" }, null,
                SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
    }

    /**
     * @param in
     * @param password
     * @return
     */
    public static SSLConnectionSocketFactory createSSLConnectionSocketFactory(InputStream in, String password) {
        KeyStore trustStore = null;
        try {
            trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            trustStore.load(in, password.toCharArray());
            // Trust own CA and all self-signed certs
            SSLContext sslcontext = SSLContexts.custom().loadTrustMaterial(trustStore, new TrustSelfSignedStrategy())
                    .build();
            // Allow TLSv1 protocol only
            return new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" }, null,
                    SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
        return null;
    }

    /***
     * @param file
     * @return
     */
    public static SSLConnectionSocketFactory createSSLConnectionSocketFactory(String file) {
        InputStream in = null;
        KeyStore trustStore = null;
        try {
            trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            in = SSLUtils.class.getResourceAsStream(file);
            trustStore.load(in, "123456".toCharArray());// NOSONAR
            // Trust own CA and all self-signed certs
            SSLContext sslcontext = SSLContexts.custom().loadTrustMaterial(trustStore, new TrustSelfSignedStrategy())
                    .build();
            // Allow TLSv1 protocol only
            return new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" }, null,
                    SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
        return null;
    }

    /**
     * @return
     */
    public static SSLContext gettrustAllSSLContext() {
        try {
            return new SSLContextBuilder().loadTrustMaterial(null, new TrustAllStrategy()).build();
        } catch (KeyManagementException | NoSuchAlgorithmException | KeyStoreException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * @Description: 内部类 @author:lixin @time:2016年8月31日 上午11:42:23
     */
    public static class TrustAllStrategy implements TrustStrategy {
        // 信任所有
        @Override
        public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            return true;
        }
    }

    /**
     * 创建捂手连接
     */
    public final static SSLConnectionSocketFactory TRUAT_ALL_SSLSF = new SSLConnectionSocketFactory(
            gettrustAllSSLContext(), null);

    public final static SSLConnectionSocketFactory SSL_SOCKET_TLSV1 = new SSLConnectionSocketFactory(
            gettrustAllSSLContext(), new String[] { "TLSv1" }, null, null);

}
