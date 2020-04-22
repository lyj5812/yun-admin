package com.lyj.admin.system.client;
import com.lyj.admin.system.domain.SysWxMiniUser;
import com.lyj.constants.SecurityConstants;
import com.lyj.pojo.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * @author lyj
 */
@FeignClient(contextId = "wxUserService", value = "admin-server")
public interface WxUserClient {

    /**
     * @param miniUser
     * @return R<SysWxMiniUser>
     */
    @PostMapping("/wxMiniUser/findByMaUserInfo")
    R<SysWxMiniUser> findByMaUserInfo(@RequestBody SysWxMiniUser miniUser, @RequestHeader(SecurityConstants.AUTH_INNER_KEY) Boolean isInner);

}
