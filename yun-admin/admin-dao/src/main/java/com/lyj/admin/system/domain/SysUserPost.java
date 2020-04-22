package com.lyj.admin.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * <p>
 * 用户与岗位关联表
 * </p>
 *
 * @author lyj
 * @date 2019-02-23
 */
@Data
public class SysUserPost extends Model<SysUserPost> {

    /**
     * 用户ID
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

    /**
     * 岗位ID
     */
    private Long postId;

}
