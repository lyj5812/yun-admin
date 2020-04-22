package com.lyj.admin.system.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.lyj.common.annotation.AuthIgnore;
import com.lyj.common.utils.UserUtils;
import com.lyj.pojo.R;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyj.admin.system.domain.SysWxMiniUser;
import com.lyj.admin.system.service.SysWxMiniUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 *
 * 微信小程序用户表 Controller
 * @author lyj
 * @date 2019-10-25
 */
@Api(tags = "微信小程序用户模块")
@RestController
@RequestMapping("/wxMiniUser")
public class SysWxMiniUserController {

    @Autowired
    private SysWxMiniUserService sysWxMiniUserService;

    /**
     * 查询分页列表
     */
    @ApiOperation("分页列表")
    @GetMapping("/listPage")
    public R<IPage<SysWxMiniUser>> listPage(Page page,SysWxMiniUser sysWxMiniUser) {
        return R.ok(sysWxMiniUserService.selectSysWxMiniUserListPage(page, sysWxMiniUser));
    }

    /**
     * 新增保存
     */
    @ApiOperation("新增修改")
    @PostMapping("/addOrEdit")
    public R addOrEdit(SysWxMiniUser sysWxMiniUser) {
        return R.ok(sysWxMiniUserService.saveOrUpdate(sysWxMiniUser));
    }

    /**
     * 删除
     */
    @ApiOperation("删除")
    @DeleteMapping( "/delete")
    public R delete(@RequestBody List<Long> ids) {
        return R.ok(sysWxMiniUserService.removeByIds(ids));
    }

    /**
     * 根据小程序openId查询
     * @param miniUser
     * @return
     */
    @ApiOperation("小程序openId查询")
    @AuthIgnore
    @PostMapping("/findByMaUserInfo")
    R<SysWxMiniUser> findByMaUserInfo(@RequestBody SysWxMiniUser miniUser){
        return R.ok(sysWxMiniUserService.findByMaUserInfo(miniUser));
    }

    /**
     * 小程序用户信息
     * @return
     */
    @ApiOperation("小程序用户信息")
    @GetMapping("/info")
    @PreAuthorize("#oauth2.hasScope('wx_mini_app')")
    public R<SysWxMiniUser> findByUserId(){
        return R.ok(sysWxMiniUserService.getById(UserUtils.getUserId()));
    }
}