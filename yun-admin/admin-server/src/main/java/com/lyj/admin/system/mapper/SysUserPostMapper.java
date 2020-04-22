package com.lyj.admin.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lyj.admin.system.domain.SysUserPost;

import java.util.List;

/**
 * <p>
 * 用户与岗位关联表 Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2019-02-23
 */
public interface SysUserPostMapper extends BaseMapper<SysUserPost> {

    int batchUserPost(List<SysUserPost> list);
}
