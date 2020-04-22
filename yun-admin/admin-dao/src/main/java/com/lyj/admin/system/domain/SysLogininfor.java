package com.lyj.admin.system.domain;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.util.Date;

import lombok.Data;

/**
 * <p>
 * 系统访问记录
 * </p>
 *
 * @author lyj
 * @date 2019-02-23
 */
@Data
public class SysLogininfor extends Model<SysLogininfor> {

    /**
     * 访问ID
     */
    @TableId(value = "info_id", type = IdType.AUTO)
    private Integer infoId;

    /**
     * 登录账号
     */
    private String loginName;

    /**
     * 登录IP地址
     */
    private String ipaddr;

    /**
     * 登录地点
     */
    private String loginLocation;

    /**
     * 浏览器类型
     */
    private String browser;

    /**
     * 操作系统
     */
    private String os;

    /**
     * 登录状态（0成功 1失败）
     */
    private String status;

    /**
     * 提示消息
     */
    private String msg;

    /**
     * 访问时间
     */
    private Date loginTime;
}
