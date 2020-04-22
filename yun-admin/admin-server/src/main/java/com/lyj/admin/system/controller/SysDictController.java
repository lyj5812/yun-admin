package com.lyj.admin.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyj.admin.system.domain.SysDictData;
import com.lyj.admin.system.domain.SysDictType;
import com.lyj.admin.system.service.SysDictDataService;
import com.lyj.admin.system.service.SysDictTypeService;
import com.lyj.pojo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author lyj
 * @date: 2019-04-17
 */
@Api(tags = "字典模块")
@RestController
@RequestMapping("/dict")
public class SysDictController {
    @Autowired
    private SysDictTypeService dictTypeService;
    @Autowired
    private SysDictDataService dictDataService;

    /**
     * 字典类型分页查询
     *
     * @param dictType
     * @return
     */
    @ApiOperation("字典类型分页查询")
    @GetMapping("/dictTypeListPage")
    public R<IPage> dictTypeListPage(Page page, SysDictType dictType) {
        return R.ok(dictTypeService.selectDictTypeListPage(page, dictType));
    }

    /**
     * 根据字典的类型查字典数据集合
     * @param dictData
     * @return
     */
    @ApiOperation("类型查字典数据集合")
    @PostMapping("/dictDataList")
    public R<List<SysDictData>> dictDataList(@RequestBody SysDictData dictData) {
        return R.ok(dictDataService.dictDataList(dictData));
    }

    /**
     * 查字典集合
     * @return
     */
    @ApiOperation("类型查字典数据集合")
    @GetMapping("/dictTypeList")
    public R<List<SysDictType>> dictTypeList() {
        return R.ok(dictTypeService.list());
    }

    /**
     * 添加或修改字典类型
     *
     * @param dictType
     * @return
     */
    @ApiOperation("添加或修改字典类型")
    @PutMapping("/dictTypeAddOrEdit")
    public R dictTypeAddOrEdit(@RequestBody SysDictType dictType) {
        return R.ok(dictTypeService.dictTypeAddOrEdit(dictType));
    }

    @ApiOperation("删除字典类型")
    @DeleteMapping("/dictTypeDelList")
    public R dictTypeDelList(@RequestBody List<Long> ids) {
        return R.ok(dictTypeService.removeByIds(ids));
    }

    @ApiOperation("添加或修改字典")
    @PutMapping("/dictDataAddOrEdit")
    public R dictDataAddOrEdit(@RequestBody SysDictData dictData) {
        return R.ok(dictDataService.saveOrUpdate(dictData));
    }

    @ApiOperation("删除字典")
    @DeleteMapping("/dictDataDel")
    public R dictDataDel(@RequestBody List<Long> ids) {
        return R.ok(dictDataService.removeByIds(ids));
    }

    @ApiOperation("字典类型查字典")
    @GetMapping("/dictType/{dictType}")
    public R<List<SysDictData>> dictType(@PathVariable("dictType") String dictType) {
        return R.ok(dictTypeService.getOne(new QueryWrapper<SysDictType>().eq("dict_type", dictType).eq("status", "0")));
    }

}
