package com.lyj.admin.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lyj.admin.system.domain.SysMenu;
import java.util.List;

/**
 * @author lyj
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {
    /**
     * 根据用户ID查询菜单
     *
     * @param userId 用户ID
     * @return 菜单列表
     */
    List<SysMenu> selectMenusByUserId(Long userId);

    /**
     * 根据用户ID权限标识
     *
     * @param userId 用户ID
     * @return 按钮
     */
    String [] selectBtnPermissionsByUserId(Long userId);
}