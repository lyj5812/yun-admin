package com.lyj.admin.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.lyj.common.po.BaseEntity;

import java.util.Date;

import lombok.Data;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author lyj
 * @date 2019-02-23
 */
@Data
public class SysUser extends BaseEntity {

    /**
     * 用户ID
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

    /**
     * 部门ID
     */
    private Long deptId;

    /**
     * 登录账号
     */
    private String username;

    /**
     * 用户昵称
     */
    private String realName;

    /**
     * 用户类型（00系统用户）
     */
    private String userType;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 密码
     */
    private String password;

    /**
     * 盐加密
     */
    private String salt;

    /**
     * 帐号状态（0正常 1停用）
     */
    private String status;

    /**
     * 手机号码
     */
    private String phonenumber;

    /**
     * 用户性别（1男 0女）
     */
    private String sex;

    /**
     * 头像路径
     */
    private String avatar;


    /**
     * 最后登陆IP
     */
    private String loginIp;

    /**
     * 最后登陆时间
     */
    private Date loginDate;

    /**
     * 小程序openId
     */
    private String maOpenId;

    /**
     * 角色ids
     */
    @TableField(exist = false)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Long[] roleIds;

    /**
     * 岗位ids
     */
    @TableField(exist = false)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Long[] postIds;

}
