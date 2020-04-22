package com.lyj.auth.granter;
import com.lyj.auth.service.YunUserDetailsService;
import com.lyj.constants.SecurityConstants;

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
public class WxMiniAppTokenGranter extends AbstractCustomTokenGranter {

    protected YunUserDetailsService yunUserDetailsService;

    public WxMiniAppTokenGranter(YunUserDetailsService yunUserDetailsService, AuthorizationServerTokenServices tokenServices, ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory) {
        super(tokenServices, clientDetailsService, requestFactory, SecurityConstants.WX_MINI_APP_GRANT);
        this.yunUserDetailsService = yunUserDetailsService;
    }

    @Override
    protected UserDetails getUserDetails(Map<String, String> parameters) {
        String appid = parameters.get("appid");
        String code = parameters.get("code");
        String encryptedData = parameters.get("encryptedData");
        String iv = parameters.get("iv");
        return yunUserDetailsService.loadUserByWxMiniApp(appid,code,encryptedData,iv);
    }

}
