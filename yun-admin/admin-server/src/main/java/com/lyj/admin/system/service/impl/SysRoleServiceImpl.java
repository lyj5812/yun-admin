package com.lyj.admin.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyj.admin.system.mapper.SysRoleMapper;
import com.lyj.admin.system.mapper.SysRoleMenuMapper;
import com.lyj.admin.system.mapper.SysUserRoleMapper;
import com.lyj.admin.system.domain.SysRole;
import com.lyj.admin.system.domain.SysRoleMenu;
import com.lyj.admin.system.domain.SysUserRole;
import com.lyj.admin.system.service.SysRoleService;
import com.lyj.utils.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 2019-04-24
 * @author lyj
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Autowired
    private SysRoleMapper roleMapper;

    @Autowired
    private SysRoleMenuMapper roleMenuMapper;

    @Autowired
    private SysUserRoleMapper userRoleMapper;

    @Override
    public List<SysRole> getRoleByUserId(Long userId) {
        return roleMapper.getRoleByUserId(userId);
    }

    @Override
    public int deleteList(List<Long> ids) {
        for (Long roleId : ids) {
            if (countUserRoleByRoleId(roleId) > 0) {
                //有角色在使用中
                return -1;
            }
        }
        return roleMapper.deleteBatchIds(ids);
    }

    @Override
    public int addOrEdit(SysRole role) {
        if (StringUtils.isNotNull(role.getRoleId())) {
            roleMapper.updateById(role);
            roleMenuMapper.delete(new QueryWrapper<SysRoleMenu>().eq("role_id", role.getRoleId()));
        } else {
            roleMapper.insert(role);
        }
        return insertRoleMenu(role);
    }

    @Override
    public IPage<SysRole> selectRoleListPage(Page page, SysRole role) {
        return roleMapper.selectRoleListPage(page, role);
    }

    /**
     * 新增角色菜单信息
     *
     * @param role 角色对象
     */
    public int insertRoleMenu(SysRole role) {
        int rows = 1;
        // 新增用户与角色管理
        List<SysRoleMenu> list = new ArrayList<SysRoleMenu>();
        for (Long menuId : role.getMenuIds()) {
            SysRoleMenu rm = new SysRoleMenu();
            rm.setRoleId(role.getRoleId());
            rm.setMenuId(menuId);
            list.add(rm);
        }
        if (list.size() > 0) {
            rows = roleMenuMapper.batchInsert(list);
        }
        return rows;
    }

    /**
     * 通过角色ID查询角色使用数量
     *
     * @param roleId 角色ID
     * @return 结果
     */
    public int countUserRoleByRoleId(Long roleId) {
        return userRoleMapper.selectCount(new QueryWrapper<SysUserRole>().eq("role_id", roleId));
    }
}
