package com.lyj.common.utils;
import com.lyj.common.pojo.YunUserDetail;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import lombok.experimental.UtilityClass;

/**
 * @author lyj
 * @date 2019-10-14
 */
@UtilityClass
public class UserUtils {
	/**
	 * 获取Authentication
	 */
	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	/**
	 * 获取用户
	 *
	 * @param authentication
	 * @return user
	 * <p>
	 */
	public YunUserDetail getUser(Authentication authentication) {
        Object principal = authentication.getPrincipal();
        if (principal instanceof YunUserDetail) {
            return (YunUserDetail) principal;
        }
        return null;
	}

    /**
     * 获取用户
     *
     * @return YunUserDetail
     * <p>
     */
	public YunUserDetail getUser() {
		Authentication authentication = getAuthentication();
		return getUser(authentication);
	}

    /**
     * 获取用户id
     *
     * @return userId
     * <p>
     */
    public Long getUserId() {
        Authentication authentication = getAuthentication();
        return getUser(authentication).getUserId();
    }


}
