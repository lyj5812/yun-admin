package com.lyj.admin.system.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lyj.admin.system.domain.SysUser;
import com.lyj.admin.system.dto.UserInfo;

/**
 * Description:
 * Date: 2019-01-26
 * @author lyj
 */
public interface SysUserService extends IService<SysUser> {

    boolean saveOrUpdateUser(SysUser user);

    UserInfo getUserInfo(SysUser user);

    IPage<SysUser> selectUserListPage(Page page, SysUser user);

    boolean resetPwd(String username, String newPassword);
}
