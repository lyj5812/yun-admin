package com.lyj.admin.system.service;

import com.lyj.admin.system.domain.SysRoleMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 角色和菜单关联表 服务类
 * </p>
 *
 * @author lyj
 * @since 2019-04-23
 */
public interface SysRoleMenuService extends IService<SysRoleMenu> {

    List<Long> menuIdsByRoleId(Long roleId);
}
