package com.lyj.admin.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.lyj.common.po.BaseEntity;

import lombok.Data;

/**
 * <p>
 * 岗位信息表
 * </p>
 *
 * @author lyj
 * @date 2019-02-23
 */

@Data
public class SysPost extends BaseEntity {

    /**
     * 岗位ID
     */
    @TableId(value = "post_id", type = IdType.AUTO)
    private Integer postId;

    /**
     * 岗位编码
     */
    private String postCode;

    /**
     * 岗位名称
     */
    private String postName;

    /**
     * 显示顺序
     */
    private Integer postSort;

    /**
     * 状态（0正常 1停用）
     */
    private String status;

}
