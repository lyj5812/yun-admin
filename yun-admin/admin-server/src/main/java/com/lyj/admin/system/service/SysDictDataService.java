package com.lyj.admin.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lyj.admin.system.domain.SysDictData;

import java.util.List;

/**
 * Description:
 * User: lyj
 * Date: 2019-04-24
 */
public interface SysDictDataService extends IService<SysDictData> {

    List<SysDictData> dictDataList(SysDictData dictData);
}
