package com.lyj.admin.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lyj.admin.system.domain.SysRoleMenu;

import java.util.List;

/**
 * <p>
 * 角色和菜单关联表 Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2019-02-23
 */
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {

    List<Long> menuIdsByRoleId(Long roleId);

    /**
     * 批量插入
     * @param list
     * @return
     */
    int batchInsert(List<SysRoleMenu> list);

}
