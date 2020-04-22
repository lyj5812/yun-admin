package com.lyj.admin.system.service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lyj.admin.system.domain.SysWxMiniUser;

/**
 * SysWxMiniUser Service
 * 
 * @author lyj
 * @date 2019-10-25
 */
public interface SysWxMiniUserService extends IService<SysWxMiniUser>{

    /**
     * 查询分页列表
     * 
     * @param page 分页参数
     * @param sysWxMiniUser
     * @return 分页数据
     */
    IPage<SysWxMiniUser> selectSysWxMiniUserListPage(Page page, SysWxMiniUser sysWxMiniUser);

    SysWxMiniUser findByMaUserInfo(SysWxMiniUser miniUser);
}
