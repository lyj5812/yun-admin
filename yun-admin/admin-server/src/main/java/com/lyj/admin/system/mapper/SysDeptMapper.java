package com.lyj.admin.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lyj.admin.system.domain.SysDept;

/**
 * <p>
 * 部门表 Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2019-02-23
 */
public interface SysDeptMapper extends BaseMapper<SysDept> {

    SysDept getDeptByUserId(Long userId);
}
