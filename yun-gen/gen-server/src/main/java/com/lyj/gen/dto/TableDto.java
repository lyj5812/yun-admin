package com.lyj.gen.dto;
import java.io.Serializable;
import java.util.List;

import cn.hutool.core.util.ObjectUtil;
import lombok.Data;

/**
 * 数据表dto
 */
@Data
public class TableDto implements Serializable {

    /**
     * 表名称
     */
    private String tableName;

    /**
     * 作者
     */
    private String author;

    /**
     * 表驼峰名称
     */
    private String tableCamelName;

    /**
     * 类名
     */
    private String className;

    /**
     * 表注释
     */
    private String tableComment;

    /**
     * 引擎
     */
    private String engine;

    /**
     * 模块名
     */
    private String moduleName;

    /**
     * 功能名
     */
    private String functionName;

    /**
     * 业务名
     */
    private String businessName;

    /**
     * 包路径
     */
    private String packageName;

    /**
     * 截取表名前缀 例：sys_
     */
    private String subPore;

    /**
     * 表的主键
     */
    private String primaryKey;

    /**
     * 表字段集合
     */
    private List<TableColumnDto> columns;

    public String getPrimaryKey() {
        if (ObjectUtil.isNotEmpty(columns)){
            for (TableColumnDto column : columns) {
                if (column.getIsPriKey()){
                    return column.getColumnCamelName();
                }
            }
        }
        return primaryKey;
    }
}
