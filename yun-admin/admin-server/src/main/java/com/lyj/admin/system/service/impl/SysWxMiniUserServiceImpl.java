package com.lyj.admin.system.service.impl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyj.admin.system.mapper.SysWxMiniUserMapper;
import com.lyj.admin.system.domain.SysWxMiniUser;
import com.lyj.admin.system.service.SysWxMiniUserService;

/**
 * 微信小程序用户表 ServiceImpl
 * 
 * @author lyj
 * @date 2019-10-25
 */
@Service
public class SysWxMiniUserServiceImpl extends ServiceImpl<SysWxMiniUserMapper, SysWxMiniUser> implements SysWxMiniUserService {

    @Autowired
    private SysWxMiniUserMapper sysWxMiniUserMapper;

    /**
     * 查询分页列表
     * @param page 分页参数
     * @param sysWxMiniUser
     * @return 分页数据
     */
    @Override
    public IPage<SysWxMiniUser> selectSysWxMiniUserListPage(Page page, SysWxMiniUser sysWxMiniUser) {
        return sysWxMiniUserMapper.selectSysWxMiniUserListPage(page,sysWxMiniUser);
    }

    @Override
    public SysWxMiniUser findByMaUserInfo(SysWxMiniUser miniUser) {
        SysWxMiniUser user = sysWxMiniUserMapper.selectOne(new QueryWrapper<SysWxMiniUser>().eq("open_id",miniUser.getOpenId()));
        if (user == null){
            sysWxMiniUserMapper.insert(miniUser);
            return miniUser;
        }else {
            BeanUtils.copyProperties(miniUser,user,"wxMiniUserId");
            sysWxMiniUserMapper.updateById(user);
            return user;
        }
    }

}
