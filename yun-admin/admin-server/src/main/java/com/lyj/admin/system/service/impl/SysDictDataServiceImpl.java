package com.lyj.admin.system.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyj.admin.system.mapper.SysDictDataMapper;
import com.lyj.admin.system.domain.SysDictData;
import com.lyj.admin.system.service.SysDictDataService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Description:
 * Date: 2019-04-24
 * Time: 20:28
 * @author lyj
 */
@Service
public class SysDictDataServiceImpl extends ServiceImpl<SysDictDataMapper, SysDictData> implements SysDictDataService {

    @Autowired
    private SysDictDataMapper sysDictDataMapper;

    @Override
    public List<SysDictData> dictDataList(SysDictData dictData) {
        return sysDictDataMapper.dictDataList(dictData);
    }
}
