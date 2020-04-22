package com.lyj.gen.controller;

import com.lyj.gen.dto.TableDto;
import com.lyj.gen.service.TableService;
import com.lyj.pojo.R;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/table")
@Api(tags = "数据表管理")
public class TableController {

    @Autowired
    private TableService tableService;

    /**
     * 获取表集合
     *
     * @return
     */
    @ApiOperation("数据表")
    @GetMapping("/list/{sourceId}")
    public R<List<TableDto>> list(@PathVariable("sourceId") String sourceId) {
        return R.ok(tableService.getTableList(sourceId));
    }

    /**
     * 获取表集合
     *
     * @return
     */
    @ApiOperation("获取表详情")
    @GetMapping("/info/{sourceId}/{tableName}")
    public R<TableDto> list(@PathVariable("sourceId") String sourceId, @PathVariable("tableName") String tableName) {
        return R.ok(tableService.getTableInfo(sourceId, tableName));
    }

    /**
     * 获取表集合
     *
     * @return
     */
    @ApiOperation("修改表")
    @PutMapping("/edit/{sourceId}/{tableName}")
    public R<Boolean> edit(@PathVariable("sourceId") String sourceId, @PathVariable("tableName") String tableName,@RequestBody TableDto tableDto) {
        return R.ok(tableService.editTableInfo(sourceId, tableName,tableDto));
    }

}
