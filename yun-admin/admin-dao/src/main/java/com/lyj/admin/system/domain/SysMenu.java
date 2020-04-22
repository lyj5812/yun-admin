package com.lyj.admin.system.domain;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.lyj.common.po.BaseEntity;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * <p>
 * 菜单权限表
 * </p>
 *
 * @author lyj
 * @date 2019-02-24
 */

@Data
public class SysMenu extends BaseEntity {

    /**
     * 菜单ID
     */
    @TableId(value = "menu_id", type = IdType.AUTO)
    private Long menuId;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 父菜单ID
     */
    private Long parentId;

    /**
     * VUE页面
     */
    private String component;

    /**
     * 显示顺序
     */
    private Integer orderNum;

    /**
     * 请求地址
     */
    private String path;

    /**
     * 菜单类型（0目录 1菜单 2按钮）
     */
    private String menuType;

    /**
     * 菜单状态（0显示 1隐藏）
     */
    private boolean hidden;

    /**
     * 权限标识
     */
    private String perms;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 子菜单
     */
    @TableField(exist = false)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<SysMenu> children = new ArrayList<SysMenu>();

}
