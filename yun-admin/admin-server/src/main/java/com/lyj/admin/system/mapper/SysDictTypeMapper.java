package com.lyj.admin.system.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyj.admin.system.domain.SysDictType;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 字典类型表 Mapper 接口
 * </p>
 *
 * @author lyj
 * @date 2019-02-23
 */
public interface SysDictTypeMapper extends BaseMapper<SysDictType> {

    IPage<SysDictType> selectDictTypeListPage(Page page,@Param("dictType") SysDictType dictType);
}
