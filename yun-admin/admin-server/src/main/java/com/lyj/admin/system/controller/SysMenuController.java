package com.lyj.admin.system.controller;
import com.lyj.admin.system.domain.SysMenu;
import com.lyj.admin.system.service.SysMenuService;
import com.lyj.admin.system.vo.RouterVo;
import com.lyj.common.utils.UserUtils;
import com.lyj.pojo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @date: 2019-01-26
 * @author lyj
 */
@Api(tags = "菜单模块")
@RestController
@RequestMapping("/menu")
public class SysMenuController {

    @Autowired
    private SysMenuService menuService;

    /**
     * 加载用户菜单列表树
     */
    @ApiOperation("当前用户菜单列表树")
    @GetMapping
    public R<List<RouterVo>> menu() {
        List<SysMenu> tree = menuService.selectMenusByUserId(UserUtils.getUserId());
        return R.ok(menuService.formatMenu(tree));
    }

    /**
     * 加载菜单列表树
     */
    @ApiOperation("菜单列表树")
    @GetMapping("/menuList")
    public R<List<SysMenu>> menuListTree() {
        List<SysMenu> tree = menuService.selectMenusListTree();
        return R.ok(tree);
    }

    /**
     * 添加或修改
     */
    @ApiOperation("添加或修改")
    @PutMapping("/menuAddOrEdit")
    public R menuAddOrEdit(@RequestBody SysMenu menu){
        return R.ok(menuService.saveOrUpdate(menu));
    }

    /**
     * 删除
     */
    @ApiOperation("删除")
    @DeleteMapping("/delMenu")
    public R delMenu(@RequestBody SysMenu menu){
        return R.ok(menuService.delMenu(menu));
    }

    /**
     * 根据ID查询菜单
     */
    @ApiOperation("根据ID查询菜单")
    @GetMapping("/getMenuById/{menuId}")
    public R<SysMenu> getMenuById(@PathVariable("menuId") Long menuId) {
        return R.ok(menuService.getById(menuId));
    }
}