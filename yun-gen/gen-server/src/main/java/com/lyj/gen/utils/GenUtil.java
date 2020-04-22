package com.lyj.gen.utils;

import com.lyj.gen.dto.TableColumnDto;
import com.lyj.gen.dto.TableDto;
import com.lyj.utils.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class GenUtil {

    /**
     * 获取模板信息
     *
     * @return 模板列表
     */
    public static List<String> getTemplateList() {
        List<String> templates = new ArrayList<>();
        templates.add("template/java/domain.java.vm");
        templates.add("template/java/mapper.java.vm");
        templates.add("template/java/service.java.vm");
        templates.add("template/java/serviceImpl.java.vm");
        templates.add("template/java/controller.java.vm");
        templates.add("template/xml/mapper.xml.vm");
        templates.add("template/xml/mapperEx.xml.vm");
        templates.add("template/sql/sql.vm");
        templates.add("template/vue/page.vue.vm");
        templates.add("template/api/api.js.vm");
        return templates;
    }

    /**
     * 获取数据库类型字段
     *
     * @param columnType 列类型
     * @return 截取后的列类型
     */
    public static String getDbType(String columnType) {
        if (StringUtils.indexOf(columnType, "(") > 0) {
            return StringUtils.substringBefore(columnType, "(");
        } else {
            return columnType;
        }
    }

    /**
     * 获取字段长度
     *
     * @param columnType 列类型
     * @return 截取后的列类型
     */
    public static Integer getColumnLength(String columnType) {
        if (StringUtils.indexOf(columnType, "(") > 0) {
            String length = StringUtils.substringBetween(columnType, "(", ")");
            return Integer.valueOf(length);
        } else {
            return 0;
        }
    }

    /**
     * 转换成java类型
     */
    public static void initColumnField(TableColumnDto column) {
        String dataType = getDbType(column.getColumnType());
        String columnName = column.getColumnName();
        // 设置java字段名
        column.setColumnCamelName(StringUtils.underlineToCamel(columnName));

        if (arraysContains(GenConstants.COLUMNTYPE_STR, dataType)) {
            column.setJavaType(GenConstants.TYPE_STRING);
            // 字符串长度超过500设置为文本域
            Integer columnLength = getColumnLength(column.getColumnType());
            String vueType = columnLength >= 500 ? GenConstants.VUE_TEXTAREA : GenConstants.VUE_INPUT;
            column.setVueType(vueType);
        } else if (arraysContains(GenConstants.COLUMNTYPE_TIME, dataType)) {
            column.setJavaType(GenConstants.TYPE_DATE);
            column.setVueType(GenConstants.VUE_DATETIME);
        } else if (arraysContains(GenConstants.COLUMNTYPE_NUMBER, dataType)) {
            column.setVueType(GenConstants.VUE_INPUT);

            // 如果是浮点型
            String[] str = StringUtils.split(StringUtils.substringBetween(column.getColumnType(), "(", ")"), ",");
            if (str != null && str.length == 2 && Integer.parseInt(str[1]) > 0) {
                column.setJavaType(GenConstants.TYPE_DOUBLE);
            }
            // 如果是整形
            else if (str != null && str.length == 1 && Integer.parseInt(str[0]) <= 10) {
                column.setJavaType(GenConstants.TYPE_INTEGER);
            }
            // 长整形
            else {
                column.setJavaType(GenConstants.TYPE_LONG);
            }
        }

        // 插入字段（默认所有字段都需要插入）
        column.setIsInsert(GenConstants.REQUIRE);

        // 编辑字段
        if (!arraysContains(GenConstants.COLUMNNAME_NOT_EDIT, columnName) && !column.getIsPriKey()) {
            column.setIsEdit(GenConstants.REQUIRE);
        }
        // 列表字段
        if (!arraysContains(GenConstants.COLUMNNAME_NOT_LIST, columnName) && !column.getIsPriKey()) {
            column.setIsList(GenConstants.REQUIRE);
        }
        // 查询字段
        if (!arraysContains(GenConstants.COLUMNNAME_NOT_QUERY, columnName) && !column.getIsPriKey()) {
            column.setIsQuery(GenConstants.REQUIRE);
        }

        // 查询字段类型
        if (StringUtils.endsWithIgnoreCase(columnName, "name")) {
            column.setQueryType(GenConstants.QUERY_LIKE);
        } else {
            column.setQueryType(GenConstants.QUERY_EQ);
        }
        // 状态字段设置单选框
        if (StringUtils.endsWithIgnoreCase(columnName, "status")) {
            column.setVueType(GenConstants.VUE_RADIO);
        }
        // 类型&性别字段设置下拉框
        else if (StringUtils.endsWithIgnoreCase(columnName, "type")
                || StringUtils.endsWithIgnoreCase(columnName, "sex")) {
            column.setVueType(GenConstants.VUE_SELECT);
        }
    }

    /**
     * 校验数组是否包含指定值
     *
     * @param arr         数组
     * @param targetValue 值
     * @return 是否包含
     */
    public static boolean arraysContains(String[] arr, String targetValue) {
        return Arrays.asList(arr).contains(targetValue);
    }

    /**
     * 根据列类型获取导入包
     *
     * @param columns 列集合
     * @return 返回需要导入的包列表
     */
    public static HashSet<String> getImportList(List<TableColumnDto> columns) {
        HashSet<String> importList = new HashSet<>();
        for (TableColumnDto column : columns) {
            if (!column.isSuperColumn() && GenConstants.TYPE_DATE.equals(column.getJavaType())) {
                importList.add("java.util.Date");
            } else if (!column.isSuperColumn() && GenConstants.TYPE_BIGDECIMAL.equals(column.getJavaType())) {
                importList.add("java.math.BigDecimal");
            }

            if (column.getIsPriKey()) {
                importList.add("com.baomidou.mybatisplus.annotation.TableId;");
            }
        }
        return importList;
    }

    /**
     * 获取文件名
     */
    public static String getFileName(String template, TableDto table, String moduleName, String businessName, String packageName) {
        // 文件名称
        String fileName = "";
        // 大写类名
        String className = table.getClassName();
        // 业务名称

        String javaPath = "java/" + StringUtils.replace(packageName, ".", "/");
        String mybatisPath = "resources/mapper/" + moduleName;
        String vuePath = moduleName;
        String apiPath = "api/" + moduleName;

        if (template.contains("domain.java.vm")) {
            fileName = StringUtils.format("%s/domain/%s.java", javaPath, className);
        } else if (template.contains("mapper.java.vm")) {
            fileName = StringUtils.format("%s/mapper/%sMapper.java", javaPath, className);
        } else if (template.contains("service.java.vm")) {
            fileName = StringUtils.format("%s/service/%sService.java", javaPath, className);
        } else if (template.contains("serviceImpl.java.vm")) {
            fileName = StringUtils.format("%s/service/impl/%sServiceImpl.java", javaPath, className);
        } else if (template.contains("controller.java.vm")) {
            fileName = StringUtils.format("%s/controller/%sController.java", javaPath, className);
        } else if (template.contains("mapper.xml.vm")) {
            fileName = StringUtils.format("%s/%sMapper.xml", mybatisPath, className);
        } else if (template.contains("mapperEx.xml.vm")) {
            fileName = StringUtils.format("%s/%sMapperEx.xml", mybatisPath, className);
        } else if (template.contains("sql.vm")) {
            fileName = className + "Menu.sql";
        } else if (template.contains("page.vue.vm")) {
            fileName = StringUtils.format("%s/%s/index.vue", vuePath, StringUtils.uncapitalize(businessName));
        } else if (template.contains("api.js.vm")) {
            fileName = StringUtils.format("%s/%s.js", apiPath, StringUtils.uncapitalize(businessName));
        }
        return fileName;
    }
}

