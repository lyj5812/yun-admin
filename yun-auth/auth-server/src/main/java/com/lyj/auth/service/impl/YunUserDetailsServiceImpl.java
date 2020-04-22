package com.lyj.auth.service.impl;
import com.lyj.admin.system.client.UserClient;
import com.lyj.admin.system.client.WxUserClient;
import com.lyj.admin.system.domain.SysUser;
import com.lyj.admin.system.domain.SysWxMiniUser;
import com.lyj.admin.system.dto.UserInfo;
import com.lyj.auth.service.YunUserDetailsService;
import com.lyj.common.pojo.YunUserDetail;
import com.lyj.constants.SecurityConstants;
import com.lyj.pojo.R;
import com.lyj.wxma.config.WxMaConfiguration;
import com.lyj.wxmp.config.WxMpConfiguration;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Set;
import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import cn.hutool.http.HttpStatus;
import lombok.SneakyThrows;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;

/**
 * @author lyj
 */
@Service
public class YunUserDetailsServiceImpl implements YunUserDetailsService {

    @Autowired
    private UserClient userClient;

    @Autowired
    private WxUserClient wxUserClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        R<UserInfo> result = userClient.getUserInfo(username,true);
        UserInfo userInfo = result.getData();
        if (result.getCode() != HttpStatus.HTTP_OK) {
            throw new InvalidGrantException("您输入的用户不存在");
        }

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        // 可用性 :true:可用 false:不可用
        boolean enabled = true;
        // 过期性 :true:没过期 false:过期
        boolean accountNonExpired = true;
        // 有效性 :true:凭证有效 false:凭证无效
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        // 锁定性 :true:未锁定 false:已锁定
        SysUser sysUser = userInfo.getSysUser();

        //用户角色权限
        for (String roleKey : userInfo.getRoleKeys()) {
            GrantedAuthority authority = new SimpleGrantedAuthority(SecurityConstants.ROLE_PREFIX+roleKey);
            grantedAuthorities.add(authority);
        }

        //用户按钮权限
        for (String perms : userInfo.getPerms()) {
            GrantedAuthority authority = new SimpleGrantedAuthority(perms);
            grantedAuthorities.add(authority);
        }

        return new YunUserDetail(sysUser.getUserId(), sysUser.getUsername(), sysUser.getPassword(), enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, grantedAuthorities);
    }

    /**
     * 微信网页扫码登录 TODO
     *
     * @param code
     * @return
     */
    @Override
    public UserDetails loadUserByWxWeb(String appid, String code) {
        final WxMpService wxService = WxMpConfiguration.getMpService(appid);

        try {
            WxMpOAuth2AccessToken accessToken = wxService.oauth2getAccessToken(code);
            WxMpUser wxMpUser = wxService.oauth2getUserInfo(accessToken, null);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @SneakyThrows
    public UserDetails loadUserByWxMiniApp(String appid, String code, String encryptedData, String iv) {
        final WxMaService wxService = WxMaConfiguration.getMaService(appid);
        WxMaJscode2SessionResult session = wxService.getUserService().getSessionInfo(code);
        // 解密用户信息
        WxMaUserInfo userInfo = wxService.getUserService().getUserInfo(session.getSessionKey(), encryptedData, iv);

        SysWxMiniUser miniUser = new SysWxMiniUser();
        BeanUtils.copyProperties(userInfo,miniUser);
        R<SysWxMiniUser> userResult = wxUserClient.findByMaUserInfo(miniUser,true);

        if (userResult.getCode() != HttpStatus.HTTP_OK) {
            throw new InvalidGrantException("查询用户失败");
        }

        SysWxMiniUser user = userResult.getData();
        // 可用性 :true:可用 false:不可用
        boolean enabled = true;
        // 过期性 :true:没过期 false:过期
        boolean accountNonExpired = true;
        // 有效性 :true:凭证有效 false:凭证无效
        boolean credentialsNonExpired = true;

        boolean accountNonLocked = true;
        // 锁定性 :true:未锁定 false:已锁定

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        return new YunUserDetail(user.getWxMiniUserId(), SecurityConstants.N_A, SecurityConstants.N_A, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, grantedAuthorities);
    }
}