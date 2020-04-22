package com.lyj.admin.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lyj.admin.system.domain.SysDictType;

/**
 * Description:
 * User: lyj
 * Date: 2019-04-24
 */
public interface SysDictTypeService extends IService<SysDictType> {

    boolean dictTypeAddOrEdit(SysDictType dictType);

    IPage<SysDictType> selectDictTypeListPage(Page page, SysDictType dictType);
}
