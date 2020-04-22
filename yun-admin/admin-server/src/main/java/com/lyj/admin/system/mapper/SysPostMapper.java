package com.lyj.admin.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyj.admin.system.domain.SysPost;

import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 岗位信息表 Mapper 接口
 * </p>
 *
 * @author lyj
 * @date 2019-02-23
 */
public interface SysPostMapper extends BaseMapper<SysPost> {

    Long[] getPostIdsByUserId(Long userId);

    IPage<SysPost> selectPostListPage(Page page,@Param("post") SysPost post);
}
