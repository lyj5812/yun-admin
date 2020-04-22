package com.lyj.admin.system.service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lyj.admin.system.domain.SysPost;

/**
 * Description:
 * User: lyj
 * Date: 2019-05-05
 */
public interface SysPostService extends IService<SysPost> {

    Long[] getPostIdsByUserId(Long userId);

    IPage<SysPost> selectPostListPage(Page page, SysPost post);
}
