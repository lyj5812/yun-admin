package com.lyj.admin.upload.domain;
import java.io.Serializable;

import lombok.Data;

@Data
public class FileInfo implements Serializable {

    private Long fileId;
    /**
     * 当前文件块，从1开始
     */
    private String filename;
    /**
     * 当前文件块，从1开始
     */
    private String identifier;
    /**
     * 当前文件块，从1开始
     */
    private Long totalSize;
    /**
     * 当前文件块，从1开始
     */
    private String type;
    /**
     * 当前文件块，从1开始
     */
    private String location;
}
