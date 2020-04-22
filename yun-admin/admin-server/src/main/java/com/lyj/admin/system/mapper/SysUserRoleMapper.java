package com.lyj.admin.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lyj.admin.system.domain.SysUserRole;

import java.util.List;

/**
 * <p>
 * 用户和角色关联表 Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2019-02-23
 */
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

    int batchUserRole(List<SysUserRole> list);
}
