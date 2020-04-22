package com.lyj.gen.dto;

import java.io.Serializable;

import lombok.Data;

/**
 * 数据源dto
 */
@Data
public class DataSourceDto implements Serializable {

    /**
     * 主键
     */
    private String sourceId;

    /**
     * 名称
     */
    private String name;

    /**
     * 数据库用户名
     */
    private String userName;

    /**
     * 数据源密码
     */
    private String password;

    /**
     * 数据源类型
     */
    private String dataSourceType;

    /**
     * host
     */
    private String host;

    /**
     * 数据库名
     */
    private String dbName;

    /**
     * 数据库端口号
     */
    private String port;

}
