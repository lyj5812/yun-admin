package com.lyj.gen.dto;

import com.lyj.utils.StringUtils;

import java.io.Serializable;

import lombok.Data;

/**
 * 数据表dto
 */
@Data
public class TableColumnDto implements Serializable {

    /**
     * 字段名称
     */
    private String columnName;

    /**
     * 字段驼峰名称
     */
    private String columnCamelName;

    /**
     * 字段注释
     */
    private String columnComment;

    /**
     * 是否主键
     */
    private Boolean isPriKey;

    /**
     * 字段类型
     */
    private String columnType;

    /**
     * java类型
     */
    private String javaType;

    /**
     * 是否自增
     */
    private Boolean isIncrement;

    /**
     * 是否必填
     */
    private Boolean isRequired;

    /**
     * 是否为插入字段
     */
    private Boolean isInsert;

    /**
     * 是否编辑字段
     */
    private Boolean isEdit;

    /**
     * 是否列表字段
     */
    private Boolean isList;

    /**
     * 是否查询字段
     */
    private Boolean isQuery;

    /**
     * 查询方式（EQ等于、NE不等于、GT大于、LT小于、LIKE模糊、BETWEEN范围）
     */
    private String queryType;

    /**
     * 显示类型（input文本框、textarea文本域、select下拉框、checkbox复选框、radio单选框、datetime日期控件）
     */
    private String vueType;

    /**
     * 字典类型
     */
    private String dictType;

    /**
     * 顺序
     */
    private Integer columnSort;

    public boolean isSuperColumn() {
        return StringUtils.equalsAnyIgnoreCase(columnCamelName, "createBy", "createTime", "updateBy", "updateTime", "remark","delFlag");
    }

}
