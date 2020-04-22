package com.lyj.gen.utils;

/**
 * 代码生成常量
 *
 * @author lyj
 */
public class GenConstants {
    /**
     * 单表（增删改查）
     */
    public static final String TPL_CRUD = "crud";

    /**
     * 数据库字符串类型
     */
    public static final String[] COLUMNTYPE_STR = {"char", "varchar", "narchar", "varchar2", "tinytext", "text",
            "mediumtext", "longtext"};

    /**
     * 数据库时间类型
     */
    public static final String[] COLUMNTYPE_TIME = {"datetime", "time", "date", "timestamp"};

    /**
     * 数据库数字类型
     */
    public static final String[] COLUMNTYPE_NUMBER = {"tinyint", "smallint", "mediumint", "int", "number", "integer",
            "bigint", "float", "float", "double", "decimal"};

    /**
     * 页面不需要编辑字段
     */
    public static final String[] COLUMNNAME_NOT_EDIT = {"id", "create_by", "create_time", "del_flag"};

    /**
     * 页面不需要显示的列表字段
     */
    public static final String[] COLUMNNAME_NOT_LIST = {"id", "create_by", "create_time", "del_flag", "update_by",
            "update_time"};

    /**
     * 页面不需要查询字段
     */
    public static final String[] COLUMNNAME_NOT_QUERY = {"id", "create_by", "create_time", "del_flag", "update_by",
            "update_time", "remark"};

    /**
     * 文本框
     */
    public static final String VUE_INPUT = "input";

    /**
     * 文本域
     */
    public static final String VUE_TEXTAREA = "textarea";

    /**
     * 下拉框
     */
    public static final String VUE_SELECT = "select";

    /**
     * 单选框
     */
    public static final String VUE_RADIO = "radio";

    /**
     * 复选框
     */
    public static final String VUE_CHECKBOX = "checkbox";

    /**
     * 日期控件
     */
    public static final String VUE_DATETIME = "datetime";

    /**
     * 字符串类型
     */
    public static final String TYPE_STRING = "String";

    /**
     * 整型
     */
    public static final String TYPE_INTEGER = "Integer";

    /**
     * 长整型
     */
    public static final String TYPE_LONG = "Long";

    /**
     * 浮点型
     */
    public static final String TYPE_DOUBLE = "Double";

    /**
     * 高精度计算类型
     */
    public static final String TYPE_BIGDECIMAL = "BigDecimal";

    /**
     * 时间类型
     */
    public static final String TYPE_DATE = "Date";

    /**
     * 模糊查询
     */
    public static final String QUERY_LIKE = "LIKE";

    /**
     * 等于查询
     */
    public static final String QUERY_EQ = "EQ";

    /**
     * 需要
     */
    public static final Boolean REQUIRE = true;

    public static final String USER_DBS_KEY = "USER_DBS";

    /**
     * 需要
     */
    public static final String DEF_PACKAGE = "com.lyj.admin";
}
