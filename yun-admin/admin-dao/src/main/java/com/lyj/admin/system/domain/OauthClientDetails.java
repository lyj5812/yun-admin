package com.lyj.admin.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * <p>
 * 客户端详情表
 * </p>
 *
 * @author lyj
 * @date 2019-02-23
 */
@Data
public class OauthClientDetails extends Model<OauthClientDetails> {

    /**
     * 客户端id
     */
    @TableId(value = "client_id", type = IdType.AUTO)
    private String clientId;

    private String resourceIds;

    /**
     * 客户端安全码
     */
    private String clientSecret;

    /**
     * 允许请求范围
     */
    private String scope;

    /**
     * 客户端可以使用的授权类型
     */
    private String authorizedGrantTypes;

    /**
     * 回调地址
     */
    private String webServerRedirectUri;

    private String authorities;

    private Integer accessTokenValidity;

    private Integer refreshTokenValidity;

    private String additionalInformation;

    private String autoapprove;

}
