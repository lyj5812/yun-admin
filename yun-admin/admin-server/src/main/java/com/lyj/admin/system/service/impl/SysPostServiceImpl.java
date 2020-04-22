package com.lyj.admin.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyj.admin.system.mapper.SysPostMapper;
import com.lyj.admin.system.domain.SysPost;
import com.lyj.admin.system.service.SysPostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @date: 2019-05-05
 * @author lyj
 */
@Service
public class SysPostServiceImpl extends ServiceImpl<SysPostMapper, SysPost> implements SysPostService {
    @Autowired
    private SysPostMapper postMapper;

    @Override
    public Long[] getPostIdsByUserId(Long userId) {
        return postMapper.getPostIdsByUserId(userId);
    }

    @Override
    public IPage<SysPost> selectPostListPage(Page page, SysPost post) {
        return postMapper.selectPostListPage(page, post);
    }
}
