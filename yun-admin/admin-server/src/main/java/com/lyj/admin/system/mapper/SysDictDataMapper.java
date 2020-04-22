package com.lyj.admin.system.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lyj.admin.system.domain.SysDictData;
import java.util.List;

/**
 * <p>
 * 字典数据表 Mapper 接口
 * </p>
 *
 * @author lyj
 * @date 2019-02-23
 */
public interface SysDictDataMapper extends BaseMapper<SysDictData> {

    List<SysDictData> dictDataList(SysDictData dictData);
}
