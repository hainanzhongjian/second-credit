package com.second.credit.spider.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

import java.io.*;

public class Base64Utils {

    private static final Logger logger = Logger.getLogger(Base64Utils.class);

    private Base64Utils() {
    }

    /** */
    /**
     * 文件读取缓冲区大小
     */
    private static final int CACHE_SIZE = 1024;

    /** */
    /**
     * <p>
     * BASE64字符串解码为二进制数据
     * </p>
     * 
     * @param base64
     * @return
     * @throws Exception
     */
    public static byte[] decode(String base64) {
        try {
            return new Base64().decode(base64);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /** */
    /**
     * <p>
     * 二进制数据编码为BASE64字符串
     * </p>
     * 
     * @param bytes
     * @return
     * @throws Exception
     */
    public static String encode(byte[] bytes) {
        return new Base64().encodeToString(bytes);
    }

    /** */
    /**
     * <p>
     * 将文件编码为BASE64字符串
     * </p>
     * <p>
     * 大文件慎用，可能会导致内存溢出
     * </p>
     * 
     * @param filePath
     *            文件绝对路径
     * @return
     * @throws Exception
     */
    public static String encodeFile(String filePath) {
        try {
            byte[] bytes = fileToByte(filePath);
            return encode(bytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /** */
    /**
     * <p>
     * BASE64字符串转回文件
     * </p>
     * 
     * @param filePath
     *            文件绝对路径
     * @param base64
     *            编码字符串
     * @throws Exception
     */
    public static void decodeToFile(String filePath, String base64) {
        try {
            byte[] bytes = decode(base64);
            byteArrayToFile(bytes, filePath);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /** */
    /**
     * <p>
     * 文件转换为二进制数组
     * </p>
     * 
     * @param filePath
     *            文件路径
     * @return
     * @throws Exception
     */
    public static byte[] fileToByte(String filePath) { // NOSONAR
        byte[] data = new byte[0];
        File file = new File(filePath);// NOSONAR
        if (!file.exists()) {
            return data;
        }

        FileInputStream in = null;
        ByteArrayOutputStream out = null;
        try {
            in = new FileInputStream(file);
            out = new ByteArrayOutputStream(2048);
            byte[] cache = new byte[CACHE_SIZE];
            int nRead = 0;
            while ((nRead = in.read(cache)) != -1) {
                out.write(cache, 0, nRead);
                out.flush();
            }

            return out.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    logger.error("fileToByte", e);
                }
            }

            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    logger.error("fileToByte", e);
                }
            }
        }

    }

    /** */
    /**
     * <p>
     * 二进制数据写文件
     * </p>
     * 
     * @param bytes
     *            二进制数据
     * @param filePath
     *            文件生成目录
     */
    public static void byteArrayToFile(byte[] bytes, String filePath) {
        InputStream in = new ByteArrayInputStream(bytes);
        File destFile = new File(filePath);// NOSONAR
        if (!destFile.getParentFile().exists()) {
            destFile.getParentFile().mkdirs();
        }

        OutputStream out = null;
        try {
            destFile.createNewFile();
            out = new FileOutputStream(destFile);
            byte[] cache = new byte[CACHE_SIZE];
            int nRead = 0;
            while ((nRead = in.read(cache)) != -1) {
                out.write(cache, 0, nRead);
                out.flush();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    logger.error("byteArrayToFile", e);
                }
            }

            try {
                in.close();
            } catch (IOException e) {
                logger.error("byteArrayToFile", e);
            }
        }
    }

}
