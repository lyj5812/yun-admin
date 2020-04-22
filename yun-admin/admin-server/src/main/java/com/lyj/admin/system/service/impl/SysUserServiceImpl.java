package com.lyj.admin.system.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyj.admin.system.domain.SysRole;
import com.lyj.admin.system.dto.UserInfo;
import com.lyj.admin.system.mapper.SysMenuMapper;
import com.lyj.admin.system.mapper.SysRoleMapper;
import com.lyj.admin.system.mapper.SysUserMapper;
import com.lyj.admin.system.mapper.SysUserPostMapper;
import com.lyj.admin.system.mapper.SysUserRoleMapper;
import com.lyj.admin.system.domain.SysUser;
import com.lyj.admin.system.domain.SysUserPost;
import com.lyj.admin.system.domain.SysUserRole;
import com.lyj.admin.system.service.SysUserService;
import com.lyj.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Description:
 * Date: 2019-01-26
 * Time: 19:57
 *
 * @author lyj
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private SysUserRoleMapper userRoleMapper;

    @Autowired
    private SysUserPostMapper userPostMapper;

    @Autowired
    private SysRoleMapper roleMapper;

    @Autowired
    private SysMenuMapper menuMapper;

    @Override
    public boolean saveOrUpdateUser(SysUser user) {
        if (StringUtils.isNotNull(user.getUserId())) {
            // 删除用户与角色关联
            userRoleMapper.delete(new QueryWrapper<SysUserRole>().eq("user_id", user.getUserId()));
            // 删除用户与岗位关联
            userPostMapper.delete(new QueryWrapper<SysUserPost>().eq("user_id", user.getUserId()));
        } else {
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        }
        boolean flag = this.saveOrUpdate(user);
        // 新增用户与角色管理
        insertUserRole(user);
        // 新增用户与岗位管理
        insertUserPost(user);
        return flag;
    }

    @Override
    public UserInfo getUserInfo(SysUser sysUser) {
        UserInfo userInfo = new UserInfo();
        userInfo.setSysUser(sysUser);
        //设置角色标识
        String[] roleKeys = roleMapper.getRoleByUserId(sysUser.getUserId()).stream().map(SysRole::getRoleKey).collect(Collectors.toList()).stream().toArray(String[]::new);
        userInfo.setRoleKeys(roleKeys);

        String[] perms = menuMapper.selectBtnPermissionsByUserId(sysUser.getUserId());
        //设置权限列表（menu.permission）
        userInfo.setPerms(perms);
        return userInfo;
    }

    @Override
    public IPage<SysUser> selectUserListPage(Page page, SysUser user) {
        return userMapper.selectUserListPage(page,user);
    }

    @Override
    public boolean resetPwd(String username, String newPassword) {
        return userMapper.update(null, Wrappers.<SysUser>update().eq("username",username).set("password",new BCryptPasswordEncoder().encode(newPassword)))>0;
    }

    /**
     * 新增用户角色信息
     *
     * @param user 用户对象
     */
    public void insertUserRole(SysUser user) {
        Long[] roles = user.getRoleIds();
        if (StringUtils.isNotNull(roles)) {
            // 新增用户与角色管理
            List<SysUserRole> list = new ArrayList<SysUserRole>();
            for (Long roleId : roles) {
                SysUserRole ur = new SysUserRole();
                ur.setUserId(user.getUserId());
                ur.setRoleId(roleId);
                list.add(ur);
            }
            if (list.size() > 0) {
                userRoleMapper.batchUserRole(list);
            }
        }
    }

    /**
     * 新增用户岗位信息
     *
     * @param user 用户对象
     */
    public void insertUserPost(SysUser user) {
        Long[] posts = user.getPostIds();
        if (StringUtils.isNotNull(posts)) {
            // 新增用户与岗位管理
            List<SysUserPost> list = new ArrayList<SysUserPost>();
            for (Long postId : posts) {
                SysUserPost up = new SysUserPost();
                up.setUserId(user.getUserId());
                up.setPostId(postId);
                list.add(up);
            }
            if (list.size() > 0) {
                userPostMapper.batchUserPost(list);
            }
        }
    }
}
