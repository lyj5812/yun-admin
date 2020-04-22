package com.lyj.admin.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.lyj.common.po.BaseEntity;

import lombok.Data;

/**
 * <p>
 * 字典类型表
 * </p>
 *
 * @author lyj
 * @date 2019-02-23
 */

@Data
public class SysDictType extends BaseEntity {

    /**
     * 字典主键
     */
    @TableId(value = "dict_id", type = IdType.AUTO)
    private Integer dictId;

    /**
     * 字典名称
     */
    private String dictName;

    /**
     * 字典类型
     */
    private String dictType;

    /**
     * 状态（0正常 1停用）
     */
    private String status;

}
