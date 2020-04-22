package com.lyj.admin.system.domain;
import com.baomidou.mybatisplus.annotation.IdType;
import com.lyj.common.po.BaseEntity;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableId;
import java.util.Date;

/**
 * <p>
 * 微信小程序用户表 对象
 * </p>
 *
 * @author lyj
 * @date 2019-10-25
 */
@Data
public class SysWxMiniUser extends BaseEntity {

    /**
     * 主键ID
     */
    @TableId(value = "wx_mini_user_id", type = IdType.AUTO )
    private Long wxMiniUserId;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 头像
     */
    private String avatarUrl;

    /**
     * 手机
     */
    private String mobile;

    /**
     * openId
     */
    private String openId;

    /**
     * 
     */
    private String subscribe;

    /**
     * 省
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 区县
     */
    private String county;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 性别
     */
    private String gender;

    /**
     * 
     */
    private Date birthday;
}
