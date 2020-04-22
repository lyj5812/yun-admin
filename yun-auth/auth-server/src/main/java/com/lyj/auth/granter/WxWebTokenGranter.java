package com.lyj.auth.granter;
import com.lyj.auth.service.YunUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;

import java.util.Map;

/**
 * @Author: lyj
 * @Date: 2019/9/25
 * @Description: 微信网页登录
 */
public class WxWebTokenGranter extends AbstractCustomTokenGranter {

    protected YunUserDetailsService yunUserDetailsService;

    public WxWebTokenGranter(YunUserDetailsService yunUserDetailsService, AuthorizationServerTokenServices tokenServices, ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory) {
        super(tokenServices, clientDetailsService, requestFactory, "wx_web");
        this.yunUserDetailsService = yunUserDetailsService;
    }

    @Override
    protected UserDetails getUserDetails(Map<String, String> parameters) {
        String code = parameters.get("code");
        String appid = parameters.get("appid");
        return yunUserDetailsService.loadUserByWxWeb(appid,code);
    }

}
