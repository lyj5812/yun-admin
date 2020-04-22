package com.lyj.admin.system.domain;
import com.baomidou.mybatisplus.annotation.IdType;
import com.lyj.common.po.BaseEntity;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableId;;

/**
 * <p>
 * 通知公告表 实体
 * </p>
 *
 * @author lyj
 * @date 2020-04-18
 */
@Data
@ApiModel("通知公告表实体")
public class SysNotice extends BaseEntity {

    @ApiModelProperty("公告内容")
    private String noticeContent;

    @ApiModelProperty("公告标题")
    private String noticeTitle;

    @TableId(value = "notice_id", type = IdType.AUTO )
    @ApiModelProperty("公告ID")
    private Integer noticeId;

    @ApiModelProperty("公告状态（0正常 1关闭）")
    private String status;

    @ApiModelProperty("公告类型（1通知 2公告）")
    private String noticeType;
}
