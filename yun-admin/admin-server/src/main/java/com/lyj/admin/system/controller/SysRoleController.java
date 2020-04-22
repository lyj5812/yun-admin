package com.lyj.admin.system.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyj.admin.system.domain.SysRole;
import com.lyj.admin.system.service.SysRoleMenuService;
import com.lyj.admin.system.service.SysRoleService;
import com.lyj.pojo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @date: 2019-04-17
 * @author lyj
 */
@Api(tags = "角色模块")
@RestController
@RequestMapping("/role")
public class SysRoleController {
    @Autowired
    private SysRoleService roleService;

    @Autowired
    private SysRoleMenuService roleMenuService;

    @ApiOperation("根据用户ID获取角色列表")
    @GetMapping("/getRoleByUserId/{userId}")
    public R<List<SysRole>> getRoleByUserId(@PathVariable("userId") Long userId) {
        return R.ok(roleService.getRoleByUserId(userId));
    }

    @ApiOperation("角色分页列表")
    @GetMapping("/roleListPage")
    public R<IPage<SysRole>> roleListPage(Page page, SysRole role) {
        return R.ok(roleService.selectRoleListPage(page, role));
    }

    @ApiOperation("删除")
    @DeleteMapping("/delByIds")
    public R deleteList(@RequestBody List<Long> ids) {
        int i = roleService.deleteList(ids);
        if (i > 0) {
            return R.ok();
        } else if (i == -1) {
            return R.error("删除失败!有角色已分配");
        } else {
            return R.error("删除失败");
        }

    }

    @ApiOperation("添加或者修改")
    @PostMapping("/addOrEdit")
    public R addOrEdit(@RequestBody SysRole role) {
        return roleService.addOrEdit(role)>0?R.ok():R.error();
    }

    @ApiOperation("查询角色列表")
    @GetMapping("/getRoleList")
    public R<List<SysRole>> getRoleList() {
        return R.ok(roleService.list(new QueryWrapper<SysRole>().eq("status", "0").orderByAsc("role_sort")));
    }

    /**
     * 根据角色id查询菜单id
     */
    @ApiOperation("根据角色id查询菜单id")
    @GetMapping("/menuIdsByRoleId")
    public R<List<Long>> menuIdsByRoleId(@RequestParam Long roleId) {
        List<Long> list = roleMenuService.menuIdsByRoleId(roleId);
        return R.ok(list);
    }

}
