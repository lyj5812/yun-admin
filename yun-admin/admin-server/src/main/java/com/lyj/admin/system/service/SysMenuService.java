package com.lyj.admin.system.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lyj.admin.system.domain.SysMenu;
import com.lyj.admin.system.vo.RouterVo;

import java.util.List;

/**
 * Description:
 * Date: 2019-02-24
 * Time: 10:12
 * @author lyj
 */
public interface SysMenuService extends IService<SysMenu> {

    /**
     * 根据用户ID查询菜单
     *
     * @param userId 用户信息
     * @return 菜单列表
     */
    List<SysMenu> selectMenusByUserId(Long userId);

    /**
     * 查询菜单集合列表
     * @return 按钮权限
     */
    List<SysMenu> selectMenusListTree();

    boolean delMenu(SysMenu menu);

    List<RouterVo> formatMenu(List<SysMenu> tree);
}
