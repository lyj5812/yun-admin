package com.lyj.admin.system.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyj.admin.system.domain.SysWxMiniUser;
import org.apache.ibatis.annotations.Param;
public interface SysWxMiniUserMapper extends BaseMapper<SysWxMiniUser> {
    IPage<SysWxMiniUser> selectSysWxMiniUserListPage(Page page,@Param("sysWxMiniUser") SysWxMiniUser sysWxMiniUser);
}
