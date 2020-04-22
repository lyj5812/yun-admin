package com.lyj.admin.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyj.admin.system.domain.SysRole;

import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface SysRoleMapper extends BaseMapper<SysRole> {

    List<SysRole> getRoleByUserId(Long userId);

    IPage<SysRole> selectRoleListPage(Page page, @Param("role") SysRole role);
}