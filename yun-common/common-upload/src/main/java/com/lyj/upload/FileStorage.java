package com.lyj.upload;

import java.io.InputStream;

/**
 * 文件上传接口
 *
 * @author lyj
 * @date 2019-12-23
 */
public interface FileStorage {

    /**
     * 上传文件
     *
     * @param fileBytes 文件的字节数组
     * @param key       文件名
     * @return 文件标识的唯一id
     */
    void store(byte[] fileBytes, String key);

    /**
     * 存储输入流
     *
     * @param input
     * @param key
     */
    void store(InputStream input, String key);

    /**
     * 下载文件
     *
     * @param key 文件名(带后缀名的)
     */
    byte[] getBytes(String key);

    /**
     * 通过KEY删除文件
     *
     * @param key
     */
    void remove(String key);

    /**
     * 过去输入流
     *
     * @param key
     * @return
     */
    InputStream getInputStream(String key);

    /**
     * 获取下载链接
     *
     * @param key
     * @return
     */
    String getDownloadUrl(String key);
}
