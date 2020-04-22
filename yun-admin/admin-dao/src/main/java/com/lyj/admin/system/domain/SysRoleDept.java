package com.lyj.admin.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;

import lombok.Data;

/**
 * <p>
 * 角色和部门关联表
 * </p>
 *
 * @author lyj
 * @date 2019-02-23
 */
@Data
public class SysRoleDept extends Model<SysRoleDept> {

    /**
     * 角色ID
     */
    @TableId(value = "role_id", type = IdType.AUTO)
    private Integer roleId;

    /**
     * 部门ID
     */
    private Integer deptId;

}
