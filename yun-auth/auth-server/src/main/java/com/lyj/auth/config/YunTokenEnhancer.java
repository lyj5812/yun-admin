package com.lyj.auth.config;
import com.lyj.common.pojo.YunUserDetail;
import com.lyj.constants.SecurityConstants;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lyj
 */
public class YunTokenEnhancer implements TokenEnhancer {

    /**
     * jwt增强器
     * @param accessToken
     * @param authentication
     * @return
     */
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

        if (SecurityConstants.CLIENT_CREDENTIALS
                .equals(authentication.getOAuth2Request().getGrantType())) {
            return accessToken;
        }
        YunUserDetail yunUserDetail = (YunUserDetail) authentication.getPrincipal();
        //往jwt添加的自定义信息
        Map<String , Object> info = new HashMap<>();
        info.put(SecurityConstants.USER_ID_KEY, yunUserDetail.getUserId());
        info.put("QQ", "1264415695");
        info.put("author", "lyj");
        ((DefaultOAuth2AccessToken)accessToken).setAdditionalInformation(info);
        return accessToken;
    }
}
