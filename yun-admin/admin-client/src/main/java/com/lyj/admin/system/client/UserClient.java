package com.lyj.admin.system.client;
import com.lyj.admin.system.dto.UserInfo;
import com.lyj.constants.SecurityConstants;
import com.lyj.pojo.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * @author lyj
 */
@FeignClient(contextId = "userService", value = "admin-server")
public interface UserClient{

    @GetMapping("/user/findByUsername/{username}")
    R<UserInfo> getUserInfo(@PathVariable("username") String username, @RequestHeader(SecurityConstants.AUTH_INNER_KEY) Boolean isInner);
}
