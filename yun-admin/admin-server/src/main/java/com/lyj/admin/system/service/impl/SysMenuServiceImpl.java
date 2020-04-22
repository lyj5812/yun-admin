package com.lyj.admin.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyj.admin.system.mapper.SysMenuMapper;
import com.lyj.admin.system.domain.SysMenu;
import com.lyj.admin.system.service.SysMenuService;
import com.lyj.admin.system.vo.MetaVo;
import com.lyj.admin.system.vo.RouterVo;
import com.lyj.constants.Constants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;

/**
 * date: 2019-01-26
 *
 * @author lyj
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Autowired
    private SysMenuMapper menuMapper;

    /**
     * 根据用户查询菜单
     *
     * @param userId 用户信息
     * @return 菜单列表
     */
    @Override
    public List<SysMenu> selectMenusByUserId(Long userId) {
        List<SysMenu> menus = new LinkedList<SysMenu>();
        // 管理员显示所有菜单信息
        if (userId == 1L) {
            //查询所有菜单信息(不含按钮)
            menus = menuMapper.selectList(new QueryWrapper<SysMenu>().eq("menu_type", 0).orderByAsc("order_num"));
        } else {
            menus = menuMapper.selectMenusByUserId(userId);
        }
        return getChildPerms(menus, 0L);
    }

    /**
     * 查询菜单集合列表
     *
     * @return
     */
    @Override
    public List<SysMenu> selectMenusListTree() {
        //查询所有菜单信息(含按钮)
        List<SysMenu> menus = menuMapper.selectList(new QueryWrapper<SysMenu>().orderByAsc("order_num"));
        return getChildPerms(menus, 0L);
    }

    @Override
    public boolean delMenu(SysMenu menu) {
        List<SysMenu> list = new ArrayList<>();
        recursionGetList(menu, list);
        return menuMapper.deleteBatchIds(list.stream().map(SysMenu::getMenuId).collect(Collectors.toList())) > 0;
    }

    @Override
    public List<RouterVo> formatMenu(List<SysMenu> menus) {
        List<RouterVo> routers = new LinkedList<>();
        for (SysMenu menu : menus) {
            RouterVo router = new RouterVo();
            router.setName(menu.getName());
            router.setPath(menu.getPath());
            router.setComponent(StrUtil.isEmpty(menu.getComponent()) ? "Layout" : menu.getComponent());
            router.setIframe(ReUtil.isMatch(Constants.URL_RE,menu.getComponent()));
            router.setHidden(menu.isHidden());
            router.setMeta(new MetaVo(menu.getName(), menu.getIcon()));
            router.setName(menu.getName());
            List<SysMenu> cMenus = menu.getChildren();
            if (!cMenus.isEmpty() && cMenus.size() > 0) {
                router.setAlwaysShow(true);
                router.setChildren(formatMenu(cMenus));
            }
            routers.add(router);
        }
        return routers;
    }

    /**
     * 递归 获得列表
     *
     * @param menu
     */
    private void recursionGetList(SysMenu menu, List<SysMenu> list) {
        list.add(menu);
        if (!menu.getChildren().isEmpty()) {
            for (SysMenu child : menu.getChildren()) {
                recursionGetList(child, list);
            }
        }
    }

    /**
     * 根据父节点的ID获取所有子节点
     *
     * @param list     分类表
     * @param parentId 传入的父节点ID
     * @return String
     */
    public List<SysMenu> getChildPerms(List<SysMenu> list, Long parentId) {
        List<SysMenu> returnList = new ArrayList<>();
        for (Iterator<SysMenu> iterator = list.iterator(); iterator.hasNext(); ) {
            SysMenu t = iterator.next();
            // 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (t.getParentId() == parentId) {
                recursionFn(list, t);
                returnList.add(t);
            }
        }
        return returnList;
    }

    /**
     * 递归列表
     *
     * @param list
     * @param t
     */
    private void recursionFn(List<SysMenu> list, SysMenu t) {
        // 得到子节点列表
        List<SysMenu> childList = getChildList(list, t);
        t.setChildren(childList);
        for (SysMenu tChild : childList) {
            if (hasChild(list, tChild)) {
                // 判断是否有子节点
                Iterator<SysMenu> it = childList.iterator();
                while (it.hasNext()) {
                    SysMenu n = it.next();
                    recursionFn(list, n);
                }
            }
        }
    }

    /**
     * 得到子节点列表
     */
    private List<SysMenu> getChildList(List<SysMenu> list, SysMenu t) {
        List<SysMenu> tlist = new ArrayList<>();
        Iterator<SysMenu> it = list.iterator();
        while (it.hasNext()) {
            SysMenu n = it.next();
            if (n.getParentId().longValue() == t.getMenuId().longValue()) {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<SysMenu> list, SysMenu t) {
        return getChildList(list, t).size() > 0 ? true : false;
    }
}
