package com.lyj.auth.granter;
import com.lyj.auth.service.YunUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import java.util.Map;

/**
 * @Author: lyj
 * @Date: 2019/9/25
 * @Description: 用户名密码登录
 */
public class UsernamePasswordTokenGranter extends AbstractCustomTokenGranter {

    protected YunUserDetailsService yunUserDetailsService;

    private static final String GRANT_TYPE = "password";

    public UsernamePasswordTokenGranter(YunUserDetailsService yunUserDetailsService, AuthorizationServerTokenServices tokenServices, ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory) {
        super(tokenServices, clientDetailsService, requestFactory, GRANT_TYPE);
        this.yunUserDetailsService = yunUserDetailsService;
    }

    @Override
    protected UserDetails getUserDetails(Map<String, String> parameters) {
        String username = parameters.get("username");
        String password = parameters.get("password");
        // 防止密码泄露
        parameters.remove("password");
        UserDetails userDetails=yunUserDetailsService.loadUserByUsername(username);
        if (!new BCryptPasswordEncoder().matches(password, userDetails.getPassword())){
            throw new InvalidGrantException("您输入的用户名或者密码不正确！");
        }
        return userDetails;
    }

}
