package com.lyj.admin.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lyj.admin.system.domain.SysRole;
import java.util.List;

/**
 * Description:
 * User: lyj
 * Date: 2019-04-24
 */
public interface SysRoleService extends IService<SysRole> {

    List<SysRole> getRoleByUserId(Long userId);

    int deleteList(List<Long> ids);

    int addOrEdit(SysRole role);

    IPage<SysRole> selectRoleListPage(Page page, SysRole role);
}
