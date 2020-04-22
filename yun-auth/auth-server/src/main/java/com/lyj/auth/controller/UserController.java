package com.lyj.auth.controller;
import com.lyj.common.pojo.YunUserDetail;
import com.lyj.pojo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author  lyj
 * @date 2019.06.11
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private ConsumerTokenServices consumerTokenServices;

    @GetMapping(value = "/info")
    public YunUserDetail userInfo(Authentication authentication){
        YunUserDetail principal = (YunUserDetail) authentication.getPrincipal();
        return principal;
    }


    @DeleteMapping(value = "/logout")
    public R revokeToken(@RequestBody String accessToken) {
        if (consumerTokenServices.revokeToken(accessToken)) {
            return R.ok("退出登录成功");
        } else {
            return R.error("退出登录失败");
        }
    }
}