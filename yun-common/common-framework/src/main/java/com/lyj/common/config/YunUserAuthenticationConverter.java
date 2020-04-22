package com.lyj.common.config;
import com.lyj.common.pojo.YunUserDetail;
import com.lyj.constants.SecurityConstants;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.token.UserAuthenticationConverter;
import org.springframework.util.StringUtils;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Default implementation of {@link UserAuthenticationConverter}. Converts to and from an Authentication using only its
 * name and authorities.
 * 
 * @author Dave Syer
 * 
 */
public class YunUserAuthenticationConverter implements UserAuthenticationConverter {

	private Collection<? extends GrantedAuthority> defaultAuthorities=new HashSet<>();

	public Map<String, ?> convertUserAuthentication(Authentication authentication) {
		Map<String, Object> response = new LinkedHashMap<String, Object>();
		response.put(USERNAME, authentication.getName());
		if (authentication.getAuthorities() != null && !authentication.getAuthorities().isEmpty()) {
			response.put(AUTHORITIES, AuthorityUtils.authorityListToSet(authentication.getAuthorities()));
		}
		return response;
	}

	public Authentication extractAuthentication(Map<String, ?> map) {
        if (map.containsKey(USERNAME)) {
            Collection<? extends GrantedAuthority> authorities = getAuthorities(map);
            String username = (String) map.get(USERNAME);
            Long userId = Long.parseLong(map.get(SecurityConstants.USER_ID_KEY).toString()) ;
            YunUserDetail yunUserDetail = new YunUserDetail(userId, username, SecurityConstants.N_A, true
                    , true, true, true, authorities);
            return new UsernamePasswordAuthenticationToken(yunUserDetail, SecurityConstants.N_A, authorities);
        }
        return null;
	}

	private Collection<? extends GrantedAuthority> getAuthorities(Map<String, ?> map) {
		if (!map.containsKey(AUTHORITIES)) {
			return defaultAuthorities;
		}
		Object authorities = map.get(AUTHORITIES);
		if (authorities instanceof String) {
			return AuthorityUtils.commaSeparatedStringToAuthorityList((String) authorities);
		}
		if (authorities instanceof Collection) {
			return AuthorityUtils.commaSeparatedStringToAuthorityList(StringUtils
					.collectionToCommaDelimitedString((Collection<?>) authorities));
		}
		throw new IllegalArgumentException("Authorities must be either a String or a Collection");
	}
}
