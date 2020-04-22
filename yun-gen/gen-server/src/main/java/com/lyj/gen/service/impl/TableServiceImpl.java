package com.lyj.gen.service.impl;

import com.alibaba.druid.pool.DruidDataSource;
import com.lyj.common.redis.RedisService;
import com.lyj.common.utils.UserUtils;
import com.lyj.gen.config.JdbcDataSource;
import com.lyj.gen.dto.DataSourceDto;
import com.lyj.gen.dto.TableColumnDto;
import com.lyj.gen.dto.TableDto;
import com.lyj.gen.service.TableService;
import com.lyj.gen.utils.GenConstants;
import com.lyj.gen.utils.GenUtil;
import com.lyj.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TableServiceImpl implements TableService {

    @Autowired
    private JdbcDataSource jdbcDataSource;

    @Autowired
    private RedisService redisService;

    @Override
    public List<TableDto> getTableList(String sourceId) {
        DataSourceDto sourceDto = (DataSourceDto) redisService.hGet(UserUtils.getUserId() + GenConstants.USER_DBS_KEY, sourceId);
        DruidDataSource dataSource = jdbcDataSource.getDataSource(sourceDto);
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
        String sql = "select table_name tableName,table_comment tableComment, engine from information_schema.tables where table_schema=" + StringUtils.quotaMark(sourceDto.getDbName());
        List<TableDto> tableList = jdbcTemplate.query(sql, new RowMapper<TableDto>() {
            @Override
            public TableDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                TableDto tableDto = new TableDto();
                tableDto.setTableName(rs.getString(1));
                tableDto.setTableCamelName(StrUtil.toCamelCase(rs.getString(1)));
                tableDto.setClassName(StrUtil.upperFirst(StrUtil.toCamelCase(rs.getString(1))));
                tableDto.setTableComment(rs.getString(2));
                tableDto.setFunctionName(rs.getString(2));
                tableDto.setEngine(rs.getString(3));
                tableDto.setPackageName(GenConstants.DEF_PACKAGE);
                String[] arr = tableDto.getTableName().split(StrUtil.UNDERLINE);
                if (ArrayUtil.isNotEmpty(arr) && arr.length >= 2) {
                    tableDto.setModuleName(arr[0]);
                    tableDto.setBusinessName(arr[1]);
                    tableDto.setPackageName(GenConstants.DEF_PACKAGE + "." + arr[0]);
                }
                if (redisService.hExists(sourceDto.getSourceId(),tableDto.getTableName())){
                    TableDto dto = (TableDto) redisService.hGet(sourceDto.getSourceId(), tableDto.getTableName());
                    BeanUtil.copyProperties(dto,tableDto, CopyOptions.create().setIgnoreProperties("tableName","tableComment"));
                }
                redisService.hPut(sourceDto.getSourceId(), tableDto.getTableName(), tableDto);
                getColumnList(tableDto.getTableName(), sourceDto, jdbcTemplate);
                return tableDto;
            }
        });
        return tableList;
    }

    @Override
    public TableDto getTableInfo(String sourceId, String tableName) {
        DataSourceDto sourceDto = (DataSourceDto) redisService.hGet(UserUtils.getUserId() + GenConstants.USER_DBS_KEY, sourceId);
        DruidDataSource dataSource = jdbcDataSource.getDataSource(sourceDto);
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
        List<TableColumnDto> tableColumns = getColumnList(tableName, sourceDto, jdbcTemplate);
        TableDto tableDto = (TableDto) redisService.hGet(sourceId, tableName);
        tableDto.setColumns(tableColumns);
        return tableDto;
    }

    private List<TableColumnDto> getColumnList(String tableName, DataSourceDto sourceDto, JdbcTemplate jdbcTemplate) {
        String columnSql = "select column_name columnName, column_type columnType, column_comment columnComment," +
                " CASE WHEN column_key = 'PRI' THEN '1' ELSE '0' END columnKey,CASE WHEN extra = 'auto_increment' THEN '1'" +
                " ELSE '0' END extra  from information_schema.columns where table_name =" + StringUtils.quotaMark(tableName)+" and table_schema ="+ StringUtils.quotaMark(sourceDto.getDbName());

        return jdbcTemplate.query(columnSql, new RowMapper<TableColumnDto>() {
            @Override
            public TableColumnDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                TableColumnDto tableColumnDto = new TableColumnDto();
                tableColumnDto.setColumnName(rs.getString(1));
                tableColumnDto.setColumnType(rs.getString(2));
                tableColumnDto.setColumnComment(rs.getString(3));
                tableColumnDto.setIsPriKey(rs.getString(4).equals("1"));
                tableColumnDto.setIsIncrement(rs.getString(5).equals("1"));
                tableColumnDto.setColumnSort(rowNum);
                GenUtil.initColumnField(tableColumnDto);
                if (redisService.hExists(sourceDto.getSourceId()+ StrUtil.COLON+tableName, tableColumnDto.getColumnName())){
                    TableColumnDto dto = (TableColumnDto) redisService.hGet(sourceDto.getSourceId()+StrUtil.COLON+tableName, tableColumnDto.getColumnName());
                    BeanUtil.copyProperties(dto,tableColumnDto, CopyOptions.create().setIgnoreProperties("columnName","columnComment","columnType","columnCamelName"));
                }
                redisService.hPut(sourceDto.getSourceId()+StrUtil.COLON+tableName, tableColumnDto.getColumnName(), tableColumnDto);
                return tableColumnDto;
            }
        });
    }

    @Override
    public Boolean editTableInfo(String sourceId, String tableName, TableDto tableDto) {
        for (TableColumnDto column : tableDto.getColumns()) {
            redisService.hPut(sourceId+StrUtil.COLON+tableName, column.getColumnName(), column);
            TableColumnDto dto = (TableColumnDto) redisService.hGet(sourceId+StrUtil.COLON+tableName, column.getColumnName());
            System.out.println(dto);
        }
        tableDto.setColumns(null);
        redisService.hPut(sourceId, tableDto.getTableName(), tableDto);
        return true;
    }
}
