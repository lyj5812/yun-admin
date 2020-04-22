package com.lyj.admin.system.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyj.admin.system.mapper.SysRoleMenuMapper;
import com.lyj.admin.system.domain.SysRoleMenu;
import com.lyj.admin.system.service.SysRoleMenuService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色和菜单关联表 服务实现类
 * </p>
 * @author lyj
 * @since 2019-04-23
 */
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements SysRoleMenuService {
    @Autowired
    private SysRoleMenuMapper roleMenuMapper;

    @Override
    public List<Long> menuIdsByRoleId(Long roleId) {
        return roleMenuMapper.menuIdsByRoleId(roleId);
    }
}
