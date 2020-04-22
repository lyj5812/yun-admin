package com.lyj.auth.service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface YunUserDetailsService extends UserDetailsService {

    /**
     * 微信网页登录
     * @param appid
     * @param code
     * @return UserDetails
     * @throws UsernameNotFoundException
     */
    UserDetails loadUserByWxWeb(String appid, String code);

    UserDetails loadUserByWxMiniApp(String appid, String code, String encryptedData, String iv);
}
