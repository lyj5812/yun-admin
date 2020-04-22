package com.lyj.gen.service.impl;
import com.lyj.common.redis.RedisService;
import com.lyj.gen.dto.TableColumnDto;
import com.lyj.gen.dto.TableDto;
import com.lyj.gen.service.GenService;
import com.lyj.gen.utils.GenUtil;
import com.lyj.gen.utils.VelocityInitializer;
import com.lyj.constants.Constants;
import com.lyj.utils.DateUtils;
import com.lyj.utils.StringUtils;

import org.apache.commons.collections.ListUtils;
import org.apache.commons.io.IOUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GenServiceImpl implements GenService {

    @Autowired
    private RedisService redisService;

    @Override
    public byte[] genCode(String sourceId, List<String> tableNameList) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        genCodeZip(sourceId,tableNameList, zip);
        IOUtils.closeQuietly(zip);
        return outputStream.toByteArray();
    }

    /**
     * 设置模板变量信息
     *
     * @return 模板列表
     */
    private void genCodeZip(String sourceId, List<String> tableNameList, ZipOutputStream zip) {
        for (String tableName : tableNameList) {
            VelocityInitializer.initVelocity();
            TableDto table = (TableDto) redisService.hGet(sourceId, tableName);
            List<Object> columns = redisService.hValues(sourceId + StrUtil.COLON + tableName);

            List<TableColumnDto> columnList =new ArrayList<>();
            for (Object column : columns) {
                columnList.add((TableColumnDto) column);
            }
            columnList.sort(Comparator.comparing(TableColumnDto::getColumnSort));
            table.setColumns(columnList);
            //截取表前缀
            if (StringUtils.isNotEmpty(table.getSubPore()) && table.getTableName().startsWith(table.getSubPore())) {
                table.setTableCamelName(StringUtils.uncapitalize(StringUtils.substringAfter(table.getTableCamelName(), StringUtils.underlineToCamel(table.getSubPore()))));
                table.setClassName(StringUtils.substringAfter(table.getClassName(), StringUtils.capitalize(StringUtils.underlineToCamel(table.getSubPore()))));
            }
            VelocityContext velocityContext = new VelocityContext();
            velocityContext.put("importList", GenUtil.getImportList(columnList));
            velocityContext.put("datetime", DateUtils.getDate());
            velocityContext.put("table", table);
            StrUtil strUtil = new StrUtil();
            velocityContext.put("strUtil", strUtil);
            // 获取模板列表
            List<String> templates = GenUtil.getTemplateList();
            for (String template : templates) {
                // 渲染模板
                StringWriter sw = new StringWriter();
                Template tpl = Velocity.getTemplate(template, Constants.UTF8);
                tpl.merge(velocityContext, sw);
                try {
                    // 添加到zip
                    zip.putNextEntry(new ZipEntry(GenUtil.getFileName(template, table, table.getModuleName(), table.getBusinessName(), table.getPackageName())));
                    IOUtils.write(sw.toString(), zip, Constants.UTF8);
                    IOUtils.closeQuietly(sw);
                    zip.closeEntry();
                } catch (IOException e) {
                    log.error("渲染模板失败，表名：" + table.getTableName(), e);
                }
            }
        }
    }
}
