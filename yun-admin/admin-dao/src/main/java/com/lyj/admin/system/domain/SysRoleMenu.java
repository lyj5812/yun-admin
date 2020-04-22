package com.lyj.admin.system.domain;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * <p>
 * 角色和菜单关联表
 * </p>
 *
 * @author lyj
 * @date 2019-02-24
 */

@Data
public class SysRoleMenu extends Model<SysRoleMenu> {

    /**
     * 角色ID
     */
    @TableId(value = "role_id", type = IdType.AUTO)
    private Long roleId;

    /**
     * 菜单ID
     */
    private Long menuId;

}
