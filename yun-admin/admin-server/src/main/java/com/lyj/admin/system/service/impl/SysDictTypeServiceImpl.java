package com.lyj.admin.system.service.impl;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyj.admin.system.mapper.SysDictDataMapper;
import com.lyj.admin.system.mapper.SysDictTypeMapper;
import com.lyj.admin.system.domain.SysDictData;
import com.lyj.admin.system.domain.SysDictType;
import com.lyj.admin.system.service.SysDictTypeService;
import com.lyj.utils.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @date: 2019-04-24
 * @author: lyj
 */
@Service
public class SysDictTypeServiceImpl extends ServiceImpl<SysDictTypeMapper, SysDictType> implements SysDictTypeService {

    @Autowired
    private SysDictDataMapper dictDataMapper;

    @Autowired
    private SysDictTypeMapper dictTypeMapper;

    /**
     * 修改
     * @param dictType
     * @return
     */
    @Override
    public boolean dictTypeAddOrEdit(SysDictType dictType) {
        if (StringUtils.isNotNull(dictType.getDictId())){
            SysDictType sysDictType = dictTypeMapper.selectById(dictType.getDictId());
            dictDataMapper.update(null,new UpdateWrapper<SysDictData>().eq("dict_type",sysDictType.getDictType()).set("dict_type",dictType.getDictType()));
        }
        return this.saveOrUpdate(dictType);
    }

    @Override
    public IPage<SysDictType> selectDictTypeListPage(Page page, SysDictType dictType) {
        return dictTypeMapper.selectDictTypeListPage(page,dictType);
    }
}
