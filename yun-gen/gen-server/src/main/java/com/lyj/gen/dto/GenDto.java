package com.lyj.gen.dto;

import java.util.List;

import lombok.Data;

/**
 * 数据表dto
 */
@Data
public class GenDto {

    /**
     * 作者
     */
    private String author;

    /**
     * 生成包路径
     */
    private String packageName;

    /**
     * 生成模块名
     */
    private String moduleName;

    /**
     * 生成业务名
     */
    private String businessName;

    /**
     * 截取表名前缀 例：sys_
     */
    private String subPore;

    /**
     * 表集合
     */
    private List<TableDto> tables;
}
