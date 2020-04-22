package com.lyj.admin.system.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyj.admin.system.dto.UserInfo;
import com.lyj.admin.system.domain.SysRole;
import com.lyj.admin.system.domain.SysUser;
import com.lyj.admin.system.service.SysDeptService;
import com.lyj.admin.system.service.SysPostService;
import com.lyj.admin.system.service.SysRoleService;
import com.lyj.admin.system.service.SysUserService;
import com.lyj.common.annotation.AuthIgnore;
import com.lyj.common.utils.UserUtils;
import com.lyj.pojo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Description:
 * Date: 2018-12-13
 * @author lyj
 */
@Api(tags = "用户模块")
@RestController
@RequestMapping("/user")
public class SysUserController {

    @Autowired
    private SysUserService userService;

    @Autowired
    private SysRoleService roleService;

    @Autowired
    private SysPostService postService;

    @Autowired
    private SysDeptService deptService;

    /**
     * 根据用户名查询
     * @param username
     * @return
     */
    @ApiOperation("根据用户名查询")
    @AuthIgnore
    @GetMapping("/findByUsername/{username}")
    public R<SysUser> findByUsername(@PathVariable("username") String username){
        SysUser user = userService.getOne(Wrappers.<SysUser>query()
                .lambda().eq(SysUser::getUsername, username));
        if (user == null) {
            return R.error("获取用户信息失败");
        }
        return R.ok(userService.getUserInfo(user));
    }

    /**
     * 获取当前用户全部信息
     *
     * @return 用户信息
     */
    @ApiOperation("当前用户信息")
    @GetMapping(value = {"/info"})
    public R<UserInfo> info() {
        String username = UserUtils.getUser().getUsername();
        SysUser user = userService.getOne(Wrappers.<SysUser>query()
                .lambda().eq(SysUser::getUsername, username));
        if (user == null) {
            return R.error("获取当前用户信息失败");
        }
        return R.ok(userService.getUserInfo(user));
    }

    /**
     * 校验用户名
     * @param username
     * @return
     */
    @ApiOperation("校验用户名")
    @GetMapping("/checkUsername/{username}")
    public R<SysUser> checkUsername(@PathVariable("username") String username){
        return R.ok(userService.getOne(new QueryWrapper<SysUser>().eq("username",username)));
    }

    /**
     * 分页查询
     * @param page
     * @return
     */
    @ApiOperation("用户分页查询")
    @GetMapping("/userListPage")
    @PreAuthorize("hasAuthority('system:user:search')")
    public R<IPage<SysUser>> userListPage(Page page, SysUser user){
        return R.ok(userService.selectUserListPage(page,user));
    }

    /**
     * 根据用户id 查询 角色id 岗位id 部门信息
     * @param userId
     * @return
     */
    @ApiOperation("根据用户id查询信息")
    @GetMapping("/roleIdsAndPostIdsAndDeptByUserId/{userId}")
    public R<Map<String,Object>> roleIdsAndPostIdsByUserId(@PathVariable("userId") Long userId){
        Map<String,Object> map =new HashMap<>();
        map.put("roleIds",roleService.getRoleByUserId(userId).stream().map(SysRole::getRoleId).collect(Collectors.toList()));
        map.put("postIds",postService.getPostIdsByUserId(userId));
        map.put("dept",deptService.getDeptByUserId(userId));
        return R.ok(map);
    }

    /**
     * 添加或修改
     */
    @ApiOperation("添加或修改")
    @PutMapping("/userAddOrEdit")
    public R userAddOrEdit(@RequestBody SysUser user){
        return R.ok(userService.saveOrUpdateUser(user));
    }

    /**
     * 删除
     * @param users
     * @return
     */
    @ApiOperation("删除")
    @DeleteMapping("/deleteList")
    public R deleteList(@RequestBody List<SysUser> users){
        boolean flag = userService.removeByIds(users.stream().map(SysUser::getUserId).collect(Collectors.toList()));
        return flag?R.ok():R.error("删除失败");
    }

    /**
     * 修改密码
     */
    @ApiOperation("修改密码")
    @PutMapping("/editPwd")
    public R editPwd(String oldPassword, String newPassword){
        SysUser user = userService.getById(UserUtils.getUserId());
        if (!new BCryptPasswordEncoder().matches(oldPassword, user.getPassword())){
            return R.error("修改密码失败，旧密码错误");
        }
        if (new BCryptPasswordEncoder().matches(newPassword, user.getPassword())){
            return R.error("新密码不能与旧密码相同");
        }
        return R.ok(userService.resetPwd(UserUtils.getUser().getUsername(), newPassword));
    }

    /**
     * 修改个人信息
     */
    @ApiOperation("修改个人信息")
    @PutMapping("/edit")
    public R edit(@RequestBody SysUser user){
        return R.ok(userService.updateById(user));
    }
}
