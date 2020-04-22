package com.lyj.common.pojo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import java.util.Collection;

import lombok.Getter;

/**
 *
 * @author lyj
 * @date 2019/2/20
 * 包装org.springframework.security.core.userdetails.User类
 */
public class YunUserDetail extends User {

    @Getter
    private Long userId;

    public YunUserDetail(Long userId, String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.userId = userId;
    }
}
